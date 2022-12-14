package org.example.entity.response;

public class BandResponse {

    private String uid;
    private Integer bandId;
    private String message;

    public BandResponse(String uid, Integer bandId, String message) {
        this.uid = uid;
        this.bandId = bandId;
        this.message = message;
    }

    public BandResponse() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getBandId() {
        return bandId;
    }

    public void setBandId(Integer bandId) {
        this.bandId = bandId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
