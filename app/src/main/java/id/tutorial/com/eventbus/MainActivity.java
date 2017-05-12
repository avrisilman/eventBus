package id.tutorial.com.eventbus;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;

import id.tutorial.com.eventbus.event.LoginEvent;

public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
    private Button secondActivityBtn;
    private EditText userName;

    private EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = (Button)findViewById(R.id.login_btn);
        secondActivityBtn = (Button)findViewById(R.id.second_activity_btn);
        userName = (EditText)findViewById(R.id.user_name);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new FragmentA())
                .commit();

        //for fragment A
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userName.getText().toString().isEmpty()){
                    userName.setError("please enter username");
                }else{
                    bus.postSticky(new LoginEvent(userName.getText().toString()));
                }
            }
        });

        //for intent secondActivity
        secondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SecondActivity.class));
            }
        });

    }
}
