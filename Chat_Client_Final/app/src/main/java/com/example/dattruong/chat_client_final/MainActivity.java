package com.example.dattruong.chat_client_final;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Socket socket = new Socket();

    //uiHandler to show data to UI
    private Handler uiHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.append((String) msg.obj + "\n");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        final EditText editText = (EditText) findViewById(R.id.editText);

        final ThreadReciever msgReceiver = new ThreadReciever(uiHandler, socket);
        Thread t = new Thread(msgReceiver);
        t.start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msgOut = editText.getText().toString();
                ThreadSender msgSender = new ThreadSender(socket, msgOut);
                Thread t = new Thread(msgSender);
                t.start();
                editText.getText().clear();

            }
        });


    }

}
