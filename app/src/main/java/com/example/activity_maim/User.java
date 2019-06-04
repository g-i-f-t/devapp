package com.example.activity_maim;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "room_info")
public class User implements Serializable {
    @PrimaryKey
    private long id;
    @ColumnInfo(name = "userNum")
    private String UserSeqNo;
    @ColumnInfo(name = "Access_token")
    private String AccessToken;

    public String getUserSeqNo() {
        return UserSeqNo;
    }

    public void setUserSeqNo(String userSeqNo) {
        UserSeqNo = userSeqNo;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}