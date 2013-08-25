package com.gmcc.util;

/**
 * 
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.DynaBean;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author xiangwenfei
 *
 */
/*
生成EXCEL的数据格式定义：
{
	name: '',
	widths: [xxx,…,xxx],
	clazz" '',
	style: '',
	data: [
		{
			height: xxx,
			clazz" '',
			style: '',
			cell: [
				{
					range: 'xxx.xxx',
					text: '',
					clazz: '',
					style: ''
},…{
…
}
]
		},…,{
		}
], …,[
…
]
}
*/
public class ExcelOperate {
	public static final String FILE_NAME = "name";
	public static final String CSS_CLASS = "clazz";
	public static final String CSS_STYLE = "style";
	public static final String COLUMN_WIDTHS = "widths";
	public static final String ROW_HEIGHT = "height";
	public static final String CELL_RANGE = "range";
	public static final String CELL_TEXT = "text";
	public static final String FIELD_DATA = "data";
	public static final String FIELD_CELL = "cell";
	
	private static final String CSS_DEFAULT = "-default-";
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private static Map<String, ExcelCss> cssMap = new HashMap<String, ExcelCss>();
	static {
		try {
			ExcelCss css = new ExcelCss("宋体", (short)9, HSSFFont.BOLDWEIGHT_NORMAL, false);
			cssMap.put(CSS_DEFAULT, css);
			
			ExcelCss title = css.clone();
			title.setFontHeightInPoints((short)12);
			title.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cssMap.put("title", title);
			
			ExcelCss top_title = css.clone();
			top_title.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cssMap.put("top_title", top_title);
			
			ExcelCss top_label = css.clone();
			top_label.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			cssMap.put("top_label", top_label);
			
			ExcelCss top_text = css.clone();
			top_text.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cssMap.put("top_text", top_text);
			
			ExcelCss text = css.clone();
			text.setBorderTop((short)1);
			text.setBorderBottom((short)1);
			text.setBorderLeft((short)1);
			text.setBorderRight((short)1);
			text.setBackgroundColor(HSSFColor.WHITE.index);
			text.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cssMap.put("text", text);
			
			ExcelCss text_left = text.clone();
			text_left.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cssMap.put("text_left", text_left);
			
			ExcelCss text_right = text.clone();
			text_right.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			cssMap.put("text_right", text_right);
			
			ExcelCss header = text.clone();
			header.setBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
			header.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cssMap.put("header", header);
			
			ExcelCss header_left = header.clone();
			header_left.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cssMap.put("header_left", header_left);
			
			ExcelCss header_right = header.clone();
			header_right.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			cssMap.put("header_right", header_right);
			
			ExcelCss header_tb = header.clone();
			header_tb.setBorderLeft((short)0);
			header_tb.setBorderRight((short)0);
			cssMap.put("header_tb", header_tb);
			
			ExcelCss header_tbl = header.clone();
			header_tbl.setBorderRight((short)0);
			cssMap.put("header_tbl", header_tbl);
			
			ExcelCss header_tbr = header.clone();
			header_tbr.setBorderLeft((short)0);
			cssMap.put("header_tbr", header_tbr);
			
			ExcelCss label = text.clone();
//			label.setBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
			cssMap.put("label", label);
			
			ExcelCss label_left = label.clone();
			label_left.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cssMap.put("label_left", label_left);
			
			ExcelCss label_indent = label_left.clone();
			label_indent.setIndention((short)1);
			cssMap.put("label_indent", label_indent);
			
			ExcelCss label_right = label.clone();
			label_right.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			cssMap.put("label_right", label_right);
			
			ExcelCss bottom_text = css.clone();
			bottom_text.setBorderBottom((short)1);
			cssMap.put("bottom_text", bottom_text);
			
			ExcelCss bottom_text_white = bottom_text.clone();
			bottom_text_white.setBackgroundColor(HSSFColor.WHITE.index);
			bottom_text_white.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cssMap.put("bottom_text_white", bottom_text_white);
			
			ExcelCss bottom_text_left = bottom_text.clone();
			bottom_text_left.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cssMap.put("bottom_text_left", bottom_text_left);
			
			ExcelCss bottom_text_right = bottom_text.clone();
			bottom_text_right.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			cssMap.put("bottom_text_right", bottom_text_right);
			
			ExcelCss bottom_label_left = css.clone();
			bottom_label_left.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cssMap.put("bottom_label_left", bottom_label_left);
			
			ExcelCss bottom_label_right = css.clone();
			bottom_label_right.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			cssMap.put("bottom_label_right", bottom_label_right);
			
			ExcelCss tip = css.clone();
			tip.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			tip.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			cssMap.put("tip", tip);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	private Map<ExcelCss, HSSFCellStyle> styleMap = new HashMap<ExcelCss, HSSFCellStyle>();
	
	private HSSFCellStyle getDefaultCellStyle(HSSFWorkbook hssfWorkbook) {
		return getHSSFCellStyle(hssfWorkbook, CSS_DEFAULT, "");
	}
	
	private HSSFCellStyle getHSSFCellStyle(HSSFWorkbook hssfWorkbook, String clazz, String style) {
		String[] classes = clazz.split(" ");
		
		ExcelCss css = null;
		
		for (String c : classes) {
			c = c.trim();
			
			if (!"".equals(c) && cssMap.containsKey(c)) {
				css = cssMap.get(c);
				break;
			}
		}
		
		if (css == null)
			css = cssMap.get(CSS_DEFAULT);

		HSSFCellStyle cellStyle;
		if (styleMap.containsKey(css))
			cellStyle = styleMap.get(css);
		else {
			HSSFFont font = hssfWorkbook.createFont();
			font.setFontName(css.getFontName());
			font.setFontHeightInPoints(css.getFontHeightInPoints());
			font.setBoldweight(css.getBoldweight());
			font.setItalic(css.isItalic());
			font.setStrikeout(css.isStrikeout());
			font.setUnderline(css.getUnderline());
			font.setColor(css.getColor());
			
			cellStyle = hssfWorkbook.createCellStyle();
			cellStyle.setAlignment(css.getAlignment());
			cellStyle.setVerticalAlignment(css.getVerticalAlignment());
			cellStyle.setBorderTop(css.getBorderTop());
			cellStyle.setBorderBottom(css.getBorderBottom());
			cellStyle.setBorderLeft(css.getBorderLeft());
			cellStyle.setBorderRight(css.getBorderRight());
			cellStyle.setTopBorderColor(css.getTopBorderColor());
			cellStyle.setBottomBorderColor(css.getBottomBorderColor());
			cellStyle.setLeftBorderColor(css.getLeftBorderColor());
			cellStyle.setRightBorderColor(css.getRightBorderColor());
			cellStyle.setFillForegroundColor(css.getBackgroundColor());
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyle.setWrapText(css.isWrapText());
			cellStyle.setIndention(css.getIndention());
			
			cellStyle.setFont(font);
			
			styleMap.put(css, cellStyle);
		}
		
		return cellStyle;
	}
	
	@SuppressWarnings("unchecked")
	public String buildExcel(JSONObject tableData, OutputStream result) throws IOException {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet hssfSheet = hssfWorkbook.createSheet();
		
		if (tableData.has(FILE_NAME))
			hssfWorkbook.setSheetName(0, tableData.getString(FILE_NAME));
		
		int rows = 100, cols = 250;
//		if (tableData.has(COLUMN_WIDTHS)) {
//			int i = 0;
//			
//			Collection widths = JSONArray.toCollection((JSONArray)tableData.get(COLUMN_WIDTHS));
//			for (Object width : widths) 
//				hssfSheet.setColumnWidth(i++, (Integer)width * 36);
//			
//			cols = i;
//		}
		
		String name = null;
		if (tableData.has(FILE_NAME))
			name = (String)tableData.get(FILE_NAME);
		
		if (tableData.has(FIELD_DATA)) {
			Collection rd = JSONArray.toCollection((JSONArray)tableData.get(FIELD_DATA));
			rows = rd.size();
			
	    	//占位符，作为判断每行每列是否需要添加单元格的依据
			HSSFCellStyle[][] placeholder = new HSSFCellStyle[1000][1000];//new HSSFCellStyle[rows][1000];
			int row = 0, orgCol, col = 0,cellnum = 0;
			
			for (Object obj : rd) {
				DynaBean rowData = (DynaBean)obj;
				HSSFRow hssfRow = hssfSheet.createRow(row);
				
				try {
					hssfRow.setHeight((short)((Integer)rowData.get(ROW_HEIGHT) * 16));
				} catch (Exception e) {}
				
				List list = (List)rowData.get(FIELD_CELL);
				col = 0;
				
				for (Object obj1 : list) {
					cellnum ++;
					DynaBean colData = (DynaBean)obj1;

					String clazz;
					try {
						clazz = (String)colData.get(CSS_CLASS);
					} catch (Exception e) {
						clazz = "";
					}
					
					String style;
					try {
						style = (String)colData.get(CSS_STYLE);
					} catch (Exception e) {
						style = "";
					}

					
					HSSFCellStyle cellStyle = getHSSFCellStyle(hssfWorkbook, clazz, style);
					
					
					
					/*
					 * 设置样式start
					 * */
					
					  // 创建单元格样式
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					cellStyle.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

				    // 设置边框
					cellStyle.setBottomBorderColor(HSSFColor.RED.index);
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

					
					/***
					 * 设置样式end
					 */
					orgCol = col;
			    	while (placeholder[row][col] != null)
			    		col++;
					
					for (;orgCol < col; orgCol++) {
						HSSFCell cell = hssfRow.createCell(orgCol);
						cell.setCellStyle(placeholder[row][orgCol]);
					}
					
					int rowSpan, colSpan;
					try {
						String range = (String)colData.get(CELL_RANGE);
						String[] tmp = range.split("[.]");
						
						rowSpan = Integer.parseInt(tmp[0]);
						colSpan = Integer.parseInt(tmp[1]);
						
						
					} catch (Exception e) {
						rowSpan = 1;
						colSpan = 1;
					}
					
					if (rowSpan > 1 || colSpan > 1)
						hssfSheet.addMergedRegion(new CellRangeAddress(row, row + rowSpan - 1, col, col + colSpan - 1));
					
					HSSFCell hssfCell = hssfRow.createCell(col);
					hssfCell.setCellStyle(cellStyle);
					
					Object val = colData.get(CELL_TEXT);
					if (val instanceof Double)
						hssfCell.setCellValue((Double)val);
					else if (val instanceof Integer)
						hssfCell.setCellValue((Integer)val);
					else if (val instanceof Date)
						hssfCell.setCellValue((Date)val);
					else if (val instanceof Boolean)
						hssfCell.setCellValue((Boolean)val);
					else {
						String text = processText(val.toString());
						
						hssfCell.setCellValue(new HSSFRichTextString(text));
					}
					
			    	for (int i = rowSpan - 1; i >= 0; i--)
			    		for (int j = colSpan - 1; j >= 0; j--)
			    			placeholder[row + i][col + j] = cellStyle;
			    	
			    	orgCol = col + 1;
			    	col = col + colSpan;
					for (;orgCol < col; orgCol++) {
						HSSFCell cell = hssfRow.createCell(orgCol);
						cell.setCellStyle(placeholder[row][orgCol]);
					}
				}
				
				for (;col < cols; col++) {
					HSSFCell cell = hssfRow.createCell(col);
					cell.setCellStyle(placeholder[row][col] == null ? getDefaultCellStyle(hssfWorkbook) : placeholder[row][col]);
					
				}
				cellnum = 0;
				row++;
			}
		}
		
		hssfWorkbook.write(result);
		return name;
	}
	
	public Map<String, Object> readExcelData(InputStream is, String readConfig) {
		//readConfig 格式如："1.1 1.3 3.1 ...17.4 "
		HSSFWorkbook hssfWorkbook;
		try {
			hssfWorkbook = new HSSFWorkbook(is);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Excel输入流有误，请检查选择的Excel文件!");
		}
		
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		
		int[][] placeholder = createPlaceHolder(hssfSheet);
		int rows = placeholder.length;
		int cols = placeholder[0].length;
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		int currRow = -1;
		HSSFRow hssfRow = null;
		
		String[] tmp = readConfig.split(" ");
		for (String s : tmp) {
			String key = s.trim();
			String[] pos = key.trim().split("[.]");
			int row = Integer.parseInt(pos[0]);
			int col = Integer.parseInt(pos[1]);

			if (row >= rows)
				continue;
			
			int index = 0;
			for (int cnt = col; index < cols; index++) {
				if (placeholder[row][index] == 0) {
					cnt--;
					
					if (cnt < 0)
						break;
				}
			}
			
			if (index >= cols)
				continue;
				
			if (row != currRow)
				hssfRow = hssfSheet.getRow(row);

			HSSFCell hssfCell = hssfRow.getCell(index);

			Object val = null;
			switch (hssfCell.getCellType()) {
               // 如果当前Cell的Type为NUMERIC
               case HSSFCell.CELL_TYPE_NUMERIC: 
               case HSSFCell.CELL_TYPE_FORMULA: 
                  if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
                     Date date = hssfCell.getDateCellValue();
                     
                     val = sdf.format(date);
                  } else
                	  val = hssfCell.getNumericCellValue();
                  
                  break;
               case HSSFCell.CELL_TYPE_BOOLEAN:
                  val = hssfCell.getBooleanCellValue();
                   
                  break;
               case HSSFCell.CELL_TYPE_STRING:
            	  HSSFRichTextString text = hssfCell.getRichStringCellValue();
                  val = text.getString();
                  
                  break;
               default:
            	   val = "";
			}

			result.put(key, val);
			//System.out.println(row + "-" + col + ":" + val);
		}
		
		return result;
	}
	
	private int[][] createPlaceHolder(HSSFSheet hssfSheet) {
		int rows = hssfSheet.getLastRowNum() + 1;
		int cols = 0;
		
		//计算最大列数量
		for (int row = 0; row < rows; row++) {
			HSSFRow hssfRow = hssfSheet.getRow(row);
			if (hssfRow.getLastCellNum() > cols)
				cols = hssfRow.getLastCellNum();
		}
		
		int[][] placeholder = new int[rows][cols];
		
		for (int i = 0, length = hssfSheet.getNumMergedRegions(); i < length; i++) {
			CellRangeAddress range = hssfSheet.getMergedRegion(i);
			
			for (int beginRow = range.getFirstRow(), endRow = range.getLastRow(); beginRow <= endRow; beginRow++)
				for (int beginCol = range.getFirstColumn(), endCol = range.getLastColumn(); beginCol <= endCol; beginCol++)
					placeholder[beginRow][beginCol] = 1;
			
			placeholder[range.getFirstRow()][range.getFirstColumn()] = 0;
		}
		
		return placeholder;
	}
	
	private String processText(String text) {
		text = text.replaceAll("<BR>", "\n");
		text = text.replaceAll("<br>", "\n");
		text = text.replaceAll("<[/]*[^>]*>","");
		text = text.replaceAll("&nbsp;", " ");
		
		return text;
	}
	
    public static void main(String[] args) throws IOException {
    	//String s = "{'widths':[0],'data':[{'height':34,'cell':[{'text':'我的报表','range':'1.6','clazz':'title'}]},{'height':20,'cell':[{'text':'录入人：','clazz':'top_label'},{'text':'zouxuem','range':'1.2','clazz':'top_text'},{'text':'录入日期：','clazz':'top_label'},{'text':'2009-03-15','range':'1.2','clazz':'top_text'}]},{'height':28,'cell':[{'text':'与上年度比较能耗指标下降的分析说明','range':'1.6','clazz':'top_title'}]},{'height':28,'cell':[{'text':'单据号：','clazz':'label_right'},{'text':'666','range':'1.2','clazz':'text'},{'text':'购买客户：','clazz':'label_right'},{'text':'customer1','range':'1.2','clazz':'text'}]},{'height':28,'cell':[{'text':'单据说明：','clazz':'label_right'},{'text':'','range':'1.5','clazz':'text'}]},{'height':28,'cell':[{'text':'商品','clazz':'header'},{'text':'单位','clazz':'header'},{'text':'单价','clazz':'header'},{'text':'数量','clazz':'header'},{'text':'金额','clazz':'header'},{'text':'操作','clazz':'header'}]},{'height':32,'cell':[{'text':'苹果','clazz':'text'},{'text':'qk','range':'3.1','clazz':'text'},{'text':'22.34','clazz':'text'},{'text':'66','clazz':'text'},{'text':'1474.4','clazz':'text'},{'text':'','clazz':'text'}]},{'height':32,'cell':[{'text':'香蕉','clazz':'text'},{'text':'12.34','clazz':'text'},{'text':'10','clazz':'text'},{'text':'123.4','clazz':'text'},{'text':'','clazz':'text'}]},{'height':32,'cell':[{'text':'桔子','clazz':'text'},{'text':'24.34','clazz':'text'},{'text':'100','clazz':'text'},{'text':'2434.0','clazz':'text'},{'text':'','clazz':'text'}]},{'height':32,'cell':[{'text':'油菜','clazz':'text'},{'text':'ton','range':'2.1','clazz':'text'},{'text':'62.34','clazz':'text'},{'text':'5','clazz':'text'},{'text':'311.7','clazz':'text'},{'text':'','clazz':'text'}]},{'height':32,'cell':[{'text':'黄瓜','clazz':'text'},{'text':'42.34','clazz':'text'},{'text':'5','clazz':'text'},{'text':'211.7','clazz':'text'},{'text':'','clazz':'text'}]},{'height':32,'cell':[{'text':'','clazz':'text'},{'text':'','clazz':'text'},{'text':'','clazz':'text'},{'text':'','clazz':'text'},{'text':'4555.2','clazz':'text'},{'text':'','clazz':'text'}]},{'height':28,'cell':[{'text':'与上年度比较能耗指标上升的分析说明','range':'1.6','clazz':'top_title'}]},{'height':20,'cell':[{'text':'录入人：','clazz':'top_label'},{'text':'zouxuem','range':'1.2','clazz':'top_text'},{'text':'录入日期：','clazz':'top_label'},{'text':'2009-03-15','range':'1.2','clazz':'top_text'}]},{'height':28,'cell':[{'text':'单据号：','clazz':'label_right'},{'text':'yyy','range':'1.2','clazz':'text'},{'text':'购买客户：','clazz':'label_right'},{'text':'customer1','range':'1.2','clazz':'text'}]},{'height':28,'cell':[{'text':'单据说明：','clazz':'label_right'},{'text':'','range':'1.5','clazz':'text'}]},{'height':28,'cell':[{'text':'商品','clazz':'header'},{'text':'单位','clazz':'header'},{'text':'单价','clazz':'header'},{'text':'数量','clazz':'header'},{'text':'金额','clazz':'header'},{'text':'操作','clazz':'header'}]},{'height':20,'cell':[{'text':'总金额：','clazz':'bottom_label_right'},{'text':'9110','clazz':'bottom_text'},{'text':'','clazz':'bottom_label_right'},{'text':'审批人：','clazz':'bottom_label_right'},{'text':'liupeng','clazz':'bottom_text'},{'text':'','clazz':'bottom_label_right'}]},{'height':20,'cell':[{'text':'','range':'1.6','clazz':'tip'}]},{'height':20,'cell':[{'text':'说明：1．从填报年度开始的三年为一个规划期。（如2008年度能源利用状况报告，则规划期为2008—2010三年。）','range':'1.6','clazz':'tip'}],'style':'height: 20px;'},{'height':37,'cell':[{'text':'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2．项目类别：燃煤工业锅炉(窑炉)改造／发电（供热）机组／区域热电联产／余热余压利用／节约和替代石油／电机系统节能／能量<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系统优化／建筑节能／绿色照明。','range':'1.6','clazz':'tip'}],'style':'height: 37px;'},{'height':16,'cell':[{'text':'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3．项目年节能量达到3000吨标准煤以上或投资金额1000万以上的节能技改项目均应填报。','range':'1.6','clazz':'tip'}],'style':'height: 16px;'}],'clazz':'report','style':'width: 798px;','name':'我的报表'}";
    	String s = "{'widths':[0],'data':[{'height':22,'cell':[{'text':'2011-09到2011-11仓库及物流商任务单总的完成情况','range':'1.12','clazz':'desc','style':'TEXT-ALIGN: center'}]},{'height':43,'cell':[{'text':'处理方','range':'2.1','clazz':'desc','style':'TEXT-ALIGN: center'},{'text':'当前状态','range':'2.1','clazz':'desc','style':'TEXT-ALIGN: center'},{'text':'2011-09月份','range':'1.2','clazz':'desc','style':'TEXT-ALIGN: center'},{'text':'2011-09月份 汇总','range':'2.1','clazz':'desc','style':'TEXT-ALIGN: center'},{'text':'2011-10月份','range':'1.2','clazz':'desc','style':'TEXT-ALIGN: center'},{'text':'2011-10月份 汇总','range':'2.1','clazz':'desc','style':'TEXT-ALIGN: center'},{'text':'2011-11月份','range':'1.2','clazz':'desc','style':'TEXT-ALIGN: center'},{'text':'2011-11月份 汇总','range':'2.1','clazz':'desc','style':'TEXT-ALIGN: center'},{'text':'总计','range':'2.1','clazz':'desc','style':'TEXT-ALIGN: center'}]},{'height':22,'cell':[{'text':'超时','clazz':'desc','style':'TEXT-ALIGN: center'},{'text':'未超时','clazz':'desc','style':'TEXT-ALIGN: center'},{'text':'超时','clazz':'desc','style':'TEXT-ALIGN: center'},{'text':'未超时','clazz':'desc','style':'TEXT-ALIGN: center'},{'text':'超时','clazz':'desc','style':'TEXT-ALIGN: center'},{'text':'未超时','clazz':'desc','style':'TEXT-ALIGN: center'}]},{'height':120,'cell':[{'text':'无处理方','range':'4.1','style':'TEXT-ALIGN: center'},{'text':'已发布待处理','style':'TEXT-ALIGN: center'},{'text':'2'},{'text':'0'},{'text':'2'},{'text':'5'},{'text':'2'},{'text':'7'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'9'}]},{'height':21,'cell':[{'text':'草稿','style':'TEXT-ALIGN: center'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'2'},{'text':'0'},{'text':'2'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'2'}]},{'height':21,'cell':[{'text':'处理中','style':'TEXT-ALIGN: center'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'2'},{'text':'0'},{'text':'2'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'2'}]},{'height':38,'cell':[{'text':'通过确认','style':'TEXT-ALIGN: center'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'6'},{'text':'6'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'6'}]},{'height':137,'cell':[{'text':'江门','range':'4.1','style':'TEXT-ALIGN: center'},{'text':'已发布待处理','style':'TEXT-ALIGN: center'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'3'},{'text':'1'},{'text':'4'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'4'}]},{'height':38,'cell':[{'text':'处理人已提交','style':'TEXT-ALIGN: center'},{'text':'1'},{'text':'0'},{'text':'1'},{'text':'1'},{'text':'6'},{'text':'7'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'8'}]},{'height':21,'cell':[{'text':'处理中','style':'TEXT-ALIGN: center'},{'text':'1'},{'text':'0'},{'text':'1'},{'text':'10'},{'text':'0'},{'text':'10'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'11'}]},{'height':38,'cell':[{'text':'通过确认','style':'TEXT-ALIGN: center'},{'text':'0'},{'text':'1'},{'text':'1'},{'text':'2'},{'text':'7'},{'text':'9'},{'text':'0'},{'text':'1'},{'text':'1'},{'text':'11'}]},{'height':115,'cell':[{'text':'埃瑞普','range':'3.1','style':'TEXT-ALIGN: center'},{'text':'处理人已提交','style':'TEXT-ALIGN: center'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'1'},{'text':'0'},{'text':'1'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'1'}]},{'height':38,'cell':[{'text':'已发布待处理','style':'TEXT-ALIGN: center'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'1'},{'text':'0'},{'text':'1'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'1'}]},{'height':38,'cell':[{'text':'通过确认','style':'TEXT-ALIGN: center'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'2'},{'text':'2'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'2'}]},{'height':77,'cell':[{'text':'江门测试仓库','range':'2.1','style':'TEXT-ALIGN: center'},{'text':'已发布待处理','style':'TEXT-ALIGN: center'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'1'},{'text':'0'},{'text':'1'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'1'}]},{'height':38,'cell':[{'text':'通过确认','style':'TEXT-ALIGN: center'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'1'},{'text':'1'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'1'}]}],'style':'TEXT-ALIGN: center','name':'任务超时统计报表'}";
    	//String s ="{'widths':[],'data':[{'height':22,'cell':[{'text':'2011-09到2011-11仓库及物流商任务单总的完成情况','range':'1.12','clazz':'desc'}]},{'height':71,'cell':[{'text':'处理方','range':'2.1','clazz':'desc'},{'text':'当前状态','range':'2.1','clazz':'desc'},{'text':'2011-09月份','range':'1.2','clazz':'desc'},{'text':'2011-09月份 汇总','range':'2.1','clazz':'desc'},{'text':'2011-10月份','range':'1.2','clazz':'desc'},{'text':'2011-10月份 汇总','range':'2.1','clazz':'desc'},{'text':'2011-11月份','range':'1.2','clazz':'desc'},{'text':'2011-11月份 汇总','range':'2.1','clazz':'desc'},{'text':'总计','range':'2.1','clazz':'desc'}]},{'height':51,'cell':[{'text':'超时','clazz':'desc'},{'text':'未超时','clazz':'desc'},{'text':'超时','clazz':'desc'},{'text':'未超时','clazz':'desc'},{'text':'超时','clazz':'desc'},{'text':'未超时','clazz':'desc'}]},{'height':103,'cell':[{'text':'无处理方','range':'4.1'},{'text':'已发布待处理'},{'text':'2'},{'text':'0'},{'text':'2'},{'text':'5'},{'text':'2'},{'text':'7'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'9'}]},{'height':21,'cell':[{'text':'草稿'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'2'},{'text':'0'},{'text':'2'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'2'}]},{'height':21,'cell':[{'text':'处理中'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'2'},{'text':'0'},{'text':'2'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'2'}]},{'height':21,'cell':[{'text':'通过确认'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'6'},{'text':'6'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'6'}]},{'height':118,'cell':[{'text':'江门','range':'4.1'},{'text':'已发布待处理'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'3'},{'text':'1'},{'text':'4'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'4'}]},{'height':36,'cell':[{'text':'处理人已提交'},{'text':'1'},{'text':'0'},{'text':'1'},{'text':'1'},{'text':'6'},{'text':'7'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'8'}]},{'height':21,'cell':[{'text':'处理中'},{'text':'1'},{'text':'0'},{'text':'1'},{'text':'10'},{'text':'0'},{'text':'10'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'11'}]},{'height':21,'cell':[{'text':'通过确认'},{'text':'0'},{'text':'1'},{'text':'1'},{'text':'2'},{'text':'7'},{'text':'9'},{'text':'0'},{'text':'1'},{'text':'1'},{'text':'11'}]},{'height':96,'cell':[{'text':'埃瑞普','range':'3.1'},{'text':'处理人已提交'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'1'},{'text':'0'},{'text':'1'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'1'}]},{'height':36,'cell':[{'text':'已发布待处理'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'1'},{'text':'0'},{'text':'1'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'1'}]},{'height':21,'cell':[{'text':'通过确认'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'2'},{'text':'2'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'2'}]},{'height':78,'cell':[{'text':'江门测试仓库','range':'2.1'},{'text':'已发布待处理'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'1'},{'text':'0'},{'text':'1'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'1'}]},{'height':29,'cell':[{'text':'通过确认'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'1'},{'text':'1'},{'text':'0'},{'text':'0'},{'text':'0'},{'text':'1'}]}],'name':'任务超时统计报表'}";
    	JSONObject jsonObj = JSONObject.fromObject(s);

    	FileOutputStream fo = new FileOutputStream("./example.xls");
    	
    	ExcelOperate excelOperate = new ExcelOperate();
    	excelOperate.buildExcel(jsonObj, fo);
    	fo.close();
    	
    	System.out.println("export ok!!!");

//    	s = "1.1 1.3 3.1 3.3 4.1 6.0 6.1 6.2 6.3 6.4 7.0 7.1 7.2 7.3 8.0 8.1 8.2 8.3 9.0 9.1 9.2 9.3 9.4 10.0 10.1 10.2 10.3 11.0 11.1 11.2 11.3 11.4 13.1 13.3 14.1 14.3 15.1 17.1 17.
//    	
//    	FileInputStream fi = new FileInputStream("./example.xls");
//    	
//    	Map<String, Object> result = excelOperate.readExcelData(fi, s);
//    	fi.close();
//
//    	jsonObj = JSONObject.fromObject(result);
//    	System.out.println(jsonObj);
//    	
//    	System.out.println("import ok!!!");
    }
}

