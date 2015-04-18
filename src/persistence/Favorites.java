package persistence;

public class Favorites {

	int _id;
	String _channelName;
	String _programName;
	String _time;
	String _date;

	// Empty constructor
	public Favorites(){

	}
	// constructor
	public Favorites(int id, String channelName, String programName,String time,String date){
		this._id = id;
		this._channelName = channelName;
		this._programName = programName;
		this._time = time;
		this._date = date;
	}


	// constructor
	public Favorites(String name, String _programName, String time,String date){
		this._channelName = name;
		this._programName = _programName;
		this._time = time;
		this._date = date;
	}
	// getting ID
	public int getID(){
		return this._id;
	}

	// setting id
	public void setID(int id){
		this._id = id;
	}

	// getting name
	public String getchannelName(){
		return this._channelName;
	}

	// setting name
	public void setchannelName(String name){
		this._channelName = name;
	}

	// getting programName
	public String get_programName(){
		return this._programName;
	}

	// setting programName
	public void setprogramName(String _programName){
		this._programName = _programName;
	}
	public String get_time() {
		return _time;
	}
	public void set_time(String _time) {
		this._time = _time;
	}
	public String get_date() {
		return _date;
	}
	public void set_date(String _date) {
		this._date = _date;
	}


}