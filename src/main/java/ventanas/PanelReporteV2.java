package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import analizador.Lexema;
import analizador.Token;
import reportes.ReporteV2;

public class PanelReporteV2 extends JPanel{


    ReporteV2 report;
    private int numCols=4;

    public PanelReporteV2(boolean hasError, ReporteV2 reporte){
        this.report = reporte;
        this.setLayout(new GridBagLayout());
        printReport(hasError);
    }

    private void printReport(boolean hasError){
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0; c.gridy =0;c.gridwidth=2;c.gridheight=2;
        c.weighty=1.0; c.weightx=1.0; c.fill=GridBagConstraints.BOTH;
        
        model.addColumn("LEXEMA");
        model.addColumn("TOKEN");
        model.addColumn("FILA");
        model.addColumn("COLUMNA");


        //Definimos nombre de las columnas
        /*table.getColumnModel().getColumn(0).setHeaderValue("LEXEMA");
        table.getColumnModel().getColumn(1).setHeaderValue("TOKEN");
        table.getColumnModel().getColumn(2).setHeaderValue("FILA");
        table.getColumnModel().getColumn(3).setHeaderValue("COLUMNA");
        */

        //Llenamos la tabla
        fillJTable(hasError, model);

        JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);    
        this.add(scrollPane,c);
    }
    
    
    private void fillJTable(boolean hasError, DefaultTableModel model ){
        Lexema [] lexem = null;
        if(hasError){
            lexem = report.getLexemasInv();
        }else{
            lexem = report.getLexemasVal();
        }
        if(lexem.length > 0 ){
            for(int i=0; i<lexem.length; i++){  //Filas
                if(lexem[i].getToken()!=Token.SEPARADOR && lexem[i].getToken()!=null){
                    model.addRow(new Object[]{lexem[i].getLine(),lexem[i].getToken().getNombreEstado(),lexem[i].getPos()[0],lexem[i].getPos()[1]});
                }
            }
        }

        

    }







}
