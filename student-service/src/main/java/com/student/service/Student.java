package com.student.service;

//due to not add lombok dependency during creating up Spring Starter Project we cannot use lombok annotation
//sp try do lombok annotation by yourself
public class Student {
	private String name;
	private String className;
	public Student(String name, String className) {
		super();
		this.name = name;
		this.className = className;
	}
	public Student() {}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
}
