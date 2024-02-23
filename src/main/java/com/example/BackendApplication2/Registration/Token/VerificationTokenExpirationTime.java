package com.example.BackendApplication2.Registration.Token;

import java.util.Calendar;
import java.util.Date;

public class VerificationTokenExpirationTime {
    private static final int EXPIRATION_TIME = 10;
    public static Date getExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(calendar.MINUTE, EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }
}
