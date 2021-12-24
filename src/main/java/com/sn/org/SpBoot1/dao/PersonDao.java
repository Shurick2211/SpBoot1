package com.sn.org.SpBoot1.dao;

import com.sn.org.SpBoot1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component


public class PersonDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private  int PEOPLE_COUNT=10;
	public List<Person> index(){
		return  jdbcTemplate.query("select * from persons", new BeanPropertyRowMapper<>(Person.class));
	}

	public Person show(int id) {
		return  jdbcTemplate.query("select * from persons where id=?", new Object[]{id},
				new BeanPropertyRowMapper<>(Person.class)).get(0);}

	public void save(Person person) {
		jdbcTemplate.update("insert into persons values(?,?,?,?)",++PEOPLE_COUNT,person.getName(),
				person.getAge(),person.getEmail());
	}

	public void update(int id, Person person) {
		jdbcTemplate.update("update persons set name=?, age=?, email=? where id=?",person.getName(),
				person.getAge(),person.getEmail(),id);
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM persons WHERE id=?",id);
	}

}
