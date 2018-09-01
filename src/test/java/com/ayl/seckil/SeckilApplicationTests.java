package com.ayl.seckil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckilApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("test");
		List<Integer> list = new ArrayList<>();
		list.add(2);
		list.add(5);
		List<Integer> aaa = list.stream().filter((num)-> num > 2).filter(num -> num > 6).collect(Collectors.toList());

		System.out.println(aaa.size());
	}

}
