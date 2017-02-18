package arkanoid;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Arkanoid
 * 
 * @author Marina
 */
public class Arkanoid extends JFrame implements GameConstants {

    /** Painel do jogo */
    private GamePanel panel;
    /** Gerenciador do jogo */
    private Game game;
    
    public static void main(String[] args) {
        Arkanoid arkanoid = new Arkanoid();
        arkanoid.setVisible(true);
        arkanoid.game.start();
    }

    /**
     * Construtor do jogo
     */
    public Arkanoid() {
        super();
        initGui();
        game = new Game(panel);
    }

    /**
     * Inicializa a interface do jogo
     */
    private void initGui() {
        setTitle("Arkanoid");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buildGamePanel());
        pack();
    }

    /**
     * Cria painel para o jogo
     * 
     * @return JComponent
     */
    private JComponent buildGamePanel() {
        panel = new GamePanel();
        panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        return panel;
    }
    
}
