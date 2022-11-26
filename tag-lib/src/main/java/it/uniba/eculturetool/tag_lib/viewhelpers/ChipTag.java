package it.uniba.eculturetool.tag_lib.viewhelpers;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.chip.Chip;

import it.uniba.eculturetool.tag_lib.R;
import it.uniba.eculturetool.tag_lib.tag.model.Tag;

public class ChipTag extends Chip {
    private Context context;
    private Tag tag;
    private OnCloseChipListener onCloseChipListener;
    private long iconResource;
    private long colorResource;

    /**
     * Crea un tag per la visualizzazione, senza la possibilità di eliminare
     * @param context Il contesto
     * @param tag Il tag rappresentato
     * @param onChipSelectedListener Il comportamento del chip quando viene selezionato
     */
    public ChipTag(Context context, Tag tag, OnChipSelectedListener onChipSelectedListener) {
        super(context);
        this.context = context;
        this.tag = tag;

        this.setText(tag.getTitle());
        this.setCheckable(true);
        this.setOnClickListener(view -> onChipSelectedListener.onChipSelected(this));
    }

    /**
     * Crea un tag per la modifica, con la possibilità di eliminazione
     * @param context Il contesto
     * @param tag Il tag rappresentato
     * @param onChipSelectedListener Il comportamento del chip quando viene selezionato
     * @param onCloseChipListener Il comportamento del chip quando viene cliccata l'icona di chiusura
     */
    public ChipTag(Context context, Tag tag, OnChipSelectedListener onChipSelectedListener, OnCloseChipListener onCloseChipListener) {
        this(context, tag, onChipSelectedListener);

        this.onCloseChipListener = onCloseChipListener;

        this.setCloseIconVisible(true);
        this.setOnCloseIconClickListener(this::closeTagAction);
    }

    /**
     * Crea un tag per la visualizzazione, senza possibilità di eliminazione o selezione
     * @param context Il contesto
     * @param tag Il tag
     * @param iconResource L'icona
     * @param colorResource Il colore del tag
     */
    public ChipTag(Context context, Tag tag, long iconResource, long colorResource) {
        super(context);

        this.context = context;
        this.tag = tag;
        this.iconResource = iconResource;
        this.colorResource = colorResource;

        this.setText(tag.getTitle());
        this.setCloseIconVisible(false);
        this.setClickable(false);
        this.setChipBackgroundColor(ColorStateList.valueOf(context.getColor((int) colorResource)));
        this.setIconStartPadding(16);
        this.setChipIconResource((int) iconResource);
    }

    /**
     * Crea un tag per la visualizzazione, senza possibilità di eliminazione o selezione
     * @param context Il contesto
     * @param tag Il tag
     * @param iconResource L'icona
     * @param colorResource Il colore del tag
     * @param onCloseChipListener Il comportamento del tag quando viene clicata l'icona di chiusura
     */
    public ChipTag(Context context, Tag tag, long iconResource, long colorResource, OnCloseChipListener onCloseChipListener) {
        this(context, tag, iconResource, colorResource);

        this.onCloseChipListener = onCloseChipListener;

        this.setClickable(false);
        this.setCloseIconVisible(true);
        this.setOnCloseIconClickListener(this::closeTagAction);
    }

    /**
     * Restituisce il tag associato
     * @return Il tag associato al chip
     */
    public Tag getTag() {
        return tag;
    }

    private void closeTagAction(View v) {
        new AlertDialog.Builder(context)
                .setMessage(context.getString(R.string.conferma_eliminazione_tag, tag.getTitle()))
                .setPositiveButton(R.string.si_elimina, (dialogInterface, i) -> onCloseChipListener.onCloseChip(this))
                .setNegativeButton(R.string.no_chiudi, null)
                .show();
    }

    public ChipTag(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChipTag(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public interface OnCloseChipListener {
        void onCloseChip(ChipTag chipTag);
    }

    public interface OnChipSelectedListener {
        void onChipSelected(ChipTag chipTag);
    }

}

