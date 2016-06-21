package com.example.heeill.termproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class SearchActivity extends Activity implements Serializable{

    Dictionary dic = new Dictionary();
    String word; 


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.i("TermProject", "Search-Create()");

        final TextView txtview = (TextView)findViewById(R.id.txtview);
        final EditText edttxt = (EditText)findViewById(R.id.edttxt);
        Button btn_ins = (Button) findViewById(R.id.btn_ins);
        Button btn_search = (Button)findViewById(R.id.btn_search);
        Button btn_del = (Button)findViewById(R.id.btn_del);
        Button btn_return = (Button)findViewById(R.id.btn_return);

        btn_ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word = edttxt.getText().toString(); //사용자가 입력한 단어
                Intent intent = new Intent(getApplicationContext(),
                        InsertActivity.class);

                Log.i("TermProject","Insert : " + word);

                if(dic.Search(word) == null) { //입력한 단어가 사전에 존재하지 않을 때
                    startActivityForResult(intent,0); //새로운 액티비티를 시작해서 사용자가 의미까지 입력하도록 한다.
                }
                else{
                    txtview.setText("이미 존재하는 단어입니다.");
                }
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                word = edttxt.getText().toString();
                String mean =dic.Search(word);
                Log.i("TermProject","Search : " + word);

                if(mean==null)
                    txtview.setText("단어장에 존재하지 않는 단어입니다. \n추가하고자 한다면 아래 '추가'버튼을 눌러주세요");
                else
                    txtview.setText("의미 : " + mean);
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                word = edttxt.getText().toString();
                Log.i("TermProject","Delete : " + word);

                if(dic.Delete(word) == true)
                    txtview.setText(word + "가 단어장에서 삭제되었습니다.");
                else
                    txtview.setText(word + "가 단어장에 존재하지 않습니다");
            }
        });


        btn_return.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

    }

    @Override
    //사전에 존재하지 않는 단어 체크는 이미 되어있음
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            final TextView txtview = (TextView)findViewById(R.id.txtview);
            Log.i("TermProject","getResult");
            String mean = data.getStringExtra("의미");

            txtview.setText(word + "가 단어장에 추가되었습니다.");
            dic.Insert(word,mean);
        }
    }


}
