/**
 * 
 */
package org.simple.calendar;

import java.util.Calendar;

import org.simple.calendar.Calendars.Week;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

/**
 * 日历控件
 * 
 * @author huanglei
 * @email code.huanglei at gmail.com
 * @date Sep 18, 2012
 */
public class CalendarView extends View implements OnGestureListener {

	private static final String TAG = "SimpleCalendar";

	/**
	 * 画笔
	 */
	private Paint paint = new Paint();

	/**
	 * 星期
	 */
	private CalendarWeek calendarWeek = new CalendarWeek();

	/**
	 * 单元格样式
	 */
	private CalendarCellStyle calendarCellStyle = new CalendarCellStyle();

	/**
	 * 6*7单元格 一行7天，总共6行 42个单元格
	 */
	private CalendarCell[] calendarCells = new CalendarCell[Calendars.CELL_COL_COUNT
			* Calendars.CELL_ROW_COUNT];
	/**
	 * 当前选择的单元格索引
	 */
	private int selectIndex = -1;

	/**
	 * 触摸单元格监听器
	 */
	private OnCalendarListener calendarListener = null;

	/**
	 * 触摸手势
	 */
	private GestureDetector gestureDetector = null;

	/**
	 * 星期位图
	 */
	private Bitmap weeksBitmap = null;

	/**
	 * 日历位图
	 */
	private Bitmap cellsBitmap = null;

	/**
	 * 新日历位图，用于动画
	 */
	private Bitmap newCellsBitmap = null;

	/**
	 * 动画播放
	 */
	private boolean isAnimation = false;

	/**
	 * x坐标
	 */
	private float x = .0f;

	/**
	 * y坐标
	 */
	private float y = .0f;

	/**
	 * 动画速度
	 */
	private int ditect = 1;

