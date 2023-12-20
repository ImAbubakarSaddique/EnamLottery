package rk.apenex.enamlottery.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import rk.apenex.enamlottery.R;
public class CampaignFragment extends Fragment {

    private ExtendedFloatingActionButton addNewCampaign_fbtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_campaign, container, false);

        addNewCampaign_fbtn = v.findViewById(R.id.addNewCampaign_fbtn);

        addNewCampaign_fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.add_new_campaign_dialog);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);

                AppCompatButton
                        submit_btn = dialog.findViewById(R.id.submit_btn),
                        cancel_btn = dialog.findViewById(R.id.cancel_btn);

                Spinner dropdown = dialog.findViewById(R.id.spinner1);
                String[] items = new String[]{"Subscribe a Youtube Channel","Watch a Youtube Video", "Follow an Instagram Account", "Like a Facebook Page", "Download an App", "Complete a Survey", "Watch a Video Ad", "Custom"};
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
                dropdown.setAdapter(adapter);

                submit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

        return v;
    }
}