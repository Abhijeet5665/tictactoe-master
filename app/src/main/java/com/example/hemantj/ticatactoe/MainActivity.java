package com.example.hemantj.ticatactoe;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int activePlayer = 1; //1 for X and 2 for Y
    ArrayList<Integer> player1 = new ArrayList<>();
    ArrayList<Integer> player2 = new ArrayList<>();
    ArrayList<Integer> emptyPlace = new ArrayList<>();

    private int choice;
    private static final String TAG = "MAIN";
    private boolean gameOver = false;
    private static int draw = 0;
    Button btn1;    Button btn2;
    Button btn3;    Button btn4;
    Button btn5;    Button btn6;
    Button btn7;    Button btn8;
    Button btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        choice = bundle.getInt("play");

        btn1 = (Button) findViewById(R.id.btn1);    btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);    btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);    btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);    btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);

        btn1.setOnClickListener(this);  btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);  btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);  btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);  btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Button btnSelected = (Button) view;
        int itemId = btnSelected.getId();
        int cellId = 0;
        switch (itemId){
            case R.id.btn1:
                cellId = 1;
                break;
            case R.id.btn2:
                cellId = 2;
                break;
            case R.id.btn3:
                cellId = 3;
                break;
            case R.id.btn4:
                cellId = 4;
                break;
            case R.id.btn5:
                cellId = 5;
                break;
            case R.id.btn6:
                cellId = 6;
                break;
            case R.id.btn7:
                cellId = 7;
                break;
            case R.id.btn8:
                cellId = 8;
                break;
            case R.id.btn9:
                cellId = 9;
                break;
        }

        gamePlay(cellId,btnSelected);
        if(gameOver){
            btn1.setEnabled(false);btn2.setEnabled(false);
            btn3.setEnabled(false);btn4.setEnabled(false);
            btn5.setEnabled(false);btn6.setEnabled(false);
            btn7.setEnabled(false);btn8.setEnabled(false);
            btn9.setEnabled(false);
        }

    }

    void gamePlay(int cellId,Button btn){
        Log.d(TAG,String.valueOf(choice));

        if(choice == 1){
            if(activePlayer == 1){
                btn.setText("X");
                btn.setBackgroundColor(Color.argb(1,50,100,200));
                player1.add(cellId);
                activePlayer = 2;
            }
            else if(activePlayer == 2){
                btn.setText("O");
                btn.setBackgroundColor(Color.argb(1,100,200,256));
                player2.add(cellId);
                activePlayer = 1;
            }
        }
        else if(choice == 2){
            if(activePlayer == 1){
                btn.setText("X");
                btn.setBackgroundColor(Color.argb(1,50,100,200));
                player1.add(cellId);
                activePlayer = 2;
                autoPlay();
            }
            else if(activePlayer == 2){
                btn.setText("O");
                btn.setBackgroundColor(Color.argb(1,100,200,256));
                player2.add(cellId);
                activePlayer = 1;
            }
        }

        btn.setEnabled(false);
        checkGame();
        if(draw == 9)
            Toast.makeText(this,"Game Draw",Toast.LENGTH_SHORT).show();
    }

    void checkGame(){
        int winner = -1;
        draw += 1;

        //checking if the rows are complete or not
        for(int i=1;i<=7;i+=3){
            if(player1.contains(i) && player1.contains(i+1) && player1.contains(i+2)){
                winner = 1;
                break;
            }
            if(player2.contains(i) && player2.contains(i+1) && player2.contains(i+2)){
                winner = 2;
                break;
            }
        }

        //checking if the columns are complete or not
        for(int i=1;i<=3;i++){
            if(player1.contains(i) && player1.contains(i+3) && player1.contains(i+6)){
                winner = 1;
                break;
            }
            if(player2.contains(i) && player2.contains(i+3) && player2.contains(i+6)){
                winner = 2;
                break;
            }
        }

        //checking if the diagonal is complete or not
        if(player1.contains(1) && player1.contains(5) && player1.contains(9))
            winner = 1;
        if(player2.contains(3) && player2.contains(5) && player2.contains(7))
            winner = 2;

        if(winner != -1){
            if(winner == 1)
                Toast.makeText(this,"Player 1 is the winner",Toast.LENGTH_SHORT).show();
            if(winner == 2)
                Toast.makeText(this,"Player 2 is the winner",Toast.LENGTH_SHORT).show();
            gameOver = true;
        }
    }

    void autoPlay(){
        for(int i=1;i<=9;i++){
            if(!(player1.contains(i) || player2.contains(i)))
                emptyPlace.add(i);
        }
        Random r = new Random();
        int randIndex = r.nextInt(emptyPlace.size());
        int cellId = emptyPlace.get(randIndex);

        Button btnSelected;
        switch (cellId){
            case 1:
                btnSelected = btn1;
                break;
            case 2:
                btnSelected = btn2;
                break;
            case 3:
                btnSelected = btn3;
                break;
            case 4:
                btnSelected = btn4;
                break;
            case 5:
                btnSelected = btn5;
                break;
            case 6:
                btnSelected = btn6;
                break;
            case 7:
                btnSelected = btn7;
                break;
            case 8:
                btnSelected = btn8;
                break;
            case 9:
                btnSelected = btn9;
                break;
            default:
                btnSelected = btn1;
        }

        gamePlay(cellId,btnSelected);
    }
}
