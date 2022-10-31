package it.uniba.eculturetool.tag_lib.textmaker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TranslatedText {
    @SerializedName("detected_source_language")
    @Expose
    private String detectedSourceLanguage;

    @SerializedName("text")
    @Expose
    private String text;

    public String getDetectedSourceLanguage() {
        return detectedSourceLanguage;
    }

    public void setDetectedSourceLanguage(String detectedSourceLanguage) {
        this.detectedSourceLanguage = detectedSourceLanguage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
