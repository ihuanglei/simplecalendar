package example;

import org.simple.calendar.CalendarAdapter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class DataAdapter extends CalendarAdapter {

	private Paint paint;

	/**
	 * 
	 */
	public DataAdapter() {
		paint = new Paint();
		paint.setAntiAlias(true);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.simple.calendar.CalendarAdapter#onDraw(android.graphics.Canvas,
	 * float, float)
	 */
	@Override
	public void onDraw(Canvas canvas, RectF bounds) {
		paint.setColor(Color.YELLOW);
		paint.setAlpha(50);
		paint.setStyle(Paint.Style.FILL);
		canvas.drawRect(bounds, paint);
		paint.setAlpha(100);
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(0.3f);
		canvas.drawRect(bounds, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText("中秋", bounds.left, bounds.centerY()
				+ (paint.descent() - paint.ascent()) / 2, paint);

	}

}
