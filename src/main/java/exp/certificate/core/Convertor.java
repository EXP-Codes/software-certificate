package exp.certificate.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exp.certificate.Config;
import exp.certificate.bean.App;
import exp.libs.utils.encode.CryptoUtils;
import exp.libs.utils.io.FileUtils;
import exp.libs.utils.other.StrUtils;
import exp.libs.utils.time.TimeUtils;
import exp.libs.warp.tpl.Template;

/**
 * <PRE>
 * 页面转换器
 * </PRE>
 * <B>PROJECT：</B> exp-certificate
 * <B>SUPPORT：</B> EXP
 * @version   1.0 2017-12-17
 * @author    EXP: 272629724@qq.com
 * @since     jdk版本：jdk1.6
 */
public class Convertor {

	private final static Logger log = LoggerFactory.getLogger(Convertor.class);
	
	/** 私有化构造函数 */
	protected Convertor() {}
	
	/**
	 * 根据应用列表生成授权页面
	 * @param apps 应用列表
	 * @return 授权页面
	 */
	public static boolean toPage(List<App> apps) {
		List<String> tables = toTables(apps);
		Template tpl = new Template(Config.PAGE_TPL, Config.DEFAULT_CHARSET);
		tpl.set("tables", StrUtils.concat(tables, ""));
		tpl.set("time", TimeUtils.getSysDate());
		return FileUtils.write(Config.PAGE_PATH, 
				tpl.getContent(), Config.DEFAULT_CHARSET, false);
	}
	
	/**
	 * 根据应用列表生成对应的&lt;div&gt;模块
	 * @param apps 应用列表
	 * @return &lt;div&gt;模块
	 */
	private static List<String> toTables(List<App> apps) {
		List<String> tables = new LinkedList<String>();
		Template tpl = new Template(Config.TABLE_TPL, Config.DEFAULT_CHARSET);
		for(App app : apps) {
			tpl.set("name", app.getName());
			tpl.set("versions", CryptoUtils.toDES(app.getVersions()));
			tpl.set("time", CryptoUtils.toDES(app.getTime()));
			tpl.set("blacklist", CryptoUtils.toDES(app.getBlacklist()));
			tables.add(tpl.getContent());
		}
		return tables;
	}
	
	/**
	 * 从页面提取应用授权信息
	 * @param pageSource 页面源码
	 * @param appName 应用名称
	 * @return app对象
	 */
	@SuppressWarnings("unchecked")
	public static App toApp(final String pageSource, final String appName) {
		App app = null;
		try {
			Document doc = DocumentHelper.parseText(pageSource);
			Element html = doc.getRootElement();
			Element body = html.element("body");
			Element div = body.element("div").element("div");
			Iterator<Element> tables = div.elementIterator();
			while(tables.hasNext()) {
				Element table = tables.next();
				String name = table.attributeValue("id");
				if(appName.equals(name)) {
					app = Convertor.toApp(table);
					break;
				}
			}
		} catch (Exception e) {
			log.error("从页面提取应用 [{}] 信息失败:\r\n{}", appName, pageSource, e);
		}
		return app;
	}
	
	/**
	 * 根据页面的&lt;table&gt;模块还原对应的App对象
	 * @param table &lt;table&gt;模块
	 * @return App对象
	 */
	@SuppressWarnings("unchecked")
	private static App toApp(Element table) {
		String name = "";
		String versions = "";
		String time = "";
		String blacklist = "";
		
		Element tbody = table.element("tbody");
		Iterator<Element> trs = tbody.elementIterator();
		while(trs.hasNext()) {
			Element tr = trs.next();
			List<Element> ths = tr.elements();
			String key = ths.get(0).getTextTrim();
			String val = ths.get(1).getTextTrim();
			
			if("SOFTWARE-NAME".equals(key)) {
				name = val;
				
			} else if("VERSIONS".equals(key)) {
				versions = CryptoUtils.deDES(val);
				
			} else if("TIME".equals(key)) {
				time = CryptoUtils.deDES(val);
				
			} else if("BLACK-LIST".equals(key)) {
				blacklist = CryptoUtils.deDES(val);
			}
		}
		return new App(name, versions, time, blacklist);
	}
	
}
