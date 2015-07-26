package com.example.kqulo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Thread t = new Thread(){
			public void run(){
				try{
					sleep(2000);
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					Intent i = new Intent(MainActivity.this,Login.class);
					startActivity(i);
				}
			}
		};
		
		t.start();
	}

	
}
