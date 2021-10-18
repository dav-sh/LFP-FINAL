package reportes;

import java.util.ArrayList;

import analizador.Token;

/** 
 * Clase encargada de llevar el control de los reportes, de cada token y lexema que se ingresa
 */
public class Reporte {
    /** 
     * Inicializa los contadores
     */
                             //I E P A D G !
    private int[] contadores= {0,0,0,0,0,0,0};
    Token []tokens = Token.values();

    //PALABRAS VALIDAS
    private ArrayList<String> palabrasAgregadas = new ArrayList<>();
    private ArrayList<String> tokensAgregados = new ArrayList<>();
    private ArrayList<Integer> columnasRegistradas = new ArrayList<>();
    private ArrayList<Integer> filasRegistradas = new ArrayList<>();

    //PALABRAS INVALIDAS
    private ArrayList<String> palabrasError = new ArrayList<>();
    private ArrayList<Integer> columnasError = new ArrayList<>();
    private ArrayList<Integer> filasError = new ArrayList<>();

    public Reporte() {
       //constructor 
    //    contadores[0]=0; //IDENTIFICADOR
    //    contadores[1]=0; //ENTERO
    //    contadores[2]=0; //PUNTUACION
    //    contadores[3]=0; //ARITMETICO
    //    contadores[4]=0; //DECIMAL
    //    contadores[5]=0; //AGRUPACION
    //    contadores[6]=0; //ERROR
       //contadores 
    }



    /**
     * Este metodo es el encargado de manejar los contaderes de estado evaluando los parametros recibidos  
     * @param estadoToken Ultimo estado del token
     * @param palabra String de la palabra analizada
     * @param columna columna en la cual se encontra la palabra
     * @param fila fila en la cual se encontra la palabra
     */
    public void contadorEstados(int estadoToken,String palabra, int columna, int fila) {
        for(Token tmp : tokens) {
            if(tmp.getNumeroEstado()==estadoToken) {
                contadorEstados(tmp, palabra, columna, fila); //sumamos al contador
            }
        }
    }



    /**
     * Este metodo se encarga de aumentar en 1 el contador de estados si se cumple la condicion 
     * @param tmp recibe el token 
     * @param palabra  String de la palabra analizada
     * @param columna columna en la cual se encontra la palabra
     * @param fila  fila en la cual se encontra la palabra
     */
    private void contadorEstados(Token tmp, String palabra, int columna, int fila) {
        
        if(tmp==Token.IDENTIFICADOR || tmp==Token.IDENTIFICADOR2){
            contadores[0]++;
            registrar(palabra, columna, fila,Token.IDENTIFICADOR.getNombreEstado());
        }
        else if(tmp==Token.ENTERO){
            contadores[1]++;
            registrar(palabra, columna, fila,Token.ENTERO.getNombreEstado());
        }
        else if(tmp==Token.PUNTUACION){
            contadores[2]++;
            registrar(palabra, columna, fila,Token.PUNTUACION.getNombreEstado());
        }
        else if(tmp==Token.ARITMETICO){
            contadores[3]++;
                registrar(palabra, columna, fila,Token.ARITMETICO.getNombreEstado());
        }
        else if(tmp==Token.DECIMAL){
            contadores[4]++;
                registrar(palabra, columna, fila,Token.DECIMAL.getNombreEstado());
        }
        else if(tmp==Token.AGRUPACION){
            contadores[5]++;
                registrar(palabra, columna, fila,Token.AGRUPACION.getNombreEstado());
        }
        else if(tmp==Token.ERROR || tmp==Token.ERROR2){
            contadores[6]++;
            registrarError(palabra, columna, fila);
        }

    }




     /**
      * Este metodo se encarga de devolver el contador de estados recibiendo por parametro un token (enum) 
      * @param token token recibido
      * @return retorna el valor(int) del token recibido
      */
    public int getcontadorEstado(Token token) {
        int contador=0;
        switch (token) {
            case IDENTIFICADOR:
                contador = contadores[0];
                break;


            case ENTERO:
                contador = contadores[1];

                break;
            
            
            case PUNTUACION:
                contador = contadores[2];

                break;

            case ARITMETICO:
                contador = contadores[3];

                break;

            case DECIMAL:
                contador = contadores[4];

                break;

            case AGRUPACION:
                contador = contadores[5];

                break;
            
            case ERROR:
                contador = contadores[6];
                break;
            default:

                break;
        }
        return contador;
    }

