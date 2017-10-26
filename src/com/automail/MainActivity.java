package com.automail;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	String arra[] = {"krishna.intech@gmail.com"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	
		Button addImage = (Button) findViewById(R.id.button1);
		addImage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				
				
				/*try {   
                    GMailSender sender = new GMailSender("justmailtokrish@gmail.com", "feelmy5220");
                    sender.sendMail("This is Subject",   
                            "This is Body",   
                            "krishna.cyprus@gmail.com",   
                            "krishna.intech@gmail.com");   
                } catch (Exception e) {   
                    Log.e("SendMail", e.getMessage(), e);   
                }*/ 

				

				/*try {   
					GMailSender sender = new GMailSender("justmailtokrish@gmail.com", "feelmy5220");
					sender.sendMail("Test subject",   
							"Hi this is testing",   
							"krishna.cyprus@gmail.com",   
							"krishna.intech@gmail.com","");   
					//sender.addAttachment("/sdcard/cut.jpg");
				} catch (Exception e) {   
					Log.e("SendMail", e.getMessage(), e);   
				} */


				
				
				
				
				
				Mail m = new Mail("justmailtokrish@gmail.com", "feelmy5220");
				String[] toArr = {"krishna.intech@gmail.com", "krishna.cyprus@gmail.com"};
				m.setTo(toArr);
				m.setFrom("justmailtokrish@gmail.com");
				m.setSubject("This is an email sent using my Mail JavaMail wrapper from an Android device.");
				m.setBody("Email body.");
				try {
					m.addAttachment("/sdcard/untitled.png");
					if(m.send()) {
						Toast.makeText(getApplicationContext(), "Email was sent successfully.", Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(getApplicationContext(), "Email was not sent.", Toast.LENGTH_LONG).show();
					}
				} catch(Exception e) {
					//Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
					Log.e("MailApp", "Could not send email", e);
				}
			}
		});
	}
	
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
