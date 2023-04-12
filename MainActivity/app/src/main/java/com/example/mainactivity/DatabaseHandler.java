package com.example.mainactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

//Handles all functions relating to database

public class DatabaseHandler extends SQLiteOpenHelper {

    public static String dateSelected = "n/a";

    // tables
    public static final String INTAKE_INPUT_TABLE = "intake_input_table";

    //global columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";

    //intake_input_table column names
    public static final String COLUMN_INPUT_DATE = "input_date";
    public static final String COLUMN_KCAL = "kcal";
    public static final String COLUMN_PROTEIN = "protein";
    public static final String COLUMN_CARB = "carb";
    public static final String COLUMN_FAT = "fat";
    public static final String COLUMN_FOOD_AMT = "food_amt";
    public static final String COLUMN_FOOD_UNIT = "food_unit";
    public static final String COLUMN_MEAL_TYPE = "meal_type";

    // Table Create Statements
    public static final String CREATE_TABLE_INTAKE_INPUT = "CREATE TABLE " + INTAKE_INPUT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMN_NAME + " TEXT, " +
            COLUMN_INPUT_DATE + " TEXT, " + COLUMN_KCAL + " INT, " + COLUMN_PROTEIN + " INT, " + COLUMN_CARB + " INT, " + COLUMN_FAT + " INT, " + COLUMN_FOOD_AMT + " INT, "
            + COLUMN_FOOD_UNIT + " TEXT, " + COLUMN_MEAL_TYPE + " INT)";

    public DatabaseHandler(@Nullable Context context) {
        super(context, "HealthBuddyDatabase", null, 1);
    }

    // called the first time the database is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_INTAKE_INPUT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addIntakeItem(IntakeInput intakeInput) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        //set item values from class intakeInput
        cv.put(COLUMN_NAME, intakeInput.getName());
        cv.put(COLUMN_INPUT_DATE, intakeInput.getInputDate());
        cv.put(COLUMN_KCAL, intakeInput.getKcal());
        cv.put(COLUMN_PROTEIN, intakeInput.getProtein());
        cv.put(COLUMN_CARB, intakeInput.getCarb());
        cv.put(COLUMN_FAT, intakeInput.getFat());
        cv.put(COLUMN_FOOD_AMT, intakeInput.getFoodAmt());
        cv.put(COLUMN_FOOD_UNIT, intakeInput.getFoodUnit());
        cv.put(COLUMN_MEAL_TYPE, intakeInput.getMealType());

        long insert = db.insert(INTAKE_INPUT_TABLE, null, cv);
        db.close();
        if (insert == -1) {
            return false;
        } else {
            return true;
        }

    }

    public ArrayList<IntakeInput> getAllIntake() {
        ArrayList<IntakeInput> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + INTAKE_INPUT_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            //loop through cursor and initialise values from database into arraylist
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String date = cursor.getString(2);
                int kcal = cursor.getInt(3);
                int protein = cursor.getInt(4);
                int carb = cursor.getInt(5);
                int fat = cursor.getInt(6);
                int amount = cursor.getInt(7);
                String unit = cursor.getString(8);
                int meal = cursor.getInt(9);

                IntakeInput newIntake = new IntakeInput(id, name, date, kcal, protein, carb, fat, amount, unit, meal);
                returnList.add(newIntake);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean deleteIntake(IntakeInput intakeInput) {
        //find intakeInput item in database
        SQLiteDatabase db = getWritableDatabase();
        String queryString = "DELETE FROM " + INTAKE_INPUT_TABLE + " WHERE " + COLUMN_ID + " = " + intakeInput.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {return false;}
    }
}
