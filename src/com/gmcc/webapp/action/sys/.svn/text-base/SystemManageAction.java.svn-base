package com.gmcc.webapp.action.sys;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;


public class SystemManageAction {

	private static final long serialVersionUID = -764591744392808015L;
	protected final Log log = LogFactory.getLog(getClass());
	

	private String command;
	private String message;
	private String host;
	
	private String fileName=File.separator+"batchfile"+File.separator+"incoming"+File.separator+"hw"+File.separator+"reloadSystem.ini";
	
	//private String fileName="d:/aaa.txt";
	
	public String init(){
		return ActionSupport.SUCCESS;
	}
	
	public String  saveCommand(){
		
		String contex=host+"|"+command;
		
		boolean flag=appendErrMsgToFile(contex,fileName);
		
		if(flag){
			message="内容【"+contex+"】已保存到文件";
		}else{
			message="内容保存到文件失败";
		}
		
		return ActionSupport.SUCCESS;
	}

	
	
	public boolean appendErrMsgToFile(String msg,String t1){
		OutputStreamWriter writer=null;
		try {
		File tmpFile=new File(t1);
		
		//根据文件编码读取文件内容
		writer = new OutputStreamWriter(new FileOutputStream(t1),"utf-8");
		writer.write(msg);
		
		} catch (IOException e) {
			log.error(" ",e);
			return false;
		} finally {
			try {
				if (writer != null)writer.close();
			} catch (IOException e) {
				log.error(" ",e);
			}
		}
		
		
		return true;
	}
	

	


	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}
	
	

}
