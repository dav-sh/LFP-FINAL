package ventanas;

import javax.swing.JTextArea;
import javax.swing.text.Element;

/**Clase encargada de manear el numero de linea a la par de donde se ingresa el texto */
public class NumeroLinea {
    JTextArea textarea;
    JTextArea tLabel;

    /**Constructor de la clase NumeroLinea
     * 
      */

      /**
       * Constructor
       * @param textarea texto (String) obtenido del text area de la clase menu principal
       * @param tlabel text area encargado del numero de linea
       */
    public NumeroLinea(JTextArea textarea, JTextArea tlabel) {
        this.textarea = textarea;
        this.tLabel = tlabel;
    }


    /**
     * Metodo encargado de obtener el numero de linea del textarea
      */
    public void getNumerosLinea() {
        String numerosLinea = getLineNumbersText();
        tLabel.setText(numerosLinea);

    }

    /**
     * Metodo encargado de devolver el string con los numeros de linea totales
      */
    private String getLineNumbersText(){
        int caretPosition = textarea.getDocument().getLength(); //se encarga de obtener la posicion del cursor en el textarea
        Element root = textarea.getDocument().getDefaultRootElement();  //detecta si hay un elemento es decir un caracter o algo por el estilo escrito en el documento
        StringBuilder lineNumbersTextBuilder = new StringBuilder(); //es como un string pero es mutable, es decir es modificable y se recomienda si vamos a concatenar elementos
        lineNumbersTextBuilder.append("1").append(System.lineSeparator()); //el System.lineSeparator es similar a un \n que indica un salto de linea

        //a partir de la linea 2 se encarga de ir agregando los numeros en el stringbuilder creado
        for (int elementIndex = 2; elementIndex < root.getElementIndex(caretPosition) + 2; elementIndex++)
        {
            lineNumbersTextBuilder.append(elementIndex).append(System.lineSeparator());
        }

        return lineNumbersTextBuilder.toString();
    }



}
