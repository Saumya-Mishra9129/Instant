package com.example.instant;

import android.content.Intent;

public class Member {
    private String Guar_name;
    private String Guar_email;
    private Long Guar_mobile;
    private Long your_number;
    private String current_address;
    private String profession;
    private String your_name;
    private String your_email;
    private Integer your_age;


    public String getGuar_name() {
        return Guar_name;
    }

    public void setGuar_name(String guar_name) {
        Guar_name = guar_name;
    }

    public String getGuar_email() {
        return Guar_email;
    }

    public void setGuar_email(String guar_email) {
        Guar_email = guar_email;
    }

    public Long getGuar_mobile() {
        return Guar_mobile;
    }

    public void setGuar_mobile(Long guar_mobile) {
        Guar_mobile = guar_mobile;
    }

    public Long getYour_number() {
        return your_number;
    }

    public void setYour_number(Long your_number) {
        this.your_number = your_number;
    }

    public String getCurrent_address() {
        return current_address;
    }

    public void setCurrent_address(String current_address) {
        this.current_address = current_address;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getYour_name() {
        return your_name;
    }

    public void setYour_name(String your_name) {
        this.your_name = your_name;
    }

    public String getYour_email() {
        return your_email;
    }

    public void setYour_email(String your_email) {
        this.your_email = your_email;
    }

    public Integer getYour_age() {
        return your_age;
    }

    public void setYour_age(Integer your_age) {
        this.your_age = your_age;
    }



    public Member() {
    }
}
