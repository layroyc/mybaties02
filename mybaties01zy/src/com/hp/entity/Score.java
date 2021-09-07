package com.hp.entity;

public class Score {
    private String s_id;
    private String c_id;
    private int s_score;

    @Override
    public String toString() {
        return "Score{" +
                "s_id='" + s_id + '\'' +
                ", c_id='" + c_id + '\'' +
                ", s_score=" + s_score +
                '}';
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public int getS_score() {
        return s_score;
    }

    public void setS_score(int s_score) {
        this.s_score = s_score;
    }
}
