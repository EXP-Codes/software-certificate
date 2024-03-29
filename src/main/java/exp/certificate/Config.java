package exp.certificate;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import exp.libs.conf.xml.XConfig;
import exp.libs.conf.xml.XConfigFactory;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exp.certificate.bean.AppInfo;
import exp.libs.envm.Charset;


/**
 * <PRE>
 * 程序配置
 * </PRE>
 * <br/><B>PROJECT : </B> exp-certificate
 * <br/><B>SUPPORT : </B> <a href="http://www.exp-blog.com" target="_blank">www.exp-blog.com</a> 
 * @version   1.0 # 2017-12-17
 * @author    EXP: 272629724@qq.com
 * @since     jdk版本：jdk1.6
 */
public class Config {
	
	private final static Logger log = LoggerFactory.getLogger(Config.class);
	
	public final static String DEFAULT_CHARSET = Charset.UTF8;
	
	private final static String CONF_PATH = "./conf/ec_conf.xml";
	
	public final static String PAGE_TPL = "/exp/certificate/core/index.tpl";
	
	public final static String TABLE_TPL = "/exp/certificate/core/table.tpl";
	
	public final static String PAGE_PATH = "./docs/index.html";
	
	private static volatile Config instance;
	
	private XConfig xConf;
	
	private Config() {
		this.xConf = XConfigFactory.createConfig("ec-conf");
		xConf.loadConfFile(CONF_PATH);
	}
	
	public static Config getInstn() {
		if(instance == null) {
			synchronized (Config.class) {
				if(instance == null) {
					instance = new Config();
				}
			}
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<AppInfo> getAppInfos() {
		List<AppInfo> appList = new LinkedList<AppInfo>();
		try {
			Element root = xConf.loadConfFile(CONF_PATH);
			Element apps = root.element("appInfos");
			Iterator<Element> appIts = apps.elementIterator("appInfo");
			while(appIts.hasNext()) {
				Element app = appIts.next();
				String name = app.elementText("name");
				String versions = app.elementText("versions");
				String time = app.elementText("time");
				String blacklist = app.elementText("blacklist");
				String whitelist = app.elementText("whitelist");
				
				appList.add(new AppInfo(name, versions, time, blacklist, whitelist));
			}
			
		} catch(Exception e) {
			log.error("加载配置文件失败: {}", CONF_PATH, e);
		}
		return appList;
	}
	
	
	
}
