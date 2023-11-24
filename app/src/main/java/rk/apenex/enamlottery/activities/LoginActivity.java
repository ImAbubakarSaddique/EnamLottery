package rk.apenex.enamlottery.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rk.apenex.enamlottery.R;
import rk.apenex.enamlottery.client.ApiClient;
import rk.apenex.enamlottery.models.GetUserDataModel;
import rk.apenex.enamlottery.response.ApiResponse;
import rk.apenex.enamlottery.services.ApiService;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText userEmail_ed, userPassword_ed;
    private AppCompatButton continue_btn;
    private TextView registerNow_btn;
    private ProgressDialog progressDialog;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("EnamLotteryApp", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.secondary));

        initViews();
        progressDialog = new ProgressDialog(this);

        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String
                        userEmailStr = userEmail_ed.getText().toString(),
                        userPasswordStr = userPassword_ed.getText().toString();
                progressDialog.setTitle("Login to Account");
                progressDialog.setMessage("Verifying Credentials.....");
                progressDialog.show();

                if (!userEmailStr.isEmpty() && !userPasswordStr.isEmpty()) {
                    authenticateUser(userEmailStr, userPasswordStr);
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerNow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

    }

    private void initViews() {
        userEmail_ed = findViewById(R.id.userEmail_ed);
        userPassword_ed = findViewById(R.id.userPassword_ed);
        continue_btn = findViewById(R.id.continue_btn);
        registerNow_btn = findViewById(R.id.registerNow_btn);
    }

    private void authenticateUser(String email, String password) {
        ApiService apiService = ApiClient.getApiClient().create(ApiService.class);
        Call<ApiResponse> call = apiService.authenticateUser("users", email, password);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null && "Correct Credentials".equals(apiResponse.getMessage())) {
                        getUserDetailsFromAPI(email);
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Error Code " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Handle failure
            }
        });

    }
    private void getUserDetailsFromAPI(String email) {
        ApiService apiService = ApiClient.getApiClient().create(ApiService.class);
        Call<GetUserDataModel> call = apiService.getUserData("users", email);

        call.enqueue(new Callback<GetUserDataModel>() {
            @Override
            public void onResponse(Call<GetUserDataModel> call, Response<GetUserDataModel> response) {
                if (response.isSuccessful()) {
                    progressDialog.setMessage("Getting User Data......");
                    GetUserDataModel getUserDataModel = response.body();
                    editor.putString("UserName",getUserDataModel.getName());
                    editor.putString("UserAge",getUserDataModel.getAge());
                    editor.putString("UserPhone",getUserDataModel.getContact());
                    editor.putString("UserGender",getUserDataModel.getGender());
                    editor.putString("UserEmail",getUserDataModel.getEmail());
                    editor.putString("UserCountry",getUserDataModel.getCountry());
                    editor.putString("isLoggedIn", "1");
                    editor.commit();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                    Toast.makeText(LoginActivity.this, getUserDataModel.getEmail(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Server Issue. Error Code " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetUserDataModel> call, Throwable t) {

            }
        });
    }
}