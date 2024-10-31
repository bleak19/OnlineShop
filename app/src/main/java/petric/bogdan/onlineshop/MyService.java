package petric.bogdan.onlineshop;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.ViewDebug;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    private myBinder mybinder = null;
    Thread thread = null;
    Thread thread2 = null;
    Thread thread3 = null;
    private int mCounter = 1;
    //private int saleCounter = 1;
    private int trajanjeRasprodaje = 20;
    private int rasprodajaOnesposobljena = 20;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        if (mybinder == null) {
            mybinder = new myBinder();
        }

        mCounter = 1;
        if (!thread.isAlive()) {
            Log.d("ServiceTAG", "starting thread");
            thread.start();
        }

        return mybinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("ServiceTAG", "onCreate");
        thread = new Thread(new Runnable(){
            @Override
            public void run() {
                Log.d("ServiceTAG", "thread running");
                int saleCounter = 0;
                while(mCounter != 0) {
                    try {
                        Thread.sleep(1000);
                        Log.d("ServiceTAG", "counter = " + mCounter);
                        mCounter++;
                        if (mCounter == 10) {
                            Log.d("ServiceTAG", "THRESHOLD reached");
                            mCounter = 1;
                            sendNotification();
                            stopSelf();
                            //Thread.sleep(1000*10*60);
                            Thread.sleep(1000);
                            while(saleCounter != 30){
                                Thread.sleep(1000);
                                saleCounter++;
                            }
                            saleCounter = 0;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }



    @Override
    public boolean onUnbind(Intent intent) {
        mybinder.stop();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mybinder = null;
    }

    public void sendNotification() {

        Log.d("ServiceTAG", "sending Notification....");
        Intent intent = new Intent(this, HomeActivity.class);
        String popust = "popust";
        String cnt;
        int odbrojavanje = rasprodajaOnesposobljena;
        intent.putExtra("POPUST_EXTRA", popust);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                int temp_cnt = 0;
                int temp2_cnt = 0;

                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationChannel channel = new NotificationChannel("1",
                        "myChannel",
                        NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DESCRIPTION");
                mNotificationManager.createNotificationChannel(channel);

                while (temp_cnt != trajanjeRasprodaje) {
                    try {
                        Thread.sleep(1000);
                        //int temp = mybinder.getValue();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    temp_cnt++;
                    String cnt = String.valueOf(temp_cnt);


                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "1")
                            .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                            .setContentTitle("SALE!") // title for notification
                            .setContentText("everything 20% off!\n" + cnt)// message for notification
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true); // clear notification after click


                    mNotificationManager.notify(0, mBuilder.build());
                }
                //mNotificationManager.cancelAll();
                mNotificationManager.cancel(0);
                temp2_cnt = 0;
                temp_cnt = 0;
                while (rasprodajaOnesposobljena - temp2_cnt > 0) {
                    try {
                        Thread.sleep(1000);
                        //int temp = mybinder.getValue();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    temp2_cnt++;
                    String cnt2 = String.valueOf(rasprodajaOnesposobljena - temp2_cnt);
                    Log.d("ServiceTAG", "sending Notification....");
                    NotificationManager mNotificationManager2 =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    NotificationChannel channel2 = new NotificationChannel("1",
                            "myChannel",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    channel2.setDescription("YOUR_NOTIFICATION_CHANNEL_DESCRIPTION");
                    mNotificationManager2.createNotificationChannel(channel);
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "1")
                            .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                            .setContentTitle("KRAJ") // title for notification
                            .setContentText("Rasprodaja je gotova, nova pocinje za " + cnt2)// message for notification
                            .setAutoCancel(true); // clear notification after click

                    mNotificationManager2.notify(1, mBuilder.build());
                }
                temp2_cnt = 0;
                temp_cnt = 0;
            }
        });
        thread2.start();



    }
}