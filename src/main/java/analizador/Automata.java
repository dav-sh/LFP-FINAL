package analizador;

import javax.swing.*;
import javax.swing.JTextArea;
import reportes.ReporteV2;

import java.awt.*;



/** Clase encargada de analizar las palabras del texto en pantalla. 
 * 
 */
public class Automata {
    StringBuilder movimiento;
    String texto;
    int posicion;
    int fila=1;
    int columna = 1;
    int estadoActual = 0;
    int estadoInicio=100;
    Token []tokens = Token.values();
    ReporteV2 reporte = null;
    //int estadosAceptacion[] = {1,2,3,4,5,7,8}; //B,C,D,E,F,I,J
    //git stash para eliminar cambios no guardados con commit

    /**
     * Constructor de la clase automata
     * @param textArea recibe text area a analizar
     * @param reportV2    recibe el objeto reporte al cual se le agregaran los nuevos registros
     */
    public Automata(JTextArea textArea, ReporteV2 reportV2) { 
        movimiento = new StringBuilder();
        this.reporte = reportV2;
        texto=textArea.getText();
        leeTexto();
        muestraMovimiento(movimiento.toString());
    }

  
	/** 
     * A=0  B=1  C=2  E=3   I=4   s1=5  s2=6 s3=7 s4=8 s5=9
     * ERROR = -1
     */

    int [][] estados = new int[10][11];
    {
        //  /                       "                         S                      G                     L                      -                    D                       0                        P               espacio             salto de linea
        //A
        estados[0][0]= 1;       estados[0][1]= 2;     estados[0][2]=-1 ;       estados[0][3]= 7   ;   estados[0][4]= 7;    estados[0][5]= 3;       estados[0][6]= 8;    estados[0][7]=9  ;    estados[0][8]= 9  ;    estados[0][9]= -1;   estados[0][10]= -1;       
        //B
        estados[1][0]= 4  ;     estados[1][1]= -1 ;   estados[1][2]= -1 ;      estados[1][3]= -1 ;    estados[1][4]= -1;   estados[1][5]=-1  ;     estados[1][6]= -1 ;  estados[1][7]= -1 ;   estados[1][8]= -1 ;    estados[1][9]= -1;   estados[1][10]= -1;     
        //C 
        estados[2][0]= -1 ;     estados[2][1]=-1  ;   estados[2][2]= 5 ;       estados[2][3]=-1 ;     estados[2][4]= 5;    estados[2][5]=-1  ;     estados[2][6]= 5 ;   estados[2][7]=  5  ;  estados[2][8]= -1 ;    estados[2][9]= 5 ;   estados[2][10]= -1;     
        //E 
        estados[3][0]= -1 ;     estados[3][1]= -1 ;   estados[3][2]= -1 ;      estados[3][3]= -1 ;    estados[3][4]= -1 ;  estados[3][5]= -1 ;     estados[3][6]= 8;    estados[3][7]= -1 ;   estados[3][8]= -1 ;    estados[3][9]= -1;   estados[3][10]= -1 ;       
        //I
        estados[4][0]= -1 ;     estados[4][1]= -1 ;   estados[4][2]= 6  ;      estados[4][3]=-1 ;     estados[4][4]= 6 ;   estados[4][5]= -1 ;     estados[4][6]=  6;   estados[4][7]= 6  ;   estados[4][8]= -1 ;    estados[4][9]= 6 ;   estados[4][10]= -1 ;     
        //S1 
        estados[5][0]= -1 ;     estados[5][1]= 9 ;    estados[5][2]= 5 ;       estados[5][3]=-1 ;     estados[5][4]= 5;    estados[5][5]= -1;      estados[5][6]= 5 ;   estados[5][7]= 5  ;   estados[5][8]=-1 ;     estados[5][9]=5  ;   estados[5][10]= -1 ;     
        //S2
        estados[6][0]= -1 ;     estados[6][1]=-1  ;   estados[6][2]= 6 ;       estados[6][3]=-1 ;     estados[6][4]= 6 ;   estados[6][5]= -1 ;     estados[6][6]= 6 ;   estados[6][7]= 6  ;   estados[6][8]= -1 ;    estados[6][9]=6  ;   estados[6][10]= 9 ;     
        //S3
        estados[7][0]= -1 ;     estados[7][1]= -1 ;   estados[7][2]= -1 ;      estados[7][3]= 7  ;    estados[7][4]= 7 ;   estados[7][5]= 7 ;      estados[7][6]= 7   ; estados[7][7]=  7 ;   estados[7][8]= -1 ;    estados[7][9]= -1 ;  estados[7][10]= -1 ;    
        //S4
        estados[8][0]= -1 ;     estados[8][1]= -1 ;   estados[8][2]= -1 ;      estados[8][3]= -1 ;    estados[8][4]= -1 ;  estados[8][5]= -1 ;     estados[8][6]= 8  ;  estados[8][7]= 8 ;    estados[8][8]= -1;     estados[8][9]= -1 ;  estados[8][10]= -1 ;    
        //S5  
        estados[9][0]= -1 ;     estados[9][1]= -1 ;   estados[9][2]= -1 ;      estados[9][3]= -1 ;    estados[9][4]= -1 ;  estados[9][5]= -1 ;     estados[9][6]= -1 ;  estados[9][7]= -1 ;   estados[9][8]= -1 ;    estados[9][9]= -1 ;  estados[9][10]= -1 ;     
    }






