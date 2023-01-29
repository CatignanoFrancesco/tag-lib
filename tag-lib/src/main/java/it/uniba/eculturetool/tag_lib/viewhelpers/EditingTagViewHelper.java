package it.uniba.eculturetool.tag_lib.viewhelpers;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.lifecycle.LifecycleOwner;

import com.google.android.material.chip.ChipGroup;

import java.util.List;

import it.uniba.eculturetool.tag_lib.R;
import it.uniba.eculturetool.tag_lib.tag.model.LanguageTag;
import it.uniba.eculturetool.tag_lib.tag.model.Tag;

/**
 * Classe che si occupa di aiutare fragment e activity a creare e dare un comportamento a tutte le viste riguardanti i tag
 */
public class EditingTagViewHelper {
    private Context context;
    private TagViewData tagViewData;
    private EditText descriptionEditText;
    private ImageButton translateButton;
    private ChipGroup chipGroup;
    private ChipTag selectedChip;   // tiene traccia dei chip selezionati
    private Button aggiungiTagButton;

    /**
     * Inizializza tutti i dati da usare sul fragment
     * @param context Il contesto
     * @param languageTagViewData Il gestore dei dati del fragment
     * @param descriptionEditText L'EditText della descrizione
     * @param translateButton Il bottone per tradurre
     * @param chipGroup Il ChipGroup che contiene i chip per le lingua
     */
    public EditingTagViewHelper(Context context, LanguageTagViewData languageTagViewData, EditText descriptionEditText, ImageButton translateButton, ChipGroup chipGroup) {
        this.context = context;
        this.tagViewData = languageTagViewData;
        this.descriptionEditText = descriptionEditText;
        this.translateButton = translateButton;
        this.chipGroup = chipGroup;
    }

    /**
     * Inizializza tutti i dati da usare sul fragment
     * @param context Il contesto
     * @param languageTagViewData Il gestore dei dati del fragment
     * @param descriptionEditText L'EditText della descrizione
     * @param translateButton Il bottone per tradurre
     * @param chipGroup Il ChipGroup che contiene i chip per le lingua
     * @param aggiungiLinguaButton Il pulsante per aggiungere una lingua
     */
    public EditingTagViewHelper(Context context, LanguageTagViewData languageTagViewData, EditText descriptionEditText, ImageButton translateButton, ChipGroup chipGroup, Button aggiungiLinguaButton) {
        this(context, languageTagViewData, descriptionEditText, translateButton, chipGroup);
        this.aggiungiTagButton = aggiungiLinguaButton;

        // Osservazione delle lingue aggiunte
        languageTagViewData.getAddedLanguages().observe((LifecycleOwner) context, languageCodes -> {
            // Se sono state aggiunte tutte le lingue...
            aggiungiLinguaButton.setClickable(!languageTagViewData.getDialogTags().isEmpty());
        });
    }

    /**
     * Inizializza tutti i dati da usare sul fragment
     * @param context Il contesto
     * @param tagViewData Il gestore dei dati del fragment
     * @param chipGroup Il ChipGroup che contiene i chip per i tag
     */
    public EditingTagViewHelper(Context context, TagViewData tagViewData, ChipGroup chipGroup) {
        this.context = context;
        this.tagViewData = tagViewData;
        this.chipGroup = chipGroup;

        tagViewData.getAddedTags().observe((LifecycleOwner) context, tags -> {
            // Se rimane un solo chip, lo rendo non eliminabile
            if(chipGroup.getChildCount() != 0) {
                ((ChipTag) chipGroup.getChildAt(0)).setCloseIconVisible(
                        tagViewData.getAddedTags().getValue().size() != 1
                );
            }
        });
    }

    /**
     * Inizializza tutti i dati da usare sul fragment
     * @param context Il contestoaddedTags.getValue().add(tag)
     * @param tagViewData Il gestore dei dati del fragment
     * @param chipGroup Il ChipGroup che contiene i chip per i tag
     * @param aggiungiTagButton Il pulsante per aggiunger un tag
     */
    public EditingTagViewHelper(Context context, TagViewData tagViewData, ChipGroup chipGroup, Button aggiungiTagButton) {
        this(context, tagViewData, chipGroup);
        this.aggiungiTagButton = aggiungiTagButton;

        tagViewData.getAddedTags().observe((LifecycleOwner) context, tags -> {
            aggiungiTagButton.setClickable(!tagViewData.getDialogTags().isEmpty());
        });
    }

