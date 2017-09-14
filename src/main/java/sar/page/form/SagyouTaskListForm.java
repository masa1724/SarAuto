package sar.page.form;

import org.openqa.selenium.WebDriver;

import library.WebDriverUtil;
import main.ProcKind;
import sar.bean.SagyouTask;

/** 作業一覧(計画/実績)フォーム */
public class SagyouTaskListForm extends WebDriverUtil {
	private String _procKindStr;
	private int _index;
	
	public SagyouTaskListForm(WebDriver driver, String procKind){
		super(driver);
		_index = 0;
		
		if (procKind.equals(ProcKind.NIPPOU_REGISTER)) {
			_procKindStr = "jisseki";
		} else if (procKind.equals(ProcKind.SHUUHOU_KEIKAKU_REGISTER)) {
			_procKindStr = "keikaku";
		}		
	}
	
	/**
	 * 作業一覧へレコードを追加します。
	 * @param sagyouJissekiRecord : 作業実績レコード
	 */
	public void addRecord(SagyouTask sagyouJissekiRecord) {
		// "追加" ボタン押下
		click(CssSelector.BTN_ADD);
		waitLoad(1000);
			
		/* 下記項目を入力 */
		// 部門名
		selectByValue(buildSelector(CssSelector.SELECT_BUMON_NAME), sagyouJissekiRecord.getBumonName());
		// PJ名
		selectByValue(buildSelector(CssSelector.SELECT_PROJECT_NAME), sagyouJissekiRecord.getProjectName());
		// 工程
		selectByValue(buildSelector(CssSelector.SELECT_PJ_KOUTEI), sagyouJissekiRecord.getProjectKoutei());
		// 作業時間(時)
		selectByValue(buildSelector(CssSelector.SELECT_SAGYOU_TIME_HOUR), sagyouJissekiRecord.getSagyouTimeHour());
		// 作業時間(分)
		selectByValue(buildSelector(CssSelector.SELECT_SAGYOU_TIME_MIN), sagyouJissekiRecord.getSagyouTimeMin());
		// タスク
		selectByValue(buildSelector(CssSelector.SELECT_TASK_ID), sagyouJissekiRecord.getTaskId());
		// 備考
		typeValue(buildSelector(CssSelector.TXT_BIKOU), sagyouJissekiRecord.getBikou());
		
		_index++;
	}
	
	/**
	 * 要素セレクタを生成する
	 * @param selector : 要素セレクタ
	 * @return 生成後の要素セレクタ
	 */
	private String buildSelector(String[] selector) {
		return selector[0] + _procKindStr + selector[1] + _index; 
	}
	
	private static class CssSelector {
		//------------------------------------------------------------------------------------------
		// 作業一覧計画/実績 (日報,週報計画 共通)
		//------------------------------------------------------------------------------------------
		/** 追加 */
		public static final String BTN_ADD = "#addLineBtn";
		/** 部門名 */
		public static final String[] SELECT_BUMON_NAME = {"#sagyou_" , "_view_bumon_name_view"};
		/** PJ名 */
		public static final String[] SELECT_PROJECT_NAME = {"#sagyou_" , "_view_project_name_view"};
		/** 工程 */
		public static final String[] SELECT_PJ_KOUTEI = {"#sagyou_" , "_view_pj_koutei_view"};
		/** 作業時間(時) */
		public static final String[] SELECT_SAGYOU_TIME_HOUR = {"#sagyou_" , "_view_sagyou_time_hour_view"};
		/** 作業時間(分) */
		public static final String[] SELECT_SAGYOU_TIME_MIN = {"#sagyou_" , "_view_sagyou_time_min_view"};
		/** タスク */
		public static final String[] SELECT_TASK_ID = {"#sagyou_" , "_view_task_id_view"};
		/** 備考 */
		public static final String[] TXT_BIKOU = {"#sagyou_" , "_view_text_bikou"};
	}
}