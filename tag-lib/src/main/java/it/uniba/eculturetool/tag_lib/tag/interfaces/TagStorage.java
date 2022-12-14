package it.uniba.eculturetool.tag_lib.tag.interfaces;

import java.util.Set;

import it.uniba.eculturetool.tag_lib.tag.model.LanguageTag;
import it.uniba.eculturetool.tag_lib.tag.model.Tag;

/**
 * Questa interfaccia contiene tutti i metodi per memorizzare in un database di qualsiasi genere, i dati relativi ai tag.
 */
public interface TagStorage {
    void saveAllTags(Set<Tag> tags);
    void saveTag(Tag tag);
    Set<Tag> getAllTags();
    Set<Tag> getGeneralTags();
    Set<LanguageTag> getLanguageTags();
    Set<LanguageTag> getPlaceLanguages(int placeId);
    void addPlaceLanguages(int placeId, Set<LanguageTag> languageTags);
    void deletePlaceLanguages(int placeId, Set<LanguageTag> languageTags);
    Set<Tag> getPathTags(int pathId);
    void addPathTags(int pathId, Set<Tag> tags);
    void deletePathTags(int pathId, Set<Tag> tags);
    void updateTag(Tag tag);
    void deleteTagById(int id);
}
