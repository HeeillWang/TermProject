package com.example.heeill.termproject;

enum font_size {S,M,L};

public interface Setting {
    static font_size search_font = font_size.M;
    static font_size wordbook_font = font_size.M;
    static font_size quiz_font = font_size.M;
    static int quiz_repeat = 3;

    public void setting_apply();
}
