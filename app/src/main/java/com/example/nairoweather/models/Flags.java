
package com.example.nairoweather.models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flags implements Serializable
{

    @SerializedName("sources")
    @Expose
    private List<String> sources = null;
    @SerializedName("units")
    @Expose
    private String units;
    private final static long serialVersionUID = 4467226863679242054L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Flags() {
    }

    /**
     *
     * @param sources
     * @param units
     */
    public Flags(List<String> sources, String units) {
        super();
        this.sources = sources;
        this.units = units;
    }

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

}
