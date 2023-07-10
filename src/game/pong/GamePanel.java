package game.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    public static boolean isRunning = true;
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (5.0 / 9.0));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;

    Thread gameThread;
    Graphics graphics;
    Paddle paddle1;
    Paddle paddle2;

    Ball ball;
    Score score;

    GamePanel(){
        newPaddle();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void startGame(){

    }
    public void restartGame() {
        isRunning = true;
        score.player1 = 0;
        score.player2 = 0;
        new GameFrame();
    }
    public void newBall(){
        Random random = new Random();
        ball = new Ball(GAME_WIDTH/2 - BALL_DIAMETER/2, random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }
    public void newPaddle(){
        paddle1 = new Paddle(0, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT , 1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2 );
    }
    public void paint(Graphics g){
        Image image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        if (!isRunning) {
            // Clear the screen
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

            // Display the winner's score
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Arial", Font.BOLD, 30));
            String winnerScore;
            if (score.player1 > score.player2) {
                winnerScore = "Player 1 Wins! Score: " + score.player1;
            } else {
                winnerScore = "Player 2 Wins! Score: " + score.player2;
            }
            int stringWidth = graphics.getFontMetrics().stringWidth(winnerScore);
            graphics.drawString(winnerScore, (GAME_WIDTH - stringWidth) / 2, GAME_HEIGHT / 2);

            // Display restart and exit instructions
            graphics.setFont(new Font("Arial", Font.BOLD, 20));
            String instructions = "Press 'R' to restart or 'ESC' to exit.";
            stringWidth = graphics.getFontMetrics().stringWidth(instructions);
            graphics.drawString(instructions, (GAME_WIDTH - stringWidth) / 2, GAME_HEIGHT / 2 + 50);
        } else {
            // Draw the game elements
            draw(graphics);
        }
        g.drawImage(image, 0, 0, this);
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(Math.abs(score.player1 - score.player2) == 3) {
                isRunning = false;
            }
            move();
            checkCollision();
            repaint();
        }

    }
}
