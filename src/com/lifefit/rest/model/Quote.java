package com.lifefit.rest.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
@XmlAccessorType(XmlAccessType.NONE)
public class Quote implements Serializable {
	private final static long serialVersionUID = 1L;
	
	@XmlElement
	private String quote;
	@XmlElement
	private String author;

	public Quote() {}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
