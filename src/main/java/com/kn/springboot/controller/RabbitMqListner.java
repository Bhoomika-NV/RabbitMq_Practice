package com.kn.springboot.controller;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import Entity.Person.Person;


@Service
public class RabbitMqListner {
 
	 @RabbitListener(queues = "Tv")
	 public void getMessage(Person p)
	{
		System.out.println(p.getName());
	}
	 

	/*  public void getMessage(byte[]message) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis = new ByteArrayInputStream(message);
	  ObjectInput in = new ObjectInputStream(bis);
	  Person p =(Person) in.readObject();
	  in.close();
	  bis.close();
	  System.out.println(p.getName());
	}*/
 
	
}
