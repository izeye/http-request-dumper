package com.izeye.httprequestdumper.support.pid;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by izeye on 15. 10. 2..
 */
// Based on org.springframework.boot.actuate.system.ApplicationPidFileWriter
public class ApplicationPidFileWriter {

	private static final String DEFAULT_FILENAME = "application.pid";
	
	public void write() {
		writeTo(DEFAULT_FILENAME);
	}
	
	public void writeTo(String filename) {
		long pid = new ApplicationPid().getPid();
		System.out.println("PID: " + pid);
		
		File pidFile = new File(filename);
		pidFile.deleteOnExit();

		FileWriter fw = null;
		try {
			fw = new FileWriter(pidFile);
			fw.append(String.valueOf(pid));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException ignored) {
				}
			}
		}
	}
	
}
