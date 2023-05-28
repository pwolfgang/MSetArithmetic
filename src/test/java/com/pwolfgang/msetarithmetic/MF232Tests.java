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

    
public class MF232Tests {
    
    public MF232Tests() {
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
        String mSetToString = mSet.toString();
        String mSetToIntegerString = mSet.toIntegerString();
        String mSetAsPolyNumber = mSet.asPolyNumber();
        System.out.printf("%s: %s%n", s, mSetToString);
        System.out.printf("%s: %s%n", s, mSetToIntegerString);
        System.out.printf("%s: %s%n", s, mSetAsPolyNumber);
        assertEquals(s, mSet.asPolyNumber());
    }

    void printIt_noAssert(String s, MSet mSet) {
        String mSetToString = mSet.toString();
        String mSetToIntegerString = mSet.toIntegerString();
        String mSetAsPolyNumber = mSet.asPolyNumber();
        System.out.printf("%s: %s%n", s, mSetToString);
        System.out.printf("%s: %s%n", s, mSetToIntegerString);
        System.out.printf("%s: %s%n", s, mSetAsPolyNumber);
    }

    
    @Test
    public void test2minus1minus4() {
        var two = MSet.of(2);
        var minusOne = MSet.of(-1);
        var minusFour = MSet.of(-4);
        var minusThree = MSet.of(-3);
        var sum=MSet.add(two, minusOne, minusFour);
        printIt("2", two);
        printIt("-1", minusOne);
        printIt("-4", minusFour);
        printIt("-3", minusThree);
        printIt("-3", sum);       
    }
    
    @Test
    public void test3timesMinus2() {
        var three = MSet.of(3);
        var minusTwo = MSet.of(-2);
        var prod = MSet.mul(three, minusTwo);
        printIt("3", three);
        printIt("-2", minusTwo);
        printIt("-6", prod);
    }
    
    @Test
    public void testMinus2TimesMinus4TimesMinusOne() {
        var minusTwo = MSet.of(-2);
        var minusFour = MSet.of(-4);
        var minusOne = MSet.of(-1);
        var prod = MSet.mul(minusTwo, minusFour, minusOne);
        printIt("-2", minusTwo);
        printIt("-4", minusFour);
        printIt("-1", minusOne);
        printIt("-8", prod);
        
    }
    
    @Test
    public void testPolyNumber() {
        System.out.println("\n\ntestPolyNumber");
        var zero = MSet.of(0);
        var two = MSet.of(2);
        var five = MSet.of(5);
        var p = MSet.of(zero, zero, two, two, two, five);
        printIt("2+3α₀²+α₀⁵", p);
        var minusOne=MSet.of(-1);
        var minusP = MSet.mul(p,minusOne);
        printIt_noAssert("-p", minusP);
        var zeroAnti = new AntiEmptySet();
        var twoAnti = MSet.add(two, zeroAnti);
        var oneAnti = MSet.add(MSet.of(1), zeroAnti);
        var q = MSet.of(zero, oneAnti, twoAnti, MSet.of(3));
        printIt("1+-1α₀+-1α₀²+α₀³",q);
        printIt("3+-1α₀+2α₀²+α₀³+α₀⁵",MSet.add(p,q));
    }
    
    @Test
    public void testMultPolyNumbers() {
        System.out.println("\n\nestMultPoly");
        var zero = new EmptyMSet();
        var one = MSet.of(1);
        var twoAnti = MSet.add(MSet.of(2),new AntiEmptySet());
        var p = MSet.of(zero, zero, one);
        var q = MSet.of(zero, zero, zero, twoAnti);
        printIt_noAssert("p", p);
        printIt_noAssert("q", q);
        printIt("6+3α₀+-2α₀²+-1α₀³", MSet.mul(p,q));
        
    }
    
    @Test
    public void testMinusTwo() {
        System.out.println("\n\ntestMinusTwo");
        var minusOne = MSet.of(-1);
        var minusTwo = MSet.of(-2);
        var two = MSet.of(2);
        printIt("-2", minusTwo);
        printIt("-1", minusOne);
        printIt("2", two);
        printIt("-2", MSet.mul(two, minusOne));
    }
    
    @Test
    public void testOneMinusAlphaCubed() {
        System.out.println("\n\n\nTest (1-\u03B1)\u00B3");
        var oneMinusAlpha = MSet.of(MSet.of(0),MSet.of(1).makeAnti());
        printIt("1+-1\u03B1\u2080", oneMinusAlpha);
        var oneMinusAlphaCubed = MSet.mul(oneMinusAlpha, oneMinusAlpha, oneMinusAlpha);
        printIt("1+-3α₀+3α₀²+-1α₀³", oneMinusAlphaCubed);
    }
    
}

