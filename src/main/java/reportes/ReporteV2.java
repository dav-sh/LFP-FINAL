package reportes;

import java.util.ArrayList;
import analizador.*;


public class ReporteV2 {
    ArrayList<Lexema> lexemasValidos = new ArrayList<>();
    ArrayList<Lexema> lexemasError = new ArrayList<>();
    Token []tokens= Token.values();


    public void evaluarLexema(int estadoFinal,String lexema, int columna, int fila){

        if(estadoFinal==1 || estadoFinal==2 || estadoFinal==3 || estadoFinal==5 || estadoFinal==6){
            estadoFinal = -1;
        }


        System.out.println("Evaluando"+lexema+"*");
        if(!lexema.equals(" ")){ //ignoramos los espacios y agregamos directamente al string builder, ya que los necesitamos para la impresion del texto nuevamente
            System.out.println("Comienza el analisis");
            
            if(estadoFinal==Token.ERROR.getNumeroEstado()){
                regTokenInv(estadoFinal,lexema,columna,fila);
            }
            else{
                System.out.println("Se registro el lexema::"+ lexema);
                regTokenVal(estadoFinal,lexema,columna,fila);
            }

        }else{
            //Aqui agregamos los espacios directamente
            System.out.println("Era un espacio, asi q no se evaluo ..... pero se agrego al texto");
        }

    }




    private void regTokenVal(int estadoFinal,String lexema,int columna,int fila) {

        //Antes de todo evaluamos si no es una palabra reservada u algun otro signo necesario para al analizador sintactico
        String [] reservadas ={"ESCRIBIR", "FIN", "REPETIR", "INICIAR", "SI", "VERDADERO", "FALSO", "ENTONCES"};
        int[] pos={fila,columna};
        if(estadoFinal==Token.IDENTIFICADOR.getNumeroEstado()){
            boolean similar=false;
            for(String tmp: reservadas){  //Si coindice con alguna de las palabras reservadas, cambiamos, los valores del Token
                if(lexema.equals(tmp)){
                    switch (tmp){
                        case "ESCRIBIR":     lexemasValidos.add(new Lexema(lexema,Token.ESCRIBIR.getNumeroEstado(), Token.ESCRIBIR ,pos));                 break;
                        case "FIN":       lexemasValidos.add(new Lexema(lexema,Token.FIN.getNumeroEstado(), Token.FIN ,pos));              break;
                        case "REPETIR":        lexemasValidos.add(new Lexema(lexema,Token.REPETIR.getNumeroEstado(), Token.REPETIR ,pos));     break;
                        case "INICIAR":          lexemasValidos.add(new Lexema(lexema,Token.INICIAR.getNumeroEstado(), Token.INICIAR ,pos));          break;
                        case "SI":       lexemasValidos.add(new Lexema(lexema,Token.SI.getNumeroEstado(), Token.SI ,pos));              break;
                        case "VERDADERO":lexemasValidos.add(new Lexema(lexema,Token.VERDADERO.getNumeroEstado(), Token.VERDADERO ,pos)); break;
                        case "FALSO": lexemasValidos.add(new Lexema(lexema,Token.FALSO.getNumeroEstado(), Token.FALSO ,pos)); break;
                        case "ENTONCES": lexemasValidos.add(new Lexema(lexema,Token.ENTONCES.getNumeroEstado(), Token.ENTONCES ,pos)); break; 
                    }

                    similar=true;
                    break;
                }
            }
            
            if(!similar){
                //AHPRA TENEMOS Q SEPARR CADA TOKEN
                lexemasValidos.add(new Lexema(lexema,estadoFinal,tipoToken(estadoFinal),pos));
            }

        }
        else{  //Si no coincide con palabras reservadas simplemente se guarda
            System.out.println("Se registro el lexema en paso 2"+ lexema);
            lexemasValidos.add(new Lexema(lexema,estadoFinal,tipoToken(estadoFinal),pos));

        }
    }



    private void regTokenInv(int estadoFinal,String lexema,int columna,int fila) {
        //Si no coincide con palabras reservadas simplemente se guarda
        int[] pos={fila,columna};
        System.out.println("Se registro el Lexema: " + lexema);
        lexemasError.add(new Lexema(lexema,estadoFinal,tipoToken(estadoFinal),pos));
        
    }




    private Token tipoToken(int estadoFinal){
        Token tmp=null;
        for(Token tok : tokens){
            if(tok.getNumeroEstado()==estadoFinal){
                tmp=tok;
            }
        }
        return tmp;
    }



    public boolean existenErrores(){
        boolean ret=true;
        if(lexemasError.isEmpty()){
            System.out.println("No habian errores");
            return false;
        }
        return ret;
    }


    public Lexema[] getLexemasVal(){
        Lexema[] lexem = new Lexema[lexemasValidos.size()];
        for(int i=0;i<lexemasValidos.size(); i++){
            lexem[i]=lexemasValidos.get(i);
        }

        return lexem;
    }


    public Lexema[] getLexemasInv(){
        Lexema[] lexem = new Lexema[lexemasError.size()];
        for(int i=0;i<lexemasError.size (); i++){
            lexem[i]=lexemasError.get(i);
        }

        return lexem;
    }


    public void cleanReport(){
        lexemasError.clear();
        lexemasValidos.clear();
    }


}





