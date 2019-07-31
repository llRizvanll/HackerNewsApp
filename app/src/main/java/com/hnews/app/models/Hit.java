
package com.hnews.app.models;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Hit", indices = {@Index(value = "title", unique = true)})
public class Hit implements Parcelable
{
    @PrimaryKey(autoGenerate = true)
    @Expose(serialize = false, deserialize = false)
    public int id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("points")
    @Expose
    private Integer points;
    @Ignore
    @SerializedName("story_text")
    @Expose
    private Object storyText;
    @Ignore
    @SerializedName("comment_text")
    @Expose
    private Object commentText;
    @SerializedName("num_comments")
    @Expose
    private Integer numComments;
    @Ignore
    @SerializedName("story_id")
    @Expose
    private Object storyId;
    @SerializedName("story_title")
    @Expose
    private String storyTitle;
    @SerializedName("story_url")
    @Expose
    private String storyUrl;
    @Ignore
    @SerializedName("parent_id")
    @Expose
    private Object parentId;
    @SerializedName("created_at_i")
    @Expose
    private Integer createdAtI;
    @SerializedName("relevancy_score")
    @Expose
    private Integer relevancyScore;
    @Ignore
    @SerializedName("_tags")
    @Expose
    private List<String> tags;
    @SerializedName("objectID")
    @Expose
    private String objectID;
    @Ignore
    @SerializedName("_highlightResult")
    @Embedded
    @Expose
    private HighlightResult highlightResult;
    public final static Creator<Hit> CREATOR = new Creator<Hit>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Hit createFromParcel(Parcel in) {
            return new Hit(in);
        }

        public Hit[] newArray(int size) {
            return (new Hit[size]);
        }

    }
    ;

    protected Hit(Parcel in) {
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.author = ((String) in.readValue((String.class.getClassLoader())));
        this.points = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.storyText = ((Object) in.readValue((Object.class.getClassLoader())));
        this.commentText = ((Object) in.readValue((Object.class.getClassLoader())));
        this.numComments = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.storyId = ((Object) in.readValue((Object.class.getClassLoader())));
        this.storyTitle = ((String) in.readValue((Object.class.getClassLoader())));
        this.storyUrl = ((String) in.readValue((Object.class.getClassLoader())));
        this.parentId = ((Object) in.readValue((Object.class.getClassLoader())));
        this.createdAtI = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.relevancyScore = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.tags, (String.class.getClassLoader()));
        this.objectID = ((String) in.readValue((String.class.getClassLoader())));
        this.highlightResult = ((HighlightResult) in.readValue((HighlightResult.class.getClassLoader())));
    }

    public Hit() {
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Object getStoryText() {
        return storyText;
    }

    public void setStoryText(Object storyText) {
        this.storyText = storyText;
    }

    public Object getCommentText() {
        return commentText;
    }

    public void setCommentText(Object commentText) {
        this.commentText = commentText;
    }

    public Integer getNumComments() {
        return numComments;
    }

    public void setNumComments(Integer numComments) {
        this.numComments = numComments;
    }

    public Object getStoryId() {
        return storyId;
    }

    public void setStoryId(Object storyId) {
        this.storyId = storyId;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getStoryUrl() {
        return storyUrl;
    }

    public void setStoryUrl(String storyUrl) {
        this.storyUrl = storyUrl;
    }

    public Object getParentId() {
        return parentId;
    }

    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }

    public Integer getCreatedAtI() {
        return createdAtI;
    }

    public void setCreatedAtI(Integer createdAtI) {
        this.createdAtI = createdAtI;
    }

    public Integer getRelevancyScore() {
        return relevancyScore;
    }

    public void setRelevancyScore(Integer relevancyScore) {
        this.relevancyScore = relevancyScore;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public HighlightResult getHighlightResult() {
        return highlightResult;
    }

    public void setHighlightResult(HighlightResult highlightResult) {
        this.highlightResult = highlightResult;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(createdAt);
        dest.writeValue(title);
        dest.writeValue(url);
        dest.writeValue(author);
        dest.writeValue(points);
        dest.writeValue(storyText);
        dest.writeValue(commentText);
        dest.writeValue(numComments);
        dest.writeValue(storyId);
        dest.writeValue(storyTitle);
        dest.writeValue(storyUrl);
        dest.writeValue(parentId);
        dest.writeValue(createdAtI);
        dest.writeValue(relevancyScore);
        dest.writeList(tags);
        dest.writeValue(objectID);
        dest.writeValue(highlightResult);
    }

    public int describeContents() {
        return  0;
    }

}
