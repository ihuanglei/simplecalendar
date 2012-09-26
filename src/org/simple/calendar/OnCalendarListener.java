package org.simple.calendar;

/**
 * 日历监听器
 * 
 * @author huanglei
 * @email code.huanglei at gmail.com
 * @date Sep 18, 2012
 */
public interface OnCalendarListener {

	/**
	 * 选择单元格事件
	 * 
	 * @param cell
	 */
	public void onCellSelected(final CalendarCell cell);

	/**
	 * 长选择单元格事件
	 * 
	 * @param cell
	 */
	public void onCellLongSelected(final CalendarCell cell);

}