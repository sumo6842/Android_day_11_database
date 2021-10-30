package com.duc.day_11_database.daos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duc.day_11_database.R;
import com.duc.day_11_database.dtos.StudentDTO;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private List<StudentDTO> students;

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }

    @Override
    public int getCount() {
        return this.students.size();
    }

    @Override
    public Object getItem(int position) {
        return this.students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item, parent, false);

        }
        //Map Control
        TextView txtId = convertView.findViewById(R.id.txtId);
        TextView textName = convertView.findViewById(R.id.txtName);
        TextView txtMark = convertView.findViewById(R.id.txtMark);
        StudentDTO dto = students.get(position);
        txtId.setText(dto.getId());
        textName.setText(dto.getName());
        txtMark.setText(String.valueOf(dto.getMark()));
        return convertView;
    }
}
