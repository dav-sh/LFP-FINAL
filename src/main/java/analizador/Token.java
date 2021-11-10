package analizador;

/**Clase de tipo enum que nos indica los tokens validos y no validos utilizados en la clase Automata */
public enum Token {

    /**
     * Tokens validos 
     */
    IDENTIFICADOR(7, "IDENTIFICADOR"), 
    ENTERO(8, "ENTERO"),
    COMENTARIO(9,"COMENTARIO"),
    //COMENTARIO(9,"COMENTARIO | LITERAL | 0 | P_RESERV"),
    LITERAL(10,"LITERAL"),
    



    //ESTO SERVIRA PARA EL SIGNO = + U OTRO SIMBOLO USADO EN LAS ESTRUCTURAS SINTACTICAS
    /**
     * Token simbolo
     */
    SIMBOLO(11, "SIMBOLO"),



    //RESERVADAS
    /**
     * Reservadas
     */
    ESCRIBIR(12,"ESCRIBIR"),
    FIN(13, "FIN"),
    REPETIR(14, "REPETIR"),
    INICIAR(15, "INICIAR"),
    SI(16,"SI"),
    VERDADERO(17, "VERDADERO"),
    FALSO(18,"FALSO"),
    ENTONCES(19, "ENTONCES"),

    //SEPARADOR
    /**
     * Separador
     */
    SEPARADOR(20,"SEPARADOR"),


    //OTROS

    /**
     * Token invalido 
     */
    ERROR(-1, "ERROR"), 

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


