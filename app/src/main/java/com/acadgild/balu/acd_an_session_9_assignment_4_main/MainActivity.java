package com.acadgild.balu.acd_an_session_9_assignment_4_main;

import android.app.DatePickerDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
{
    ArrayList<Contact> array_contacts;
    ListView listView_contacts;
    CustomAdaptor customAdaptor_contacts;

    TextView textView_title, textView_dob_label, textView_dob;
    EditText editText_name, editText_phone;
    ImageButton imageButton_dob;
    Button button_save, button_cancel;

    int current_year, current_month, current_day;
    String new_date, current_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        array_contacts = new ArrayList<>();
        listView_contacts = (ListView) findViewById(R.id.listView_contacts);
        customAdaptor_contacts = new CustomAdaptor(getApplicationContext(), array_contacts);
        listView_contacts.setAdapter(customAdaptor_contacts);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_dialog, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.menu_add)
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            LayoutInflater layoutInflater = LayoutInflater.from(this);

            View viewDialog = layoutInflater.inflate(R.layout.custom_dialog, null);
            alertDialog.setView(viewDialog);

            final AlertDialog dialog_display = alertDialog.create();
            dialog_display.show();

            textView_title = (TextView) viewDialog.findViewById(R.id.textView_title);
            editText_name = (EditText) viewDialog.findViewById(R.id.editText_name);
            editText_phone = (EditText) viewDialog.findViewById(R.id.editText_phone);
            textView_dob_label = (TextView) viewDialog.findViewById(R.id.textView_dob_label);
            textView_dob = (TextView) viewDialog.findViewById(R.id.textView_dob);
            imageButton_dob = (ImageButton) viewDialog.findViewById(R.id.imageButton_dob);
            button_save = (Button) viewDialog.findViewById(R.id.button_save);
            button_cancel = (Button) viewDialog.findViewById(R.id.button_cancel);

            final Calendar calendar_current = Calendar.getInstance();
            current_day = calendar_current.get(Calendar.DAY_OF_MONTH);
            current_month = calendar_current.get(Calendar.MONTH);
            current_year = calendar_current.get(Calendar.YEAR);
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            textView_dob.setText(simpleDateFormat.format(calendar_current.getTime()));

            final SimpleDateFormat currentDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            current_date = currentDateFormat.format(calendar_current.getTime()).toString();

            imageButton_dob.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                            new DatePickerDialog.OnDateSetListener()
                            {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                                {
                                    Calendar calendar_new_picker = Calendar.getInstance();
                                    calendar_new_picker.set(year, monthOfYear, dayOfMonth);
                                    textView_dob.setText(simpleDateFormat.format(calendar_new_picker.getTime()));
                                }
                            },current_year, current_month, current_day);
                    datePickerDialog.show();
                }
            });

            button_save.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(editText_name.getText().toString())) {
                        editText_name.setError(getResources().getString(R.string.blank_name));
                    } else if (TextUtils.isEmpty(editText_phone.getText().toString())) {
                        editText_phone.setError(getResources().getString(R.string.blank_phone));
                    } else {
                        new_date = ui_to_db_date(textView_dob.getText().toString());
                        try {
                            Date c_date = currentDateFormat.parse(current_date);
                            Date n_date = currentDateFormat.parse(new_date);
                            if (n_date.after(c_date)) {
                                textView_dob.setError(getResources().getString(R.string.date_valid));
                                Toast.makeText(getApplicationContext(),
                                        getResources().getString(R.string.date_valid),
                                        Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                             array_contacts.add(new Contact(editText_name.getText().toString(),
                                                            editText_phone.getText().toString(),
                                                            textView_dob.getText().toString()));
                                customAdaptor_contacts.notifyDataSetChanged();
                                editText_name.setText("");
                                editText_phone.setText("");
                                textView_dob.setText(simpleDateFormat.format(calendar_current.getTime()));

                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            button_cancel.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    dialog_display.dismiss();
                }
            });


        }
        return super.onOptionsItemSelected(item);
    }

    private String ui_to_db_date(String input_date)
    {
        String str_date_array[] = input_date.split("/");

        int get_day = Integer.parseInt(str_date_array[0]);
        int get_month = Integer.parseInt(str_date_array[1]);
        int get_year = Integer.parseInt(str_date_array[2]);

        Calendar calendar_temp = Calendar.getInstance();
        calendar_temp.set(get_year, get_month-1, get_day);
        SimpleDateFormat date_db_format = new SimpleDateFormat("yyyy-MM-dd");
        return(date_db_format.format(calendar_temp.getTime()));
    }
}
