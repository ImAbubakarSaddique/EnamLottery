package rk.apenex.enamlottery.services;

import android.service.autofill.UserData;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rk.apenex.enamlottery.models.GetUserDataModel;
import rk.apenex.enamlottery.models.UserDataModel;
import rk.apenex.enamlottery.response.ApiResponse;

public interface ApiService {

    @Headers({"Authorization: Bearer WGycOfkARR"})
    @GET("api.php")
    Call<ApiResponse> authenticateUser(
            @Query("table") String table,
            @Query("email") String email,
            @Query("password") String password
    );

    @POST("api.php")
    Call<ApiResponse> registerUser(
            @Header("Authorization") String authorization,
            @Body UserDataModel userDataModel
    );

    @Headers({"Authorization: Bearer WGycOfkARR"})
    @GET("api.php")
    Call<GetUserDataModel> getUserData(
            @Query("table") String table,
            @Query("email") String email
    );

    @POST("updatedata.php")
    Call<ApiResponse> updateUserData(
            @Header("Authorization") String authorization,
            @Body UserDataModel userDataModel
    );
}