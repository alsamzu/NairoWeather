
package com.example.nairoweather.models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyInfo implements Serializable
{

    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("data")
    @Expose
    private List<DailyData> data = null;
    private final static long serialVersionUID = 1119938953041055753L;

    /**
     * No args constructor for use in serialization
     *
     */
    public DailyInfo() {
    }

    /**
     *
     * @param summary
     * @param data
     * @param icon
     */
    public DailyInfo(String summary, String icon, List<DailyData> data) {
        super();
        this.summary = summary;
        this.icon = icon;
        this.data = data;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<DailyData> getData() {
        return data;
    }

    public void setData(List<DailyData> data) {
        this.data = data;
    }

}
