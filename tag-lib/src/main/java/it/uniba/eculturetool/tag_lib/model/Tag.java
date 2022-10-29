package it.uniba.eculturetool.tag_lib.model;

public class Tag {
    private int id;
    private String title;
    private long color; // Il valore sarà qualcosa tipo R.color.x
    private long icon;   // Il valore sarà qualcosa tipo R.drawable.x

    public Tag(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Tag(int id, String title, long color) {
        this.id = id;
        this.title = title;
        this.color = color;
    }

    public Tag(int id, String title, long color, long icon) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.icon = icon;
    }
}
