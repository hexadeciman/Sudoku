package com.example.sudoku;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * View for the Sudoku Game that'll be used in SudokuActivity
 */
class GridCustView extends View{
	private static Paint paint;
	private SudokuController s;
	private int width;
	private int height;
	private int highlightX;
	private int highlightY;
	private int posX;
	private int posY;
	private int level;
	public int toggleOutOfBounds;
	public boolean hide;
	private ArrayList<Tuple> entreesUtilisateur;
	
	public GridCustView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        hide = false;
		entreesUtilisateur = new ArrayList<Tuple>();

        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        s = new SudokuController(level);
        toggleOutOfBounds=1;
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
		if(e.getAction() != 1)
			return true;
		highlightX = (int)(e.getX()/(width/9));
		highlightY = (int)(e.getY()/(width/9));
		posX = highlightX;
		posY = highlightY;
		
		if(highlightX<9 && highlightY<9){
			mEventListener.onEventAccured();
			invalidate(); //<=> repaint
			return true;
		}
		return false;
	}
	
	public int addNumber(int val){
		System.out.println(val+" pos:"+posX+" ; "+posY);
		//if we click on delete
		
		if(val==0){
			if(s.deleteAt(posX, posY)){
				invalidate();
			}
			return 0;
		}
		//Result vaut 2 si c'est fini. 1 si c'est pas fini
		int result = s.PlayerAdd(posX, posY, val);
		if(result!=3)
			entreesUtilisateur.add(new Tuple(posX,posY));
		System.out.println("Result"+result);
		invalidate();
		return result;
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
				if(s.getValueAt(x, y)!=0)
					canvas.drawRect( x*margin,  y*margin,x*margin+margin, y*margin+margin,  paint );

			}
		}
		paint.setColor(Color.BLACK);

		if(!hide){
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
		}

		//Drawing horizontal lines
		for(int i=1; i<9; i++){
			if(i%3==0){
		        paint.setStrokeWidth(2);
			}
			else{
		        paint.setStrokeWidth(1);
			}
			//debut-x,fin-x, debut-y,fin-y
			canvas.drawLine(margin*i,0,margin*i,width-8, paint);
		}
		
		//Drawing vertical lines
		for(int i=1; i<9; i++){
			if(i%3==0){
		        paint.setStrokeWidth(2);
			}
			else{
		        paint.setStrokeWidth(1);

			}
			canvas.drawLine(0,margin*i,width-8,margin*i, paint);
		}
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(1);
	    paint.setColor(Color.RED);
		//Drawing the main Rectangle
	    canvas.drawRect(1,1, width-8, width-8, paint);
	}
}