    /**
     * Metodo que se encarga de devolver el siguiente movimiento en la matriz 
     * @param estadoActual    recibe el estado actual en el que se encuentra la matriz
     * @param tipoCaracter    recibe el tipo caracter a evaluar dentro de la matriz
     * @return
     */
    public int getNextEstado(int estadoActual, int tipoCaracter){
        int result = -1; //si fuera error        

        if(tipoCaracter>=0 && tipoCaracter<=10 && estadoActual!=-1){ //solo hay 11 tipos de caracter por lo mismo se coloca el 10 porque inicia en 0
            result= estados[estadoActual][tipoCaracter];
        }

        return result;
    }


    /**
     * Metodo encargado de devolver el simbolo o texto del estado final
     * @param estadoActual recibe el estado actual del caracter evaluado
     * @return  regresa el String completo de la palabra a evaluar
     */

    
    public String getEstadoActual(int estadoActual) {
        String result = "";
        for(Token tmp : tokens) {
            if(tmp.getNumeroEstado()==estadoActual) {
                result = tmp.getNombreEstado();
                break;
            }
        }
        return result;
    }

    







    /**
     *  Metodo encargado de revisar si el caracter esta dentro de nuestro alfabeto
     * @param caracter recibe el caracter que sera evaluado para ver si pertenece o no al alfabeto
     * @return regresa el int que indica el tipo de caracter al que pertecene
     */
    public int getIntTipoCaracter(char caracter) {
        //Definimos el alfabeto restante
        char [] symbolsV1={':',',',';','/','<','>','=','(',')','[',']','{','}','-','_','.','!','?','*','+'};
        char [] symbolsV2={':',',',';','/','<','>','=','(',')','[',']','{','}','-','_','"','.','!','?','*','+'};
        char [] symbolsSint = {'+','(',')','='};

        //Si no lo encuentra devuelve un error identificado como -1
        int result = -1;

        System.out.println("Evaluando el caracter: " + caracter);

        //Dependiendo el orden de las columnas, es el valor que debe de devolver 
        //        Σ = /, “, S,	G, L, -, D,	0,	P,	espacio,	Salto de linea  
        if(caracter=='/' && (estadoInicio==1 || estadoInicio==100) && estadoActual!=6){
            result =0;
        }
        
        if(caracter=='"' || caracter== '“' || caracter== '”' &&(estadoInicio==2 || estadoInicio==100) ){
            
            result =1;
            
        }

        if(estadoActual ==5 || estadoActual==6  || estadoActual==2 || estadoActual==4){
            if(estadoActual==5){
                for(char c:symbolsV1){
                    if(caracter==c){
                        result =2;
                    }
                }

            }else{
                for(char c:symbolsV2){
                    if(caracter==c){
                        result =2;
                    }
                }
            }

        }

        if(caracter=='_' && (estadoInicio ==7 || estadoInicio==100)){
            result =3;
        }

        if(Character.isLetter(caracter)){
            result =4;
            System.out.println("El caracter: " + caracter + " devuelve " + result);
        }

        if(caracter=='-' && (estadoInicio==100 || estadoInicio==7 )){
            result =5;
        }

        if(Character.isDigit(caracter) && caracter!='0'){
            result =6;
        }

        if(caracter=='0'){
            result =7;
        }

        //Aqui iria palabra ---> 8  pero lo vamos a utilizar para los caracteres del sintactico xd xdxdxd
        if(estadoInicio==9 || estadoInicio==100){
            for(char c : symbolsSint){
                if(c==caracter) {
                    result =8;
                }
            }
        }

        if(Character.isSpaceChar(caracter)){
            result =9;
        }

        if(Character.toString(caracter).equals("\n")){
            result =10;
        }

        
        return result;
    }






    





