/**
 * 
 */
package org.simple.calendar;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 日历适配器
 * 
 * @author huanglei
 * @email code.huanglei at gmail.com
 * @date Sep 18, 2012
 */
public abstract class CalendarAdapter extends BaseAdapter {

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	public abstract Drawable getDraw(int position);

}
