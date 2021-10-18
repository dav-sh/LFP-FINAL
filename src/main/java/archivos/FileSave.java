package archivos;

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
    public FileSave(JTextArea textarea) {
        this.textarea = textarea;
        file();  
    }

    /**
     * Este metodo sirve para abrir el explorador de archivos y seleccionar el archivo de texto con extension .txt
    */
    public void file(){
        JFileChooser fileChooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Archivos de texto (.txt)", "txt");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showSaveDialog(fileChooser);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    new SaveFile(fileChooser, this.textarea);
                } catch (Exception e) {
                    System.out.println("No se envio el archivo o no se pudo guardar correctament");    
                }
        }

    }
}