package com.lifefit.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="results")
@XmlAccessorType(XmlAccessType.FIELD)
public class QuoteStore {

	@XmlElement(name="result")
	private List<Quote> data = new ArrayList<Quote>();
	
	public QuoteStore() {}

	public List<Quote> getData() {
		return data;
	}

	public void setData(List<Quote> data) {
		this.data = data;
	}
}
