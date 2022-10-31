package it.uniba.eculturetool.tag_lib.textmaker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import it.uniba.eculturetool.tag_lib.textmaker.model.TranslatedText;

public class Translations {
    @SerializedName("translations")
    @Expose
    private List<TranslatedText> translatedTextList = null;

    public List<TranslatedText> getTranslatedTextList() {
        return translatedTextList;
    }

    public void setTranslatedTextList(List<TranslatedText> translatedTextList) {
        this.translatedTextList = translatedTextList;
    }
}
