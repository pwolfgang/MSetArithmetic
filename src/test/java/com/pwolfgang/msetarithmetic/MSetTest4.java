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

public class MSetTest4 {
    
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
        String asPolyNumber = mSet.asPolyNumber();
        System.out.printf("%s: %s%n", s, asPolyNumber);
        assertEquals(s, mSet.asPolyNumber());
    }
    
    @Test
    public void testAlphaZero() {
        var alphaZero = MSet.of(MSet.of(MSet.of(0)));
        printIt("\u03B1\u2080", alphaZero);
    }

    @Test
    public void testAlphaOne() {
        var alphaOne = MSet.of(MSet.of(MSet.of(1)));
        printIt("\u03B1\u2081", alphaOne);
    }

    @Test
    public void testAlphaTwo() {
        var alphaTwo = MSet.of(MSet.of(MSet.of(2)));
        printIt("\u03B1\u2082", alphaTwo);
    }

    
}
