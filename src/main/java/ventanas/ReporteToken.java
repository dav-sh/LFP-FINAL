package ventanas;

import javax.swing.JFrame;

/**Clase encargada del reporte de tokens */
public class ReporteToken {

    /**
     * Constructor de la clase de reporte de token
     * @param reporteCont texto (String[][]) con el contenido del reporte
     */
    public ReporteToken(String[][] reporteCont) { 
        init(reporteCont);

    }

   /**
    * Metodo encargado de inicializar los componentes del reporte de tokens
    * @param reporteCont texto (String[][]) con el contenido del reporte
    */
    public void init(String[][] reporteCont){
        JFrame frame2 = new JFrame("REPORTE TOKENS");
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.add(new PanelRepTok(reporteCont));
        frame2.setSize(400, 400);
        frame2.setVisible(true);

    }

}