    /**
     * Este metodo se encarga de devolver el array (int) de los contadores 
    */
    public int[] getContadores(){
        return contadores;
    }

    /**
     * Este metodo se encarga de devolver el array (String) de palabras con guardadas o
     * almacenadas que cumplen con los tokens VALIDOS
     * */
    public String[] getPalabrasGuardadas(){
        String[] tmp = new String[palabrasAgregadas.size()];
        for(int i = 0; i < tmp.length; i++){
            tmp[i] = palabrasAgregadas.get(i);
        }
        return tmp;
    }

    /**
     * Este metodo se encarga de devolver el array (String) de la posicion de las COLUMNAS de las palabras
     * almacenadas que cumplen con los tokens VALIDOS
     * */
    public String[] getPosicionColumna(){
        String[] pos = new String[columnasRegistradas.size()];
        for(int i = 0; i < pos.length; i++){
            pos[i] = columnasRegistradas.get(i).toString();
        }
        return pos;
    }

    /**
     * Este metodo se encarga de devolver el array (String) de la posicion de las FILAS de las palabras
     * almacenadas que cumplen con los tokens VALIDOS
     * */
    public String[] getPosicionFila(){
        String[] pos = new String[columnasRegistradas.size()];
        for(int i = 0; i < pos.length; i++){
            pos[i] = filasRegistradas.get(i).toString();
        }
        return pos;
    }


    /**
     * Este metodo se encargara de resetear los valores de los contadores al ser llamado por el boton evaluar
    */
    public void resetContadores(){
        for(int i = 0; i < contadores.length; i++){
            contadores[i] = 0;
        }
    }



    /**
     * Metodo encargado de registrar las palabras VALIDAS, filas y columnas de los reportes
     * @param palabra  String de la palabra analizada
     * @param columna columna en la cual se encontro la palabra
     * @param fila fila en la cual se encontro la palabra
     * @param token token recibido
     */
    private void registrar(String palabra, int columna, int fila, String token){
        palabrasAgregadas.add(palabra); //y agregamos la palabra a la lista de palabras
        columnasRegistradas.add(columna);
        filasRegistradas.add(fila);
        tokensAgregados.add(token);
    }



    /**
     * Metodo encargado de registrar las palabras ERRONEAS, filas y columnas de los reportes
     * @param palabra  String de la palabra analizada
     * @param columna columna en la cual se encontra la palabra
     * @param fila fila en la cual se encontra la palabra
     */
    private void registrarError(String palabra, int columna, int fila){
        palabrasError.add(palabra); //y agregamos la palabra a la lista de palabras
        columnasError.add(columna);
        filasError.add(fila);
    }


    /**
     * Este metodo se encarga de devolver el array (String) de palabras con guardadas o
     * almacenadas que cumplen con los tokens INVALIDOS
     * */
    public String[] getPalabrasError(){
        String[] tmp = new String[palabrasError.size()];
        for(int i = 0; i < tmp.length; i++){
            tmp[i] = palabrasError.get(i);
        }
        return tmp;
    }

    /**
     * Este metodo se encarga de devolver el array (String) de la posicion de las COLUMNAS de las palabras
     * almacenadas que cumplen con los tokens INVALIDOS
     * */
    public String[] getPosicionColumnaError(){
        String[] pos = new String[columnasError.size()];
        for(int i = 0; i < pos.length; i++){
            pos[i] = columnasError.get(i).toString();
        }
        return pos;
    }



    /**
     * Este metodo se encarga de devolver el array (String) de la posicion de las FILAS de las palabras
     * almacenadas que cumplen con los tokens INVALIDOS
     * */
    public String[] getPosicionFilaError(){
        String[] pos = new String[columnasError.size()];
        for(int i = 0; i < pos.length; i++){
            pos[i] = filasError.get(i).toString();
        }
        return pos;
    }



    /**
     * Este metodo se encarga de devolver el array (String) de palabras con guardadas o
     * almacenadas que cumplen con los tokens INVALIDOS
     * */
    public String[] getTokensAgregados(){
        String[] tmp = new String[palabrasAgregadas.size()];
        for(int i = 0; i < tmp.length; i++){
            tmp[i] = tokensAgregados.get(i);
        }
        return tmp;
    }


     /**
     * Este metodo se encargara de resetar los valores de los arrays
    */
    public void resetArrays(){
        columnasRegistradas.clear();
        palabrasAgregadas.clear();
        filasRegistradas.clear();
        tokensAgregados.clear();

        palabrasError.clear();
        columnasError.clear();
        filasError.clear();

    }

}
