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
     * A=0  B=1  C=2  D=3   E=4   F=5  H=6 I=7 J=8
     * ERROR = -1
     */

    int [][] estados = new int[9][6];
    {
        //  L                          D                    .                       P                      A                      G
        //A
        estados[0][0]= 1;       estados[0][1]= 2;     estados[0][2]= -1 ;   estados[0][3]= 3  ;     estados[0][4]= 4  ;   estados[0][5]= 8;   
        //B
        estados[1][0]= 5  ;     estados[1][1]= 5  ;   estados[1][2]= -1 ;   estados[1][3]= -1 ;     estados[1][4]= -1 ;   estados[1][5]= -1 ;   
        //C 
        estados[2][0]= -1 ;     estados[2][1]= 2  ;   estados[2][2]= 6  ;   estados[2][3]= -1 ;     estados[2][4]= -1 ;   estados[2][5]= -1 ;   
        //D 
        estados[3][0]= -1 ;     estados[3][1]= -1 ;   estados[3][2]= -1 ;   estados[3][3]= -1 ;     estados[3][4]= -1 ;   estados[3][5]= -1 ;   
        //E
        estados[4][0]= -1 ;     estados[4][1]= -1 ;   estados[4][2]= -1 ;   estados[4][3]= -1 ;     estados[4][4]= -1 ;   estados[4][5]= -1 ;   
        //F 
        estados[5][0]= 5  ;     estados[5][1]= 5  ;   estados[5][2]= -1 ;   estados[5][3]= -1 ;     estados[5][4]= -1 ;   estados[5][5]= -1 ;   
        //H 
        estados[6][0]= -1 ;     estados[6][1]= 7  ;   estados[6][2]= -1 ;   estados[6][3]= -1 ;     estados[6][4]= -1 ;   estados[6][5]= -1 ;   
        //I
        estados[7][0]= -1 ;     estados[7][1]= 7  ;   estados[7][2]= -1 ;   estados[7][3]= -1 ;     estados[7][4]= -1 ;   estados[7][5]= -1 ;   
        //J  
        estados[8][0]= -1 ;     estados[8][1]= -1 ;   estados[8][2]= -1 ;   estados[8][3]= -1 ;     estados[8][4]= -1 ;   estados[8][5]= -1 ;   
    }






    /**
     * Metodo que se encarga de devolver el siguiente movimiento en la matriz 
     * @param estadoActual    recibe el estado actual en el que se encuentra la matriz
     * @param tipoCaracter    recibe el tipo caracter a evaluar dentro de la matriz
     * @return
     */
    public int getNextEstado(int estadoActual, int tipoCaracter){
        int result = -1; //si fuera error        

        if(tipoCaracter>=0 && tipoCaracter<=5 && estadoActual!=-1){ //solo hay 6 tipos de caracter por lo mismo se coloca el 5 porque inicia en 0
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
        char[] puntuacion = {'.',',',';',':'};
        char[] aritmeticos = {'+', '-', '*','/','%'};
        char[] agrupacion = {'(', ')', '[', ']', '{', '}'};
        int result = -1;



        if(Character.isLetter(caracter)){
            result = 0;
        }else if(Character.isDigit(caracter)){
            result = 1;
        }else if(caracter == '.' && estadoActual!=0){
            result = 2;
        }    
        else{
            for(char tmp : puntuacion){
                if(tmp == caracter){
                    result = 3;
                    break;
                }
            }
            for(char tmp : aritmeticos){
                if(tmp==caracter){
                    
                    result = 4;
                    break;
                }
            }
            for(char tmp : agrupacion){
                if(tmp==caracter){
                    result = 5;
                    break;
                }
            }
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
