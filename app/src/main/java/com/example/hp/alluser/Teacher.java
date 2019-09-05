package com.example.hp.alluser;

import com.google.firebase.database.Exclude;

/**
 * Created by hp on 11-Apr-19.
 */

public class Teacher {
    private String mName;
    private String mImageUrl;
    private String Key;
    private String mPhoneNo;
    private String mPhoneNo2;
    private String mDesignation;
    private String mEmail;

    @Exclude
    public String getKey() {
        return Key;
    }
    @Exclude
    public void setKey(String key) {
        Key = key;
    }

    public Teacher() {

    }
    public Teacher(String name, String ImageUrl, String phoneNo,String phoneNo2,String designation,String Email){
        if(name.trim().equals("")){
            name="NO Name";
        }
        mName=name;
        mImageUrl=ImageUrl;
        mPhoneNo=phoneNo;
        mPhoneNo2=phoneNo2;
        mDesignation=designation;
        mEmail=Email;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String name) {
        mName = name;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String ImageUrl) {
        mImageUrl = ImageUrl;
    }

    public String getmPhoneNo() {
        return mPhoneNo;
    }

    public void setmPhoneNo(String phoneNo ) {
        mPhoneNo = phoneNo;
    }
    public String getmPhoneNo2() {
        return mPhoneNo2;
    }

    public void setmPhoneNo2(String phoneNo2 ) {
        mPhoneNo2 = phoneNo2;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String Email ) {
        mEmail = Email;
    }

    public String getmDesignation() {
        return mDesignation;
    }

    public void setmDesignation(String designation ) {
        mDesignation =designation ;
    }
}
