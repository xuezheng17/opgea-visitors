package com.opgea.visitors.domain.model;

import java.util.Arrays;

public class MailModel {

	private String from;
	private String to;
	private String subject;
	private String bodyText;
	private String[] filePath;
	
	public MailModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MailModel(String from, String to, String subject, String bodyText,
			String[] filePath) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.bodyText = bodyText;
		this.filePath = filePath;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBodyText() {
		return bodyText;
	}
	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}
	public String[] getFilePath() {
		return filePath;
	}
	public void setFilePath(String[] filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		return "MailModel [from=" + from + ", to=" + to + ", subject="
				+ subject + ", bodyText=" + bodyText + ", filePath="
				+ Arrays.toString(filePath) + "]";
	}
}
