package arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Componente bloco
 * 
 * @author Marina
 */
public class Block implements Entity {
    
    /** Posição do bloco */
    private Point position;
    /** Cor do bloco */
    private Color color;
    /** Largura e altura do bloco */
    private static final Dimension tamanho = new Dimension(50, 25);
    /** Bloco quebrado */
    private boolean broken;

    /**
     * Construtor do bloco
     * 
     * @param p
     */
    public Block(Point p) {
        position = p;
        broken = false;
        color = Color.getHSBColor((float) Math.random(), 1.0f, 1.0f);
    }

    /**
     * Retorna a posição do bloco
     * 
     * @return Posição
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Retorna o tamanho do bloco
     * 
     * @return Tamanho do bloco
     */
    public static Dimension getTamanho() {
        return tamanho;
    }
    
    /**
     * Retorna os limites físicos do bloco
     * 
     * @return Limites do bloco
     */
    public Rectangle getBounds() {
        return new Rectangle(position, tamanho);
    }
    
    /**
     * Quebra o bloco
     * 
     * @param broken 
     */
    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    /**
     * Retorna se o bloco está quebrado
     * 
     * @return Quebrado
     */
    public boolean isBroken() {
        return broken;
    }
    
    @Override
    public void update(UpdateContext context) {
        
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(position.x, position.y, tamanho.width, tamanho.height);
    }

}
