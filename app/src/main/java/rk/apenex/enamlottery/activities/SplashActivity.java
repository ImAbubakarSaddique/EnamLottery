package rk.apenex.enamlottery.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import rk.apenex.enamlottery.R;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences = getSharedPreferences("EnamLotteryApp", Context.MODE_PRIVATE);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.secondary));

        String isLoggedIn = sharedPreferences.getString("isLoggedIn", "0");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLoggedIn.equals("1")) {
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        }, 3000);
    }
}