    /*
    *Metodo encargado de continuar leer el texto mientras recorre cada char de la palabra a analizar
    */
    public void leeTexto(){

    
        //reporte.resetContadores(); //Aqui se resetean los valores de los contadores, al iniciar un nuevo analisis
        //reporte.resetArrays(); //Aqui se resetean los arrays con las palabras y posicones de error
        reporte.cleanReport();
        while(posicion<texto.length()){
            leePalabra();
        }

        
    }





    /**
     * Metodo encargado de analizar cada char de la palabra
    */
    public void leePalabra(){
        StringBuilder palabra = new StringBuilder();
        estadoActual =0;
        int columnaValida=0;
        int filaValida=1;
        boolean continuar = true;
        while(posicion<texto.length() && continuar){
            char c = texto.charAt(posicion);
            System.out.println("\n Caracter "+ c+ " pos "+posicion);
           
            int numeroCaracter= getIntTipoCaracter(c);
            //el segundo valor es el caracter que mandamos -->char que nos devuelve un int correspondiente a un tipo de token en el metodo getIntTipoCaracter
            System.out.println("Estado actual "+estadoActual+ " numero que devuelve el caracter "+ numeroCaracter+" ahora pedimos valor a matriz ");
            int estadoTemporal = getNextEstado(estadoActual, numeroCaracter); 

            if(estadoActual ==0){
                //Guardamos el primer estado que calculamos, esto nos servira para evaluar el tipo de caracter y el numero que devuelve
                estadoInicio = estadoTemporal;
                System.out.println("Estado de inicio = "+estadoInicio);
            }



            if(estadoTemporal==-1 && Character.isSpaceChar(c)){
                //Evaluamos si el siguiente estado es de error
                continuar=false;
                estadoInicio=100;
                System.out.println("El siguiente estado es de error, por lo tanto reiniciamos automata para un nuevo analisis");
                


            }else{
                //Si el siguiente estado no es erroneo, realizamos las acciones correspondientes
                palabra.append(c);
                System.out.println("estado actual "+estadoActual+" Caracter: "+c + " posicion "+" estado temporal(siguiente) "+ estadoTemporal+ " posicion: "+ posicion+ " valor matriz actual "+ getNextEstado(estadoActual, numeroCaracter));
                movimiento.append("estado actual "+estadoActual+" Caracter: "+c + " posicion "+" estado temporal(siguiente) "+ estadoTemporal+ " posicion: "+ posicion+ " valor matriz actual "+ getNextEstado(estadoActual, numeroCaracter)+"\n");
                estadoActual = estadoTemporal;
                columnaValida = columna;
                filaValida = fila;
            }
            
            if(Character.toString(c).equals("\n")){
                continuar=false;
                estadoInicio=100;
                System.out.println("Reiniciamos el automata ... debido al salto de linea");
                fila++; //Si dectecta un salto de linea aumenta en 1 el numero de filas
                columna=0; //inicializa en 0 ya que el contador la aumenta a 1
            }
        
            columna++;
            posicion++;
        }

       
        
        //creamos el reporte el cual tiene por parametros el estado actual del token(si es valido o no), el lexema completo(palabra evaluada), el numero de columna en donde se encontro, el numeor de fila donde se encontro 
        System.out.println("Estado Actual "+estadoActual + " = "+ getEstadoActual(estadoActual)+" con palabra "+ palabra.toString()+" ->*columna: "+columnaValida + "  fila "+ filaValida+"\n");
        movimiento.append("Estado final:" +getEstadoActual(estadoActual)+" palabra: "+ palabra.toString()+" Pos-> C "+columnaValida + " ,F "+ filaValida+"\n\n");
        //reporte.contadorEstados(estadoActual,palabra.toString(),columnaValida,filaValida);
        System.out.println("Lexema enviado a reporte"+palabra.toString());
        reporte.evaluarLexema(estadoActual,palabra.toString(),columnaValida,filaValida);
    }














    /**
     * Metodo encargado de mostrar en una nueva ventana el movimiento dentro de la matriz del automata
     * @param mov String con el valor o numero del movimiento del automata
     */
    private void muestraMovimiento(String mov) {
        JFrame frameMov = new JFrame("Mov. Automata");
        JPanel panelMov = new JPanel();
        panelMov.setLayout(new GridLayout());
        JTextArea textareaMov = new JTextArea(mov);
        textareaMov.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textareaMov);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelMov.add(scrollPane);
        frameMov.add(panelMov);
        frameMov.setSize(400, 400);
        frameMov.setVisible(true);


	}

    



    


}
