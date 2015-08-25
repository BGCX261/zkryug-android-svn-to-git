package com.zkryug.test.square;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Parameters controlling the size of the tiles and their range within view.
 * Width/Height are in pixels, and Drawables will be scaled to fit to these
 * dimensions. X/Y Tile Counts are the number of tiles that will be drawn.
 */

public class SquarePixelView extends View {

	protected static int mPixelSize;

	protected static int mXPixelCount;
	protected static int mYPixelCount;

	private static int mXOffset;
	private static int mYOffset;

	/**
	 * A hash that maps integer handles specified by the subclasser to the
	 * drawable that will be used for that reference Basically this variable can
	 * handle multiple drawable objects
	 */
	private Bitmap[] mPixelArray;

	/**
	 * A two-dimensional array of integers in which the number represents the
	 * index of the pixel that should be drawn at that locations
	 */
	private int[][] mPixelGrid;

	private final Paint mPaint = new Paint();

	public SquarePixelView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mPixelSize = 12;
	}

	public SquarePixelView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPixelSize = 12;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		mXPixelCount = (int) Math.floor(w / mPixelSize);
		mYPixelCount = (int) Math.floor(h / mPixelSize);

		mXOffset = ((w - (mPixelSize * mXPixelCount)) / 2);
		mYOffset = ((h - (mPixelSize * mYPixelCount)) / 2);

		mPixelGrid = new int[mXPixelCount][mYPixelCount];
		clearPixels();
	}

	/**
	 * Function to set the specified Drawable as the pixel for a particular
	 * integer key.
	 * 
	 * @param key
	 * @param tile
	 */
	public void loadTile(int key, Drawable tile) {
		Bitmap bitmap = Bitmap.createBitmap(mPixelSize, mPixelSize,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		tile.setBounds(0, 0, mPixelSize, mPixelSize);
		tile.draw(canvas);

		mPixelArray[key] = bitmap;
	}

	
	  /**
	   * Resets all pixels to 0 (empty)
	   * 
	   */
	  public void clearPixels() {
	    for (int x = 0; x < mXPixelCount; x++) {
	      for (int y = 0; y < mYPixelCount; y++) {
	        setPixel(0, x, y);
	      }
	    }
	  }
	
	/**
	 * Used to indicate that a particular pixel (set with loadPixel and
	 * referenced by an integer) should be drawn at the given x/y coordinates
	 * during the next invalidate/draw cycle.
	 * 
	 * @param pixelindex
	 * @param x
	 * @param y
	 */
	public void setPixel(int pixelindex, int x, int y) {
		mPixelGrid[x][y] = pixelindex;
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// TODO: This implementation only will do the movement of the pixel in
		// the square
		/*
		 * for (int x = 0; x < mXTileCount; x += 1) { for (int y = 0; y <
		 * mYTileCount; y += 1) { if (mTileGrid[x][y] > 0) {
		 * canvas.drawBitmap(mTileArray[mTileGrid[x][y]], mXOffset + x
		 * mTileSize, mYOffset + y * mTileSize, mPaint); } } }
		 */
	}

}
