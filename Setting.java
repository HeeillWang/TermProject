package com.example.heeill.termproject;

enum font_size {S,M,L};

public class Setting {
    static font_size search_font = font_size.M;
    static font_size wordbook_font = font_size.M;
    static font_size quiz_font = font_size.M;
    static int quiz_repeat = 3;

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
}
