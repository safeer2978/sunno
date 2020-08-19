package com.sunno.AuthModule.network.request;

public class SignUpRequest {

    String email;
    String password;
    String phone_no;
    String first_name;
    String last_name;
    Integer age;
    String gender;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public SignUpRequest(String email, String password, String phone_no, String first_name, String last_name, Integer age, String gender) {
        this.email = email;
        this.password = password;
        this.phone_no = phone_no;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.gender = gender;
    }
}
