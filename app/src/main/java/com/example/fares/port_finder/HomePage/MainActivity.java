package com.example.fares.port_finder.HomePage;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fares.port_finder.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String EXTRA_ADDRESS = "fares";
    public static final String EXTRA_NOM = "ayme";
    private static  final Integer RES = 1;
    BluetoothAdapter mBluetoothAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    DeviceListAdapter deviceListAdapter;
    private ListView pairedListView;
    private ArrayList<BluetoothDevice> paireditems = new ArrayList<>();
    FloatingActionButton fab;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RES){
            loadPairedDevices();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deviceListAdapter = new DeviceListAdapter(getApplicationContext(),
                R.layout.device_adapter_view, paireditems);
        TextView txtdistance = findViewById(R.id.distance);
        loadPairedDevices();
        //-----------------Refresh--------------------------
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadPairedDevices();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        //----------------------------------------------------
       fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intentOpenBluetoothSettings = new Intent();
                intentOpenBluetoothSettings.setAction(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
                startActivityForResult(intentOpenBluetoothSettings,RES);

            }
        });
        pairedListView = findViewById(R.id.pairedevice);



    }


    public void loadPairedDevices(){
        deviceListAdapter = new DeviceListAdapter(getApplicationContext(),R.layout.device_adapter_view,paireditems);
        pairedListView = findViewById(R.id.pairedevice);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.startDiscovery();
        paireditems.clear();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        // There are paired devices. Get the name and address of each paired device.
        if (pairedDevices.size() > 0) for (BluetoothDevice device : pairedDevices)
            paireditems.add(device);
        pairedListView.setAdapter(deviceListAdapter);
        pairedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String address = paireditems.get(position).getAddress();
                String name = paireditems.get(position).getName();
                Intent i = new Intent(MainActivity.this, DeviceActivity.class);
                i.putExtra("ADDRESS", address);
                i.putExtra(EXTRA_NOM,name);

                startActivity(i);
            }
        });

        pairedListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id)
            {
                showInputBox(pos);

                return true;
            }
        });
    }
    public void showInputBox( int pos){
        final Dialog dialog=new Dialog(MainActivity.this);
        dialog.setTitle(paireditems.get(pos).getName());
        dialog.setContentView(R.layout.input_box);
        Button btdel=(Button)dialog.findViewById(R.id.btdel);
        Button btann=(Button)dialog.findViewById(R.id.annulation);
        final BluetoothDevice device = paireditems.get(pos);
        btdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Method m = device.getClass()
                            .getMethod("removeBond", (Class[]) null);
                    m.invoke(device, (Object[]) null);
                    loadPairedDevices();
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
                paireditems.remove(device);
                deviceListAdapter.notifyDataSetChanged();
                dialog.dismiss();

                Toast.makeText(getApplicationContext(),"Suppression Valider",Toast.LENGTH_SHORT).show();
            }
        });
        btann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}