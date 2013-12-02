package es.madgeeklabs.circularview;

import java.util.HashMap;
import es.madgeeklabs.circularview.R;

import es.madgeeklabs.circularview.views.CircularView;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private CircularView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		view = (CircularView) findViewById(R.id.circularView1);
		
		view.setAnimation(CircularView.EXPAND);
		view.setSizeOfElements(30, 30);
		
		int [] elementos = {0,1,1,0,0,0,0,1,0,0,0,0,0,1,0,1,1,1,0,1,0,1,1,1};
		view.setElements(elementos);
		
		HashMap<Integer, Drawable> drawables = new HashMap<Integer, Drawable>();
		drawables.put(0, getResources().getDrawable(R.drawable.red_circle));
		drawables.put(1, getResources().getDrawable(R.drawable.green_circle));
		view.setResources(drawables);
		
//		view.paint();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
