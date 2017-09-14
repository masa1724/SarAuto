 package sar.page.common;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import library.WebDriverUtil;
import main.ProcKind;
import sar.page.geppou.GeppouListPage;
import sar.page.nippou.NippouListPage;
import sar.page.notloginpage.SARLoginPage;
import sar.page.shuuhou.keikaku.ShuuhouKeikakuListPage;

/** SARページ親クラス */
public abstract class AbstractSARPage extends WebDriverUtil implements SARPageHeader {
	/** ページ読込待機時間(日報)　*/
	public static final long WAIT_TIME_NIPPOU = 11000L;
	/** ページ読込待機時間(週報)　*/
	public static final long WAIT_TIME_SHUHOU = 6000L;
	/** ページ読込待機時間(月報)　*/
	public static final long WAIT_TIME_GEPPOU = 8000L;
	
	protected Map<String,String> _dateElementIdList = new LinkedHashMap<String,String>();
	
	public AbstractSARPage(WebDriver driver){
		super(driver);
	}
	
	/** 
	 * 日報(一覧)画面ヘ遷移します。（週報のタブを選択します。）
	 * @return　日報(一覧)ページオブジェクト
	 */
	public NippouListPage navigateToNippouListPage(){
		click(CssSelector.A_NIPPOU);
		System.out.println("日報(一覧)画面へ遷移します。");
		
		waitForSARPageLoaded();
		return new NippouListPage(_driver);
	}
	
	/**
	 * 週報計画(一覧)画面ヘ遷移します。（週報のタブを選択します。）
	 * @return　週報計画(一覧)ページオブジェクト
	 */
	public ShuuhouKeikakuListPage navigateToShuuhouKeikakuListPage(){
		click(CssSelector.A_SHUHOU);
		System.out.println("週報計画(一覧)画面へ遷移します。");
		
		waitForSARPageLoaded();
		return new ShuuhouKeikakuListPage(_driver);
	}

	/**
	 * 月報(一覧)画面ヘ遷移します。（月報のタブを選択します。）
	 * @return　月報(一覧)ページオブジェクト 
	 */
	public GeppouListPage navigateToGeppouListPage(){
		click(CssSelector.A_GEPPOU);
		System.out.println("月報(一覧)画面へ遷移します。");
		
		waitForSARPageLoaded();
		return new GeppouListPage(_driver);
	}
	
	/**
	 * SARからログアウトします。
	 * @return　SARログインページオブジェクト 
	 */
	public SARLoginPage clickLogoutButton() {
		click(CommonCssSelector.BTN_LOGOUT);
		
		waitForSARPageLoaded();
		return new SARLoginPage(_driver);
	}
	
	/**
	 * 週報(計画),月報の未作成(作成中)最古のリンクのエレメントを取得します。
	 * @param procKind : 処理区分
	 * @return 取得成功(リンクID) 取得失敗(空文字)
	 */
	protected WebElement getUncreatedDateElement(String procKind) {
		String s1 = getProcKindString(procKind);
		String s2 = CssSelector.A_STATUS_LINK[0];
		String s3 = CssSelector.A_STATUS_LINK[1];
		WebElement tmpElem = null;
		WebElement retElem = null;
		String id = "";
		int count = 0;
		
		while (true) {
			id = "#" +s1 + s2 + count + s3;	
			tmpElem = findElement(id);
			
			// 未作成・作成中であれば設定
			if (tmpElem.getText().equals("未作成") || tmpElem.getText().equals("作成中")) {
				retElem =  tmpElem;
			// 未作成・作成中以外の場合、1つ前が最古の未作成日付のものと判断します。
			} else {
				break;
			}
			count++;
		}
		return retElem;
	}
	
	/**
	 * 作業一覧の日付一覧を取得します。
	 * @param procKind : 処理区分
	 * @return 作業一覧の日付一覧
	 */
	public List<String> getDateList(String procKind) {
		String s1 = CssSelector.A_DATE_LINK[0] + getProcKindString(procKind);
		String s2 = CssSelector.A_DATE_LINK[1];
		String s3 = CssSelector.A_DATE_LINK[2];
		
		List<String> dateList = new LinkedList<String>();
		String selector = "";
		String date = "";
		for (int i = 0; i < 7; i++) {
			selector = "#" + s1 + s2 + i + s3;
			date = findElement(selector).getText();
			_dateElementIdList.put(date, selector);
			dateList.add(date);
		}
		
		return dateList;
	}
	
