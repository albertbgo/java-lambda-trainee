package org.example.entity.request;

public class BandRequest {
    private String uid;
    private Integer id;
    private String bandName;
    private String bandGenre;
    private String bandYearInit;

    public BandRequest() {
    }

    public BandRequest(String uid, Integer id, String bandName, String bandGenre, String bandYearInit) {
        this.id = id;
        this.uid = uid;
        this.bandName = bandName;
        this.bandGenre = bandGenre;
        this.bandYearInit = bandYearInit;
    }

    public String getUid() {
        return uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getBandGenre() {
        return bandGenre;
    }

    public void setBandGenre(String bandGenre) {
        this.bandGenre = bandGenre;
    }

    public String getBandYearInit() {
        return bandYearInit;
    }

    public void setBandYearInit(String bandYearInit) {
        this.bandYearInit = bandYearInit;
    }

    @Override
    public String toString() {
        return "BandRequest{" +
                "uid='" + uid + '\'' +
                ", bandName='" + bandName + '\'' +
                ", bandGenre='" + bandGenre + '\'' +
                ", bandYearInit='" + bandYearInit + '\'' +
                '}';
    }
}
