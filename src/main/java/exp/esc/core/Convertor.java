package exp.esc.core;

import java.util.LinkedList;
import java.util.List;

import exp.esc.Config;
import exp.esc.bean.App;
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
		return FileUtils.write(Config.PAGE_PATH, tpl.getContent(), Config.DEFAULT_CHARSET, false);
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
	
}
