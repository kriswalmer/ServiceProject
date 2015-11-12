package edu.temple.tue80717.serviceproject;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {


    EditText et1;
    EditText et2;
    Button b1;
    Service myService ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        b1 = (Button) findViewById(R.id.button);
        et1 = (EditText) findViewById(R.id.editText);
        View.OnClickListener listener1 = new View.OnClickListener() {
            public void onClick(View v) {



                String s = String.valueOf(et1.getText());

                System.out.println(s);
                String param1 = "www.google.com" ;
                Intent ServiceIntent  = new Intent(getApplicationContext() , MyIntentService.class) ;
                ServiceIntent.putExtra("STRING_I_NEED", param1);

                ServiceIntent.setAction("edu.temple.tue80717.serviceproject.action.FOO");


                startService(ServiceIntent);



            }

        };
        b1.setOnClickListener(listener1);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
