package it.uniba.eculturetool.tag_lib.facade;

import android.os.StrictMode;
import android.util.Log;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniba.eculturetool.tag_lib.interfaces.FailureListener;
import it.uniba.eculturetool.tag_lib.interfaces.SuccessDataListener;
import it.uniba.eculturetool.tag_lib.interfaces.Translator;
import it.uniba.eculturetool.tag_lib.model.LanguageTag;
import it.uniba.eculturetool.tag_lib.model.TranslatedText;
import it.uniba.eculturetool.tag_lib.model.Translations;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Questa classe si occupa di generare dei testi nelle varie lingue partendo da uno in una lingua casuale
 */
public class TextMaker {
    private static final String TAG = TextMaker.class.getSimpleName();
    private static final String BASE_URL = "https://api-free.deepl.com";

    private static TextMaker instance;
    private static String authKey;
    private Map<String, String> texts = new HashMap<>();

    public void generateTexts(String sourceText, List<LanguageTag> tags, SuccessDataListener<Map<String, String>> successListener, FailureListener failureListener) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Translator translator = retrofit.create(Translator.class);

        Call<Translations> call = translator.translate("DeepL-Auth-Key " + authKey, sourceText, tags.get(0).getLanguage());
        call.enqueue(new Callback<Translations>() {
            @Override
            public void onResponse(Call<Translations> call, Response<Translations> response) {
                Translations translations = response.body();

                for(TranslatedText translatedText : translations.getTranslatedTextList()) {
                    texts.put(tags.get(0).getLanguage(), translatedText.getText());
                }
                successListener.execute(texts);
            }

            @Override
            public void onFailure(Call<Translations> call, Throwable t) {
                Log.e(TAG, "onFailure: message: " + call + ", exception: ", t);
                failureListener.execute(t.getMessage());
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