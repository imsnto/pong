package game.pong;

import java.awt.*;

public class Score extends Rectangle {
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1, player2;
    Score(int GAME_WIDTH, int GAME_HEIGHT){
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }

    public void draw(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));
        g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);

        g.drawString(String.valueOf(player1), (GAME_WIDTH/2)-85, 50);
        g.drawString(String.valueOf(player2), (GAME_WIDTH/2)+40, 50);

        g.setColor(Color.blue);
        g.setFont(new Font("Consolas", Font.PLAIN, 30));
        g.drawString("Player: 1", 230, GAME_HEIGHT - 60);
        g.setColor(Color.red);
        g.drawString("Player: 2", 730, GAME_HEIGHT - 60);

    }


}
