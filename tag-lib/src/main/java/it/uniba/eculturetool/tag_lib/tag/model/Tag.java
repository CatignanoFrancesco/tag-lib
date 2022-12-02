package it.uniba.eculturetool.tag_lib.tag.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;

public class Tag implements Serializable, Parcelable {
    private int id;
    private String title;
    private long colorResource; // Il valore sarà qualcosa tipo R.color.x
    private long iconResource;  // Il valore sarà qualcosa tipo R.drawable.y
    private Bitmap icon;
    private String iconName;
    private String color;

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
        color = in.readString();
        icon = in.readParcelable(getClass().getClassLoader());

        iconName = title.toLowerCase();
    }

    public Tag(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Deprecated
    public Tag(int id, String title, long color) {
        this.id = id;
        this.title = title;
        this.colorResource = color;
    }

    public Tag(int id, String title, String color) {
        this.id = id;
        this.title = title;
        this.color = color;
    }

    @Deprecated
    public Tag(int id, String title, long color, long icon) {
        this.id = id;
        this.title = title;
        this.colorResource = color;
        this.iconResource = icon;
    }

    public Tag(int id, String title, String color, Bitmap icon) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.icon = icon;

        iconName = title.toLowerCase();
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
        iconName = title.toLowerCase();
    }

    @Deprecated
    public long getColor() {
        return colorResource;
    }

    public String getColorString() {
        return color;
    }

    @Deprecated
    public void setColor(long color) {
        this.colorResource = color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Deprecated
    public long getIcon() {
        return iconResource;
    }

    public Bitmap getIconBitmap() {
        return icon;
    }

    public String getIconName() {
        return iconName;
    }

    @Deprecated
    public void setIcon(long icon) {
        this.iconResource = icon;
    }

    public void setIcon(Bitmap icon) {
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
        parcel.writeString(color);
        parcel.writeParcelable(icon, i);
    }
}
