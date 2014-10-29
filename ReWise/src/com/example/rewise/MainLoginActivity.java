package com.example.rewise;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;


public class MainLoginActivity extends ActionBarActivity {
	Button btn_instr_login, btn_stu_login, btn_login, btn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        
        //INITIALIZING VIEW FIELDS
        btn_instr_login=(Button)findViewById(R.id.btn_instr_login);
        btn_stu_login=(Button)findViewById(R.id.btn_stu_login);
        btn_signup=(Button)findViewById(R.id.btn_signup);
        
        //ONCLICK LISTENERS
        
        btn_instr_login.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		Intent i = new Intent(getApplicationContext(), InstrLoginActivity.class); 
        		startActivity(i);
        	}
        });
        
        btn_stu_login.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		Intent i = new Intent(getApplicationContext(), StuLoginActivity.class); 
        		startActivity(i);
        	}
        });
        
        btn_signup.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		Intent i = new Intent(getApplicationContext(), SignupActivity.class); 
        		startActivity(i);
        	}
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
