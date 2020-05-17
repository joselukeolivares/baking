package com.example.baking;

import android.os.Parcel;
import android.os.Parcelable;

class steps implements Parcelable {

    int id;
    String shortDescription;
    String description;
    String videoURL;
    String thubnailURL;

    protected steps(Parcel in) {
        id = in.readInt();
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thubnailURL = in.readString();
    }

    public static final Creator<steps> CREATOR = new Creator<steps>() {
        @Override
        public steps createFromParcel(Parcel in) {
            return new steps(in);
        }

        @Override
        public steps[] newArray(int size) {
            return new steps[size];
        }
    };

    public steps() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThubnailURL() {
        return thubnailURL;
    }

    public void setThubnailURL(String thubnailURL) {
        this.thubnailURL = thubnailURL;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(thubnailURL);
    }
}