	/**
	 * @param context
	 */
	public CalendarView(Context context) {
		this(context, null);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public CalendarView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public CalendarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray typedArray = getContext().obtainStyledAttributes(attrs,
				R.styleable.CalendarView, defStyle, 0);

		int n = typedArray.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = typedArray.getIndex(i);
			switch (attr) {
			/**
			 * 星期
			 */
			case R.styleable.CalendarView_weeks:
				calendarWeek.setWeeks(typedArray.getString(attr));
				break;
			case R.styleable.CalendarView_weeksHeight:
				calendarWeek.setHeight(typedArray.getDimensionPixelSize(attr,
						calendarWeek.getHeight()));
				break;
			case R.styleable.CalendarView_weeksTextSize:
				calendarWeek.setTextSize(typedArray.getDimensionPixelSize(attr,
						calendarWeek.getTextSize()));
				break;
			case R.styleable.CalendarView_weeksTextColor:
				calendarWeek.setTextColor(typedArray.getColor(attr,
						calendarWeek.getTextColor()));
				break;
			case R.styleable.CalendarView_weeksBackgroundColor:
				calendarWeek.setBackgroundColor(typedArray.getColor(attr,
						calendarWeek.getBackgroundColor()));
				break;
			case R.styleable.CalendarView_weeksDrawable:
				Drawable weeksDrawable = typedArray.getDrawable(attr);
				calendarWeek.setDrawable(weeksDrawable);
				break;
			/**
			 * 日历单元格
			 */
			case R.styleable.CalendarView_cellWidth:
				calendarCellStyle.setWidth(typedArray.getDimensionPixelSize(
						attr, (int) calendarCellStyle.getWidth()));
				break;
			case R.styleable.CalendarView_cellHeight:
				calendarCellStyle.setHeight(typedArray.getDimensionPixelSize(
						attr, (int) calendarCellStyle.getHeight()));
				break;
			case R.styleable.CalendarView_cellSpace:
				calendarCellStyle.setSpace(typedArray.getDimensionPixelSize(
						attr, calendarCellStyle.getSpace()));
				break;
			case R.styleable.CalendarView_cellDrawable:
				Drawable cellDrawable = typedArray.getDrawable(attr);
				calendarCellStyle.setDrawable(cellDrawable);
				break;
			case R.styleable.CalendarView_cellDrawableX:
				Drawable cellDrawableX = typedArray.getDrawable(attr);
				calendarCellStyle.setDrawableX(cellDrawableX);
				break;
			case R.styleable.CalendarView_cellBackgroundColor:
				ColorStateList cellBackgroundColor = typedArray
						.getColorStateList(attr);
				calendarCellStyle
						.setBackgroundColor(cellBackgroundColor != null ? cellBackgroundColor
								: calendarCellStyle.getBackgroundColor());
				break;
			case R.styleable.CalendarView_cellBackgroundColorX:
				calendarCellStyle.setBackgroundColorX(typedArray.getColor(attr,
						calendarCellStyle.getBackgroundColorX()));
				break;
			case R.styleable.CalendarView_cellTextSize:
				calendarCellStyle.setTextSize(typedArray.getDimensionPixelSize(
						attr, calendarCellStyle.getTextSize()));
				break;
			case R.styleable.CalendarView_cellTextColor:
				ColorStateList cellTextColor = typedArray
						.getColorStateList(attr);
				calendarCellStyle
						.setTextColor(cellTextColor != null ? cellTextColor
								: calendarCellStyle.getTextColor());
				break;
			case R.styleable.CalendarView_cellTextColorX:
				calendarCellStyle.setTextColorX(typedArray.getColor(attr,
						calendarCellStyle.getTextColorX()));
				break;
			case R.styleable.CalendarView_cellWeekEndTextColor:
				calendarCellStyle.setWeekEndTextColor(typedArray.getColor(attr,
						calendarCellStyle.getWeekEndTextColor()));
				break;
			case R.styleable.CalendarView_cellWeekEndTextColorX:
				calendarCellStyle.setWeekEndTextColorX(typedArray.getColor(
						attr, calendarCellStyle.getWeekEndTextColorX()));
				break;
			case R.styleable.CalendarView_cellLineSize:
				calendarCellStyle.setLineSize(typedArray.getFloat(attr,
						calendarCellStyle.getLineSize()));
				break;
			case R.styleable.CalendarView_cellLineColor:
				calendarCellStyle.setLineColor(typedArray.getColor(attr,
						calendarCellStyle.getLineColor()));
				break;
			case R.styleable.CalendarView_cellPaddingLeft:
				calendarCellStyle.setPaddingLeft(typedArray
						.getDimensionPixelSize(attr,
								calendarCellStyle.getPaddingLeft()));
				break;
			case R.styleable.CalendarView_cellPaddingTop:
				calendarCellStyle.setPaddingTop(typedArray
						.getDimensionPixelSize(attr,
								calendarCellStyle.getPaddingTop()));
				break;
			case R.styleable.CalendarView_cellPaddingRight:
				calendarCellStyle.setPaddingRight(typedArray
						.getDimensionPixelSize(attr,
								calendarCellStyle.getPaddingRight()));
				break;
			case R.styleable.CalendarView_cellPaddingBottom:
				calendarCellStyle.setPaddingBottom(typedArray
						.getDimensionPixelSize(attr,
								calendarCellStyle.getPaddingBottom()));
				break;
			default:
				break;
			}
		}
		typedArray.recycle();

		paint.setAntiAlias(true);

		gestureDetector = new GestureDetector(getContext(), this);

