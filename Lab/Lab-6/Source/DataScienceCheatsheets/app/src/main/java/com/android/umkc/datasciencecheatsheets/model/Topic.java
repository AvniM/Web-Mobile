package com.android.umkc.datasciencecheatsheets.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 */
public class Topic implements Parcelable {

    private int id;
    private String name;
    private String count;

    public Topic() {
    }

    protected Topic(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.count = in.readString();
    }

    public static final Parcelable.Creator<Topic> CREATOR = new Parcelable.Creator<Topic>() {
        @Override
        public Topic createFromParcel(Parcel source) {
            return new Topic(source);
        }

        @Override
        public Topic[] newArray(int size) {
            return new Topic[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.count);
    }

}