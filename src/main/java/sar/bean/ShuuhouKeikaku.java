package sar.bean;

import java.util.List;

import lombok.Data;

/** 週報計画レコード */
@Data
public class ShuuhouKeikaku {
	private String ketsugouKinmuDate;
	private String kinnmuYotei; // #nippou_keikaku_view_kinmu_jyoukyou1 指定なし:01 作業せず:12 
	
	private List<SagyouTask> sagyouKeikakuList;
}