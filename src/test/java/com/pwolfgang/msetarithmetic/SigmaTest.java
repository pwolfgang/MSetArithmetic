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

public class SigmaTest {
    
    public SigmaTest() {}
    
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
    public void testAlpha1() {
        System.out.println("\n\n\n\u03B1\u00B9 to \u03B1\u207B\u00B9");
        var alpha = MSet.of(MSet.of(1));
        printIt("α₀", alpha);
        var alphaMone = MSet.of(MSet.of(-1));
        printIt("\u03B1\u2080\u207B\u00B9", alphaMone);
        var alphaMone2 = alpha.sigma();
        printIt("\u03B1\u2080\u207B\u00B9", alphaMone2);
    }
    
    @Test
    public void testAlphaMinus3() {
        System.out.println("\n\n\n\u03B1\u2080\u207B\u00B3 to \u03B1\u2080\u00B3");        
        var alphaM3 = MSet.of(MSet.of(-3));
        printIt("\u03B1\u2080\u207B\u00B3", alphaM3);
        printIt("\u03B1\u2080\u00B3", alphaM3.sigma());
    }
    
    @Test
    public void testTwoTerms() {
        System.out.println("\n\n\n\u03B1\u00B9 + \u03B1\u2080\u207B\u00B3 to \u03B1\u207B\u00B9 + \u03B1\u2080\u00B3");
        var alphaPlusAlphaM3 = MSet.of(MSet.of(1), MSet.of(-3));
        var sigmaOfAlphaPlusAlphaM3 = alphaPlusAlphaM3.sigma();
        var expected = MSet.of(MSet.of(-1),MSet.of(3));
        printIt("\u03B1\u2080+\u03B1\u2080\u207B\u00B3", alphaPlusAlphaM3);
        printIt_noAssert("expected", expected);
        printIt("\u03B1\u2080\u207B\u00B9+\u03B1\u2080\u00B3", sigmaOfAlphaPlusAlphaM3);            
    }
    
    @Test
    public void testLargerPolyNumber() {
        System.out.println("\n\n\n\u03B1\u2080\u207B\u00B9 - \u03B1\u2080 + 3\u03B1\u2080\u00B2");
        var p = MSet.of(MSet.of(-1), MSet.of(1).makeAnti(), MSet.of(2),MSet.of(2));
        var sigmaP = MSet.of(MSet.of(1),MSet.of(-1).makeAnti(), MSet.of(-2),MSet.of(-2));
        printIt_noAssert("p", p);
        printIt_noAssert("expected", sigmaP);
        printIt_noAssert("sigmaP", p.sigma());
        assertEquals(sigmaP,p.sigma());      
    }
    
}
