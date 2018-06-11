package com.example.filealan.youniverse.API_Classes;

//How to import the library
import com.google.gson.annotations.SerializedName;

public class User_Object {




    @SerializedName("_id")
    User_Id id;
    @SerializedName("username")
    String username;
    @SerializedName("hash")
    String hash;
    @SerializedName("selected_avatar")
    int selected_avatar;
    @SerializedName("tokens")
    int tokens;
    @SerializedName("iterations")
    int iterations;
    @SerializedName("salt")
    String salt;


    public User_Object (String name, String hash, int avatar, int tokens, int iterations, String salt) {
        this.username = name;
        this.hash = hash;
        this.selected_avatar = avatar;
        this.tokens = tokens;
        this.iterations = iterations;
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getSelected_avatar() {
        return selected_avatar;
    }

    public void setSelected_avatar(int selected_avatar) {
        this.selected_avatar = selected_avatar;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public User_Id getId() {
        return id;
    }

    public void setId(User_Id id) {
        this.id = id;
    }
}
