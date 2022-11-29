package it.uniba.eculturetool.tag_lib.tag.interfaces;

import java.util.Set;

import it.uniba.eculturetool.tag_lib.interfaces.FailureDataListener;
import it.uniba.eculturetool.tag_lib.interfaces.SuccessDataListener;
import it.uniba.eculturetool.tag_lib.tag.model.LanguageTag;
import it.uniba.eculturetool.tag_lib.tag.model.Tag;

/**
 * Questa interfaccia contiene tutti i metodi per memorizzare ed ottenere in maniera asincrona informazioni sui tag
 */
public interface AsyncTagStorage {
    void saveAllTags(Set<Tag> tags, SuccessDataListener<Void> onSuccessListener, FailureDataListener<String> onFailureListener);
    void saveTag(Tag tag, SuccessDataListener<Integer> onSuccessListener,  FailureDataListener<String> onFailureListener);
    void getAllTags(SuccessDataListener<Set<Tag>> onSuccessListener,  FailureDataListener<String> onFailureListener);
    void getGeneralTags(SuccessDataListener<Set<Tag>> onSuccessListener,  FailureDataListener<String> onFailureListener);
    void getLanguageTags(SuccessDataListener<Set<LanguageTag>> onSuccessListener,  FailureDataListener<String> onFailureListener);
    void getPlaceLanguages(Object placeId, SuccessDataListener<Set<LanguageTag>> onSuccessListener,  FailureDataListener<String> onFailureListener);
    void addPlaceLanguages(Object placeId, Set<LanguageTag> languageTags, SuccessDataListener<Void> onSuccessListener,  FailureDataListener<String> onFailureListener);
    void deletePlaceLanguages(Object placeId, Set<LanguageTag> languageTags, SuccessDataListener<Void> onSuccessListener,  FailureDataListener<String> onFailureListener);
    void getPathTags(Object pathId, SuccessDataListener<Set<Tag>> onSuccessListener,  FailureDataListener<String> onFailureListener);
    void addPathTags(Object pathId, Set<Tag> tags, SuccessDataListener<Void> onSuccessListener,  FailureDataListener<String> onFailureListener);
    void deletePathTags(Object pathId, Set<Tag> tags, SuccessDataListener<Void> onSuccessListener,  FailureDataListener<String> onFailureListener);
    void updateTag(Tag tag, SuccessDataListener<Void> onSuccessListener,  FailureDataListener<String> onFailureListener);
    void deleteTagById(Object id, SuccessDataListener<Void> onSuccessListener,  FailureDataListener<String> onFailureListener);
}
