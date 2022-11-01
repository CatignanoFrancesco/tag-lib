package it.uniba.eculturetool.tag_lib.tag;

import java.util.HashMap;
import java.util.Map;

/**
 * Questa classe contiene le lingue utilizzate per la traduzione e i testi già pronti per essere visualizzati all'interno delle varie schermate.
 */
public final class Languages {
    private Languages() {}

    // Costanti per le stringhe da usare per le traduzioni
    public static final String ITALIAN = "IT";
    public static final String ENGLISH = "EN";
    public static final String SPANISH = "SP";
    public static final String GERMAN = "DE";
    public static final String FRENCH = "FR";

    // Mappa contenente le stringhe pronte per la stampa
    private static final Map<String, String> languagesMap = new HashMap<>();

    static {
        languagesMap.put(ITALIAN, "\uD83C\uDDEE\uD83C\uDDF9 Italiano");
        languagesMap.put(ENGLISH, "\uD83C\uDDFA\uD83C\uDDF8 English");
        languagesMap.put(FRENCH, "\uD83C\uDDEB\uD83C\uDDF7 Français");
        languagesMap.put(GERMAN, "\uD83C\uDDE9\uD83C\uDDEA Deutsch");
        languagesMap.put(SPANISH, "\uD83C\uDDEA\uD83C\uDDF8 Español");
    }

    /**
     * Passando in input il codice della lingua, verrà restituita la stringa visualizzabile associata a quella lingua.
     * @param language Il codice della lingua
     * @return La lingua visualizzabile nelle varie schermate
     */
    public static String get(String language) {
        return languagesMap.get(language);
    }
}
