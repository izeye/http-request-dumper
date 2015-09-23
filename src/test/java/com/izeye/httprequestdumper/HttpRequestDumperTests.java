package com.izeye.httprequestdumper;

import org.junit.Test;

/**
 * Created by izeye on 15. 9. 23..
 */
public class HttpRequestDumperTests {
	
	@Test
	public void test() {
		new HttpRequestDumper(18080).run();
	}
	
}
