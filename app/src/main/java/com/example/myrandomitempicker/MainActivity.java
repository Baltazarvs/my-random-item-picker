// Created 2023 - Baltazarus

package com.example.myrandomitempicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.EditText;

import com.example.myrandomitempicker.databinding.ActivityMainBinding;
import com.example.myrandomitempicker.SelectedItemMsgDialog;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'myrandomitempicker' library on application startup.
    static {
        System.loadLibrary("myrandomitempicker");
    }

    private ActivityMainBinding binding;
    public List<String> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.initializeRand();

        TextView te = binding.sampleText;
        Button addButton = binding.addButton;
        Button deleteButton = binding.deleteItemButton;
        Button selectButton = binding.buttonPickRandom;
        ListView itemsList = binding.itemsList;
        EditText et = binding.itemValueEdit;

        String[] init_items = { };

        listItems = new ArrayList<String>(Arrays.asList(init_items));
        final ArrayAdapter<String> list_adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                listItems
        );

        itemsList.setAdapter(list_adapter);

        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String text_to_add = et.getText().toString();
                        if(text_to_add.length() > 0)
                        {
                            listItems.add(et.getText().toString());
                            et.setText("");
                            list_adapter.notifyDataSetChanged();
                        }
                    }
                }
        );

        deleteButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(itemsList.isSelected())
                        {
                            int index = itemsList.getSelectedItemPosition();
                            String str_to_remove = listItems.get(index);
                            list_adapter.remove(str_to_remove);
                            listItems.remove(index);
                            list_adapter.notifyDataSetChanged();
                        }
                    }
                }
        );

        selectButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String[] strarr = listItems.toArray(new String[0]);
                        if(strarr.length < 1)
                        {
                            displayDialogMessage("No items to pick.", "Alert");
                            return;
                        }
                        String current_selection = getRandomItem(strarr);
                        displayDialogMessage(current_selection, "Picked Item!");
                        te.setText("Last Picked: " + current_selection);
                    }
                }
        );

    }

    private void displayDialogMessage(String msg_str, String caption)
    {
        SelectedItemMsgDialog msg_dlg = new SelectedItemMsgDialog(msg_str, caption);
        msg_dlg.show(getSupportFragmentManager(), "result dlg");
        return;
    }

    public native String getRandomItem(String[] str_arr);
    public native void initializeRand();
}