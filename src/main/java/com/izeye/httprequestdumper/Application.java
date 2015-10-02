package com.izeye.httprequestdumper;

import com.izeye.httprequestdumper.support.pid.ApplicationPidFileWriter;

/**
 * Created by izeye on 15. 9. 23..
 */
public class Application {
	
	public static void main(String[] args) {
		writePidFile();

		HttpRequestDumper httpRequestDumper;
		if (args.length == 0) {
			httpRequestDumper = new HttpRequestDumper();
		}
		else {
			httpRequestDumper = new HttpRequestDumper(Integer.parseInt(args[0]));
		}
		httpRequestDumper.run();
	}

	private static void writePidFile() {
		new ApplicationPidFileWriter().write();
	}

}
