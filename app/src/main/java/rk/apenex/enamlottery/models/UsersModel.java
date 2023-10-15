package rk.apenex.enamlottery.models;

public class UsersModel {
    String uName, uCountry, uGender, uEmail, uPassword, uProfilePictureLink, uAge, uContactNumber;

    public UsersModel(String uName, String uCountry, String uGender, String uEmail, String uPassword, String uProfilePictureLink, String uAge, String uContactNumber) {
        this.uName = uName;
        this.uCountry = uCountry;
        this.uGender = uGender;
        this.uEmail = uEmail;
        this.uPassword = uPassword;
        this.uProfilePictureLink = uProfilePictureLink;
        this.uAge = uAge;
        this.uContactNumber = uContactNumber;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuCountry() {
        return uCountry;
    }

    public void setuCountry(String uCountry) {
        this.uCountry = uCountry;
    }

    public String getuGender() {
        return uGender;
    }

    public void setuGender(String uGender) {
        this.uGender = uGender;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuProfilePictureLink() {
        return uProfilePictureLink;
    }

    public void setuProfilePictureLink(String uProfilePictureLink) {
        this.uProfilePictureLink = uProfilePictureLink;
    }

    public String getuAge() {
        return uAge;
    }

    public void setuAge(String uAge) {
        this.uAge = uAge;
    }

    public String getuContactNumber() {
        return uContactNumber;
    }

    public void setuContactNumber(String uContactNumber) {
        this.uContactNumber = uContactNumber;
    }
}
