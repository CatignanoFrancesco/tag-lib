package it.uniba.eculturetool.tag_lib.viewhelpers;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniba.eculturetool.tag_lib.tag.model.LanguageTag;
import it.uniba.eculturetool.tag_lib.tag.model.Tag;

/**
 * Classe che gestisce i dati mostrati nell'interfaccia per i tag di tipo lingua
 */
public class LanguageTagViewData extends TagViewData{
    private String mainLanguageCode;    // Questo attributo indica il codice
    private MutableLiveData<List<LanguageTag>> addedLanguages = new MutableLiveData<>(new ArrayList<>());   // Lingue aggiunte al chip group
    private List<Tag> dialogTags = new ArrayList<>(); // Tag da inserire nel dialog
    private Map<String, String> descriptions = new HashMap<>();  // Conterrà le descrizioni nelle varie lingue
    private List<LanguageTag> languages;

    /**
     * Costruttore della classe LanguageTagViewData.
     * @param mainLanguageCode Il codice della lingua principale. La lingua principale è quella che verrà usata come riferimento per tradurre tutti i testi.
     */
    public LanguageTagViewData(String mainLanguageCode) {
        this.mainLanguageCode = mainLanguageCode;
    }

    /**
     * Costruttore della classe LanguageTagViewData.
     * @param mainLanguageCode Il codice della lingua principale. La lingua principale è quella che verrà usata come riferimento per tradurre tutti i testi.
     * @param languages I tag delle lingue supportate.
     */
    public LanguageTagViewData(String mainLanguageCode, List<LanguageTag> languages) {
        this(mainLanguageCode);
        this.languages = languages;
    }

    public void initLanguages() {
        for(LanguageTag languageTag : languages) {
            if(mainLanguageCode.equals(languageTag.getLanguage())) addedLanguages.getValue().add(languageTag);
            else dialogTags.add(languageTag);
        }
    }

    public void initLanguages(List<LanguageTag> languageTags) {
        for(LanguageTag languageTag : languages) {
            if(languageTags.contains(languageTag)) addedLanguages.getValue().add(languageTag);
            else dialogTags.add(languageTag);
        }
    }

    /**
     * Restituisce le lingue scelte
     * @return Le lingue scelte
     */
    public MutableLiveData<List<LanguageTag>> getAddedLanguages() {
        return this.addedLanguages;
    }

    /**
     * Restituisce le lingue di cui bisogna fare la traduzione, quindi tutte le lingue aggiunte ad eccezione deella lingua principale
     * @return
     */
    public List<LanguageTag> getTargetLanguages() {
        List<LanguageTag> languages = new ArrayList<>(addedLanguages.getValue());
        languages.removeIf(languageTag -> languageTag.getLanguage().equals(mainLanguageCode));
        return languages;
    }

    /**
     * Restituisce i tag presenti nel dialog e che si possono selezionare
     * @return I tag nel dialog
     */
    public List<Tag> getDialogTags() {
        return this.dialogTags;
    }

    /**
     * Restituisce tutte le descrizioni per ogni lingua.
     * @return Le descrizioni
     */
    public Map<String, String> getDescriptions() {
        return descriptions;
    }

    /**
     * Imposta le descrizioni
     * @param descriptions Le descrizioni da impostare
     */
    public void setDescriptions(Map<String, String> descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * Aggiunge un tag
     * @param tag Il tag da aggiungere
     */
    @Override
    public void addTag(Tag tag) {
        String language = ((LanguageTag) tag).getLanguage();
        addedLanguages.getValue().add((LanguageTag) tag);
        if(!descriptions.containsKey(language)) descriptions.put(language, ""); // la descrizione viene aggiunta soltanto se non esiste per evitare che venga sostituita con una lingua vuota
        dialogTags.remove(tag);
        notifyObserver();
    }

    /**
     * Rimuove un tag
     * @param tag Il tag da rimuovere
     */
    @Override
    public void removeTag(Tag tag) {
        dialogTags.add(tag);
        addedLanguages.getValue().remove(tag);
        notifyObserver();

        String language = ((LanguageTag) tag).getLanguage();
        descriptions.remove(language);

        // Se il tag appena rimosso è quello della lingua principale, la nuova lingua principale, diventa il primo tag inserito
        if(language.equals(mainLanguageCode)) mainLanguageCode = (addedLanguages.getValue().get(0)).getLanguage();
    }

    /**
     * Verifica che le descrizioni non siano tutte vuote
     * @return true se le descrizioni sono tutte vuote, false altrimenti
     */
    public boolean areDescriptionsEmpty() {
        for(Map.Entry<String, String> entry : descriptions.entrySet()) {
            if(!entry.getValue().isEmpty()) return false;
        }
        return true;
    }

    public boolean areDescriptionsEmptyExcept(String language) {
        for(Map.Entry<String, String> entry : descriptions.entrySet()) {
            if(!entry.getKey().equals(language) && !entry.getValue().isEmpty()) return false;
        }
        return true;
    }

    private void notifyObserver() {
        addedLanguages.setValue(addedLanguages.getValue());
    }
}
