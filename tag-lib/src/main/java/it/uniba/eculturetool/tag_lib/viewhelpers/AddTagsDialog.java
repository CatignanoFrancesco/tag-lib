package it.uniba.eculturetool.tag_lib.viewhelpers;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import it.uniba.eculturetool.tag_lib.R;
import it.uniba.eculturetool.tag_lib.tag.model.Tag;

public class AddTagsDialog extends AlertDialog {
    private AddTagsDialog.Builder builder;
    private final Context context;
    private final List<Tag> selectedTags = new ArrayList<>();

    public AddTagsDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        this.builder = new AddTagsDialog.Builder(context);
    }

    protected AddTagsDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected AddTagsDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    /**
     * Crea e mosta un dialog con i tag selezionabili, permettendo una corrispondenza tra ciò che è selezionato e l'oggetto tag in un array.
     * @param tags I tag da inserire nel dialog.
     * @param onClickListener Il listener per il click dell'ok
     */
    public void showAddTagsDialog(List<Tag> tags, OnPositiveButtonClickListener onClickListener) {
        createAddTagsDialog(tags, onClickListener);
        builder.show();
    }

    /**
     * Crea il dialog e lo restituisce
     * @param tags I tag da mostrare
     * @param onClickListener Il metodo da eseguire dopo il click del pulsante di ok
     * @return Il dialog creato
     */
    public AddTagsDialog createAddTagsDialog(List<Tag> tags, OnPositiveButtonClickListener onClickListener) {
        MyAdapter adapter = new MyAdapter(context, R.layout.add_tag_alert_layout, tags.toArray(new Tag[0]));

        // Imposto il listener
        adapter.setOnItemCheckListener((tag -> {
            if(selectedTags.contains(tag)) selectedTags.remove(tag);
            else selectedTags.add(tag);
        }));

        // Creo il builder del dialog
        builder.setAdapter(adapter, null)
                .setPositiveButton(context.getString(R.string.ok), (dialogInterface, i) -> {
                    onClickListener.onClick(selectedTags);
                });
        return this;
    }


    /**
     * Interfaccia per gestire il click del pulsante ok
     */
    public interface OnPositiveButtonClickListener {
        void onClick(List<Tag> tags);
    }


    /**
     * Interfaccia per gestire il click dell'item nella lista
     */
    public interface OnItemCheckListener {
        void onCheck(Tag tag);
    }


    /**
     * Adapter della lista
     */
    class MyAdapter extends ArrayAdapter {
        private ViewHolder viewHolder;
        private Tag[] items;
        private OnItemCheckListener listener;

        public MyAdapter(@NonNull Context context, int resource, @NonNull Tag[] items) {
            super(context, resource, items);
            this.items = items;
        }

        public void setOnItemCheckListener(OnItemCheckListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if(convertView == null) {
                convertView = layoutInflater.inflate(R.layout.add_tag_alert_item, null);
                viewHolder = new ViewHolder();
                viewHolder.chip = convertView.findViewById(R.id.chip_tag);
                viewHolder.checkBox = convertView.findViewById(R.id.check_box);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Tag tag = items[position];
            viewHolder.chip.setText(tag.getTitle());
            viewHolder.checkBox.setOnClickListener(view -> {
                // Cambio lo stato del check
                viewHolder.checkBox.setChecked(!viewHolder.checkBox.isChecked());
                listener.onCheck(tag);
            });

            if(tag.getColor() != 0) {   // Imposto il colore e l'icona se sono presenti
                viewHolder.chip.setChipBackgroundColor(ColorStateList.valueOf(context.getColor((int) tag.getColor())));
                viewHolder.chip.setChipIconResource((int) tag.getIcon());
            }
            return convertView;
        }

        class ViewHolder {
            Chip chip;
            CheckBox checkBox;
        }
    }
}

