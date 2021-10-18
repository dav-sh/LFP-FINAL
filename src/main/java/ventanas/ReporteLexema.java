package ventanas;

import javax.swing.JFrame;

/**Clase encargada del reporte de lexemas */
public class ReporteLexema {
    public ReporteLexema(String[] lexemas, String[] tokens) { 
        init( lexemas, tokens);

    }

    /**
     * Metodo encargado de inicializar los componeentes del JFrame de reportes de lexemas.
     * @param lexemas array (String[]) con los nombres de los lexemas
     * @param tokens array (String[]) con los nombres de los tokens
     */
    public void init(String[] lexemas, String[] tokens){
        JFrame frame = new JFrame("REPORTE LEXEMAS");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new PanelRepLex(lexemas, tokens));
        frame.setSize(400, 400);
        frame.setVisible(true);

    }

}
