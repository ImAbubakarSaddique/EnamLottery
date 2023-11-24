package rk.apenex.enamlottery.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import rk.apenex.enamlottery.R;
import rk.apenex.enamlottery.activities.ContactUsActivity;
import rk.apenex.enamlottery.activities.EditProfileActivity;
import rk.apenex.enamlottery.activities.LoginActivity;

public class MoreFragment extends Fragment {

    private TextView moreUserName_tv, moreUserEmail_tv, editProfileButton_tv, aboutUsButton_tv, contactUsButton_tv, shareButton_tv, logoutButton_tv;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_more, container, false);

        sharedPreferences = getContext().getSharedPreferences("EnamLotteryApp", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        moreUserName_tv = v.findViewById(R.id.moreUserName_tv);
        moreUserEmail_tv = v.findViewById(R.id.moreUserEmail_tv);
        editProfileButton_tv = v.findViewById(R.id.editProfileButton_tv);
        aboutUsButton_tv = v.findViewById(R.id.aboutUsButton_tv);
        contactUsButton_tv = v.findViewById(R.id.contactUsButton_tv);
        shareButton_tv = v.findViewById(R.id.shareButton_tv);
        logoutButton_tv = v.findViewById(R.id.logoutButton_tv);

        String UserName =sharedPreferences.getString("UserName", "");
        String UserEmail =sharedPreferences.getString("UserEmail", "");

        moreUserName_tv.setText(UserName);
        moreUserEmail_tv.setText(UserEmail);

        editProfileButton_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext().getApplicationContext(), EditProfileActivity.class));
            }
        });

        contactUsButton_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext().getApplicationContext(), ContactUsActivity.class));
            }
        });

        logoutButton_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogoutDialog();
            }
        });

        return v;
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Logout")
                .setMessage("Do you want to Logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        editor.putString("isLoggedIn", "0");
                        editor.commit();
                        startActivity(new Intent(getContext(), LoginActivity.class));
                        getActivity().finish();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}