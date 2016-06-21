package com.example.heeill.termproject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016-06-08.
 */
public class QuizActivity extends Activity {

    int question;   //문제 인덱스 번호
    int a[] = new int[4];   //보기 번호들
    int real_answer;    //정답 보기 번호

    Dictionary dic=new Dictionary();
    ArrayList<String> word = new ArrayList<>(); //문제로 낼 단어 리스트
    ArrayList<String> quizlist = new ArrayList<>(); //이미 시행 한 문제 저장
    int word_max = dic.ReturnSize();    //전체 단어들 숫자
    int repeat;
    final String filename = "quiz_data.dat";

    TextView textQuestion;
    TextView num[] = new TextView[4];
    TextView textAnswer[] = new TextView[4];
    TextView wrong;
    LinearLayout answerBtn[] = new LinearLayout[4];

    Setting sett;

    /*
    테스트 완료시 작동 함수
     */
    private void quiz_over()
    {
        Toast.makeText(getApplicationContext(),"Test Over",Toast.LENGTH_LONG).show();
        quizlist.clear();
        finish();
    }

    /*
    설정사항을 적용하는 함수
     */
    private void setting_apply()
    {
        switch(sett.getQuiz_font())
        {
            case S:
                textQuestion.setTextSize(16);
                for(int i = 0;i<4;i++)
                {
                    textAnswer[i].setTextSize(10);
                    num[i].setTextSize(10);
                }
                wrong.setTextSize(10);
                break;
            case M:
                textQuestion.setTextSize(24);
                for(int i = 0;i<4;i++)
                {
                    textAnswer[i].setTextSize(15);
                    num[i].setTextSize(15);
                }
                wrong.setTextSize(15);
                break;
            case L:
                textQuestion.setTextSize(32);
                for(int i = 0;i<4;i++)
                {
                    textAnswer[i].setTextSize(20);
                    num[i].setTextSize(20);
                }
                wrong.setTextSize(20);
                break;
        }
        repeat = sett.getQuiz_repeat();
    }

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
        num[0] = (TextView)findViewById(R.id.text_num1);
        num[1] = (TextView)findViewById(R.id.text_num2);
        num[2] = (TextView)findViewById(R.id.text_num3);
        num[3] = (TextView)findViewById(R.id.text_num4);
        answerBtn[0] = (LinearLayout)findViewById(R.id.answer1);
        answerBtn[1] = (LinearLayout)findViewById(R.id.answer2);
        answerBtn[2] = (LinearLayout)findViewById(R.id.answer3);
        answerBtn[3] = (LinearLayout)findViewById(R.id.answer4);
        wrong = (TextView)findViewById(R.id.wrong);

        for(int i = 0;i<word_max;i++)   //최초 단어 리스트 저장
        {
            word.add(dic.ReturnWord(i));
        }

        setting_apply();

        return true;
    }

    /*
    정답일시 작동 함수
     */
    private void correct()
    {
        dic.Search_info(word.get(question)).correct();
        if(dic.Search_info(word.get(question)).getCorrect_count()>=repeat)  //설정된 반복횟수에 도달하게되면
        {
            //해당 단어 삭제
            dic.Delete(word.get(question));
            quizlist.remove(word.get(question));
            word.remove(question);
            word_max--;

            if(word_max<4)
            {
                quiz_over();
            }

            Toast.makeText(getApplicationContext(),"Goal Reached!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
        }
    }

    /*
    오답일시 작동 함수
     */
    private void wrong()
    {
        dic.Search_info(word.get(question)).wrong();
        Toast.makeText(getApplicationContext(),"Wrong!",Toast.LENGTH_SHORT).show();
    }

    /*
    퀴즈 생성 함수

    return : 모든 단어에 대해서 퀴즈를 시행했다면 false 반환
     */
    private boolean quizCreater()
    {
        Random random = new Random();

        if(quizlist.size()==word_max)   //만약 모든 단어를 시행완료했다면 false 반환
            return false;

        while(true) {
            question = random.nextInt(word_max);    //퀴즈 랜덤 입력
            if (!quizlist.contains(word.get(question)))   //퀴즈 중복 확인 후 만약 중복이 아닐시 계속 진행
            {
                quizlist.add(word.get(question));   //문제로 나올 해당 단어 저장
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
        textQuestion.setText(word.get(question));
        for(int i = 0;i<4;i++)
        {
            textAnswer[i].setText(dic.Search(word.get(a[i])));
        }

        return true;
    }

    /*
    퀴즈 함수

    return : 모든 단어에 대해서 퀴즈를 시행했다면 false 반환
     */
    private boolean quiz()
    {
        if(false==quizCreater()) {
            quiz_over();
        }

        answerBtn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(0 == real_answer)
                    correct();
                else
                    wrong();

                if(false==quizCreater()) {
                    quiz_over();
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
                    quiz_over();
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
                    quiz_over();
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
                    quiz_over();
                }
            }
        });
        wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrong();
                if(false==quizCreater()) {
                    quiz_over();
                }
            }
        });

        return true;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        File_Load();
        if(false==inizializer())
            quiz_over();
        else {
            quiz();
        }
    }

    private void File_Save()
    {
        try {
            FileOutputStream file = openFileOutput(filename, Context.MODE_PRIVATE);
            BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(file));

            for(int i = 0; i<quizlist.size();i++)
            {
                try {
                    buffer.write(quizlist.get(i));
                    buffer.newLine();
                } catch (IOException e) {
                    Log.i("TermProject","quiz data saving error");
                }
            }

            try {
                buffer.close();
                file.close();
            } catch (IOException e) {
                Log.i("TermProject","quiz data closing error");
            }

        } catch (FileNotFoundException e) {
            Log.i("TermProject","quiz data save error");
        }
    }

    private void File_Load()
    {
        try {
            FileInputStream file = openFileInput(filename);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(file));
            String tem;

            try {
                while((tem = buffer.readLine())!=null)
                {
                    if(word.contains(tem))
                        quizlist.add(tem);
                }
            } catch (IOException e) {
                Log.i("TermProject","quiz data loading error");
            }

            try {
                buffer.close();
                file.close();
            } catch (IOException e) {
                Log.i("TermProject","quiz data closing error");
            }

        } catch (FileNotFoundException e) {
            Log.i("TermProject","quiz data load error");
        }

    }

    @Override
    protected void onDestroy() {
        File_Save();
        super.onDestroy();
    }
}
