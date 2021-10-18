package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.*;
import analizador.Buscador;

/**Clase encargada de inicializar el JFrame de la busqueda */
public class Busqueda extends JFrame{
    /**
     * Constructor
     * @param textoTA texto obtenido del Panel Menu (String)
     * @param palabraB texto de la palabra a buscar (String)
     */
    public Busqueda(String textoTA, String palabraB) {
        super("Busqueda");
        creaPanel(textoTA, palabraB);
    }

    /**
     * Metodo encargado de crear el panel el cual contiene las posibles palabras encontradas
     * @param textoTA texto obtenido del Panel Menu (String)
     * @param palabraB texto de la palabra a buscar (String)
     */
	private void creaPanel(String textoTA, String palabraB) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        JTextArea textA = new JTextArea(textoTA);
        textA.setEditable(false);
        new Buscador(textA, palabraB);
        this.add(panel);
        this.setSize(400, 400);
        this.setVisible(true);
        panel.add(textA);
    }
}
