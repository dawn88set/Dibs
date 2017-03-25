package com.example.shaharcohen.dibs.Entity;

import java.util.ArrayList;

public class AppUser {
    private int user_id;
    private String first_name;
    private String last_name;
    private int age;
    private String about_me;
    private String gender;
    private double geographic_lat;
    private double geographic_len;
    private String join_date;
    private String  looking_for_gender;
    private ArrayList<AppUser> my_connections;

    public AppUser(int id ,String first_name,String last_name, int age,String about_me,String gender,double geographic_lat,double geographic_len,String join_date,String  looking_for_gender){
        this.user_id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.about_me = about_me;
        this.gender = gender;
        this.geographic_lat = geographic_lat;
        this.geographic_len = geographic_len;
        this.join_date = join_date;
        this.looking_for_gender = looking_for_gender;
    }
    public AppUser(String first_name,String last_name, int age,String about_me,String gender,double geographic_lat,double geographic_len,String join_date,String  looking_for_gender){
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.about_me = about_me;
        this.gender = gender;
        this.geographic_lat = geographic_lat;
        this.geographic_len = geographic_len;
        this.join_date = join_date;
        this.looking_for_gender = looking_for_gender;
    }
    public AppUser(){

    }
    public double getGeographic_lat() {
        return geographic_lat;
    }

    public double getGeographic_len() {
        return geographic_len;
    }

    public int getAge() {
        return age;
    }

    public String getAbout_me() {
        return about_me;
    }

    public String getJoin_date() {
        return join_date;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getGender() {
        return gender;
    }

    public String getLooking_for_gender() {
        return looking_for_gender;
    }

    public int getUser_id() {
        return user_id;
    }
}
