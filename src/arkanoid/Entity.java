package arkanoid;

import java.awt.Graphics;

/**
 * Interface para as entidades do jogo
 * 
 * @author Marina
 */
interface Entity {
   
    /**
     * Atualiza o componente do jogo
     */
    void update(UpdateContext context);
    
    /**
     * Renderiza o componente do jogo
     */
    void render(Graphics g);
    
}
