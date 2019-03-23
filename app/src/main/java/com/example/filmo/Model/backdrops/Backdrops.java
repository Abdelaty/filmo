package com.example.filmo.Model.backdrops;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Backdrops {

    @SerializedName("id")
    @Expose
    private Double id;
    @SerializedName("backdrops")
    @Expose
    private List<BackdropResult> backdrops = null;
    @SerializedName("posters")
    @Expose
    private List<Poster> posters = null;

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public List<BackdropResult> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<BackdropResult> backdrops) {
        this.backdrops = backdrops;
    }

    public List<Poster> getPosters() {
        return posters;
    }

    public void setPosters(List<Poster> posters) {
        this.posters = posters;
    }

}
