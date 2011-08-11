package org.kyoss.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Main extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Set the UI to display the layout defined in layout/main.xml
		setContentView(R.layout.main);

		// Retrieve a reference to the KYOSS logo from the layout which is defined in layout/main.xml
		ImageView kyossLogo = (ImageView)findViewById(R.id.kyossLogo) ;

		// Retrieve a reference to the "Start Another Activity" button defined in the layout/main.xml
		Button activityButton = (Button)findViewById(R.id.newActivityButton) ;

		// Add an onClick event listener to the button
		activityButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// This method is called when the user clicks on the "Start Another Activity" button.
				// To start another Activity which we wrote, we will create a new Intent
				// The new intent is saying that within this application (getApplicationContext) we would
				// like to start the Activity described in the class ExampleActivity
				Intent newActivityIntent = new Intent(getApplicationContext(), ExampleActivity.class) ;

				// We can also send data to the new activity by using what are referred to as "Extras"
				newActivityIntent.putExtra("source", "Started from Main!") ;
				newActivityIntent.putExtra("anIntValue", 5) ;
				newActivityIntent.putExtra("aLongValue", 60L) ;
				newActivityIntent.putExtra("aBooleanValue", true) ;

				// Once the Intent is configured, we can simply start that activity
				Main.this.startActivity(newActivityIntent) ;
				// Starting the new activity will cause the onPause() method of this activity to be called
			}
		}) ;

		// Add an onClick() listener to the logo image
		kyossLogo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// If we are here, the user has clicked on the KYOSS logo and we should show them 
				// a dialog explaining that. The Dialog builder requires a reference to the parent
				// object's context, so we will pass Main (the name of the parent class) dot "." this
				// in order to show that we are referring to the instance of Main which we are
				// running inside of
				AlertDialog.Builder builder = new AlertDialog.Builder(Main.this) ;

				// NOTE: The dialog will not be displayed to the user until you make a call to
				// builder.show()

				// Set the title of the dialog using a string defined in values/strings.xml
				builder.setTitle(R.string.logo_click_dialog_title) ;

				// The the message body for the dialog, also using a string defined in values/strings.xml
				builder.setMessage(R.string.logo_click_dialog_message) ;

				// Let's add an "OK" button to the dialog and assign an action to that button
				builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// The user clicked "OK", so we will start a new Activity by using an Intent. Intents are the way that
						// you tell Android that you would like to do something new. In this case, we will use a built-in 
						// ACTIVITY_SERVICE to open a web site for us, using the default browser for the device.
						dialog.dismiss() ;
						Main.this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kyoss.org/"))) ;
						
						// NOTE: Remember, just because you launched a new Activity; even if it is not part of your app, 
						// hitting the Back buttion will bring you back to this calling activity with the same state!!
					}
				}) ;

				// Let's add a "Cancel" button to the dialog, and set it so that when it is clicked, the 
				// dialog will be dismissed
				builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss() ;
					}
				}) ;

				// And now that we have finished configuring the dialog, we call builder.show() to
				// display the dialog to the user.
				builder.show() ;
			}
		}) ;
	}
}