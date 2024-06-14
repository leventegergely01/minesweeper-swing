import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 * A játékosok adatait tároljuk el ebben az osztályban.
 * (A SwingMVC labor alapján)
 * @author Gergely Levente Máté - I15OZJ
 *
 */
public class JatekosData extends AbstractTableModel {
    private static List<Jatekos> jatekosok= new ArrayList<Jatekos>();
    /**
     * Visszaadja azt, hogy a táblázat hány soros, és ez határozza meg a megjelenített sorok számát. Újradefiniált függvény.
     * @return a táblázat sorainak száma
     */
    public int getRowCount(){
    	if(jatekosok.size()<10) {
    		return jatekosok.size();
    	}
    	else {
    		return 10;
    	}
        
    }
    /**
     * A játékosok eredményeit az idejük szerint rendezi sorrendbe a függvény.
     * 
     */
    public static void listaz() {
		ArrayList<Jatekos> tmp = new ArrayList<Jatekos>(jatekosok);
		Comparator<Jatekos> cmp= Comparator.comparing(Jatekos::getIdo);
		Collections.sort(tmp,cmp);
		jatekosok=tmp;	
	}
    /**
     * Visszaadja a táblázat oszlopainak számát. Meghatározza, hogy hány oszlop jelenjen meg a táblázatban. Újradefiniált függvény.
     * @return a táblázat oslopainak a száma
     */
    public int getColumnCount(){
        return 3;
    }
    /**
     * Visszaadja, hogy egy sorban, és azoknak a celláiban milyen értékek jelennek meg. Újradefiniált függvény.
     * @param row - a cella sorindexe
     *  @param column - a cella oszlopindexe, aminek az értékét lekérdezi
     */
    public Object getValueAt(int row,int column){
        Jatekos j =jatekosok.get(row);
        switch(column){
            case 0:
                return  j.getNev();
            case 1:
                return  j.getSzint();
            default:
            	return  j.getIdo();

        }
    }
    /**
     * Visszaadja a táblázat fejléceinek a címét. Újradefiniált függvény.
     * @param column - a táblázat sorának indexe
     * @return a táblázat adott oszlopindexében a fejléc 
     */
    public String getColumnName(int column){
        switch (column){
            case 0:
                return "Név";
            case 1:
                return "Szint";
            default:
            	return "Idő";
        }

    }
    /**
     * Visszaadja azt, hogy az egyes oszlopok milyen adattípusú adatokat tartalmaznak. Újradefiniált függvény.
     * @param columnIndex - Az adott oszlopok oszlopindexe.
     */
    public  Class<?> getColumnClass(int columnIndex){
        switch (columnIndex){
            case 0:
                return String.class;
            case 1:
                return String.class;
            default:
                return Integer.class;

        }
    }
    /**
     * Visszaadja, hogy egy adott cella módosítható -e. Ebben a táblázatban semelyik cella nem módosítható. Újradefiniált függvény.
     * @param row - a cella sorindexe
     * @param column - a cella oszlopindexe
     */
    public boolean isCellEditable(int row,int column){
        return false;
    }
    /**
     * Hozzáaadja egy játékos adatait az eredménytáblához.
     * @param nev - Játékos neve
     * @param szint - a szint amelyen a játékos játszott
     * @param ido - az idő, ameddig az adott játékos játéka tartott.
     */
    public void addJatekos(String nev,String szint, int ido){
        jatekosok.add(new Jatekos(nev,szint,ido));
        fireTableRowsInserted(0, jatekosok.size()-1);
    }
    /**
     * Visszaadja a jatekosok listát. Getter függvény.
     * @return jatekosok - a lista, ami a játékosok adatait tartalmazza. 
     */
    public List<Jatekos> getLista(){
    	return jatekosok;
    }
    /**
     * A függvény paraméterként egy List<Jatekos> típusú listát kap, aminek az értékeivel feltölti a jatekosok listát. Setter függvény.
     * @param list - A lista, aminek az értékeivel feltölti a jatekosok listát a függvény.
     */
    public void setLista(List<Jatekos> list){
    	jatekosok=list;
    }
}
