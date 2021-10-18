package ventanas;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import analizador.Automata;
import analizador.Token;
import reportes.Reporte;

/**Clase encargada de manejar los elementos dentro del JPanel del menu principal. */
public class PanelMenu extends JPanel {
    JTextArea textArea;
    JTextField textB;
    JTextArea tLabel;
    Reporte report = new Reporte();

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


        //AQUI VA EL SCROLL  --> OJO no se agrega el text area porq ya esta dentro del scroll pane, por lo tanto se trabaja como uno mismo
        c.gridx = 1; c.gridy = 0; c.gridwidth = 2; c.gridheight = 3; 
        c.weighty=1.0; c.weightx=1.0; c.fill = GridBagConstraints.BOTH; 
        c.anchor = GridBagConstraints.PAGE_START;
        this.add(scrollPane,c);

        c.weighty=0.0; c.weightx=0.0; //reset

        //Aqui agrego el textarea del # de lienas
        c.gridx = 0; c.gridy = 0; c.gridwidth = 1; c.gridheight = 3; //posicion x,y cuantas casillas ocupa ancho, alto
        //el weighty y weightx es el valor de si estirar o no las filas y columnas, debemos de resetarla
        c.weighty=1.0; c.weightx=0.0; c.fill = GridBagConstraints.BOTH; //el eje y debe estirarse, el eje x no, y solo se estira en vertial en %
        c.anchor = GridBagConstraints.FIRST_LINE_START; //
        scrollPaneLabel.setPreferredSize(new Dimension(22,20));
        this.add(scrollPaneLabel, c);
        c.weighty=0.0; c.weightx=0.0;
        

        


        //boton abrir
        c.gridx = 3; c.gridy = 0; c.gridwidth = 1; c.gridheight = 1; 
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






        //boton guardar
        c.gridx = 3; c.gridy = 1; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=1.0; c.anchor = GridBagConstraints.NORTHEAST;

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







         //boton reportes

         c.gridx = 3; c.gridy = 3; c.gridwidth = 1; c.gridheight = 1; 
         c.weighty=0.0; c.anchor = GridBagConstraints.NORTHEAST;
         JButton r = createButton("Reportes", layout, c);
         c.weighty=0.0; //reset
         r.addActionListener(new ActionListener() {
 
             @Override
             public void actionPerformed(ActionEvent e) {
                System.out.println("Hola soy Reportes");
                if(report.getcontadorEstado(Token.ERROR)>=1){
                    new ReporteJF(true, report);
                }else{
                    new ReporteJF(false, report);
                }
             }
             
         });
         this.add(r);
         c.fill = GridBagConstraints.NONE;




         //JLabel de la parte inferior
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
        c.weighty=1.0; c.weightx=0.2; 
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
