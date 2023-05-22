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

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class ToPolyNumberTest {
    
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
    
    @Test
    public void test1() {
        var p = MSet.of(MSet.of(0),MSet.of(0),MSet.of(0),MSet.of(1),MSet.of(3),MSet.of(4),MSet.of(4));
        var s = p.asPolyNumber();
        printIt("3+\u03B1\u2080+\u03B1\u2080\u00B3+2\u03B1\u2080\u2074",p);
    }
    
    @Test
    public void testAlpha() {
        var alphaSubOne = MSet.of(MSet.of(MSet.of(1)));
        printIt("\u03B1\u2081", alphaSubOne);
        printIt("2\u03B1\u2081", MSet.of(MSet.of(MSet.of(1)),MSet.of(MSet.of(1))));
        printIt("\u03B1\u2081\u00B2", alphaSubOne.mul(alphaSubOne));
        printIt("\u03B1\u2081\u00B3", MSet.mul(alphaSubOne,alphaSubOne,alphaSubOne));
        var alphaSubTwo = MSet.of(MSet.of(MSet.of(2)));
        printIt("\u03B1\u2082", alphaSubTwo);
        printIt("2\u03B1\u2082", MSet.of(MSet.of(MSet.of(2)),MSet.of(MSet.of(2))));
        printIt("\u03B1\u2082\u00B2", alphaSubTwo.mul(alphaSubTwo));
        printIt("\u03B1\u2082\u00B3", MSet.mul(alphaSubTwo,alphaSubTwo,alphaSubTwo));
    }
    
    @Test
    public void testAlphaExpressions() {
        var alphaSubOne = MSet.of(MSet.of(MSet.of(1)));
        var alphaSubTwo = MSet.of(MSet.of(MSet.of(2)));
        var alphaSubOneCubed = MSet.mul(alphaSubOne,alphaSubOne,alphaSubOne);
        printIt("\u03B1\u2081\u00B3\u03b1\u2082", alphaSubOneCubed.mul(alphaSubTwo));
        var alphaSubZero = MSet.of(MSet.of(1));
        var alpha5alpha7 = MSet.of(MSet.of(MSet.of(5), MSet.of(7)));
        printIt("\u03B1\u2080", alphaSubZero);
        var twoAlphaSubZero = alphaSubZero.add(alphaSubZero);
        printIt("2\u03B1\u2080",twoAlphaSubZero);
        printIt("\u03B1\u2085\u03b1\u2087", alpha5alpha7);
        printIt("2\u03B1\u2080+\u03B1\u2085\u03b1\u2087", twoAlphaSubZero.add(alpha5alpha7));
    }
    
    
}
