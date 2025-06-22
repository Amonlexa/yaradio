package com.amonlexasoftware.yaradio.ui.activity;

import static com.amonlexasoftware.yaradio.support.Config.backPressedTime;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amonlexasoftware.yaradio.support.service.Connection;
import com.amonlexasoftware.yaradio.support.Assistant;

public class SplashScreen extends AppCompatActivity {

    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishAffinity();
        }else {
            Assistant.showToast(getApplicationContext(),"Нажмите еще раз для выхода");
        }
        backPressedTime = System.currentTimeMillis();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            if (!Connection.checkInternetConnection(this)) {
                Assistant.showToast(this, "Проверьте пожалуйста соединение с интернетом");
                Assistant.showToast(this, "Завершение приложения");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finishAffinity();
                    }
                }, 3500);
            } else {
                Assistant.runActivity(this, MainActivity.class);
        }
    }
}
