package rk.apenex.enamlottery.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;

import rk.apenex.enamlottery.R;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.secondary));

    }

    public void onBackPress(View view) {
        onBackPressed();
    }
}