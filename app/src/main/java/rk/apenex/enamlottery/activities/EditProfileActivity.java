package rk.apenex.enamlottery.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rk.apenex.enamlottery.R;
import rk.apenex.enamlottery.client.ApiClient;
import rk.apenex.enamlottery.models.UserDataModel;
import rk.apenex.enamlottery.response.ApiResponse;
import rk.apenex.enamlottery.services.ApiService;

public class EditProfileActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextInputEditText editUserName_ed, editUserAge_ed, editUserContactNumber_ed, editUserEmail_ed;
    private RadioButton editMale_rBtn, editFemale_rBtn;
    private AppCompatButton updateProfileInformation_btn;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        sharedPreferences = getSharedPreferences("EnamLotteryApp", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.secondary));

        String UserName = sharedPreferences.getString("UserName", "");
        String UserAge = sharedPreferences.getString("UserAge", "");
        String UserContactNumber = sharedPreferences.getString("UserPhone", "");
        String UserEmail = sharedPreferences.getString("UserEmail", "");
        String UserGender = sharedPreferences.getString("UserGender", "");

        initViews();
        progressDialog = new ProgressDialog(this);

        editUserName_ed.setText(UserName);
        editUserAge_ed.setText(UserAge);
        editUserContactNumber_ed.setText(UserContactNumber);
        editUserEmail_ed.setText(UserEmail);

        if(UserGender.equalsIgnoreCase("Male")){
            editMale_rBtn.setChecked(true);
        }else {
            editFemale_rBtn.setChecked(true);
        }

        updateProfileInformation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String
                        UserNameStr = editUserName_ed.getText().toString().trim(),
                        UserAgeStr = editUserAge_ed.getText().toString().trim(),
                        UserContactNumberStr = editUserContactNumber_ed.getText().toString().trim(),
                        UserEmailStr = editUserEmail_ed.getText().toString().trim(),
                        UserGenderStr = "Male";
                progressDialog.setTitle("Update Profile");
                progressDialog.setMessage("Updating Profile.....");
                progressDialog.show();
                if(editMale_rBtn.isChecked()){
                    UserGenderStr = "Male";
                }else{
                    UserGenderStr = "Female";
                }

                if(!UserNameStr.isEmpty() && !UserAgeStr.isEmpty() && !UserContactNumberStr.isEmpty() && !UserEmailStr.isEmpty() && !UserGenderStr.isEmpty()){
                    UserDataModel userDataModel = new UserDataModel(UserNameStr,UserAgeStr,UserContactNumberStr,UserGenderStr,UserEmailStr);
                    updateUserProfile(userDataModel);
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(EditProfileActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void updateUserProfile(UserDataModel userDataModel) {
        ApiService apiService = ApiClient.getApiClient().create(ApiService.class);
        Call<ApiResponse> call = apiService.updateUserData("Bearer WGycOfkARR",userDataModel);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.isSuccessful()){
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null && "User updated successfully.".equals(apiResponse.getMessage())) {
                        updateInSharedPref(userDataModel.getName(),userDataModel.getAge(),userDataModel.getContact(),userDataModel.getGender(),userDataModel.getEmail());
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(EditProfileActivity.this, "Try again later", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(EditProfileActivity.this, "Error Code " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }

    private void updateInSharedPref(String name, String age, String contact, String gender, String email) {
        editor.putString("UserName",name);
        editor.putString("UserAge",age);
        editor.putString("UserPhone",contact);
        editor.putString("UserGender",gender);
        editor.putString("UserEmail",email);
        editor.commit();
        progressDialog.dismiss();
        onBackPressed();
    }

    private void initViews() {
        editUserName_ed = findViewById(R.id.editUserName_ed);
        editUserAge_ed = findViewById(R.id.editUserAge_ed);
        editUserContactNumber_ed = findViewById(R.id.editUserContactNumber_ed);
        editUserEmail_ed = findViewById(R.id.editUserEmail_ed);

        editMale_rBtn = findViewById(R.id.editMale_rBtn);
        editFemale_rBtn = findViewById(R.id.editFemale_rBtn);

        updateProfileInformation_btn = findViewById(R.id.updateProfileInformation_btn);
    }
    public void onBackPress(View view) {
        onBackPressed();
    }
}