package com.example.heeill.termproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        final Intent intent = getIntent();

        Log.i("TermProject","InsertActivity");

        Button btn_confirm = (Button)findViewById(R.id.btn_confirm);


        btn_confirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                returnResult();
            }
        });
    }

    private void returnResult() {
        final EditText edttxt2 = (EditText)findViewById(R.id.edttxt2);
        Intent result = new Intent(getApplicationContext(),
                SearchActivity.class);
        result.putExtra("의미", edttxt2.getText().toString());
        this.setResult(RESULT_OK, result);
        this.finish();
    }

}

