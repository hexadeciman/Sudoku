package com.example.sudoku;

import android.app.Activity;
<<<<<<< HEAD
import android.content.Intent;
=======
>>>>>>> 021d4d72e30b84f27d6c4f0414683143d362bd89
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
<<<<<<< HEAD
import android.widget.EditText;
=======
import android.widget.ArrayAdapter;
import android.widget.GridView;
>>>>>>> 021d4d72e30b84f27d6c4f0414683143d362bd89
import android.widget.ListView;

public class SudukuMain extends Activity {
	//Constants for context menu options
	public static final int MENU_MARK = 1;
	public static final int MENU_REMOVE = 2;
	ListView listView1 ;
	int level ;
    
<<<<<<< HEAD
	public static int INTENT_LEVEL = 1;
=======
>>>>>>> 021d4d72e30b84f27d6c4f0414683143d362bd89
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suduku_main);
        
<<<<<<< HEAD
=======

>>>>>>> 021d4d72e30b84f27d6c4f0414683143d362bd89
    	listView1 = (ListView) findViewById(R.id.listView1);
        // Register the ListView  for Context menu  
        registerForContextMenu(listView1);
    }

<<<<<<< HEAD
    
	/** Called when the user clicks the Start button */
	public void start(View view) {
		Intent intent = new Intent(this, GridSudoku.class);
		intent.putExtra("level", (int)level);
		startActivity(intent);
	}
	
	@Override
=======
    @Override
>>>>>>> 021d4d72e30b84f27d6c4f0414683143d362bd89
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_top_home, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
<<<<<<< HEAD
        // Handle item selection    	
=======
        // Handle item selection
    	
>>>>>>> 021d4d72e30b84f27d6c4f0414683143d362bd89
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.about:
            	return true;
            case R.id.difficulty:
           	 openContextMenu(listView1);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);  
        menu.setHeaderTitle("Difficulty");  
        menu.add(0, v.getId(), 0, "Easy");//groupId, itemId, order, title   
        menu.add(0, v.getId(), 0, "Medium");  
        menu.add(0, v.getId(), 0, "Hard");  
    }
    
<<<<<<< HEAD
    
    
    @Override    
    public boolean onContextItemSelected(MenuItem item){    
            if(item.getTitle()=="Easy"){  
                level=1;
=======
    @Override    
    public boolean onContextItemSelected(MenuItem item){    
            if(item.getTitle()=="Easy"){  
                  level=1;
>>>>>>> 021d4d72e30b84f27d6c4f0414683143d362bd89
            }    
            else if(item.getTitle()=="Medium"){  
                level=2;

            }
            else if(item.getTitle()=="Hard"){  
                level=3;

            }else{  
               return false;  
            }
            
          return true;    
      }    
    
}
