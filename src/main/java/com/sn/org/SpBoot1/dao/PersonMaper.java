package com.sn.org.SpBoot1.dao;


import com.sn.org.SpBoot1.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMaper implements RowMapper <Person>{

    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return  new Person(resultSet.getInt("id"), resultSet.getString("name"),
                resultSet.getInt("age"), resultSet.getString("email"));
    }
}
