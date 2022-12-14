package org.example.entity.response;

import org.example.entity.Band;

import java.util.List;

public class BandResponse {

    private String uid;
    private List<Band> bands;

    public BandResponse() {
    }

    public BandResponse(String uid, List<Band> bands) {
        this.uid = uid;
        this.bands = bands;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Band> getBands() {
        return bands;
    }

    public void setBands(List<Band> bands) {
        this.bands = bands;
    }
}