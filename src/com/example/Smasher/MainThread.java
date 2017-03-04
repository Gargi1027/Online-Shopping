package com.example.Smasher;

import java.util.Random;

import com.example.Smasher.R;
import com.example.Smasher.Bug.BugState;
import com.example.Smasher.LadyBug.LadyBugState;
import com.example.Smasher.Assets;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainThread extends Thread {
	private SurfaceHolder holder;
	private Handler handler; // required for running code in the UI thread
	private boolean isRunning = false;
	Context context;
	Paint paint;
	int touchx, touchy; // x,y of touch event
	int touch_count = 0;
	boolean touched; // true if touch happened
	// boolean touched_ladybug;
	private static final Object lock = new Object();
	
	 Random random = new Random();

	public MainThread(SurfaceHolder surfaceHolder, Context context) {
		holder = surfaceHolder;
		this.context = context;
		handler = new Handler();
		touched = false;
		// touched_ladybug = false;
	}

	public void setRunning(boolean b) {
		isRunning = b; // no need to synchronize this since this is the only
						// line of code to writes this variable
	}

	// Set the touch event x,y location and flag indicating a touch has happened
	public void setXY(int x, int y) {
		synchronized (lock) {
			touchx = x;
			touchy = y;
			this.touched = true;
			// this.touched_ladybug = true;
		}
	}

	@Override
	public void run() {
		while (isRunning) {
			// Lock the canvas before drawing
			Canvas canvas = holder.lockCanvas();
			if (canvas != null) {
				// Perform drawing operations on the canvas
				render(canvas);
				// After drawing, unlock the canvas and display it
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}

	// Loads graphics, etc. used in game
	private void loadData(Canvas canvas) {
		Bitmap bmp;
		int newWidth, newHeight;
		float scaleFactor;

		// Create a paint object for drawing vector graphics
		paint = new Paint();

		// Load score bar
		bmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.scorebar);
		// Compute size of bitmap needed (suppose want height = 10% of screen
		// height)
		newHeight = (int) (canvas.getHeight() * 0.1f);
		// Scale it to a new size
		Assets.scorebar = Bitmap.createScaledBitmap(bmp, canvas.getWidth(),
				newHeight, false);
		// Delete the original
		bmp = null;

		// Load food bar
		bmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.foodbar);
		// Compute size of bitmap needed (suppose want height = 10% of screen
		// height)
		newHeight = (int) (canvas.getHeight() * 0.1f);
		// Scale it to a new size
		Assets.foodbar = Bitmap.createScaledBitmap(bmp, canvas.getWidth(),
				newHeight, false);
		// Delete the original
		bmp = null;

		// Load roach1
		bmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.roach1);
		// Compute size of bitmap needed (suppose want width = 20% of screen
		// width)
		newWidth = (int) (canvas.getWidth() * 0.2f);
		// What was the scaling factor to get to this?
		scaleFactor = (float) newWidth / bmp.getWidth();
		// Compute the new height
		newHeight = (int) (bmp.getHeight() * scaleFactor);
		// Scale it to a new size
		Assets.roach1 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight,
				false);
		// Delete the original
		bmp = null;

		bmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.roach2);
		// Compute size of bitmap needed (suppose want width = 20% of screen
		// width)
		newWidth = (int) (canvas.getWidth() * 0.2f);
		// What was the scaling factor to get to this?
		scaleFactor = (float) newWidth / bmp.getWidth();
		// Compute the new height
		newHeight = (int) (bmp.getHeight() * scaleFactor);
		// Scale it to a new size
		Assets.roach2 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight,
				false);
		// Delete the original
		bmp = null;

		// Load the leaf

		bmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.leaf);
		// Compute size of bitmap needed (suppose want width = 20% of screen
		// width)
		newWidth = (int) (canvas.getWidth() * 0.5f);
		// What was the scaling factor to get to this?
		scaleFactor = (float) newWidth / bmp.getWidth();
		// Compute the new height
		newHeight = (int) (bmp.getHeight() * scaleFactor);
		// Scale it to a new size
		Assets.leaf = Bitmap
				.createScaledBitmap(bmp, newWidth, newHeight, false);
		// Delete the original
		bmp = null;
		// ...

		bmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.ladybug);
		// Compute size of bitmap needed (suppose want width = 20% of screen
		// width)
		newWidth = (int) (canvas.getWidth() * 0.2f);
		// What was the scaling factor to get to this?
		scaleFactor = (float) newWidth / bmp.getWidth();
		// Compute the new height
		newHeight = (int) (bmp.getHeight() * scaleFactor);
		// Scale it to a new size
		Assets.ladyroach = Bitmap.createScaledBitmap(bmp, newWidth, newHeight,
				false);
		// Delete the original
		bmp = null;

		// Load roach3 (dead bug)
		bmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.roach3);
		// Compute size of bitmap needed (suppose want width = 20% of screen
		// width)
		newWidth = (int) (canvas.getWidth() * 0.2f);
		// What was the scaling factor to get to this?
		scaleFactor = (float) newWidth / bmp.getWidth();
		// Compute the new height
		newHeight = (int) (bmp.getHeight() * scaleFactor);
		// Scale it to a new size
		Assets.roach3 = Bitmap.createScaledBitmap(bmp, newWidth, newHeight,
				false);
		// Delete the original
		bmp = null;
		
		bmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.superbug);
		// Compute size of bitmap needed (suppose want width = 20% of screen
		// width)
		newWidth = (int) (canvas.getWidth() * 0.2f);
		// What was the scaling factor to get to this?
		scaleFactor = (float) newWidth / bmp.getWidth();
		// Compute the new height
		newHeight = (int) (bmp.getHeight() * scaleFactor);
		// Scale it to a new size
		Assets.superroach = Bitmap.createScaledBitmap(bmp, newWidth, newHeight,
				false);
		// Delete the original
		bmp = null;

		// Create a bug
		Assets.bug = new Bug();
		Assets.bug1 = new Bug();
		Assets.bug2 = new Bug();
		Assets.bug3 = new Bug();
		Assets.lady_bug = new LadyBug();
		 Assets.super_bug = new SuperBug();
		 Assets.leaf1 = new Leaf();
		Assets.leaf2 = new Leaf();
		Assets.score = 0;
	}

	// Load specific background screen
	private void loadBackground(Canvas canvas, int resId) {
		// Load background
		Bitmap bmp = BitmapFactory
				.decodeResource(context.getResources(), resId);
		// Scale it to fill entire canvas
		Assets.background = Bitmap.createScaledBitmap(bmp, canvas.getWidth(),
				canvas.getHeight(), false);
		// Delete the original
		bmp = null;
	}

	private void render(Canvas canvas) {
		int i, x, y;

		switch (Assets.state) {
		case GettingReady:
			// Load a special "getting ready screen"
			loadBackground(canvas, R.drawable.scaredperson);
			// Load data and other graphics needed by game
			loadData(canvas);
			// Draw the background screen
			canvas.drawBitmap(Assets.background, 0, 0, null);
			// Play a sound effect
			Assets.soundPool.play(Assets.sound_getready, 1, 1, 1, 0, 1);
			// Start a timer
			Assets.gameTimer = System.nanoTime() / 1000000000f;
			// Go to next state
			Assets.state = Assets.GameState.Starting;
			break;
		case Starting:
			// Draw the background screen
			canvas.drawBitmap(Assets.background, 0, 0, null);
			// Has 3 seconds elapsed?
			float currentTime = System.nanoTime() / 1000000000f;
			if (currentTime - Assets.gameTimer >= 3) {
				// Load game play background
				loadBackground(canvas, R.drawable.background);
				// Goto next state

				Assets.state = Assets.GameState.Running;
			}
			break;
		case Running:
			// Draw the background screen
			canvas.drawBitmap(Assets.background, 0, 0, null);
			// Draw the score bar at top of screen
			canvas.drawBitmap(Assets.scorebar, canvas.getWidth()
					- Assets.scorebar.getWidth(), 0, null);
			// Draw the score on the score bar
			// canvas.drawText(....)
			paint.setColor(Color.WHITE); // Text Color
			// paint.setStrokeWidth(12); // Text Size
			paint.setTextSize(15);
			canvas.drawText(Assets.current_score, 30, 20, paint);
			// Draw the foodbar at bottom of screen
			canvas.drawBitmap(Assets.foodbar, 0, canvas.getHeight()
					- Assets.foodbar.getHeight(), null);
			// Draw one circle for each life at top right corner of screen
			// Let circle radius be 5% of width of screen
			int radius = (int) (canvas.getWidth() * 0.05f);
			int spacing = 8; // spacing in between circles
			x = canvas.getWidth() - radius - spacing; // coordinates for
														// rightmost circle to
														// draw
			y = radius + spacing;
			for (i = 0; i < Assets.livesLeft; i++) {
				paint.setColor(Color.GREEN);
				paint.setStyle(Style.FILL);
				canvas.drawCircle(x, y, radius, paint);
				paint.setColor(Color.BLACK);
				paint.setStyle(Style.STROKE);
				canvas.drawCircle(x, y, radius, paint);
				// Reposition to draw the next circle to the left
				x -= (radius * 2 + spacing);
			}
			Assets.mp.start();
			Assets.mp.setLooping(true);
			// Process a touch for bug

			if (touched) {
				// Set touch flag to false since we are processing this touch
				// now
				touched = false;

				// See if this touch killed a bug
				boolean bugKilled = Assets.bug.touched(canvas, touchx, touchy);
				boolean bugKilled1 = Assets.bug1
						.touched(canvas, touchx, touchy);
				boolean bugKilled2 = Assets.bug2
						.touched(canvas, touchx, touchy);
				boolean bugKilled3 = Assets.bug3
						.touched(canvas, touchx, touchy);

				boolean ladybugKilled = Assets.lady_bug.touched_ladybug(canvas,
						touchx, touchy);
				boolean superbugKilled = Assets.super_bug.touched_superbug(
						canvas, touchx, touchy);
				boolean leafKilled1 = Assets.leaf1.touched(canvas, touchx,
						touchy);
				boolean leafKilled2 = Assets.leaf2.touched(canvas, touchx,
						touchy);

				

				if (bugKilled || bugKilled1 || bugKilled2 || bugKilled3) {
					
					int randomInteger = random.nextInt(2);
					if(randomInteger==1)
					Assets.soundPool.play(Assets.sound_squish1, 1, 1, 1, 0, 1);
					else if(randomInteger==2)
						Assets.soundPool.play(Assets.sound_squish2, 1, 1, 1, 0, 1);
					else
						Assets.soundPool.play(Assets.sound_squish3, 1, 1, 1, 0, 1);	
					Assets.score++;
					Assets.current_score = "score: " + Assets.score;
				}
				if (superbugKilled) {
					Assets.soundPool.play(Assets.sound_squish_super, 1, 1, 1, 0, 1);
					Assets.score = Assets.score + 10;
					Assets.current_score = "score: " + Assets.score;
				}
				
				if (ladybugKilled) {
					Assets.soundPool.play(Assets.sound_ladybugdeath, 1, 1, 1,
							0, 1);
					Log.i("ProjectLogging","Here");
					Assets.state = Assets.GameState.GameEnding;
				}
				
				 if(leafKilled1 || leafKilled2)
					{
					 
					    
						Assets.soundPool.play(Assets.sound_squish1, 1, 1, 1, 0, 1);
						Assets.livesLeft++;
					}

				else {
					Assets.soundPool.play(Assets.sound_thump, 1, 1, 1, 0, 1);
				}

			}

			// Draw dead bugs on screen
			Assets.bug.drawDead(canvas);
			Assets.bug.move(canvas);

			Assets.bug.birth(canvas);

			Assets.bug1.drawDead(canvas);
			Assets.bug1.move(canvas);
			Assets.bug1.birth(canvas);

			Assets.bug2.drawDead(canvas);
			Assets.bug2.move(canvas);
			Assets.bug2.birth(canvas);

			Assets.bug3.drawDead(canvas);
			Assets.bug3.move(canvas);
			Assets.bug3.birth(canvas);

			Assets.lady_bug.drawDead(canvas);

			Assets.lady_bug.move(canvas);
			Assets.lady_bug.birth(canvas);

			Assets.super_bug.drawDead(canvas);
			Assets.super_bug.move(canvas);
			Assets.super_bug.birth(canvas);

			if (Assets.livesLeft == 1) {

				Assets.leaf1.drawDead(canvas);
				Assets.leaf1.birth(canvas);
				Assets.leaf1.move(canvas);
			}

			if (Assets.livesLeft == 2) {

				Assets.leaf2.drawDead(canvas);
				Assets.leaf2.birth(canvas);
				Assets.leaf2.move(canvas);
			}

			// Are no lives left?

			if (Assets.livesLeft == 0) {
				// Goto next state
				Assets.state = Assets.GameState.GameEnding;
			}
			break;

		case GameEnding:
			// Show a game over message

			if (Assets.mp != null) {
				Assets.mp.stop();
				Assets.mp.release();
				Assets.mp = null;

			}
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "Game Over", Toast.LENGTH_SHORT)
							.show();
					
				}
			});
			// Goto next state
			Assets.state = Assets.GameState.GameOver;
			break;
		case GameOver:

			// Fill the entire canvas' bitmap with 'black'
			canvas.drawColor(Color.BLACK);

			// Get the current score from preferences
			SharedPreferences sharedPreferences = PreferenceManager
					.getDefaultSharedPreferences(context);
			int highscore = sharedPreferences.getInt("prefs_highscore", 0);

			// Check for the new high score has been reached or not
			if (Assets.score > highscore) {
				// save the new high score
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putInt("prefs_highscore", Assets.score);
				editor.commit();

				// Show a new high score message
				handler.post(new Runnable() {
					public void run() {
						Assets.soundPool.play(Assets.sound_high_score, 1, 1, 1, 0, 1);
						Toast.makeText(context,
								"New High Score has been reached",
								Toast.LENGTH_LONG).show();
					}
				});

			}
			
			

			break;

		}
	}
	
}
