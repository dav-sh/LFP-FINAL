package analizador;


/**
 * Clase encargada de los Lexemas
 */
public class Lexema {
    private String line;
    private int estado;
    private Token token;
    private int[]pos;

    /**
     * Constructor de la clase lexema
     * @param line String con el lexema
     * @param estado estado de finalizacion
     * @param token tipo de token guardado
     * @param pos posicion en el documento
     */
    public Lexema(String line, int estado, Token token,int[] pos) {
        this.line = line;
        this.estado = estado;
        this.token = token;
        this.pos = pos;
    }

    /**
     * regresea un string
     * @return String
     */
    public String getLine() {
        return line;
    }

    /**
     * define el string
     * @param line string de la linea
     */
    public void setLine(String line) {
        this.line = line;
    }

    /**
     * regresa el estado final
     * @return regresa el estado final int
     */
    public int getEstado() {
        return estado;
    }

    /**
     * define el estado final int
     * @param estado final int de la estado
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * regresa el token
     * @return regresa Token
     */
    public Token getToken() {
        return token;
    }


    /**
     * define token
     * @param token tipo de token
     */
    public void setToken(Token token) {
        this.token = token;
    }

    /**
     * regresa la posicion
     * @return regresa posicion int 
     */
    public int[] getPos() {
        return pos;
    }

    /**
     * define la posicion
     * @param pos posicion int
     */
    public void setPos(int[] pos) {
        this.pos = pos;
    }
}
