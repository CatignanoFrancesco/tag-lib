package it.uniba.eculturetool.tag_lib.viewhelpers;

import android.content.Context;
import android.widget.TextView;

import com.google.android.material.chip.ChipGroup;

import it.uniba.eculturetool.tag_lib.tag.model.LanguageTag;
import it.uniba.eculturetool.tag_lib.tag.model.Tag;

public class TagViewHelper {
    private Context context;
    private ChipGroup chipGroup;
    private TextView descriptionTextView;
    private LanguageTagViewData languageTagViewData;

    public TagViewHelper(Context context, LanguageTagViewData languageTagViewData, ChipGroup chipGroup, TextView descriptionTextView) {
        this.context = context;
        this.languageTagViewData = languageTagViewData;
        this.chipGroup = chipGroup;
        this.descriptionTextView = descriptionTextView;
    }

    /**
     * Imposta il comportamento dei chip per le lingue basandosi sulla lista ricevuta come parametro
     */
    public void setChipGroupBehaviorForLanguages() {
        chipGroup.removeAllViews(); // prima di inserire i nuovi chip, li rimuovo tutti
        for(Tag tag : languageTagViewData.getAddedLanguages().getValue()) {
            ChipTag chipTag = new ChipTag(context, tag, this::onChipSelected);
            chipGroup.addView(chipTag);
            if(chipGroup.getChildCount() == 1) {  // se è il primo chip inserito, lo seleziono
                chipTag.performClick();
            }
        }
    }

    /**
     * Imposta il comportamento del chip quando viene selezionato
     * @param chipTag Il chip selezionato
     */
    private void onChipSelected(ChipTag chipTag) {
        String lang = ((LanguageTag) chipTag.getTag()).getLanguage();
        descriptionTextView.setText(languageTagViewData.getDescriptions().get(lang));
    }
}
