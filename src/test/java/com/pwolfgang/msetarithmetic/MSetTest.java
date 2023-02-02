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

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
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
        var result = oneOneTwo.toIntegerString();
        System.out.println(result);
        assertEquals("[1 1 2]", result);
        System.out.println(oneOneTwo.asPolyNumber());
    }
    
    @Test 
    public void testPoly2() {
        System.out.println("testPoly2");
        var mSetOf13 = MSet.of(MSet.of(13));
        assertEquals("[13]", mSetOf13.toIntegerString());
        printIt("13", mSetOf13);
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
        printIt("test1", test1);
        assertEquals("[[3 5 11]]", test2.toIntegerString());
        printIt("test2", test1);
        assertEquals("1", test3.toIntegerString());
        assertEquals("0", test4.toIntegerString());
        printIt("test3", test1);
        printIt("test4", test1);
    }
    
    @Test
    public void testAdd() {
        System.out.println("testAdd");
        var a = MSet.of(MSet.of(MSet.of(4)), MSet.of(MSet.of(3)));
        var b = MSet.of(MSet.of(MSet.of(1),MSet.of(1),MSet.of(2)),MSet.of(MSet.of(4)), MSet.of(0));
        var c = MSet.of(MSet.of(4),MSet.of(MSet.of(1),MSet.of(2),MSet.of(1)));
        System.out.printf("a: %s%n", a.toIntegerString());
        System.out.printf("b: %s%n", b.toIntegerString());
        System.out.printf("c: %s%n", c.toIntegerString());
        System.out.printf("a: %s%n", a.asPolyNumber());
        System.out.printf("b: %s%n", b.asPolyNumber());
        System.out.printf("c: %s%n", c.asPolyNumber());
        var sum = MSet.add(a, b, c);
        printIt("a", a);
        printIt("b", b);
        printIt("C", c);
        printIt("a+b+c",sum);
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
    }
    
    @Test
    public void testMulNat() {
        System.out.println("testMulNat");
        var x = MSet.of(2);
        var y = MSet.of(3);
        var p = MSet.mul(x,y);
        assertEquals("6", p.toIntegerString());
    }
    
    @Test
    public void testMulPoly() {
        System.out.println("testMulPoly");
        var x = MSet.of(MSet.of(2), MSet.of(3));
        var y = MSet.of(MSet.of(1), MSet.of(1), MSet.of(0));
        var z = MSet.of(MSet.of(3),MSet.of(3),MSet.of(2),MSet.of(4),MSet.of(4),MSet.of(3));
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
        System.out.printf("e: %s%n", e.toIntegerString());
        System.out.printf("r: %s%n", r.toIntegerString());
        assertEquals(e.toIntegerString(),r.toIntegerString());     
    }
    
    @Test
    public void testMulMulti() {
        System.out.println("testMulMulti");
        var x = MSet.of(MSet.of(MSet.of(0), MSet.of(0), MSet.of(2)),MSet.of(MSet.of(3),MSet.of(8)));
        var y = MSet.of(MSet.of(MSet.of(11)),MSet.of(2),MSet.of(MSet.of(9)));
        System.out.printf("x: %s%n", x.toIntegerString());
        System.out.printf("y: %s%n", y.toIntegerString());
        System.out.printf("x * y: %s%n", MSet.mul(x,y).toIntegerString());
    }
    
    @Test
    public void testMulMulti2() {
        System.out.println("testMulMulti2");
        var x = MSet.of(MSet.of(MSet.of(2)),MSet.of(3));
        var y = MSet.of(MSet.of(MSet.of(4)),MSet.of(MSet.of(4)));
        var z = MSet.of(MSet.of(MSet.of(1),MSet.of(6)),MSet.of(0));
        var p = MSet.mul(x,y,z);
        System.out.printf("x: %s%n", x.toIntegerString());
        System.out.printf("y: %s%n", y.toIntegerString());
        System.out.printf("x: %s%n", z.toIntegerString());
        System.out.printf("p: %s%n", p.toIntegerString());
    }
    
    @Test
    public void testCrt() {
        System.out.println("testCrt");
        var a = MSet.of(MSet.of(MSet.of(1),MSet.of(2)),MSet.of(4));
        var b = MSet.of(MSet.of(0),MSet.of(MSet.of(0),MSet.of(3)));
        System.out.printf("a: %s%n", a.toIntegerString());
        System.out.printf("b: %s%n", b.toIntegerString());
        System.out.printf("a^b: %s%n", MSet.crt(a,b).toIntegerString());
    }
    
    @Test
    public void testCtr2() {
        System.out.println("testCrt2");
        var a = MSet.of(MSet.of(MSet.of(1),MSet.of(MSet.of(2))),MSet.of(MSet.of(3)));
        var b = MSet.of(MSet.of(2),MSet.of(MSet.of(1),MSet.of(3)));
        System.out.printf("a: %s%n", a.toIntegerString());
        System.out.printf("b: %s%n", b.toIntegerString());
        System.out.printf("a^b: %s%n", MSet.crt(a,b).toIntegerString());
    }
    
    @Test
    public void testTree() {
        System.out.println("test Tree");
        MSet a = MSet.of(MSet.of(0),MSet.of(0),MSet.of(2),MSet.of(MSet.of(1)),MSet.of(MSet.of(1)));
        System.out.println(a.toString());
        System.out.println(a.toStringWithHeight());
        System.out.println(a.toIntegerString());
        System.out.printf("Z(a): %S%n", a.Z().toString());
        System.out.printf("Z(a): %S%n", a.Z().toIntegerString());
        System.out.printf("N(a): %S%n", a.N().toString());
        System.out.printf("N(a): %S%n", a.N().toIntegerString());
        System.out.printf("P(a): %S%n", a.P().toString());
        System.out.printf("P(a): %S%n", a.P().toIntegerString());
        System.out.printf("M(a): %S%n", a.M().toString());
        System.out.printf("M(a): %S%n", a.M().toIntegerString());
        System.out.println(a.asPolyNumber());
    }
    
    
    @Test
    public void testTree2() {
        System.out.println("test tree 2");
        MSet a = MSet.of(MSet.of(1),MSet.of(MSet.of(1)),MSet.of(0));
        MSet b = MSet.of(MSet.of(0),MSet.of(2));
        System.out.printf("a: %s%n", a.toString());
        System.out.printf("a: %s%n", a.toIntegerString());
        System.out.printf("a: %s%n", a.asPolyNumber());
        System.out.printf("b: %s%n", b.toString());
        System.out.printf("b: %s%n", b.toIntegerString());
        System.out.printf("b: %s%n", b.asPolyNumber());
        System.out.printf("a+b: %s%n", MSet.add(a,b).toString());
        System.out.printf("a+b: %s%n", MSet.add(a,b).toIntegerString());
        System.out.printf("a+b: %s%n", MSet.add(a,b).asPolyNumber());
        System.out.printf("a×b: %s%n", MSet.mul(a,b).toString());
        System.out.printf("a×b: %s%n", MSet.mul(a,b).toIntegerString());
        System.out.printf("a×b: %s%n", MSet.mul(a,b).asPolyNumber());
        System.out.printf("a^b: %s%n", MSet.crt(a,b).toString());
        System.out.printf("a^b: %s%n", MSet.crt(a,b).toIntegerString());
        System.out.printf("a^b: %s%n", MSet.crt(a,b).asPolyNumber());
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
