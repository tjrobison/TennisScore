package com.tjrobison.android.tenisscore.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tjrobison.android.tenisscore.database.ScoreDbSchema.ScoreTable;

public class ScoreBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "matchScores.db";

    public ScoreBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ScoreTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ScoreTable.Cols.P1Name + ", " +
                ScoreTable.Cols.P2Name + ", " +
                ScoreTable.Cols.P1Set1 + ", " +
                ScoreTable.Cols.P1Set2 + ", " +
                ScoreTable.Cols.P1Set3 + ", " +
                ScoreTable.Cols.P2Set1 + ", " +
                ScoreTable.Cols.P2Set2 + ", " +
                ScoreTable.Cols.P2Set3 + ", " +
                ScoreTable.Cols.P1Match + ", " +
                ScoreTable.Cols.P2Match + ", " +
                ScoreTable.Cols.Completed +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
