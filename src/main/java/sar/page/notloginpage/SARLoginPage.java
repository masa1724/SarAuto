package sar.page.notloginpage;

import org.openqa.selenium.WebDriver;

import sar.page.common.AbstractSARPage;
import sar.page.nippou.NippouListPage;

/** SARログインページクラス  */
public class SARLoginPage extends AbstractSARPage {
	/** SARログインURL */
	private static final String SAR_LOGIN_URL = "https://trustpro-apps.tdc.co.jp/trustpro/index.action";
	
	public SARLoginPage(WebDriver driver){
		super(driver);
		waitForPageLoaded();
		navigate(SAR_LOGIN_URL);
	}
	
	/**
	 * "ログイン"ボタン押下します。 
	 * @return　日報(一覧)ページオブジェクト
	 */
	public NippouListPage login(String userName, String password) {
		typeValue(CssSelector.TXT_LOGIN, userName);
		typeValue(CssSelector.PASS_PASSWORD, password);
		
		String title = getTitle();
		submit(CssSelector.BTN_LOGIN);
		waitForSARPageLoaded();
		
		if (!title.equals(getTitle())) {
			return new NippouListPage(_driver);
		}
		
		return null;
	}
	
	private class CssSelector {
		/** ユーザ名 */
		public static final String TXT_LOGIN = "#userid";
		/** パスワード */
		public static final String PASS_PASSWORD = "input[type='password']";
		/** ログイン */
		public static final String BTN_LOGIN = ".tp-login-form button[type=submit]";
	}
}