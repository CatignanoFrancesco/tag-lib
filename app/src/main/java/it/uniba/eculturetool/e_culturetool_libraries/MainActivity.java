package it.uniba.eculturetool.e_culturetool_libraries;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import it.uniba.eculturetool.tag_lib.tag.Languages;
import it.uniba.eculturetool.tag_lib.textmaker.facade.TextMaker;
import it.uniba.eculturetool.tag_lib.tag.model.LanguageTag;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testTranslation();
    }

    private void testTranslation() {
        TextMaker textMaker = TextMaker.getInstance(null);
        List<LanguageTag> tags = new ArrayList<>();
        String source = "Il Louvre è il museo più famoso al mondo. Ogni anno conta circa 9,6 milioni di visitatori!";

        tags.add(new LanguageTag(0, Languages.get(Languages.ENGLISH), Languages.ENGLISH));
        textMaker.generateTexts(
                source,
                tags,
                texts -> Log.d(TAG, "testTranslation: source: " + source + "\ntranslated text: " + texts.get(Languages.ENGLISH)),
                err -> {}
        );
    }
}