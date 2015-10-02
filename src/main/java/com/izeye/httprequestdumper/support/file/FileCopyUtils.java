package com.izeye.httprequestdumper.support.file;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by izeye on 15. 10. 2..
 */
// Based on org.springframework.util.FileCopyUtils
public class FileCopyUtils {
	
	private static final int BUFFER_SIZE = 4096;
	
	public static String copyToString(Reader in) {
		StringWriter out = new StringWriter();
		copy(in, out);
		return out.toString();
	}

	public static void copy(Reader in, Writer out) {
		char[] buffer = new char[BUFFER_SIZE];
		int readChars;
		try {
			while ((readChars = in.read(buffer)) != -1) {
				out.write(buffer, 0, readChars);
			}
			out.flush();
		}
		catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		finally {
			try {
				in.close();
			} catch (IOException ignored) {
			}
			
			try {
				out.close();
			} catch (IOException ignored) {
			}
		}
	}

}
