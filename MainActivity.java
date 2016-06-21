package com.example.heeill.termproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Set;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    Dictionary dic = new Dictionary();
    String filename = "myfile.txt";

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("TermProject","Main-OnDestroy");
        FileOutputStream fos;
        try{
            fos = openFileOutput(filename, Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

            dic.Save(writer);

            writer.close();
            fos.close();
        }catch(Exception e){
            Log.i("TermProject","File Save Error");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("TermProject","Main-OnCreate");

        Button btn_search_act = (Button) findViewById(R.id.btn_search_act);
        Button btn_quiz_act = (Button)findViewById(R.id.btn_quiz_act);
        Button btn_word_act = (Button)findViewById(R.id.btn_word_act);
        Button btn_set_act = (Button)findViewById(R.id.btn_set_act);


        try{
            FileInputStream fin = openFileInput(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
            dic.Load(reader); //이전에 저장된 파일을 읽어들임

            fin.close();
            reader.close();

        }catch(Exception e){ //이전에 저장한 파일이 없는 경우
            Log.i("TermProject","Main - File Load Error");
        }




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
