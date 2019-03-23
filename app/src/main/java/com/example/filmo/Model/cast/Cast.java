
package com.example.filmo.Model.cast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cast implements Parcelable {

    public final static Parcelable.Creator<Cast> CREATOR = new Creator<Cast>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Cast createFromParcel(Parcel in) {
            return new Cast(in);
        }

        public Cast[] newArray(int size) {
            return (new Cast[size]);
        }

    };
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cast")
    @Expose
    private List<CastResult> cast = null;
    @SerializedName("crew")
    @Expose
    private List<Crew> crew = null;

    protected Cast(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.cast, (CastResult.class.getClassLoader()));
        in.readList(this.crew, (Crew.class.getClassLoader()));
    }

    public Cast() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CastResult> getCast() {
        return cast;
    }

    public void setCast(List<CastResult> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(cast);
        dest.writeList(crew);
    }

    public int describeContents() {
        return 0;
    }

}
