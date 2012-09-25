/**
 * 
 */
package org.simple.calendar;

import android.graphics.Bitmap;

/**
 * @author huanglei
 * @email code.huanglei at gmail.com
 * @date Sep 20, 2012
 */
public class Calendars {

	/**
	 * 无状态
	 */
	public static final int[] EMPTY_STATE_SET = {};

	/**
	 * 选择状态
	 */
	public static final int[] SELECTED_STATE_SET = { android.R.attr.state_selected };

	/**
	 * 星期
	 */
	public static final String[] WEEKS = { "Sun", "Mon", "Tues", "Wed", "Thur",
			"Fri", "Sat" };

	/**
	 * 星期
	 */
	public enum Week {
		SUNDAY(1), MONDAY(2), TUESDAY(3), WEDNESDAY(4), THURSDAY(5), FRIDAY(6), SATURDAY(
				7);
		private int value;

		private Week(int value) {
			this.value = value;
		}

		public static Week get(int value) {
			for (Week week : Week.values()) {
				if (week.value == value) {
					return week;
				}
			}
			return null;
		}
	};

	/**
	 * 触摸坐标偏移(滑动效果)
	 */
	public static final int FLING_MIN_DISTANCE = 50;

	/**
	 * 单元格行数量
	 */
	public static final int CELL_ROW_COUNT = 6;

	/**
	 * 单元格列数量
	 */
	public static final int CELL_COL_COUNT = 7;

	/**
	 * 对应指定年对应月的天数，包含闰年的计算
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	private static final int[] DAYS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
			30, 31 };

	public static int getDays(int year, int month) {
		if (month != 1) {
			return DAYS[month];
		}
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			return 29;
		}
		return DAYS[month];
	}

	/**
	 * 动画因子
	 */
	public static final float ANIMATION_STEP_FACTOR = 0.5f;

	public static void destoryBitmap(Bitmap bitmap) {
		if (bitmap != null && !bitmap.isRecycled()) {
			bitmap.recycle();
		}
	}

}
