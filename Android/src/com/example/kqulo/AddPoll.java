package com.example.kqulo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPoll extends Activity{
	
	EditText pn,pl;
	Button addp;
	URL url;
	String res;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#263238")));
		bar.setTitle("Add Poll");
		
		setContentView(R.layout.add_poll);
		
		pn = (EditText) findViewById(R.id.poll_name);
		pl = (EditText) findViewById(R.id.poll_loc);
		addp = (Button) findViewById(R.id.add_p);
		
		addp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String pollName = pn.getText().toString();
				final String pollLoc = pl.getText().toString();
				
				Thread t = new Thread(){
					public void run(){
						try{
							url = new URL("https://pollingcube.herokuapp.com/new_poll/abc/"+pollName+"/"+pollLoc);

							try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
							    for (String line; (line = reader.readLine()) != null;) {
							        //System.out.println(line);
							        res += line;
							    }
							    //System.out.println("Status : "+res);
							    if(res.equals("null1")){
							    	System.out.println("Status : "+res);
							    	runOnUiThread(new Runnable() {
							    	    public void run() {
							    	    	Toast.makeText(getApplicationContext(), "Poll Added!! :-D", Toast.LENGTH_LONG).show();
							    	    }
							    	});
							    	
							    }else{
							    	System.out.println("Status : "+res);
							    	runOnUiThread(new Runnable() {
							    	    public void run() {
							    	    	Toast.makeText(getApplicationContext(), "Error Adding Poll!! :-(", Toast.LENGTH_LONG).show();
							    	    }
							    	});
							    	
							    }
							}
							
						}catch(Exception e){
							e.printStackTrace();
						}
						}
					
				};
				
				t.start();
			}
		});
	}

	
	
}
