package ventanas;

public class EditarTxt {
    PanelMenu menu;

    public EditarTxt(PanelMenu menu){
        this.menu = menu;
    }

    public void deshacer(){
        try {
            menu.edit.undo();
            
        } catch (Exception e) {
            System.out.println("No habia nada");
        }
    }
    
    
    public void rehacer(){
        try {
            menu.edit.redo();
            
        } catch (Exception e) {
            System.out.println("No habia nada");
        }
    }
}
