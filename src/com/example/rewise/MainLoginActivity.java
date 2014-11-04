package com.example.rewise;

import com.google.android.gms.common.AccountPicker;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;


public class MainLoginActivity extends Activity {
	
	static final int REQUEST_CODE_PICK_ACCOUNT_INSTR = 1000;
	static final int REQUEST_CODE_PICK_ACCOUNT_STU = 1001;
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
        		
        		if (isNetworkAvailable() == true) {
        			pickUserAccount(REQUEST_CODE_PICK_ACCOUNT_INSTR);
        		} else {
        			Toast.makeText(MainLoginActivity.this, "No Network Service!",
        					Toast.LENGTH_SHORT).show();
        		}
        		
        	}
        });
        
        btn_stu_login.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		if (isNetworkAvailable() == true) {
        			pickUserAccount(REQUEST_CODE_PICK_ACCOUNT_STU);
        		} else {
        			Toast.makeText(MainLoginActivity.this, "No Network Service!",
        					Toast.LENGTH_SHORT).show();
        		}
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
        
        Button btn_quiz=(Button)findViewById(R.id.gotoQuiz);
        btn_quiz.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),AttemptQuizActivity.class));
			}
		});
    }


    private void pickUserAccount(int requestCode) {
        String[] accountTypes = new String[]{"com.google"};
        Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                accountTypes, false, null, null, null, null);
        startActivityForResult(intent, requestCode);
    }
    
    public boolean isNetworkAvailable() {
    	ConnectivityManager cm = (ConnectivityManager) (MainLoginActivity.this).getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkInfo = cm.getActiveNetworkInfo();
    	if (networkInfo != null && networkInfo.isConnected()) {
    		Log.e("Network Testing", "***Available***");
    		return true;
    	}
    	Log.e("Network Testing", "***Not Available***");
    	return false;
    }



    String mEmail; // Received from newChooseAccountIntent(); passed to getToken()

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == REQUEST_CODE_PICK_ACCOUNT_INSTR || requestCode == REQUEST_CODE_PICK_ACCOUNT_STU) {
    		// Receiving a result from the AccountPicker
    		if (resultCode == RESULT_OK) {
    			mEmail = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
    			if(requestCode == REQUEST_CODE_PICK_ACCOUNT_INSTR) {
        			Intent i = new Intent(getApplicationContext(), InstrMainActivity.class);
        			i.putExtra("email_id", mEmail);
        			startActivity(i);
        			//Toast.makeText(MainLoginActivity.this, "mEmail is "+mEmail, Toast.LENGTH_SHORT).show();
        			// With the account name acquired, go get the auth token
        			//getUsername(); //create this function if needed
    			} else {
    				Intent i = new Intent(getApplicationContext(), StuLoginActivity.class);
        			i.putExtra("email_id", mEmail);
        			startActivity(i);
    			}
    		} else if (resultCode == RESULT_CANCELED) {
    			// The account picker dialog closed without selecting an account.
    			// Notify users that they must pick an account to proceed.
    			Toast.makeText(this, "Pick an account!", Toast.LENGTH_SHORT).show();
    		}
    	}
    	// Later, more code will go here to handle the result from some exceptions...
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
