package com.doctor.happytooth.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Please fill the message")
    @Length(max = 255, message = "Reason too long")
    private String reason;
    private String time;
    private String phone;
    private String timeVisit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Registration() {
    }

    public Registration(String reason, String time, User user) {
        this.author = user;
        this.reason = reason;
        this.time = time;
    }

    public String getAuthorName(){
        return author != null ? author.getSurName() + " " + author.getName() : "<none>";
    }

    public String getAuthorAddress(){
        return author != null ? author.getAddress() : "<none>";
    }

    public String getAuthorAge(){
        return author != null ? author.getAge() : "<none>";
    }

    public String getAuthorMobile(){
        return author != null ? author.getMobile() : "<none>";
    }

    public String getAuthorEmail(){
        return author != null ? author.getEmail() : "<none>";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTimeVisit() {
        return timeVisit;
    }

    public void setTimeVisit(String timeVisit) {
        this.timeVisit = timeVisit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
