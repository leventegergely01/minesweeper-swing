import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
/**
 * Ez az osztály valósítja meg magát a játékot.
 * @author Gergely Levente Máté - I15OZJ
 *
 */
public class Jatek extends JFrame implements ActionListener, MouseListener, Runnable{
	private ImageIcon smile= new ImageIcon("smiling-face.png");
	private ImageIcon flag= new ImageIcon("red-flag.png");
    private Mezo[][] palya;
    private Container ui = new Container();
    private JPanel upperPanel = new JPanel(new BorderLayout());
    private int ido =0;
    private JLabel timLabel = new JLabel(String.valueOf(ido));
    private JLabel aknakSzamaLabel= new JLabel();
    private JButton resetButton= new JButton();
    private Timer tmr;
    private int m,sz,a;
    private int aknakSzama;
    private String szint;
    private JMenuBar jmb= new JMenuBar(); 
    private JMenu jatekMenu= new JMenu("Játék");
    /**
     * A Jatek osztály konstruktora, létrehozza a játék ablakát, és elhelyezi a rajta lévő komponenseket.
     * @param mag - a pálya magassága
     * @param szel - a pálya szélessége
     * @param ak - aknák száma a pályán
     * @param szint - a játék szintje
     */
    public Jatek(int mag, int szel, int ak, String szint) {
        this.m=mag;
        this.sz=szel;
        this.a=ak;
        this.szint=szint;
        aknakSzama = ak;
        palya = new Mezo[this.m][this.sz];
        this.setTitle("Aknakereső");
        this.setSize(this.sz*30,this.m*30);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setBackground(Color.lightGray);
        GridBagConstraints megszor= new GridBagConstraints();
        megszor.gridwidth=GridBagConstraints.REMAINDER;
        aknakSzamaLabel.setText(String.valueOf(aknakSzama));
        upperPanel.add(timLabel, BorderLayout.EAST);
        timLabel.setForeground(Color.red);
        upperPanel.add(resetButton);
        resetButton.setIcon(smile);
        resetButton.setForeground(Color.blue);
        resetButton.setBackground(Color.lightGray);
        resetButton.setBorder(BorderFactory.createEtchedBorder());
        upperPanel.add(aknakSzamaLabel, BorderLayout.WEST);
        aknakSzamaLabel.setForeground(Color.red);
        ui.setLayout(new GridLayout(m,sz,0,0));
        this.setJMenuBar(jmb);
        jmb.add(jatekMenu);
        JMenuItem ujjatek= new JMenuItem("Új játék");
        JMenuItem kzd= new JMenuItem("Kezdő");
        JMenuItem hld= new JMenuItem("Haladó");
        JMenuItem mstr= new JMenuItem("Mester");
        JMenuItem rdmny= new JMenuItem("Legjobb idők");
        JMenuItem exit= new JMenuItem("Kilépés");
        jatekMenu.add(ujjatek);
        ujjatek.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        } );
        jatekMenu.addSeparator();
        jatekMenu.add(kzd);
        kzd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	rstKezdo();
            }
        } );
        jatekMenu.add(hld);
        hld.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	rstHalado();
            }
        } );
        jatekMenu.add(mstr);
        mstr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	rstMester();
            }
        } );
        jatekMenu.addSeparator();
        jatekMenu.add(rdmny);
        rdmny.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JatekosFrame jf =new JatekosFrame();
                jf.setVisible(true);
        	}
        });
        jatekMenu.addSeparator();
        jatekMenu.add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        } );
        this.add(upperPanel, BorderLayout.NORTH);
        this.add(ui, BorderLayout.CENTER);
        
         /**
          * Ha a reset gombra kattintunk, akkor új játékot kezdünk
          */
         
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        } );
        for(int i = 0; i<this.m; i++) {
            for(int j=0; j<this.sz; j++) {
                palya[i][j]= new Mezo();
                JPanel jp = new JPanel(new GridLayout());
                jp.add(palya[i][j]);
                palya[i][j].addMouseListener(this);
                ui.add(jp);
            }
        }
        
    }
    /**
     * A függvény újraindítja a játékot, de kezdő módban.
     */
    public void rstKezdo() {
    	m=9;
    	sz=9;
    	a=10;
    	szint="kezdő";
    	Jatek j = new Jatek(m,sz,a,szint);
        j.setVisible(true);
        Thread t = new Thread(j);
        t.run();
        this.dispatchEvent(new WindowEvent(Jatek.this, WindowEvent.WINDOW_CLOSING));
    }
    /**
     * A függvény újraindítja a játékot, de haladó módban.
     */
    public void rstHalado() {
    	m=16;
    	sz=16;
    	a=40;
    	szint="haladó";
    	Jatek j = new Jatek(m,sz,a,szint);
        j.setVisible(true);
        Thread t = new Thread(j);
        t.run();
        this.dispatchEvent(new WindowEvent(Jatek.this, WindowEvent.WINDOW_CLOSING));
    }
    /**
     * A függvény újraindítja a játékot, de mester módban.
     */
    public void rstMester() {
    	m=16;
    	sz=30;
    	a=99;
    	szint="mester";
    	Jatek j = new Jatek(m,sz,a,szint);
        j.setVisible(true);
        Thread t = new Thread(j);
        t.run();
        this.dispatchEvent(new WindowEvent(Jatek.this, WindowEvent.WINDOW_CLOSING));
    }
    /**
     * A játék kezdetekor hívjuk meg a run függvényt, ez hajta végre azokat a függvényeket, amiket a játék indításakor szükséges indítani. Inicializálja a timert is, amit az első mező felfedésénél indít el a program. 
     */
    public void run() {
        mineSet();
        nmineCount();
        tmr= new Timer(1000, this);
        tmr.setInitialDelay(0);
        
    } 
    /**
     * A függvény újraindítja a játékot.
     */
    public void reset() {
        Jatek j = new Jatek(m,sz,a,szint);
        j.setVisible(true);
        Thread t = new Thread(j);
    	t.run();
        this.dispatchEvent(new WindowEvent(Jatek.this, WindowEvent.WINDOW_CLOSING));
    	
    }
    /**
     * Elhelyezzük az aknákat véletlenszerűen a mezőkön.
     */
    public void mineSet() {
       int i=0;
        while(i<a) {
        	int x=(int)(Math.random()*((m-1)-0+1)+0);
        	int y=(int)(Math.random()*((sz-1)-0+1)+0);
        	if(!palya[x][y].getMine()) {
        		palya[x][y].setMine();
        		i++;
        	}
        }
    }
    /**
     * Megszámoljuk a szomszédos aknákat minden cellánál, ami alatt nem akna van.
     */
    public void nmineCount() {
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < sz; j++) {
                if(!palya[i][j].getMine()) {
                    if(m-1 > i && palya[i+1][j].getMine()) {//Jobb oldalt mellette
                        palya[i][j].addNmines();
                    }
                    if(m-1 > i && sz-1 > j && palya[i+1][j+1].getMine()) {//Alatta jobb oldalt
                        palya[i][j].addNmines();
                    }
                    if(sz-1 > j && palya[i][j+1].getMine()) {//Alatta
                        palya[i][j].addNmines();
                    }
                    if(i > 0 && sz -1 > j && palya[i-1][j+1].getMine()) {//Alatta bal oldalt
                        palya[i][j].addNmines();
                    }
                    if(i > 0 && palya[i-1][j].getMine()) {//Bal oldalt mellette
                        palya[i][j].addNmines();
                    }
                    if(i > 0 && j > 0 && palya[i-1][j-1].getMine()) { //Bal oldalt felette
                        palya[i][j].addNmines();
                    }
                    if(j > 0 && palya[i][j-1].getMine()) {//Felette
                        palya[i][j].addNmines();
                    }
                    if(m-1 > i && j > 0 && palya[i+1][j-1].getMine()) {//jobb oldalt felette
                        palya[i][j].addNmines();
                    }

                }
            }
        }
    }
    /**
     * Ha olyan mezőre kattintunk, aminek nincs akna a szomszédságában, akkor rekurzívan felfedi a mezőket körülötte minden irányban, és akkor hagyja abba a függvény a felfedést a pályán egy adott irányban, ha a mező széléhez érkezik, vagy olyan mezőt fed fel, aminek van akna a szomszédságában. 
     * @param x - a sor amiben az a gomb van, amire kattintottunk
     * @param y - az oszlop, amiben az a gomb van, amire kattintottunk 
     */
    public void rekurziv(int x, int y) {
		if(palya[x][y].getNmines()== 0) 
			palya[x][y].setText("");
		if(m-1>x) { //Jobb oldalt mellette
			if(!palya[x+1][y].getMine() && palya[x+1][y].isEnabled()) {
				palya[x+1][y].setText(String.valueOf(palya[x+1][y].getNmines()));
				palya[x+1][y].setEnabled(false);
				palya[x+1][y].setFelfedett();
				palya[x+1][y].setBackground(Color.white);
				if(palya[x+1][y].getNmines()== 0)
					rekurziv(x+1, y);
			}
		}
		if(sz-1>y && m-1>x) { //Jobb oldalt alatta
			if(!palya[x+1][y+1].getMine() && palya[x+1][y+1].isEnabled()) {
				palya[x+1][y+1].setText(String.valueOf(palya[x+1][y+1].getNmines()));
				palya[x+1][y+1].setEnabled(false);
				palya[x+1][y+1].setFelfedett();
				palya[x+1][y+1].setBackground(Color.white);
				if(palya[x+1][y+1].getNmines()== 0)
					rekurziv(x+1, y+1);
			}
		}
		if(sz-1>y) { //Alatta
			if(!palya[x][y+1].getMine() && palya[x][y+1].isEnabled()) {
				palya[x][y+1].setText(String.valueOf(palya[x][y+1].getNmines()));
				palya[x][y+1].setEnabled(false);
				palya[x][y+1].setFelfedett();
				palya[x][y+1].setBackground(Color.white);
				if(palya[x][y+1].getNmines()== 0)
					rekurziv(x, y+1);
			}
		}
		if(sz-1>y && x>0) { //Bal oldalt alatta
			if(!palya[x-1][y+1].getMine() && palya[x-1][y+1].isEnabled()) {
				palya[x-1][y+1].setText(String.valueOf(palya[x-1][y+1].getNmines()));
				palya[x-1][y+1].setEnabled(false);
				palya[x-1][y+1].setFelfedett();
				palya[x-1][y+1].setBackground(Color.white);
				if(palya[x-1][y+1].getNmines()== 0)
					rekurziv(x-1, y+1);
			}
		}
		if(x>0) { //Bal oldalt mellette
			if(!palya[x-1][y].getMine() && palya[x-1][y].isEnabled()) {
				palya[x-1][y].setText(String.valueOf(palya[x-1][y].getNmines()));
				palya[x-1][y].setEnabled(false);
				palya[x-1][y].setFelfedett();
				palya[x-1][y].setBackground(Color.white);
				if(palya[x-1][y].getNmines()== 0)
					rekurziv(x-1, y);
			}
		}
		if(y>0 && x>0) { //Bal oldalt felette
			if(!palya[x-1][y-1].getMine() && palya[x-1][y-1].isEnabled()) {
				palya[x-1][y-1].setText(String.valueOf(palya[x-1][y-1].getNmines()));
				palya[x-1][y-1].setEnabled(false);
				palya[x-1][y-1].setFelfedett();
				palya[x-1][y-1].setBackground(Color.white);
				if(palya[x-1][y-1].getNmines()== 0)
					rekurziv(x-1, y-1);
			}
		}
		if(y>0) { //Felette
			if(!palya[x][y-1].getMine()&& palya[x][y-1].isEnabled()) {
				palya[x][y-1].setText(String.valueOf(palya[x][y-1].getNmines()));
				palya[x][y-1].setEnabled(false);
				palya[x][y-1].setFelfedett();
				palya[x][y-1].setBackground(Color.white);
				if(palya[x][y-1].getNmines()== 0)
					rekurziv(x, y-1);
			}
		}
		if(y>0 && m-1>x) { //Jobb oldalt felette
			if(!palya[x+1][y-1].getMine() && palya[x+1][y-1].isEnabled()) {
				palya[x+1][y-1].setText(String.valueOf(palya[x+1][y-1].getNmines()));
				palya[x+1][y-1].setEnabled(false);
				palya[x+1][y-1].setFelfedett();
				palya[x+1][y-1].setBackground(Color.white);
				if(palya[x+1][y-1].getNmines()== 0)
					rekurziv(x+1, y-1);
			}
		}
		
		
		return;
	}
   
    /**
     * A játék végén hívódik meg a függvény. Ha a játékos megnyerte a játékot, bekéri a nevét és elmenti az eredményét az eredménylistába. Ha aknára kattint a játékos, akkor azt csak egy felugró ablakkal jelzi a játékosnak, de ekkor nem kér nevet a program, és nem menti el aze eredményt az eredménylistába.
     * @param vegeremeny - "igen"vagy "nem" az értéke az alapján, hogy a játékos megnyerte -e a játékot.
     */
    public void vege(String kimenetel) {
    	 tmr.stop();
    	 if(kimenetel.equals("igen")) {
    		 JOptionPane.showMessageDialog(null,"Sikeresen kikerülted az összes aknát!", "Nyertél", JOptionPane.INFORMATION_MESSAGE);
    		 JFrame nevbekeres = new JFrame("Pontszám");
             nevbekeres.setLayout(new GridLayout(2,2));
             nevbekeres.setSize(300,200);
             nevbekeres.setVisible(true);
             nevbekeres.setResizable(false);
             JTextField jtf = new JTextField(20);
             JLabel addmeg = new JLabel("Add meg a neved:");
             JButton mentesGomb = new JButton("Mentés");
             JPanel Panel = new JPanel(new FlowLayout());
             Panel.add(addmeg);
             Panel.add(jtf);
             Panel.add(mentesGomb);
             nevbekeres.add(Panel);
             mentesGomb.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     JatekosFrame sf = new JatekosFrame();
                     JatekosData d =sf.getData();
                     sf.setVisible(false);
                     d.addJatekos(jtf.getText(), szint, ido);
                     try {
                         ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("eredmenyek.dat"));
                         d.listaz();
                         oos.writeObject(d.getLista());
                         oos.close();
                     } catch(Exception ex) {
                         ex.printStackTrace();
                     }
                     sf.dispose();
                     nevbekeres.dispose();
                     Jatek.this.dispose();
                 }
             });
         }
         else {
             JOptionPane.showMessageDialog(null,"Aknára léptél, felrobbantál! BUMM!!", "Vesztettél", JOptionPane.INFORMATION_MESSAGE);
             Jatek.this.dispose();
         }
		 
    	 
    	 
    }
    /**
     * Ellenőrzi, hogy felfedtünk -e minden olyan területet, ahol nincs akna.
     */
    public void isThereAnyMines() {
        int felfedetlen=0;
        for(int i=0; m>i; i++) {
            for(int j=0;sz>j; j++) {
                if(!palya[i][j].getFelfedett()) {
                    felfedetlen++;
                }
            }
        }
        if(felfedetlen==a) {
            this.setEnabled(false);
            vege("igen");

        }
    }
    /**
     * Ha bal egérgombbal kattintunk egy mezőre, akkor az felfedődik, míg ha jobb egérgombbal, akkor megjelölünk vele egy mezőt.
     * Ez a függvény végrehajtja ezeket attól függően, hogy melyik egérgombot nyomtuk le.
     * @param e - Egérgombbal kattintás (jobb vagy bal)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)) {
        	tmr.start();
            for(int i = 0; m > i; i++) {
                for(int j = 0; sz > j; j++) {
                    if(e.getSource().equals(palya[i][j])&&palya[i][j].isEnabled()) {
                        palya[i][j].setEnabled(false);
                        palya[i][j].setFelfedett();
                        palya[i][j].setBackground(Color.white);
                        if(palya[i][j].getNmines()==0) {
                        	rekurziv(i,j);
                        }
                        if(palya[i][j].getMine()) {
                            vege("nem");
                        }
                        else if(palya[i][j].getNmines()!=0) {
                        			palya[i][j].setText(String.valueOf(palya[i][j].getNmines()));
                        			isThereAnyMines();
                        }
                        
                    }
                }
            }
        }
        else if(SwingUtilities.isRightMouseButton(e)) {
            for(int i=0; m>i; i++) {
                for(int j=0;sz>j; j++) {
                    if(e.getSource().equals(palya[i][j])) {
                        if(palya[i][j].getFlagged()) {
                                palya[i][j].setNothing();
                                palya[i][j].setText("");
                                palya[i][j].setEnabled(true);
                                palya[i][j].setIcon(null);
                                if(a>aknakSzama) {
                                    aknakSzama++;
                                }
                        } else if(palya[i][j].isEnabled()&& aknakSzama>0) {
                        	palya[i][j].setEnabled(false);
                            palya[i][j].setFlagged();
                            palya[i][j].setIcon(flag);
                            aknakSzama--;
                        }
                    }
                }
            }
            aknakSzamaLabel.setText(String.valueOf(aknakSzama));
        }
    }

    /**
     * A számláló léptetését intézi.
     *  @param ae - A késleltetés által kiváltott esemény 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        ido++;
        timLabel.setText(Integer.toString(ido));
    }
    /**
     * A mouseClicked függvény újradefiniálása miatt újra kellett definiálnom ezt a függvényt is. Nem csinál semmit jelen állapotban.
     * @param me - esemény
     */
    @Override
    public void mouseExited(MouseEvent me) {}
    /**
     * A mouseClicked függvény újradefiniálása miatt újra kellett definiálnom ezt a függgvényt is. Nem csinál semmit jelen állapotban.
     * @param me - esemény
     */
    @Override
    public void mousePressed(MouseEvent me) {}
    /**
     * A mouseClicked függvény újradefiniálása miatt újra kellett definiálnom ezt a függvényt is. Nem csinál semmit jelen állapotban.
     * @param me - esemény
     */
    @Override
    public void mouseReleased(MouseEvent me) {}
    /**
     * A mouseClicked függvény újradefiniálása miatt újra kellett definiálnom ezt a függvényt is. Nem csinál semmit jelen állapotban.
     * @param me - esemény
     */
    @Override
    public void mouseEntered(MouseEvent me) {}
    
    
}