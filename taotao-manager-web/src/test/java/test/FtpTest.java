package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;

public class FtpTest {

	@Test
	public void testFtpClient() throws SocketException, IOException
	{
		//创建FtpClient对象
		FTPClient ftpClient = new FTPClient();
		
		//创建连接
		ftpClient.connect("192.168.0.103", 21);
		//登录ftp
		ftpClient.login("ftpuser", "123456");

		ftpClient.setDataTimeout(60000);       //设置传输超时时间为60秒 
		ftpClient.setConnectTimeout(60000);       //连接超时为60秒
		System.out.println(ftpClient);
		//上传文件
		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\ppc\\Desktop\\1510499353872950.jpg"));
		System.out.println(inputStream);
		//設置為被動模式
		ftpClient.enterLocalPassiveMode();
		//文件类型
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/image");
		ftpClient.storeFile("1510499353872950.jpg", inputStream);
		
		//close
		ftpClient.logout();
	}
	
	@Test
	public void ftpDownload()
	{
		//创建FtpClient对象
		FTPClient ftpClient = new FTPClient();
		
		try {
			int reply;
			
			//创建连接
			ftpClient.connect("192.168.0.103", 21);
			//登录ftp
			ftpClient.login("ftpuser", "123456");
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            
            //设置linux环境
            FTPClientConfig conf = new FTPClientConfig( FTPClientConfig.SYST_UNIX);
            ftpClient.configure(conf);
			
          //设置访问被动模式
            ftpClient.setRemoteVerificationEnabled(false);
            ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory("/home/ftpuser/www/image");// 转移到FTP服务器目录
			FTPFile[] fs = ftpClient.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals("hello9.png")) {
					File localFile = new File("C:\\Users\\ppc\\Desktop" + "/"+ff.getName());

					OutputStream is = new FileOutputStream(localFile);
					ftpClient.retrieveFile(ff.getName(), is);
					is.close();
				}
			}

			ftpClient.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
	}
}
