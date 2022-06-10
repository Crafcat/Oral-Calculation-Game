package com.example.oral_calculation_game;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.Random;

public class GameViewModel extends AndroidViewModel {
    SavedStateHandle handle;
    boolean win_flag = false;

    public GameViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        //初始化程序
        if (!handle.contains(KEY_HIGH_SCORE)){
            SharedPreferences shp = getApplication().getSharedPreferences(SAVE_SHP_DATA, Context.MODE_PRIVATE);
            handle.set(KEY_HIGH_SCORE,shp.getInt(KEY_HIGH_SCORE,0));
            handle.set(KEY_LEFT_NUMBER,0);
            handle.set(KEY_RIGHT_NUMBER,0);
            handle.set(KEY_OPERATOR,"+");
            handle.set(KEY_ANSWER,0);
            handle.set(KEY_CURRENT_SCORE,0);
        }
        this.handle = handle;
    }

    /*————————————————————私有模块————————————————————*/
    //加载所有定义变量
    //从values/Game_Value获取Key

    private final String KEY_HIGH_SCORE = getApplication().getResources().getString(R.string.KEY_HIGH_SCORE);
    private final String KEY_LEFT_NUMBER = getApplication().getResources().getString(R.string.KEY_LEFT_NUMBER);
    private final String KEY_RIGHT_NUMBER = getApplication().getResources().getString(R.string.KEY_RIGHT_NUMBER);
    private final String KEY_OPERATOR = getApplication().getResources().getString(R.string.KEY_OPERATOR);
    private final String KEY_ANSWER = getApplication().getResources().getString(R.string.KEY_ANSWER);
    private final String KEY_CURRENT_SCORE = getApplication().getResources().getString(R.string.KEY_CURRENT_SCORE);
    private final String SAVE_SHP_DATA = getApplication().getResources().getString(R.string.SAVE_SHP_DATA);

    /*————————————————————公共模块————————————————————*/

    public MutableLiveData<Integer> getLeftNumber(){
        return handle.getLiveData(KEY_LEFT_NUMBER);
    }

    public MutableLiveData<Integer>getRightNumber(){
        return handle.getLiveData(KEY_RIGHT_NUMBER);
    }

    public MutableLiveData<String>getOperator(){
        return handle.getLiveData(KEY_OPERATOR);
    }

    public MutableLiveData<Integer>getHighScore(){
        return handle.getLiveData(KEY_HIGH_SCORE);
    }

    public MutableLiveData<Integer>getCurrentScore(){
        return handle.getLiveData(KEY_CURRENT_SCORE);
    }

    public MutableLiveData<Integer>getAnswer(){
        return handle.getLiveData(KEY_ANSWER);
    }

    //计算题
    public void Generator() {
        int LEVEL = 10;
        int Left, Right;

        Random random = new Random();
        Left = random.nextInt(LEVEL) + 1;
        Right = random.nextInt(LEVEL) + 1;

        if (Left % 2 == 0) {
            getOperator().setValue("+");
            getLeftNumber().setValue(Left);
            getRightNumber().setValue(Right);
            getAnswer().setValue(Left + Right);
        }else {
            getOperator().setValue("-");
            //当结果为负数时候，将左右两值对调使答案恒大于0
            if (Left > Right){
                getLeftNumber().setValue(Left);
                getRightNumber().setValue(Right);
                getAnswer().setValue(Left - Right);
            }else{
                getLeftNumber().setValue(Right);
                getRightNumber().setValue(Left);
                getAnswer().setValue(Right - Left);
            }
        }
    }

    public void CorrectAddScore(){
        //答对题目时候，得分增加
        getCurrentScore().setValue(getCurrentScore().getValue() + 1);
        if(getCurrentScore().getValue() > getHighScore().getValue()){
            getHighScore().setValue(getCurrentScore().getValue());
            win_flag = true;
        }
        //计算完得分后继续下一题
        Generator();
    }

    //保存最高分
    public void Save(){
        SharedPreferences shp = getApplication().getSharedPreferences(SAVE_SHP_DATA,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putInt(KEY_HIGH_SCORE,getHighScore().getValue());
        editor.apply();
    }
}

