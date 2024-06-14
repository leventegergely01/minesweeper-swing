import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Junitjatekos {
	@Test
	void Nevteszt() {
		Jatekos teszt=new Jatekos("Pisti","kezdő",100);
		String nev =teszt.getNev();
		assertEquals("Pisti",nev);
		
	}
	@Test
	void Szintteszt() {
		Jatekos teszt=new Jatekos("Pisti","kezdő",100);
		String szint =teszt.getSzint();
		assertEquals("kezdő",szint);	
	}
	@Test
	void Idoteszt() {
		Jatekos teszt=new Jatekos("Pisti","kezdő",100);
		int i =teszt.getIdo();
		assertEquals(100,i);
		
	}
	@Test
	void setNevTest(){
		Jatekos teszt=new Jatekos("Pisti","kezdő",100);
		teszt.setNev("Jóska");
		String s=teszt.getNev();
		assertEquals("Jóska",s);
	}
	@Test
	void setSzintTest(){
		Jatekos teszt=new Jatekos("Pisti","kezdő",100);
		teszt.setSzint("haladó");
		String s=teszt.getSzint();
		assertEquals("haladó",s);
	}
	@Test
	void setIdoTest(){
		Jatekos teszt=new Jatekos("Pisti","kezdő",100);
		teszt.setIdo(200);
		int i=teszt.getIdo();
		assertEquals(200,i);
	}
	
}
