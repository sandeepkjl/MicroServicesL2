package com.wipro.movieuser.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserRecord")
public class User {

    @Id
    @GeneratedValue
    private long userId;
    private String userName;
    private String userPassword;
    private String userEmailId;
    private String userMobileNo;

    public User() {
        super();
    }

    public User(String userName, String userPassword, String userEmailId, String userMobileNo) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmailId = userEmailId;
        this.userMobileNo = userMobileNo;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getUserMobileNo() {
        return userMobileNo;
    }

    public void setUserMobileNo(String userMobileNo) {
        this.userMobileNo = userMobileNo;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmailId='" + userEmailId + '\'' +
                ", userMobileNo='" + userMobileNo + '\'' +
                '}';
    }
}
