package arkanoid;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Painel do jogo
 * 
 * @author Marina
 */
public class GamePanel extends JPanel {
    
    /** Desenhador do gráfico */
    private Painter painter;

    public Painter getPainter() {
        return painter;
    }

    public void setPainter(Painter painter) {
        this.painter = painter;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        painter.paint(grphcs);
    }
    
}
