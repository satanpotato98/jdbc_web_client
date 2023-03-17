package com.hdfc.webclient.entity;

import java.sql.Date;



public class Student {
	private int id;
	private String name;
	private String dept;
	private Date doj;
	public int getId() {
		return id;
	}
	public Student() {}
	public Student(int id, String name, String dept, Date doj) {
		super();
		this.id=id;
		this.dept=dept;
		this.name=name;
		this.doj=doj;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	@Override
	public String toString() {
		return "student [id=" + id + ", name=" + name + ", dept=" + dept + ", doj=" + doj + "]";
	}
}