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
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MSetTest3 {
    
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
        assertEquals(s, mSet.asPolyNumber());
    }

    void printItNoAssert(String s, MSet mSet) {
        System.out.printf("%s: %s%n", s, mSet.toString());
        System.out.printf("%s: %s%n", s, mSet.toIntegerString());
        System.out.printf("%s: %s%n", s, mSet.asPolyNumber());
    }
   
    @Test
    public void testAlphaZero() {
        var alphaZero = MSet.of(MSet.of(MSet.of(0)));
        var twoAlphaZero = MSet.add(alphaZero, alphaZero);
        var threeAlphaZero = MSet.add(twoAlphaZero, alphaZero);
        var fourAlphaZero = MSet.add(twoAlphaZero, twoAlphaZero);
        var alphaZeroSq = MSet.mul(alphaZero, alphaZero);
        var alphaZero3rd = MSet.mul(alphaZeroSq, alphaZero);
        var alphaZero4th = MSet.mul(alphaZeroSq, alphaZeroSq);
        var twoAlphaZeroSq = MSet.add(alphaZeroSq, alphaZeroSq);
        var twoAlphaZero3rd = MSet.add(alphaZero3rd, alphaZero3rd);
        var twoAlphaZero4th = MSet.add(alphaZero4th, alphaZero4th);
        var threeAlphaZeroSq = MSet.add(twoAlphaZeroSq, alphaZeroSq);
        var threeAlphaZero3rd = MSet.add(twoAlphaZero3rd, alphaZero3rd);
        var threeAlphaZero4th = MSet.add(twoAlphaZero4th, alphaZero4th);
        var fourAlphaZeroSq = MSet.add(twoAlphaZeroSq, twoAlphaZeroSq);
        var fourAlphaZero3rd = MSet.add(twoAlphaZero3rd, twoAlphaZero3rd);
        var fourAlphaZero4th = MSet.add(twoAlphaZero4th, twoAlphaZero4th);
        printIt("\u03B1\u2080", alphaZero);
        printIt("2\u03B1\u2080", twoAlphaZero);
        printIt("3\u03B1\u2080", threeAlphaZero);
        printIt("4\u03B1\u2080", fourAlphaZero);
        printIt("\u03B1\u2080\u00B2", alphaZeroSq);
        printIt("\u03B1\u2080\u00B3", alphaZero3rd);
        printIt("\u03B1\u2080\u2074", alphaZero4th);
        printIt("2\u03B1\u2080\u00B2", twoAlphaZeroSq);
        printIt("2\u03B1\u2080\u00B3", twoAlphaZero3rd);
        printIt("2\u03B1\u2080\u2074", twoAlphaZero4th);
        printIt("3\u03B1\u2080\u00B2", threeAlphaZeroSq);
        printIt("3\u03B1\u2080\u00B3", threeAlphaZero3rd);
        printIt("3\u03B1\u2080\u2074", threeAlphaZero4th);
        printIt("4\u03B1\u2080\u00B2", fourAlphaZeroSq);
        printIt("4\u03B1\u2080\u00B3", fourAlphaZero3rd);
        printIt("4\u03B1\u2080\u2074", fourAlphaZero4th);
    }
    
    @Test
    public void testAlphaOne() {
        var alphaOne = MSet.of(MSet.of(MSet.of(1)));
        var twoAlphaOne = MSet.add(alphaOne, alphaOne);
        var threeAlphaOne = MSet.add(twoAlphaOne, alphaOne);
        var fourAlphaOne = MSet.add(twoAlphaOne, twoAlphaOne);
        var alphaOneSq = MSet.mul(alphaOne, alphaOne);
        var alphaOne3rd = MSet.mul(alphaOneSq, alphaOne);
        var alphaOne4th = MSet.mul(alphaOneSq, alphaOneSq);
        var twoAlphaOneSq = MSet.add(alphaOneSq, alphaOneSq);
        var twoAlphaOne3rd = MSet.add(alphaOne3rd, alphaOne3rd);
        var twoAlphaOne4th = MSet.add(alphaOne4th, alphaOne4th);
        var threeAlphaOneSq = MSet.add(twoAlphaOneSq, alphaOneSq);
        var threeAlphaOne3rd = MSet.add(twoAlphaOne3rd, alphaOne3rd);
        var threeAlphaOne4th = MSet.add(twoAlphaOne4th, alphaOne4th);
        var fourAlphaOneSq = MSet.add(twoAlphaOneSq, twoAlphaOneSq);
        var fourAlphaOne3rd = MSet.add(twoAlphaOne3rd, twoAlphaOne3rd);
        var fourAlphaOne4th = MSet.add(twoAlphaOne4th, twoAlphaOne4th);
        printIt("\u03B1\u2081", alphaOne);
        printIt("2\u03B1\u2081", twoAlphaOne);
        printIt("3\u03B1\u2081", threeAlphaOne);
        printIt("4\u03B1\u2081", fourAlphaOne);
        printIt("\u03B1\u2081\u00B2", alphaOneSq);
        printIt("\u03B1\u2081\u00B3", alphaOne3rd);
        printIt("\u03B1\u2081\u2074", alphaOne4th);
        printIt("2\u03B1\u2081\u00B2", twoAlphaOneSq);
        printIt("2\u03B1\u2081\u00B3", twoAlphaOne3rd);
        printIt("2\u03B1\u2081\u2074", twoAlphaOne4th);
        printIt("3\u03B1\u2081\u00B2", threeAlphaOneSq);
        printIt("3\u03B1\u2081\u00B3", threeAlphaOne3rd);
        printIt("3\u03B1\u2081\u2074", threeAlphaOne4th);
        printIt("4\u03B1\u2081\u00B2", fourAlphaOneSq);
        printIt("4\u03B1\u2081\u00B3", fourAlphaOne3rd);
        printIt("4\u03B1\u2081\u2074", fourAlphaOne4th);
    }
    @Test

    public void testAlphaTwo() {
        var alphaZero = MSet.of(MSet.of(MSet.of(0)));
        var alphaZeroSq = MSet.mul(alphaZero,alphaZero);
        var alphaOne = MSet.of(alphaZero);
        var alphaTwo = MSet.of(alphaZeroSq);
        var twoAlphaTwo = MSet.add(alphaTwo, alphaTwo);
        var threeAlphaTwo = MSet.add(twoAlphaTwo, alphaTwo);
        var fourAlphaTwo = MSet.add(twoAlphaTwo, twoAlphaTwo);
        var alphaTwoSq = MSet.mul(alphaTwo, alphaTwo);
        var alphaTwo3rd = MSet.mul(alphaTwoSq, alphaTwo);
        var alphaTwo4th = MSet.mul(alphaTwoSq, alphaTwoSq);
        var twoAlphaTwoSq = MSet.add(alphaTwoSq, alphaTwoSq);
        var twoAlphaTwo3rd = MSet.add(alphaTwo3rd, alphaTwo3rd);
        var twoAlphaTwo4th = MSet.add(alphaTwo4th, alphaTwo4th);
        var threeAlphaTwoSq = MSet.add(twoAlphaTwoSq, alphaTwoSq);
        var threeAlphaTwo3rd = MSet.add(twoAlphaTwo3rd, alphaTwo3rd);
        var threeAlphaTwo4th = MSet.add(twoAlphaTwo4th, alphaTwo4th);
        var fourAlphaTwoSq = MSet.add(twoAlphaTwoSq, twoAlphaTwoSq);
        var fourAlphaTwo3rd = MSet.add(twoAlphaTwo3rd, twoAlphaTwo3rd);
        var fourAlphaTwo4th = MSet.add(twoAlphaTwo4th, twoAlphaTwo4th);
        printIt("\u03B1\u2081", alphaOne);
        printIt("\u03B1\u2082", alphaTwo);
        printIt("2\u03B1\u2082", twoAlphaTwo);
        printIt("3\u03B1\u2082", threeAlphaTwo);
        printIt("4\u03B1\u2082", fourAlphaTwo);
        printIt("\u03B1\u2082\u00B2", alphaTwoSq);
        printIt("\u03B1\u2082\u00B3", alphaTwo3rd);
        printIt("\u03B1\u2082\u2074", alphaTwo4th);
        printIt("2\u03B1\u2082\u00B2", twoAlphaTwoSq);
        printIt("2\u03B1\u2082\u00B3", twoAlphaTwo3rd);
        printIt("2\u03B1\u2082\u2074", twoAlphaTwo4th);
        printIt("3\u03B1\u2082\u00B2", threeAlphaTwoSq);
        printIt("3\u03B1\u2082\u00B3", threeAlphaTwo3rd);
        printIt("3\u03B1\u2082\u2074", threeAlphaTwo4th);
        printIt("4\u03B1\u2082\u00B2", fourAlphaTwoSq);
        printIt("4\u03B1\u2082\u00B3", fourAlphaTwo3rd);
        printIt("4\u03B1\u2082\u2074", fourAlphaTwo4th);
    }
    
    @Test
    public void testAlphaZero4thPlusAlphaZero3rd() {
        System.out.println("\n\n\n\u03B1\u2080\u00B3+\u03B1\u2080\u2074");
        var alphaZero = MSet.of(MSet.of(MSet.of(0)));
        var alphaZeroSq = MSet.mul(alphaZero, alphaZero);
        var alphaZero3rd = MSet.mul(alphaZeroSq, alphaZero);
        var alphaZero4th = MSet.mul(alphaZeroSq, alphaZeroSq);
        var alphaZero3rdPlusAlphaZero4th=MSet.add(alphaZero3rd,alphaZero4th);
        printIt("\u03B1\u2080\u00B3", alphaZero3rd);
        printIt("\u03B1\u2080\u2074", alphaZero4th);
        printIt("\u03B1\u2080\u00B3+\u03B1\u2080\u2074", alphaZero3rdPlusAlphaZero4th);
    }

    @Test
    public void testAlphaThreePlusAlphaFour() {
        System.out.println("\n\nALPHA THREE PLUS ALPHA FOUR");
        var alphaThree = MSet.of(MSet.of(MSet.of(3)));
        var alphaFour = MSet.of(MSet.of(MSet.of(4)));
        var alphaThreePlusAlphaFour=MSet.add(alphaThree,alphaFour);
        printIt("\u03B1\u2083", alphaThree);
        printIt("\u03B1\u2084", alphaFour);
        printIt("\u03B1\u2083+\u03B1\u2084", alphaThreePlusAlphaFour);
        System.out.println("\n\n\n");
    }
}
