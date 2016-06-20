package com.example.heeill.termproject;

import java.util.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016-06-08.
 */
public class QuizActivity extends Activity {

    int question;   //문제 번호
    int a[] = new int[4];   //보기 번호들
    int real_answer;    //답 보기 번호

    Dictionary dic=new Dictionary();
    ArrayList<String> word = new ArrayList<>();
    ArrayList<Integer> quizlist = new ArrayList<>();
    final int word_max = dic.ReturnSize();

    TextView textQuestion;
    TextView textAnswer[] = new TextView[4];
    TextView wrong;
    LinearLayout answerBtn[] = new LinearLayout[4];

    /*
    최초 초기화 함수
     */
    private boolean inizializer()
    {
        if(word_max<4) {
            Toast.makeText(getApplicationContext(),"Need more than 4 word!",Toast.LENGTH_SHORT).show();
            return false;
        }

        textQuestion = (TextView)findViewById(R.id.text_question);
        textAnswer[0] = (TextView)findViewById(R.id.text_answer1);
        textAnswer[1] = (TextView)findViewById(R.id.text_answer2);
        textAnswer[2] = (TextView)findViewById(R.id.text_answer3);
        textAnswer[3] = (TextView)findViewById(R.id.text_answer4);
        answerBtn[0] = (LinearLayout)findViewById(R.id.answer1);
        answerBtn[1] = (LinearLayout)findViewById(R.id.answer2);
        answerBtn[2] = (LinearLayout)findViewById(R.id.answer3);
        answerBtn[3] = (LinearLayout)findViewById(R.id.answer4);
        wrong = (TextView)findViewById(R.id.wrong);

        for(int i = 0;i<word_max;i++)
        {
            word.add(dic.ReturnWord(i+1));
        }

        return true;
    }

    /*
    정답일시 작동 함수
     */
    private void correct()
    {
        Toast.makeText(getApplicationContext(),"Correct!",Toast.LENGTH_SHORT).show();
    }

    /*
    오답일시 작동 함수
     */
    private void wrong()
    {
        Toast.makeText(getApplicationContext(),"Wrong!",Toast.LENGTH_SHORT).show();
    }

    /*
    퀴즈 생성 함수

    정답일 시
     */
    private boolean quizCreater()
    {
        Random random = new Random();

        if(quizlist.size()==word_max)
            return false;

        while(true) {
            question = random.nextInt(word_max);    //퀴즈 랜덤 입력
            if (!quizlist.contains(question))   //퀴즈 중복 확인 후 만약 중복이 아닐시
            {
                quizlist.add(question);
                break;
            }
        }

        for(int i = 0;i<4;i++)
        {
            while(true) {
                a[i] = random.nextInt(word_max);
                if(a[i]!=question)
                    break;
            }
        }

        real_answer = random.nextInt(4);
        a[real_answer] = question;

        textQuestion.setText(word.get(question));
        for(int i = 0;i<4;i++)
        {
            textAnswer[i].setText(dic.Search(word.get(a[i])));
        }

        return true;
    }

    private boolean quiz()
    {
        if(false==quizCreater()) {
            Toast.makeText(getApplicationContext(),"Test Over",Toast.LENGTH_LONG).show();
            finish();
        }

        answerBtn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(0 == real_answer)
                    correct();
                else
                    wrong();

                if(false==quizCreater()) {
                    Toast.makeText(getApplicationContext(),"Test Over",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
        answerBtn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(1 == real_answer)
                    correct();
                else
                    wrong();
                if(false==quizCreater()) {
                    Toast.makeText(getApplicationContext(),"Test Over",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
        answerBtn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(2 == real_answer)
                    correct();
                else
                    wrong();
                if(false==quizCreater()) {
                    Toast.makeText(getApplicationContext(),"Test Over",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
        answerBtn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(3 == real_answer)
                    correct();
                else
                    wrong();
                if(false==quizCreater()) {
                    Toast.makeText(getApplicationContext(),"Test Over",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
        wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong();
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
        setContentView(R.layout.activity_quiz);

        if(false==inizializer())
            this.finish();
        else {
            quiz();
        }
    }


}
