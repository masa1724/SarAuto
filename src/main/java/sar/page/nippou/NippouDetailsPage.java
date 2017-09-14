package sar.page.nippou;

import org.openqa.selenium.WebDriver;

import main.ProcKind;
import sar.page.common.AbstractSARPage;
import sar.page.form.SagyouTaskListForm;

/** 日報(詳細)ページクラス  */
public class NippouDetailsPage extends AbstractSARPage {	
	public NippouDetailsPage(WebDriver driver){
		super(driver);
	}
	
	/** 
	 * "日付"を入力します。  
	 *　@param value : 入力情報 
	 */
	public void typeKinmuDate(String value) {
		typeValue(CssSelector.TXT_KINMU_DATE, value);
	}
	
	/**
	 * "勤務種別"を選択します。
	 *　@param value : optionのvalue値
	 */
	public void selectKinmuShubetsu(String value) {
		selectByValue(CssSelector.SELECT_KINMU_SHUBETSU, value.trim());
	}
	
	/**
	 * "出勤時間(時)"を選択します。
	 *　@param value : optionのvalue値
	 */
	public void selectShukkinTimeHour(String value) {
		selectByValue(CssSelector.SELECT_SHUKKIN_TIME_HOUR, value);
	}
	
	/**
	 * "出勤時間(分)"を選択します。
	 *　@param value : optionのvalue値
	 */
	public void selectShukkinTimeMin(String value) {
		selectByValue(CssSelector.SELECT_SHUKKIN_TIME_MIN, value);
	}
	
	/**
	 * "退勤時間(時)"を選択します。
	 *　@param value : optionのvalue値
	 */
	public void selectTaikinTimeHour(String value) {
		selectByValue(CssSelector.SELECT_TAIKIN_TIME_HOUR, value);
	}
	
	/**
	 * "退勤時間(分)"を選択します。
	 *　@param value : optionのvalue値
	 */
	public void selectTaikinTimeMin(String value) {
		selectByValue(CssSelector.SELECT_TAIKIN_TIME_MIN, value);
	}

	/** 
	 * "保存"ボタン押下する 
	 */
	public void clickSaveButton() {
		click(CommonCssSelector.BTN_SAVE);
	    alertAccept();
	    
	    waitForSARPageLoaded();
	}

	/** 
	 *　"登録"ボタン押下する 
	 */
	public NippouListPage clickRegisterButton() {
		click(CommonCssSelector.BTN_REGISTER);
		alertAccept();
		
		waitForSARPageLoaded();
		return new NippouListPage(_driver);
	}
	
	/**
	 * 作業一覧(実績)フォームを取得する
	 * @return 作業一覧(実績)フォーム
	 */
	public SagyouTaskListForm getSagyouListJissekiForm() {
		return new SagyouTaskListForm(_driver, ProcKind.NIPPOU_REGISTER);
	}
	
	private class CssSelector {
		/** 日付 */
		public static final String TXT_KINMU_DATE = "#kinmu_date";
		/** 出勤時間(時) */
		public static final String SELECT_SHUKKIN_TIME_HOUR = "#shukkin_time_hour";
		/** 出勤時間(分) */
		public static final String SELECT_SHUKKIN_TIME_MIN = "#shukkin_time_min";
		/** 退勤時間(時) */
		public static final String SELECT_TAIKIN_TIME_HOUR = "#taikin_time_hour";
		/** 退勤時間(分) */
		public static final String SELECT_TAIKIN_TIME_MIN = "#taikin_time_min";
		/** 勤務種別  */
		public static final String SELECT_KINMU_SHUBETSU = "#kinmu_shubetsu_view";
	}
}