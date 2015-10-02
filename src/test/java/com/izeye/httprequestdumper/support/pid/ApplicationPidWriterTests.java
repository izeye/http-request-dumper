package com.izeye.httprequestdumper.support.pid;

import com.izeye.httprequestdumper.support.file.FileCopyUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by izeye on 15. 10. 2..
 */
public class ApplicationPidWriterTests {
	
	@Test
	public void testWrite() throws FileNotFoundException {
		ApplicationPidFileWriter writer = new ApplicationPidFileWriter();
		writer.write();

		File file = new File("application.pid");
		assertTrue(file.exists());
		assertFalse(FileCopyUtils.copyToString(new FileReader(file)).isEmpty());
	}
	
}
