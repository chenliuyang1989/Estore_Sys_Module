package com.gmcc.webapp.action.sys;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {
	private final Logger log = Logger.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;
	private String fileName;
	
	private int xtype=0;  //0 为工程相对路径  1为 系统绝对路径

	
	
	public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    @Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public InputStream getUploadFiles(){
		try {
			
			String temp = new String(fileName.getBytes("ISO-8859-1"),"gbk");
			
			if(xtype==0){
				return ServletActionContext.getServletContext().getResourceAsStream(File.separator+ "downFile"+File.separator+temp);
			}else if(xtype==1){
				return new FileInputStream(new File(fileName));
			}else{
			    return null;
			}
		
		} catch (Exception e) {
			log.error("下载文件错误",e);
			return null;
		}		
	}

	public int getXtype() {
		return xtype;
	}

	public void setXtype(int xtype) {
		this.xtype = xtype;
	}
	
	
	
	
	
}
