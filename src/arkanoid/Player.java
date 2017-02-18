package arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Componente do jogador
 * 
 * @author Marina
 */
public class Player implements Entity {

    /** Posição vertical do componente da barra do jogador */
    private int x;
    /** Posição horizontal do componente da barra do jogador */
    private static final int y = 600;
    /** Altura e largura da barra */
    private static final Dimension tamanho = new Dimension(150, 20);
    
    /**
     * Construtor do jogador
     * 
     * @param x 
     */
    public Player(int x) {
        this.x = x;
    }

    /**
     * Retorna a posição do jogador
     * 
     * @return Posição
     */
    public Point getPosition() {
        return new Point(x, y);
    }

    /**
     * Retorna os limites físicos do jogador
     * 
     * @return Limites do jogador
     */
    public Rectangle getBounds() {
        return new Rectangle(getPosition(), tamanho);
    }
    
    /**
     * Define a posição do jogador
     * 
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retorna o tamanho do jogador
     * 
     * @return Tamanho
     */
    public static Dimension getTamanho() {
        return tamanho;
    }
    
    @Override
    public void update(UpdateContext context) {
        
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.GRAY);
        g2d.fillRect(x, y, tamanho.width, tamanho.height);
    }
    
}
