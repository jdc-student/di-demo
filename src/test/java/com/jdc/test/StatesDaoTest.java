package com.jdc.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.location.config.AppConfig;
import com.jdc.location.dao.StatesDao;
import com.jdc.location.dto.States;
import com.jdc.utils.DbUtil;

@SpringJUnitConfig(classes = AppConfig.class)
@TestMethodOrder(OrderAnnotation.class)
public class StatesDaoTest {

	@Autowired
	private StatesDao dao;
	
	@BeforeAll
	static void init()
	{
		DbUtil.truncate("states");
	}
	
	@Order(1)
	@ParameterizedTest
	@CsvSource(value= {
			"Yangon,1",
			"Mandalay,2",
			"Myitkyina,3"
	})
	void insertTest(String name,int expectedId)
	{
		var state = new States();
		state.setName(name);
		
		var result = dao.insert(state);
		
		Assertions.assertEquals(expectedId,result);
	}
	
	@Test
	@Order(2)
	void findAllTest()
	{
		var result = dao.findAll();
		for (States states : result) {
			System.out.print(states.getId()+".");
			System.out.println(states.getName());
		}
	}
	
	@Order(3)
	@ParameterizedTest
	@CsvSource(value= {
			"1,Yangon",
			"2,Mandalay",
			"3,Myitkyina"
	})
	void findByIdTest(int id,String expectedString)
	{
		var result = dao.findById(id);
		
		Assertions.assertEquals(result.getName(), expectedString);
		
	}
}
