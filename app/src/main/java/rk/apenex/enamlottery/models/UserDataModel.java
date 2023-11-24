package rk.apenex.enamlottery.models;

import com.google.gson.annotations.SerializedName;

public class UserDataModel {
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

    @SerializedName("password")
    private String password;

    public UserDataModel(String name, String age, String contact, String gender, String email) {
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.gender = gender;
        this.email = email;
    }

    public UserDataModel(String name, String age, String contact, String gender, String email, String country, String password) {
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.gender = gender;
        this.email = email;
        this.country = country;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

