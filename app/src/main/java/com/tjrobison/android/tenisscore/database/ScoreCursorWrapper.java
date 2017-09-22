package com.tjrobison.android.tenisscore.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.tjrobison.android.tenisscore.Match;
import com.tjrobison.android.tenisscore.database.ScoreDbSchema.ScoreTable;

public class ScoreCursorWrapper extends CursorWrapper {

    public ScoreCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Match getMatch() {
        String p1name = getString(getColumnIndex(ScoreTable.Cols.P1Name));
        int p1set1score = getInt(getColumnIndex(ScoreTable.Cols.P1Set1));
        int p1set2score = getInt(getColumnIndex(ScoreTable.Cols.P1Set2));
        int p1set3score = getInt(getColumnIndex(ScoreTable.Cols.P1Set3));
        int p1matchscore = getInt(getColumnIndex(ScoreTable.Cols.P1Match));
        String p2name = getString(getColumnIndex(ScoreTable.Cols.P2Name));
        int p2set1score = getInt(getColumnIndex(ScoreTable.Cols.P2Set1));
        int p2set2score = getInt(getColumnIndex(ScoreTable.Cols.P2Set2));
        int p2set3score = getInt(getColumnIndex(ScoreTable.Cols.P2Set2));
        int p2matchscore = getInt(getColumnIndex(ScoreTable.Cols.P2Match));
        int completed = getInt(getColumnIndex(ScoreTable.Cols.Completed));

        Match match = new Match();
        match.setPlayerNames(p1name, p2name);
        match.setPlayer1Stats(p1set1score, p1set2score, p1set3score, p1matchscore);
        match.setPlayer2Stats(p2set1score, p2set2score, p2set3score, p2matchscore);
        match.setMatchCompleted(completed);

        return match;
    }
}
