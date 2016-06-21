package com.example.heeill.termproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class LockScreenActivity extends Activity {

    int question;   //문제 인덱스 번호
    int a[] = new int[4];   //보기 번호들
    int real_answer;    //정답 보기 번호

    Dictionary dic = new Dictionary();
    ArrayList<String> word_lock = new ArrayList<>(); //문제로 낼 단어 리스트
    ArrayList<String> quizlist = new ArrayList<>(); //이미 시행 한 문제 저장
    int word_max = dic.ReturnSize();

    TextView textQuestion_lock;
    TextView textAnswer_lock[] = new TextView[4];
    LinearLayout answerBtn_lock[] = new LinearLayout[4];
    int wrong_counter = 0;

    public boolean inicializer()
    {
        if(word_max<4) {
            Toast.makeText(getApplicationContext(),"Need more than 4 word!",Toast.LENGTH_SHORT).show();
            return false;
        }

        textQuestion_lock = (TextView)findViewById(R.id.text_question_lock);
        textAnswer_lock[0] = (TextView)findViewById(R.id.text_answer1_lock);
        textAnswer_lock[1] = (TextView)findViewById(R.id.text_answer2_lock);
        textAnswer_lock[2] = (TextView)findViewById(R.id.text_answer3_lock);
        textAnswer_lock[3] = (TextView)findViewById(R.id.text_answer4_lock);
        answerBtn_lock[0] = (LinearLayout)findViewById(R.id.answer1_lock);
        answerBtn_lock[1] = (LinearLayout)findViewById(R.id.answer2_lock);
        answerBtn_lock[2] = (LinearLayout)findViewById(R.id.answer3_lock);
        answerBtn_lock[3] = (LinearLayout)findViewById(R.id.answer4_lock);

        for(int i = 0;i<word_max;i++)   //최초 단어 리스트 저장
        {
            word_lock.add(dic.ReturnWord(i));
        }
        return true;
    }

    private void correct()
    {
        dic.Search_info(word_lock.get(question)).correct();
    }

    private void wrong()
    {
        dic.Search_info(word_lock.get(question)).wrong();
        Toast.makeText(getApplicationContext(),"Wrong!",Toast.LENGTH_SHORT).show();
    }

    private boolean quizCreater()
    {
        Random random = new Random();

        while(true) {
            question = random.nextInt(word_max);    //퀴즈 랜덤 입력
            if (!quizlist.contains(word_lock.get(question)))   //퀴즈 중복 확인 후 만약 중복이 아닐시 계속 진행
            {
                quizlist.add(word_lock.get(question));   //문제로 나올 해당 단어 저장
                break;
            }
        }

        for(int i = 0;i<4;i++)  //보기들 입력
        {
            while(true) {
                a[i] = random.nextInt(word_max);
                if(a[i]!=question)  //이때 보기는 정답과 중복되면 안됨
                    break;
            }
        }

        real_answer = random.nextInt(4);    //정답이 될 보기 번호 저장
        a[real_answer] = question;  //정답 보기에 정답 인덱스 번호 저장

        //문제 출력
        textQuestion_lock.setText(word_lock.get(question));
        for(int i = 0;i<4;i++)
        {
            textAnswer_lock[i].setText(dic.Search(word_lock.get(a[i])));
        }

        return true;
    }

    private boolean quiz()
    {

        if(false==quizCreater()) {
            finish();
        }

        answerBtn_lock[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(0 == real_answer) {
                    correct();
                    Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                    finish();
                }
                else {
                    wrong();
                    wrong_counter++;
                    if(wrong_counter == 4)
                    {
                        Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                        finish();
                        Toast.makeText(getApplicationContext(),"모두 틀렸습니다. 분발하세요!",Toast.LENGTH_LONG).show();
                    }
                }


                if(false==quizCreater()) {
                    Toast.makeText(getApplicationContext(),"Test Over",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
        answerBtn_lock[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(1 == real_answer){
                    correct();
                    Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                    finish();
                }
                else {
                    wrong();
                    wrong_counter++;
                    if(wrong_counter == 4)
                    {
                        Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                        finish();
                        Toast.makeText(getApplicationContext(),"모두 틀렸습니다. 분발하세요!",Toast.LENGTH_LONG).show();
                    }
                }
                if(false==quizCreater()) {
                    Toast.makeText(getApplicationContext(),"Test Over",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
        answerBtn_lock[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(2 == real_answer){
                    correct();
                    Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                    finish();
                }
                else {
                    wrong();
                    wrong_counter++;
                    if(wrong_counter == 4)
                    {
                        Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                        finish();
                        Toast.makeText(getApplicationContext(),"모두 틀렸습니다. 분발하세요!",Toast.LENGTH_LONG).show();
                    }
                }
                if(false==quizCreater()) {
                    Toast.makeText(getApplicationContext(),"Test Over",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
        answerBtn_lock[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(3 == real_answer){
                    correct();
                    Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                    finish();
                }
                else {
                    wrong();
                    wrong_counter++;
                    if(wrong_counter == 4)
                    {
                        Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                        finish();
                        Toast.makeText(getApplicationContext(),"모두 틀렸습니다. 분발하세요!",Toast.LENGTH_LONG).show();
                    }
                }
                if(false==quizCreater()) {
                    Toast.makeText(getApplicationContext(),"Test Over",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lockscreen);

        QuizActivity quiz = new QuizActivity();

        // 안드로이드 잠금화면 위에 띄우라는 명령

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Button btn_unlock = (Button)findViewById(R.id.btn_unlock);

        btn_unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                finish();
            }
        });

        if(false == inicializer())
            this.finish();
        else {
            quiz();
        }
    }


}
