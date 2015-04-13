package app.models;

import java.util.ArrayList;
import java.util.Date;

public class Channel {
	
	public Date date = new Date();
	public String name;
	public ArrayList<String> programs = new ArrayList<String>();
	
	public Channel (String program) {
		this.programs.add(program);
	}
	
	public Channel (String name, Date date, String program) {
		this.name = name;
		this.date = date;
		this.programs.add(program);
	}
}