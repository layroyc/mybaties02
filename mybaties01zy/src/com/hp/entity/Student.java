package com.hp.entity;

public class Student {
    private String s_id;
    private String s_name;
    private String s_birth;
    private String s_sex;
    private Score score;
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Student{" +
                "s_id='" + s_id + '\'' +
                ", s_name='" + s_name + '\'' +
                ", s_birth='" + s_birth + '\'' +
                ", s_sex='" + s_sex + '\'' +
                ", score=" + score +
                ", teacher=" + teacher +
                '}';
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_birth() {
        return s_birth;
    }

    public void setS_birth(String s_birth) {
        this.s_birth = s_birth;
    }

    public String getS_sex() {
        return s_sex;
    }

    public void setS_sex(String s_sex) {
        this.s_sex = s_sex;
    }
}
