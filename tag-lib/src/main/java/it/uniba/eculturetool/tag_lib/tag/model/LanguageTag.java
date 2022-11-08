package it.uniba.eculturetool.tag_lib.tag.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LanguageTag extends Tag {
    private String language;

    public static final Creator<LanguageTag> CREATOR = new Creator<LanguageTag>() {
        @Override
        public LanguageTag createFromParcel(Parcel in) {
            return new LanguageTag(in);
        }

        @Override
        public LanguageTag[] newArray(int size) {
            return new LanguageTag[size];
        }
    };

    protected LanguageTag(Parcel in) {
        super(in);
        language = in.readString();
    }

    public LanguageTag(int id, String title, String language) {
        super(id, title);
        this.language = language;
    }

    public LanguageTag(int id, String title, long color, String language) {
        super(id, title, color);
        this.language = language;
    }

    public LanguageTag(int id, String title, long color, long icon, String language) {
        super(id, title, color, icon);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(language);
    }
}
