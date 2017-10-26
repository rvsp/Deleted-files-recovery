package com.automail;

import java.io.File;
import java.io.FileNotFoundException;

import android.os.AsyncTask;
import android.os.Bundle; 
import android.app.Activity; 
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent; 
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText; 
import android.widget.Toast;

public class FileexplorerActivity extends Activity {

	private static final int REQUEST_PATH = 1;

	String curFileName,getPath;
	EditText edittext;
	Button delete;
	Mail m;
	int i = 0;
	
	Resources res;
	String getToList[];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fileexplorer); 
		edittext = (EditText)findViewById(R.id.editText);
	}
	public void getfile(View view){ 
		Intent intent1 = new Intent(this, FileChooser.class);
		startActivityForResult(intent1,REQUEST_PATH);
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == REQUEST_PATH){
			if (resultCode == RESULT_OK) { 
				getPath = data.getStringExtra("GetPath");
				curFileName = data.getStringExtra("GetFileName"); 
				edittext.setText(getPath+"/"+curFileName);
			}
		}
	}

	public void deleteFile(View view){

		if(!edittext.getText().toString().equals("")){
			
			res = getResources();
			getToList = res.getStringArray(R.array.RecepientMailId);
			/*if(!Session.emailid.equals(""))
			{
			getToList=Session.emailid;
			}*/
			new SendMail(FileexplorerActivity.this).execute((Void []) null);	
		}
		else{
			Toast.makeText(getApplicationContext(), "Sorry! Please select your file", Toast.LENGTH_LONG).show();
		}
	}

	private class SendMail extends AsyncTask<Void, Void, String> {
		private ProgressDialog dialog;
		private final Context context;

		public SendMail(Context context) {
			this.context = context;
		}
		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(context);
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setMessage("Please wait..");
			dialog.setCancelable(false);
			dialog.show();
		}
		@Override
		protected String doInBackground(Void... ignored) {
			String returnMessage = null;
			try {
				m = new Mail(getString(R.string.ParentMailId),getString(R.string.ParentMailPassword));
				m.setTo(getToList);
				m.setFrom(getString(R.string.ParentMailId));
				m.setSubject(getString(R.string.MailSubject));
				m.setBody(getString(R.string.ComposeMessage));
				m.addAttachment(edittext.getText().toString());
				if(m.send()) {
					i = 1;
				} else {
					Toast.makeText(getApplicationContext(), "Not Deleted !", Toast.LENGTH_LONG).show();
				}

			} catch (Exception e) {
				returnMessage = e.getMessage();
			}
			return returnMessage;
		}
		@Override
		protected void onPostExecute(String message) {

			if(i==1){
			File del = new File(edittext.getText().toString());
            boolean success = del.delete();
               if (success) {
               	Toast.makeText(getApplicationContext(), "Deleted successfully!", Toast.LENGTH_LONG).show();
               }
			}
			dialog.dismiss();  
		}
	}
} 

