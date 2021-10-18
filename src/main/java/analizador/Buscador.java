package analizador;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import java.awt.*;


/**Clase encargada de buscar una palabra en un texto de tipo String */
public class Buscador {
    String texto=null;
    String palabra;
    JTextArea textarea=null;

    /**
     * Constructor que recibe por parametro un nuevo textarea donde sera mostrado el texto analizado y recibe la palabra a buscar  
     * @param textarea  String en el cual se buscara la palabra
     * @param palabra  String de la palabra a buscar
     */
    public Buscador(JTextArea textarea, String palabra) {
        this.texto = textarea.getText();
        this.palabra = palabra;
        this.textarea = textarea;
        if(palabra.equals("") || palabra==null){
            JOptionPane.showMessageDialog(null, "No ha ingresado ningun texto");
        }else{
            JOptionPane.showMessageDialog(null, "Busqueda completada");
            buscar();
        }
    }



    /**Metodo encargado de buscar la palabra */
    public void buscar(){
        DefaultHighlightPainter pinta = new DefaultHighlightPainter(Color.CYAN); 
        Highlighter highlighter = textarea.getHighlighter();
        int indice;
        for(int i=0; i<texto.length()- palabra.length()+1; i++){

            indice = i;
            String cadena="";

            for(int j=0; j<palabra.length(); j++){

                if(texto.charAt(indice)==palabra.charAt(j)){
                    cadena = (cadena+texto.charAt(indice));

                    if(cadena.equals(palabra)){
                        try {
                            highlighter.addHighlight(i, i+palabra.length(), pinta);
                            
                        } catch (Exception e) {
                            System.out.println("No se pudo resaltar, porque estaba fuera del indice");
                        }
                    }
                }
                indice++;
            }
        }
    }
}
