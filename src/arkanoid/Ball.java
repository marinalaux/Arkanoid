package arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Componente bola
 * 
 * @author Marina
 */
public class Ball implements Entity, GameConstants {
    
    /** Posição da bola */
    private Point position;
    /** Velocidade da bola */
    private Vector velocity;
    /** Largura e altura da bola */
    private static final Dimension size = new Dimension(20, 20);

    /**
     * Construtor da bola
     * 
     * @param p
     */
    public Ball(Point p) {
        position = p;
        velocity = new Vector(5, 5);
    }
    
    @Override
    public void update(UpdateContext context) {
        if (collideWallX()) {
            velocity.setX(-velocity.getX());
        }
        if (collideWallY()) {
            velocity.setY(-velocity.getY());
        }
        if (collidePlayer(context)) {
            velocity.setY(-Math.abs(velocity.getY()));
        }
        for (int x = 0; x < context.getBlocks().length; x++) {
            for (int y = 0; y < context.getBlocks()[x].length; y++) {
                if (context.getBlocks()[x][y] != null) {
                    processBlockCollision(context.getBlocks()[x][y]);
                }
            }
        }

        updatePosition();
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.GRAY);
        g2d.fillOval(position.x, position.y, size.width, size.height);
        g2d.dispose();
    }

    /**
     * Atualiza a posição da bola
     */
    private void updatePosition() {
        position.x += velocity.getX();
        position.y += velocity.getY();
    }
    
    /**
     * Verifica se a bola colidiu no eixo X
     * 
     * @param c
     * @return Colidiu
     */
    private boolean collideWallX() {
        return (position.x + size.width) > WINDOW_WIDTH || position.x <= 0;
    }
    
    /**
     * Verifica se a bola colidiu no eixo Y
     * 
     * @param c
     * @return Colidiu
     */
    private boolean collideWallY() {
        return collideFloor() || position.y <= 0;
    }
    
    /**
     * Verifica se a bola colidiu no chão
     * 
     * @return Colidiu
     */
    public boolean collideFloor() {
        return (position.y + size.height) > WINDOW_HEIGHT;
    }
    
    /**
     * Verifica se a bola colidiu com o jogador
     * 
     * @param c
     * @return Colidiu
     */
    private boolean collidePlayer(UpdateContext c) {
        // TODO: Trocar pelo formato de colisão do bloco
        return (position.y + size.height) >= c.getPlayer().getPosition().y &&
               (position.y + size.height) <= (c.getPlayer().getPosition().y + Player.getTamanho().height) &&
               (position.x + size.width) >= c.getPlayer().getPosition().x &&
               (position.x + size.width) <= (c.getPlayer().getPosition().x + Player.getTamanho().width);
    }
    
    /**
     * Direciona a bola em caso de colisão com bloco
     * 
     * @param b
     */
    private void processBlockCollision(Block b) {
        if (b.getBounds().contains(getPointN()) || b.getBounds().contains(getPointS())) {
            velocity.setY(-velocity.getY());
            b.setBroken(true);
        } else {
            if (b.getBounds().contains(getPointE()) || b.getBounds().contains(getPointW())) {
                velocity.setX(-velocity.getX());
                b.setBroken(true);
            } else {
                if (b.getBounds().contains(getPointNE())) {
                    collideCorner(new Vector(-1, 1), new Vector(-1, -1), new Vector(1, 1), new Vector(1, -1));
                    b.setBroken(true);
                }
                if (b.getBounds().contains(getPointSE())) {
                    collideCorner(new Vector(-1, -1), new Vector(-1, 1), new Vector(1, -1), new Vector(1, 1));
                    b.setBroken(true);
                }
                if (b.getBounds().contains(getPointNW())) {
                    collideCorner(new Vector(1, 1), new Vector(1, -1), new Vector(-1, 1), new Vector(-1, -1));
                    b.setBroken(true);
                }
                if(b.getBounds().contains(getPointSW())) {
                    collideCorner(new Vector(1, -1), new Vector(1, 1), new Vector(-1, -1), new Vector(-1, 1));
                    b.setBroken(true);
                }
            }
        }
    }
    
    /**
     * Ajusta direção da bola conforme colisão dos cantos
     * 
     * @param southEast
     * @param northEast
     * @param southWest
     * @param northWest 
     */
    private void collideCorner(Vector southEast, Vector northEast, Vector southWest, Vector northWest) {
        if (velocity.getX() >= 0 && velocity.getY() >= 0) {
            velocity.setX(velocity.getX() * southEast.getX());
            velocity.setY(velocity.getY() * southEast.getY());
        }
        if (velocity.getX() >= 0 && velocity.getY() < 0) {
            velocity.setX(velocity.getX() * northEast.getX());
            velocity.setY(velocity.getY() * northEast.getY());
        }
        if (velocity.getX() < 0 && velocity.getY() >= 0) {
            velocity.setX(velocity.getX() * southWest.getX());
            velocity.setY(velocity.getY() * southWest.getY());
        }
        if (velocity.getX() < 0 && velocity.getY() < 0) {
            velocity.setX(velocity.getX() * northWest.getX());
            velocity.setY(velocity.getY() * northWest.getY());
        }
    }
    
    /**
     * Retorna a posição do ponto superior central
     * 
     * @return Posição do ponto
     */
    private Point getPointN() {
        return new Point(getMidPoint().x, position.y);
    }
    
    /**
     * Retorna a posição do ponto inferior central
     * 
     * @return Posição do ponto
     */
    private Point getPointS() {
        return new Point(getMidPoint().x, position.y + size.height);
    }
    
    /**
     * Retorna a posição do ponto direito central
     * 
     * @return Posição do ponto
     */
    private Point getPointE() {
        return new Point(position.x + size.width, getMidPoint().y);
    }
    
    /**
     * Retorna a posição do ponto esquerdo central
     * 
     * @return Posição do ponto
     */
    private Point getPointW() {
        return new Point(position.x, getMidPoint().y);
    }
    
    /**
     * Retorna a posição do ponto direito superior
     * 
     * @return Posição do ponto
     */
    private Point getPointNE() {
        return new Point(getPointE().x, getPointN().y);
    }
    
    /**
     * Retorna a posição do ponto direito inferior
     * 
     * @return Posição do ponto
     */
    private Point getPointSE() {
        return new Point(getPointE().x, getPointS().y);
    }
    
    /**
     * Retorna a posição do ponto esquerdo superior
     * 
     * @return Posição do ponto
     */
    private Point getPointNW() {
        return new Point(getPointW().x, getPointN().y);
    }
    
    /**
     * Retorna a posição do ponto esquerdo inferior
     * 
     * @return Posição do ponto
     */
    private Point getPointSW() {
        return new Point(getPointW().x, getPointS().y);
    }
    
    /**
     * Retorna a posição do ponto central
     * 
     * @return Posição do ponto
     */
    private Point getMidPoint() {
        return new Point(position.x + (size.width / 2), position.y + (size.height / 2));
    }

}
