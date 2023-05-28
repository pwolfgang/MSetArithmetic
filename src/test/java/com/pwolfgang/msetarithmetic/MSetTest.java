/*
 * Copyright (C) 2023 Paul Wolfgang <paul@pwolfgang.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.pwolfgang.msetarithmetic;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class MSetTest {
    
    public MSetTest() {
    }
    
    @BeforeEach
    public void init() {
        try {
            PrintStream p = new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8");
            System.setOut(p);          
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    void printIt(String s, MSet mSet) {
        System.out.printf("%s: %s%n", s, mSet.toString());
        System.out.printf("%s: %s%n", s, mSet.toIntegerString());
        System.out.printf("%s: %s%n", s, mSet.asPolyNumber());
    }

    
    @Test
    public void testCreateEmptySet() {
        System.out.println("testCreateEmptySet");
        var m = MSet.of();
        printIt("m", m);
        assertEquals(0, m.size());
        assertEquals("[]", m.toString());
        assertEquals("0", m.toIntegerString());
        assertEquals("0", m.asPolyNumber());
    }
    
    @Test
    public void testOne() {
        System.out.println("testOne");
        var zero = MSet.of();
        var one = MSet.of(zero);
        printIt("one", one);
        assertEquals(1, one.size());
        assertEquals("[[]]", one.toString());
        assertEquals("1", one.toIntegerString());
        assertEquals("1", one.asPolyNumber());
    }
    
    @Test
    public void testPoly() {
        System.out.println("testPoly");
        var zero = MSet.of();
        var two = MSet.of(MSet.of(), MSet.of());
        var oneOneTwo = MSet.of(MSet.of(zero), MSet.of(zero), two);
        printIt("oneOneTwo", oneOneTwo);
        assertEquals("[1 1 2]", oneOneTwo.toIntegerString());
    }
    
    @Test 
    public void testPoly2() {
        System.out.println("testPoly2");
        var mSetOf13 = MSet.of(MSet.of(13));
        printIt("13", mSetOf13);
        assertEquals("[13]", mSetOf13.toIntegerString());
    }
    
    
    @Test
    public void testMulti() {
        System.out.println("testMulti");
        var oneOne = MSet.of(MSet.of(1), MSet.of(1));
        var threeFiveEleven = MSet.of(MSet.of(3), MSet.of(5), MSet.of(11));
        var test1 = MSet.of(oneOne, oneOne);
        var test2 = MSet.of(threeFiveEleven);
        var test3 = MSet.of(MSet.of(0));
        var test4 = MSet.of(0);
        assertEquals("[[1 1] [1 1]]", test1.toIntegerString());
        printIt("2α₁²", test1);
        assertEquals("[[3 5 11]]", test2.toIntegerString());
        printIt("α₃α₅α₁₁", test2);
        assertEquals("1", test3.toIntegerString());
        assertEquals("0", test4.toIntegerString());
        printIt("1", test3);
        printIt("0", test4);
    }
    
    @Test
    public void testAdd() {
        System.out.println("testAdd");
        var a = MSet.of(MSet.of(MSet.of(4)), MSet.of(MSet.of(3)));
        var b = MSet.of(MSet.of(MSet.of(1),MSet.of(1),MSet.of(2)),MSet.of(MSet.of(4)), MSet.of(0));
        var c = MSet.of(MSet.of(4),MSet.of(MSet.of(1),MSet.of(2),MSet.of(1)));
        printIt("a", a);
        printIt("b", b);
        printIt("c", c);
        var sum = MSet.add(a, b, c);
        printIt("a+b+c",sum);
        var expected = MSet.parse("[0 4 [3] [4] [4] [1 1 2] [1 1 2]]");
        assertEquals(expected, sum);
    }
    
    @Test
    public void testAdd2() {
        System.out.println("testAdd2");
        var a = MSet.of(MSet.of(3),MSet.of(3),MSet.of(4));
        var b = MSet.of(0);
        var c = MSet.of(MSet.of(0));
        var d = MSet.of(MSet.of(3),MSet.of(7));
        var sum = MSet.add(a, b, c, d);
        printIt("a", a);
        printIt("b", b);
        printIt("C", c);
        printIt("d", d);
        printIt("a+b+c+d",sum);
        var expected = MSet.parse("[0 3 3 3 4 7]");
        assertEquals(expected, sum);
    }
    
    @Test
    public void testMulNat() {
        System.out.println("testMulNat");
        var x = MSet.of(2);
        var y = MSet.of(3);
        var p = MSet.mul(x,y);
        printIt("x", x);
        printIt("y", y);
        printIt("x × y", p);
        assertEquals("6", p.toIntegerString());
    }
    
    @Test
    public void testMulPoly() {
        System.out.println("testMulPoly");
        var x = MSet.of(MSet.of(2), MSet.of(3));
        var y = MSet.of(MSet.of(1), MSet.of(1), MSet.of(0));
        var z = MSet.of(MSet.of(3),MSet.of(3),MSet.of(2),MSet.of(4),MSet.of(4),MSet.of(3));
        printIt("x", x);
        printIt("y", y);
        printIt("x × y", MSet.mul(x,y));
        assertEquals(z.toIntegerString(),MSet.mul(x,y).toIntegerString());
    }
    
    @Test
    public void testMulPoly2() {
        System.out.println("testMulPoly2");
        var x = MSet.of(MSet.of(0), MSet.of(3));
        var y = MSet.of(MSet.of(1), MSet.of(4));
        var z = MSet.of(MSet.of(2), MSet.of(7));
        var e = MSet.of(MSet.of(3), MSet.of(8), MSet.of(6), MSet.of(11), MSet.of(6), MSet.of(11), MSet.of(9), MSet.of(14));
        var r = MSet.mul(x, y, z);
        printIt("x", x);
        printIt("y", y);
        printIt("z", z);
        printIt("x × y × z", r);
        assertEquals(e, r);     
    }
    
    @Test
    public void testMulMulti() {
        System.out.println("testMulMulti");
        var x = MSet.of(MSet.of(MSet.of(0), MSet.of(0), MSet.of(2)),MSet.of(MSet.of(3),MSet.of(8)));
        var y = MSet.of(MSet.of(MSet.of(11)),MSet.of(2),MSet.of(MSet.of(9)));
        printIt("x", x);
        printIt("y", y);
        var r = MSet.mul(x, y);
        var e = MSet.parse("[[3 8 11] [3 8 9] [0 0 3 8] [0 0 2 11] [0 0 2 9] [0 0 0 0 2]]");
        printIt("x × y", r);
        assertEquals(e, r);
   }
    
    @Test
    public void testMulMulti2() {
        System.out.println("testMulMulti2");
        var x = MSet.of(MSet.of(MSet.of(2)),MSet.of(3));
        var y = MSet.of(MSet.of(MSet.of(4)),MSet.of(MSet.of(4)));
        var z = MSet.of(MSet.of(MSet.of(1),MSet.of(6)),MSet.of(0));
        var r = MSet.mul(x,y,z);
        var e = MSet.parse("[[2 4] [2 4] [0 0 0 4] [0 0 0 4] [1 2 4 6] [1 2 4 6] [0 0 0 1 4 6] [0 0 0 1 4 6]]");
        printIt("x", x);
        printIt("y", y);
        printIt("x", z);
        printIt("x × y × z", r);
        assertEquals(e, r);
    }
    
    @Test
    public void testCrt() {
        System.out.println("testCrt");
        var a = MSet.of(MSet.of(MSet.of(1),MSet.of(2)),MSet.of(4));
        var b = MSet.of(MSet.of(0),MSet.of(MSet.of(0),MSet.of(3)));
        var r = MSet.crt(a,b);
        var e = MSet.parse("[0 0 [1 2 4 5] [0 0 0 0 3 3 3 3]]");
        printIt("a", a);
        printIt("b", b);
        printIt("a ^ b", r);
        assertEquals(e, r);
    }
    
    @Test
    public void testCtr2() {
        System.out.println("testCrt2");
        var a = MSet.of(MSet.of(MSet.of(1),MSet.of(MSet.of(2))),MSet.of(MSet.of(3)));
        var b = MSet.of(MSet.of(2),MSet.of(MSet.of(1),MSet.of(3)));
        var r = MSet.crt(a,b);
        var e = MSet.parse("[[3 3] [4 6] [1 1 [2] [2]] [2 4 [0 2] [0 0 0 2]]]");
        printIt("a", a);
        printIt("b", b);
        printIt("a ^ b", r);
        assertEquals(e, r);
    }
    
    @Test
    public void testCrtNat() {
        System.out.println("testCrtNat");
        var two = MSet.of(2);
        var three = MSet.of(3);
        var r = MSet.crt(two, three);
        var e = MSet.parse("[[] [] [] [] [] []]");
        printIt("2", two);
        printIt("3", three);
        printIt("2 ^ 3", r);
        assertEquals(e, r);       
    }
    
    @Test
    public void testCrtEmptySets() {
        var x = new EmptyMSet();
        var y = new EmptyMSet();
        var z = x.crt(y);
        assertEquals(new EmptyMSet(), z);
    }
    
    @Test
    public void testTree() {
        System.out.println("test Tree");
        MSet a = MSet.of(MSet.of(0),MSet.of(0),MSet.of(2),MSet.of(MSet.of(1)),MSet.of(MSet.of(1)));
        var z = a.Z();
        var n = a.N();
        var p = a.P();
        var m = a.M();
        var zE = MSet.of(0);
        var nE = MSet.of(5);
        var pE = MSet.parse("[0 0 1 1 2]");
        var mE = MSet.parse("[0 0 2 [1] [1]]");
        printIt("a", a);
        printIt("Z(a)", z);
        printIt("N(a)", n);
        printIt("P(a)", p);
        printIt("M(a)", m);
        assertEquals(zE, z);
        assertEquals(nE, n);
        assertEquals(pE, p);
        assertEquals(mE, m);
    }
    
    
    @Test
    public void testTree2() {
        System.out.println("test tree 2");
        MSet a = MSet.of(MSet.of(1),MSet.of(MSet.of(1)),MSet.of(0));
        MSet b = MSet.of(MSet.of(0),MSet.of(2));
        printIt("a", a);
        printIt("b", b);
        var aPlusB = a.add(b);
        var aMulB = a.mul(b);
        var aCrtB = a.crt(b);
        var aPlusB_e = MSet.parse("[0 0 1 2 [1]]");
        var aMulB_e = MSet.parse("[0 1 2 3 [1] [0 0 1]]");
        var aCrtB_e = MSet.parse("[0 0 0 0 2 [1 1]]");
        assertEquals(aPlusB_e, aPlusB);
        assertEquals(aMulB_e, aMulB);
        assertEquals(aCrtB_e, aCrtB);
    }
    
    @Test
    public void testEquals() {
        System.out.println("Test Equals");
        var three_1 = MSet.of(3);
        var three_2 = MSet.of(3);
        var alphaSubOne_1 = MSet.of(MSet.of(MSet.of(1)));
        var alphaSubOne_2 = MSet.of(MSet.of(MSet.of(1)));
        var twoAlphaTwoSq_1 = MSet.of(MSet.of(MSet.of(MSet.of(2)),MSet.of(MSet.of(2))),MSet.of(MSet.of(MSet.of(2)),MSet.of(MSet.of(2))));
        var twoAlphaTwoSq_2 = MSet.of(MSet.of(MSet.of(MSet.of(2)),MSet.of(MSet.of(2))),MSet.of(MSet.of(MSet.of(2)),MSet.of(MSet.of(2))));
        System.out.printf("%s %s%n", "alpha sub one", alphaSubOne_1.toString());
        System.out.printf("%s %s%n", "alpha sub one", alphaSubOne_1.toIntegerString());
        System.out.printf("%s %s%n", "two alpha sub two squared", twoAlphaTwoSq_1.toString());
        System.out.printf("%s %s%n", "two alpha sub two squared", twoAlphaTwoSq_1.toIntegerString());
        assertEquals(three_1,three_2);
        assertEquals(alphaSubOne_1,alphaSubOne_2);
        assertEquals(twoAlphaTwoSq_1,twoAlphaTwoSq_2);
        assertNotEquals(alphaSubOne_1,twoAlphaTwoSq_1);
    }
    
    
}
