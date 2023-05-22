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

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class Main {
    
        static void printIt(String s, MSet mSet) {
        System.out.printf("%s: %s%n", s, mSet.toString());
        System.out.printf("%s: %s%n", s, mSet.toIntegerString());
        System.out.printf("%s: %s%n", s, mSet.asPolyNumber());
    }

    
    public static void main(String... args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        System.out.printf("%s%n", "\u03b1\u2081\u00b2");
        var a = MSet.of(MSet.of(3),MSet.of(3),MSet.of(4));
        var b = MSet.of(0);
        var c = MSet.of(1);
        var d = MSet.of(MSet.of(3),MSet.of(7));
        var sum = MSet.add(a, b, c, d);
        printIt("a", a);
        printIt("b", b);
        printIt("c", c);
        printIt("d", d);
        printIt("a+b+c+c", sum);

    }
    
}
