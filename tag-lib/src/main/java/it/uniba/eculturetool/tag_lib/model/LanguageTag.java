package it.uniba.eculturetool.tag_lib.model;

public class LanguageTag extends Tag {
    private String language;

    public LanguageTag(int id, String title) {
        super(id, title);
    }

    public LanguageTag(int id, String title, long color) {
        super(id, title, color);
    }

    public LanguageTag(int id, String title, long color, long icon) {
        super(id, title, color, icon);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
