package it.uniba.eculturetool.tag_lib;

import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import it.uniba.eculturetool.tag_lib.textmaker.facade.TextMaker;
import it.uniba.eculturetool.tag_lib.tag.model.LanguageTag;

public class TextMakerTest {
    private static final String TAG = TextMakerTest.class.getSimpleName();

    @Test
    public void translationFromItToEn() {
        TextMaker textMaker = TextMaker.getInstance(null);
        List<LanguageTag> tags = new ArrayList<>();
        String source = "Il Louvre è il museo più famoso al mondo. Ogni anno conta circa 9,6 milioni di visitatori.";

        tags.add(new LanguageTag(0, "English", "EN"));
        textMaker.generateTexts(
                source,
                tags,
                texts -> {
                    Log.d(TAG, "translationFromItToEn:source: " + source + "\ntranslated text: " + texts.get("EN"));
                    assertTrue(texts.containsKey("EN"));
                },
                err -> {}
        );

    }
}
