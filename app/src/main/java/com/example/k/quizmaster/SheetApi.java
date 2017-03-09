package com.example.k.quizmaster;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by k on 3/8/2017.
 */

public interface SheetApi {
    @GET("v1.0/575271416565")
    Call<ArrayList<QuestionsClass>> getQuestions();

}
