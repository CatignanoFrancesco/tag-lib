package it.uniba.eculturetool.tag_lib.viewhelpers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.uniba.eculturetool.tag_lib.tag.model.Tag;

public class TagViewData {
    private Tag mainTag;
    private List<Tag> tags;
    private MutableLiveData<List<Tag>> addedTags = new MutableLiveData<>(new ArrayList<>());
    private List<Tag> dialogTags = new ArrayList<>();

    /**
     * Costruttore vuoto in modo da permettere l'override
     */
    protected TagViewData() {}

    /**
     * Costruttore di TagViewData
     * @param tags Tutti i tag disponibili
     */
    public TagViewData(List<Tag> tags) {
        this.tags = tags;
        mainTag = tags.get(0);  // Imposto come tag principale il primo
    }

    /**
     * Inizializza i tag inseriti
     */
    public void initTags() {
        initTags(Collections.singletonList(mainTag));
    }

    /**
     * Inizializza i tag inseriti con quelli passati come parametro
     */
    public void initTags(List<Tag> tagsToAdd) {
        for(Tag tag : tags) {
            if(tagsToAdd.contains(tag)) addedTags.getValue().add(tag);
            else dialogTags.add(tag);
        }
    }

    /**
     * Restituisce i tag scelti
     * @return I tag scelti
     */
    public LiveData<List<Tag>> getAddedTags() {
        return addedTags;
    }

    /**
     * Restituisce i tag disponibili nel dialog
     * @return I tag disponibili nel dialog
     */
    public List<Tag> getDialogTags() {
        return dialogTags;
    }

    /**
     * Aggiunge un tag
     * @param tag Il tag da aggiungere
     */
    public void addTag(Tag tag) {
        addedTags.getValue().add(tag);
        dialogTags.remove(tag);
        notifyObserver();
    }

    /**
     * Rimuove un tag. Se il tag rimosso è il principale, viene reso principale il primo tag disponibile
     * @param tag Il tag da rimuovere
     */
    public void removeTag(Tag tag) {
        dialogTags.add(tag);
        addedTags.getValue().remove(tag);
        notifyObserver();

        // Se è stato rimosso un tag principale, rendo il primo tag quello principale
        if(tag.equals(mainTag)) mainTag = addedTags.getValue().get(0);
    }

    private void notifyObserver() {
        addedTags.setValue(addedTags.getValue());
    }
}

