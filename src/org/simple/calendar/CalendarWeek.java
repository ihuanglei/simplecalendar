package org.simple.calendar;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

/**
 * 日历星期
 * 
 * @author huanglei
 * @email code.huanglei at gmail.com
 * @date Sep 18, 2012
 */
public class CalendarWeek {

	/**
	 * 单元格背景图片
	 */
	private Drawable drawable;

	/**
	 * 单元格背景颜色
	 */
	private int backgroundColor = Color.TRANSPARENT;

	/**
	 * 字体大小
	 */
	private int textSize = 14;

	/**
	 * 文字颜色
	 */
	private int textColor = 0xFF57513D;

	/**
	 * 高度
	 */
	private int height = 0;

	/**
	 * 星期
	 */
	private String[] weeks = Calendars.WEEKS;

	/**
	 * @return the weeks
	 */
	public String[] getWeeks() {
		return weeks;
	}

	/**
	 * @param weeks
	 *            the weeks to set
	 */
	public void setWeeks(String weekStr) {
		if (weekStr != null) {
			int i = 0;
			for (String week : weekStr.split(",")) {
				weeks[i++] = week;
			}
		}
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the drawable
	 */
	public Drawable getDrawable() {
		return drawable;
	}

	/**
	 * @param drawable
	 *            the drawable to set
	 */
	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}

	/**
	 * @return the backgroundColor
	 */
	public int getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param backgroundColor
	 *            the backgroundColor to set
	 */
	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @return the textSize
	 */
	public int getTextSize() {
		return textSize;
	}

	/**
	 * @param textSize
	 *            the textSize to set
	 */
	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}

	/**
	 * @return the textColor
	 */
	public int getTextColor() {
		return textColor;
	}

	/**
	 * @param textColor
	 *            the textColor to set
	 */
	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}

}