	/**
	 * 一覧テーブルから作成日付に対応します。未作成(作成中)のリンクエレメントを取得します。
	 * @param date              : 作成日付
	 * @param dateColumnIndex   : 日付項目のインデックス
	 * @param statusColumnIndex : ステータス項目のインデックス
	 * @return 未作成(作成中)のリンクエレメント
	 */
	public WebElement getDateLinkElement(String date, int dateColumnIndex, int statusColumnIndex) {
		List<WebElement> tdElems = null;
		WebElement statusElem = null;
		String selector = "";
		int i = 1;
		
		while(true) {
			selector = CssSelector.ITIRAN_TABLE_TD[0] + i + CssSelector.ITIRAN_TABLE_TD[1];
			tdElems = findElements(selector);
			
			WebElement startDateElem = tdElems.get(dateColumnIndex);
			if (date.equals(startDateElem.getText())) {
				statusElem = tdElems.get(statusColumnIndex);
				break;
			}
			i++;
		}
		
		return statusElem;
	}
	
	/**
	 * 要素セレクタを生成します。
	 * @param selector : 要素セレクタ
	 * @param procKind : 処理区分
	 * @param index    : インデックス
	 * @return 処理区分
	 */
	protected String buildSelector(String[] selector, String procKind, int index) {
		String s = "";
		if(procKind.equals(ProcKind.SHUUHOU_KEIKAKU_REGISTER)) {
			s = "keikaku";
		} else if(procKind.equals(ProcKind.NIPPOU_REGISTER)) {
			s = "jisseki";
		}
		return selector[0] + s + selector[1] + index; 
	}
	
	/**
	 * 処理区分文字列を取得します。
	 * @param procKind : 処理区分
	 * @return 処理区分文字列
	 */
	protected String getProcKindString(String procKind) {
		if (procKind.equals(ProcKind.SHUUHOU_KEIKAKU_REGISTER)) {
			return "keikaku";
		} else if(procKind.equals(ProcKind.SHUUHOU_JISSEKI_REGISTER)) {
			return "jisseki";
		} else if(procKind.equals(ProcKind.NIPPOU_REGISTER)) {
			return "nippou";
		} else if(procKind.equals(ProcKind.GEPPOU_REGISTER)) {
			 return "geppou";
		}
		
		return ""; 
	}
	
	protected class CommonCssSelector {
		//------------------------------------------------------------------------------------------
		// ボタン(共通)
		//------------------------------------------------------------------------------------------
		/** 新規作成 */
		public static final String BTN_CREATE_NEW = ".btn_size_07";
		/** 保存 */
		public static final String BTN_SAVE = ".dctp_ichiran_01_top_mid_header .btn_size_05";
		/** 登録 */
		public static final String BTN_REGISTER = ".dctp_ichiran_01_top_mid_header .btn_size_05:nth-child(2)";
		/** 申請 */
		public static final String BTN_APPLY = ".dctp_ichiran_01_top_mid_header .btn_size_05";
		/** 申請(週報実績) */
		public static final String BTN_APPLY_JISSEKI = ".dctp_ichiran_01_top_mid_header .btn_size_05:nth-child(2)";
		/** 勤務計画保存  */
		public static final String BTN_KINMU_KEIKAKU_SAVE = "#nippou_keikaku_view_dctp_recordset .dctp_ichiran_01_top_mid_header .btn_size_05";
		/** 月締 */
		public static final String BTN_TSUKIJIME = ".dctp_ichiran_01_top_mid_header .btn_size_05:nth-child(2)";
		
		/** ログアウト */
		public static final String BTN_LOGOUT = "#dctp_logout";
	}
	
	private static class CssSelector {
		//------------------------------------------------------------------------------------------
		// ヘッダー部画面リンク
		//------------------------------------------------------------------------------------------
		/** 日報(TOP)画面リンク */
		public static final String A_NIPPOU = "#name1";
		/** 週報画面リンク */
		public static final String A_SHUHOU = "#name2";
		/** 月報(TOP)画面リンク */
		public static final String A_GEPPOU = "#name3";

		//------------------------------------------------------------------------------------------
		// 週報計画/実績 共通) keikakum,jisseki
		//------------------------------------------------------------------------------------------
		/** 日付リンク */
		public static final String[] A_DATE_LINK = {"nippou_", "_view_kinmu_date", "_VIEW_LABEL"};

		//------------------------------------------------------------------------------------------
		// 作業一覧(計画),月報 共通
		//------------------------------------------------------------------------------------------
		/** 未作成ステータスリンク */
		public static final String[] A_STATUS_LINK = {"_status", "_VIEW_LABEL"};
		
		public static final String[] ITIRAN_TABLE_TD = {".dctp_ichiran_table .ichiran_tr_data", " .ichiran_01_mid_data"};
	}
}