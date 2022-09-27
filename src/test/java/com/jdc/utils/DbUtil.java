package com.jdc.utils;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	private static final String URL = "jdbc:mysql://localhost:3306/location_db";
	private static final String PASS = "location";
	private static final String USER = "location";

	public static void truncate(String...tables)
	{
		try(var conn = DriverManager.getConnection(URL,USER,PASS)){
			
			for(var table : tables)
			{
				var stmt = conn.prepareStatement("truncate table %s".formatted(table));
				stmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
