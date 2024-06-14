import java.io.Serializable;
/**
 * A Jatekos osztály tartalmazza egy Jatekos objektum attribútumait, valamint a játékosok adatainak a mentéséhez is felhasználjuk a Jatekos osztályt.
 * @author Gergely Levente Máté - I15OZJ
 *
 */
public class Jatekos implements Serializable{
    private String nev;
    private String szint;
    private int ido;
    public Jatekos(String n,String s,int i){
        this.nev=n;
        this.szint=s;
        this.ido=i;
    }
    /**
     * Beállítja a játékosok nevét. Setter függvény.
     * @param n - Játékos neve
     */
    public void setNev(String n){
        this.nev=n;
    }
    /**
     * Beállítja a játékosok szintjét. Setter függvény.
     * @param sz- Játék szintje
     */
    public void setSzint(String sz){
        this.szint=sz;
    }
    /**
     * Beállítja a játékosok idejét. Setter függvény.
     * @param i - Játékos ideje
     */
    public void setIdo(int i){
        this.ido=i;
    }
    /**
     * Visszaadja egy játékos nevét. Getter függvény.
     * @return nev - Játékos neve
     */
    public String getNev(){
        return nev;
    }
    /**
     * Visszaadja egy játékos szintjét. Getter függvény.
     * @param szint - Játék szintje
     */
    public String getSzint(){
        return szint;
    }
    /**
     * Visszaadja egy játékos idejét. Getter függvény.
     * @return ido - Amennyi időt játszott a játékos
     */
    public int getIdo(){
        return ido;
    }
   
}
