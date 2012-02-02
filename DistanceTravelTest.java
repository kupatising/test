package edu.upenn.cis350.gpx;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DistanceTravelTest {

	GPXtrkpt zero;
	GPXtrkpt nPoleEast;
	GPXtrkpt nPoleWest;
	GPXtrkpt sPoleEast;
	GPXtrkpt sPoleWest;
	
	GPXtrkpt topLeft;
	GPXtrkpt topRight;
	GPXtrkpt bottomLeft;
	GPXtrkpt bottomRight;

	GPXtrkpt tooNorth;
	GPXtrkpt tooSouth;
	GPXtrkpt tooEast;
	GPXtrkpt tooWest;
	
	public DistanceTravelTest() {
		zero = new GPXtrkpt(0, 0, null);
		nPoleEast = new GPXtrkpt(90, 180, null);
		nPoleWest = new GPXtrkpt(90, -180, null);
		sPoleEast= new GPXtrkpt(-90, 180, null);
		sPoleWest = new GPXtrkpt(-90, -180, null);
				
		topLeft = new GPXtrkpt(45, 90, null);
		topRight = new GPXtrkpt(45, -90, null);
		bottomLeft = new GPXtrkpt(-45, 90, null);
		bottomRight = new GPXtrkpt(-45, -90, null);
		
		tooNorth = new GPXtrkpt(100, 0, null);
		tooSouth = new GPXtrkpt(-100, 0, null);
		tooEast= new GPXtrkpt(0, 200, null);
		tooWest = new GPXtrkpt(0, -200, null);
	}
	
	
	@Test
	public void testSimple() {
		ArrayList<GPXtrkpt> a1 = new ArrayList<GPXtrkpt>();
		a1.add(zero);
		a1.add(nPoleEast);
		
		ArrayList<GPXtrkpt> a2 = new ArrayList<GPXtrkpt>();
		a2.add(nPoleEast);
		a2.add(sPoleEast);
		
		
		GPXtrkseg s1 = new GPXtrkseg(a1);
		GPXtrkseg s2 = new GPXtrkseg(a2);
		
		ArrayList<GPXtrkseg> as1 = new ArrayList<GPXtrkseg>();
		as1.add(s1);
		ArrayList<GPXtrkseg> as2 = new ArrayList<GPXtrkseg>();
		as2.add(s2);
		ArrayList<GPXtrkseg> as3 = new ArrayList<GPXtrkseg>();
		as3.add(s1);
		as3.add(s2);
		
		assertEquals(201.2461, GPXcalculator.calculateDistanceTraveled(new GPXtrk("Test", as1)), 0.01);
		assertEquals(90, GPXcalculator.calculateDistanceTraveled(new GPXtrk("Test", as2)), 0.01);
		assertEquals(291.2461, GPXcalculator.calculateDistanceTraveled(new GPXtrk("Test", as3)), 0.01);	
	}
	
	@Test
	public void testComplex() {
		ArrayList<GPXtrkpt> a1 = new ArrayList<GPXtrkpt>();
		a1.add(topLeft);
		a1.add(topRight);
		a1.add(bottomLeft);
		a1.add(bottomRight);
		
		ArrayList<GPXtrkpt> a2 = new ArrayList<GPXtrkpt>();
		a2.add(nPoleEast);
		a2.add(nPoleWest);
		
		ArrayList<GPXtrkpt> a3 = new ArrayList<GPXtrkpt>();
		a3.add(nPoleEast);
		a3.add(nPoleWest);
		a3.add(sPoleWest);
		a3.add(zero);
		a3.add(sPoleWest);
		
		GPXtrkseg s1 = new GPXtrkseg(a1);
		GPXtrkseg s2 = new GPXtrkseg(a2);
		GPXtrkseg s3 = new GPXtrkseg(a3);
		
		ArrayList<GPXtrkseg> as1 = new ArrayList<GPXtrkseg>();
		as1.add(s1);
		ArrayList<GPXtrkseg> as2 = new ArrayList<GPXtrkseg>();
		as2.add(s2);
		ArrayList<GPXtrkseg> as3 = new ArrayList<GPXtrkseg>();
		as3.add(s3);
		ArrayList<GPXtrkseg> as4 = new ArrayList<GPXtrkseg>();
		as4.add(s3);
		as4.add(s2);
		as4.add(s1);
		
		assertEquals(561.24612, GPXcalculator.calculateDistanceTraveled(new GPXtrk("Test", as1)), 0.01);
		assertEquals(0, GPXcalculator.calculateDistanceTraveled(new GPXtrk("Test", as2)), 0.01);
		assertEquals(381.2461, GPXcalculator.calculateDistanceTraveled(new GPXtrk("Test", as3)), 0.01);	
		assertEquals(942.49224, GPXcalculator.calculateDistanceTraveled(new GPXtrk("Test", as4)), 0.01);
	}
	
	@Test
	public void testNullGPXtrk() {
		assertEquals(-1, GPXcalculator.calculateDistanceTraveled(null));
	}

	@Test
	public void testNoGPXtrkseg() {
		GPXtrk g = new GPXtrk("Test", new ArrayList<GPXtrkseg>());
		assertEquals(-1, GPXcalculator.calculateDistanceTraveled(g));
		g =  new GPXtrk("Test", new ArrayList<GPXtrkseg>());
		assertEquals(-1, GPXcalculator.calculateDistanceTraveled(g));
	}
	
	
	@Test
	public void testNullGPXtrkseg() {
		ArrayList<GPXtrkpt> a1 = new ArrayList<GPXtrkpt>();
		a1.add(nPoleWest);
		a1.add(sPoleWest);
	
		GPXtrkseg s1 = new GPXtrkseg(a1);
		ArrayList<GPXtrkseg> as1 = new ArrayList<GPXtrkseg>();
		as1.add(s1);
		as1.add(null);
		
		assertEquals(90, GPXcalculator.calculateDistanceTraveled(new GPXtrk("Test", as1)), 0.01);	
	}
	
	
	@Test
	public void testInvalidtrkpt() {
		ArrayList<GPXtrkpt> a1 = new ArrayList<GPXtrkpt>();
		a1.add(zero);

		ArrayList<GPXtrkpt> a2 = new ArrayList<GPXtrkpt>();
		
		ArrayList<GPXtrkpt> a3 = new ArrayList<GPXtrkpt>();
		a3.add(null);
		
		ArrayList<GPXtrkpt> a4 = new ArrayList<GPXtrkpt>();
		a4.add(nPoleWest);
		a4.add(sPoleWest);
		
		GPXtrkseg s1 = new GPXtrkseg(a1);
		GPXtrkseg s2 = new GPXtrkseg(a2);
		GPXtrkseg s3 = new GPXtrkseg(a3);
		GPXtrkseg s4 = new GPXtrkseg(a4);
		
		ArrayList<GPXtrkseg> as1 = new ArrayList<GPXtrkseg>();
		as1.add(s1);
		as1.add(s4);
		ArrayList<GPXtrkseg> as2 = new ArrayList<GPXtrkseg>();
		as2.add(s2);
		as2.add(s4);
		ArrayList<GPXtrkseg> as3 = new ArrayList<GPXtrkseg>();
		as3.add(s3);
		as2.add(s4);

		assertEquals(90, GPXcalculator.calculateDistanceTraveled(new GPXtrk("Test", as1)), 0.01);
		assertEquals(90, GPXcalculator.calculateDistanceTraveled(new GPXtrk("Test", as2)), 0.01);
		assertEquals(90, GPXcalculator.calculateDistanceTraveled(new GPXtrk("Test", as3)), 0.01);	
	}
	
	@Test
	public void testOutofBounds() {
		ArrayList<GPXtrkpt> a1 = new ArrayList<GPXtrkpt>();
		a1.add(tooNorth);
		a1.add(zero);

		ArrayList<GPXtrkpt> a2 = new ArrayList<GPXtrkpt>();
		a2.add(nPoleEast);
		a2.add(tooSouth);
		
		ArrayList<GPXtrkpt> a3 = new ArrayList<GPXtrkpt>();
		a3.add(nPoleEast);
		a3.add(sPoleWest);
		
		ArrayList<GPXtrkpt> a4 = new ArrayList<GPXtrkpt>();
		a2.add(tooWest);
		a2.add(tooEast);
		
		GPXtrkseg s1 = new GPXtrkseg(a1);
		GPXtrkseg s2 = new GPXtrkseg(a2);
		GPXtrkseg s3 = new GPXtrkseg(a3);
		GPXtrkseg s4 = new GPXtrkseg(a4);
		
		ArrayList<GPXtrkseg> as1 = new ArrayList<GPXtrkseg>();
		as1.add(s1);
		ArrayList<GPXtrkseg> as2 = new ArrayList<GPXtrkseg>();
		as2.add(s2);
		ArrayList<GPXtrkseg> as3 = new ArrayList<GPXtrkseg>();
		as3.add(s3);
		as3.add(s4);
		ArrayList<GPXtrkseg> as4 = new ArrayList<GPXtrkseg>();
		as4.add(s3);
		as4.add(s2);
		as4.add(s1);
		as4.add(s4);
		
		assertEquals(0, GPXcalculator.calculateDistanceTraveled(new GPXtrk("Test", as1)), 0.01);
		assertEquals(0, GPXcalculator.calculateDistanceTraveled(new GPXtrk("Test", as2)), 0.01);
		assertEquals(90, GPXcalculator.calculateDistanceTraveled(new GPXtrk("Test", as3)), 0.01);	
		assertEquals(90, GPXcalculator.calculateDistanceTraveled(new GPXtrk("Test", as4)), 0.01);
	}

	
}
