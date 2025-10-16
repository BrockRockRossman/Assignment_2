package com.example.assignment2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class smsReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        // What we want to do when the SMS happens
        final Bundle bundle = intent.getExtras();
        if(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){
            if(bundle != null){
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                String format = bundle.getString("format").toString();
                String message = "";
                for (Object o : pdusObj) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) o, format);
                    String sender = currentMessage.getDisplayOriginatingAddress();
                    message = currentMessage.getDisplayMessageBody();
                    String printMessage = "Sender: " + sender + " Message: " + message;
                }
                Intent launchIntent = new Intent(context, MainActivity.class);
                launchIntent.setAction(Intent.ACTION_SEND);
                launchIntent.putExtra("sms", message);

                //Toast.makeText(context,message,Toast.LENGTH_LONG).show();

                launchIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(launchIntent);
            }
        }


    }
}