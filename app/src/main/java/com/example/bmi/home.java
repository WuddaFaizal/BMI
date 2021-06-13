package com.example.bmi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class home extends Activity {

    Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.btnG).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_btn("https://github.com/Inikimi/BMI_Calculator");

            }
        } );


        button = (Button) findViewById(R.id.btnC);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void clicked_btn(String url){
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);     }

    public void openCalculator(){
        Intent intent = new Intent(this, BMICalcUtil.class);
        startActivity(intent);
    }

    public void openC(){

    }
}