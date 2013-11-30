package es.madgeeklabs.circularview;

import java.util.HashMap;
import java.util.LinkedList;

import es.madgeeklabs.circularview.R;

import es.madgeeklabs.circularview.views.CircularView;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private CircularView view;
	private static int RED_STATUS = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		view = (CircularView) findViewById(R.id.circularView1);
		
		LinkedList<Integer> elements = new LinkedList<Integer>();
		elements.add(0);
		elements.add(1);
		elements.add(1);
		elements.add(0);
		elements.add(0);
		elements.add(1);
		elements.add(1);
		elements.add(1);
		elements.add(1);
		view.setElements(elements);
		
		HashMap<Integer, Drawable> drawables = new HashMap<Integer, Drawable>();
		drawables.put(0, getResources().getDrawable(R.drawable.red_circle));
		drawables.put(1, getResources().getDrawable(R.drawable.green_circle));
		view.setResources(drawables);
		
		view.paint();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
