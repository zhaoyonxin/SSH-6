package junit.test;

import org.apache.log4j.Logger;
import org.junit.Test;

public class LogTest {
	private Logger logger = Logger.getLogger(LogTest.class);
	@Test
	public void testLog() {
		logger.info("log4j测试,info");
		logger.debug("log4j测试,debug");
	}
}