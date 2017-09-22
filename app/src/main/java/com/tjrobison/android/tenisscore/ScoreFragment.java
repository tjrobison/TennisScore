package com.tjrobison.android.tenisscore;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreFragment extends Fragment {

    private TextView mPlayer1Name;
    private TextView mPlayer2Name;
    private TextView mPlayer1Set1Score;
    private TextView mPlayer1Set2Score;
    private TextView mPlayer1Set3Score;
    private TextView mPlayer1GameScore;
    private TextView mPlayer2Set1Score;
    private TextView mPlayer2Set2Score;
    private TextView mPlayer2Set3Score;
    private TextView mPlayer2GameScore;
    private Button mResetButton;
    private Button mResetSaveButton;
    private TextView[] mPlayer1Sets = {mPlayer1Set1Score, mPlayer1Set2Score, mPlayer1Set3Score};
    private TextView[] mPlayer2Sets = {mPlayer2Set1Score, mPlayer2Set2Score, mPlayer2Set3Score};

    private static String[] sGameScores = {"0", "15", "30", "40", "AD"};
    private Player mPlayer1;
    private Player mPlayer2;
    private int currentSet;
    private int p1SetsWon;
    private int p2SetsWon;
    private boolean gameCompleted;

    //TODO make points to win set user specified
    private int pointsToWinSet = 4;
    private int advantageToWinSet = 2;

    public static ScoreFragment newInstance() {
        return new ScoreFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //TODO make the player names user specified
        mPlayer1 = new Player("P1");
        mPlayer2 = new Player("P2");
        p1SetsWon = 0;
        p2SetsWon = 0;
        currentSet = 0;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_score, container, false);

        mPlayer1Set1Score = (TextView) v.findViewById(R.id.p1_s1_score);
        mPlayer1Set2Score = (TextView) v.findViewById(R.id.p1_s2_score);
        mPlayer1Set3Score = (TextView) v.findViewById(R.id.p1_s3_score);
        mPlayer1GameScore = (TextView) v.findViewById(R.id.p1_game_score);

        mPlayer2Set1Score = (TextView) v.findViewById(R.id.p2_s1_score);
        mPlayer2Set2Score = (TextView) v.findViewById(R.id.p2_s2_score);
        mPlayer2Set3Score = (TextView) v.findViewById(R.id.p2_s3_score);
        mPlayer2GameScore = (TextView) v.findViewById(R.id.p2_game_score);

        mPlayer1Name = (TextView) v.findViewById(R.id.p1_name);
        mPlayer1Name.setText(mPlayer1.getPlayerName());
        mPlayer1Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = mPlayer1.incrementScore(currentSet, mPlayer2);

                if (score < 0) {
                    mPlayer1GameScore.setText(sGameScores[0]);
                    TextView curSetText = getCurSetTextView(currentSet, 1);
                    handleWonSet(curSetText, mPlayer1, mPlayer2);
                } else {
                    mPlayer1GameScore.setText(sGameScores[score]);
                    mPlayer2GameScore.setText(sGameScores[mPlayer2.getScore()]);
                }
            }
        });

        mPlayer2Name = (TextView) v.findViewById(R.id.p2_name);
        mPlayer2Name.setText(mPlayer2.getPlayerName());
        mPlayer2Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = mPlayer2.incrementScore(currentSet, mPlayer1);
                if (score < 0) {
                    mPlayer2GameScore.setText(sGameScores[0]);
                    TextView curSetText = getCurSetTextView(currentSet, 2);
                    handleWonSet(curSetText, mPlayer2, mPlayer1);
                } else {
                    mPlayer2GameScore.setText(sGameScores[score]);
                    mPlayer1GameScore.setText(sGameScores[mPlayer1.getScore()]);
                }

            }
        });

        mResetButton = (Button) v.findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });

        mResetSaveButton = (Button) v.findViewById(R.id.reset_save_button);
        mResetSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.activity_score, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_match_options) {
            Intent intent = new Intent(getActivity(), MatchOptionsActivity.class);
            startActivityForResult(intent, 1);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            String p1 = data.getStringExtra("P1NAME");
            String p2 = data.getStringExtra("P2NAME");
            setPlayerNames(p1, p2);
            pointsToWinSet = Integer.parseInt(data.getStringExtra("SETPOINTS"));
        }
    }

    private void resetFields() {
        mPlayer1.resetNoSave();
        mPlayer1GameScore.setText("0");
        mPlayer1Set1Score.setText("0");
        mPlayer1Set2Score.setText("0");
        mPlayer1Set3Score.setText("0");
        mPlayer1Set1Score.setTypeface(null, Typeface.NORMAL);
        mPlayer1Set2Score.setTypeface(null, Typeface.NORMAL);
        mPlayer1Set3Score.setTypeface(null, Typeface.NORMAL);

        mPlayer2.resetNoSave();
        mPlayer2GameScore.setText("0");
        mPlayer2Set1Score.setText("0");
        mPlayer2Set2Score.setText("0");
        mPlayer2Set3Score.setText("0");
        mPlayer2Set1Score.setTypeface(null, Typeface.NORMAL);
        mPlayer2Set2Score.setTypeface(null, Typeface.NORMAL);
        mPlayer2Set3Score.setTypeface(null, Typeface.NORMAL);

        p1SetsWon = 0;
        p2SetsWon = 0;
        currentSet = 0;
        mPlayer1Name.setClickable(true);
        mPlayer2Name.setClickable(true);
    }

    private void processGameCompletion(Player player) {
        gameCompleted = true;
        mPlayer1Name.setClickable(false);
        mPlayer2Name.setClickable(false);
        String name = player.getPlayerName();
        Toast.makeText(getActivity(), name + " WINS!", Toast.LENGTH_LONG).show();
    }

    private void handleWonSet(TextView curSetText, Player player, Player oppPlayer) {
        if (curSetText != null) {
            curSetText.setText("" + player.getSetScore(currentSet));
            if (getSetWinner() != 0 && getSetWinner() == 2) {
                curSetText.setTypeface(null, Typeface.BOLD);
                curSetText.setText("" + player.getSetScore(currentSet));
                currentSet = currentSet + 1;
                if (!(player.getSetsWon() + oppPlayer.getSetsWon() + 1 > 4)) {
                    player.incrementSetsWon();
                }
                if (currentSet >= 3) {
                    processGameCompletion(player);
                }
            }
        }
    }

    private void setPlayerNames(String player1, String player2) {
        View view = getView();

        TextView p1 = (TextView) view.findViewById(R.id.p1_name);
        mPlayer1.setPlayerName(player1);
        p1.setText(player1);

        TextView p2 = (TextView) view.findViewById(R.id.p2_name);
        mPlayer2.setPlayerName(player2);
        p2.setText(player2);
    }

    private TextView getCurSetTextView(int currentSet, int player) {
        if (player == 1) {
            switch (currentSet) {
                case 0:
                    return mPlayer1Set1Score;
                case 1:
                    return mPlayer1Set2Score;
                case 2:
                    return mPlayer1Set3Score;
                default:
                    return null;
            }
        } else {
            switch (currentSet) {
                case 0:
                    return mPlayer2Set1Score;
                case 1:
                    return mPlayer2Set2Score;
                case 2:
                    return mPlayer2Set3Score;
                default:
                    return null;
            }
        }
    }

    private int getSetWinner() {
        int p1SetScore = mPlayer1.getSetScore(currentSet);
        int p2SetScore = mPlayer2.getSetScore(currentSet);
        if (p1SetScore >= pointsToWinSet || p2SetScore >= pointsToWinSet) {

            if (p1SetScore - p2SetScore >= advantageToWinSet) {
                return 1;
            } else if (p2SetScore - p1SetScore >= advantageToWinSet) {
                return 2;
            } else {
                return 0;
            }

        } else {
            return 0;
        }
    }
}
