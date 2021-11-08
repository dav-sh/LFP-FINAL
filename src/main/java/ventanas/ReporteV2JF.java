package ventanas;

import javax.swing.JFrame;
import reportes.ReporteV2;

/** 
     * Clase encargada de crear el JFrame para los reportes
    */
public class ReporteV2JF {
    
    /**
     * Constructor de la clase reporte
     * @param existeError boolean que indica si existe un error o no.
     * @param reportV2 objeto de tipo reporte
     */
    public ReporteV2JF(boolean existeError, ReporteV2 reportV2){
        init(existeError, reportV2);
    }


    /**
     * Metodo encargado de inicializar los compontes del JFrame
     * @param existeError Indica si existen errores en el texto evaluado (true==si)
     * @param reportV2  Enviamos el objeto reporte, el cual contiene la informacion acerca de los tokens
     */
    public void init(boolean existeError, ReporteV2 reportV2){
        String title="";
        if(existeError){
            title = "Reporte de Errores";
        }else{
            title = "Reporte de Tokens";
        }
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new PanelReporteV2(existeError,reportV2));
        frame.setSize(400, 400);
        frame.setVisible(true);

    }

}
