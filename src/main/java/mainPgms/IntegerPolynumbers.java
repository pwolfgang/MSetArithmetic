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
package mainPgms;

import com.pwolfgang.msetarithmetic.MSet;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class IntegerPolynumbers {

    static void printIt(String s, MSet mSet) {
        String mSetToString = mSet.toString();
        String mSetToIntegerString = mSet.toIntegerString();
        String mSetAsPolyNumber = mSet.asPolyNumber();
        System.out.printf("%s: %s%n", s, mSetToString);
        System.out.printf("%s: %s%n", s, mSetToIntegerString);
        System.out.printf("%s: %s%n", s, mSetAsPolyNumber);
    }


    public static void main(String... args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        var p = MSet.of(MSet.of(0), MSet.of(0), MSet.of(1), MSet.of(4), MSet.of(4), MSet.of(4));
        printIt("p", p);
        var q = MSet.of(MSet.of(-3), MSet.of(-3), MSet.of(-1), MSet.of(0), MSet.of(1),MSet.of(1), MSet.of(1));
        printIt("q", q);
        var r = MSet.of(MSet.of(0).makeAnti(),MSet.of(0).makeAnti(),MSet.of(1),MSet.of(1),MSet.of(1),MSet.of(3).makeAnti());
        printIt("r", r);
        var s = MSet.of(MSet.of(-2).makeAnti(),MSet.of(-1).makeAnti(),MSet.of(-1).makeAnti(),MSet.of(0), MSet.of(2),MSet.of(2),MSet.of(2));
        printIt("s", s);
        p = MSet.of(MSet.of(-2),MSet.of(0),MSet.of(1),MSet.of(1));
        printIt("p", p);
        q = MSet.of(MSet.of(-1).makeAnti(),MSet.of(-1).makeAnti(),MSet.of(1));
        printIt("q", q);
        printIt("p + q", p.add(q));
        printIt("p × q", p.mul(q));
        var x = MSet.of(MSet.of(-1), MSet.of(1).makeAnti(), MSet.of(2),MSet.of(2),MSet.of(2));
        printIt("x", x);
        var sigmaX = MSet.of(MSet.of(-2),MSet.of(-2),MSet.of(-2),MSet.of(-1).makeAnti(),MSet.of(1));
        printIt("σ(x)", sigmaX);
        
        
    }
    
}
