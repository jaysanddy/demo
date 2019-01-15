package com.vk;

import com.vk.request.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YuanheApplicationTests {
	@Autowired
	private Project project;

	@Test
	public void contextLoads() {
	}

	public void out(Object o){
		System.out.println(o.toString());
	}

}
