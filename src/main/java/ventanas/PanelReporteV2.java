package ventanas;

import javax.swing.*;
import java.awt.*;
import analizador.Lexema;
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
        JTable table;
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0; c.gridy =0;c.gridwidth=2;c.gridheight=2;
        c.weighty=1.0; c.weightx=1.0; c.fill=GridBagConstraints.BOTH;
        if(hasError){
            table= new JTable(report.getLexemasInv().length, numCols);
        }else{
            table= new JTable(report.getLexemasVal().length, numCols);
            
        }
        //Definimos nombre de las columnas
        table.getColumnModel().getColumn(0).setHeaderValue("LEXEMA");
        table.getColumnModel().getColumn(1).setHeaderValue("TOKEN");
        table.getColumnModel().getColumn(2).setHeaderValue("FILA");
        table.getColumnModel().getColumn(3).setHeaderValue("COLUMNA");
        
        //Llenamos la tabla
        fillJTable(hasError, table);

        JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);    
        this.add(scrollPane,c);
    }
    
    
    private void fillJTable(boolean hasError, JTable table){
        Lexema [] lexem = null;
        if(hasError){
            lexem = report.getLexemasInv();
        }else{
            lexem = report.getLexemasVal();
        }
        if(lexem.length > 0){
            for(int i=0; i<lexem.length; i++){  //Filas
                for(int j=0; j<numCols; j++){
                    if(j==0){
                        table.setValueAt(lexem[i].getLine() , i, j);
                    }else if(j==1){
                        table.setValueAt(lexem[i].getToken().getNombreEstado() , i, j);
                    }else if(j==2){
                        table.setValueAt(lexem[i].getPos()[0], i, j);
                    }else if(j==3){
                        table.setValueAt(lexem[i].getPos()[1], i, j);
                    }
    
                }
            }
        }

        

    }







}
