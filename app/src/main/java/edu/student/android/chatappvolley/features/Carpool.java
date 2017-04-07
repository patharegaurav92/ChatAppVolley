package edu.student.android.chatappvolley.features;

/**
 * Created by Gaurav on 07-04-2017.
 */

public class Carpool {
    private String slocation;
    private String dlocation;
    private String date;

    public Carpool(String slocation, String dlocation, String date) {
        this.slocation = slocation;
        this.dlocation = dlocation;
        this.date = date;
    }

    public String getSlocation() {
        return slocation;
    }

    public void setSlocation(String slocation) {
        this.slocation = slocation;
    }

    public String getDlocation() {
        return dlocation;
    }

    public void setDlocation(String dlocation) {
        this.dlocation = dlocation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
