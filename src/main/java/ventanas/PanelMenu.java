package ventanas;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.UndoManager;
import analizador.Automata;
import analizador.Token;
import reportes.Reporte;
import reportes.ReporteV2;

/**Clase encargada de manejar los elementos dentro del JPanel del menu principal. */
public class PanelMenu extends JPanel {
    JTextArea textArea;
    JTextField textB;
    JTextArea tLabel;
    JComboBox<String> menu;
    //Reporte report = new Reporte();
    ReporteV2 reportV2 = new ReporteV2();
    UndoManager edit = new UndoManager();
    EditarTxt edicion = new EditarTxt(this);

    /**Constructor de la clase PanelMenu. */
    public PanelMenu(){
        createPanel();

    }


    /**
     * Metodo encargado de crear los botones del menu principal
     * @param name  nombre del boton a crear (String)
     * @param layout tipo de layout a utilizar (GridBaglayout)
     * @param c posicion del boton (GridBaglayout)
     * @return JButton con los parametros anteriores
     */
    public JButton createButton(String name, GridBagLayout layout, GridBagConstraints c){
        JButton button = new JButton(name);
        layout.setConstraints(button, c);
        return button;
    }



    /*Metodo encargado de crear el panel dle menu principal*/
    public void createPanel() {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout (layout); // Definimos el tipo de layout
        GridBagConstraints c = new GridBagConstraints();

        


        //JLabel de las lineas del texto
        tLabel = new JTextArea("1");
        tLabel.setEditable(false);
        tLabel.setBackground(Color.LIGHT_GRAY);
       

        //text area
        textArea = new JTextArea("Escribe algo",15,20);
        //Aqui trato de agregarle un scroll 
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        JScrollPane scrollPaneLabel = new JScrollPane(tLabel,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneLabel.getVerticalScrollBar().setModel(scrollPane.getVerticalScrollBar().getModel()); //tomamos el modelo del scroll principal, el que aparece en el textarea


        //Aqui agregamos un evento que encarga de ver las actualizaciones del textarea
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent event) {
                new NumeroLinea(textArea,tLabel).getNumerosLinea();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                new NumeroLinea(textArea,tLabel).getNumerosLinea();
                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                new NumeroLinea(textArea,tLabel).getNumerosLinea();
                
            }
        });

        textArea.getDocument().addUndoableEditListener(
            new UndoableEditListener() {
                public void undoableEditHappened(UndoableEditEvent event) {
                    edit.addEdit(event.getEdit());
                }
            }
        );





        //AQUI VA EL SCROLL  --> OJO no se agrega el text area porq ya esta dentro del scroll pane, por lo tanto se trabaja como uno mismo
        c.gridx = 1; c.gridy = 1; c.gridwidth = 10; c.gridheight = 3; 
        c.weighty=1.0; c.weightx=1.0; c.fill = GridBagConstraints.BOTH; 
        c.anchor = GridBagConstraints.PAGE_START;
        this.add(scrollPane,c);

        c.weighty=0.0; c.weightx=0.0; //reset

        //Aqui agrego el textarea del # de lienas
        c.gridx = 0; c.gridy = 1; c.gridwidth = 1; c.gridheight = 3; //posicion x,y cuantas casillas ocupa ancho, alto
        //el weighty y weightx es el valor de si estirar o no las filas y columnas, debemos de resetarla
        c.weighty=1.0; c.weightx=0.0; c.fill = GridBagConstraints.BOTH; //el eje y debe estirarse, el eje x no, y solo se estira en vertial en %
        c.anchor = GridBagConstraints.FIRST_LINE_START; //
        scrollPaneLabel.setPreferredSize(new Dimension(22,20));
        this.add(scrollPaneLabel, c);
        c.weighty=0.0; c.weightx=0.0;
        





        //Menu de Opciones
        menu=new JComboBox<>(new String[]{"Abrir", "Nuevo","Guardar","Guardar Como"});
        c.gridx = 1; c.gridy = 0; c.gridwidth = 1; c.gridheight = 1; //posicion x,y cuantas casillas ocupa ancho, alto
        c.weighty=1.0; c.fill = GridBagConstraints.BOTH; 
        menu.setPreferredSize(new Dimension(10,10));
        this.add(menu, c);
        c.weighty=0.0; c.weightx=0.0;
        

        menu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Elegiste: "+ menu.getSelectedItem().toString());
                String opcion = menu.getSelectedItem().toString();
                switch (opcion) {
                    case "Abrir":    new archivos.FileOpen(textArea,tLabel);    ;  break;
                    case "Nuevo":                    ;  break;
                    case "Guardar":                    ;  break;
                    case "Guardar Como":  new archivos.FileSave(textArea) ;  break;
                }
			}
            
        });





        
        //deshacer
        c.gridx = 3; c.gridy = 0; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=1.0; c.fill = GridBagConstraints.BOTH; 

        JButton deshacer = createButton("<-", layout, c);
        c.weighty=0.0;  c.anchor = GridBagConstraints.CENTER; //reset

        deshacer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				edicion.deshacer();
			}
            
        });

        this.add(deshacer);



        
        //rehacer
        c.gridx = 4; c.gridy = 0; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=1.0; c.fill = GridBagConstraints.BOTH; 

        JButton rehacer = createButton("->", layout, c);
        c.weighty=0.0;  c.anchor = GridBagConstraints.CENTER; //reset

        rehacer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				edicion.rehacer();
			}
            
        });

        this.add(rehacer);
        
        /*
        //boton abrir
        c.gridx = 7; c.gridy = 0; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=1.0; c.fill = GridBagConstraints.HORIZONTAL; c.anchor = GridBagConstraints.NORTHEAST;

        JButton a = createButton("Abrir", layout, c);
        c.weighty=0.0;  c.anchor = GridBagConstraints.CENTER; //reset

        a.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola soy abrir");
                new archivos.FileOpen(textArea,tLabel);
			}
            
        });

        this.add(a);

        */
        
        
        /*

        //boton guardar
        c.gridx = 3; c.gridy = 1; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=1.0; c.anchor = GridBagConstraints.NORTHEAST; c.fill = GridBagConstraints.HORIZONTAL;

        JButton g = createButton("Guardar", layout, c);
        c.weighty=0.0; c.anchor = GridBagConstraints.CENTER; //reset

        g.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola soy Guardar");
                new archivos.FileSave(textArea);
			}
            
        });
        this.add(g);



        */

        /*
        //boton evaluar

        c.gridx = 3; c.gridy = 2; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=1.0; c.anchor = GridBagConstraints.NORTHEAST; 

        JButton e = createButton("Evaluar", layout, c);
        c.weighty=0.0; //reset

        e.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola soy Evaluar");
                new Automata(textArea, report);
                JOptionPane.showMessageDialog(null,"Analisis Realizado");

			}
            
        });
        this.add(e);




        */

        //boton copiar

        c.gridx = 5; c.gridy = 0; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=0.0; c.fill = GridBagConstraints.BOTH; //c.anchor = GridBagConstraints.NORTHEAST;
        JButton copy = createButton("Copiar", layout, c);
        c.weighty=0.0; //reset
        copy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            textArea.copy();
            
            }
            
        });
        this.add(copy);
        c.fill = GridBagConstraints.NONE;


        //boton pegar

        c.gridx = 6; c.gridy = 0; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=0.0; c.fill = GridBagConstraints.BOTH; //c.anchor = GridBagConstraints.NORTHEAST;
        JButton paste = createButton("Pegar", layout, c);
        c.weighty=0.0; //reset
        paste.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            textArea.paste();
            
            }
            
        });
        this.add(paste);
        c.fill = GridBagConstraints.NONE;




        //boton sintactico

        c.gridx = 8; c.gridy = 0; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=0.0; c.fill = GridBagConstraints.BOTH; //c.anchor = GridBagConstraints.NORTHEAST;
        JButton sintactico = createButton("A. Sinctactico", layout, c);
        c.weighty=0.0; //reset
        sintactico.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            System.out.println("Hola soy sintactico");

            /*
            if(report.getcontadorEstado(Token.ERROR)>=1){
                new ReporteJF(true, report);
            }else{
                new ReporteJF(false, report);
            }
            */
            
            }
            
        });
        this.add(sintactico);
        c.fill = GridBagConstraints.NONE;


        //boton lexico

        c.gridx = 9; c.gridy = 0; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=0.0; c.fill = GridBagConstraints.BOTH; 
        JButton lexico = createButton("A. Lexico", layout, c);
        c.weighty=0.0; //reset
        lexico.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //new Automata(textArea, report);
                new Automata(textArea, reportV2);
                JOptionPane.showMessageDialog(null,"Analisis Realizado");

            /*
            if(report.getcontadorEstado(Token.ERROR)>=1){
                new ReporteJF(true, report);
            }else{
                new ReporteJF(false, report);
            }
            */
            
            }
            
        });
        this.add(lexico);
        c.fill = GridBagConstraints.NONE;




         //boton reportes

         c.gridx = 10; c.gridy = 0; c.gridwidth = 1; c.gridheight = 1; 
         c.weighty=0.0; c.fill = GridBagConstraints.BOTH; //c.anchor = GridBagConstraints.NORTHEAST;
         JButton r = createButton("Reportes", layout, c);
         c.weighty=0.0; //reset
         r.addActionListener(new ActionListener() {
 
             @Override
             public void actionPerformed(ActionEvent e) {
                System.out.println("Hola soy Reportes");

                new ReporteV2JF(reportV2.existenErrores(),reportV2);

                /*
                if(report.getcontadorEstado(Token.ERROR)>=1){
                    new ReporteJF(true, report);
                }else{
                    new ReporteJF(false, report);
                }
                */

                //new 
                
             }
             
            });
            this.add(r);
            c.fill = GridBagConstraints.NONE;
            
        
         //boton Acerca de

         c.gridx = 10; c.gridy = 4; c.gridwidth = 1; c.gridheight = 1; 
         c.weighty=0.0; c.fill = GridBagConstraints.BOTH; //c.anchor = GridBagConstraints.NORTHEAST;
         JButton acerca = createButton("Acerca de", layout, c);
         c.weighty=0.0; //reset
         acerca.addActionListener(new ActionListener() {
 
             @Override
             public void actionPerformed(ActionEvent e) {
                System.out.println("Yo hice el programa, pos quien mas xd");

                
             }
             
            });
            this.add(acerca);
            c.fill = GridBagConstraints.NONE;




        //JLabel de la parte inferior --> busqueda
        textB = new JTextField("Buscar texto:");
        c.gridx = 1; c.gridy = 4; c.gridwidth = 1; c.gridheight = 1; //posicion x,y cuantas casillas ocupa ancho, alto
        c.weighty=0.0; c.weightx=1.0; c.fill = GridBagConstraints.HORIZONTAL;
        textB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                textB.setText("");
            }

        });
        this.add(textB, c);
        c.weighty=0.0; c.weightx=0.0;






        //JButton Busqueda
        JButton buttonB = new JButton("Buscar"); 
        c.gridx = 2; c.gridy = 4; c.gridwidth = 1; c.gridheight = 1; //posicion x,y cuantas casillas ocupa ancho, alto
        c.weighty=1.0; c.weightx=0.5; 
        buttonB.setPreferredSize(new Dimension(20,20));
        this.add(buttonB, c);
        c.weighty=0.0; c.weightx=0.0;
        buttonB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Busqueda(textArea.getText(),textB.getText());
			}
            
        });


        

         


    }




}
