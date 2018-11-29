package com.example.android.diego_baking_app.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Steps implements Parcelable {
    private int id;
    private String description;
    private String videoUrl;
    private String thumbnailUrl;

    public Steps(int id, String description, String videoUrl, String thumbnailUrl){
        this.id = id;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    //ID getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //Description getter and setter
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    //VideoUrl getter and setter
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }


    //ThumbnailUrl getter and setter
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }


    //Parcelable methods and overrides
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(description);
        parcel.writeString(videoUrl);
        parcel.writeString(thumbnailUrl);
    }

    protected Steps(Parcel parcelItem){
        this.id = parcelItem.readInt();
        this.description = parcelItem.readString();
        this.videoUrl = parcelItem.readString();
        this.thumbnailUrl = parcelItem.readString();
    }

    public static final Parcelable.Creator<Steps>CREATOR = new Parcelable.Creator<Steps>(){

        @Override
        public Steps createFromParcel(Parcel parcel){
            return new Steps(parcel);
        }

        @Override
        public Steps[] newArray(int i) {
            return new Steps[i];
        }
    };
}

