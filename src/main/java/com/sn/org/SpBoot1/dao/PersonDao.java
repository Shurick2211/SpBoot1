package com.sn.org.SpBoot1.dao;

import com.sn.org.SpBoot1.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component


public class PersonDao {

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
	private Statement statement;
	{
		try {
			statement=connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private  int PEOPLE_COUNT;

	{
		sql = "select count(*) from persons";

		try {

			ResultSet resultSet=statement.executeQuery(sql);
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
	           ResultSet resultSet=statement.executeQuery(sql);
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

			sql="select * from persons where id='"+id+"'";

				ResultSet resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
				person = new Person(resultSet.getInt("id"), resultSet.getString("name"),
						resultSet.getInt("age"), resultSet.getString("email"));

			}
		} catch (SQLException e) {
			e.printStackTrace(); }
		return person;

	}
	
	public void save(Person person) {

		person.setId(++PEOPLE_COUNT);
		sql="insert into persons values ('"+person.getId()+"','"+person.getName()+"','"
				+person.getAge()+"','"+person.getEmail()+"')";
		try {
			statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(int id, Person person) {

		sql="update persons set name='"+person.getName()+
				"', age='"+person.getAge()+"', email='"+person.getEmail()+"' where id="+id;
		try {
			statement.execute(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		sql="DELETE FROM persons WHERE id="+"'"+id+"'";
		try {
			statement.execute(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

}
