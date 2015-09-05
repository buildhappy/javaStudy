package com.buildhappy.json;

/**
 * the class for apk
 * @author Administrator
 *
 */
public class Apk {
	private String appName; // 抓取到的app name
	private String appDetailUrl; // 抓取到的app详细介绍页面的地址
	private String appDownloadUrl; // 抓取到的app的下载地址
	private String osPlatform ; // app适合的系统
	private String appVersion; // 抓取到的app版本信息
	private String appSize; // 抓取到的app文件大小
	private String appUpdateDate; // 抓取到的app文件的更新时间
	private String appType; // 抓取到的app格式(有些apk放在zip压缩文件中)
	private String cookie;

	/**
	 * create an apk object with the following parameter
	 * @param appName
	 * @param appDetailUrl
	 * @param appDownloadUrl
	 * @param osPlatform
	 * @param appVersion
	 * @param appSize
	 * @param appUpdateDate
	 * @param appType
	 * @param cookie
	 */
	public Apk(String appName,String appDetailUrl,String appDownloadUrl,String osPlatform ,
			String appVersion,String appSize,String appUpdateDate, String appType,String cookie){	
		create(appName,appDetailUrl, appDownloadUrl, osPlatform,appVersion,
				appSize,appUpdateDate, appType,cookie);
	}
	
	/**
	 * create an apk object with the following parameter
	 * @param appName
	 * @param appDetailUrl
	 * @param appDownloadUrl
	 * @param osPlatform
	 * @param appVersion
	 * @param appSize
	 * @param appUpdateDate
	 * @param appType
	 */
	public Apk(String appName,String appDetailUrl,String appDownloadUrl,String osPlatform ,
			String appVersion,String appSize,String appUpdateDate, String appType){
		create(appName,appDetailUrl, appDownloadUrl, osPlatform,appVersion,
				appSize,appUpdateDate, appType,null);
	}
	
	/**
	 * create an apk object with the following parameter
	 * @param appName
	 * @param appDetailUrl
	 * @param appDownloadUrl
	 * @param osPlatform
	 * @param appVersion
	 * @param appSize
	 * @param appUpdateDate
	 */
	public Apk(String appName,String appDetailUrl,String appDownloadUrl,String osPlatform ,
			String appVersion,String appSize,String appUpdateDate){
		create(appName,appDetailUrl, appDownloadUrl, osPlatform,appVersion,
				appSize,appUpdateDate, null,null);
	}
	
	/**
	 * create an apk object with the following parameter
	 * @param appName
	 * @param appDetailUrl
	 * @param appDownloadUrl
	 * @param osPlatform
	 * @param appVersion
	 * @param appSize
	 */
	public Apk(String appName,String appDetailUrl,String appDownloadUrl,String osPlatform ,
			String appVersion,String appSize){
		create(appName,appDetailUrl, appDownloadUrl, osPlatform,appVersion,
				appSize,null, null,null);
	}
	
	/**
	 * create an apk object with the following parameter
	 * @param appName
	 * @param appDetailUrl
	 * @param appDownloadUrl
	 * @param osPlatform
	 * @param appVersion
	 */
	public Apk(String appName,String appDetailUrl,String appDownloadUrl,String osPlatform ,
			String appVersion){
		create(appName,appDetailUrl, appDownloadUrl, osPlatform,appVersion,
				null,null, null,null);
	}
	
	public void create(String appName,String appDetailUrl,String appDownloadUrl,String osPlatform ,
			String appVersion,String appSize,String appUpdateDate, String appType,String cookie){
		this.appName = appName;
		this.appVersion = appVersion;
		this.appType = appType;
		this.appSize = appSize;
		this.appUpdateDate = appUpdateDate;
		this.osPlatform = osPlatform;
		this.appDetailUrl = appDetailUrl;
		this.appDownloadUrl = appDownloadUrl;
		this.cookie = cookie;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getAppSize() {
		return appSize;
	}

	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}

	public String getAppUpdateDate() {
		return appUpdateDate;
	}

	public void setAppUpdateDate(String appUpdateDate) {
		this.appUpdateDate = appUpdateDate;
	}

	public String getOsPlatform() {
		return osPlatform;
	}

	public void setOsPlatform(String osPlatform) {
		this.osPlatform = osPlatform;
	}

	public String getAppDetailUrl() {
		return appDetailUrl;
	}

	public void setAppDetailUrl(String appDetailUrl) {
		this.appDetailUrl = appDetailUrl;
	}

	public String getAppDownloadUrl() {
		return appDownloadUrl;
	}

	public void setAppDownloadUrl(String appDownloadUrl) {
		this.appDownloadUrl = appDownloadUrl;
	}
	
	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

}
