package id.tutorial.com.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import id.tutorial.com.eventbus.event.LoginEvent;

public class FragmentA extends Fragment {

    private EventBus eventBus = EventBus.getDefault();
    private TextView textView;

    public FragmentA(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        textView = (TextView)view.findViewById(R.id.user_status);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @Subscribe
    public void onLogingEvent(LoginEvent loginEvent){
        textView.setText("user status : " + loginEvent.username);
    }

}
