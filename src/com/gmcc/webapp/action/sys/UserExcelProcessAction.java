package com.gmcc.webapp.action.sys;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.security.providers.encoding.PasswordEncoder;
import com.gmcc.model.Element;
import com.gmcc.model.HrCompany;
import com.gmcc.model.Role;
import com.gmcc.model.User;
import com.gmcc.service.AuthorityManager;
import com.gmcc.util.AppContentGmcc;
import com.gmcc.webapp.action.base.DisplayTagQueryAction;
import com.ibm.service.IOperateManager;

public class UserExcelProcessAction extends DisplayTagQueryAction<User, Long> {
	private File myFile;
	private String myFileFileName;

	protected IOperateManager<User, Long> operateManager;

	public IOperateManager<User, Long> getOperateManager() {
		return operateManager;
	}

	public void setOperateManager(IOperateManager<User, Long> operateManager) {
		this.operateManager = operateManager;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	private PasswordEncoder passwordEncoder;
	private AuthorityManager authorityManager;

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public AuthorityManager getAuthorityManager() {
		return authorityManager;
	}

	public void setAuthorityManager(AuthorityManager authorityManager) {
		this.authorityManager = authorityManager;
	}

	public String execute() throws Exception {
		this.addActionMessage("下载成功");
		return SUCCESS;
	}

	public InputStream getUploadFiles() throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建sheet页
		HSSFSheet sheet = wb.createSheet("批量创建用户");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue(new HSSFRichTextString("用户名*"));
		row.createCell(1).setCellValue(new HSSFRichTextString("真实姓名*"));
		row.createCell(2).setCellValue(new HSSFRichTextString("联系电话*"));
		row.createCell(3).setCellValue(new HSSFRichTextString("密码*"));
		row.createCell(4).setCellValue(new HSSFRichTextString("用户类型*"));
		row.createCell(5).setCellValue(new HSSFRichTextString("用户角色*"));
		row.createCell(6).setCellValue(new HSSFRichTextString("机构"));
		row.createCell(7).setCellValue(new HSSFRichTextString("地市"));
		row.createCell(8).setCellValue(new HSSFRichTextString("渠道"));
		row.createCell(9).setCellValue(
				new HSSFRichTextString("是否激活(默认激活，不激活输入：0)"));
		row.createCell(10).setCellValue(
				new HSSFRichTextString("是否短信验证(默认不验证，验证的话需输入：1)"));
		row.createCell(11).setCellValue(new HSSFRichTextString("备注"));
		row.createCell(12).setCellValue(new HSSFRichTextString("身份证号"));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		os.flush();
		byte[] aa = os.toByteArray();
		os.close();
		return new ByteArrayInputStream(aa, 0, aa.length);

	}

