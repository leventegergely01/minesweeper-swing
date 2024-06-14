import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Ez az osztály hozza létre az ablakot, amin beállíthatjuk a nehézségi szintet. 
 * @author Gergely Levente Máté - I15OZJ
 *
 */
public class Beallitas extends JFrame implements ActionListener {
    private static int m=9;
    private static int sz=9;
    private static int a=10;
    private static String szi="kezdő";
    private String[] modok ={"kezdő","haladó","mester"};
    private JLabel szint = new JLabel("Szint:");
	private JComboBox szintek= new JComboBox(modok);
	private JButton ok = new JButton("OK");
	private JButton megse = new JButton("Mégse");
    private JPanel szovegpanel = new JPanel();
    /**
     * A Beallitas osztály konstruktora. Létrehozza a a beállítások ablakát. 
     */
    public Beallitas(){
        szovegpanel.add(new JLabel("Válassz szintet!"));
        this.add(szovegpanel,BorderLayout.NORTH);
        this.setTitle("Beállítások");
        setResizable(false);
        this.setSize(400,200);
        GridBagConstraints megszor =new GridBagConstraints();
        megszor.gridwidth=GridBagConstraints.REMAINDER;
        megszor.fill = GridBagConstraints.BOTH;
        JPanel midPanel=new JPanel(new GridBagLayout());
        JPanel gombPanel=new JPanel(new GridBagLayout());
        midPanel.add(szint);
        midPanel.add(szintek);
        szintek.setBackground(Color.lightGray);
        szintek.setBorder(BorderFactory.createEtchedBorder());
        gombPanel.add(ok);
        ok.setBackground(Color.lightGray);
        ok.setBorder(BorderFactory.createEtchedBorder());
        gombPanel.add(megse);
        megse.setBackground(Color.lightGray);
        megse.setBorder(BorderFactory.createEtchedBorder());
        this.add(midPanel,BorderLayout.CENTER);
        this.add(gombPanel,BorderLayout.SOUTH);
        ok.addActionListener(this);
        megse.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae){
    	/**
    	 * Ha beállítottuk a JCombobox legördülő menüben a nehézségi szintet, az OK gomb lenyomásával állítjuk be az új beállításokat.
    	 */
        if(ae.getSource()==ok){
            if(szintek.getSelectedItem().equals("kezdő")){//kezdő szint
                this.setMag(9);
                this.setSzel(9);
                this.setAkna(10);
                this.setSzint((String) szintek.getSelectedItem());
            }
            else if(szintek.getSelectedItem().equals("haladó")){//haladó szint
                this.setMag(16);
                this.setSzel(16);
                this.setAkna(40);
                this.setSzint((String) szintek.getSelectedItem());
            }
            if(szintek.getSelectedItem().equals("mester")){// mester szint
            	this.setMag(16);
                this.setSzel(30);
                this.setAkna(99);
                this.setSzint((String) szintek.getSelectedItem());
            }
            this.dispose();
        }
        /**
         * Ha a mégse gombra nyomunk, akkor egyszerűencsak bezáródik az ablak.
         */
        if(ae.getSource()==megse){
            this.dispose();
        }
    }
    /**
     * Beállítja a pálya hosszát
     * @param magas - A pálya magassága mezőkben mérve. Setter függvény.
     */
    public void setMag(int magas){
        this.m=magas;
    }
    /**
     * Beállítja a pálya szélességét. Setter függvény.
     * @param szel - A pálya szélessége mezőkben mérve.
     */
    public void setSzel(int szel){
        this.sz=szel;
    }
    /**
     * Beállítja az aknák számát a mezőn. Setter függvény.
     * @param akna - Aknák száma a mezőn
     */
    public void setAkna(int akna){
        this.a=akna;
    }
    /**
     * Beállítja a játék szintjét(Ez majd az adatok mentésénél lesz fontos). Setter függvény.
     * @param szint - Játék kiválasztott szintje
     */
    public void setSzint(String szint){
        this.szi=szint;
    }
    /**
     * Visszaadja a pálya magasságát. Getter függvény.
     * @return m - Pálya magassága mezőkben mérve.
     */
    public int getMag(){
        return this.m;
    }
    /**
     * Visszaadja a pálya szélességét. Getter függvény.
     * @return sz - Pálya szélessége mezőkben mérve.
     */
    public int getSzel(){
        return this.sz;
    }
    /**
     * Visszaadja a pályán található aknák számát. Getter függvény.
     * @return a - Pályán található aknák száma.
     */
    public int getAkna(){
        return this.a;
    }
    /**
     * Visszaadja a kiválasztott szintet. Getter függvény.
     * @return szint - Kiválasztott szint.
     */
    public String getSzint(){
        return this.szi;
    }
}
