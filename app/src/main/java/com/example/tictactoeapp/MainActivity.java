package com.example.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Inflater.ExampleDialogListener {

                                                                                                    //adding all the TextFields
    private TextView PlayerOneScore, PlayerTwoScore, playerStatus, edit_PlayerOne, edit_PlayerTwo;
    private int playerOneScoreCount, playerTwoScoreCount;


    //adding all buttons
    private Button resetGame;
    private Button resetTurns;
    private Button[] buttons = new Button[9];
    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8;                           //all the X and O positions as buttons

                                                                                                    //assigning values to players and empty
    int playerOne = 0;                                                                              //PlayerOne is consider as '0'
    int playerTwo = 1;                                                                              //PlayerTwo is consider as '1'
    int[] filledPos = {-1, -1, -1, -1, -1, -1, -1, -1, -1};                                         //-1 is consider empty

                                                                                                    //adding activePlayer and GameState
    int activePlayer = playerOne;                                                                   //in start the
    boolean isGameActive = true;                                                                    //boolean for checking of the game is active


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assigning all the Text and Buttons to the Layout Xml

        edit_PlayerOne = (TextView) findViewById(R.id.PlayerOne);
        edit_PlayerTwo = (TextView) findViewById(R.id.playerTwo);

        PlayerOneScore = (TextView) findViewById(R.id.PlayerOneScore);
        PlayerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        playerStatus = (TextView) findViewById(R.id.PlayerStatus);

        resetGame = (Button) findViewById(R.id.ResetGame);
        resetTurns = (Button) findViewById(R.id.resetTurns);


        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);

                                                                                                    //adding OnClickListener to each Button (to provide action when clicked)
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);

                                                                                                    //making all Scores and gameCount to '0' when the app starts
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;

        TextView PlyOne = findViewById(R.id.PlayerOne);                                             //Inflater
        PlyOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        TextView PlyTwo = findViewById(R.id.playerTwo);
        PlyTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

    }
    public void openDialog() {
        Inflater inflater = new Inflater();
        inflater.show(getSupportFragmentManager(), "example dialog");

    }
    //if the PlayerName is empty then it will restore them to default state other then leaving them empty
    @Override
    public void applyTexts(String PlayerOne, String PlayerTwo) {
        if (PlayerOne.length() == 0) edit_PlayerOne.setText("Player One");
        else edit_PlayerOne.setText(PlayerOne);
        if (PlayerTwo.length() == 0) edit_PlayerTwo.setText("Player Two");
        else edit_PlayerTwo.setText(PlayerTwo);
    }

                                                                                                    //Creating a Reset function, when called all the positions will be blank or '-1'
    public void resetTurns() {

        filledPos = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};
        btn_0.setText("");
        btn_1.setText("");
        btn_2.setText("");
        btn_3.setText("");
        btn_4.setText("");
        btn_5.setText("");
        btn_6.setText("");
        btn_7.setText("");
        btn_8.setText("");
        isGameActive = true;                                                                        //Making the gameState True so the App is Running
        updatePlayerScore();                                                                        //Updating PlayerScore
        resetTurns.setVisibility(View.INVISIBLE);                                                   //making the ReSetTurn Button 'Invisible' as the Game ReSet
    }

    @Override
    public void onClick(View v) {

        if(!isGameActive)                                                                           //for Every Click of any Button Checking if the Game is Active or Not
            return;

        Button clickedBtn = findViewById(v.getId());                                                // find the id of the button
        int clickedTag = Integer.parseInt(v.getTag().toString());                                   //changing Clicked tags to String 'X' or 'O'

        //check if the position empty or not with tag to prevent Overriding.
        if (filledPos[clickedTag] != -1) {                                                          //Checking the the Clicked Position is empty or not
            return;                                                                                 //Unless its empty it's not allowed to be overWritten
        }

        filledPos[clickedTag] = activePlayer;                                                       //if the pos is not empty then

        if (activePlayer == playerOne) {                                        //Creating an if statement in if the active player is 'X' then he's Clicked position will be filled with 'X'
            clickedBtn.setText("X");                                                                // and it ActivePlayer will become 'O'
            activePlayer = playerTwo;
            ((Button) v).setTextColor(Color.parseColor("#FFC34A"));
            playerStatus.setText(edit_PlayerTwo.getText().toString() + " turn");                                                         //as every click the PlayerStatus Text Changes Between X and O
        } else {
            clickedBtn.setText("O");
            activePlayer = playerOne;
            ((Button) v).setTextColor(Color.parseColor("#70FFEA"));
            playerStatus.setText(edit_PlayerOne.getText().toString() + " turn");
        }
        resetTurns.setVisibility(View.VISIBLE);                                                     //Setting the ResetTurn Button Visible after Click so it can be reset


                                                                                                    //adding ClickListener to ResetTurn Button by calling the resetTurn function
        resetTurns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTurns();
            }
        });

                                                                                                    //adding ClickListener to ResetGame Button by calling the resetTurn function
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTurns();
                                                                                                    //also making all the player status as the beginning to Reset all stats
                activePlayer = playerOne;
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                playerStatus.setText(edit_PlayerOne.getText().toString() + " turn");
                isGameActive = true;
                updatePlayerScore();
                resetTurns.setVisibility(View.INVISIBLE);
            }
        });
        CheckForWin();
    }

    //Creating logic to find winner on every Click
    public void CheckForWin() {
                                                                                                    //Making a String of all Possible winning positions to define the winner
        int[][] winningPos = {  {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                                {0, 4, 8}, {2, 4, 6}            };

        for (int i = 0; i < 8; i++) {                                                               //setting up the winningPos String to # different Val
            int val0 = winningPos[i][0];
            int val1 = winningPos[i][1];
            int val2 = winningPos[i][2];

            if (filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]) {         //checking for winner
                if (filledPos[val0] != -1) {
                    //winner declare

                    isGameActive = false;                                                           //as soon as the winner is found the Gave State gets False so no one can enter extra Values

                    if (filledPos[val0] == playerOne) {                                             //making an If statement for the action for the winner
                        playerStatus.setText(edit_PlayerOne.getText().toString() + " is the winner");
                        playerOneScoreCount++;
                        updatePlayerScore();
                        Toast.makeText(this, edit_PlayerOne.getText().toString() + " Won", Toast.LENGTH_SHORT).show();
                    } else {
                        playerStatus.setText(edit_PlayerTwo.getText().toString() + " is the Winner");
                        playerTwoScoreCount++;
                        updatePlayerScore();
                        Toast.makeText(this, edit_PlayerTwo.getText().toString() + "  Won", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
    public void updatePlayerScore() {                                                           //updating player Scores
        PlayerOneScore.setText(Integer.toString(playerOneScoreCount));
        PlayerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }

}

