package it.uniba.eculturetool.tag_lib.interfaces;

import java.util.List;

import it.uniba.eculturetool.tag_lib.model.TranslatedText;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Translator {
    @POST("/v2/translate")
    Call<List<TranslatedText>> translate(@Header("Authorization: DeepL-Auth-Key ") String authKey, @Field("text") String sourceText, @Field("target_lang") String targetLang);
}
