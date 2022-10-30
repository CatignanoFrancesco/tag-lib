package it.uniba.eculturetool.tag_lib.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TranslatedText {
    @SerializedName("detected_source_language")
    @Expose
    private String sourceLanguage;

    @SerializedName("text")
    @Expose
    private String text;

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
