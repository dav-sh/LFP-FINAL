package ventanas;


/**
 * Clase encargada del undo y redo del archivo de texto
 */
public class EditarTxt {
    PanelMenu menu;

    /**
     * Constructor de la clase
     * @param menu Area donde se quiera trabajar
     */
    public EditarTxt(PanelMenu menu){
        this.menu = menu;
    }


    /**
     * Metodo deshacer la clase
     */
    public void deshacer(){
        try {
            menu.edit.undo();
            
        } catch (Exception e) {
            System.out.println("No habia nada");
        }
    }
    
    /**
     * Metodo rehacer la clase
     */
    public void rehacer(){
        try {
            menu.edit.redo();
            
        } catch (Exception e) {
            System.out.println("No habia nada");
        }
    }
}
