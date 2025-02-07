package com.kn.springboot.controller;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Entity.Person.Person;

@RestController
@RequestMapping("/api/v1")
public class TestController {
///autowiring the RabbiteTemplate ..
	//Bhoomika-NV <bhoomikanv6@gmail.com>
	@Autowired
	RabbitTemplate rabbitTemplate;

	@GetMapping("/test/{name}")    //get Api

 public String testApi(@PathVariable String name) {

		Person p = new Person(1L,name);

	   rabbitTemplate.convertAndSend("Tv", p);               //this template simple mesage converter supports string , byte ,
																			// and serializable objects// make sure that the cperson class is serializle
	  	rabbitTemplate.convertAndSend("Direct-Exchange","tv",p);

		rabbitTemplate.convertAndSend("Fanout-Exchange","",p);

		rabbitTemplate.convertAndSend("Topic-Exchange","tv.mobile.ac",p);

		return "Success";
	}


	/*public String testApi(@PathVariable("name")String name) throws IOException {
		Person p = new Person(1L,name);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(bos);
		out.writeObject(p);
		out.flush();
		out.close();
		byte[]byteMessage = bos.toByteArray();
		bos.close();
		Message message = MessageBuilder.withBody(byteMessage)
				.setHeader("item1","mobile")
				.setHeader("item2","television").build();
		rabbitTemplate.send("Headers-Exchange","",message);
		return "Success";




	}*/
}
