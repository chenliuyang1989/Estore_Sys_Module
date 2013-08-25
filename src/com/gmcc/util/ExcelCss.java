package com.gmcc.util;

/**
 * 
 */

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author xiangwenfei
 *
 * 存放Excel支持的Style风格
 */
public class ExcelCss implements Cloneable {
	private String fontName = "宋体";
	
	private short fontHeightInPoints = 9;
	
	private short boldweight = HSSFFont.BOLDWEIGHT_NORMAL;
	
	private boolean italic = false;
	
	private short color = HSSFFont.COLOR_NORMAL;
	
	private boolean strikeout = false;
	
	private byte underline = HSSFFont.U_NONE;
	
	private short borderTop = 0;
	
	private short borderBottom = 0;
	
	private short borderLeft = 0;
	
	private short borderRight = 0;
	
	private short topBorderColor = HSSFColor.BLACK.index;
	
	private short bottomBorderColor = HSSFColor.BLACK.index;
	
	private short leftBorderColor = HSSFColor.BLACK.index;
	
	private short rightBorderColor = HSSFColor.BLACK.index;
	
	private short backgroundColor = HSSFColor.WHITE.index;
	
	private short alignment = HSSFCellStyle.ALIGN_CENTER;
	
	private short verticalAlignment = HSSFCellStyle.VERTICAL_CENTER;
	
	private short indention = 0;
	
	private boolean wrapText = true;

	public ExcelCss(String fontName, short fontHeightInPoints, short boldweight,
			boolean italic) {
		this.fontName = fontName;
		this.fontHeightInPoints = fontHeightInPoints;
		this.boldweight = boldweight;
		this.italic = italic;
	}

	public ExcelCss(String fontName, short fontHeightInPoints, short boldweight,
			boolean italic, boolean strikeout, byte underline, short color,
			short backgroundColor) {
		this.fontName = fontName;
		this.fontHeightInPoints = fontHeightInPoints;
		this.boldweight = boldweight;
		this.italic = italic;
		this.color = color;
		this.strikeout = strikeout;
		this.underline = underline;
		this.backgroundColor = backgroundColor;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public short getFontHeightInPoints() {
		return fontHeightInPoints;
	}

	public void setFontHeightInPoints(short fontHeightInPoints) {
		this.fontHeightInPoints = fontHeightInPoints;
	}

	public short getBoldweight() {
		return boldweight;
	}

	public void setBoldweight(short boldweight) {
		this.boldweight = boldweight;
	}

	public boolean isItalic() {
		return italic;
	}

	public void setItalic(boolean italic) {
		this.italic = italic;
	}

	public short getColor() {
		return color;
	}

	public void setColor(short color) {
		this.color = color;
	}

	public boolean isStrikeout() {
		return strikeout;
	}

	public void setStrikeout(boolean strikeout) {
		this.strikeout = strikeout;
	}

	public byte getUnderline() {
		return underline;
	}

	public void setUnderline(byte underline) {
		this.underline = underline;
	}

	public short getBorderTop() {
		return borderTop;
	}

	public void setBorderTop(short borderTop) {
		this.borderTop = borderTop;
	}

	public short getBorderBottom() {
		return borderBottom;
	}

	public void setBorderBottom(short borderBottom) {
		this.borderBottom = borderBottom;
	}

	public short getBorderLeft() {
		return borderLeft;
	}

	public void setBorderLeft(short borderLeft) {
		this.borderLeft = borderLeft;
	}

	public short getBorderRight() {
		return borderRight;
	}

	public void setBorderRight(short borderRight) {
		this.borderRight = borderRight;
	}

	public short getTopBorderColor() {
		return topBorderColor;
	}

	public void setTopBorderColor(short topBorderColor) {
		this.topBorderColor = topBorderColor;
	}

	public short getBottomBorderColor() {
		return bottomBorderColor;
	}

	public void setBottomBorderColor(short bottomBorderColor) {
		this.bottomBorderColor = bottomBorderColor;
	}

	public short getLeftBorderColor() {
		return leftBorderColor;
	}

	public void setLeftBorderColor(short leftBorderColor) {
		this.leftBorderColor = leftBorderColor;
	}

	public short getRightBorderColor() {
		return rightBorderColor;
	}

	public void setRightBorderColor(short rightBorderColor) {
		this.rightBorderColor = rightBorderColor;
	}

	public short getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(short backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public short getAlignment() {
		return alignment;
	}

	public void setAlignment(short alignment) {
		this.alignment = alignment;
	}

	public short getVerticalAlignment() {
		return verticalAlignment;
	}

	public void setVerticalAlignment(short verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	public short getIndention() {
		return indention;
	}

	public void setIndention(short indention) {
		this.indention = indention;
	}

	public boolean isWrapText() {
		return wrapText;
	}

	public void setWrapText(boolean wrapText) {
		this.wrapText = wrapText;
	}

	public ExcelCss clone() throws CloneNotSupportedException {
		return (ExcelCss)super.clone();
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ExcelCss)) {
			return false;
		}
		ExcelCss rhs = (ExcelCss) object;
		return new EqualsBuilder().appendSuper(super.equals(object)).append(
				this.leftBorderColor, rhs.leftBorderColor).append(
				this.borderRight, rhs.borderRight).append(this.strikeout,
				rhs.strikeout).append(this.verticalAlignment,
				rhs.verticalAlignment).append(this.boldweight, rhs.boldweight)
				.append(this.bottomBorderColor, rhs.bottomBorderColor).append(
						this.alignment, rhs.alignment).append(this.borderLeft,
						rhs.borderLeft).append(this.borderTop, rhs.borderTop)
				.append(this.color, rhs.color).append(this.rightBorderColor,
						rhs.rightBorderColor).append(this.fontHeightInPoints,
						rhs.fontHeightInPoints).append(this.italic, rhs.italic)
				.append(this.borderBottom, rhs.borderBottom).append(
						this.underline, rhs.underline).append(
						this.topBorderColor, rhs.topBorderColor).append(
						this.backgroundColor, rhs.backgroundColor).append(
						this.indention, rhs.indention).append(
						this.wrapText, rhs.wrapText).append(this.fontName,
						rhs.fontName).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1357361335, 1045380061).appendSuper(
				super.hashCode()).append(this.leftBorderColor).append(
				this.borderRight).append(this.strikeout).append(
				this.verticalAlignment).append(this.boldweight).append(
				this.bottomBorderColor).append(this.alignment).append(
				this.borderLeft).append(this.borderTop).append(this.color)
				.append(this.rightBorderColor).append(this.fontHeightInPoints)
				.append(this.italic).append(this.borderBottom).append(
						this.underline).append(this.topBorderColor).append(
						this.backgroundColor).append(this.wrapText).append(
						this.fontName).append(this.indention).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("borderRight", this.borderRight).append(
						"fontHeightInPoints", this.fontHeightInPoints).append(
						"topBorderColor", this.topBorderColor).append(
						"alignment", this.alignment).append("borderBottom",
						this.borderBottom).append("rightBorderColor",
						this.rightBorderColor).append("strikeout",
						this.strikeout).append("bottomBorderColor",
						this.bottomBorderColor).append("borderLeft",
						this.borderLeft).append("backgroundColor",
						this.backgroundColor).append("fontName", this.fontName)
				.append("boldweight", this.boldweight).append("borderTop",
						this.borderTop).append("leftBorderColor",
						this.leftBorderColor).append("italic", this.italic)
				.append("verticalAlignment", this.verticalAlignment).append(
						"underline", this.underline)
				.append("color", this.color).append("wrapText", this.wrapText)
				.append("indention", this.indention).toString();
	}

}
