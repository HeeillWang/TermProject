package com.example.heeill.termproject;

/**
 * Created by HeeIll on 2016-06-20.
 */
public class Word_info {
    private String mean;
    private int correct_count;

    public int getCorrect_count() {
        return correct_count;
    }

    public String getMean() {
        return mean;
    }

    public void correct(){
        correct_count++;
    }

    public void wrong(){
        correct_count = 0;
    }

    //load시 사용되는 생성자
    public Word_info(int correct_count, String mean) {
        this.correct_count = correct_count;
        this.mean = mean;
    }

    //단어 입력할 때 사용되는 생성자
    public Word_info(String mean) {
        this.correct_count = 0;
        this.mean = mean;
    }
}
