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

    public String getLine() {
        return line;
    }
    public void setLine(String line) {
        this.line = line;
    }
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }
}
