package it.uniba.eculturetool.tag_lib.interfaces;

@Deprecated
public interface FailureListener extends FailureDataListener<String> {
    void execute(String err);
}
