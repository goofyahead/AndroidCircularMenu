package es.madgeeklabs.views;

import com.example.es.madgeeklabs.circularview.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CircularView extends RelativeLayout{

	private int centerId = 1337;
	private int radius = 130;
	View center;
	
	public CircularView(Context context, AttributeSet attrs) {
		super(context, attrs);
		center = new View(context);
		center.setId(centerId );
		center.setBackgroundColor(getResources().getColor(R.color.red));
		RelativeLayout.LayoutParams lpcenter = new RelativeLayout.LayoutParams(1, 1);
		lpcenter.addRule(CENTER_HORIZONTAL);
		lpcenter.addRule(CENTER_VERTICAL);
		center.setLayoutParams(lpcenter);
		this.addView(center);
		setElements();
	}

	private void setElements () {
		for (int i = 0; i < 8; i++) {
			ImageView elem = new ImageView(getContext());
			elem.setImageResource(R.drawable.circle);
	        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
	                           RelativeLayout.LayoutParams.WRAP_CONTENT,
	                           RelativeLayout.LayoutParams.WRAP_CONTENT);
	        lp.addRule(RelativeLayout.ABOVE, center.getId());
	        lp.addRule(RIGHT_OF, center.getId());
	        elem.measure(0, 0);

	        int deltaX = elem.getMeasuredWidth() / 2;
	        int deltaY = elem.getMeasuredHeight() / 2;

	        lp.setMargins(((int) (radius * Math.sin(2 * Math.PI * i
	                           / 8)) - deltaX), 0, 0, ((int) (radius * Math
	                           .cos(2 * Math.PI * i / 8)) - deltaY));
	        elem.setLayoutParams(lp);
	        this.addView(elem);
		}
	}
	
}
