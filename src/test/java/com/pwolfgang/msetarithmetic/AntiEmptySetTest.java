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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class AntiEmptySetTest {
    
    MSet mSet;
    
    public AntiEmptySetTest() {
    }
    
    @BeforeEach
    public void init() {
        try {
            PrintStream p = new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8");
            System.setOut(p);          
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        mSet = new AntiEmptySet();
        
    }
    
    void printIt(String s, MSet mSet) {
        String mSetToString = mSet.toString();
        String mSetToIntegerString = mSet.toIntegerString();
        String mSetAsPolyNumber = mSet.asPolyNumber();
        System.out.printf("%s: %s%n", s, mSetToString);
        System.out.printf("%s: %s%n", s, mSetToIntegerString);
        System.out.printf("%s: %s%n", s, mSetAsPolyNumber);
        assertEquals(s, mSet.asPolyNumber());
    }

    @Test
    public void testSize() {
        assertEquals(0, mSet.size());
    }

    @Test
    public void testToString() {
        assertEquals("[]\u1D43", mSet.toString());
    }

    @Test
    public void testToStringWithHeight() {
        assertEquals("[]\u1D430", mSet.toStringWithHeight());
    }

    @Test
    public void testToIntegerString() {
        assertEquals("0\u1D43",mSet.toIntegerString());
    }


    @Test
    public void testAsPolyNumber() {
        printIt("0\u1D43", mSet);
    }
    
    @Test
    public void testAddNegative() {
        var five = MSet.of(5);
        var minusThree = MSet.of(-3);
        System.out.println(five.toString());
        System.out.println(minusThree.toString());
        var sum = MSet.add(five, minusThree);
        var two = MSet.of(2);
        assertEquals(two, sum);
        
    }
    
    @Test
    public void testAddNegative2() {
        var minusFive = MSet.of(-5);
        var three = MSet.of(3);
        System.out.println(minusFive.toString());
        System.out.println(minusFive.toIntegerString());
        System.out.println(three.toString());
        var sum = MSet.add(minusFive, three);
        var two = MSet.of(-2);
        assertEquals(two, sum);       
    }

    @Test
    public void testAddNegative3() {
        var minusFive = MSet.of(-5);
        var five = MSet.of(5);
        System.out.println(minusFive.toString());
        System.out.println(five.toString());
        var sum = MSet.add(minusFive, five);
        var zero = MSet.of(0);
        assertEquals(zero, sum);       
    }
    
    @Test
    public void testMinusOne() {
        var zero = MSet.of(0);
        var minusOne = MSet.of(-1);
        printIt("-1", minusOne);
        var one = MSet.of(1);
        printIt("1", one);
        System.out.println("1 + -1");
        printIt("0", MSet.add(one, minusOne));
        System.out.println("-1 × -1");
        var prod = MSet.mul(minusOne, minusOne);
        printIt("1", prod);
        System.out.println("-1 × 1");
        printIt("-1", MSet.mul(minusOne, one));
        System.out.println("1 ^ 0");
        printIt("0", MSet.crt(one, zero));
    }
    
    @Test
    public void testMultiTimesZero() {
        System.out.println("\n\n\nTest of M times 0");
        MSet a = MSet.of(MSet.of(0),MSet.of(0),MSet.of(2),MSet.of(MSet.of(1)),MSet.of(MSet.of(1)));
        System.out.println(a.toString());
        System.out.println(a.toIntegerString());
        System.out.println(a.asPolyNumber());
        MSet aPlusAntiZero = MSet.add(a, new AntiEmptySet());
        System.out.println(aPlusAntiZero.toString());
        System.out.println(aPlusAntiZero.toIntegerString());
        System.out.println(aPlusAntiZero.asPolyNumber());
        MSet aTimesZero = MSet.mul(a,MSet.of(0));
        System.out.println(aTimesZero.toString());
        System.out.println(aTimesZero.toIntegerString());
        System.out.println(aTimesZero.asPolyNumber());
        assertEquals(new EmptyMSet(), aTimesZero);
    }
    
}
