package hk.ust.hackathon.peertopeerforeignexchange;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseObject;


public class MainActivity extends Activity implements View.OnClickListener {


    EditText txtFieldName;
    EditText txtFieldPassword;
    Button btnSignIn;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable Local Datastore.
       // Parse.enableLocalDatastore(this);

        //Parse.initialize(this, "lZzduirAh9J4BqjRyjKlaApumnbU4AvMfFzUY6kb", "nqY8NA76p6rjm43oyCqgpcq7EKpsiYZWXEzngO9d");

        txtFieldName = (EditText) findViewById(R.id.txtFieldName);
        txtFieldPassword = (EditText) findViewById(R.id.txtFieldPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(this);
        //ParseObject testObject = new ParseObject("TestObject");
        //testObject.put("foo", "bar");
        //testObject.saveInBackground();
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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnSignIn:
                Intent mIntent = new Intent(this,ExchangeViewActivity.class);
                //mIntent.putExtra("ExchangeList", "Hello " + "a" + "!");
                startActivity(mIntent);
                break;

            case R.id.btnSignup:

                break;
        }
    }
}
