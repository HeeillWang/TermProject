package com.example.heeill.termproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Dictionary dic = new Dictionary();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_search_act = (Button) findViewById(R.id.btn_search_act);
        Button btn_quiz_act = (Button)findViewById(R.id.btn_quiz_act);
        Button btn_word_act = (Button)findViewById(R.id.btn_word_act);
        Button btn_set_act = (Button)findViewById(R.id.btn_set_act);

        btn_search_act.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent);
            }
        });

        btn_quiz_act.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),QuizActivity.class);
                startActivity(intent);
            }
        });

        btn_word_act.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),ViewActivity.class);
                startActivity(intent);
            }
        });

        btn_set_act.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),SettingActivity.class);
                startActivity(intent);
            }
        });

    }
}
