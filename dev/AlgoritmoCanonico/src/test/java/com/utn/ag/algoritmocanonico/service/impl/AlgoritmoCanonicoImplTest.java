package com.utn.ag.algoritmocanonico.service.impl;

import org.junit.Test;

public class AlgoritmoCanonicoImplTest {

	private static String GENOMA = "0000010000111000000";
	private AlgoritmoCanonicoImpl algoritmoCanonico = new AlgoritmoCanonicoImpl();

	@Test
	public void testMutarBit() {
		String nuevoGenoma = algoritmoCanonico.mutarBit(GENOMA);
		org.junit.Assert.assertFalse(nuevoGenoma.equals(GENOMA));
	}
}
