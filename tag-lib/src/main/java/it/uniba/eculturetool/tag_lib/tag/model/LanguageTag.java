package it.uniba.eculturetool.tag_lib.tag.model;

public class LanguageTag extends Tag {
    private String language;

    public LanguageTag(int id, String title, String language) {
        super(id, title);
        this.language = language;
    }

    public LanguageTag(int id, String title, long color, String language) {
        super(id, title, color);
        this.language = language;
    }

    public LanguageTag(int id, String title, long color, long icon, String language) {
        super(id, title, color, icon);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
