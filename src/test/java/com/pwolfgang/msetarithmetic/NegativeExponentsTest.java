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
public class NegativeExponentsTest {
    
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
    public void testNegSubscript() {
        var minusTwo = MSet.of(-2);
        var minusOne = MSet.of(-1);
        var zero = MSet.of(0);
        var three = MSet.of(3);
        var p = MSet.of(minusTwo, minusOne, minusOne, minusOne, zero, three, three);
        printIt_noAssert("p", p);
    }
    
    @Test
    public void testAddWithAnti() {
        var minusThree = MSet.of(-3);
        var minusOne = MSet.of(-1);
        var twoAnti = MSet.of(2).makeAnti();
        var minusOneAnti = minusOne.makeAnti();
        var zero = MSet.of(0);
        var two = MSet.of(2);
        var three = MSet.of(3);
        var lhs = MSet.of(minusThree, minusOne, twoAnti);
        var rhs = MSet.of(minusOneAnti, zero, two, three);
        printIt_noAssert("lhs", lhs);
        printIt_noAssert("rhs", rhs);
        printIt_noAssert("sum", MSet.add(lhs,rhs));
    }
    
    @Test
    public void testMulWithAnti() {
        var minusTwo = MSet.of(-2);
        var one = MSet.of(1);
        var minusOne = MSet.of(-1);
        var threeAnti = MSet.of(3).makeAnti();
        var lhs = MSet.of(minusTwo, one);
        var rhs = MSet.of(minusOne, minusOne, threeAnti);
        printIt_noAssert("lhs", lhs);
        printIt_noAssert("rhs", rhs);
        printIt_noAssert("prod", MSet.mul(lhs, rhs));
        
    }
        
}
