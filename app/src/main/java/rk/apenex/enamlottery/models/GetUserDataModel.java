package rk.apenex.enamlottery.models;

import com.google.gson.annotations.SerializedName;

public class GetUserDataModel {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("age")
    private String age;
    @SerializedName("contact")
    private String contact;
    @SerializedName("gender")
    private String gender;
    @SerializedName("email")
    private String email;
    @SerializedName("country")
    private String country;
    @SerializedName("user_type")
    private String user_type;
    @SerializedName("email_verified_at")
    private String email_verified_at;
    @SerializedName("password")
    private String password;
    @SerializedName("two_factor_secret")
    private String two_factor_secret;
    @SerializedName("two_factor_recovery_codes")
    private String two_factor_recovery_codes;
    @SerializedName("two_factor_confirmed_at")
    private String two_factor_confirmed_at;
    @SerializedName("remember_token")
    private String remember_token;
    @SerializedName("current_team_id")
    private String current_team_id;
    @SerializedName("profile_photo_path")
    private String profile_photo_path;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;

    public GetUserDataModel(String id, String name, String age, String contact, String gender, String email, String country, String user_type, String email_verified_at, String password, String two_factor_secret, String two_factor_recovery_codes, String two_factor_confirmed_at, String remember_token, String current_team_id, String profile_photo_path, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.gender = gender;
        this.email = email;
        this.country = country;
        this.user_type = user_type;
        this.email_verified_at = email_verified_at;
        this.password = password;
        this.two_factor_secret = two_factor_secret;
        this.two_factor_recovery_codes = two_factor_recovery_codes;
        this.two_factor_confirmed_at = two_factor_confirmed_at;
        this.remember_token = remember_token;
        this.current_team_id = current_team_id;
        this.profile_photo_path = profile_photo_path;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTwo_factor_secret() {
        return two_factor_secret;
    }

    public void setTwo_factor_secret(String two_factor_secret) {
        this.two_factor_secret = two_factor_secret;
    }

    public String getTwo_factor_recovery_codes() {
        return two_factor_recovery_codes;
    }

    public void setTwo_factor_recovery_codes(String two_factor_recovery_codes) {
        this.two_factor_recovery_codes = two_factor_recovery_codes;
    }

    public String getTwo_factor_confirmed_at() {
        return two_factor_confirmed_at;
    }

    public void setTwo_factor_confirmed_at(String two_factor_confirmed_at) {
        this.two_factor_confirmed_at = two_factor_confirmed_at;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
    }

    public String getCurrent_team_id() {
        return current_team_id;
    }

    public void setCurrent_team_id(String current_team_id) {
        this.current_team_id = current_team_id;
    }

    public String getProfile_photo_path() {
        return profile_photo_path;
    }

    public void setProfile_photo_path(String profile_photo_path) {
        this.profile_photo_path = profile_photo_path;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
