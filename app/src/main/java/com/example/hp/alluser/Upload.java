package com.example.hp.alluser;

import com.google.firebase.database.Exclude;

/**
 * Created by hp on 07-Feb-19.
 */

public class Upload {
    private String mName;
    private String mImageUrl;
    private String Key;

    @Exclude
    public String getKey() {
        return Key;
    }

    @Exclude
    public void setKey(String key) {
        Key = key;
    }

    public Upload() {

    }

    public Upload(String name, String ImageUrl) {
        if (name.trim().equals("")) {
            name = "NO Name";
        }
        mName = name;
        mImageUrl = ImageUrl;
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
}
