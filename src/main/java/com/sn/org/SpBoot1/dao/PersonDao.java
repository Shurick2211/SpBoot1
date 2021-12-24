package com.sn.org.SpBoot1.dao;

import com.sn.org.SpBoot1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
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

/*
//	@Value("${DB_URL}")
	 private  String DB_URL ="jdbc:postgresql://localhost:5432/fMVC_db";
//	@Value("${USER}")
	    private  String USER="postgres" ;
//	@Value("${PASS}")
	    private  String PASS="koshka$" ;
	
	    private String sql;


	
	private  Connection connection;
	 {
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
			System.out.println(DB_URL);
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
        if(connection!=null)
            System.out.println("Connected!");
        else System.out.println("Connected is FAIL!");
	}



	private  int PEOPLE_COUNT;

	{
		sql = "select count(*) from persons";

		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next()) {
				PEOPLE_COUNT=resultSet.getInt(1);
			}
			System.out.println(PEOPLE_COUNT);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Person> index(){
		List<Person> people=new ArrayList<>();
		try {
			sql="select * from persons";
			PreparedStatement statement=connection.prepareStatement(sql);
	           ResultSet resultSet=statement.executeQuery();
	           while(resultSet.next()) {
	           Person person=new Person(resultSet.getInt("id"), resultSet.getString("name"),
	        		   resultSet.getInt("age"), resultSet.getString("email"));
	           people.add(person);
	           }
	        } catch (SQLException e) {
	            e.printStackTrace(); }
				PEOPLE_COUNT=people.size();
			return people;
	}
	
	public Person show(int id) {
		Person person=null;
		try {
			sql="select * from persons where id=?";
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1,id);
				ResultSet resultSet = statement.executeQuery();
				while(resultSet.next()) {
				person = new Person(resultSet.getInt("id"), resultSet.getString("name"),
						resultSet.getInt("age"), resultSet.getString("email"));

			}
		} catch (SQLException e) {
			e.printStackTrace(); }
		return person;

	}
	
	public void save(Person person) {

		sql="insert into persons values (?,?,?,?)";
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1,++PEOPLE_COUNT);
			statement.setString(2, person.getName());
			statement.setInt(3,person.getAge());
			statement.setString(4, person.getEmail());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(int id, Person person) {

		sql="update persons set name='?', age=?, email='?' where id="+id;
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1,person.getName());
			statement.setInt(2,person.getAge());
			statement.setString(3, person.getEmail());

			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		sql="DELETE FROM persons WHERE id=?";
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1,id);
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	*/

}
