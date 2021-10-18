package analizador;

/**Clase de tipo enum que nos indica los tokens validos y no validos utilizados en la clase Automata */
public enum Token {

    /**Tokens validos */
    IDENTIFICADOR(1, "IDENTIFICADOR"), //IDENTIFICADOR CORTO EX. A 
    ENTERO(2,"ENTERO"), //ENTERO CORTO EX. 2
    PUNTUACION(3, "PUNTUACION"),
    ARITMETICO(4, "ARITMETICO"),
    IDENTIFICADOR2(5, "IDENTIFICADOR"),
    DECIMAL(7,"DECIMAL"),
    AGRUPACION(8, "AGRUPACION"),

    /**Token invalido */
    ERROR(-1, "ERROR"), //ERROR
    ERROR2(6, "ERROR"), //ERROR
    ;
    
    
    /**
     * Metodo encargado de retornar el numero de estado del token correspondiente
     * @return retorna un int con el valor del token asignado
     */
    public int getNumeroEstado(){
        return this.estado;
    }

    /**
     * Metodo encargado de retornar el String de estado del token correspondiente
     * @return retorna un String con el valor del token asignado
     */
    public String getNombreEstado(){
        return this.nombre;
    }

    private final int estado;
    private final String nombre;
    

     /**
      * Constructor
      * @param estado int del estado del token
      * @param nombre String con el nombre del token
      */
    private Token(int estado, String nombre){
        this.estado = estado;
        this.nombre = nombre;
    }

}


