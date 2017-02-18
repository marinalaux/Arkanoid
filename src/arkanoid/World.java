package arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Manipulação do tabuleiro do jogo
 * 
 * @author Marina
 */
public class World implements Entity {
    
    /** Blocos do jogo */
    private Block[][] blocks;
    /** Bola do jogo */
    private Ball ball;
    /** Barra de controle do jogador */
    private Player player;

    public World() {
//        blocks = new Block[20][20];
        blocks = new Block[2][2];
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                if (x % 2==0) {
                    blocks[x][y] = new Block(new Point(x * Block.getTamanho().width + 10, y * Block.getTamanho().height));
                }
            }
        }
        
        ball = new Ball(new Point(100, 100));
        
        player = new Player(400);
    }
    
    @Override
    public void update(UpdateContext context) {

        boolean hasBlocks = false;
        
        player.setX(context.getMousePosition().x - (Player.getTamanho().width / 2));
        
        context.setPlayer(player);
        context.setBlocks(blocks);
        
        ball.update(context);
        player.update(context);
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                if (blocks[x][y] != null) {
                    if (blocks[x][y].isBroken()) {
                        blocks[x][y] = null;
                    } else {
                        blocks[x][y].update(context);
                    }
                }
                if (blocks[x][y] != null) {
                    hasBlocks = true;
                }
            }
        }
        
        if (!hasBlocks) {
            context.setStatus(GameStatus.WON);
        }
        if (ball.collideFloor()) {
            context.setStatus(GameStatus.LOST);
        }
        
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 2000, 2000);
        ball.render(g2d);
        player.render(g);
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                if (blocks[x][y] != null) {
                    blocks[x][y].render(g2d);
                }
            }
        }
        g2d.dispose();
    }
    
}
