package app.models;


public class Program {

	public String name;
	public String description;
	public String directors;
	public String cast;
	public String country;
	public String ageRating;
	public int year;
	
	public Program (String name) {
		this.name = name;
	}
	
	public Program (String name, String description, String directors, String cast,
					String country, String ageRating, int year) {
		this.name = name;
		this.description = description;
		this.directors = directors;
		this.cast = cast;
		this.country = country;
		this.year = year;
		this.ageRating = ageRating;
	}
}
