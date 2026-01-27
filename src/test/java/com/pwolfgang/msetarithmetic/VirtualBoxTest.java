/*
 * Copyright (C) 2026 Paul
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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author Paul
 */
public class VirtualBoxTest {
    
    void printIt(String s, MSet mSet) {
        System.out.printf("%s: %s%n", s, mSet.toString());
        System.out.printf("%s: %s%n", s, mSet.toIntegerString());
        System.out.printf("%s: %s%n", s, mSet.asPolyNumber());
    }

    
    public VirtualBoxTest() {
    }

    /**
     * Test of toString method, of class VirtualBox.
     */
    @Test
    public void testToString() {
        System.out.println("\n\nTesting toString");
        MSet a = MSet.parse("[0 1 1 3]");
        MSet b = MSet.parse("[0 0 2 1]");
        MSet c = MSet.parse("[0 1 1 2 3 5]");
        MSet d = MSet.parse("[0 0 1 2 2 5]");
        var U = new VirtualBox(a,b);
        var V = new VirtualBox(c,d);
        System.out.printf("%s = %s\n", "A \u2296 B", U.toString());
        System.out.printf("%s = %s\n", "C \u2296 D", V.toString());
        assertEquals("([0 1 1 3]⊖[0 0 1 2])", U.toString());
        assertEquals("([0 1 1 2 3 5]⊖[0 0 1 2 2 5])", V.toString());
    }
    
    @Test
    public void testEquals() {
        System.out.println("\n\nTesting equals");
        var x = new VirtualBox(MSet.of(4), MSet.of(6));
        var y = new VirtualBox(MSet.of(5), MSet.of(7));
        System.out.printf("x: %s\n", x);
        System.out.printf("y: %s\n", y);
        assertEquals(x,x);
        assertEquals(x,y);
        assertEquals(y,x);       
    }
    
    @Test
    public void testAdd() {
        System.out.println("\n\nTesting add");
        var x = new VirtualBox(MSet.of(4),MSet.of(6));
        var y = new VirtualBox(MSet.of(2),MSet.of(0));
        var sum = x.add(y);
        System.out.printf("x: %s\n", x);
        System.out.printf("y: %s\n", y);
        System.out.printf("sum: %s%n", sum);
        assertEquals(new VirtualBox(MSet.of(0),MSet.of(0)), sum);
    }
    
    @Test
    public void testSub() {
        System.out.println("\n\nTesting sub");
        MSet a = MSet.parse("[0 1 1 3]");
        MSet b = MSet.parse("[0 0 2 1]");
        MSet c = MSet.parse("[0 1 1 2 3 5]");
        MSet d = MSet.parse("[0 0 1 2 2 5]");
        MSet e = MSet.parse("[3 [4]]");
        MSet f = MSet.parse("[0 1]");
        var U = new VirtualBox(a,b);
        var V = new VirtualBox(c,d);
        var W = new VirtualBox(e,f);
        System.out.printf("U: %s\n", U);
        System.out.printf("V: %s\n", V);
        System.out.printf("W: %s\n", W);
        assertEquals(U.add(V.sub(W)), U.add(V).sub(W));
        assertEquals(U.sub(V.add(W)), U.sub(V).sub(W));
        assertEquals(U.sub(V.sub(W)), U.sub(V).add(W));
        assertEquals(U.add(V).neg(), U.neg().add(V.neg()));
        assertEquals(U.sub(V).neg(), V.sub(U));
    }
    
    @Test
    public void testMul() {
        System.out.println("\n\nTest mul");
        var A = new VirtualBox(MSet.of(0), MSet.of(3));
        var B = new VirtualBox(MSet.of(0), MSet.of(2));
        var expected = new VirtualBox(MSet.of(6),MSet.of(0));
        System.out.printf("A: %s%n", A);
        System.out.printf("B: %s\n", B);
        System.out.printf("A × B: %s\n", A.mul(B).toString());
        assertEquals(expected, A.mul(B));
    }
    
}
