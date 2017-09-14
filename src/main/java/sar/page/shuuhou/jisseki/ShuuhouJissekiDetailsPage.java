package sar.page.shuuhou.jisseki;

import org.openqa.selenium.WebDriver;

import sar.page.shuuhou.AbstractShuuhouPage;

/** 週報実績(詳細)ページクラス  */
public class ShuuhouJissekiDetailsPage extends AbstractShuuhouPage {
	public ShuuhouJissekiDetailsPage(WebDriver driver){
		super(driver);
	}
	
	/**
	 * "作業開始日"を入力します。
	 *　@param value : 入力情報 
	 */
	public void typeKetsugouKinmuDate(String value) {
		typeValue(CssSelector.TXT_KETSUGOU_KINMU_DATE, value);
	}
	
	/**
	 * "評価"を選択します。
	 *　@param value : optionのvalue値
	 */
	public void selectJissekiHyouka(String value) {
		selectByValue(CssSelector.SELECT_JISSEKI_HYOUKA, value);
	}
	
	/**
	 * "評価理由"を入力します。
	 *　@param value : 入力情報 
	 */
	public void typeHyoukaRiyuu(String value) {
		typeValue(CssSelector.TXT_HYUOUKA_RIYUU, value);
	}
	
	/**
	 * "保存"ボタン押下します。
	 */
	public void clickSaveButton() {
		waitLoad(2000);
		click(CommonCssSelector.BTN_SAVE);
		alertAccept();
		
		waitForSARPageLoaded();
	}
	
	/**H
	 * "申請"ボタン押下します。
	 */
	public ShuuhouJissekiListPage clickApplyJisseki() {
		waitLoad(2000);
		click(CommonCssSelector.BTN_APPLY_JISSEKI);
		alertAccept();
		
		waitForSARPageLoaded();
		return new ShuuhouJissekiListPage(_driver);
	}
	
	private class CssSelector {
		/** 作業開始日 */
		public static final String TXT_KETSUGOU_KINMU_DATE = "#ketsugou_kinmu_date";
		/** 評価 */
		public static final String SELECT_JISSEKI_HYOUKA = "#jisseki_hyouka";
		/** 評価理由 */
		public static final String TXT_HYUOUKA_RIYUU = "#hyouka_riyuu";	
	}
}