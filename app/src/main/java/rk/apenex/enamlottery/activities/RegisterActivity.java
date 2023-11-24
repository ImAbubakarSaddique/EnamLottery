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

import com.ehsanmashhadi.library.model.Country;
import com.ehsanmashhadi.library.view.CountryPicker;
import com.ehsanmashhadi.library.view.RecyclerViewAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rk.apenex.enamlottery.R;
import rk.apenex.enamlottery.client.ApiClient;
import rk.apenex.enamlottery.models.GetUserDataModel;
import rk.apenex.enamlottery.models.UserDataModel;
import rk.apenex.enamlottery.response.ApiResponse;
import rk.apenex.enamlottery.services.ApiService;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText registerUserName_ed, registerUserAge_ed, registerUserPhoneNumber_ed, registerUserCountry_ed, registerUserEmail_ed, registerUserPassword_ed;
    private AppCompatButton registerNow_btn;
    private TextView loginNow_tv;
    private ProgressDialog progressDialog;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharedPreferences = getSharedPreferences("EnamLotteryApp", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.secondary));
        initViews();
        progressDialog = new ProgressDialog(this);
        registerNow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String
                        UserName = registerUserName_ed.getText().toString().trim(),
                        UserAge = registerUserAge_ed.getText().toString().trim(),
                        UserPhone = registerUserPhoneNumber_ed.getText().toString().trim(),
                        UserCountry = registerUserCountry_ed.getText().toString().trim(),
                        UserEmail = registerUserEmail_ed.getText().toString().trim(),
                        UserPassword = registerUserPassword_ed.getText().toString().trim();
                progressDialog.setTitle("Create an Account");
                progressDialog.setMessage("Creating account.....");
                progressDialog.show();

                if (!UserName.isEmpty() && !UserAge.isEmpty() && !UserPhone.isEmpty() && !UserCountry.isEmpty() && !UserEmail.isEmpty() && !UserPassword.isEmpty()) {
                    UserDataModel userDataModel = new UserDataModel(
                            UserName,
                            UserAge,
                            UserPhone,
                            "Male",
                            UserEmail,
                            UserCountry,
                            UserPassword
                    );
                    registerNewUser(userDataModel);
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
        loginNow_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void registerNewUser(UserDataModel userDataModel) {
        ApiService apiService = ApiClient.getApiClient().create(ApiService.class);
        Call<ApiResponse> call = apiService.registerUser("Bearer WGycOfkARR", userDataModel);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null && "User created successfully.".equals(apiResponse.getMessage())) {
                        getUserDetailsFromAPI(userDataModel.getEmail());
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Email Already Registered", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Email Already Registered", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void initViews() {
        registerUserName_ed = findViewById(R.id.registerUserName_ed);
        registerUserAge_ed = findViewById(R.id.registerUserAge_ed);
        registerUserPhoneNumber_ed = findViewById(R.id.registerUserPhoneNumber_ed);
        registerUserCountry_ed = findViewById(R.id.registerUserCountry_ed);
        registerUserEmail_ed = findViewById(R.id.registerUserEmail_ed);
        registerUserPassword_ed = findViewById(R.id.registerUserPassword_ed);

        registerNow_btn = findViewById(R.id.registerNow_btn);

        loginNow_tv = findViewById(R.id.loginNow_tv);
    }

    public void launchCountryCode(View view) {
        CountryPicker countryPicker = new CountryPicker.Builder(this)
                .showingFlag(true)
                .enablingSearch(true)
                .sortBy(CountryPicker.Sort.COUNTRY)
                .setViewType(CountryPicker.ViewType.BOTTOMSHEET)
                .setLocale(new Locale("EN"))
                .setPreSelectedCountry("pakistan")
                .setCountrySelectionListener(new RecyclerViewAdapter.OnCountryClickListener() {
                    @Override
                    public void onCountrySelected(Country country) {
                        registerUserCountry_ed.setText(country.getName().toString());
                    }
                })
                .build();
        countryPicker.show(this);
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
                    Toast.makeText(RegisterActivity.this, getUserDataModel.getEmail(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Server Issue. Error Code " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetUserDataModel> call, Throwable t) {

            }
        });
    }
}