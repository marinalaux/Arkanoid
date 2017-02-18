package arkanoid;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Gerenciador do jogo
 * 
 * @author Marina
 */
public class Game implements Painter {
    
    /** Tabuleiro do jogo */
    private World world;
    /** Thread para atualização do jogo */
    private Thread updateThread;
    /** Painel para criação dos componentes do jogo */
    private GamePanel panel;
    /** Contexto de atualização do jogo */
    private UpdateContext context;

    /**
     * Construtor do jogo
     * 
     * @param gamePanel
     */
    public Game(GamePanel gamePanel) {
        panel = gamePanel;
        updateThread = new Thread(new GameRunnable());
        world = new World();
        context = new UpdateContext();
        context.setStatus(GameStatus.PLAYING);
        panel.setPainter(this);
    }

    /**
     * Inicia a thread de controle do jogo
     */
    public void start() {
        updateThread.start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        world.render(g2d);
        if (context.getStatus().equals(GameStatus.LOST)) {
            paintOverlayLost(g2d);
        }
        if (context.getStatus().equals(GameStatus.WON)) {
            paintOverlayWon(g2d);
        }
        g2d.dispose();
    }

    /**
     * Finaliza o jogo ao perder
     * 
     * @param g2d 
     */
    private void paintOverlayLost(Graphics2D g2d) {

        FontMetrics metrics;
        int stringWidth, x;
        
        g2d.setColor(new Color(0x80FFFFFF, true));
        g2d.fillRect(0, 0, panel.getWidth(), panel.getHeight());
        
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 48));
        
        metrics = g2d.getFontMetrics();
        stringWidth = metrics.stringWidth("Loser!");
        x = (panel.getWidth() / 2) - (stringWidth / 2);
        
        g2d.drawString("Loser!", x, panel.getHeight() / 2);
    }

    /**
     * Finaliza o jogo ao ganhar
     * 
     * @param g2d 
     */
    private void paintOverlayWon(Graphics2D g2d) {

        FontMetrics metrics;
        int stringWidth, x;
        
        g2d.setColor(new Color(0x80FFFFFF, true));
        g2d.fillRect(0, 0, panel.getWidth(), panel.getHeight());
        
        g2d.setColor(Color.CYAN);
        g2d.setFont(new Font("Arial", Font.BOLD, 48));
        
        metrics = g2d.getFontMetrics();
        stringWidth = metrics.stringWidth("You won!");
        x = (panel.getWidth() / 2) - (stringWidth / 2);
        
        g2d.drawString("You won!", x, panel.getHeight() / 2);
    }
    
    /**
     * Classe de execução do jogo
     */
    private class GameRunnable implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(1000 / 60);
                    panel.repaint();
                    if (panel.getMousePosition(false) != null) {
                        context.setMousePosition(panel.getMousePosition(false));
                    }
                    if (context.getStatus().equals(GameStatus.PLAYING)) {
                        world.update(context);
                    }

                }
            } catch (InterruptedException ex) {}
        }
        
    }
}
