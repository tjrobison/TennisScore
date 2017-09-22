package com.tjrobison.android.tenisscore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MatchOptionsActivity extends AppCompatActivity {

    private EditText mPlayer1Name;
    private EditText mPlayer2Name;
    private EditText mSetPoints;
    private Button mDoneButton;

    private String p1name;
    private String p2name;
    private String setpoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_options_fragment);

        mPlayer1Name = (EditText) findViewById(R.id.edit_p1_name_field);
        p1name = "Player 1";

        mPlayer2Name = (EditText) findViewById(R.id.edit_p2_name_field);
        p2name = "Player 2";

        mSetPoints = (EditText) findViewById(R.id.edit_set_points_field);
        setpoints = "6";

        mDoneButton = (Button) findViewById(R.id.match_options_done_button);
        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayer1Name != null && mPlayer1Name.length() != 0) {
                    p1name = mPlayer1Name.getText().toString();
                }
                if (mPlayer1Name != null && mPlayer1Name.length() != 0) {
                    p2name = mPlayer2Name.getText().toString();
                }
                if (mSetPoints != null && mSetPoints.length() != 0) {
                    setpoints = mSetPoints.getText().toString();
                }

                Intent intent = new Intent();
                intent.putExtra("P1NAME", p1name);
                intent.putExtra("P2NAME", p2name);
                intent.putExtra("SETPOINTS", setpoints);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (mPlayer1Name != null && mPlayer1Name.length() != 0) {
            p1name = mPlayer1Name.getText().toString();
        }
        if (mPlayer1Name != null && mPlayer1Name.length() != 0) {
            p2name = mPlayer2Name.getText().toString();
        }
        if (mSetPoints != null && mSetPoints.length() != 0) {
            setpoints = mSetPoints.getText().toString();
        }

        Intent intent = new Intent();
        intent.putExtra("P1NAME", p1name);
        intent.putExtra("P2NAME", p2name);
        intent.putExtra("SETPOINTS", setpoints);
        setResult(RESULT_OK, intent);
        finish();
    }
}
