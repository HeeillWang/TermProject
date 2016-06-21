package com.example.heeill.termproject;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

enum font_size {S,M,L};

public class Setting {
    static font_size search_font = font_size.M;
    static font_size wordbook_font = font_size.M;
    static font_size quiz_font = font_size.M;
    static int quiz_repeat = 3;
    static final String filename = "settingData.dat";

    public static font_size getQuiz_font() {
        return quiz_font;
    }

    public static int getQuiz_repeat() {
        return quiz_repeat;
    }

    public static font_size getSearch_font() {
        return search_font;
    }

    public static font_size getWordbook_font() {
        return wordbook_font;
    }

    public static void fileSave(BufferedWriter file)
    {
        try {
            switch(search_font)
            {
                case S:
                    file.write(0);
                    break;
                case M:
                    file.write(1);
                    break;
                case L:
                    file.write(2);
                    break;
            }
            file.newLine();
            switch(wordbook_font)
            {
                case S:
                    file.write(0);
                    break;
                case M:
                    file.write(1);
                    break;
                case L:
                    file.write(2);
                    break;
            }
            file.newLine();
            switch(quiz_font)
            {
                case S:
                    file.write(0);
                    break;
                case M:
                    file.write(1);
                    break;
                case L:
                    file.write(2);
                    break;
            }
            file.newLine();
            file.write(quiz_repeat);
        } catch (IOException e) {
            Log.i("TermProject","file saving error");
        }
    }

    public static void fileLoad(BufferedReader file)
    {
        try {
            switch(file.read())
            {
                case 0:
                    search_font = font_size.S;
                    break;
                case 1:
                    search_font = font_size.M;
                    break;
                case 2:
                    search_font = font_size.L;
                    break;
            }
            file.readLine();
            switch(file.read())
            {
                case 0:
                    wordbook_font = font_size.S;
                    break;
                case 1:
                    wordbook_font = font_size.M;
                    break;
                case 2:
                    wordbook_font = font_size.L;
                    break;
            }
            file.readLine();
            switch(file.read())
            {
                case 0:
                    quiz_font = font_size.S;
                    break;
                case 1:
                    quiz_font = font_size.M;
                    break;
                case 2:
                    quiz_font = font_size.L;
                    break;
            }
            file.readLine();
            quiz_repeat = file.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
