package com.example.Smasher;

import com.example.Smasher.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

 

public class LayoutActivity extends Activity {

 
    final Context context =getApplicationContext();
    
    public void onCreate(Bundle savedInstanceState) {

    	 

        super.onCreate(savedInstanceState);
        
    }
    
    public void showCustomDialog() {
    	
    	if (context != null) {
    	 // Create custom dialog object
    	 final Dialog dialog = new Dialog(context);
    	 
    	 
    	//tell the Dialog to use the dialog.xml as it's layout description

         dialog.setContentView(R.layout.dialog);

         dialog.setTitle("Android Custom Dialog Box");



         TextView txt = (TextView) dialog.findViewById(R.id.txt);



         txt.setText("This is an Android custom Dialog Box Example! Enjoy!");



       Button dialogButton = (Button) dialog.findViewById(R.id.dialogButton);



         dialogButton.setOnClickListener(new OnClickListener() {

             @Override

             public void onClick(View v) {

                 dialog.dismiss();

             }

         });



         dialog.show();
    }
    }
}