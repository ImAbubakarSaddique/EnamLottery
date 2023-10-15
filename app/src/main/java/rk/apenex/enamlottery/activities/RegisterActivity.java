package rk.apenex.enamlottery.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.ehsanmashhadi.library.view.CountryPicker;

import java.util.Locale;

import rk.apenex.enamlottery.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.secondary));

        CountryPicker countryPicker = new CountryPicker.Builder(this)
                .showingFlag(true)
                .enablingSearch(true)
                .sortBy(CountryPicker.Sort.COUNTRY)
                .setViewType(CountryPicker.ViewType.BOTTOMSHEET)
                .setLocale(new Locale("EN"))
                .setPreSelectedCountry("pakistan")
                .build();

        countryPicker.show(this);
    }
}