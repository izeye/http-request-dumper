package com.izeye.httprequestdumper.support.pid;

import java.lang.management.ManagementFactory;

/**
 * Created by izeye on 15. 10. 2..
 */
// Based on org.springframework.boot.ApplicationPid
public class ApplicationPid {
	
	private final long pid;
	
	public ApplicationPid() {
		this.pid = findPid();
	}

	private long findPid() {
		String jvmName = ManagementFactory.getRuntimeMXBean().getName();
		return Long.parseLong(jvmName.split("@")[0]);
	}

	public long getPid() {
		return pid;
	}
	
}
