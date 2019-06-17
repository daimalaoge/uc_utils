package com.ucomponent.base.controller.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 2019年3月11日
 * @Author:Daimalaoge
 */

public class BaseLayuiVO implements Serializable{
	private static final long serialVersionUID = 1L;
	@JSONField(name ="LAY_CHECKED")
	@Transient
	private boolean LAY_CHECKED = false;
	@Transient
	public String encCode = "";

	@JsonProperty("LAY_CHECKED")
	public boolean isLAY_CHECKED() {
		return LAY_CHECKED;
	}

	@JsonProperty("LAY_CHECKED")
	public void setLAY_CHECKED(boolean lAY_CHECKED) {
		LAY_CHECKED = lAY_CHECKED;
	}

	public String getEncCode() {
		return encCode;
	}

	public void setEncCode(String encCode) {
		this.encCode = encCode;
	}
}

