package junit.test;

import org.junit.Test;

import com.example.utils.IPUtil;

public class IPUtilsTest {
	@Test
	public void testIP() {
		IPUtil ipUtils = new IPUtil("218.241.197.98");
		System.out.println(ipUtils.getContent());
	}
}
