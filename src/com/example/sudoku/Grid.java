package com.example.sudoku;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.ListView;

class Grid extends View{

	private static Paint paint;
	private SudokuGrid s;
	private int width;
	private int height;
	private int highlightX;
	private int highlightY;
	private int posX;
	private int posY;
	private int level;
	private int toggleListenner;
	
	private ArrayList<Tuple> entreesUtilisateur;
	
	public Grid(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
		entreesUtilisateur = new ArrayList<Tuple>();

        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        s = new SudokuGrid(level);
        toggleListenner=1;
    }
	 void setDiff(int l){
		 System.out.println("LEVEL: " +l);
		 level = l;
		 s.changeDiff(level);
		 invalidate();

	 }
	@Override
	 protected void onDraw(Canvas canvas) {
	  super.onDraw(canvas);
	  
      
	  height = this.getMeasuredHeight();
	  width = this.getMeasuredWidth();
	   
	  if(width>height){
		  int t = width;
		  width = height;
		  height = t;
	  }

	  drawGrid(canvas, width, height,paint);

	 }
	
	
	//------------LISTENNER----------------//
	public interface IMyEventListener {
	    public void onEventAccured();
	}

	private IMyEventListener mEventListener;

	public void setEventListener(IMyEventListener mEventListener) {
	    this.mEventListener = mEventListener;
	}
	//------------END LISTENNER----------------//

	@Override
	public boolean onTouchEvent(MotionEvent e){
		highlightX = (int)(e.getX()/(width/9));
		highlightY = (int)(e.getY()/(width/9));
		posX = highlightX;
		posY = highlightY;
		
		if(toggleListenner==1){
			mEventListener.onEventAccured();
			toggleListenner=0;
		}
		
		if(highlightX<9 && highlightY<9)
			invalidate(); //<=> repaint
		
		return true;
	}
	
	public void setListenner(){
		toggleListenner=1;
	}
	
	public void addNumber(int val){
		System.out.println(val+" pos:"+posX+" ; "+posY);
		boolean bool = s.PlayerAdd(posX, posY, val);
		if(bool){
			entreesUtilisateur.add(new Tuple(posX,posY));
		}
		invalidate();
	}
	
	
	private void drawGrid(Canvas canvas, int width, int height, Paint p){
        paint.setStrokeWidth(1);
        paint.setColor(Color.BLACK);
        
		int margin = width/9;
		
		
		//x y

		paint.setColor(Color.rgb(103, 197, 246));

		paint.setStyle(Style.FILL);
		System.out.println(margin);
		paint.setTextSize(margin/2); 
		paint.setTextAlign(Align.CENTER);
		
		//Drawing rect
		highlightX = highlightX*margin;
		highlightY = highlightY*margin;
		System.out.println(highlightX+" "+highlightY);
        canvas.drawRect( highlightX,  highlightY,highlightX+margin, highlightY+margin,  paint );

		//Drawing rectangles of values added
		if(!entreesUtilisateur.isEmpty()){
			for( Tuple entree : entreesUtilisateur){
				paint.setColor(Color.GRAY);
				int x = entree.getX();
				int y = entree.getY();
		        canvas.drawRect( x*margin,  y*margin,x*margin+margin, y*margin+margin,  paint );

			}
		}
		paint.setColor(Color.BLACK);

		

				
		
		//Drawing text
		for(int i=1;i<=9;i++){
			int x = margin*i-margin/2;
			for(int j=1;j<=9;j++){
				int y = (margin*j)-(margin*1/4);
				String val = Integer.toString(s.getValueAt(i-1, j-1));
				if (val.equals("0"))
					continue;
				canvas.drawText(val, x, y, paint); 
			}
		}
		//paint.setStyle(Style.STROKE);

		//Drawing horizontal lines
		for(int i=1; i<9; i++){
			if(i%3==0){
		        paint.setStrokeWidth(2);
			}
			else{
		        paint.setStrokeWidth(1);
			}
			//debut-x,fin-x, debut-y,fin-y
			canvas.drawLine(margin*i,0,margin*i,width-2, paint);
		}
		
		//Drawing vertical lines
		for(int i=1; i<9; i++){
			if(i%3==0){
		        paint.setStrokeWidth(2);
			}
			else{
		        paint.setStrokeWidth(1);

			}
			canvas.drawLine(0,margin*i,width,margin*i, paint);
		}
		  paint.setStyle(Style.STROKE);
		  paint.setStrokeWidth(1);
	      paint.setColor(Color.RED);
		  canvas.drawRect(1,1, width-2, width-2, paint);
		  
	}
	
	 
}