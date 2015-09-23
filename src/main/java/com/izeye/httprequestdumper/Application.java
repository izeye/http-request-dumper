package com.izeye.httprequestdumper;

/**
 * Created by izeye on 15. 9. 23..
 */
public class Application {

	public static void main(String[] args) {
		HttpRequestDumper httpRequestDumper;
		if (args.length == 0) {
			httpRequestDumper = new HttpRequestDumper();
		}
		else {
			httpRequestDumper = new HttpRequestDumper(Integer.parseInt(args[0]));
		}
		httpRequestDumper.run();
	}
	
}
