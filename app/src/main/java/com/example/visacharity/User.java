package com.example.visacharity;

import android.app.Application;

import java.io.Serializable;

public class User extends Application implements Serializable {

    private Integer totalPoints;

    public User(){

    }

    public Integer getTotalPoints(){
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints){
        this.totalPoints = totalPoints;
    }
}
