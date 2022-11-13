package Ch3.Traditional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TraditionalServer {
	public static void main(String[] args) throws Exception {
		try(ServerSocket serverSocket = new ServerSocket(8080)) {
			System.out.println("wait connection ....");
			Socket socket = serverSocket.accept();
			System.out.println("connect. remote address:" + serverSocket.getInetAddress().getHostAddress());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("receive data from client:" + bufferedReader.readLine());
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
			outputStreamWriter.write("server receive");
			outputStreamWriter.flush();
		}
	}
}
