package exp.certificate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exp.certificate.core.MakePage;
import exp.libs.utils.other.LogUtils;


/**
 * <PRE>
 * 生成软件授权信息页面.
 * ---------------------
 * 	授权信息需在 ./conf/ec_conf.xml 中配置
 * </PRE>
 * <B>PROJECT : </B> exp-certificate
 * <B>SUPPORT : </B> <a href="http://www.exp-blog.com" target="_blank">www.exp-blog.com</a> 
 * @version   1.0 # 2017-12-17
 * @author    EXP: 272629724@qq.com
 * @since     jdk版本：jdk1.6
 */
public class Main {
	
	/** 日志器 */
	private final static Logger log = LoggerFactory.getLogger(Config.class);
	
	/**
	 * 程序入口
	 * @param args
	 */
	public static void main(String[] args) {
		LogUtils.loadLogBackConfig();
		
		boolean isOk = MakePage.toPage(Config.getInstn().getAppInfos());
		log.info("生成软件验证页{}", (isOk ? "成功" : "失败"));
	}
	
}
