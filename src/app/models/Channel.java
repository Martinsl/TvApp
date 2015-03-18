package app.models;

import java.util.ArrayList;
import java.util.Date;

public class Channel {
	
	public Date date = new Date();
	public ArrayList<String> programs = new ArrayList<String>();
	
	public Channel (String program) {
		this.programs.add(program);
	}
	
	public Channel (Date date, String program) {
		this.date = date;
		this.programs.add(program);
	}
}