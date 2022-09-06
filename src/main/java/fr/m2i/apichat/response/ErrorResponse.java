
package fr.m2i.apichat.response;

import java.time.LocalDateTime;
import java.util.Calendar;

public class ErrorResponse {

    //private Calendar calendar;
    private String time;
    private String error;
    private int status;
    private String path;

    public ErrorResponse(String error, int status, String path) {
        //this.calendar = Calendar.getInstance();//Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        this.time = LocalDateTime.now().toString();
        this.error = error;
        this.status = status;
        this.path = path;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}