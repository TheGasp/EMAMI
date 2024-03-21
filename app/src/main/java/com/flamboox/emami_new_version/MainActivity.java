package com.flamboox.emami_new_version;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ACCESS_FINE_LOCATION = 1;

    private BluetoothAdapter bluetoothAdapter;
    private LinearLayout deviceLayout;
    private BluetoothLeScanner bluetoothLeScanner;
    private ScanCallback scanCallback; // Déclaration de la variable de callback

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deviceLayout = findViewById(R.id.deviceLayout);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            Toast.makeText(this, "Bluetooth is not supported or not enabled on this device", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize BluetoothLeScanner
        bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();

        // Start BLE scan
        startScan();
    }

    private void startScan() {
        scanCallback = new ScanCallback() { // Initialisation du scanCallback
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                BluetoothDevice device = result.getDevice();
                String deviceName = device.getName();
                String deviceAddress = device.getAddress();
                // Add the detected BLE device to the LinearLayout
                addDeviceToList(deviceName, deviceAddress);
            }
        };

        // Start the scan
        bluetoothLeScanner.startScan(scanCallback);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Stop BLE scan when the activity is stopped
        stopScan();
    }

    private void stopScan() {
        bluetoothLeScanner.stopScan(scanCallback);
    }

    private void addDeviceToList(String name, String address) {
        TextView deviceTextView = new TextView(this);
        deviceTextView.setText(name + " - " + address);
        deviceLayout.addView(deviceTextView);
    }
}