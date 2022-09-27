package com.jdc.location.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jdc.location.dto.States;

@Component
public class StatesDao {
	
	private DataSource dataSource;

	public StatesDao(@Autowired(required = true) DataSource data)
	{
		this.dataSource=data;
	}

	public int insert(States state)
	{
		try(var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement(""" 
					insert into States (name) values (?)
					""",Statement.RETURN_GENERATED_KEYS)) {
			
			
			stmt.setString(1, state.getName());
			stmt.executeUpdate();
			
			var result = stmt.getGeneratedKeys();
			
			if(result.next())
			{
				return result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<States> findAll()
	{
		var list = new ArrayList<States>();
		
		try(var conn=dataSource.getConnection();
				var stmt= conn.prepareStatement("select * from states ")){
			
			var result= stmt.executeQuery();
			
			while(result.next()) {
				var data = new States();
				data.setId(result.getInt("id"));
				data.setName(result.getString("name"));
				list.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public States findById(int id)
	{
		try(var conn=dataSource.getConnection();
				var stmt= conn.prepareStatement("select * from states where id = ?")){
			
			stmt.setInt(1, id);
			var result= stmt.executeQuery();
			
			while(result.next()) {
				var data = new States();
				data.setId(result.getInt("id"));
				data.setName(result.getString("name"));
				return data;				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
