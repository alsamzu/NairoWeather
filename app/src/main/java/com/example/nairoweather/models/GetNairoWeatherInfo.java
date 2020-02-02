
package com.example.nairoweather.models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetNairoWeatherInfo implements Serializable
{

    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("currently")
    @Expose
    private Currently currently;
    @SerializedName("hourly")
    @Expose
    private HourlyInfo hourly;
    @SerializedName("daily")
    @Expose
    private DailyInfo daily;
    @SerializedName("flags")
    @Expose
    private Flags flags;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    private final static long serialVersionUID = 130411951167441969L;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetNairoWeatherInfo() {
    }

    /**
     *
     * @param currently
     * @param offset
     * @param timezone
     * @param latitude
     * @param daily
     * @param flags
     * @param hourly
     * @param longitude
     */
    public GetNairoWeatherInfo(Double latitude, Double longitude, String timezone, Currently currently, HourlyInfo hourly, DailyInfo daily, Flags flags, Integer offset) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.currently = currently;
        this.hourly = hourly;
        this.daily = daily;
        this.flags = flags;
        this.offset = offset;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    public HourlyInfo getHourly() {
        return hourly;
    }

    public void setHourly(HourlyInfo hourly) {
        this.hourly = hourly;
    }

    public DailyInfo getDaily() {
        return daily;
    }

    public void setDaily(DailyInfo daily) {
        this.daily = daily;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

}
