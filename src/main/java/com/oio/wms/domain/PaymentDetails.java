package com.oio.wms.domain;

import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

public class PaymentDetails {

    private String digits;
    private LocalDate expiryDate;
    @ManyToOne
    @JoinTable(name = "user_id")
    private User user;

    public String getDigits() {
        return digits;
    }

    public void setDigits(String digits) {
        this.digits = digits;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
