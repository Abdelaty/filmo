package com.example.filmo.Model.cast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CastResult implements Parcelable {

    public final static Parcelable.Creator<CastResult> CREATOR = new Creator<CastResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CastResult createFromParcel(Parcel in) {
            return new CastResult(in);
        }

        public CastResult[] newArray(int size) {
            return (new CastResult[size]);
        }

    };
    @SerializedName("cast_id")
    @Expose
    private Integer castId;
    @SerializedName("character")
    @Expose
    private String character;
    @SerializedName("credit_id")
    @Expose
    private String creditId;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;

    protected CastResult(Parcel in) {
        this.castId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.character = ((String) in.readValue((String.class.getClassLoader())));
        this.creditId = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.order = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.profilePath = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CastResult() {
    }

    public Integer getCastId() {
        return castId;
    }

    public void setCastId(Integer castId) {
        this.castId = castId;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(castId);
        dest.writeValue(character);
        dest.writeValue(creditId);
        dest.writeValue(gender);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(order);
        dest.writeValue(profilePath);
    }

    public int describeContents() {
        return 0;
    }

}
