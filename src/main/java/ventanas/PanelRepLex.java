package ventanas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.*;
import java.util.ArrayList;

/**Clase encargada del panel de reportes de lexemas*/
public class PanelRepLex extends JPanel{
    JTable table;
    int numCols=3;

    /**
     * constructor dela clase
     * @param lexemas array (String[]) lexemas analizados 
     * @param tokens nombre de tokens (String[]) obtenidos al analizar
     */
    public PanelRepLex(String[] lexemas, String[] tokens) {
        init(lexemas, tokens);
    }


    /**
     * Metodo encargado de iniciar los componentes del reporte de lexema
     * @param lexemas array (String[]) lexemas analizados 
     * @param tokens nombre de tokens (String[]) obtenidos al analizar
     */
    public void init(String[] lexemas, String[] tokens){
        //Creamos el Jtable y le agregamos el scroll
        this.setLayout(new BorderLayout());
        String[][] tmp = contador(lexemas, tokens);
        
        table = new JTable(tmp.length,numCols);
        JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);    

        //Definimos nombre de las columnas
        table.getColumnModel().getColumn(0).setHeaderValue("LEXEMA");
        table.getColumnModel().getColumn(1).setHeaderValue("TIPO TOKEN");
        table.getColumnModel().getColumn(2).setHeaderValue("NO. VECES (LEXEMA)");

        //imprimeInfo
        for(int i= 0; i <tmp.length;i++){ //fila
            for(int j= 0; j < numCols;j++){ //columnas
                if(j==0){ //
                    table.setValueAt(tmp[i][0],i,j);
                    
                }
                else if(j==1){
                    table.setValueAt(tmp[i][2], i, j);

                }
                else if(j==2){
                    table.setValueAt(tmp[i][1], i, j);

                }
            }
        
        }
        
        this.add(scrollPane, BorderLayout.CENTER);
    }


    /**
     * Metodo encargado del contador de lexemas
     * @param lexemas array (String[]) lexemas analizados 
     * @param tokens nombre de tokens (String[]) obtenidos al analizar
     * @return
     */
    public String[][] contador(String[] lexemas, String[] tokens) {
        ArrayList<String> yaRevisado = new ArrayList<>();
        ArrayList<String> yaRevisadoTokens = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();
        
        for(int i=0; i<lexemas.length; i++) {
            if(yaRevisado.contains(lexemas[i])){
                int pos=0;
                for(int j=0; j<yaRevisado.size(); j++) {
                    if(lexemas[i].equals(yaRevisado.get(j))){
                        pos=j;
                        break;
                    }
                }
                
                count.set(pos, count.get(pos).intValue()+1);
            }else{
                yaRevisado.add(lexemas[i]);
                yaRevisadoTokens.add(tokens[i]);
                count.add(1);
            }
        }
        String[][] tmp = new String[yaRevisado.size()][3];
        for(int i = 0; i < tmp.length; i++){ //filas
            
                
                tmp[i][0] = yaRevisado.get(i);
                tmp[i][1] = count.get(i).toString();
                tmp[i][2] = yaRevisadoTokens.get(i);
                
                System.out.println("Palabra"+tmp[i][0]+"Contador"+tmp[i][1]+" Token"+tmp[i][2]);


        }
        return tmp;
        
    }
    
}
