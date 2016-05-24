package com.buildhappy.structureddata.dom;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;


/**
 * package the chanel info into a class
 * using the class to load channel info from siteInfo.xml
 * usage:
 * 	provide the channel id,return the searchUrl and PageProcessor of the channel
 * @author buildhappy
 *
 */
public class ChannelsDom {
	private String channelId;
	private String searchUrl;
	private String postParam = null;
	private String homeUrl = null;
	private String pageEncoding = null;
	private String urlEncoding = null;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public ChannelsDom(){
		logger.info("com.buildhappy.structureddata.dom.ChannelsDom no-parameter constructor");
	}
	public ChannelsDom(String channelId){
		logger.info("com.buildhappy.structureddata.dom.ChannelsDom constructor with parameter channelId");
		this.channelId = channelId;
		load();
	}
	public ChannelsDom createChannelsDom(String channelId){
		logger.info("com.buildhappy.structureddata.dom.ChannelsDom createChannelsDom(String channelId)");
		this.channelId = channelId;
		//if there is no the channel dom
		if(load() == 1){
			return null;
		}else{
			return new ChannelsDom(channelId);
		}
	}
	public int load(){
		logger.info("com.buildhappy.structureddata.dom.ChannelsDom load()");
		try {
			// 将待抓取的网站信息存在xml文件中
			DocumentBuilderFactory dbf = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();

			InputStream in = ChannelsDom.class.getClassLoader().getResourceAsStream("sitesInfo.xml");

			// 将xml文件转化成dom树
			Document doc = builder.parse(in);
			XPathFactory factory = XPathFactory.newInstance();
			XPath xPath = factory.newXPath();
			// 利用xpath获取节点信息
			XPathExpression exp = xPath.compile("//site[@id='" + channelId
					+ "']/searchUrl");
			Node node = (Node) exp.evaluate(doc, XPathConstants.NODE);
			if(node != null){
				String s = node.getTextContent();
				setSearchUrl(s.replace("!%!%!%", "&"));
				exp= xPath.compile("//site[@id='" + channelId
						+ "']/urlEncoding");
				node = (Node) exp.evaluate(doc, XPathConstants.NODE);
				if(node != null){
					urlEncoding = node.getTextContent();
				}else{
					urlEncoding = null;
				}
				
				exp = xPath.compile("//site[@id='" + channelId
						+ "']/pageEncoding");
				node = (Node) exp.evaluate(doc, XPathConstants.NODE);
				if(node != null){
					pageEncoding = node.getTextContent();
				}else{
					pageEncoding = "utf-8";
				}
				
				exp = xPath.compile("//site[@id='" + channelId
						+ "']/homePage");
				node = (Node) exp.evaluate(doc, XPathConstants.NODE);
				homeUrl = node.getTextContent();
				
				exp = xPath.compile("//site[@id='" + channelId + "']/pageProcessor");
				node = (Node) exp.evaluate(doc, XPathConstants.NODE);

				exp = xPath.compile("//site[@id='" + channelId + "']/postPara");
				node = (Node) exp.evaluate(doc, XPathConstants.NODE);
				return 0;
			}else{
				logger.warn("could not find the channel for channelId=" + channelId);
				return 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getSearchUrl() {
		return searchUrl;
	}
	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}

	public String getPostParam() {
		return postParam;
	}
	public void setPostParam(String postParam) {
		this.postParam = postParam;
	}

	public String getHomeUrl() {
		return homeUrl;
	}
	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}
	
	
	public String getPageEncoding() {
		return pageEncoding;
	}
	public void setPageEncoding(String pageEncoding) {
		this.pageEncoding = pageEncoding;
	}
	public String getUrlEncoding() {
		return urlEncoding;
	}
	public void setUrlEncoding(String urlEncoding) {
		this.urlEncoding = urlEncoding;
	}
	
	public static void main(String[] args){
		ChannelsDom dom = new ChannelsDom("1");//load the channel with id=1
		System.out.println("channel id:" + dom.getChannelId());
		System.out.println("serch url:" + dom.getSearchUrl());
	}

}
