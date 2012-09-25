package org.simple.calendar;

import java.util.Arrays;
import java.util.Calendar;

import org.simple.calendar.Calendars.Week;

import android.graphics.RectF;

/**
 * 日历单元格
 * 
 * @author huanglei
 * @email code.huanglei at gmail.com
 * @date Sep 18, 2012
 */
public class CalendarCell {

	/**
	 * 日历中的索引
	 */
	private int index;
	/**
	 * 年
	 */
	private int year;
	/**
	 * 月
	 */
	private int month;
	/**
	 * 日
	 */
	private int day;
	/**
	 * 是否今天
	 */
	private boolean isToday = false;
	/**
	 * 是否下个月
	 */
	private boolean nextMonth = false;
	/**
	 * 是否上个月
	 */
	private boolean previousMonth = false;
	/**
	 * 单元格绘制的坐标
	 */
	private RectF bounds = new RectF();
	/**
	 * 单元格点击状态
	 */
	private int[] state = {};

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	/* package */void setIndex(int index) {
		this.index = index;
	}

	/**
	 * 
	 * @return
	 */
	public final RectF getBounds() {
		return bounds;
	}

	/**
	 * 
	 * @param bounds
	 */
	/* package */void setBounds(RectF bounds) {
		this.bounds = bounds;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	/* package */void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param day
	 *            the day to set
	 */
	/* package */void setDay(int day) {
		this.day = day;
	}

	/**
	 * @return the isToday
	 */
	public boolean isToday() {
		return isToday;
	}

	/**
	 * @param isToday
	 *            the isToday to set
	 */
	/* package */void setToday(boolean isToday) {
		this.isToday = isToday;
	}

	/**
	 * @return the nextMonth
	 */
	public boolean isNextMonth() {
		return nextMonth;
	}

	/**
	 * @param nextMonth
	 *            the nextMonth to set
	 */
	/* package */void setNextMonth(boolean nextMonth) {
		this.nextMonth = nextMonth;
	}

	/**
	 * @return the previousMonth
	 */
	public boolean isPreviousMonth() {
		return previousMonth;
	}

	/**
	 * @param previousMonth
	 *            the previousMonth to set
	 */
	/* package */void setPreviousMonth(boolean previousMonth) {
		this.previousMonth = previousMonth;
	}

	/**
	 * 失去焦点
	 */
	/* package */void unSelected() {
		state = Calendars.EMPTY_STATE_SET;
	}

	/**
	 * 选中状态
	 */
	/* package */void selected() {
		state = Calendars.SELECTED_STATE_SET;
	}

	/**
	 * @return the state
	 */
	public int[] getState() {
		return state;
	}

	/**
	 * 日历
	 * 
	 * @return
	 */
	public Calendar getCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return calendar;
	}

	/**
	 * 时间戳
	 * 
	 * @return
	 */
	public long getTimestamp() {
		return getCalendar().getTimeInMillis();
	}

	/**
	 * 返回星期
	 * 
	 * @return
	 */
	public Week getWeek() {
		int dayOfWeek = getCalendar().get(Calendar.DAY_OF_WEEK);
		return Week.get(dayOfWeek);
	}

	@Override
	public String toString() {
		return "CalendarCell [index=" + index + ", year=" + year + ", month="
				+ month + ", day=" + day + ", isToday=" + isToday
				+ ", nextMonth=" + nextMonth + ", previousMonth="
				+ previousMonth + ", bounds=" + bounds + ", state="
				+ Arrays.toString(state) + "]";
	}

}