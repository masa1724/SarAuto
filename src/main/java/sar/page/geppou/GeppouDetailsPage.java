package sar.page.geppou;

import org.openqa.selenium.WebDriver;

import sar.page.common.AbstractSARPage;

/** 月報(詳細)ページクラス  */
public class GeppouDetailsPage extends AbstractSARPage {
	public static final String SELECT_ACTION_EXCEL = "Download:Form";
	
	public GeppouDetailsPage(WebDriver driver){
		super(driver);
	}
	
	/** 
	 * "保存"ボタンを押下します。
	 */
	public void clickSaveButton() {
		waitLoad(2000);
		click(CommonCssSelector.BTN_SAVE);
		alertAccept();
		waitForSARPageLoaded();
	}
	
	/**
	 * "月締"ボタンを押下します。
	 * @return　月報(詳細)ページオブジェクト
	 */
	public GeppouListPage clickTsukijimeButton() {
		waitLoad(2000);
		click(CommonCssSelector.BTN_TSUKIJIME);
		alertAccept();
		
		waitForSARPageLoaded();
		return new GeppouListPage(_driver);
	}
	
	/** 
	 * 画面アクションの選択します。 
	 */
	public void selectAction(String action) {
		selectByValue(CssSelector.SELECT_ACTIONS, action);
	}
	
	/** 
	 * "実行"ボタンを押下します。
	 */
	public void clickActionButton() {
		click(CssSelector.BTN_ACTION);
		waitForSARPageLoaded();
	}

	private static class CssSelector {
		/** 実行*/
		public static final String BTN_ACTION = ".btn_action";
		
		/** アクション */
		public static final String SELECT_ACTIONS = "#actions";
		/** 月報(一覧) 年月 */
		public static final String[] GEPPOU_LIST_YYYYMM = {".dctp_ichiran_table .ichiran_tr_data", " td:nth-child(0)"};
		/** 月報(一覧) 月報S(ステータス) */
		public static final String[] GEPPOU_LIST_STATUS = {".dctp_ichiran_table .ichiran_tr_data", " td:nth-child(2)"};
	}
}