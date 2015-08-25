package com.zkryug.test.square;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;


public class SquareView extends SquarePixelView {

	
	  /**
	   * Current mode of application: READY to run, RUNNING, or you have already
	   * lost. static final ints are used instead of an enum for performance
	   * reasons.
	   */
	  private int mMode = READY;
	  public static final int PAUSE = 0;
	  public static final int READY = 1;
	  public static final int RUNNING = 2;
	  public static final int QUIT = 3;
	  
	  private static final int NORTH = 1;
	  private static final int SOUTH = 2;
	  private static final int EAST = 3;
	  private static final int WEST = 4;
	  
	  /**
	   * Labels for the drawables that will be loaded into the TileView class
	   */
	  private static final int RED_STAR = 1;
	  private static final int YELLOW_STAR = 2;
	  private static final int GREEN_STAR = 3;
	  
	  /**
	   * mMoveDelay: number of
	   * milliseconds between pixel movements.
	   */
	  private long mMoveDelay = 600;
	  
	  /**
	   * mSnakeTrail: a list of Coordinates that make up the snake's body
	   * mAppleList: the secret location of the juicy apples the snake craves.
	   */
	  private ArrayList<Coordinate> mPixelTrail = new ArrayList<Coordinate>();

	  /**
	   * Create a simple handler that we can use to cause animation to happen. We
	   * set ourselves as a target and we can use the sleep() function to cause an
	   * update/invalidate to occur at a later date.
	   */
	  private RefreshHandler mRedrawHandler = new RefreshHandler();

	  class RefreshHandler extends Handler {

	    @Override
	    public void handleMessage(Message msg) {
	      SquareView.this.update();
	      SquareView.this.invalidate();
	    }

	    public void sleep(long delayMillis) {
	      this.removeMessages(0);
	      sendMessageDelayed(obtainMessage(0), delayMillis);
	    }
	  };
	  

	  /**
	   * Constructs a SnakeView based on inflation from XML
	   * 
	   * @param context
	   * @param attrs
	   */
	  public SquareView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    //TODO: initSnakeView();
	  }

	  public SquareView(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	    //TODO: initSnakeView();
	  }


	  /**
	   * Handles the basic update loop, checking to see if we are in the running
	   * state, determining if a move should be made, updating the pixel's
	   * location.
	   */
	  public void update() {
	    if (mMode == RUNNING) {
	      long now = System.currentTimeMillis();

	      //if (now - mLastMove > mMoveDelay) {
	        clearPixels();
	        updateWalls();
	      //  updateSnake();
	      //  updateApples();
	      //  mLastMove = now;
	      //}
	      mRedrawHandler.sleep(mMoveDelay);
	    }

	  }
	  
	  /**
	   * Draws square walls.
	   * 
	   */
	  private void updateWalls() {
	    for (int x = 0; x < mXPixelCount; x++) {
	      setPixel(GREEN_STAR, x, 0);
	      setPixel(GREEN_STAR, x, mXPixelCount - 1);
	    }
	    for (int y = 1; y < mXPixelCount - 1; y++) {
	      setPixel(GREEN_STAR, 0, y);
	      setPixel(GREEN_STAR, mXPixelCount - 1, y);
	    }
	  }
	  
	  /**
	   * Simple class containing two integer values and a comparison function.
	   * There's probably something I should use instead, but this was quick and
	   * easy to build.
	   * 
	   */
	  private class Coordinate {
	    public int x;
	    public int y;

	    public Coordinate(int newX, int newY) {
	      x = newX;
	      y = newY;
	    }

	    public boolean equals(Coordinate other) {
	      if (x == other.x && y == other.y) {
	        return true;
	      }
	      return false;
	    }

	    @Override
	    public String toString() {
	      return "Coordinate: [" + x + "," + y + "]";
	    }
	  }

}
