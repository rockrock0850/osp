/**
 * Copyright (c) 2017 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 */

package com.fet.crm.osp.platform.core.common.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * 
 * @author LawrenceLai
 */
public class FTPUtil {

	private String server;
	private int port;
	private String user;
	private String password;
	private String dir;
	
	private FTPUtil() {
		;
	}
	
	public FTPUtil(String server, int port, String user, String password, String dir) {
		this.server = server;
		this.port = port;
		this.user = user;
		this.password = password;
		this.dir = dir;
	}
	
	public boolean upload(File file) {
		if (file != null) {
			// STEP.1 登入取得連線資源
			FTPClient client = init();
			
			// STEP.2 上傳檔案
			if (client != null) {
				upaload(client, file);
			}
			
			// STEP.3 登出關閉連線資源
			closeConnection(client);
			
			return true;
		}
		
		return false;
	}
	
	public boolean upload(File file, String name) {
		if (file != null) {
			// STEP.1 登入取得連線資源
			FTPClient client = init();
			
			// STEP.2 上傳檔案
			if (client != null) {
				upaload(client, file, name);
			}
			
			// STEP.3 登出關閉連線資源
			closeConnection(client);
			
			return true;
		}
		
		return false;
	}
	
	private FTPClient getInstance() {
		return new FTPClient();
	}
	
	private FTPClient init() {
		FTPClient client = getInstance();
		
		// STEP.1 取得連線
		boolean isConn = createConnection(client, server, port);
		if (!isConn) {
			showServerReply(client);
			
			return null;
		}
		
		// STEP.2 使用者登入
		boolean isLogin = login(client, user, password);
		if (!isLogin) {
			showServerReply(client);
			
			return null;
		}
		
		// STEP.2 切換至工作目錄
		boolean isWorkDir = changeDir(client, dir);
		if (!isWorkDir) {
			showServerReply(client);
			
			return null;
		}
		
		return client;
	}
	
	private boolean createConnection(FTPClient client, String server, int port) {
		try {
			client.connect(server, port);
			
			int replyCode = client.getReplyCode();
			
			if (FTPReply.isPositiveCompletion(replyCode)) {
				return true;
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private void closeConnection(FTPClient client) {
		logout(client);
	}
	
	private boolean login(FTPClient client, String user, String password) {
		boolean isLogin = false;
		
		try {
			isLogin = client.login(user, password);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return isLogin;
	}
	
	private boolean changeDir(FTPClient client, String dir) {
		boolean isChange = false;
		
		try {
			isChange = client.changeWorkingDirectory(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return isChange;
	}
	
	private void logout(FTPClient client) {
		try {
			client.logout();
			client.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			client = null;
		}
	}
	
	private boolean upaload(FTPClient client, File file) {
		return upaload(client, file, null);
	}
	
	private boolean upaload(FTPClient client, File file, String fileName) {
		if (file != null) {
			InputStream inputStream = null;
			
			if (fileName == null) {
				fileName = file.getName();
			}
			
			try {
				inputStream = new FileInputStream(file);
		            
				return client.storeFile(fileName, inputStream);
		    } catch (FileNotFoundException e) {
				e.printStackTrace();
			}  catch (IOException e) {
		        e.printStackTrace();
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				inputStream = null;
			}
			
			showServerReply(client);
		}
		
		return false;
	}

	private void showServerReply(FTPClient client) {
		if (client != null) {
			String[] replies = client.getReplyStrings();

			if (replies != null && replies.length > 0) {
				for (String reply : replies) {
					System.out.println("SERVER: " + reply);
				}
			}
		}
	}

}
