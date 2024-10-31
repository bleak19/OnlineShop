package petric.bogdan.onlineshop;

import android.os.RemoteException;

public class myBinder extends IMyAidlInterface.Stub {

    private boolean mRun = true;

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public int getValue() throws RemoteException {
        return 0;
    }


    @Override
    public void setValue(int value) throws RemoteException {

    }

    public void stop() {
        mRun = false;
    }
}
