import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Junitmezo {

	@Test
	void Aknateszt() {
		Mezo m=new Mezo();
		m.setMine();
		Boolean b=m.getMine();
		assertEquals(true, b);
	}
	@Test
	void Flagteszt() {
		Mezo m=new Mezo();
		m.setFlagged();
		Boolean b=m.getFlagged();
		assertEquals(true, b);
		
	}
	@Test
	void Nmineteszt() {
		Mezo m=new Mezo();
		m.addNmines();
		m.addNmines();
		m.addNmines();
		int i= m.getNmines();
		assertEquals(3,i);
	}

}
