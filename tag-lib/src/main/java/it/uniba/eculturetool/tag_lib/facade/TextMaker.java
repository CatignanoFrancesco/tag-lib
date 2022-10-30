package it.uniba.eculturetool.tag_lib.facade;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniba.eculturetool.tag_lib.interfaces.Translator;
import it.uniba.eculturetool.tag_lib.model.LanguageTag;
import it.uniba.eculturetool.tag_lib.model.TranslatedText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Questa classe si occupa di generare dei testi nelle varie lingue partendo da uno in una lingua casuale
 */
public class TextMaker {
    private static final String TAG = TextMaker.class.getSimpleName();
    private static final String BASE_URL = "https://api-free.deepl.com";

    private static TextMaker instance;
    private static String authKey;
    private Map<String, String> texts;

    public void generateTexts(String sourceText, List<LanguageTag> tags) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();

        Translator translator = retrofit.create(Translator.class);

        Call<List<TranslatedText>> call = translator.translate(authKey, sourceText, tags.get(0).getLanguage());
        call.enqueue(new Callback<List<TranslatedText>>() {
            @Override
            public void onResponse(Call<List<TranslatedText>> call, Response<List<TranslatedText>> response) {
                List<TranslatedText> translatedTextList = response.body();
                texts = new HashMap<>();

                for(TranslatedText translatedText : translatedTextList) {
                    texts.put(tags.get(0).getLanguage(), translatedText.getText());
                }
            }

            @Override
            public void onFailure(Call<List<TranslatedText>> call, Throwable t) {
                Log.e(TAG, "onFailure: message: " + call + ", exception: ", t);
            }
        });
    }

    public Map<String, String> getTexts() {
        return texts;
    }

    public static TextMaker getInstance(String apiAuthKey) {
        if(instance == null) {
            authKey = apiAuthKey;
            instance = new TextMaker();
        }
        return instance;
    }
}
