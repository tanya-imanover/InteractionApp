package com.ti.interactionapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.openAppBtn);

        //Add OnClickListener for your button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get launch intent of your package - do not forget
                // add name of package you want to open, you can find it in AndroidManifest.xml file
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.ti.interactionapp");
                //If intent is null it means that app is not exist, we check this
                if(intent != null){
                    //if app exist on your device, it will open
                    startActivity(intent);
                }else {
                    //if it's not, we start dialog
                    showUpdateDialog();
                }
            }
        });
    }
    private void showUpdateDialog(){

        //We create alert dialog builder
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.not_installed_title);
        builder.setMessage(R.string.not_installed_message);

        //set positive button, it will open link on your app on GooglePlay
        builder.setPositiveButton(R.string.download_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        //Do not forget add link on your application
                        Uri.parse("https://play.google.com/store")));
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.download_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
