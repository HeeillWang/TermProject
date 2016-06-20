package com.example.heeill.termproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Set;

public class ViewActivity extends Activity {

    Dictionary dic = new Dictionary();
    Integer index = 0; // 단어가 저장되어 있는 순서

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Log.i("TermProject","ViewActivity");

        final TextView text_word = (TextView)findViewById(R.id.txtview_word); // 단어 표시
        final TextView text_meaning = (TextView)findViewById(R.id.txtview_meaning); // 뜻 표시
        Button btn_before = (Button)findViewById(R.id.btn_before); // 이전 버튼
        Button btn_next = (Button)findViewById(R.id.btn_next); // 다음 버튼
        Button btn_return = (Button)findViewById(R.id.btn_return);

        //Intent intent = getIntent();
        //String[] wordString1 = intent.getStringArrayExtra("wordString");
        //String[] meaningString1 = intent.getStringArrayExtra("meaningString");

        /*for(int i=0;i<wordString1.length;i++) // 단어와 뜻 받아온 후 dic에 저장
        {
            dic.Insert(wordString1[i], meaningString1[i]);
        }*/

        // 이전 단어를 보는 데 사용

        btn_before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // index가 사전의 크기와 맞을 때에는 이전 단어를 보여주지만
                // 아닐 때에는 그 전에 보여줬던 마지막 단어를 보여줌

                if((index > 1) && (index <= dic.ReturnSize())) {
                    index = index - 1;
                    String wordTemp = dic.ReturnWord(index);
                    text_word.setText(wordTemp);
                    text_meaning.setText(dic.Search(wordTemp));
                }
                else
                {
                    String wordTemp = dic.ReturnWord(index);
                    text_word.setText(wordTemp);
                    text_meaning.setText(dic.Search(wordTemp));
                }
            }
        });

        // 다음 단어를 보는 데 사용

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // index가 사전의 크기와 맞을 때에는 다음 단어를 보여주지만
                // 아닐 때에는 그 전에 보여줬던 마지막 단어를 보여줌

                if((index >= 0) && (index < dic.ReturnSize())) {
                    index = index + 1;
                    String wordTemp = dic.ReturnWord(index);
                    text_word.setText(wordTemp);
                    text_meaning.setText(dic.Search(wordTemp));
                }
                else
                {
                    String wordTemp = dic.ReturnWord(index);
                    text_word.setText(wordTemp);
                    text_meaning.setText(dic.Search(wordTemp));
                }
            }
        });

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
