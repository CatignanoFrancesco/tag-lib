package it.uniba.eculturetool.tag_lib.tag.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;

public class Tag implements Serializable, Parcelable {
    private int id;
    private String title;
    private long color; // Il valore sarà qualcosa tipo R.color.x
    private long icon;   // Il valore sarà qualcosa tipo R.drawable.y

    public static final Creator<Tag> CREATOR = new Creator<Tag>() {
        @Override
        public Tag createFromParcel(Parcel in) {
            return new Tag(in);
        }

        @Override
        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };

    protected Tag(Parcel in) {
        id = in.readInt();
        title = in.readString();
        color = in.readLong();
        icon = in.readLong();
    }

    public Tag(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Tag(int id, String title, long color) {
        this.id = id;
        this.title = title;
        this.color = color;
    }

    public Tag(int id, String title, long color, long icon) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getColor() {
        return color;
    }

    public void setColor(long color) {
        this.color = color;
    }

    public long getIcon() {
        return icon;
    }

    public void setIcon(long icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return id == tag.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeLong(color);
        parcel.writeLong(icon);
    }
}
