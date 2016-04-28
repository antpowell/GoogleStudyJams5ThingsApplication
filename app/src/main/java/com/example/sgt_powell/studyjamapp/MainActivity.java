package com.example.sgt_powell.studyjamapp;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ActionViewTarget;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button toastBtn = (Button) findViewById(R.id.toastBtn);
        Button alertDialogBtn = (Button) findViewById(R.id.alertDialogBtn);
        Button showCaseViewBtn = (Button) findViewById(R.id.showCaseViewBtn);
        Button notificatinBtn = (Button) findViewById(R.id.notificationBtn);
        Button customDialogBtn = (Button) findViewById(R.id.customDialogBtn);


//        --------------->Example of a Toast<---------------
//        A toast provides simple feedback about an operation in a small popup. It only fills the amount of space required for the message and the current activity remains visible and interactive.
        toastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.toastTxt, Toast.LENGTH_LONG).show();
            }
        });
//        --------------->Example of an basic AlertDialog<---------------
//        A dialog is a small window that prompts the user to make a decision or enter additional information. A dialog does not fill the screen and is normally used for modal events that require users to take an action before they can proceed.
//        A subclass of Dialog that can display one, two or three buttons.
        alertDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.alertTitle)
                        .setCancelable(false)
                        .setMessage(R.string.alertTxt)
                        .setPositiveButton(R.string.alertOKBtnTxt, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).create().show();
            }
        });
//        --------------->Example of a Showcase View<---------------
//        The ShowcaseView (SCV) library is designed to highlight and showcase specific parts of apps to the user with a distinctive and attractive overlay. This library is great for pointing out points of interest for users, gestures, or obscure but useful items.
        showCaseViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShowcaseView.Builder(MainActivity.this)
//                        .setTarget(new ActionViewTarget(MainActivity.this, ActionViewTarget.Type.HOME))
                        .setTarget(new ViewTarget(findViewById(R.id.showCaseViewBtn)))
                        .setContentTitle(getString(R.string.showCaseViewTitle))
                        .setContentText(getString(R.string.showCaseViewTxt))
                        .hideOnTouchOutside().build();
            }
        });
        //        --------------->Example of a Notification<---------------
//        A notification is a message you can display to the user outside of your application's normal UI. When you tell the system to issue a notification, it first appears as an icon in the notification area.
        notificatinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.ic_speaker_notes_white_24dp)
                        .setContentTitle(getString(R.string.notificationTitle))
                        .setContentText(getString(R.string.notificationTxt))
                        .setAutoCancel(true);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(001, notificationBuilder.build());
                Toast.makeText(MainActivity.this, R.string.notificationToast, Toast.LENGTH_SHORT).show();
            }
        });
//        --------------->Example of a custom AlertDialog<---------------
//        Achieve a custom AlertDialog by creating a layout and add it to an AlertDialog by calling setView() on your AlertDialog.Builder object.
        customDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.alert_dialog_layout, null);
//                Get the radio buttons from the inflated View NOTE: must find the id from the inflated view to get the right element
                final RadioButton yesRadioBtn = (RadioButton) view.findViewById(R.id.yesRadioBtn);
                final RadioButton noRadioBtn = (RadioButton) view.findViewById(R.id.noRadioBtn);
//                Show the custom layout in AlertDialog
                dialogBuilder.setView(view)

                        .setPositiveButton(R.string.customAlertDialogOkBtnTxt, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean yesIsChecked = yesRadioBtn.isChecked();
                                boolean noIsChecked = noRadioBtn.isChecked();
                                if (yesIsChecked) {
                                    Toast.makeText(MainActivity.this, R.string.customAlertDialogYesTxt, Toast.LENGTH_LONG).show();
                                }else if(noIsChecked){
                                    Toast.makeText(getApplicationContext(), R.string.customAlertDialogNoTxt, Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(MainActivity.this, R.string.customAlertNoSelectionTxt, Toast.LENGTH_SHORT).show();
                                }
                                dialog.cancel();
                            }
                        })
                        .setCancelable(false).create().show();
            }
        });
    }

}