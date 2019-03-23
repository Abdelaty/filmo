
package com.example.filmo.Model.trailer;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.filmo.Model.movies.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trailers implements Parcelable {

    public final static Parcelable.Creator<Trailers> CREATOR = new Creator<Trailers>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Trailers createFromParcel(Parcel in) {
            return new Trailers(in);
        }

        public Trailers[] newArray(int size) {
            return (new Trailers[size]);
        }

    };
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<ResultTrailer> results = null;

    protected Trailers(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.results, (Result.class.getClassLoader()));
    }

    public Trailers() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ResultTrailer> getResults() {
        return results;
    }

    public void setResults(List<ResultTrailer> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}
