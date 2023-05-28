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

public class MSetTest2 {
    
    public MSetTest2() {
        
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
        assertEquals(s, mSet.asPolyNumber());
    }
    
    @Test
    public void testPolyNumber() {
        MSet p = MSet.of(MSet.of(0), MSet.of(0), MSet.of(0), MSet.of(1), MSet.of(3), MSet.of(4), MSet.of(4));
        printIt("3+α₀+α₀³+2α₀⁴", p);
    }
    
    @Test
    public void testTree() {
        System.out.println("test tree 2");
        MSet a = MSet.of(MSet.of(1),MSet.of(MSet.of(1)),MSet.of(0));
        MSet b = MSet.of(MSet.of(0),MSet.of(2));
        System.out.print("a: ");
        printIt("1+\u03B1\u2080+\u03B1\u2081", a);
        System.out.print("b: ");
        printIt("1+\u03B1\u2080\u00B2",b);
        System.out.print("a+b: ");
        printIt("2+\u03B1\u2080+\u03B1\u2080\u00B2+\u03B1\u2081", MSet.add(a,b));
        System.out.print("a×b: ");
        printIt("1+\u03B1\u2080+\u03B1\u2080\u00B2+\u03B1\u2080\u00B3+\u03B1\u2081+\u03B1\u2080\u00B2\u03B1\u2081", MSet.mul(a,b));
        System.out.print("a^b: ");
        printIt("4+\u03B1\u2080\u00B2+\u03B1\u2081\u00B2", MSet.crt(a,b));
    }
    
}
