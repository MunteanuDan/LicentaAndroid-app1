package eu.ase.firebaseauthlicenta.order.util;

public interface Callback<R> {
    void runResultOnUiThread(R result);
}
