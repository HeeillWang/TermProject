package com.example.heeill.termproject;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SettingActivity extends AppCompatActivity{

    RadioGroup search_rad_font;
    RadioGroup wordbook_rad_font;
    RadioGroup quiz_rad_font;
    RadioGroup quiz_rad_repeat;
    Button save;
    Button cancel;
    Setting sett = new Setting();


    /*
    데이터를 파일로 저장하는 함수
     */
    private void saveFile()
    {
        try {
            FileOutputStream file = openFileOutput(sett.filename, Context.MODE_PRIVATE);
            BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(file));

            sett.fileSave(buffer);

            try {
                buffer.close();
                file.close();
            } catch (IOException e) {
                Log.i("TermProject","savefile close error");
            }


        } catch (FileNotFoundException e) {
            Log.i("TermProject","savefile open error");
        }
    }

    /*
    설정한 데이터를 저장하는 함수
     */
    private void saveData()
    {
        switch (search_rad_font.getCheckedRadioButtonId())
        {
            case R.id.Set_rad_search_font_small:
                sett.search_font = font_size.S;
                break;
            case R.id.Set_rad_search_font_midium:
                sett.search_font = font_size.M;
                break;
            case R.id.Set_rad_search_font_large:
                sett.search_font = font_size.L;
                break;
        }
        switch (wordbook_rad_font.getCheckedRadioButtonId())
        {
            case R.id.Set_rad_wordbook_font_small:
                sett.wordbook_font = font_size.S;
                break;
            case R.id.Set_rad_wordbook_font_midium:
                sett.wordbook_font = font_size.M;
                break;
            case R.id.Set_rad_wordbook_font_large:
                sett.wordbook_font = font_size.L;
                break;
        }
        switch (quiz_rad_font.getCheckedRadioButtonId())
        {
            case R.id.Set_rad_quiz_font_small:
                sett.quiz_font = font_size.S;
                break;
            case R.id.Set_rad_quiz_font_midium:
                sett.quiz_font = font_size.M;
                break;
            case R.id.Set_rad_quiz_font_large:
                sett.quiz_font = font_size.L;
                break;
        }
        switch (quiz_rad_repeat.getCheckedRadioButtonId())
        {
            case R.id.Set_rad_quiz_repeat_1:
                sett.quiz_repeat = 1;
                break;
            case R.id.Set_rad_quiz_repeat_2:
                sett.quiz_repeat = 2;
                break;
            case R.id.Set_rad_quiz_repeat_3:
                sett.quiz_repeat = 3;
                break;
            case R.id.Set_rad_quiz_repeat_4:
                sett.quiz_repeat = 4;
                break;
            case R.id.Set_rad_quiz_repeat_5:
                sett.quiz_repeat = 5;
                break;
        }

        saveFile();
    }

    /*
    기존 설정을 setting액티비티에 띄워주는 함수
     */
    public void setting_apply() {
        search_rad_font = (RadioGroup) findViewById(R.id.Set_radg_search_font);
        wordbook_rad_font = (RadioGroup) findViewById(R.id.Set_radg_wordbook_font);
        quiz_rad_font = (RadioGroup) findViewById(R.id.Set_radg_quiz_font);
        quiz_rad_repeat = (RadioGroup) findViewById(R.id.Set_radg_quiz_repeat);
        save = (Button) findViewById(R.id.Set_btn_save);
        cancel = (Button) findViewById(R.id.Set_btn_cancel);

        switch (sett.search_font) {
            case S:
                search_rad_font.check(R.id.Set_rad_search_font_small);
                break;
            case M:
                search_rad_font.check(R.id.Set_rad_search_font_midium);
                break;
            case L:
                search_rad_font.check(R.id.Set_rad_search_font_large);
                break;
        }

        switch (sett.wordbook_font) {
            case S:
                wordbook_rad_font.check(R.id.Set_rad_wordbook_font_small);
                break;
            case M:
                wordbook_rad_font.check(R.id.Set_rad_wordbook_font_midium);
                break;
            case L:
                wordbook_rad_font.check(R.id.Set_rad_wordbook_font_large);
                break;
        }

        switch (sett.quiz_font) {
            case S:
                quiz_rad_font.check(R.id.Set_rad_quiz_font_small);
                break;
            case M:
                quiz_rad_font.check(R.id.Set_rad_quiz_font_midium);
                break;
            case L:
                quiz_rad_font.check(R.id.Set_rad_quiz_font_large);
                break;
        }

        switch (sett.quiz_repeat) {
            case 1:
                quiz_rad_repeat.check(R.id.Set_rad_quiz_repeat_1);
                break;
            case 2:
                quiz_rad_repeat.check(R.id.Set_rad_quiz_repeat_2);
                break;
            case 3:
                quiz_rad_repeat.check(R.id.Set_rad_quiz_repeat_3);
                break;
            case 4:
                quiz_rad_repeat.check(R.id.Set_rad_quiz_repeat_4);
                break;
            case 5:
                quiz_rad_repeat.check(R.id.Set_rad_quiz_repeat_5);
                break;
        }

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Log.i("TermProject","setting open");
        setting_apply();
        Log.i("TermProject","setting open2");

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveData();
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }

}
