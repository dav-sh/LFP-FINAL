package ventanas;

public class EditarTxt {
    PanelMenu menu;

    public EditarTxt(PanelMenu menu){
        this.menu = menu;
    }

    public void deshacer(){
        menu.edit.undo();
    }

    
    public void rehacer(){
        menu.edit.redo();
    }
}
