package exp.certificate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exp.certificate.core.Convertor;
import exp.libs.utils.other.LogUtils;


/**
 * <PRE>
 * 程序入口
 * </PRE>
 * <B>PROJECT：</B> exp-certificate
 * <B>SUPPORT：</B> EXP
 * @version   1.0 2017-12-17
 * @author    EXP: 272629724@qq.com
 * @since     jdk版本：jdk1.6
 */
public class Main {
	
	private final static Logger log = LoggerFactory.getLogger(Config.class);
	
	public static void main(String[] args) {
		LogUtils.loadLogBackConfig();
		
		boolean isOk = Convertor.toPage(Config.getInstn().getAppInfos());
		log.info("生成软件验证页{}", (isOk ? "成功" : "失败"));
	}
	
}
