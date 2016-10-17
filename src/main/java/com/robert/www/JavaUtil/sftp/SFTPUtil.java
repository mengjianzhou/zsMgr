package com.robert.www.JavaUtil.sftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * SFTP上传工具
 * @author zhaobo
 * @date   2016-09-09
 */
public class SFTPUtil {
	
	private static final Logger logger = Logger.getLogger("SFTPUtil");
	private static SFTPUtil sftpUtil;
	private ChannelSftp sftp;
	private PropertiesConfiguration commonsProperties;
	private String SFTP_FILE_ROOT;
	private String UPLOAD_FILE_BASE_PATH;
	
	private SFTPUtil(){
		try {
			commonsProperties = new PropertiesConfiguration("common.properties");
			String SFTP_REMOTE_HOST_ADDRESS = commonsProperties.getString("SFTP_REMOTE_HOST_ADDRESS");
			int SFTP_REMOTE_PORT = Integer.parseInt(commonsProperties.getString("SFTP_REMOTE_PORT"));
			String SFTP_USERNAME = commonsProperties.getString("SFTP_USERNAME");
			String SFTP_PASSWORD = commonsProperties.getString("SFTP_PASSWORD");
			SFTP_FILE_ROOT = commonsProperties.getString("SFTP_FILE_ROOT");
			UPLOAD_FILE_BASE_PATH = commonsProperties.getString("UPLOAD_FILE_BASE_PATH");
			
			initConnect(SFTP_REMOTE_HOST_ADDRESS, SFTP_REMOTE_PORT, SFTP_USERNAME, SFTP_PASSWORD);
		} catch (ConfigurationException e) {
			e.printStackTrace();
			logger.info("文件找不到!");
		}
	}
	
	public static SFTPUtil getInstance(){
		if(sftpUtil==null){
			sftpUtil =  new SFTPUtil();
		} 
		return sftpUtil;
	}
	
	
	
	/**
	 * 连接sftp服务器
	 * @param host 主机
	 * @param port 端口
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	private ChannelSftp initConnect(String host, int port, String username, String password) {
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			Session sshSession = jsch.getSession(username, host, port);
			logger.info("Session created.");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			logger.info("Session connected.");
			logger.info("Opening Channel.");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			logger.info("Connected to " + host + ".");
		} catch (Exception e) {
			logger.log(Level.INFO, "文件服务器连接不上address="+host+" port="+port, e);
		}
		return sftp;
	}

	/**
	 * 上传文件
	 * 
	 * @param directory 上传的目录
	 * @param uploadFile 要上传的文件
	 * @param sftp
	 */
	public void upload(String fileName) {
		logger.info("上传文件到:"+SFTP_FILE_ROOT+"目录下,上传的文件名称是："+UPLOAD_FILE_BASE_PATH+fileName);
		try {
			sftp.cd(SFTP_FILE_ROOT);
			File file = new File(UPLOAD_FILE_BASE_PATH+fileName);
			sftp.put(new FileInputStream(file), file.getName());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("上传文件失败");
		}
		logger.info("上传文件成功！");
	}
	
	/**
	* 下载文件
	* @param directory 下载目录
	* @param downloadFile 下载的文件
	* @param saveFile 存在本地的路径
	* @param sftp
	*/
	public void download(String directory, String downloadFile,String saveFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file=new File(saveFile);
			sftp.get(downloadFile, new FileOutputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* 删除文件
	* @param directory 要删除文件所在目录
	* @param deleteFile 要删除的文件
	* @param sftp
	*/
	public void delete(String directory, String deleteFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建目录
	 * @param sftp
	 * @param baseFilePath
	 * @param forderName
	 */
	public static void createDirectory(ChannelSftp sftp,String baseFilePath,String forderName){
		try {
			sftp.cd(baseFilePath);
			sftp.mkdir(forderName);
		} catch (SftpException e) {
			e.printStackTrace();
		}
		System.out.println("create directory["+ baseFilePath +"/"+ forderName +"] success!");
	}
	
	
	/**
	* 列出目录下的文件
	* @param directory 要列出的目录
	* @param sftp
	* @return
	* @throws SftpException
	*/
	public Vector listFiles(String directory, ChannelSftp sftp) throws SftpException{
		return sftp.ls(directory);
	}
	
	public static void main(String[] args) {
		SFTPUtil sftpClient = getInstance();
		
		//创建目录
//		createDirectory(sftp,SFTP_FILE_ROOT,"zbtest2");
		
		//上传文件
		String fileName = "zbTest.txt";
		sftpClient.upload(fileName);
		
		//下载文件
//		sf.download(directory, downloadFile, saveFile, sftp);
		//删除文件
//		sf.delete(directory, deleteFile, sftp);
	}
}























