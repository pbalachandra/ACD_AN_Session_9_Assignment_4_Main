package com.acadgild.balu.acd_an_session_9_assignment_4_main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by BALU on 4/19/2016.
 */
public class CustomAdaptor extends BaseAdapter
{
    ArrayList<Contact> array_contacts;
    LayoutInflater layoutInflater_contacts;

    public CustomAdaptor(Context context, ArrayList<Contact> array_contacts)
    {
        this.array_contacts = array_contacts;
        layoutInflater_contacts = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return array_contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return array_contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = layoutInflater_contacts.inflate(R.layout.contact_list, parent, false);
        }

        TextView textView_contact_name = (TextView) convertView.findViewById(R.id.textView_contact_name);
        TextView textView_contact_number = (TextView) convertView.findViewById(R.id.textView_contact_number);
        TextView textView_contact_dob = (TextView) convertView.findViewById(R.id.textView_contact_dob);

        Contact contact = array_contacts.get(position);

        textView_contact_name.setText(contact.getContact_name());
        textView_contact_number.setText(contact.getContact_number());
        textView_contact_dob.setText(contact.getContact_dob());

        return convertView;
    }
}
