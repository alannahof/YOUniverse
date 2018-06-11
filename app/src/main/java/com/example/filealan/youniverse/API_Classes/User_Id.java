package com.example.filealan.youniverse.API_Classes;
import com.google.gson.annotations.SerializedName;
public class User_Id {

        @SerializedName("$oid")
        String $oid;

        public User_Id(String $oid) {
            this.$oid = $oid;
        }

        public String get$oid() {
            return $oid;
        }

        public void set$oid(String $oid) {
            this.$oid = $oid;
        }
    }

