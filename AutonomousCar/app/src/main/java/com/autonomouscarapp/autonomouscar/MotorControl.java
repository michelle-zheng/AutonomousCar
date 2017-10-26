package com.autonomouscarapp.autonomouscar;
import android.support.v7.app.AppCompatActivity;

// CHECK
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.content.Intent;

// Visual Imports
import android.view.View;
import android.widget.Button;

// Messaging Imports
import android.widget.Toast;
import android.app.ProgressDialog;

// Bluetooth Imports
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;

public class MotorControl extends AppCompatActivity implements View.OnClickListener{ // Implement statement is so switch onClick can be used

    // Variable Declaration

    // Visuals
    Button forwardButton, backwardButton, forwardLeftButton, forwardRightButton, backwardLeftButton, backwardRightButton, stopButton;

    // Bluetooth Connection
    private BluetoothConnection myBluetoothConnection;
    BluetoothSocket myBluetoothSocket = null;
    String MACAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Set up layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_control);

        // Creates a new Bluetooth Connection
        myBluetoothConnection = new BluetoothConnection();

        // Gets the MAC Address of the desired device
        MACAddress = getIntent().getExtras().getString("device_address");

        // Sends a message to show it's connecting, then connects (make new connect thread, make RFCOMM connection)
        message("Connecting...");
        myBluetoothConnection.connect(myBluetoothConnection.getDevice(MACAddress));
        myBluetoothSocket = myBluetoothConnection.getMyBluetoothSocket();

        // View initialization
        forwardButton = (Button)findViewById(R.id.forwardButton);
        forwardButton.setOnClickListener(this);
        forwardLeftButton = (Button)findViewById(R.id.forwardLeftButton);
        forwardLeftButton.setOnClickListener(this);
        forwardRightButton = (Button)findViewById(R.id.forwardRightButton);
        forwardRightButton.setOnClickListener(this);
        backwardButton = (Button)findViewById(R.id.backwardButton);
        backwardButton.setOnClickListener(this);
        backwardLeftButton = (Button)findViewById(R.id.backwardLeftButton);
        backwardLeftButton.setOnClickListener(this);
        backwardRightButton = (Button)findViewById(R.id.backwardRightButton);
        backwardRightButton.setOnClickListener(this);
        stopButton = (Button)findViewById(R.id.stopButton);
        stopButton.setOnClickListener(this);
    }

    // Creates a small popup message
    private void message(String s)
    {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }

    // Runs method depending on which button is clicked
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.forwardButton:
                forward();
                break;

            case R.id.forwardLeftButton:
                forwardLeft();
                break;

            case R.id.forwardRightButton:
                forwardRight();
                break;

            case R.id.backwardButton:
                backward();
                break;

            case R.id.backwardLeftButton:
                backwardLeft();
                break;

            case R.id.backwardRightButton:
                backwardRight();
                break;

            case R.id.stopButton:
                stop();
                break;
        }
    }

    private void forward() {
        if (myBluetoothSocket!=null)
        {
            message("Forward");
            myBluetoothConnection.send("1");
        }
    }

    private void forwardLeft()
    {
        if (myBluetoothSocket!=null)
        {
            message("Forward Left");
            myBluetoothConnection.send("4");
        }
    }

    private void forwardRight()
    {
        if (myBluetoothSocket!=null)
        {
            message("Forward Right");
            myBluetoothConnection.send("3");
        }
    }

    private void backward()
    {
        if (myBluetoothSocket!=null)
        {
            message("Backward");
            myBluetoothConnection.send("2");
        }
    }

    private void backwardLeft()
    {
        if (myBluetoothSocket!=null)
        {
            message("Backward Left");
            myBluetoothConnection.send("6");
        }
    }

    private void backwardRight ()
    {
        if (myBluetoothSocket!=null)
        {
            message("Backward Right");
            myBluetoothConnection.send("5");
        }
    }

    private void stop()
    {
        if (myBluetoothSocket!=null)
        {
            message("Stop");
            myBluetoothConnection.send("7");
        }
    }
}
