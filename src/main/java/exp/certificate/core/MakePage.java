package exp.certificate.core;

import java.util.LinkedList;
import java.util.List;

import exp.certificate.Config;
import exp.certificate.bean.AppInfo;
import exp.libs.utils.encode.CryptoUtils;
import exp.libs.utils.io.FileUtils;
import exp.libs.utils.other.StrUtils;
import exp.libs.utils.time.TimeUtils;
import exp.libs.warp.tpl.Template;

/**
 * <PRE>
 * 生成授权校验页面
 * </PRE>
 * <B>PROJECT : </B> exp-certificate
 * <B>SUPPORT : </B> <a href="http://www.exp-blog.com" target="_blank">www.exp-blog.com</a>
 * @version   1.0 # 2017-12-17
 * @author    EXP: 272629724@qq.com
 * @since     jdk版本：jdk1.6
 */
public class MakePage {

	/** 断行长度:64 */
	private final static int BREAK_LINE_LEN = 64;
	
	/** 私有化构造函�? */
	protected MakePage() {}
	
	/**
	 * 根据应用信息列表生成授权页面
	 * @param appInfos 应用列表
	 * @return 是否生成成功
	 */
	public static boolean toPage(List<AppInfo> appInfos) {
		List<String> tables = toTables(appInfos);
		Template tpl = new Template(Config.PAGE_TPL, Config.DEFAULT_CHARSET);
		tpl.set("tables", StrUtils.concat(tables, ""));
		tpl.set("time", TimeUtils.getSysDate());
		return FileUtils.write(Config.PAGE_PATH, 
				tpl.getContent(), Config.DEFAULT_CHARSET, false);
	}
	
	/**
	 * 根据应用列表生成对应�?&lt;div&gt;模块
	 * @param appInfos 应用信息列表
	 * @return &lt;div&gt;模块
	 */
	private static List<String> toTables(List<AppInfo> appInfos) {
		List<String> tables = new LinkedList<String>();
		Template tpl = new Template(Config.TABLE_TPL, Config.DEFAULT_CHARSET);
		for(AppInfo appInfo : appInfos) {
			tpl.set("name", appInfo.getName());
			tpl.set("versions", breakLine(CryptoUtils.toDES(appInfo.getVersions())));
			tpl.set("time", breakLine(CryptoUtils.toDES(appInfo.getTime())));
			tpl.set("blacklist", breakLine(CryptoUtils.toDES(appInfo.getBlacklist())));
			tpl.set("whitelist", breakLine(CryptoUtils.toDES(appInfo.getWhitelist())));
			tables.add(tpl.getContent());
		}
		return tables;
	}
	
	/**
	 * 对字符串断行(保证页面表单不会因为内容过长而变�?)
	 * @param str
	 * @return
	 */
	private static String breakLine(String str) {
		return StrUtils.breakLine(str, BREAK_LINE_LEN);	// �?64个字符断行一�?
	}
	
}
