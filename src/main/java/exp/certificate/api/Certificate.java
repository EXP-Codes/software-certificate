package exp.certificate.api;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exp.certificate.bean.AppInfo;
import exp.libs.utils.encode.CryptoUtils;
import exp.libs.utils.other.StrUtils;
import exp.libs.warp.net.http.HttpURLUtils;

/**
 * <PRE>
 * 页面信息转换器: 获取指定应用的授权信息
 * </PRE>
 * <br/><B>PROJECT : </B> exp-certificate
 * <br/><B>SUPPORT : </B> <a href="http://www.exp-blog.com" target="_blank">www.exp-blog.com</a> 
 * @version   1.0 # 2017-12-17
 * @author    EXP: 272629724@qq.com
 * @since     jdk版本：jdk1.6
 */
public class Certificate {

	/** 日志器 */
	private final static Logger log = LoggerFactory.getLogger(Certificate.class);
	
	/** 私有化构造函数 */
	protected Certificate() {}
	
	/**
	 * 从页面提取应用授权信息
	 * @param url 授权页面地址
	 * @param appName 应用名称
	 * @return 应用信息对象
	 */
	public static AppInfo getAppInfo(final String URL, final String APP_NAME) {
		String pageSource = HttpURLUtils.doGet(URL);
		return toAppInfo(pageSource, APP_NAME);
	}
	
	/**
	 * 从页面提取应用授权信息
	 * @param pageSource 页面源码
	 * @param appName 应用名称
	 * @return 应用信息对象
	 */
	@SuppressWarnings("unchecked")
	private static AppInfo toAppInfo(String pageSource, String appName) {
		AppInfo app = null;
		try {
			Document doc = DocumentHelper.parseText(pageSource);
			Element html = doc.getRootElement();
			Element body = html.element("body");
			Element div = body.element("div");
			Iterator<Element> divs = div.elementIterator("div");
			while(divs.hasNext()) {
				Element table = divs.next().element("table");
				String name = table.attributeValue("id");
				if(appName.equals(name)) {
					app = toAppInfo(table);
					break;
				}
			}
		} catch (Exception e) {
			log.error("从页面提取应用 [{}] 信息失败:\r\n{}", appName, pageSource, e);
		}
		return app;
	}
	
	/**
	 * 根据页面的&lt;table&gt;模块还原对应的应用信息对象
	 * @param table &lt;table&gt;模块
	 * @return 应用信息对象
	 */
	@SuppressWarnings("unchecked")
	private static AppInfo toAppInfo(Element table) {
		String name = "";
		String versions = "";
		String time = "";
		String blacklist = "";
		String whitelist = "";
		
		Element tbody = table.element("tbody");
		Iterator<Element> trs = tbody.elementIterator();
		while(trs.hasNext()) {
			Element tr = trs.next();
			List<Element> ths = tr.elements();
			String key = ths.get(0).getTextTrim();
			String val = StrUtils.trimAll(ths.get(1).getText());
			
			if("SOFTWARE-NAME".equals(key)) {
				name = val;
				
			} else if("VERSIONS".equals(key)) {
				versions = CryptoUtils.deDES(val);
				
			} else if("TIME".equals(key)) {
				time = CryptoUtils.deDES(val);
				
			} else if("BLACK-LIST".equals(key)) {
				blacklist = CryptoUtils.deDES(val);
				
			} else if("WHITE-LIST".equals(key)) {
				whitelist = CryptoUtils.deDES(val);
				
			}
		}
		return new AppInfo(name, versions, time, blacklist, whitelist);
	}
	
}
