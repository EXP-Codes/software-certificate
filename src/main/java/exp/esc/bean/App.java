package exp.esc.bean;

/**
 * <PRE>
 * 应用对象
 * </PRE>
 * <B>PROJECT：</B> exp-certificate
 * <B>SUPPORT：</B> EXP
 * @version   1.0 2017-12-17
 * @author    EXP: 272629724@qq.com
 * @since     jdk版本：jdk1.6
 */
public class App {

	/** 应用名称 */
	private String name;
	
	/** 授权版本 */
	private String versions;
	
	/** 授权时间 */
	private String time;
	
	/** 黑名单 */
	private String blacklist;
	
	public App(String name, String versions, String time, String blacklist) {
		this.name = (name == null ? "" : name.trim());
		this.versions = (versions == null ? "" : versions.trim());
		this.time = (time == null ? "" : time.trim());
		this.blacklist = (blacklist == null ? "" : blacklist.trim());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersions() {
		return versions;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
	}
	
}
