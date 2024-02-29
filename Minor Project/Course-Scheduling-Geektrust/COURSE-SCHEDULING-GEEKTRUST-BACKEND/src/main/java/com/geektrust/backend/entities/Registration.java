package com.geektrust.backend.entities;

public class Registration  implements Comparable<Registration> {
    private final String regId;
    private final String emailAddress;
    private final String courseOfferingId;
    private boolean isAccepted;

    public Registration(String regId, String emailAddress, String courseOfferingId, boolean isAccepted) {
        this.regId = regId;
        this.emailAddress = emailAddress;
        this.courseOfferingId = courseOfferingId;
        this.isAccepted = false;
    }

    public String getRegId() {
        return regId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getCourseOfferingId() {
        return courseOfferingId;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    @Override
    public String toString() {
        return "Registration [courseOfferingId=" + courseOfferingId + ", emailAddress="
                + emailAddress + ", isAccepted=" + isAccepted + ", regId=" + regId + "]";
    }

   
    
    @Override
    public int compareTo(Registration c) {
        return this.emailAddress.compareTo(c.emailAddress);
    }
    
    
}
