package archivos;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;


/** 
 * Clase encargada de seleccionar el archivo de texto 
*/
public class FileSave {
    JTextArea textarea;
    /**
     * Constructor de la clase File Save
     * @param textarea text area en donde se escribir el archivo de texto
     */
    public FileSave() {
        //file();  
    }

    /**
     * Este metodo sirve para abrir el explorador de archivos y seleccionar el archivo de texto con extension .txt
    */
    public File file(JTextArea textarea){
        this.textarea = textarea;
        File archivo=null;
        JFileChooser fileChooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Archivos de texto (.txt)", "txt");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showSaveDialog(fileChooser);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    archivo=new SaveFile().saveText(fileChooser, this.textarea);
                } catch (Exception e) {
                    System.out.println("No se envio el archivo o no se pudo guardar correctament");    
                }
        }
        return archivo;
    }
}