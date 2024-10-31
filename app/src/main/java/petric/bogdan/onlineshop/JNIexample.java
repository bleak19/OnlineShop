package petric.bogdan.onlineshop;

public class JNIexample {

    static{
        System.loadLibrary("MojaBiblioteka");
    }
    public native int smanjiCenu(int x);
}
