package arkanoid;

import java.awt.Point;

/**
 * Atualização do jogo
 * 
 * @author Marina
 */
public class UpdateContext {
    
    /** Jogador */
    private Player player;
    /** Blocos */
    private Block[][] blocks;
    /** Posição do mouse */
    private Point mousePosition;
    /** Estado do jogo */
    private GameStatus status;

    /**
     * Construtor
     */
    public UpdateContext() {
        this.mousePosition = new Point(0, 0);
    }

    /**
     * Retorna o status do jogo
     * 
     * @return Status do jogo
     */
    public GameStatus getStatus() {
        return status;
    }

    /**
     * Define o status do jogo
     * 
     * @param status 
     */
    public void setStatus(GameStatus status) {
        this.status = status;
    }

    /**
     * Retorna o jogador
     * 
     * @return Jogador
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Define o jogador
     * 
     * @param player 
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Retorna os blocos
     * 
     * @return Blocos
     */
    public Block[][] getBlocks() {
        return blocks;
    }

    /**
     * Define os blocos
     * 
     * @param blocks 
     */
    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    /**
     * Retorna posição do mouse
     * 
     * @return Posição do mouse
     */
    public Point getMousePosition() {
        return mousePosition;
    }

    /**
     * Define a posição do mouse
     * 
     * @param mousePosition 
     */
    public void setMousePosition(Point mousePosition) {
        this.mousePosition = mousePosition;
    }
    
}
