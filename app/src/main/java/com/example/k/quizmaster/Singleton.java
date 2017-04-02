package com.example.k.quizmaster;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashukaushik on 02/04/17.
 */

public class Singleton {

    //  CREATED THIS CLASS SO THAT AN ARRAYLIST IS ACCESSIBLE EVERYWHERE COMMITTED

    private static Singleton instance;

    private ArrayList<String> frameList;

    private Singleton() {
        this.frameList = new ArrayList<String>();
    }


    public static Singleton Singleton() {
        if(instance == null)
            instance = new Singleton();

        return instance;
    }

    public ArrayList<String> getList() {
        return this.frameList;
    }

    public void setList(ArrayList<String> frameList) {
        this.frameList = frameList;
    }

}
