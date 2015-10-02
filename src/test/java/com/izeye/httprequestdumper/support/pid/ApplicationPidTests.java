package com.izeye.httprequestdumper.support.pid;

import org.junit.Test;

/**
 * Created by izeye on 15. 10. 2..
 */
public class ApplicationPidTests {
	
	@Test
	public void test() {
		System.out.println(new ApplicationPid().getPid());
	}
	
}
