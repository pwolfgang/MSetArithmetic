/*
 * Copyright (C) 2023 Paul Wolfgang <a href="mailto:paul@pwolfgang.com"></a>
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Paul Wolfgang <a href="mailto:paul@pwolfgang.com"></a>
 */
public class TestSymetricBinomials {
    
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
        String mSetToString = mSet.toString();
        String mSetToIntegerString = mSet.toIntegerString();
        String mSetAsPolyNumber = mSet.asPolyNumber();
        System.out.printf("%s: %s%n", s, mSetToString);
        System.out.printf("%s: %s%n", s, mSetToIntegerString);
        System.out.printf("%s: %s%n", s, mSetAsPolyNumber);
    }
    
    @Test
    public void testB2() {
        var b2 = MSet.B(2);
        var expected = MSet.parse("[2 -2]");
        printIt("B2", b2);
        printIt("B1_expected", expected);
        assertEquals(expected, b2);
    }
    
    @Test
    public void testB0() {
        var b0 = MSet.B(0);
        var expected = MSet.of(2);
        printIt("B0", b0);
        printIt("expected", expected);
        assertEquals(expected, b0);
    }
    
    @Test
    public void testMul() {
        var b3 = MSet.B(3);
        var b5 = MSet.B(5);
        var b3b5 = b3.mul(b5);
        var b2 = MSet.B(2);
        var b8 = MSet.B(8);
        var b2PlusB8 = b2.add(b8);
        printIt("b3", b3);
        printIt("b5", b5);
        printIt("b2", b2);
        printIt("b8", b8);
        printIt("b3b5", b3b5);
        printIt("b2+b8",b2PlusB8);
        assertEquals(b2PlusB8, b3b5);
    }
    
    @Test
    public void testSq() {
        var b4 = MSet.B(4);
        var b4Sq = b4.mul(b4);
        var b2PlusB8 = MSet.B(0).add(MSet.B(8));
        printIt("b4^2",b4Sq);
        assertEquals(b2PlusB8, b4Sq);
    }
    
        @Test
    public void testA2() {
        var a2 = MSet.A(2);
        var expected = MSet.parse("[2 -2\u1D43]");
        printIt("A2", a2);
        printIt("A2_expected", expected);
        assertEquals(expected, a2);
    }
    
    @Test
    public void testA0() {
        var a0 = MSet.A(0);
        var expected = MSet.of(0);
        printIt("A0", a0);
        printIt("expected", expected);
        assertEquals(expected, a0);
    }
    
    @Test
    public void testAmAn() {
        var Am = MSet.A(3);
        var An = MSet.A(5);
        var B2 = MSet.B(2);
        var B8 = MSet.B(8);
        var AmAn = Am.mul(An);
        var mBmmnpBmpm = B2.mul(MSet.of(-1)).add(B8);
        printIt("AmAn", AmAn);
        printIt("-Bn-m+Bn+m", mBmmnpBmpm);
        assertEquals(mBmmnpBmpm, AmAn);
    }
    
    @Test
    public void testAnSq() {
        var A3 = MSet.A(3);
        var A3Sq = A3.mul(A3);
        printIt("A3", A3);
        printIt("An3^2", A3Sq);
        assertEquals(MSet.parse("[0ᵃ 0ᵃ -6 6]"), A3Sq);
        var A4 = MSet.A(4);
        var A4Sq = A4.mul(A4);
        printIt("A4", A4);
        printIt("An4^2", A4Sq);
        assertEquals(MSet.parse("[0ᵃ 0ᵃ -8 8]"), A4Sq);
    }
    
    @Test
    public void testC() {
        var c0 = MSet.C(0);
        var c1 = MSet.C(1);
        var c2 = MSet.C(2);
        var c3 = MSet.C(3);
        var c4 = MSet.C(4);
        var c5 = MSet.C(5);
        printIt("c0", c0);
        printIt("c1", c1);
        printIt("c2", c2);
        printIt("c3", c3);
        printIt("c4", c4);
        printIt("c5", c5);
        assertEquals(MSet.parse("[0]"), c0);
        assertEquals(MSet.parse("[-1 1]"), c1);
        assertEquals(MSet.parse("[-2 0 2]"), c2);
        assertEquals(MSet.parse("[-3 -1 1 3]"), c3);
        assertEquals(MSet.parse("[-4 -2 0 2 4]"), c4);
        assertEquals(MSet.parse("[-5 -3 -1 1 3 5]"), c5);
    }

}
