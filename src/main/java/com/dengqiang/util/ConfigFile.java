package com.dengqiang.util;

/**
 * 项目配置文件
 * 
 * @author dengqiang
 * 
 */
public abstract class ConfigFile {
	/**
	 * 是否打印错误信息
	 */
	 public static boolean  PRINT_ERROR =false;
	// 验证码有效时间
	public static int code_Obsolete_date = 1000;
	public static final String registerVerificationCode = "registerVerificationCode";
	///////
	public static final String DATETIME_FORMAT="yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FORMAT =  "yyyy-MM-dd";
	public static final int LOGDAY = 10;

	/**
	 * 当前域名前缀 http://
	 */
	public static String urlPrefix="http://www.hxxq.cn";
	/**
	 * 是否显示其他运营商的消息
	 */
	public static boolean isShowAllProduct=false;
	/**
	 * 本机外网IP地址
	 */
	public static String LocalIP="140.143.85.120";
}
