package analizador;

import javax.swing.*;
import javax.swing.JTextArea;
import reportes.Reporte;
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
    Token []tokens = Token.values();
    Reporte reporte = null;
    //int estadosAceptacion[] = {1,2,3,4,5,7,8}; //B,C,D,E,F,I,J
    //git stash para eliminar cambios no guardados con commit

    /**
     * Constructor de la clase automata
     * @param textArea recibe text area a analizar
     * @param report    recibe el objeto reporte al cual se le agregaran los nuevos registros
     */
    public Automata(JTextArea textArea, Reporte report) { 
        movimiento = new StringBuilder();
        this.reporte = report;
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
        estados[3][0]= -1 ;     estados[3][1]= -1 ;   estados[3][2]= -1 ;      estados[3][3]= -1 ;    estados[3][4]= -1 ;  estados[3][5]= -1 ;     estados[3][6]= -1;   estados[3][7]= -1 ;   estados[3][8]= -1 ;    estados[3][9]= -1;   estados[3][10]= -1 ;       
        //I
        estados[4][0]= -1 ;     estados[4][1]= 6 ;    estados[4][2]= 6  ;      estados[4][3]=-1 ;     estados[4][4]= 6 ;   estados[4][5]= -1 ;     estados[4][6]=  6;   estados[4][7]= 6  ;   estados[4][8]= -1 ;    estados[4][9]= 6 ;   estados[4][10]= -1 ;     
        //S1 
        estados[5][0]= -1 ;     estados[5][1]= -1 ;   estados[5][2]= 5 ;       estados[5][3]=-1 ;     estados[5][4]= 5;    estados[5][5]= -1;      estados[5][6]= 5 ;   estados[5][7]= 5  ;   estados[5][8]=-1 ;     estados[5][9]=5  ;   estados[5][10]= -1 ;     
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
        String result = "Error "+estadoActual;
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
        char [] symbols={':',',',';','/','<','>','=','(',')','[',']','{','}','-','_'};
        //char [] dig={'1','2','3','4','5','6','7','8','9'};

        

        //Si no lo encuentra devuelve un error identificado como -1
        int result = -1;

        //Dependiendo el orden de las columnas, es el valor que debe de devolver en este caso son de 0-13 
        //        Σ = /, “, S,	G, L, -, D,	0,	P,	espacio,	Salto de linea  


        if(caracter=='/'){
            result =0;
        }
        
        else if(caracter=='"'){
            result =1;
        }

        for(char c:symbols){
            if(caracter==c){
                result =2;
            }
        }

        if(caracter=='_'){
            result =3;
        }

        else if(Character.isLetter(caracter)){
            result =4;
        }

        else if(caracter=='-'){
            result =5;
        }

        else if(Character.isDigit(caracter) && caracter!='0'){
            result =6;
        }

        else if(caracter=='0'){
            result =7;
        }

        //Aqui iria palabra ---> 8

        else if(Character.isSpaceChar(caracter)){
            result =9;
        }

        else if(Character.toString(caracter).equals("\n")){
            result =10;
        }

        
        return result;
    }






    





    /*
    *Metodo encargado de continuar leer el texto mientras recorre cada char de la palabra a analizar
    */
    public void leeTexto(){
        reporte.resetContadores(); //Aqui se resetean los valores de los contadores, al iniciar un nuevo analisis
        reporte.resetArrays(); //Aqui se resetean los arrays con las palabras y posicones de error
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
            
            //Aqui se evalua si el caracter es un espacio o salto de linea
            if(Character.isSpaceChar(c) ||  Character.toString(c).equals("\n")){  
                continuar = false;

                if(Character.toString(c).equals("\n")){
                    System.out.println("Posicion:"+ columna + " = salto linea ");
                    movimiento.append("Salto de linea\n");
                    fila++; //Si dectecta un salto de linea aumenta en 1 el numero de filas
                    columna=0; //inicializa en 0 ya que el contador la aumenta a 1
                    
                }else if(Character.isSpaceChar(c) ){ 
                    System.out.println("Posicion:"+ columna + " = Espacio " );
                    movimiento.append("Espacio\n");

                }


            }
            else{
                //si el char(c) no es un espacio o columna, se agrega en la palabra 
                palabra.append(c);

                //el segundo valor es el caracter que mandamos (char) que nos devuelve un int correspondiente a un tipo de token en el metodo getIntTipoCaracter
                int estadoTemporal = getNextEstado(estadoActual, getIntTipoCaracter(c)); 
                
                //Si no detecta salto de linea o espacio reconoce la cadena como valida sin importar que este en error y guarda su posicion
                System.out.println("estado actual "+estadoActual+" Caracter: "+c +" estado temporal(siguiente) "+ estadoTemporal+ " posicion: "+ posicion);
                movimiento.append("estado actual "+estadoActual+" Caracter: "+c +" estado temporal(siguiente) "+ estadoTemporal+ " posicion: "+ posicion+"\n");
                estadoActual = estadoTemporal;
                columnaValida = columna;
                filaValida = fila;
            }
            columna++;
            posicion++;
        }
        System.out.println("Me movi del estado "+estadoActual + " al estado "+ getEstadoActual(estadoActual)+" con un(a) "+ palabra.toString()+" ->*columna: "+columnaValida + "  fila "+ filaValida);
        //creamos el reporte el cual tiene por parametros el estado actual del token(si es valido o no), el lexema completo(palabra evaluada), el numero de columna en donde se encontro, el numeor de fila donde se encontro 
        movimiento.append("Me movi del estado "+estadoActual + " al estado "+ getEstadoActual(estadoActual)+" con un(a) "+ palabra.toString()+" ->*columna: "+columnaValida + "  fila "+ filaValida+"\n");
        reporte.contadorEstados(estadoActual,palabra.toString(),columnaValida,filaValida);

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
