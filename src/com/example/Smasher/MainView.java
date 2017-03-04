package com.example.Smasher;

import com.example.Smasher.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainView extends SurfaceView {
	private SurfaceHolder holder = null;
	Context context;
	private MainThread t = null;
	
	// Constructor 
	public MainView (Context context) {
		super(context);
		// Retrieve the SurfaceHolder instance associated with this SurfaceView. 
		holder = getHolder();
		
		// Initialize variables
		this.context = context;
		Assets.state = Assets.GameState.GettingReady;
		Assets.livesLeft = 3;

		// Load the sound effects
	    Assets.soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		Assets.sound_getready = Assets.soundPool.load(context, R.raw.getready, 1);
		Assets.sound_squish1 = Assets.soundPool.load(context, R.raw.squish1, 1);
		Assets.sound_squish2 = Assets.soundPool.load(context, R.raw.squish2, 1);
		Assets.sound_squish3 = Assets.soundPool.load(context, R.raw.squish3, 1);
		Assets.sound_ladybugdeath = Assets.soundPool.load(context, R.raw.ladybugdeath, 1);
		Assets.sound_thump = Assets.soundPool.load(context, R.raw.thump, 1);
		Assets.sound_squish_super=Assets.soundPool.load(context,R.raw.superbugkill,1);
		Assets.mp=MediaPlayer.create(context, R.raw.background);
		Assets.sound_high_score=Assets.soundPool.load(context,R.raw.highscore,1);
	}
	
	public void pause ()
	{
		t.setRunning(false);
		while (true) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
		t = null;
	}
	
	public void resume () 
	{
		t = new MainThread (holder, context);
		t.setRunning(true);
		t.start();
		setFocusable(true); // make sure we get events
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		float x, y;
		int action = event.getAction();
		x = event.getX();
		y = event.getY();

		if (action == MotionEvent.ACTION_DOWN) {
			t.setXY ((int)x, (int)y);
		}		
		return true; // to indicate we have handled this event
	}
	
}

