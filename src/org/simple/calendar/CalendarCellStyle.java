package org.simple.calendar;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

/**
 * 日历单元格样式
 * 
 * @author huanglei
 * @email code.huanglei at gmail.com
 * @date Sep 18, 2012
 */
public class CalendarCellStyle {
	/**
	 * 单元格背景图片
	 */
	private Drawable drawable;

	/**
	 * 非本月单元格背景图片
	 */
	private Drawable drawableX;

	/**
	 * 单元格背景颜色
	 */
	private ColorStateList backgroundColor = new ColorStateList(new int[][] {
			Calendars.SELECTED_STATE_SET, Calendars.EMPTY_STATE_SET },
			new int[] { 0xFFE5DFD5, Color.TRANSPARENT });

	/**
	 * 非本月单元格背景颜色
	 */
	private int backgroundColorX = Color.TRANSPARENT;

	/**
	 * 字体大小
	 */
	private int textSize = 20;

	/**
	 * 字体颜色
	 */
	private ColorStateList textColor = new ColorStateList(new int[][] {
			Calendars.SELECTED_STATE_SET, Calendars.EMPTY_STATE_SET },
			new int[] { Color.WHITE, 0xFF3C3B38 });

	/**
	 * 非本月字体颜色
	 */
	private int textColorX = 0xFFB4B4B4;

	/**
	 * 周末文字颜色
	 */
	private int weekEndTextColor = 0xFFB24C49;

	/**
	 * 非本月周末文字颜色
	 */
	private int weekEndTextColorX = 0xFFE5B4B2;

	/**
	 * 边框宽度
	 */
	private float lineSize = .9f;

	/**
	 * 边框颜色
	 */
	private int lineColor = 0xFFA69D95;

	/**
	 * 单元格间距
	 */
	private int space = 0;

	/**
	 * 宽度
	 */
	private float width = 0;

	/**
	 * 高度
	 */
	private float height = 0;

	/**
	 * 内填充
	 */
	private int paddingTop = 5;
	private int paddingRight = 5;
	private int paddingBottom = 5;
	private int paddingLeft = 5;

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
	public ColorStateList getTextColor() {
		return textColor;
	}

	/**
	 * @param textColor
	 *            the textColor to set
	 */
	public void setTextColor(ColorStateList textColor) {
		this.textColor = textColor;
	}

	/**
	 * @return the lineSize
	 */
	public float getLineSize() {
		return lineSize;
	}

	/**
	 * @param lineSize
	 *            the lineSize to set
	 */
	public void setLineSize(float lineSize) {
		this.lineSize = lineSize;
	}

	/**
	 * @return the lineColor
	 */
	public int getLineColor() {
		return lineColor;
	}

	/**
	 * @param lineColor
	 *            the lineColor to set
	 */
	public void setLineColor(int lineColor) {
		this.lineColor = lineColor;
	}

	/**
	 * @return the space
	 */
	public int getSpace() {
		return space;
	}

	/**
	 * @param space
	 *            the space to set
	 */
	public void setSpace(int space) {
		this.space = space;
	}

	/**
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(float width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	/**
	 * @return the paddingTop
	 */
	public int getPaddingTop() {
		return paddingTop;
	}

	/**
	 * @param paddingTop
	 *            the paddingTop to set
	 */
	public void setPaddingTop(int paddingTop) {
		this.paddingTop = paddingTop;
	}

	/**
	 * @return the paddingRight
	 */
	public int getPaddingRight() {
		return paddingRight;
	}

	/**
	 * @param paddingRight
	 *            the paddingRight to set
	 */
	public void setPaddingRight(int paddingRight) {
		this.paddingRight = paddingRight;
	}

	/**
	 * @return the paddingBottom
	 */
	public int getPaddingBottom() {
		return paddingBottom;
	}

	/**
	 * @param paddingBottom
	 *            the paddingBottom to set
	 */
	public void setPaddingBottom(int paddingBottom) {
		this.paddingBottom = paddingBottom;
	}

	/**
	 * @return the paddingLeft
	 */
	public int getPaddingLeft() {
		return paddingLeft;
	}

	/**
	 * @param paddingLeft
	 *            the paddingLeft to set
	 */
	public void setPaddingLeft(int paddingLeft) {
		this.paddingLeft = paddingLeft;
	}

	/**
	 * 
	 * @param index
	 */
	public void setBounds(int index) {

	}

	/**
	 * @param drawable
	 *            the drawable to set
	 */
	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}

	/**
	 * @return the drawable
	 */
	public Drawable getDrawable() {
		return drawable;
	}

	/**
	 * @return the backgroundColor
	 */
	public ColorStateList getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param backgroundColor
	 *            the backgroundColor to set
	 */
	public void setBackgroundColor(ColorStateList backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @return the drawableX
	 */
	public Drawable getDrawableX() {
		return drawableX;
	}

	/**
	 * @param drawableX
	 *            the drawableX to set
	 */
	public void setDrawableX(Drawable drawableX) {
		this.drawableX = drawableX;
	}

	/**
	 * @return the backgroundColorX
	 */
	public int getBackgroundColorX() {
		return backgroundColorX;
	}

	/**
	 * @param backgroundColorX
	 *            the backgroundColorX to set
	 */
	public void setBackgroundColorX(int backgroundColorX) {
		this.backgroundColorX = backgroundColorX;
	}

	/**
	 * @return the textColorX
	 */
	public int getTextColorX() {
		return textColorX;
	}

	/**
	 * @param textColorX
	 *            the textColorX to set
	 */
	public void setTextColorX(int textColorX) {
		this.textColorX = textColorX;
	}

	/**
	 * @return the weekEndTextColor
	 */
	public int getWeekEndTextColor() {
		return weekEndTextColor;
	}

	/**
	 * @param weekEndTextColor
	 *            the weekEndTextColor to set
	 */
	public void setWeekEndTextColor(int weekEndTextColor) {
		this.weekEndTextColor = weekEndTextColor;
	}

	/**
	 * @return the weekEndTextColorX
	 */
	public int getWeekEndTextColorX() {
		return weekEndTextColorX;
	}

	/**
	 * @param weekEndTextColorX
	 *            the weekEndTextColorX to set
	 */
	public void setWeekEndTextColorX(int weekEndTextColorX) {
		this.weekEndTextColorX = weekEndTextColorX;
	}

	/**
	 * 当前状态字体颜色
	 * 
	 * @param state
	 * @return
	 */
	public int getCurrentTextColor(int[] state) {
		return textColor.getColorForState(state, 0);
	}

	/**
	 * 当前状态单元格颜色
	 * 
	 * @param state
	 * @return
	 */
	public int getCurrentBackgroundColor(int[] state) {
		return backgroundColor.getColorForState(state, 0);
	}

	/**
	 * 当前状态单元格背景
	 * 
	 * @param state
	 * @return
	 */
	public Drawable getCurrentDrawable(int[] state) {
		if (drawable == null) {
			return null;
		}
		drawable.setState(state);
		return drawable.getCurrent();
	}

}