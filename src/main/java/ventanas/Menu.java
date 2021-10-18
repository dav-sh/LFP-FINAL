package ventanas;

import javax.swing.JFrame;

/**Clase
 * Esta clase es la encargada de crear la ventana del menu principal
 */
public class Menu {
    public Menu() {
        construye();
    }

    /*Este metodo se encarga de construir la ventana del menu*/
    public void construye() {
        JFrame frame = new JFrame("Inicio");
        frame.setDefaultCloseOperation(3);
        frame.add(new PanelMenu());
        frame.setSize(600, 400);
        frame.setVisible(true);
        
    }

}
