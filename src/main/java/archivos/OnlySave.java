package archivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;



/**
 * Clase en encargada de guardar el documento de texto 
*/
public class OnlySave {
    File archivo;
    JTextArea textArea;




    /**
     * Metodo encargado de guardar el documento de texto
     */
    public File saveText(File archivo, JTextArea textarea){
        this.textArea = textarea;
        this.archivo = archivo;
        if(archivo != null){
            if(archivo.exists()){
                JOptionPane.showMessageDialog(null, "Cambios Guardados");
                System.out.println("El archivo SI existe: " + archivo.getAbsolutePath());
                writeText(archivo); 
            }
        }
        else{
            int a = JOptionPane.showConfirmDialog(null, "Desea guardar los cambios?");
            if(a == JOptionPane.YES_OPTION){
                archivo=new archivos.FileSave().file(textArea);

            }


        }


        return archivo;
    }



    /**
     * 
     */

     /**
      * Metodo encargado de escribir el texto en un archivo para posteriormente guardarlo 
      * @param archivo archivo del cual se extraera el texto a analizar
      */
    public void writeText(File archivo){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(this.textArea.getText());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Algo salio mal xd");
        }
    }



}
