package com.autonomouscarapp.autonomouscar;

import android.app.Activity;

// Visuals
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;

// Array of Bluetooth Devices
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// List of Bluetooth Devices
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

// Messages
import android.widget.Toast;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class BluetoothDeviceList extends Activity  {

    // Variable Declaration

    // Visual
    Button onButton,visibleButton,listDevicesButton;
    ListView pairedDeviceListView;
    TextView pairedDeviceText;

    // Bluetooth
    private BluetoothAdapter myBluetoothAdapter;
    private Set<BluetoothDevice>pairedDevices;
    String EXTRA_ADDRESS = "device_address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Sets up window
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_device_list);

        // Variable Initialization

        // View
        listDevicesButton=(Button)findViewById(R.id.listDevicesButton);
        pairedDeviceText = (TextView)findViewById(R.id.pairedDeviceText);
        pairedDeviceListView = (ListView)findViewById(R.id.pairedDeviceListView);

        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); // Gets default bluetooth adapter for the device

        // Initial View
        pairedDeviceText.setVisibility(View.INVISIBLE);
        pairedDeviceListView.setVisibility(View.INVISIBLE);

        // If Bluetooth isn't on, causes dialog box to pop up asking you if you can turn on Bluetooth
        if (!myBluetoothAdapter.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
        }
    }

    // Creates small message popup at bottom
    private void message(String s){
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    // On click of list devices button, shows paired devices
    public void showPairedDevices(View v){
        // Variable Declaration
        ArrayList pairedDeviceArrayList = new ArrayList();
        final ArrayAdapter myArrayAdapter = new  ArrayAdapter(this,android.R.layout.simple_list_item_1, pairedDeviceArrayList);

        // Set visibility
        listDevicesButton.setVisibility(View.INVISIBLE);
        pairedDeviceText.setVisibility(View.VISIBLE);
        pairedDeviceListView.setVisibility(View.VISIBLE);

        // Gets devices bonded to phone
        pairedDevices = myBluetoothAdapter.getBondedDevices();

        // Loops through all paired bluetooth devices and adds their name and address to array list
        for(BluetoothDevice bluetoothDevice : pairedDevices) {
            pairedDeviceArrayList.add(bluetoothDevice.getName() + "\n" + bluetoothDevice.getAddress());
        }

        // Puts all the devices into the list view
        pairedDeviceListView.setAdapter(myArrayAdapter);

        // Listens for click on item in list
        pairedDeviceListView.setOnItemClickListener(myListItemClickListener);
    }

    private AdapterView.OnItemClickListener myListItemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView av, View v, int position, long id) {

            // Get device's MAC address
            String text = ((TextView) v).getText().toString();
            String deviceAddress = text.substring(text.length() - 17);

            // Starts connection and motor control activity, sends device address
            Intent i = new Intent(BluetoothDeviceList.this, MotorControl.class);
            i.putExtra(EXTRA_ADDRESS, deviceAddress);
            startActivity(i);
        }

    };
}