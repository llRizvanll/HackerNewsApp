
package com.hnews.app.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import androidx.room.Embedded;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HighlightResult implements Parcelable
{
    @Embedded
    @SerializedName("title")
    @Expose
    private Title title;
    @Embedded
    @SerializedName("url")
    @Expose
    private Url url;
    @Embedded
    @SerializedName("author")
    @Expose
    private Author author;
    @Embedded
    @SerializedName("story_text")
    @Expose
    private StoryText storyText;
    public final static Creator<HighlightResult> CREATOR = new Creator<HighlightResult>() {


        @SuppressWarnings({
            "unchecked"
        })
        public HighlightResult createFromParcel(Parcel in) {
            return new HighlightResult(in);
        }

        public HighlightResult[] newArray(int size) {
            return (new HighlightResult[size]);
        }

    }
    ;

    protected HighlightResult(Parcel in) {
        this.title = ((Title) in.readValue((Title.class.getClassLoader())));
        this.url = ((Url) in.readValue((Url.class.getClassLoader())));
        this.author = ((Author) in.readValue((Author.class.getClassLoader())));
        this.storyText = ((StoryText) in.readValue((StoryText.class.getClassLoader())));
    }

    public HighlightResult() {
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public StoryText getStoryText() {
        return storyText;
    }

    public void setStoryText(StoryText storyText) {
        this.storyText = storyText;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeValue(url);
        dest.writeValue(author);
        dest.writeValue(storyText);
    }

    public int describeContents() {
        return  0;
    }

}
