package archivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

/** 
 * Clase encargada de leer el archivo de texto
*/
public class ReadFile {
    JTextArea textarea;
    JFileChooser f;
    FileReader fr;
    BufferedReader rdr;
    JTextArea tLabel;


    /**
     * Constructor de la clase ReadFile
     * @param file archivo (.txt) a analizar
     * @param textarea text area en donde se escribira el archivo de texto
     * @param tlabel text area con el numero de linea 
     */
    public ReadFile(JFileChooser file, JTextArea textarea, JTextArea tlabel){
        this.tLabel= tlabel;
        this.f = file;
        this.textarea = textarea;

        try {
            fr = new FileReader(f.getSelectedFile().getPath());
            rdr = new BufferedReader(fr);
            //Aqui leemos el archivo y procedemos a mandar el string al text area ...
            leerArchivo();
            
            
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        }finally{
            System.out.println("Archivo cerrado");
        }

    }



    /**
     * Metodo encargado de leer el archivo 
    */
    private void leerArchivo() {
        //int count = 0; //vere que uso le doy
        String linea;
        this.textarea.setText("");
        try {
            while((linea=rdr.readLine())!=null){
                // count++; parte de
                System.out.println(linea);
                updateText(linea);

            }
            
        } catch (Exception e) {
            System.out.println("Algo salio mal al leer el archivo de texto");
        }
    }




    
    /**
     * Metodo encargado de actualizar el texto del TextArea con el numero de linea correspondiente 
     * SOLO al cargar un archivo ya existente
    */
    private void updateText(String textL){
        this.textarea.append(textL+"\n");
    }
}