		setDate(0, 0);
	}

	/**
	 * 计算日历单元格数据
	 */
	private void calculateCalendarCell(int year, int month) {
		selectIndex = -1;
		// 月份从0开始
		Calendar calendar = Calendar.getInstance();
		final int curYear = calendar.get(Calendar.YEAR);
		final int curMonth = calendar.get(Calendar.MONDAY);
		final int curDay = calendar.get(Calendar.DATE);
		// 如果不设置参数"年"，默认为当前年、月
		if (year == 0) {
			year = curYear;
			month = curMonth;
		}
		// 将当前日历设为指定年月的第一天
		calendar.set(year, month, 1);
		// 指定月份的第一天是当前周的第几天
		final int theDays = calendar.get(Calendar.DAY_OF_WEEK);
		// 指定月份的天数
		final int days = Calendars.getDays(year, month);
		// 单元格在日历中的索引，在最后绘制时，用作 6*7
		// 矩阵
		int index = 0;

		/**
		 * 日历上个月数据填充
		 */
		int preYear = year;
		int preMonth = month - 1;
		// 如果是第一个月，上个月为去年的十二月
		if (month == 0) {
			--preYear;
			preMonth = 11;
		}
		// 上个月天数
		final int preDays = Calendars.getDays(preYear, preMonth);
		// 填充上月数据
		for (int i = 0, day = preDays - theDays + 2; i < theDays - 1; ++i, ++day) {
			setCalendarCells(i, preYear, preMonth, day, index++)
					.setPreviousMonth(true);
		}

		/**
		 * 日历指定本月数据填充
		 */
		for (int i = theDays - 1, day = 1; day <= days; ++i, ++day) {
			CalendarCell cell = setCalendarCells(i, year, month, day, index++);
			if (selectIndex == -1) {
				selectIndex = cell.getIndex();
			}
			// 时间为今天
			if (year == curYear && month == curMonth && day == curDay) {
				cell.setToday(true);
				selectIndex = cell.getIndex();
			}
		}

		/**
		 * 日历下个月数据填充
		 */
		int neYear = year;
		int neMonth = month + 1;
		// 如果是十二月份，下个月为下一年一月份
		if (month == 11) {
			++neYear;
			neMonth = 0;
		}
		// 填充下月数据
		for (int i = theDays + days - 1, day = 1; i < calendarCells.length; ++i, ++day) {
			setCalendarCells(i, neYear, neMonth, day, index++).setNextMonth(
					true);
		}

		// 默认第一天或今天选中状态
		calendarCells[selectIndex].selected();

		/**
		 * 调试输出
		 */
		if (BuildConfig.DEBUG) {
			// Log.d(TAG, Arrays.toString(calendarCells));
		}

	}

	/**
	 * 设置单元格数据
	 * 
	 * @param index
	 * @param year
	 * @param month
	 * @param day
	 * @param calendarIndex
	 * @param isToday
	 */
	private CalendarCell setCalendarCells(int index, int year, int month,
			int day, int calendarIndex) {
		if (index < 0 || index >= calendarCells.length) {
			throw new ArrayIndexOutOfBoundsException("calendar cells index");
		}
		if (calendarCells[index] != null) {
			calendarCells[index] = null;
		}
		calendarCells[index] = new CalendarCell();
		calendarCells[index].setIndex(calendarIndex);
		calendarCells[index].setYear(year);
		calendarCells[index].setMonth(month);
		calendarCells[index].setDay(day);
		return calendarCells[index];
	}

	/**
	 * @param calendarListener
	 *            the calendarCellTouchListener to set
	 */
	public void setCalendarListener(OnCalendarListener calendarListener) {
		if (calendarListener != this.calendarListener) {
			this.calendarListener = calendarListener;
		}
	}

	/**
	 * 设置日期
	 * 
	 * @param year
	 * @param month
	 */
	public void setDate(int year, int month) {
		calculateCalendarCell(year, month);
	}

	/**
	 * 日历跳转到下个月
	 */
	public void goNextMonth() {
		Calendar calendar = getCalendar();
		calendar.add(Calendar.MONTH, 1);
		setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
		isAnimation = true;
		ditect = -1;
	}

	/**
	 * 日历跳转到上个月
	 */
	public void goPreviousMonth() {
		Calendar calendar = getCalendar();
		calendar.add(Calendar.MONTH, -1);
		setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
		isAnimation = true;
		ditect = 1;
	}

	/**
	 * 刷新
	 */
	public void refresh() {
		postInvalidate();
	}

	/**
	 * 当前选中日期
	 * 
	 * @return
	 */
	public Calendar getCalendar() {
		return getSelectedCell().getCalendar();
	}

	/**
	 * 当前选中时间戳
	 * 
	 * @return
	 */
	public long getTimestamp() {
		return getSelectedCell().getTimestamp();
	}

	/**
	 * 当前选中单元格
	 * 
	 * @return
	 */
	private CalendarCell getSelectedCell() {
		return calendarCells[selectIndex];
	}

	/**
	 * 处理选择
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private int performSelected(int x, int y) {
		int oldIndex = selectIndex;
		for (CalendarCell cell : calendarCells) {
			cell.unSelected();
			if (cell.getBounds().contains(x, y)) {
				selectIndex = cell.getIndex();
			}
		}
		if (oldIndex == selectIndex) {
			return -1;
		}
		if (selectIndex >= 0) {
			CalendarCell cell = getSelectedCell();
			cell.selected();
		}
		return selectIndex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int measuredWidth = measureWidth(widthMeasureSpec);
		int measuredHeight = measureHeight(heightMeasureSpec);
		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	/**
	 * 计算view所需宽度
	 * 
	 * @param measureSpec
	 * @return
	 */
	private int measureWidth(int measureSpec) {
		int mode = MeasureSpec.getMode(measureSpec);
		int size = MeasureSpec.getSize(measureSpec);
		int width = (int) (calendarCellStyle.getWidth() * Calendars.CELL_COL_COUNT);
		// 精确尺寸
		// 当我们将控件的layout_width指定具体数值或者为FILL_PARENT,忽略cellWidth参数
		if (mode == MeasureSpec.EXACTLY) {
			Log.w(TAG, "layout_width=\"FILL_PARENT\" ignore cellWidth");
			return size;
		}
		// 最大尺寸
		// 当控件的layout_width指定为WRAP_CONTENT,
		else if (mode == MeasureSpec.AT_MOST) {
			if (width == 0) {
				throw new IllegalArgumentException(
						"CalendarView layout_width=\"WRAP_CONTENT\" cellWidth must be set");
			}
			size = Math.min(size, width);
			return size;
		}
		throw new IllegalArgumentException("CalendarView layout_width");
	}

	/**
	 * 计算view所需高度
	 * 
	 * @param measureSpec
	 * @return
	 */
	private int measureHeight(int measureSpec) {
		int mode = MeasureSpec.getMode(measureSpec);
		int size = MeasureSpec.getSize(measureSpec);
		int height = (int) (calendarCellStyle.getHeight() * Calendars.CELL_ROW_COUNT);
		// 星期高度
		int weeksHeight = calendarWeek.getHeight();
		// 精确尺寸
		// 当我们将控件的layout_height指定具体数值或者为FILL_PARENT,忽略cellHeight参数
		if (mode == MeasureSpec.EXACTLY) {
			Log.w(TAG, "layout_height=\"FILL_PARENT\" ignore cellHeight");
			if (weeksHeight <= 0) {
				calendarWeek.setHeight((int) (size * .05f));
			}
			return size;
		}
		// 最大尺寸
		// 当控件的layout_height指定为WRAP_CONTENT,
		else if (mode == MeasureSpec.AT_MOST) {
			if (height == 0) {
				throw new IllegalArgumentException(
						"CalendarView layout_height=\"WRAP_CONTENT\" cellHeight must be set");
			}
			if (weeksHeight <= 0) {
				weeksHeight = (int) (height * .05f);
				calendarWeek.setHeight(weeksHeight);
			}
			height += weeksHeight;
			return height;
		}
		throw new IllegalArgumentException("CalendarView layout_height");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {

		if (weeksBitmap == null) {
			weeksBitmap = generateWeeksBmp();
		}
		canvas.drawBitmap(weeksBitmap, 0, 0, null);

		if (cellsBitmap == null) {
			cellsBitmap = generateCellsBmp();
		}
		canvas.drawBitmap(cellsBitmap, x, y, null);

		if (isAnimation) {
			if (newCellsBitmap == null || newCellsBitmap.isRecycled()) {
				newCellsBitmap = generateCellsBmp();
			}
			canvas.drawBitmap(newCellsBitmap, x > 0 ? x - getWidth() : x
					+ getWidth(), y, null);
		}

		if (isAnimation) {
			if ((getWidth() - Math.abs(x)) <= Calendars.ANIMATION_STEP_FACTOR) {
				onAnimationEnd();
			} else {
				x += ditect * (getWidth() - Math.abs(x))
						* Calendars.ANIMATION_STEP_FACTOR;
			}
			postInvalidate();
		}

		if (!isAnimation) {
			drawSelected(canvas);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onAnimationStart()
	 */
	@Override
	protected void onAnimationStart() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onAnimationEnd()
	 */
	@Override
	protected void onAnimationEnd() {
		Calendars.destoryBitmap(cellsBitmap);
		cellsBitmap = Bitmap.createBitmap(newCellsBitmap);
		Calendars.destoryBitmap(newCellsBitmap);
		isAnimation = false;
		x = 0;
		y = 0;
	}

	/**
	 * 绘制日历星期
	 * 
	 * @param canvas
	 */
	private Bitmap generateWeeksBmp() {
		Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(),
				Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		// 整个星期宽度和高度
		final int canvasWidth = getWidth();
		final int canvasHeight = calendarWeek.getHeight();

		// 日历内边距
		final int paddingLeft = getPaddingLeft();
		final int paddingTop = getPaddingTop();
		final int paddingRight = getPaddingRight();

		// 星期宽度
		float weekWidth = (float) (canvasWidth - paddingLeft - paddingRight)
				/ Calendars.CELL_COL_COUNT;

		float x = 0f;
		float y = 0f;
		float stringHeight = 0f;
		int col = 0;

		/**
		 * 绘制背景颜色、背景图片
		 */
		RectF bounds = new RectF();
		bounds.left = paddingRight;
		bounds.top = paddingTop;
		bounds.right = canvasWidth - paddingRight;
		bounds.bottom = bounds.top + canvasHeight;

		paint.setStyle(Paint.Style.FILL);
		paint.setColor(calendarWeek.getBackgroundColor());
		canvas.drawRect(bounds, paint);

		Drawable drawable = calendarWeek.getDrawable();
		if (drawable != null) {
			Rect rect = new Rect();
			bounds.round(rect);
			drawable.setBounds(rect);
			drawable.draw(canvas);
		}

		/**
		 * 绘制星期
		 */
		paint.setColor(calendarWeek.getTextColor());
		paint.setTextSize(calendarWeek.getTextSize());
		for (String week : calendarWeek.getWeeks()) {
			// 文字宽度
			stringHeight = paint.descent() - paint.ascent();
			// 日历内边距，文字宽度 必须把顶点坐标偏移
			x = weekWidth * (col++) + paddingLeft;
			y = paddingTop + stringHeight;
			canvas.drawText(week, x, y, paint);
		}

		Log.d(TAG, "Generate Weeks Bitmap ");

		return bitmap;
	}

	/**
	 * 绘制日历单元格
	 * 
	 * @param canvas
	 */
	private Bitmap generateCellsBmp() {
		Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(),
				Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		// 星期高度
		final int heightOffset = calendarWeek.getHeight();
		// 画布宽度、高度
		final int canvasWidth = getWidth();
		final int canvasHeight = getHeight() - heightOffset;
		// 间隔
		final int cellSpace = calendarCellStyle.getSpace();
		// 外框宽度
		final float cellLineSize = calendarCellStyle.getLineSize();
		// 外框颜色
		final int cellLineColor = calendarCellStyle.getLineColor();
		// 单元格内边距
		final int cellPaddingLeft = calendarCellStyle.getPaddingLeft();
		final int cellPaddingTop = calendarCellStyle.getPaddingTop();
		final int cellPaddingRight = calendarCellStyle.getPaddingRight();
		final int cellPaddingBottom = calendarCellStyle.getPaddingBottom();
		// 单元格左右、上下分别多出的边距
		final int cellPaddingTB = cellPaddingTop + cellPaddingBottom;
		final int cellPaddingLF = cellPaddingLeft + cellPaddingRight;
		// 日历内边距
		final int paddingLeft = getPaddingLeft();
		final int paddingTop = getPaddingTop();
		final int paddingRight = getPaddingRight();
		final int paddingBottom = getPaddingBottom();
		// 日历左右、上下分别多出的边距
		final int paddingTB = paddingTop + paddingBottom;
		final int paddingLF = paddingLeft + paddingRight;
		// 文字高度
		float stringHeight = 0;
		// 单元格宽度
		// (画布宽度 - 单元格边距 * 6[一行7个单元格6个边距] - 单元格左右边距 * 7[7个单元格] - 日历左右边距) /
		// 单元格数量
		final float cellWidth = (float) (canvasWidth - cellSpace
				* (Calendars.CELL_COL_COUNT - 1) - cellPaddingLF
				* Calendars.CELL_COL_COUNT - paddingLF)
				/ Calendars.CELL_COL_COUNT;
		// 单元格高度
		// (画布高度 - 单元格边距 * 5[一列6个单元格5个边距] - 单元格上下边距 * 6[6个单元格] - 日历上下边距) /
		// 单元格数量
		final float cellHeight = (float) (canvasHeight - cellSpace
				* (Calendars.CELL_ROW_COUNT - 1) - cellPaddingTB
				* Calendars.CELL_ROW_COUNT - paddingTB)
				/ Calendars.CELL_ROW_COUNT;

		// 循环绘制日历单元格
		for (CalendarCell cell : calendarCells) {

			// 计算当前单元格在日历中对应的行、列
			int row = cell.getIndex() / 7;
			int col = cell.getIndex() % 7;

			int top = row * cellPaddingTB;
			int left = col * cellPaddingLF;
			RectF bounds = new RectF();
			// 日历内边距，必须把顶点坐标偏移
			// 单元格边距，顶点坐标偏移
			bounds.left = cellWidth * col + left
					+ (col > 0 ? cellSpace * col : 0) + paddingLeft;
			bounds.top = cellHeight * row + top
					+ (row > 0 ? cellSpace * row + 0 : 0) + paddingTop
					+ heightOffset;
			// 单元格内边距，必须算在整个单元格的宽度和高度内
			bounds.right = bounds.left + cellWidth + cellPaddingLF;
			bounds.bottom = bounds.top + cellHeight + cellPaddingTB;

			// 设置单元格对应的坐标
			cell.setBounds(bounds);

			int textColor = Color.TRANSPARENT;
			Drawable drawable = null;
			int backgroundColor = Color.TRANSPARENT;
			/**
			 * 绘制日期文字
			 */
			if (cell.isNextMonth() || cell.isPreviousMonth()) {
				// 非本月
				if (cell.getWeek() == Week.SUNDAY) {
					textColor = calendarCellStyle.getWeekEndTextColorX();
				} else {
					textColor = calendarCellStyle.getTextColorX();
				}
				backgroundColor = calendarCellStyle.getBackgroundColorX();
				drawable = calendarCellStyle.getDrawableX();
			} else {
				// 本月
				if (cell.getWeek() == Week.SUNDAY) {
					textColor = calendarCellStyle.getWeekEndTextColor();
				} else {
					textColor = calendarCellStyle
							.getCurrentTextColor(Calendars.EMPTY_STATE_SET);
				}
				backgroundColor = calendarCellStyle
						.getCurrentBackgroundColor(Calendars.EMPTY_STATE_SET);
				drawable = calendarCellStyle
						.getCurrentDrawable(Calendars.EMPTY_STATE_SET);
			}

			/**
			 * 绘制单元格颜色
			 */
			paint.setStyle(Paint.Style.FILL);
			paint.setColor(backgroundColor);
			canvas.drawRect(bounds, paint);

			/**
			 * 绘制单元格背景
			 */
			if (drawable != null) {
				Rect rect = new Rect();
				bounds.round(rect);
				drawable.setBounds(rect);
				drawable.draw(canvas);
			}

			paint.setColor(textColor);
			paint.setTextSize(calendarCellStyle.getTextSize());
			stringHeight = paint.descent() - paint.ascent();
			canvas.drawText("" + cell.getDay(), bounds.left + stringHeight / 4,
					bounds.top + stringHeight, paint);

			/**
			 * 绘制外框
			 */
			if (cellLineSize > 0) {
				paint.setStyle(Paint.Style.STROKE);
				paint.setStrokeWidth(cellLineSize);
				paint.setColor(cellLineColor);
				canvas.drawRect(bounds, paint);
			}

		}
		Log.d(TAG, "Generate Cells Bitmap");
		return bitmap;
	}

	/**
	 * 绘制选中日历单元格
	 * 
	 * @param canvas
	 */
	private void drawSelected(Canvas canvas) {
		CalendarCell cell = getSelectedCell();
		RectF bounds = cell.getBounds();
		/**
		 * 绘制单元格颜色
		 */
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(calendarCellStyle
				.getCurrentBackgroundColor(Calendars.SELECTED_STATE_SET));
		canvas.drawRect(bounds, paint);

		/**
		 * 绘制单元格背景
		 */
		Drawable drawable = calendarCellStyle
				.getCurrentDrawable(Calendars.SELECTED_STATE_SET);
		if (drawable != null) {
			Rect rect = new Rect();
			bounds.round(rect);
			drawable.setBounds(rect);
			drawable.draw(canvas);
		}

		/**
		 * 绘制文字
		 */
		paint.setTextSize(calendarCellStyle.getTextSize());
		paint.setColor(calendarCellStyle
				.getCurrentTextColor(Calendars.SELECTED_STATE_SET));
		float stringHeight = paint.descent() - paint.ascent();
		canvas.drawText("" + cell.getDay(), bounds.left + stringHeight / 4,
				bounds.top + stringHeight, paint);

	}

	/**
	 * 
	 * @param event
	 */
	private void selectCell(MotionEvent event) {
		final int x = (int) event.getX();
		final int y = (int) event.getY();
		if (performSelected(x, y) >= 0) {
			postInvalidate();
		}
	}

	@Override
	public boolean onDown(MotionEvent event) {
		if (isAnimation) {
			return false;
		}
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent event) {
		selectCell(event);
		if (BuildConfig.DEBUG) {
			Log.d(TAG, "onSingleTapUp :" + getSelectedCell());
		}
		if (calendarListener != null) {
			calendarListener.onCellSelected(getSelectedCell());
		}
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent event) {
		selectCell(event);
		if (BuildConfig.DEBUG) {
			Log.d(TAG, "onLongPress :" + getSelectedCell());
		}
		if (calendarListener != null) {
			calendarListener.onCellLongSelected(getSelectedCell());
		}
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		float dx = e2.getX() - e1.getX();
		if (Math.abs(dx) > Calendars.FLING_MIN_DISTANCE) {
			if (dx > 0) {
				goPreviousMonth();
			}
			if (dx < 0) {
				goNextMonth();
			}
			postInvalidate();
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gestureDetector.onTouchEvent(event);
	}

}
