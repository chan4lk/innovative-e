package com.iepl.pathapp.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * This class manages alerts.
 */
public class AlertDialogManager {
	
	
	/**
	 * Show message dialog.
	 */
	public void showMessageDialog(Context context, String title, String message,
			Boolean status )
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(context); // activity
		  builder.setTitle(title)
		  .setMessage(message)
		  .setCancelable(false)
		  .setIcon((status)?android.R.drawable.ic_dialog_dialer:android.R.drawable.ic_dialog_alert)
		  .setNegativeButton("Close",new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int id) {
		          dialog.cancel();
		      }
		  });
		  AlertDialog alert = builder.create();
		  alert.show();
	}
}
