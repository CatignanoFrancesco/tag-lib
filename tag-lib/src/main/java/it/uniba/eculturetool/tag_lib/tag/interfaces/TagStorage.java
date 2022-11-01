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
    Set<Tag> getPathTags(int pathId);
    void updateTag(Tag tag);
    void deleteTagById(int id);
}
