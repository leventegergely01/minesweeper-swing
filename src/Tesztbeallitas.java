import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Tesztbeallitas {

	@Test
	void testSetMag() {
		Beallitas b= new Beallitas();
		b.setMag(20);
		int i=b.getMag();
		assertEquals(20,i);
	}
	@Test
	void testSetSzel() {
		Beallitas b= new Beallitas();
		b.setSzel(20);
		int i=b.getSzel();
		assertEquals(20,i);
	}
	@Test
	void testSetAkna() {
		Beallitas b= new Beallitas();
		b.setAkna(20);
		int i=b.getAkna();
		assertEquals(20,i);
	}

}
