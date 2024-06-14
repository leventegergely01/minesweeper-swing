import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * Ez a kezdőképernyő osztálya.
 * @author Gergely Levente Máté - I15OZJ
 */

public class Start extends JFrame{
    /**
     * A Start osztály konstruktora. Létrehozza a kezdőképernyőt, és elhelyezi rajta a szükséges komponenseket.
     *
     */
    public Start(){
    	Beallitas b =new Beallitas();
    	JButton kezdes = new JButton("Játék kezdése");
        JButton beall = new JButton("Beállítás");
        JButton erlist = new JButton("Eredménylista");
        JPanel cimpanel=new JPanel();
        cimpanel.add(new JLabel("Aknakereső"));
        cimpanel.setBackground(Color.LIGHT_GRAY);
        this.add(cimpanel,BorderLayout.NORTH);
        this.setTitle("Aknakereső");
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setResizable(false);
        this.setVisible(true);
        this.setSize(400,200);
        GridBagConstraints megszor= new GridBagConstraints();
        megszor.gridwidth= GridBagConstraints.REMAINDER;
        megszor.fill = GridBagConstraints.BOTH;
        JPanel gombpanel = new JPanel(new GridBagLayout());
        gombpanel.setBackground(Color.LIGHT_GRAY);
        gombpanel.add(kezdes,megszor);
        kezdes.setBackground(Color.lightGray);
        kezdes.setBorder(BorderFactory.createEtchedBorder());
        gombpanel.add(beall,megszor);
        beall.setBackground(Color.lightGray);
        beall.setBorder(BorderFactory.createEtchedBorder());
        gombpanel.add(erlist,megszor);
        erlist.setBackground(Color.lightGray);
        erlist.setBorder(BorderFactory.createEtchedBorder());
        this.add(gombpanel,BorderLayout.CENTER);
        kezdes.addActionListener(new ActionListener() {
            @Override
            /**
             *  Ha a kezdőképernyőn az OK gombot nyomjuk meg, akkor kezdetét veszi a játék. 
             */
           
            public void actionPerformed(ActionEvent e) {
                Jatek j= new Jatek(b.getMag(),b.getSzel(),b.getAkna(),b.getSzint());
                j.setVisible(true);
                Thread t = new Thread(j);
                t.run();
            }
        });
        beall.addActionListener(new ActionListener() {
            @Override
             /**
              * Ha a kezdőképernyőn a 'Beállítás' gombot nyomjuk meg, akkor beállíthatjuk a pálya nehézségét.
              */
            public void actionPerformed(ActionEvent e) {
                b.setVisible(true);
            }
        });

        erlist.addActionListener(new ActionListener() {
            @Override
            /**
             * Ha az eredményeket szeretnénk megtekinteni, akkor az 'Eredménylista' gombra kattintva tekinthetjük meg azt.
             */
            public void actionPerformed(ActionEvent e) {
                JatekosFrame jf =new JatekosFrame();
                jf.setVisible(true);
            }
        });

    }
}
