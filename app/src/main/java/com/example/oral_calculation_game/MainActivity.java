package com.example.oral_calculation_game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavHostFragment navHostFragment =(NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController controller = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this,controller);
    }

    @Override
   public boolean onSupportNavigateUp(){

       NavHostFragment navHostFragment =(NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
       NavController controller = navHostFragment.getNavController();

        //如果该界面在游戏界面中，则跳出是否退出
       if (controller.getCurrentDestination().getId() == R.id.gameFragment){
           AlertDialog.Builder builder = new AlertDialog.Builder(this);
           builder.setTitle(getString(R.string.quit_dailog_title));

           builder.setPositiveButton(R.string.quit_dailog_yes, (dialog, which) -> controller.navigateUp());

           builder.setNegativeButton(R.string.quit_dailog_no, (dialog, which) -> {
            //点否则不作任何处理
           });

           AlertDialog dailog = builder.create();
           dailog.show();

       }else{
           //其他情况下则返回主页
           controller.navigate(R.id.homeFragment);
       }
       return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed(){
        //重写导航栏返回键
        onSupportNavigateUp();
    }
}