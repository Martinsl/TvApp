package com.example.tvapp;

import com.google.gson.Gson;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import app.models.Programs;
import app.models.TvProgram;

public class DisplayListActivity extends Base {

	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String channelJson = extras.getString("ChannelsJson");
			Log.v("Activity Params", channelJson);
			
			Gson gson = new Gson();
			TvProgram channelG = gson.fromJson(channelJson, TvProgram.class);
			
			listView = (ListView) findViewById(R.id.channelSchedule);
		    ChannelAdapter adapter = new ChannelAdapter(this, channelG);
		    listView.setAdapter(adapter);
		} else {
			listView = (ListView) findViewById(R.id.channelSchedule);
		    ChannelAdapter adapter = new ChannelAdapter(this, channelGson);
		    listView.setAdapter(adapter);
		}
	}
}

class ChannelAdapter extends ArrayAdapter<TvProgram> {

	private Context context;
	public  TvProgram programsList;

	public ChannelAdapter(Context context, TvProgram programsList) {
		super(context, R.layout.row_display);
	    this.context   = context;
	    this.programsList = programsList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

	    LayoutInflater inflater = (LayoutInflater) context
	    		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	    View view = inflater.inflate(R.layout.row_display, parent, false);
	    Programs program = programsList.programs.get(position);
	    TextView nameView = (TextView) view.findViewById(R.id.program_name);
	    TextView timeView = (TextView) view.findViewById(R.id.program_time);
	    
	    nameView.setText(program.name);
	    timeView.setText(program.time);
	    return view;
	}

	@Override
	public int getCount() {
	    return programsList.programs.size();
	}
}