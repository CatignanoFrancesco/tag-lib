package it.uniba.eculturetool.tag_lib.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TranslatedText {
    @SerializedName("detecetd_source_language")
    @Expose
    private String sourceLanguage;

    @SerializedName("text")
    @Expose
    private String text;

    public String getText() {
        return text;
    }
}
