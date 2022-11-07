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
        TextMaker textMaker = TextMaker.getInstance(getString(R.string.auth_key));
        List<LanguageTag> tags = new ArrayList<>();
        String source = "La galleria degli Uffizi è il museo piu famoso d'Italia";

        tags.add(new LanguageTag(0, Languages.get(Languages.ENGLISH), Languages.ENGLISH));
        tags.add(new LanguageTag(1, Languages.get(Languages.SPANISH), Languages.GERMAN));

        for(LanguageTag languageTag : tags) {
            textMaker.generateText(
                    source,
                    languageTag,
                    bundle -> Log.d(TAG, "testTranslation: " + bundle.getString(languageTag.getLanguage())),
                    tag -> Log.e(TAG, "testTranslation: error on: " + tag.getTitle())
            );
        }
    }
}