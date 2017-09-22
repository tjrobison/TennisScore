package com.tjrobison.android.tenisscore.database;

public class ScoreDbSchema {
    public static final class ScoreTable {
        public static final String NAME = "match_scores";

        public static final class Cols {
            public static final String P1Name = "p1name";
            public static final String P2Name = "p2name";
            public static final String P1Set1 = "p1set1";
            public static final String P1Set2 = "p1set2";
            public static final String P1Set3 = "p1set3";
            public static final String P2Set1 = "p2set1";
            public static final String P2Set2 = "p2set2";
            public static final String P2Set3 = "p2set3";
            public static final String P1Match = "p1match";
            public static final String P2Match = "p2match";
            public static final String Completed = "completed";
        }
    }
}
