package com.lijia.filedb.test;



import com.lijia.filedb.annotation.Table;
import com.lijia.filedb.annotation.Column;

@Table(name="testObj", schema = "test")
public class TestObj {
	
	

	@Column(id=true)
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String descrption;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}
	



}
