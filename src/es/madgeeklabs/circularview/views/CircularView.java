package es.madgeeklabs.circularview.views;

import java.util.HashMap;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ObjectAnimator;

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
	public static final int EXPAND = 0;
	public static final int ROLL = 0;
	private int centerId = 1337;
	private int radius = 200;
	private int widthElement;
	private int heightElement;
	private int numberOfElements;
	private boolean animation = false;
	private int animationType;
	private HashMap<Integer, Drawable> drawables;
	private int [] elems;
	View center;
	
	public CircularView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.getTheme().obtainStyledAttributes(
		        attrs,
		        R.styleable.CircleMenu,
		        0, 0);
		center = new View(context);
		center.setId(centerId);
		RelativeLayout.LayoutParams lpcenter = new RelativeLayout.LayoutParams(0, 0);
		lpcenter.addRule(CENTER_HORIZONTAL);
		lpcenter.addRule(CENTER_VERTICAL);
		center.setLayoutParams(lpcenter);
		this.addView(center);
		
		int width = this.getMeasuredWidth();
		Log.d(TAG, "size is: " + width);
		
		if (isInEditMode()) {
			center.setBackgroundColor(getResources().getColor(R.color.Red));
			ImageView preview = new ImageView(getContext());
			preview.setImageDrawable(getResources().getDrawable(R.drawable.stroke_circle));
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
	                LayoutParams.MATCH_PARENT,
	                LayoutParams.MATCH_PARENT);
			lp.setMargins(0, 0, 1, 1);
			preview.setLayoutParams(lp);
			this.addView(preview);
		}
	}


	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		Log.d(TAG, "size in measure " + w);
		radius = ((int) w / 2 )- widthElement;
		if (! isInEditMode()) {
			setElements();
		}
	}

	public void paint() {
		setElements();
	}

	public void setResources (HashMap<Integer, Drawable> drawables) {
		this.drawables = drawables;
	}
	public void setElements (int [] elements) {
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
		elems[element] = status;
		setElements();
	}
	
	public void setAnimation(int type) {
		this.animation = true;
		animationType = type;
	}
	
	private void setElements () {
		Log.d(TAG,"elems: " + elems.length);
		for (int i = 0; i < elems.length; i++) {
			
			final ImageView elem = new ImageView(getContext());
			
			elem.setImageDrawable(drawables.get(elems[i]));
	        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
	                           widthElement,
	                           heightElement);
	        lp.addRule(RelativeLayout.ABOVE, center.getId());
	        lp.addRule(RIGHT_OF, center.getId());
	

	        int deltaX = widthElement / 2;
	        int deltaY = heightElement / 2;
	        
	        lp.setMargins(((int) (radius * Math.sin(2 * Math.PI * i
	                           / elems.length)) - deltaX), 0, 0, ((int) (radius * Math
	                           .cos(2 * Math.PI * i / elems.length)) - deltaY));
	        
	        Log.d(TAG, "position " + (radius * Math.sin(2 * Math.PI * i / numberOfElements) - deltaX) + " " + 
	        		((radius * Math.cos(2 * Math.PI * i / elems.length)) - deltaY));
	        elem.setLayoutParams(lp);
	        this.addView(elem); 
	        
	        if (animation) {
	        	addAnimation(elem, i, deltaX, deltaY);
	        }
		}
	}


	private void addAnimation(final ImageView elem, int i, int deltaX, int deltaY) {
		elem.setVisibility(GONE);
        
        ObjectAnimator anim = ObjectAnimator.ofFloat(elem, "translationX", -(int)((radius * Math.sin(2 * Math.PI * i
                / elems.length)) - deltaX), 0f );
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(elem, "translationY", (int)((radius * Math.cos(2 * Math.PI * i 
        		/ elems.length)) - deltaY), 0f );
        anim2.setDuration(100);
        
        anim2.start();
        anim.setDuration(100);
        if (animationType == ROLL) {
        	anim2.setStartDelay(i * 50);
            anim.setStartDelay(i * 50);
        }
        
        anim.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				elem.setVisibility(VISIBLE);
				
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
		});
        anim.start();
		
	}
	
}
