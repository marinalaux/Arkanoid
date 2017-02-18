package arkanoid;

/**
 * Vetor
 * 
 * @author Marina
 */
public class Vector {
    
    /** Linha */
    private double x;
    /** Coluna */
    private double y;

    /**
     * Construtor do vetor
     * 
     * @param x
     * @param y 
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Retorna a linha
     * 
     * @return double
     */
    public double getX() {
        return x;
    }

    /**
     * Retorna a coluna
     * 
     * @return double
     */
    public double getY() {
        return y;
    }

    /**
     * Define a linha
     * 
     * @param x 
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Define a coluna
     * 
     * @param y 
     */
    public void setY(double y) {
        this.y = y;
    }
    
}
