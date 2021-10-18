package archivos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;


/**
 * Clase en encargada de guardar el documento de texto 
*/
public class SaveFile {
    JFileChooser fileChooser;
    JTextArea textArea;


    /**
     * Constructor de la clase SaveFile
     * @param fileChooser2 file chooser encargado de elegir el archivo
     * @param textarea text area en donde se escribira el archivo de texto
     */
    public SaveFile(JFileChooser fileChooser2, JTextArea textarea) {
        this.textArea = textarea;
        this.fileChooser = fileChooser2;
        saveText();
    }

    /**
     * Metodo encargado de guardar el documento de texto
     */
    public void saveText(){
        fileChooser.setApproveButtonText("Guardar");
        //File archivo = fileChooser.getSelectedFile(); //sirve apra crear un archivo pero no especifica la extension
        
        File archivoR = fileChooser.getSelectedFile(); //obtenemos la ruta del archivo a crear
        if(archivoR.exists()){
            System.out.println("El archivo ya existe: " + archivoR.getAbsolutePath());
        }else{
            archivoR = new File(archivoR.getAbsolutePath() + ".txt");
            System.out.println("El archivo ha sido creado: "+archivoR.getAbsolutePath());
            writeText(archivoR);
            // File archivo = new File(fileChooser.getSelectedFile()+".txt"); //sirve para crear un archivo y especifica la extension

        }
        

        
    }



    /**
     * 
     */

     /**
      * Metodo encargado de escribir el texto en un archivo para posteriormente guardarlo 
      * @param archivo archivo del cual se extraera el texto a analizar
      */
    public void writeText(File archivo){
        FileWriter writer=null;
        try {
            writer = new FileWriter(archivo, true);
            writer.write(this.textArea.getText());
            //writer.write("Holaaaaa"); ejemplo para guardar texto
            //System.out.println(this.textArea.getText());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Algo salio mal xd");
        }
    }



}
