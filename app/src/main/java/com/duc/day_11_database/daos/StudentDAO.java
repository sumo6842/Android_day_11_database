package com.duc.day_11_database.daos;

import com.duc.day_11_database.dtos.StudentDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<StudentDTO> loadDataFromRaw(InputStream is) {
        List<StudentDTO> result = new ArrayList<>();
        try(InputStreamReader ins = new InputStreamReader(is);
            BufferedReader buffer = new BufferedReader(ins)
        ) {
            String s = "";
            StudentDTO dto = null;

            while((s = buffer.readLine()) != null) {
                String[] tmp = s.split("-");
                dto = new StudentDTO(tmp[0], tmp[1], Float.parseFloat(tmp[2]));
                result.add(dto);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
