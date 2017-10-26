package com.automail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends Activity 
{
	Button getemailid;
	String[] user_emailid;
	EditText user_emailid1;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.input_layout);
		getemailid=(Button)findViewById(R.id.email_button);
		getemailid.setOnClickListener(emailidlistener);
	}
	
	OnClickListener emailidlistener = new OnClickListener() 
	{
		@Override
		public void onClick(View v) 
		{
			Intent move=new Intent(InputActivity.this,FileexplorerActivity.class);
			startActivity(move);
			/*user_emailid1=(EditText)findViewById(R.id.entered_emailid);
			user_emailid[0]=user_emailid1.toString();
			Session.emailid=user_emailid;*/
			
			
		}
	};
}
