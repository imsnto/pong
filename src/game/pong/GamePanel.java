package game.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class GamePanel extends JPanel implements Runnable {
    public static boolean isRunning = true;
    public static boolean isPause = false;
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (5.0 / 9.0));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static  int PADDLE_HEIGHT = 100;
    int paddleSize , ballSpeed;

    Thread gameThread;
    Graphics graphics;
    Paddle paddle1;
    Paddle paddle2;

    Ball ball;
    Score score;

    GamePanel(int paddleSize, int ballSpeed){
        this.paddleSize = paddleSize;
        this.ballSpeed = ballSpeed;
        PADDLE_HEIGHT = paddleSize;
        newPaddle();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void restartGame() {
        isRunning = true;
        score.player1 = 0;
        score.player2 = 0;
        new GameFrame(paddleSize, ballSpeed);
    }
    public void newBall(){
        Random random = new Random();
        ball = new Ball(GAME_WIDTH/2 - BALL_DIAMETER/2, random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER, ballSpeed);
    }
    public void newPaddle(){
        paddle1 = new Paddle(0, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT , 1, paddleSize);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2 , paddleSize);
    }
    public void paint(Graphics g){
        Image image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        if (!isRunning  ) {
            gameOver();
        } else {
            draw(graphics);
        }
        g.drawImage(image, 0, 0, this);
    }
    public void gameOver(){
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        // Display the winner's score
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 30));



        String winnerScore;
        int scoree;
        if (score.player1 > score.player2) {
            winnerScore = "Player 1 Wins! Score: " + score.player1;
            scoree = score.player1;
        } else {
            winnerScore = "Player 2 Wins! Score: " + score.player2;
            scoree = score.player2;
        }
        int currenScore = scoree;
        File file = new File("score.txt");
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int previousMaxScore = sc.nextInt();
        System.out.println(previousMaxScore);
        if(currenScore > previousMaxScore){
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter("score.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                fileWriter.write(Integer.toString(currenScore));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        int stringWidth = graphics.getFontMetrics().stringWidth(winnerScore);
        graphics.drawString(winnerScore, (GAME_WIDTH - stringWidth) / 2, GAME_HEIGHT / 2);

        // Display restart and exit instructions
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        String instructions = "Press 'R' to restart\n" +
                "      'ESC' to exit\n" +
                "      'H' to Home";
        stringWidth = graphics.getFontMetrics().stringWidth(instructions);
        graphics.drawString(instructions, (GAME_WIDTH - stringWidth) / 2, GAME_HEIGHT / 2 + 50);
    }
    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    public void checkCollision(){

        if(ball.y <= 0){
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= GAME_HEIGHT - BALL_DIAMETER){
            ball.setYDirection(-ball.yVelocity);
        }

        if(ball.x < BALL_DIAMETER ) {
            newPaddle();
            newBall();
            score.player2 ++;
        }
        if(ball.x >= GAME_WIDTH - BALL_DIAMETER){
            newPaddle();
            newBall();
            score.player1++;
        }

        if(paddle1.intersects(ball)){
            if(ball.xVelocity < 0)
                ball.setXDirection(-ball.xVelocity);
        }

        if(paddle2.intersects(ball)){
            if(ball.yVelocity<0)
                ball.setXDirection(-ball.xVelocity);
            if(ball.yVelocity>0){
                ball.setXDirection(-ball.xVelocity);
            }
        }

        if(paddle1.y <= 0)
            paddle1.y = 0;
        if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;

        if(paddle2.y <= 0 )
            paddle2.y = 0;
        if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);

            if (e.getKeyCode() == KeyEvent.VK_R && !isRunning) {
                restartGame();
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }else if(e.getKeyCode() == KeyEvent.VK_SPACE && isPause){
                isPause = false;
            }else if(e.getKeyCode() == KeyEvent.VK_SPACE && !isPause){
                isPause = true;
                //gameThread.interrupt();
            }else if(!isRunning && e.getKeyCode() == 'H'){
                score.player1 = 0; score.player2  = 0;
                isRunning = true;
                new GameHomePage();
            }

        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }

    @Override
    public void run() {

        while(isRunning) {
            try {
                Thread.sleep(20);
                if(isPause){
                    while (isPause) Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Math.abs(score.player1 - score.player2) == 5) {
                isRunning = false;
            }
            move();
            checkCollision();
            repaint();

        }

    }
}
