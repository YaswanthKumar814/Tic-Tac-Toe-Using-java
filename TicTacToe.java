import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;
    
    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton[][] board = new JButton[3][3]; //array to keep track of all elements
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;

    int turns=0;


    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null); //Opens the window in the center of the screen
        frame.setResizable(false); //window is not resizable
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.red);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Algerian", Font.BOLD,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH); //north refers to top part of the window

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.cyan);
        frame.add(boardPanel);

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                JButton tile = new JButton();
                board[i][j]=tile;
                boardPanel.add(tile);
                tile.setBackground(Color.cyan);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                tile.setFocusable(false);    
                // tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent a){
                        if(gameOver) return;
                        JButton tile =  (JButton) a.getSource();
                        if(tile.getText() ==""){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();  
                            if(!gameOver){
                                currentPlayer = currentPlayer==playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn");
                            }
                        }
                    }
                });
            }
        }

    }
    void checkWinner(){
        //horizontal
        for(int i=0;i<3;i++){
            if(board[i][0].getText()=="") continue;
            if(board[i][0].getText()== board[i][1].getText() && 
            board[i][1].getText() == board[i][2].getText()){
                for(int j=0;j<3;j++){
                    setWinner(board[i][j]);
                }
                gameOver=true;
                return; 
            }
        }
        //vertical
        for(int i=0;i<3;i++){
            if(board[0][i].getText()=="") continue;
            if(board[0][i].getText()==board[1][i].getText() &&
            board[1][i].getText()==board[2][i].getText()){
                for(int j=0;j<3;j++){
                    setWinner(board[j][i]);
                }
                gameOver = true;
                return;
            }
        }
        //diagonally
        if (board[0][0].getText() == board[1][1].getText() &&
        board[1][1].getText() == board[2][2].getText() &&
        board[0][0].getText() != "") {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
       }
        //anti-diagonal
        if (board[0][2].getText() == board[1][1].getText() &&
        board[1][1].getText() == board[2][0].getText() &&
        board[0][2].getText() != "") {
        setWinner(board[0][2]);
        setWinner(board[1][1]);
        setWinner(board[2][0]);
        gameOver = true;
        return;
        }
        //tie
        if(turns==9){
            for(int i=0;i<3;i++){           
                for(int j=0;j<3;j++){
                    tie(board[i][j]);
                }
            }
            gameOver=true;
        }

    }
    void setWinner(JButton tile){
        tile.setForeground(Color.green);  
        textLabel.setText(currentPlayer + " is the winner");

    }
    void tie(JButton tile){
        tile.setForeground(Color.yellow);
        textLabel.setText(" It is a Tie! ");
    }
}
