
package com.hnews.app.models;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Title implements Parcelable
{

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("matchLevel")
    @Expose
    private String matchLevel;
    @SerializedName("fullyHighlighted")
    @Expose
    private Boolean fullyHighlighted;
    @Ignore
    @SerializedName("matchedWords")
    @Expose
    private List<String> matchedWords = null;
    public final static Creator<Title> CREATOR = new Creator<Title>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Title createFromParcel(Parcel in) {
            return new Title(in);
        }

        public Title[] newArray(int size) {
            return (new Title[size]);
        }

    }
    ;

    protected Title(Parcel in) {
        this.value = ((String) in.readValue((String.class.getClassLoader())));
        this.matchLevel = ((String) in.readValue((String.class.getClassLoader())));
        this.fullyHighlighted = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.matchedWords, (String.class.getClassLoader()));
    }

    public Title() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMatchLevel() {
        return matchLevel;
    }

    public void setMatchLevel(String matchLevel) {
        this.matchLevel = matchLevel;
    }

    public Boolean getFullyHighlighted() {
        return fullyHighlighted;
    }

    public void setFullyHighlighted(Boolean fullyHighlighted) {
        this.fullyHighlighted = fullyHighlighted;
    }

    public List<String> getMatchedWords() {
        return matchedWords;
    }

    public void setMatchedWords(List<String> matchedWords) {
        this.matchedWords = matchedWords;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(value);
        dest.writeValue(matchLevel);
        dest.writeValue(fullyHighlighted);
        dest.writeList(matchedWords);
    }

    public int describeContents() {
        return  0;
    }

}
