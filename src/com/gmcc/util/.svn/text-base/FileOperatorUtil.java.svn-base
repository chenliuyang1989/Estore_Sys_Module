package com.gmcc.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.gmcc.exception.FunctionalException;
import com.opensymphony.xwork2.util.LocalizedTextUtil;



public class FileOperatorUtil {

private static final int BUFFER_SIZE = 64 * 1024; 
	
	private  String getExtention(String fileName)  {
        int pos = fileName.lastIndexOf( "." );
        return fileName.substring(pos);
   } 
	/**
	 * 取得文件内容(有空行时立即打断)
	 * @param srcFile
	 * @param targetPath
	 * @param extention
	 * @return
	 * @throws Exception
	 */
	public List getFileContent(File srcFile,File targetPath,String extention) throws Exception{
		BufferedReader in = null; 
		List<String> result = null;
		String fileName="";
		try{
			fileName=this.copy(srcFile, targetPath,extention);
//			in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(targetPath,fileName))), BUFFER_SIZE); 
			File file=new File(targetPath,fileName);
			in = new BufferedReader(new InputStreamReader(new FileInputStream(file),getFileType(file)), BUFFER_SIZE); 
//			 in = new BufferedReader(new InputStreamReader(new FileInputStream(this.uploadFile)), BUFFER_SIZE); 
		    byte [] buffer = new byte [BUFFER_SIZE];
		    String lineContent = null;
			int lineNum = 0;
			result = new ArrayList();
			while ((lineContent = in.readLine()) != null&& !"".equals(lineContent.trim())) {
				lineNum++;
				if (lineNum > 1){
					result.add(lineContent);
				}
					
			}
		}finally{
			if (in != null){
				in.close();
				in=null;
			}
			File file = new File(targetPath, fileName);
			if(file.exists()){
				deleteFile(file);
			}
		}
		return result;
	}
	/***
	 * 获取excle数据
	 * @param wb
	 * @author xwf
	 * @return List<String> 格式为| data1|data2|。。。
	 * @throws Exception 
	 */
	public List<String> getExcelSheetData(HSSFWorkbook wb) throws Exception {
	
		List<String> excelList = new ArrayList<String>();
		HSSFSheet sheet = wb.getSheetAt(0);
		
		//获取列数
		int cellNuber =0;
		HSSFRow FirstRow = sheet.getRow(sheet.getFirstRowNum());
		for(int i=0;2>1;i++)
		{
			if (FirstRow.getCell(i) != null)
			{
				cellNuber++;
			}
			else
			{
				break;
			}
			
		}
		
		//遍历行数据
		for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			HSSFRow row = sheet.getRow(i);
			
			if(null != row)
			{
			StringBuffer rowString = new StringBuffer("|");
			int cellNum = row.getPhysicalNumberOfCells();
			
			for(int j=0;j<cellNuber;j++)
				{
					if (null != row.getCell(j))
					{
						try
						{
							if (row.getCell(j).getCellType() == HSSFCell.CELL_TYPE_STRING)
							{
								rowString.append(row.getCell(j).getStringCellValue());
								rowString.append("|");
							} else
							{
								String str= String.valueOf(row.getCell(j).getNumericCellValue());
								String strTemp = str.substring(0, str.length()-2);
								rowString.append(strTemp);
								rowString.append("|");
							}
							
						} catch (Exception ExcelError) 
						{
							throw new FunctionalException("第"+i+"行导入数据不正确！");
						}
	
					 }
					else
					{
						rowString.append("|");
					}
				}
				excelList.add(rowString.toString());
			}
			
		}
		return excelList;
		
	}
	/**
	 * 读取页面上文件保存到一个临时文件
	 * @param srcFile
	 * @param targetPath
	 * @param extention
	 * @return
	 */
	public  String copy(File srcFile,File targetPath,String extention)  {
		String fileName="";
        try  {
           InputStream in = null ;
           OutputStream out = null ;
            try  { 
            	if(extention!=null && !"".equals(extention)){
            		 fileName= new Date().getTime() + "."+extention;
            	}else{
            		fileName= new Date().getTime() + getExtention(srcFile.getName());
            	}
            	
//            	 File filePath=new File();
//            	 File filePath=new File("c:/Upload" );
            	 if(!targetPath.exists()){
            		 targetPath.mkdirs();
            	 }
            	File file = new File(targetPath, fileName);
            	in = new BufferedInputStream( new FileInputStream(srcFile), BUFFER_SIZE);
            	out=new BufferedOutputStream( new FileOutputStream(file), BUFFER_SIZE);
                byte [] buffer = new byte [BUFFER_SIZE];
                int len;
                while ((len = in.read(buffer)) != -1) {
    			    out.write(buffer, 0, len);
    			}
            } finally  {
                if ( null != in)  {
                   in.close();
               } 
                 if ( null != out)  {
                   out.close();
               } 
           } 
        } catch (Exception e)  {
           e.printStackTrace();
       } 
        return fileName;
   } 
	/**
	 * 删除临时文件
	 * @param f
	 * @return
	 */
	private  boolean deleteFile(File f){
        if (f.isFile())
            f.delete();
        return true;
    }
	public boolean deleteDir(File f){
		if(f.isDirectory()){
			File[] files = f.listFiles();
			for(int i=0;i<files.length;i++){
				if(files[i].isDirectory()) deleteDir(files[i]);
				else deleteFile(files[i]);
			}
		}
        f.delete();
        return true;
	}
	/**
	 * 检查数据是否重复(有多列数据)
	 * @param resultList 数据LIST
	 * @param column 第几列-从1开始
	 * @param errorMessKey 各列重复时返回的提示信息
	 * @return
	 * @throws Exception
	 */
	public String checkFile(List<String> resultList,Integer[] column,String[] errorMessKey)throws Exception{
		Locale locale = Locale.getDefault();
		String strPrev="";
		String[] arrPrev=null;
		String prev="";
		String strNext="";
		String[] arrNext=null;
		String next="";
		String errorMess="";
		for(int i=0;i<resultList.size()-1;i++){
			strPrev=resultList.get(i);
			arrPrev=strPrev.split("\\|");
			for(int j=i+1;j<resultList.size();j++){
				strNext=resultList.get(j);
				arrNext=strNext.split("\\|");
				for(int k=0;k<column.length;k++){
					prev=arrPrev[column[k].intValue()];
					next=arrNext[column[k].intValue()];
					if(prev!=null && next!=null && !prev.trim().equals("") && !next.trim().equals("") && next.trim().equals(prev.trim())){
						errorMess=LocalizedTextUtil.findDefaultText(errorMessKey[k], locale,new Integer[]{i+1,j+1});
						return errorMess;
					}
				}
				
			}
		}
		return null;
	}
	/**
	 * 检查数据是否重复(只有一列数据)
	 * @param resultList 数据LIST
	 * @param errorMess 重复时返回的提示信息
	 * @return
	 * @throws Exception
	 */
	public String checkFileOneCol(List<String> resultList,String errorMess)throws Exception{
		Locale locale = Locale.getDefault();
		String strPrev="";
		String strNext="";
		for(int i=0;i<resultList.size()-1;i++){
			strPrev=resultList.get(i);
			for(int j=i+1;j<resultList.size();j++){
				strNext=resultList.get(j);
					if(strPrev!=null && strNext!=null && !strPrev.trim().equals("") && !strNext.trim().equals("") && strNext.trim().equals(strPrev.trim())){
						errorMess=LocalizedTextUtil.findDefaultText(errorMess, locale,new Integer[]{i+1,j+1});
						return errorMess;
					}
			}
		}
		return null;
	}
	public static void moveTo(File file, String targetPath, String newFileName) throws Exception {
		FileInputStream input = null;
		BufferedInputStream inBuff = null;
		FileOutputStream output = null;
		BufferedOutputStream outBuff = null;
		try {
			String targetFileName = file.getName();
			if (newFileName != null) {
				targetFileName = newFileName;
			}
			File targetFile = new File(targetPath, targetFileName);
			input = new FileInputStream(file);
			inBuff = new BufferedInputStream(input);
			File t = new File(targetPath);
			if(!t.exists())t.mkdirs();
			output = new FileOutputStream(targetFile);
			outBuff = new BufferedOutputStream(output);
			
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
			    outBuff.write(b, 0, len);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				outBuff.flush();
				inBuff.close();
				outBuff.close();
				output.close();
				input.close();
			} catch (Exception e) {
				throw e;
			}
		}
        file.delete();
	}
	
	/**
	 * 获取文件类型：支持读取GBK UTF-8 UTF-16LE UTF-16BE编码的文件
	 * @param file_path 文件路径
	 * @return 文件类型
	 */
	public static String getFileType(File file) throws Exception{
		if (file == null)
			return null;
		java.io.File f = file;
		BufferedInputStream bis = null;
		byte[] first3Bytes = new byte[3];
		String charset = "GBK";
		boolean checked = false;
		String filePath = file.getPath();

		try {
			bis = new BufferedInputStream(new FileInputStream(f));
			bis.mark(0);

			int read = bis.read(first3Bytes, 0, 3);

			if (read == -1) {
				return charset;
			} else if (first3Bytes[0] == (byte) 0xFF
					&& first3Bytes[1] == (byte) 0xFE) {
				charset = "UTF-16LE";
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xFE
					&& first3Bytes[1] == (byte) 0xFF) {
				charset = "UTF-16BE";
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xEF
					&& first3Bytes[1] == (byte) 0xBB
					&& first3Bytes[2] == (byte) 0xBF) {
				charset = "UTF-8";
				checked = true;
			}

			bis.reset();

			if (!checked) {
				int loc = 0;
				while ((read = bis.read()) != -1) {
					loc++;
					if (read >= 0xF0) {
						break;
					}
					if (0x80 <= read && read <= 0xBF) { // 单独出现BF以下的，也算是GBK
						break;
					}
					if (0xC0 <= read && read <= 0xDF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) { // 双字节 (0xC0 - 0xDF)
															// (0x80 -
															// 0xBF),也可能在GB编码内
							continue;
						} else {
							break;
						}
					} else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) {
							read = bis.read();
							if (0x80 <= read && read <= 0xBF) {
								charset = "UTF-8";
								break;
							} else {
								break;
							}
						} else {
							break;
						}
					}
				}
			}

			if (bis != null) {
				bis.close();
			}
		} catch (Exception e) {
			throw e;
		}

		return charset;
	}
}
