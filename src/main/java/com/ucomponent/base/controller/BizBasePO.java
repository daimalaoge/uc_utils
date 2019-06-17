package com.ucomponent.base.controller;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * 2019/5/22
 * Author:代码老哥
 * NAME:
 * TODO:
 **/
@MappedSuperclass
@Data
public class BizBasePO extends BaseLayuiVO {
	@Column(updatable=false)
	private int orgId ;
//	@Column(updatable=false)
//	private int teamId ;
	@Column(name="biz_codeset_gstatus")
	private String bizCodesetGstatus = "G_STATUS_USE";
	@Column(name="uc_create_datetime",updatable=false)
	private Date ucCreateDatetime = new Date();
	@Column(name="uc_update_datetime")
	private Date ucUpdateDatetime = new Date();
	@Column(name="uc_create_user_id",updatable=false)
	private int ucCreateUserId;
	@Column(name="uc_update_user_id")
	private int ucUpdateUserId;
}