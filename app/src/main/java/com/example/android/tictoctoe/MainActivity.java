package com.example.android.tictoctoe;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    int[] score = new int[2];
    int[][][] grid = new int[2][3][3];
    char currUser = 'O';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetGrid();
        resetScore();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void resetClick(View view)
    {
        resetGrid();
        resetScore();
    }

    void resetScore()
    {
        TextView tv;
        score[0]=0;
        score[1]=0;
        tv = (TextView)findViewById(R.id.score1);
        tv.setText("" + score[0]);
        tv = (TextView)findViewById(R.id.score2);
        tv.setText("" + score[1]);
    }

    void resetGrid()
    {
        Button bt;
        currUser = 'O';
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                grid[0][i][j]=0;
                grid[1][i][j]=0;
            }
        }

        bt = (Button)findViewById(R.id.bb00);
        bt.setText(" ");
        bt = (Button)findViewById(R.id.bb01);
        bt.setText(" ");
        bt = (Button)findViewById(R.id.bb02);
        bt.setText(" ");

        bt = (Button)findViewById(R.id.bb10);
        bt.setText(" ");
        bt = (Button)findViewById(R.id.bb11);
        bt.setText(" ");
        bt = (Button)findViewById(R.id.bb12);
        bt.setText(" ");

        bt = (Button)findViewById(R.id.bb20);
        bt.setText(" ");
        bt = (Button)findViewById(R.id.bb21);
        bt.setText(" ");
        bt = (Button)findViewById(R.id.bb22);
        bt.setText(" ");
    }

    public void newGameClick(View view)
    {
        resetGrid();
        currUser = 'O';
    }

    public void bbClick(View view)
    {
        int userIndex;
        int row,col;
        if(currUser=='O')
            userIndex=0;
        else
            userIndex=1;


        Button bt;

        if(view.getId()==R.id.bb00)
        {
            bt = (Button)findViewById(R.id.bb00);
            row=0;
            col=0;
        }
        else if(view.getId()==R.id.bb01)
        {
            bt = (Button)findViewById(R.id.bb01);
            row=0;
            col=1;
        }
        else if(view.getId()==R.id.bb02)
        {
            bt = (Button)findViewById(R.id.bb02);
            row=0;
            col=2;
        }
        else if(view.getId()==R.id.bb10)
        {
            bt = (Button)findViewById(R.id.bb10);
            row=1;
            col=0;
        }
        else if(view.getId()==R.id.bb11)
        {
            bt = (Button)findViewById(R.id.bb11);
            row=1;
            col=1;
        }
        else if(view.getId()==R.id.bb12)
        {
            bt = (Button)findViewById(R.id.bb12);
            row=1;
            col=2;
        }
        else if(view.getId()==R.id.bb20)
        {
            bt = (Button)findViewById(R.id.bb20);
            row=2;
            col=0;
        }
        else if(view.getId()==R.id.bb21)
        {
            bt = (Button)findViewById(R.id.bb21);
            row=2;
            col=1;
        }
        else
        {
            bt = (Button)findViewById(R.id.bb22);
            row=2;
            col=2;
        }

        if(grid[(userIndex+1)%2][row][col]!=1)
        {
            grid[userIndex][row][col]=1;
            if (currUser == 'O')
            {
                bt.setText("O");
                currUser = 'X';
            }
            else
            {
                bt.setText("X");
                currUser = 'O';
            }
            gameWinCheck(userIndex);
        }
    }

    void gameWinCheck(int index)
    {
        int gameWin=0;

        if(grid[index][0][0]+grid[index][1][1]+grid[index][2][2]==3) //diagonal-1
        {
            gameWin=1;
        }
        else if(grid[index][0][2]+grid[index][1][1]+grid[index][2][0]==3) //diagonal-2
        {
            gameWin=1;
        }
        else if(grid[index][0][0]+grid[index][0][1]+grid[index][0][2]==3) //row-1
        {
            gameWin=1;
        }
        else if(grid[index][1][0]+grid[index][1][1]+grid[index][1][2]==3) //row-2
        {
            gameWin=1;
        }
        else if(grid[index][2][0]+grid[index][2][1]+grid[index][2][2]==3) //row-3
        {
            gameWin=1;
        }
        else if(grid[index][0][0]+grid[index][1][0]+grid[index][2][0]==3) //col-1
        {
            gameWin=1;
        }
        else if(grid[index][0][1]+grid[index][1][1]+grid[index][2][1]==3) //col-2
        {
            gameWin=1;
        }
        else if(grid[index][0][2]+grid[index][1][2]+grid[index][2][2]==3) //col-3
        {
            gameWin=1;
        }
        else
        {
            int sum=0;
            gameWin=0;
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    sum = sum + grid[0][i][j];
                    sum = sum + grid[1][i][j];
                }
            }
            if(sum==9)
            {
                Toast.makeText(this,"Game draw.",Toast.LENGTH_SHORT).show();
                resetGrid();
                currUser = 'O';
            }
        }

        if(gameWin==1)
        {
            TextView tv;
            score[index]++;
            if(index==0)
            {
                tv = (TextView) findViewById(R.id.score1);
                Toast.makeText(this,"Player-1 won the game.",Toast.LENGTH_SHORT).show();
            }
            else
            {
                tv = (TextView) findViewById(R.id.score2);
                Toast.makeText(this,"Player-2 won the game.",Toast.LENGTH_SHORT).show();
            }
            tv.setText("" + score[index]);
            resetGrid();
            currUser = 'O';
        }
    }
}
