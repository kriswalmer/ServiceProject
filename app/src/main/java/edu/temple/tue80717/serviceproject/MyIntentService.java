package edu.temple.tue80717.serviceproject;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "edu.temple.tue80717.serviceproject.action.FOO";
    private static final String ACTION_BAZ = "edu.temple.tue80717.serviceproject.action.BAZ";

    // TODO: Rename parameters
    private static  String EXTRA_PARAM1 = "edu.temple.tue80717.serviceproject.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "edu.temple.tue80717.serviceproject.extra.PARAM2";

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);

        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        System.out.println("Service intent starting");


        if (intent != null) {
            final String action = intent.getAction();

            String newString;
            Bundle extras = intent.getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("STRING_I_NEED");
                System.out.println(newString);
            }






            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                try {
                    handleActionFoo(newString, param2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }



    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) throws IOException {
        // TODO: Handle action Foo
        System.out.println("action Started Url needed " + param1);

        //final String  urlStr = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22GOOG%22)%0A%09%09&env=http%3A%2F%2Fdatatables.org%2Falltables.env&format=json" ;
        // final String  urlStr = "http://www.google.com";
        final String urlStr = "http://finance.yahoo.com/webservice/v1/symbols/GOOG/quote?format=json&view=basic";
       // while (param1.equals("www.google.com")) {

         (new Thread (new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlStr);
                    java.net.URLConnection con = url.openConnection();
                    con.connect();
                    java.io.BufferedReader in =
                            new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()));
                    String line;
                    String full = "";
                    for (; (line = in.readLine()) != null; ) {
                        Log.d("MainActivity", "read from web " + line);
                        full += line + "\n";

                    }
                    System.out.println(full);


                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        })).start();


   // }
/*

        String sUrl = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22GOOG%22)%0A%09%09&env=http%3A%2F%2Fdatatables.org%2Falltables.env&format=json";
        URL url = new URL(sUrl);

        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));//, Charset.forName("ISO-8859-1")));

        String res = "";

        String line;
        while ((line = rd.readLine()) != null) {
            res += line;
        }

        rd.close();


        System.out.println(res); */

            //throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
