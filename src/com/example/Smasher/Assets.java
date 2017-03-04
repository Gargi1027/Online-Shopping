package com.example.Smasher;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class Assets {
	static Bitmap background;
	static Bitmap foodbar;
	static Bitmap scorebar;
	static Bitmap leaf;
	static Bitmap roach1;
	static Bitmap roach2;
	static Bitmap roach3;
	static Bitmap ladyroach;
	static Bitmap superroach;
	

	// States of the Game Screen
	enum GameState {
	  GettingReady,	// play "get ready" sound and start timer, goto next state 
	  Starting,		// when 3 seconds have elapsed, goto next state
	  Running, 		// play the game, when livesLeft == 0 goto next state
	  GameEnding,	// show game over message
	  GameOver,		// game is over, wait for any Touch and go back to title activity screen
	 
	};
	static GameState state;		// current state of the game
	static float gameTimer;	// in seconds
	static float notifyCallTime;
	static float waitCallTime;
	static int livesLeft;		// 0-3
	public static int score=0;
	public static int touch_count=0;
	public static String current_score = "score: 0";
	static SoundPool soundPool;
	static int sound_getready;
	static int sound_squish1;
	static int sound_squish2;
	static int sound_squish3;
	static int sound_thump;
	static int sound_ladybugdeath;
	static int sound_squish_super;
	static int sound_high_score;
	public static MediaPlayer mp=null;
	
	static Bug bug;
	static Bug bug1;
	static Bug bug2;
	static Bug bug3;
	static LadyBug lady_bug;
	static SuperBug super_bug;
	static Leaf leaf1;
static Leaf leaf2;
}

