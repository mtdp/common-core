package me.wanx.common.detection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 应用检测
 * 场景 检测线上的应用是否挂了
 * @author gqwang
 *
 */
public class DetectionServer {
	private static final Logger logger = LoggerFactory.getLogger(DetectionServer.class);
	private int port;
	
	public DetectionServer(int port){
		this.port = port;
		this.server();
	}
	
	public void server (){
		BufferedReader reader = null;
		PrintWriter writer = null;
		Socket socket = null;
		try {
			//监听port端口
			ServerSocket server = new ServerSocket(port);
			logger.info("***** server socket started *****");
			logger.info("listenint port:" + this.port);
			//阻塞
			socket = server.accept();
			while(true){
				//获取数据 socket.getInputStream() 阻塞
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String info = reader.readLine();
				logger.info("server接受到client的消息是:"+ info);
				
				if(DetectionConstant.SERVER_SEND_STR.equals(info.toLowerCase())){
					writer = new PrintWriter(socket.getOutputStream());
					writer.println(DetectionConstant.CLIENT_SEND_STR);
					writer.flush();
					
					logger.info("server回复client消息是:"+DetectionConstant.CLIENT_SEND_STR);
				}
			}
		} catch (UnknownHostException e) {
			logger.error("server找不到主机",e);
		} catch (IOException e) {
			logger.error("server服务器建立socket连接失败",e);
		}finally{
			writer.close();
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		DetectionServer server = new DetectionServer(9999);
		server.server();
	}
	
}
