package sar.page.shuuhou;

import org.openqa.selenium.WebDriver;

import sar.page.common.AbstractSARPage;
import sar.page.shuuhou.jisseki.ShuuhouJissekiListPage;
import sar.page.shuuhou.keikaku.ShuuhouKeikakuListPage;

public class AbstractShuuhouPage extends AbstractSARPage{
	public AbstractShuuhouPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * 週報計画(一覧)画面ヘ遷移します。（週報計画のタブを選択します。）
	 * @return　週報実績(一覧)ページオブジェクト
	 */
	public ShuuhouKeikakuListPage navigateToShuuhouKeikakuListPage(){
		click(CssSelector.A_SHUHOU_KEIKAKU);
		System.out.println("週報(計画)画面へ遷移します。");
		
		waitForSARPageLoaded();
		return new ShuuhouKeikakuListPage(_driver);
	}
	
	/**
	 * 週報実績(一覧)画面ヘ遷移します。（週報実績のタブを選択します。）
	 * @return　週報実績(一覧)ページオブジェクト
	 */
	public ShuuhouJissekiListPage navigateToShuuhouJissekiListPage(){
		click(CssSelector.A_SHUHOU_JISSEKI);
		System.out.println("週報(実績)画面へ遷移します。");
		
		waitForSARPageLoaded();
		return new ShuuhouJissekiListPage(_driver);
	}
	
	private class CssSelector {
		/** 週報(計画)画面リンク */
		public static final String A_SHUHOU_KEIKAKU = "#name2_0";
		/** 週報(実績)画面リンク */
		public static final String A_SHUHOU_JISSEKI = "#name2_1";
	}
}
