package root.if_it_rains;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by root1 on 2017. 8. 21..
 */

public class FirebaseMessagingManagerService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d("xxx", "onMessageReceived: " + remoteMessage.getNotification().getBody());

    }
}
