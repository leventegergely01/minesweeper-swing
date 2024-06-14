import java.awt.*;
import javax.swing.*;

/**
 * A Mező osztályban megtalálhatóak az egyes mezők adatai.
 * @author Gergely Levente Máté - I15OZJ
 */
public class Mezo extends JButton{
    private int nmines;
    private boolean flagged;
    private boolean mine;
    private boolean felfedett;
    public Mezo(){
        nmines=0;
        flagged=false;
        mine=false;
        felfedett=false;
        this.setBorderPainted(true);
        this.setBackground(Color.lightGray);
        this.setBorder(BorderFactory.createEtchedBorder());
    }
    /**
     * Ha egy mezőre aknát helyezünk, beállítja azt a mine attribútumnak. Setter függvény.
     *   
     */
    public void setMine(){
        this.mine=true;
    }
    /**
     * Ha egy mezőt megjelölünk, beállítja azt a flagged attribútumnak. Setter függvény.
     */
    public void setFlagged(){
        this.flagged=true;
    }
    /**
     * Ha a mező megjelölését visszavonjuk, visszaállítja az alapállapotába a mezőt. Setter függvény.
     */
    public void setNothing(){
        this.flagged=false;
    }
    /**
     * Növeli az nmines attribútumnak a számát eggyel. Ezt az adott mező szomszédságában lévő aknák megszámolására használjuk.
     */
    public void addNmines(){
        this.nmines++;
    }
    /**
     * Visszaadja, hogy egy mező akna -e.
     * @return mine - akna -e a mező (boolean)
     */
    public boolean getMine(){
        return mine;
    }
    /**
     * Visszaadja, hogy egy mező meg van -e jelölve
     * @return flagged - meg van -e jelölve a mező (boolean)
     */
    public boolean getFlagged(){
        return flagged;
    }
    /**
     * Visszaadja az akna szomszédainak számát
     * @return nmines - meg van -e jelölve a mező (boolean)
     */
    public int getNmines(){
        return nmines;
    }
    /**
     * Ha egy mezőt felfedtünk, akkor a felfedett attribútumot truera állítjuk (ha a játék során felfedünk egy mezőt, az a játék végéig fel lesz fedve). Setter függvény.
     * 
     */
    public void setFelfedett() {
    	this.felfedett=true;
    }
    /**
     * Visszaadja, hogy egy mezőt felfedtünk -e már. Getter függvény.
     *  @return felfedett - fel van e fedve a mező (boolean) 
     */
    public boolean getFelfedett() {
    	return felfedett;
    }

}
