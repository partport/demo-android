package com.example.systempause.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by SystemPause on 4/14/2016.
 */
public class page2 extends AppCompatActivity {
    int number =0;
    boolean hold = false;
    TextView tv1 ;
    Handler hd = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        Button btn1 = (Button) findViewById(R.id.holdbtn);
        tv1 = (TextView) findViewById(R.id.tvshow);

       tv1.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               if(event.getAction() == MotionEvent.ACTION_DOWN){
                   isPress();
               }else if(event.getAction() == MotionEvent.ACTION_UP){
                   unPress();
               }
               return true;
           }
       });

    }

    private void isPress(){
        hold = true;


        hd.post(new Runnable() {
            @Override
            public void run() {
                if (hold){
                    number++;
                    final Toast to = Toast.makeText(getApplicationContext(),String.valueOf(number),Toast.LENGTH_SHORT);
                    to.show();
                    Handler hand = new Handler();
                    hand.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            to.cancel();
                        }
                    },800);


                    Log.d("##",String.valueOf(number));
                    if(number == 8){
                        AlertDialog.Builder ad = new AlertDialog.Builder(page2.this);
                        ad.setTitle("COUNT TO NUMBER"+number);
                        ad.setMessage("ABCDEF");
                        ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        ad.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        ad.show();
                    }
                    hd.postDelayed(this,1000);
                }
            }
        });
    }
    private  void unPress(){
        hold = false;
        number=0;
    }
}
