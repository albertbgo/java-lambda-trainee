package org.example.entity;

public class Band {

    private Integer bandId;
    private String bandName;
    private String bandGenre;
    private String bandYearInit;

    public Band() {
    }

    public Band(Integer bandId, String bandName, String bandGenre, String bandYearInit) {
        this.bandId = bandId;
        this.bandName = bandName;
        this.bandGenre = bandGenre;
        this.bandYearInit = bandYearInit;
    }

    public Integer getBandId() {
        return bandId;
    }

    public void setBandId(Integer bandId) {
        this.bandId = bandId;
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
}