	public String bassAddUser() throws Exception {
		try {
			DecimalFormat df = new DecimalFormat("#");
			InputStream in = new FileInputStream(myFile);
			HSSFWorkbook wb = new HSSFWorkbook(in);
			HSSFSheet sheet = wb.getSheetAt(0);

			int rowNum = sheet.getPhysicalNumberOfRows();

			int successNum = 0;
			int errorNum = 0;
			for(int i = 0; i < sheet.getPhysicalNumberOfRows(); i++)
			{
				HSSFRow row = sheet.getRow(i);
				if(row ==null || row.getCell(0) == null || "".equals(row.getCell(0).toString().trim())){
					rowNum  = rowNum-1;
					continue;
				}
			}
			for (int i = 1; i < rowNum; i++) {
				User user = new User();
				HSSFRow row = sheet.getRow(i);
				// 用户名的校验
			
				if (row.getCell(0) == null) {
					this.addActionError("用户名不能为空，第" + i + "行添加失败！");
					continue;
				}
				String userName = row.getCell(0).toString().trim();
				if (authorityManager.getUserbyName(userName) != null) {
					this.addActionError("用户名不能重复，第" + i + "行添加失败！");
					continue;
				}
				user.setUsername(userName);

				// 真实姓名

				if (row.getCell(1) == null) {
					this.addActionError("用户真实姓名不能为空，第" + i + "行添加失败！");
					continue;
				}
				String fullName = row.getCell(1).toString().trim();
				if ("".equals(fullName)) {
					this.addActionError("用户真实姓名不能为空，第" + i + "行添加失败！");
					continue;
				}
				user.setFullname(fullName);

				// 用户手机号码的判断
				if (row.getCell(2) == null) {
					this.addActionError("手机号码不能为空，第" + i + "行添加失败！");
					continue;
				}

				String telPhone;
				switch (row.getCell(2).getCellType()) {
				case (HSSFCell.CELL_TYPE_NUMERIC):
					telPhone = df.format(row.getCell(2).getNumericCellValue());
					break;
				case (HSSFCell.CELL_TYPE_STRING):
					telPhone = row.getCell(2).toString();
					break;
				default:
					telPhone = "";
				}

				if (!isMobileNO(telPhone.trim())) {
					this.addActionError("手机号码不正确，第" + i + "行添加失败！");
					continue;
				}

				user.setPhoneNumber(telPhone);

				// 用户密码判断
				String password = "";
				if (row.getCell(3) == null) {
					this.addActionError("用户密码不能为空，第" + i + "行添加失败！");
					continue;
				}
				switch (row.getCell(3).getCellType()) {
				
				case (HSSFCell.CELL_TYPE_NUMERIC):
					password = df.format(row.getCell(3).getNumericCellValue());
					break;
				case (HSSFCell.CELL_TYPE_STRING):
					password = row.getCell(3).toString().trim();
					break;
				}
				user.setPassword(passwordEncoder.encodePassword(password, null));

				// 用户类型判断
				if (row.getCell(4) == null) {
					this.addActionError("用户类型不能为空，第" + i + "行添加失败！");
					continue;
				}

				if ("后台用户".equals(row.getCell(4).toString().trim())) {
					Element elemnt = this.authorityManager
							.getItemsOfGroupByGroupNameAndEleName(
									AppContentGmcc.USER_TYPE_GROUP, "后台用户");
					user.setUserType(elemnt);

				} else if ("前台用户".equals(row.getCell(4).toString().trim())) {
					Element elemnt = this.authorityManager
							.getItemsOfGroupByGroupNameAndEleName(
									AppContentGmcc.USER_TYPE_GROUP, "前台用户");
					user.setUserType(elemnt);
				} else {
					this.addActionError("用户类型填写不正确，第" + i + "行添加失败！");
					continue;
				}
				// 角色的判断
				if (row.getCell(5) == null) {
					this.addActionError("角色名不能为空，第" + i + "行添加失败！");
				}
				String[] roleNames = row.getCell(5).toString().split("，");// 角色名称的判断
				boolean roleMark = false;
				for (int j = 0; j < roleNames.length; j++) {
					String roleName = roleNames[j];
					if (authorityManager.getRoleByName(roleName) == null) {
						this.addActionError("角色名称填写不正确，第" + i + "行添加失败！");
						roleMark = true;
						break;
					}
					Role role = authorityManager.getRoleByName(roleName);
					user.addRole(role);
				}
				if (roleMark) {
					continue;
				}
				
				if(authorityManager.getRoleTypeByNames(roleNames)>1)
				{
					this.addActionError("一个用户只能有一种角色类型，第" + i + "行添加失败！");
					continue;

				}
				
				
				

				// 机构名称
				boolean comMark = false;
				if (row.getCell(6) != null) {
					String[] comNames = row.getCell(6).toString().trim()
							.split("，");
					for (int k = 0; k < comNames.length; k++) {
						String comName = comNames[k];
						if (authorityManager.getHrComByName(comName) == null) {
							this.addActionError("机构名称填写不正确，第" + i + "行添加失败！");
							comMark = true;
							break;
						}
						HrCompany company = authorityManager
								.getHrComByName(comName);
						user.addComp(company);
					}
				}
				if (comMark) {
					continue;
				}

				// 地市填写
				String cityId;
				if (row.getCell(7) == null) {
					user.setCitySysID("-1");// 默认用户地市编码
					cityId = "-1";
				} else {
					String cityName = row.getCell(7).toString().trim();
					Element element = authorityManager
							.getItemsOfGroupByGroupNameAndEleName(
									AppContentGmcc.CITY_GROUPNAME, cityName);
					if (element == null) {
						this.addActionError("地市名称填写不正确，第" + i + "行添加失败！");
						continue;
					}
					user.setCitySysID(element.getEleCode());
					cityId = element.getEleCode();
				}

				if (this.authorityManager.getUserByFullNameAndCityId(fullName,
						cityId) != null) {
					this.addActionError("一个地市的一个用户姓名只能创建一个账号，第" + i + "行添加失败！");
					continue;
				}
				// 渠道类型
				if (row.getCell(8) == null) {
					user.setChannel("-1");
				} else {
					Element channelElement = authorityManager
							.getItemsOfGroupByGroupNameAndEleName(
									AppContentGmcc.CHANNEL_GROUPNAME, row
											.getCell(8).toString().trim());
					if (channelElement == null) {
						this.addActionError("渠道类型填写不正确，第" + i + "行添加失败！");
						continue;
					} else
						user.setChannel(channelElement.getEleCode());
				}
				// 是否激活处理
				String activeMark;

				if (row.getCell(9) == null) {
					user.setEnabled(true);
				} else {
						switch (row.getCell(9).getCellType()) {
						case (HSSFCell.CELL_TYPE_NUMERIC):
							activeMark = df.format(row.getCell(9).getNumericCellValue());
							break;
						case (HSSFCell.CELL_TYPE_STRING):
							activeMark = row.getCell(9).toString();
							break;
						default:
							activeMark = "";
						}

						if("0".equals(activeMark.toString().trim())) 
				        	user.setEnabled(false);
						else if("1".equals(activeMark.toString().trim()))
								user.setEnabled(true);
					
					
				} 
				// 是否短信验证
				if (row.getCell(10) == null) {
					user.setIsPhoneCheck(Long.valueOf(0));
				}
				if (row.getCell(10) != null) {
					boolean pMark;
					String id = "";
					switch (row.getCell(10).getCellType()) {
					case (HSSFCell.CELL_TYPE_NUMERIC):
						id = df.format(row.getCell(10).getNumericCellValue());
						break;
					case (HSSFCell.CELL_TYPE_STRING):
						id = row.getCell(10).toString().trim();
						break;
					}

					if ("1".equals(id)) {
						user.setIsPhoneCheck(Long.valueOf(1));
					}
					else if("0".equals(id))
					{
						user.setIsPhoneCheck(Long.valueOf(0));
					}
				}

				// 备注
				if (row.getCell(11) != null) {
					user.setRemark(row.getCell(11).toString().trim());
				}
				// 身份证号码
				if (row.getCell(12) != null) {
					String identifyNumber="";

					switch (row.getCell(12).getCellType()){
					case (HSSFCell.CELL_TYPE_NUMERIC):
						identifyNumber = df.format(row.getCell(10).getNumericCellValue());
						break;
					case (HSSFCell.CELL_TYPE_STRING):
						identifyNumber = row.getCell(10).toString();
						break;
					}
					user.setIdentifyNumber(identifyNumber.trim());
				}

				try {
					user.setCreatedTime(new Date());
					user.setErrorNum(0L);
					user.setActivateTime(new Date());
					User u = operateManager.merge(user);
					authorityManager.saveAuthorityChanged(u.getUsername());
					successNum += 1;
				} catch (Exception e) {
					errorNum += 1;
					this.addActionError("用户" + userName + "保存失败，此用户需要重新添加！");
				}

			}

			this.addActionMessage("本次成功导入条数为：" + successNum + ",失败条数为："
					+ (rowNum-1 - successNum));
			return INPUT;
		} catch (Exception e) {
			this.addActionError("文件导入失败，请检查文件格式后重新导入！");
			return INPUT;
		}

	}

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public String bassAdd() throws Exception {
		return "bassAdd";
	}

}
