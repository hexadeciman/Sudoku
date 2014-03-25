package com.example.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Main Activity (Start Screen Activity)
 */
public class MainActivity extends Activity {
	
	//Constants for context menu options
	public static final int MENU_MARK = 1;
	public static final int MENU_REMOVE = 2;
	ListView listView1 ;
	int level ;
    
	public static int INTENT_LEVEL = 1;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suduku_main);
        listView1 = (ListView) findViewById(R.id.listView1);
    	
        
    	registerForContextMenu(listView1);
		
    }

	/** Called when the user clicks the Start button */
	public void start(View view) {
		Intent intent = new Intent(this, SudokuActivity.class);
		intent.putExtra("level", level);
		startActivity(intent);
	}
	public void showAbout(){
        Toast.makeText(getApplicationContext(), 
                "Created by Yassine, with love.", Toast.LENGTH_LONG).show();

	}
	/** Called when the user clicks the HighScore button */
	public void showHighScore(View view) {
		// Getting the db handler
        DatabaseHandler db = new DatabaseHandler(this);
        
        //Uncomment this to purge the DataBase.
        // db.purge();
        
        List<Integer> scores = new ArrayList<Integer>();
        scores = db.getAllScores();
        
        //Sorting the scores (to get the HighScores)
        Collections.sort(scores);
        List<String> listDisplay = new ArrayList<String>();
        int i = 0;
        for(Integer s : scores){
        	i++;
        	listDisplay.add(i+": "+s+" secondes");
        	//Displaying 5 scores only
        	if(i==5){
        		break;
        	}
        }

        //Showing the highScores alert
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.custom, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle("HighScores");
        ListView lv = (ListView) convertView.findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listDisplay);
        lv.setAdapter(adapter);
        alertDialog.show();
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_top_home, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection    	
        switch (item.getItemId()) {
            case R.id.about:
            	showAbout();
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
    
    @Override    
    public boolean onContextItemSelected(MenuItem item){    
            if(item.getTitle()=="Easy"){  
                level=1;
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
