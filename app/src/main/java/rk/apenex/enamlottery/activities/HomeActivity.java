package rk.apenex.enamlottery.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;
import rk.apenex.enamlottery.R;
import rk.apenex.enamlottery.fragments.CampaignFragment;
import rk.apenex.enamlottery.fragments.EarnCoinsFragment;
import rk.apenex.enamlottery.fragments.HomeFragment;
import rk.apenex.enamlottery.fragments.MoreFragment;
import rk.apenex.enamlottery.fragments.TicketFragment;

public class HomeActivity extends AppCompatActivity {

    SmoothBottomBar smoothBottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.secondary));

        smoothBottomBar = findViewById(R.id.smoothBottomBar);
        loadFragment(new HomeFragment());


        smoothBottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                if(i == 0){
                    loadFragment(new HomeFragment());
                } else if (i == 1) {
                    loadFragment(new TicketFragment());
                } else if (i == 2) {
                    loadFragment(new CampaignFragment());
                } else if (i == 3) {
                    loadFragment(new EarnCoinsFragment());
                } else if (i == 4) {
                    loadFragment(new MoreFragment());
                }
                return false;
            }
        });

    }
    private void loadFragment(Fragment fragment) {
        // Replace the FrameLayout with the fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }
}