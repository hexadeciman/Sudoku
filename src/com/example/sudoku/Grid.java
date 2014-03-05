package com.example.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

class Grid extends View{

	private static Paint paint;
	   
	public Grid(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
    }
	 
	@Override
	 protected void onDraw(Canvas canvas) {
	  super.onDraw(canvas);
	  
	  paint.setStrokeWidth(1);
      paint.setColor(Color.RED);
      
	  int height = this.getMeasuredHeight();
	  int width = this.getMeasuredWidth();
	   
	  if(width>height){
		  int t = width;
		  width = height;
		  height = t;
	  }
	  // Draw a border
	  canvas.drawRect(1,1, width-2, width-2, paint);
	  
	  drawGrid(canvas, width-2, height-2,paint);
	 }
	
	private void drawGrid(Canvas canvas, int width, int height, Paint p){
        paint.setStrokeWidth(1);
        paint.setColor(Color.BLACK);

		int margin = width/9;
		
		
		for(int i=1; i<9; i++){
			if(i%3==0){
		        paint.setStrokeWidth(2);
			}
			else{
		        paint.setStrokeWidth(1);

			}
			canvas.drawLine(margin*i,0,margin*i,width, p);
		}
		for(int i=1; i<9; i++){
			if(i%3==0){
		        paint.setStrokeWidth(2);
			}
			else{
		        paint.setStrokeWidth(1);

			}
			canvas.drawLine(0,margin*i,width,margin*i, p);
		}
	}
	
	 
}