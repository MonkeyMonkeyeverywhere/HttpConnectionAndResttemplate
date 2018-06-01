package com.qf.datatool.constants;

/**
 * 发布房源状态
 * @author yakun.zhao
 *
 */
public enum PublishStatus {

	STATUS_UNRELEASED("unreleased", "未发布"),
	STATUS_RELEASE("release", "发布中"),
	STATUS_ON("on", "上架"),
	STATUS_OFF_ING("offing", "下架中"),
	STATUS_OFF("off", "下架");
	
	private String code;
	private String name;
	
	PublishStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
}
