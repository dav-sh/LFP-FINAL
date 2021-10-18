package ventanas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.*;

/**Clase encargada del panel con el reporte de tokens */
public class PanelRepTok extends JPanel{
    JTable table;
    int numCols=2;
    public PanelRepTok(String[][] reporteCont) {
        init(reporteCont);
    }

    /**
     * Metodo encargado de inicializar los componentes del reporte de tokens
     * @param reporteCont texto (String) con el contenido del reporte
     */
    public void init(String[][] reporteCont){
        //Creamos el Jtable y le agregamos el scroll
        this.setLayout(new BorderLayout());
        
        
        table = new JTable(reporteCont.length,numCols);
        JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);    

        //Definimos nombre de las columnas
        table.getColumnModel().getColumn(0).setHeaderValue("TOKEN");
        table.getColumnModel().getColumn(1).setHeaderValue("NO. VECES (TOKEN)");

        //imprimeInfo
        for(int i= 0; i <reporteCont.length;i++){ //fila
            for(int j= 0; j < numCols;j++){ //columnas
                if(j==0){ //
                    table.setValueAt(reporteCont[i][0],i,j);
                    
                }
                else if(j==1){
                    table.setValueAt(reporteCont[i][1], i, j);

                }
            }
        
        }
        
        this.add(scrollPane, BorderLayout.CENTER);
    }




}
