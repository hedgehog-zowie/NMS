/*
 * @(#)SSOConstant.java 2013-8-26
 *
 * Copyright 2013 Shenzhen Gionee,Inc. All rights reserved.
 */
package com.iuni.nms.sso.constants;

import org.springframework.stereotype.Service;

/**
 * @ClsssName SsoConstant
 * @author ZuoChangjun 2013-8-26
 * @version dp-admin-1.0.0
 */
@Service
public class SsoConstant {
	/**
	 * 应用名
	 */
	public static final String APP_NAME="ias";
	
	/**
	 * 默认编码
	 */
	public static final String DEFAULT_ENCODING = "UTF-8";
	
	/**
	 * 与UUC对接的常量
	 */
//	public static final String UUC_URL = PropertiesUtil.get("nms.uuc.url");//用户中心地址
	public static final String UUC_AUTHC_TICKET_URI = "/sso?";//取用户ticket
	public static final String UUC_AUTHC_LOGOUT_URI = "/logout?";//登出
	public static final String UUC_AUTHC_CALLBACK_URI = "/authcCallback";//认证回调接口，如   http://18.8.0.28:8080/ias/authcCallback?ticket=ST-271-5m6bog4ayUMUABXIHJU7-sso
	public static final String UUC_AUTHC_VALIDATE_URI = "/cas/validate";//验证ticket
	public static final String UUC_AUTHZ_CHECK_URI = "/cas/validlogin";//检测是否登录
	public static final String UUC_FETCH_MENU_URI = "/cas/menu";//取用户菜单
	public static final String UUC_FETCH_PERMISSION_URI = "/cas/permission";//取用户权限
//	public static final String UUC_AUTHC_VALIDATE_URI = "/api/queryStock.action";//验证ticket
//	public static final String UUC_AUTHZ_CHECK_URI = "/api/queryStock.action";//检测是否登录
//	public static final String UUC_FETCH_MENU_URI = "/api/queryStock.action";//取用户菜单
//	public static final String UUC_FETCH_PERMISSION_URI = "/api/queryStock.action";//取用户权限
//	public static final String UUC_APP_NMS_ID = PropertiesUtil.get("nms.uuc.app.id");//NMS在用户中心配置的应用ID
//	public static final String UUC_APP_NMS_KEY = PropertiesUtil.get("nms.uuc.app.key");//NMS在用户中心配置的应用ID

	private String UUC_URL;
	private String UUC_APP_NMS_ID;
	private String UUC_APP_NMS_KEY;

	public String getUUC_APP_NMS_KEY() {
		return UUC_APP_NMS_KEY;
	}

	public void setUUC_APP_NMS_KEY(String UUC_APP_NMS_KEY) {
		this.UUC_APP_NMS_KEY = UUC_APP_NMS_KEY;
	}

	public String getUUC_APP_NMS_ID() {
		return UUC_APP_NMS_ID;
	}

	public void setUUC_APP_NMS_ID(String UUC_APP_NMS_ID) {
		this.UUC_APP_NMS_ID = UUC_APP_NMS_ID;
	}

	public String getUUC_URL() {
		return UUC_URL;
	}

	public void setUUC_URL(String UUC_URL) {
		this.UUC_URL = UUC_URL;
	}
}
