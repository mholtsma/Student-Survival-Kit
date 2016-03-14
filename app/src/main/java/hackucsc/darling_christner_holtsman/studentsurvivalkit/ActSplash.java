package hackucsc.darling_christner_holtsman.studentsurvivalkit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActSplash extends AppCompatActivity {
    static final public String MYPREFS = "myprefs";
    static final public String PREF_STRING_NAME = "string";
    SharedPreferences pref;
    SharedPreferences.Editor editor1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = getSharedPreferences("testapp", MODE_PRIVATE);
        editor1 = pref.edit();
        String getStatus=pref.getString("register", "nil");
        if(getStatus.equals("true")) {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);

        }
        setContentView(R.layout.activity_act_splash);
    }
    public void TextClick(View v){
        Button   Go_button = (Button)   findViewById(R.id.button);
        Go_button.setVisibility(View.VISIBLE);
    }

    public void onClick(View v){
        EditText Your_Name = (EditText) findViewById(R.id.editText);
        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        String nameCheck;
        nameCheck = Your_Name.getText().toString();
        SharedPreferences settings = getSharedPreferences(MYPREFS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_STRING_NAME, nameCheck);
        SharedPreferences.Editor edit = pref.edit();
        editor.apply();
        editor.putString("register","true");
        editor1.commit();
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}
