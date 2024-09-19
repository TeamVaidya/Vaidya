package com.example.registration.entity;

import java.util.Date;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name="confirmTokenDoctor")
public class ConfirmTokenDoctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private Long tokenId;

    @Column(name="confirm_token_Doctor")
    private String confirmTokenDoctor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "doctor_id")
    private User doctor;

    public ConfirmTokenDoctor() {}

    public ConfirmTokenDoctor(User doctor) {
        this.doctor = doctor;
        this.createdDate = new Date();
        this.confirmTokenDoctor = UUID.randomUUID().toString();
    }

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public String getConfirmationToken() {
        return confirmTokenDoctor;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmTokenDoctor = confirmationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getDoctorEntity() {
        return doctor;
    }

    public void setDoctorEntity(User doctor) {
        this.doctor = doctor;
    }
}
