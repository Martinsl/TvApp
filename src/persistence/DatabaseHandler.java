package persistence;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 6;

	// Database Name
	private static final String DATABASE_NAME = "FavoritesManager";

	// Favoritess table name
	private static final String TABLE_FAVORITES = "Favorites";

	// Favoritess Table Columns names
	public static final String _ID = "id";
	public static final String KEY_CHANNELNAME = "channelName";
	public static final String KEY_PROGRAMNAME = "programName";
	public static final String KEY_TIME = "airTime";
	public static final String KEY_DATE = "airDate";


	private DatabaseHandler mDbHelper;
	private SQLiteDatabase mDb;
	private static final String TAG = "FavoritesDbAdapter";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		//onCreate(this.getWritableDatabase());
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("Iaadasdasdadasdsadnsert: ", "Inasdasdasdsadasdserting .."); 
		String CREATE_FavoritesS_TABLE = "CREATE TABLE " + TABLE_FAVORITES + "("
				+ _ID + " INTEGER ," + KEY_CHANNELNAME  + " TEXT ," + 
				KEY_PROGRAMNAME + " TEXT , " + KEY_TIME + " TEXT, " + KEY_DATE + " TEXT "+", "
						+ "PRIMARY KEY ("+KEY_CHANNELNAME+"))";
		db.execSQL(CREATE_FavoritesS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);

		// Create tables again
		onCreate(db);
	}

	public DatabaseHandler open(Context ctx) throws SQLException {
		mDbHelper = new DatabaseHandler(ctx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		if (mDbHelper != null) {
			mDbHelper.close();
		}
	}

	/**
	 * All CRUD(Create, Read, UpprogramName, Delete) Operations
	 */

	// Adding new Favorites
	public void addFavorites(Favorites Favorites) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_CHANNELNAME, Favorites.getchannelName()); // Favorites desc
		values.put(KEY_PROGRAMNAME, Favorites.get_programName()); // Favorites programName
		values.put(KEY_TIME, Favorites.get_time());
		values.put(KEY_DATE, Favorites.get_date());

		// Inserting Row
		db.insert(TABLE_FAVORITES, null, values);
		db.close(); // Closing database connection
	}

	// Getting single Favorites
	Favorites getFavorites(int id) {

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_FAVORITES, new String[] { _ID,
				KEY_CHANNELNAME, KEY_PROGRAMNAME,KEY_TIME,KEY_DATE }, _ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Favorites Favorites = new Favorites(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4));
		// return Favorites
		return Favorites;
	}

	// Getting All Favoritess
	public List<Favorites> getAllFavoritess() throws ParseException {
		List<Favorites> FavoritesList = new ArrayList<Favorites>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_FAVORITES;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Favorites Favorites = new Favorites();
				Favorites.setID(Integer.parseInt(cursor.getString(0)));
				Favorites.setchannelName(cursor.getString(1));
				Favorites.setprogramName(cursor.getString(2));
				Favorites.set_time(cursor.getString(3));
				Favorites.set_date(cursor.getString(4));
				// Adding Favorites to list
				FavoritesList.add(Favorites);
			} while (cursor.moveToNext());
		}

		// return Favorites list
		return FavoritesList;
	}

	// Updating single Favorites
	public int updateFavorites(Favorites Favorites) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_CHANNELNAME, Favorites.getchannelName()); // Favorites desc
		values.put(KEY_PROGRAMNAME, (String)Favorites.get_programName()); // Favorites programName
		values.put(KEY_TIME, Favorites.get_time());

		// updating row
		return db.update(TABLE_FAVORITES, values, _ID + " = ?",
				new String[] { String.valueOf(Favorites.getID()) });
	}

	// Deleting single Favorites
	public void deleteFavorites(Favorites Favorites) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_FAVORITES, _ID + " = ?",
				new String[] { String.valueOf(Favorites.getID()) });
		db.close();
	}


	// Getting Favoritess Count
	public int getFavoritesCount() {
		String countQuery = "SELECT  * FROM " + TABLE_FAVORITES;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

	public Cursor fetchAllFavorites() {
		SQLiteDatabase db = this.getReadableDatabase(); 
		Cursor cursor = db.query(TABLE_FAVORITES, new String[] { "rowid _id",
				KEY_CHANNELNAME, KEY_PROGRAMNAME,KEY_TIME,KEY_DATE },null, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		return cursor;
	}


	public boolean deleteAllFavorites() {

		int doneDelete = 0;
		doneDelete = mDb.delete(TABLE_FAVORITES, null , null);
		
		Log.w(TAG, Integer.toString(doneDelete));

		return doneDelete > 0;

	}

	public int findByChannel(String channel){
		String countQuery = "SELECT  * FROM " + TABLE_FAVORITES + " WHERE channelName = '" + channel+"'";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		
		int cont = cursor.getCount();
		cursor.close();
		
		if (cont == 0)
			return 0;
		else
			return 1;

	}
	
	public void deleteByChannel(String channel){
		String query = "DELETE  FROM " + TABLE_FAVORITES + " WHERE channelName = '" + channel+"'";
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL(query);
		db.close();

	}


}