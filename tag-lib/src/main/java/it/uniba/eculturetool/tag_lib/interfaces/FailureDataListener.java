package it.uniba.eculturetool.tag_lib.interfaces;

/**
 * Interfaccia per gestire il fallimento di un task
 * @param <T> Il tipo dell'oggetto da passare per mostrare l'errore
 */
public interface FailureDataListener<T> {
    void execute(T data);
}
