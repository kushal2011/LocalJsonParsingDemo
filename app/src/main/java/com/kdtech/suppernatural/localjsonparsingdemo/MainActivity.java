package com.kdtech.suppernatural.localjsonparsingdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void series(View view) {
        //passing value 0 with intent so we can know in next activity that the person clicked on series button
        int position = 0;
        Bundle detail=new Bundle();
        detail.putInt("pos",position);
        Intent intent = new Intent(MainActivity.this,jsonActivity.class);
        intent.putExtras(detail);
        startActivity(intent);
    }

    public void food(View view) {
        //passing value 1 with intent so we can know in next activity that the person clicked on food button
        int position = 1;
        Bundle detail=new Bundle();
        detail.putInt("pos",position);
        Intent intent = new Intent(MainActivity.this,jsonActivity.class);
        intent.putExtras(detail);
        startActivity(intent);
    }
}
