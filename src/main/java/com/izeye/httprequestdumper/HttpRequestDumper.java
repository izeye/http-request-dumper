package com.izeye.httprequestdumper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by izeye on 15. 9. 23..
 */
public class HttpRequestDumper {
	
	private static final int DEFAULT_PORT = 8080;
	
	private static final int BUFFER_SIZE = 1024;
	
	private ServerSocket serverSocket;
	
	public HttpRequestDumper() {
		this(DEFAULT_PORT);
	}
	
	public HttpRequestDumper(int port) {
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void run() {
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				
				new Thread(new Worker(socket)).start();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	static class Worker implements Runnable {

		private static final byte CR = '\r';
		private static final byte LF = '\n';

		private static final String DELIMITER = "\r\n";
		
		private Socket socket;
		
		private Worker(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try (
					Socket socket = this.socket;
					InputStream is = socket.getInputStream();
					OutputStream os = socket.getOutputStream()
			) {
				SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
				System.out.println("Connected from :" + remoteSocketAddress);
				
				byte[] bytes = receiveRequest(is);
				logRequest(bytes);

				sendResponse(os, bytes);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private byte[] receiveRequest(InputStream is) throws IOException {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int readBytes;
			while ((readBytes = is.read(buffer)) > 0) {
				baos.write(buffer, 0, readBytes);
				byte[] bytes = baos.toByteArray();
				if (isFinished(bytes)) {
					break;
				}
			}
			return baos.toByteArray();
		}

		private boolean isFinished(byte[] bytes) {
			return bytes[bytes.length - 4] == CR
					&& bytes[bytes.length - 3] == LF
					&& bytes[bytes.length - 2] == CR
					&& bytes[bytes.length - 1] == LF;
		}

		private void logRequest(byte[] bytes) {
			System.out.printf("Request:%n-----%n%s%n-----%n%n", new String(bytes));
		}

		private void sendResponse(OutputStream os, byte[] bytes) throws IOException {
			String responseHeaders = createResponseHeaders(bytes);
			os.write(responseHeaders.getBytes());
			os.write(bytes);
			os.flush();
		}

		private String createResponseHeaders(byte[] bytes) {
			StringBuilder sb = new StringBuilder();
			sb.append("HTTP/1.1 200 OK");
			sb.append(DELIMITER);
			sb.append("Connection: close");
			sb.append(DELIMITER);
			sb.append("Content-Type: text/plain");
			sb.append(DELIMITER);
			sb.append("Content-Type: ");
			sb.append(bytes.length);
			sb.append(DELIMITER);
			sb.append(DELIMITER);
			return sb.toString();
		}
		
	}
	
}
