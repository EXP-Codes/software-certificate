package exp.certificate.bean;

/**
 * <PRE>
 * 应用授权信息对象
 * </PRE>
 * <br/><B>PROJECT : </B> exp-certificate
 * <br/><B>SUPPORT : </B> <a href="http://www.exp-blog.com" target="_blank">www.exp-blog.com</a> 
 * @version   1.0 # 2017-12-17
 * @author    EXP: 272629724@qq.com
 * @since     jdk版本：jdk1.6
 */
public class AppInfo {

	/** 应用名称 */
	private String name;
	
	/** 授权版本 */
	private String versions;
	
	/** 授权时间 */
	private String time;
	
	/** 黑名单 */
	private String blacklist;
	
	/** 白名单 */
	private String whitelist;
	
	public AppInfo(String name, String versions, String time, 
			String blacklist, String whitelist) {
		this.name = (name == null ? "" : name.trim());
		this.versions = (versions == null ? "" : versions.trim());
		this.time = (time == null ? "" : time.trim());
		this.blacklist = (blacklist == null ? "" : blacklist.trim());
		this.whitelist = (whitelist == null ? "" : whitelist.trim());
	}

	public String getName() {
		return name;
	}

	public String getVersions() {
		return versions;
	}

	public String getTime() {
		return time;
	}

	public String getBlacklist() {
		return blacklist;
	}
	
	public String getWhitelist() {
		return whitelist;
	}
	
}
