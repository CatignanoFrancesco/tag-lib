package it.uniba.eculturetool.tag_lib.textmaker.interfaces;

import it.uniba.eculturetool.tag_lib.textmaker.model.Translations;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Translator {
    @FormUrlEncoded
    @POST("/v2/translate")
    Call<Translations> translate(@Header("Authorization") String authKey, @Field("text") String sourceText, @Field("target_lang") String targetLang);
}
