package com.example.sudoku;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sudoku.GridCustView.IMyEventListener;

/**
 * Activity for the Sudoku Game (after the button Start is clicked)
 */
public class SudokuActivity extends Activity {
	int level ;
	public int time;
	static boolean isTimerRunning;
	ListView listView2 ;
	GridCustView v ;
	 Thread t;
	int menuOpened;
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    return;
	}
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isTimerRunning=true;
        menuOpened =1;
        setContentView(R.layout.activity_grid_sudoku);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
		level = extras.getInt("level");
		
    	v = (GridCustView) findViewById(R.id.maGrille);
    	v.setDiff(level);
    	v.setEventListener(new IMyEventListener() {
            @Override
            public void onEventAccured() {
           	 	openContextMenu(listView2);
           	 		
            }
        });
    	
        listView2 = (ListView) findViewById(R.id.listView2);
        registerForContextMenu(listView2);
        listView2.getLayoutParams().width = 1;
        time = 0;
        t = new Thread() {
        	
        	  @Override
        	  public void run() {
        	    try {
        	      while (!isInterrupted()) {
        	        Thread.sleep(1000);
        	        runOnUiThread(new Runnable() {
        	          @Override
        	          public void run() {
        	        	  TextView textViewToChange = (TextView) findViewById(R.id.timeLeft);
        	        	  if(isTimerRunning)
        	        		  time++;        	        		  
        	        	  textViewToChange.setText("Current time: "+time);
        	          }
        	        });
        	      }
        	    } catch (InterruptedException e) {
        	    }
        	  }
        	};

        	t.start();
        
        
	}
	//TIMER

	
	//TOP MENU
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_top_game, menu);
        return true;
    }
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection    	
        switch (item.getItemId()) {
            case R.id.pause:
            	
            	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
         
        			// set title
        			alertDialogBuilder.setTitle("The Game is paused");
        			isTimerRunning=false;
        			v.hide=true;
        			v.invalidate();
        			// set dialog message
        			alertDialogBuilder
        				.setMessage("Current time: "+time+" s")
        				.setCancelable(false).setNegativeButton("Continue Game",new DialogInterface.OnClickListener() {
        					@Override
							public void onClick(DialogInterface dialog,int id) {
        						dialog.cancel();
        						isTimerRunning=true;
        	        			v.hide=false;
        	        			v.invalidate();
        					}
        				});
        				// create alert dialog
        				AlertDialog alertDialog = alertDialogBuilder.create();
        				
        				// show it
        				alertDialog.show();
            	System.out.println("PAUSE !");
            	return true;
            
            case R.id.how:
            	showRules();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	public void showRules(){
        Toast.makeText(getApplicationContext(), 
                "All the rows & columns & 3x3 Squares should contain numbers 1 to 9", Toast.LENGTH_LONG).show();

	}
	//END TOP MENU
	
	 @Override
	 public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	    	super.onCreateContextMenu(menu, v, menuInfo);  
	        menu.setHeaderTitle("Value");  
	        menu.add(0, v.getId(), 0, "1");//groupId, itemId, order, title   
	        menu.add(0, v.getId(), 0, "2");  
	        menu.add(0, v.getId(), 0, "3");  
	        menu.add(0, v.getId(), 0, "4");  
	        menu.add(0, v.getId(), 0, "5");  
	        menu.add(0, v.getId(), 0, "6");  
	        menu.add(0, v.getId(), 0, "7");  
	        menu.add(0, v.getId(), 0, "8");  
	        menu.add(0, v.getId(), 0, "9");  
	        menu.add(0, v.getId(), 0, "delete");  
	}
	 
    @Override    
    public boolean onContextItemSelected(MenuItem item){
         CharSequence i = item.getTitle();  

    	if(i.toString().equals("delete")){
    		v.addNumber(0);
    		return true;
    	}
         int number = Integer.parseInt(i.toString());
         int res = v.addNumber(number);
         //if game finished
         if(res==2){
        	 //Saving the new High Score in the DB
             DatabaseHandler db = new DatabaseHandler(this);
             db.addScore(time);
             
             //Creating our Alert Programatically
        	 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
     		 alertDialogBuilder.setTitle("Congratulations");
 			 alertDialogBuilder
 				.setMessage("Current time: "+time+" s")
 				.setCancelable(false).setNegativeButton("Go back to main menu",new DialogInterface.OnClickListener() {
 					@Override
					public void onClick(DialogInterface dialog,int id) {
 						dialog.cancel();
 		                onBackPressed();
 					}
 			 });
 			// create alert dialog
 			AlertDialog alertDialog = alertDialogBuilder.create();
 			// show it
 			alertDialog.show();
         }
         return true;    
    }
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();   
    }
}
