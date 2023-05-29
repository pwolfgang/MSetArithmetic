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
package mainPgms;

import com.pwolfgang.msetarithmetic.MSet;

/**
 *
 * @author Paul Wolfgang <a href="mailto:paul@pwolfgang.com"></a>
 */
public class CaretExamples {
    
        static void printIt(String s, MSet mSet) {
        System.out.printf("%s: %s%n", s, mSet.toString());
        System.out.printf("%s: %s%n", s, mSet.toIntegerString());
        System.out.printf("%s: %s%n", s, mSet.asPolyNumber());
    }

    public static void main(String... args) {
        
        var p = MSet.parse("[[0 1] 3 [2]]");
        var q = MSet.parse("[1 4]");
        printIt("p",p);
        printIt("q",q);
        printIt("p + q", p.add(q));
        p = MSet.parse("[[0 6] 1]");
        q = MSet.parse("[4 [2]]");
        printIt("p", p);
        printIt("q", q);
        printIt("p × q", p.mul(q));
        var b = MSet.parse("[[2 [4]] [0 1 1] [[0] [2]]]");
        printIt("b", b);
        printIt("Z(b)", b.Z());
        printIt("N(b)", b.N());
        printIt("P(b)", b.P());
        printIt("M(b)", b.M());
        var A = MSet.parse("[[1 2] 4]");
        var B = MSet.parse("[0 [0 3]]");
        printIt("A", A);
        printIt("B", B);
        printIt("A ^ B", A.crt(B));
        A = MSet.parse("[[1[2]][3]]");
        B = MSet.parse("[2[1 3]]");
        printIt("A", A);
        printIt("B", B);
        printIt("A ^ B", A.crt(B));
          
    }
    
}
