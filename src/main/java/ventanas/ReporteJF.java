package ventanas;

import javax.swing.JFrame;
import reportes.Reporte;

/** 
     * Clase encargada de crear el JFrame para los reportes
    */
public class ReporteJF {
    
    /**
     * Constructor de la clase reporte
     * @param existeError boolean que indica si existe un error o no.
     * @param reporte objeto de tipo reporte
     */
    public ReporteJF(boolean existeError, Reporte reporte){
        init(existeError, reporte);
    }


    /**
     * Metodo encargado de inicializar los compontes del JFrame
     * @param existeError Indica si existen errores en el texto evaluado (true==si)
     * @param reporte  Enviamos el objeto reporte, el cual contiene la informacion acerca de los tokens
     */
    public void init(boolean existeError, Reporte reporte){
        String title="";
        if(existeError){
            title = "Reporte de Errores";
        }else{
            title = "Reporte de Tokens";
        }
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new PanelReporte(existeError,reporte));
        frame.setSize(400, 400);
        frame.setVisible(true);

    }

}
