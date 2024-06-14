import java.awt.event.*;
import java.io.*;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
/**
 * A JatekosFrame osztály hozza létre az eredménytáblázatot. 
 * (A SwingMVC labor alapján)
 * @author Gergely Levente Máté - I15OZJ
 *
 */
public class JatekosFrame extends JFrame{
	/**
	 * Tárolja a játékosok adatait.
	 */
    private JatekosData data; 
    /**
     * Létrehozza az eredménylista ablakát
     */
    private void initComponents(){
        this.setLayout(new BorderLayout());
        JTable t =new JTable(data);
        JScrollPane sp = new JScrollPane(t);
        t.setFillsViewportHeight(true);
        add(sp,BorderLayout.CENTER);
    }
    /**
     * A JatekosFrame osztály konstruktora. Az ablak megnyitásakor betölti az elmentett adatokat, majd bezáráskor elmenti.
     */
    public  JatekosFrame(){
        super("Eredménylista");
        try{
        	
        	 /**
        	  * Adatok betöltése induláskor
        	  */
        	 
            data = new JatekosData();
            data.listaz();
            ObjectInputStream ois= new ObjectInputStream(new FileInputStream("eredmenyek.dat"));
            data.setLista((List<Jatekos>)ois.readObject());
            ois.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        /**
         * Adatok mentése bezáráskor
         */
         
         
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try{
                	data.listaz();
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("eredmenyek.dat"));
                    oos.writeObject(data.getLista());
                    oos.close();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        /**
         * Az ablak felépítése
         */
        setMinimumSize(new Dimension(500,300));
        initComponents();
    }
    /**
     * Visszaadja az objektumot, ami a játékosok adatait tárolja. Getter függvény.
     * @return data - A játékosok adatait tároló objektum
     */
    public JatekosData getData() {
    	return data;
    }
    /**
     * A függvénynek paraméterként megadunk egy JatekosData objektumot, aminek az adatait feltöltjük a data attribútumba. Setter függvény. 
     * @param d - A JatekosData objektum, aminek a tartalmával feltöltjük a data objektumot.
     */
    public void setData(JatekosData d) {
    	data=d;
    }
}

