package it.uniba.eculturetool.tag_lib.tag.model;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getColor() {
        return color;
    }

    public void setColor(long color) {
        this.color = color;
    }

    public long getIcon() {
        return icon;
    }

    public void setIcon(long icon) {
        this.icon = icon;
    }
}