    /**
     * Imposta il comportamento del campo descrizione
     */
    public void setDescriptionEditTextBehavior() {
        LanguageTagViewData languageTagViewData = (LanguageTagViewData) tagViewData;
        descriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                languageTagViewData.getDescriptions().put(((LanguageTag)selectedChip.getTag()).getLanguage(), charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                showTranslateButtonIfNeeded();
            }
        });
    }

    /**
     * Imposta il comportamento dei chip per le lingue
     */
    public void setChipGroupBehaviorForLanguages() {
        setChipGroupBehaviorForLanguages(((LanguageTagViewData) tagViewData).getAddedLanguages().getValue(), true);
    }

    /**
     * Imposta il comportamento dei chip per le lingue senza il pulsante di chiusura.
     */
    public void setSimpleChipGroupBehaviorForLanguages() {
        setChipGroupBehaviorForLanguages(((LanguageTagViewData) tagViewData).getAddedLanguages().getValue(), false);
    }

    /**
     * Imposta il comportamento dei chp per le lingue senza il pulsante di chiusura e caricando delle lingue specifiche
     * @param tagList La lista delle lingue
     */
    public void setSimpleChipGroupBehaviorForLanguages(List<LanguageTag> tagList) {
        setChipGroupBehaviorForLanguages(tagList, false);
    }

    /**
     * Imposta il comportamento dei chip per le lingue, decidendo se impostare o meno il pulsante di chiusura
     * @param tagList La lista dei tag da mostrare
     * @param enableChipClosing se vale true, c'è il pulsantei di chiusura, altrimenti vale false
     */
    private void setChipGroupBehaviorForLanguages(List<LanguageTag> tagList, boolean enableChipClosing) {
        LanguageTagViewData languageTagViewData = (LanguageTagViewData) tagViewData;
        chipGroup.removeAllViews(); // Prima pulisco le view

        for(Tag tag : tagList) {
            ChipTag chipTag;
            if(enableChipClosing) {
                chipTag = new ChipTag(context, tag, this::onChipSelected, this::onChipClose);
            } else {
                chipTag = new ChipTag(context, tag, this::onChipSelected);
            }

            chipGroup.addView(chipTag);
            if(chipGroup.getChildCount() == 1) {  // se è il primo chip inserito, lo seleziono
                chipTag.performClick();
                String lang = ((LanguageTag) chipTag.getTag()).getLanguage();
                if(languageTagViewData.getDescriptions().containsKey(lang))
                    descriptionEditText.setText(languageTagViewData.getDescriptions().get(lang));
            }
        }
    }

    /**
     * Imposta il comportamento dei chip per i tag, decidendo se impostare o meno il pulsante di chiusura
     * @param enableChipClosing se vale true, c'è il pulsante di chiusura, altrimenti vale false
     * @param tags I tag da caricare
     */
    public void setChipGroupBehaviorForTags(boolean enableChipClosing, List<Tag> tags) {
        chipGroup.removeAllViews(); // Pulisco prima le view

        for(Tag tag : tags) {
            ChipTag chipTag;
            if(enableChipClosing) {
                chipTag = new ChipTag(context, tag, tag.getIconBitmap(), tag.getColorString(), this::onChipClose);
            } else {
                chipTag = new ChipTag(context, tag, tag.getIconBitmap(), tag.getColorString());
            }

            chipGroup.addView(chipTag);
        }
    }

    /**
     * Imposta il comportamento dei chip per i tag, decidendo se impostare o meno il pulsante di chiusura
     * @param enableChipClosing se vale true, c'è il pulsante di chiusura, altrimenti vale false
     */
    public void setChipGroupBehaviorForTags(boolean enableChipClosing) {
        setChipGroupBehaviorForTags(enableChipClosing, tagViewData.getAddedTags().getValue());
    }

    /**
     * Metodo che chiama il dialog per scegliere le lingue
     */
    public void setAddLanguageButtonBehavior() {
        aggiungiTagButton.setOnClickListener(v -> {
            // Mostro il dialog
            new AddTagsDialog(context).showAddTagsDialog(tagViewData.getDialogTags(), (List<Tag> tagList) -> {
                for(Tag tag : tagList) {
                    chipGroup.addView(new ChipTag(context, tag, this::onChipSelected, this::onChipClose));
                    tagViewData.addTag(tag);
                    showTranslateButtonIfNeeded();
                }
            });
        });
    }

    /**
     * Metodo che chiama il dialog per scegliere i tag
     */
    public void setAddTagButtonBehavior() {
        aggiungiTagButton.setOnClickListener(v -> {
            // Mostro il dialog
            new AddTagsDialog(context).showAddTagsDialog(tagViewData.getDialogTags(), (List<Tag> tagList) -> {
                for (Tag tag : tagList) {
                    chipGroup.addView(new ChipTag(context, tag, tag.getIconBitmap(), tag.getColorString(), this::onChipClose));
                    tagViewData.addTag(tag);
                }
            });
        });
    }

    /**
     * Metodo che gestisce l'evento di selezione di un chip
     * @param chipTag Il chip selezionato
     */
    private void onChipSelected(ChipTag chipTag) {
        selectedChip = chipTag;
        descriptionEditText.requestFocus();
        descriptionEditText.setHint(chipTag.getTag().getTitle());
        descriptionEditText.setText(((LanguageTagViewData) tagViewData).getDescriptions().get(((LanguageTag) chipTag.getTag()).getLanguage()));
    }

    /**
     * Metodo che gestisce l'evento di cacellazione di un chip
     * @param chipTag Il chip che si vuole rimuovere
     */
    private void onChipClose(ChipTag chipTag) {
        chipGroup.removeView(chipTag);
        tagViewData.removeTag(chipTag.getTag());
    }

    /**
     * Regola il comportamento del pulsante di traduzione
     */
    private void showTranslateButtonIfNeeded() {
        LanguageTagViewData languageTagViewData = (LanguageTagViewData) tagViewData;
        if(languageTagViewData.getDescriptions().size() > 1 && languageTagViewData.areDescriptionsEmptyExcept(((LanguageTag) selectedChip.getTag()).getLanguage()))
            translateButton.setVisibility(View.VISIBLE);
        else
            translateButton.setVisibility(View.GONE);
    }
}

