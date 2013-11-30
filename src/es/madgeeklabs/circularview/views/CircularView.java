package es.madgeeklabs.circularview.views;

import java.util.HashMap;
import java.util.LinkedList;

import es.madgeeklabs.circularview.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CircularView extends RelativeLayout{

	private static final String TAG = CircularView.class.getName();
	private int centerId = 1337;
	private int radius = 200;
	private int widthElement;
	private int heightElement;
	private int numberOfElements;
	private HashMap<Integer, Drawable> drawables;
	private LinkedList<Integer> elems;
	View center;
	
	public CircularView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.getTheme().obtainStyledAttributes(
		        attrs,
		        R.styleable.CircleMenu,
		        0, 0);
		center = new View(context);
		center.setId(centerId );
		center.setBackgroundColor(getResources().getColor(R.color.Red));
		RelativeLayout.LayoutParams lpcenter = new RelativeLayout.LayoutParams(1, 1);
		lpcenter.addRule(CENTER_HORIZONTAL);
		lpcenter.addRule(CENTER_VERTICAL);
		center.setLayoutParams(lpcenter);
		this.addView(center);
		numberOfElements = a.getInt(R.styleable.CircleMenu_numberOfElements, 20);
		widthElement = a.getInt(R.styleable.CircleMenu_widthOfElement, 20);
		heightElement = a.getInt(R.styleable.CircleMenu_heightOfElement, 20);
		this.measure(0, 0);
		int width = this.getMeasuredWidth();
		Log.d(TAG, "size is: " + width);
		
		if (isInEditMode()) {
			setEditElements();
		}
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		Log.d(TAG, "size in measure " + w);
		radius = w;
	}

	public void paint() {
		setElements();
	}

	public void setResources (HashMap<Integer, Drawable> drawables) {
		this.drawables = drawables;
	}
	public void setElements (LinkedList<Integer> elements) {
		this.elems = elements;
	}
	
	public void setRadius (int radius) {
		this.radius = radius;
	}
	
	public void setSizeOfElements (int width, int heigth) {
		this.widthElement = width;
		this.heightElement = heigth;
	}
	
	public void setStatus (int element, int status) {
		elems.set(element, status);
		setElements();
	}
	
	private void setEditElements () {
		for (int x = 0 ; x < numberOfElements; x++){
			
			ImageView elem = new ImageView(getContext());
			
			elem.setImageDrawable(getResources().getDrawable(R.drawable.green_circle));
	        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
	                           widthElement,
	                           heightElement);
	        lp.addRule(RelativeLayout.ABOVE, center.getId());
	        lp.addRule(RIGHT_OF, center.getId());
	        elem.measure(0, 0);

	        int deltaX = elem.getMeasuredWidth() / 2;
	        int deltaY = elem.getMeasuredHeight() / 2;
	        
	        lp.setMargins(((int) (radius * Math.sin(2 * Math.PI * x
	                           / numberOfElements)) - deltaX), 0, 0, ((int) (radius * Math
	                           .cos(2 * Math.PI * x / numberOfElements)) - deltaY));
	        elem.setLayoutParams(lp);
	        this.addView(elem);
	        
		}
	}
	
	private void setElements () {
		for (int i = 0; i < elems.size(); i++) {
			
			ImageView elem = new ImageView(getContext());
			
			elem.setImageDrawable(drawables.get(elems.get(i)));
	        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
	                           widthElement,
	                           heightElement);
	        lp.addRule(RelativeLayout.ABOVE, center.getId());
	        lp.addRule(RIGHT_OF, center.getId());
	

	        int deltaX = widthElement / 2;
	        int deltaY = heightElement / 2;
	        
	        lp.setMargins(((int) (radius * Math.sin(2 * Math.PI * i
	                           / elems.size())) - deltaX), 0, 0, ((int) (radius * Math
	                           .cos(2 * Math.PI * i / elems.size())) - deltaY));
	        elem.setLayoutParams(lp);
	        this.addView(elem);
	        
		}
	}
	
}
