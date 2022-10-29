package it.uniba.eculturetool.tag_lib.facade;

import java.util.Set;

import it.uniba.eculturetool.tag_lib.model.LanguageTag;
import it.uniba.eculturetool.tag_lib.model.Tag;

/**
 * Questa interfaccia contiene tutti i metodi per memorizzare in un database di qualsiasi genere, i dati relativi ai tag.
 */
public interface TagStorage {
    void saveAllTags(Set<Tag> tags);
    void saveTag(Tag tag);
    Set<Tag> getAllTags();
    Set<Tag> getGeneralTags();
    Set<LanguageTag> getLanguageTags();
    void updateTag(Tag tag);
    void deleteTagById(int id);
}
