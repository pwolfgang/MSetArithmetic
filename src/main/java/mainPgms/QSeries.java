/*
 * Copyright (C) 2024 Paul
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
 * @author Paul
 */
public class QSeries {
    
    static void printIt(String s, MSet mSet) {
        String mSetToString = mSet.toString();
        String mSetToIntegerString = mSet.toIntegerString();
        String mSetAsPolyNumber = mSet.asPolyNumber();
        System.out.printf("%s: %s%n", s, mSetToString);
        System.out.printf("%s: %s%n", s, mSetToIntegerString);
        System.out.printf("%s: %s%n", s, mSetAsPolyNumber);
    }
        
    public static void main(String... args) {
        var p1 = MSet.parse("[0 1\u1D43]");
        var p2 = MSet.parse("[0 2\u1D43]");
        var p3 = MSet.parse("[0 3\u1D43]");
        var prod = MSet.mul(p1,p2,p3);
        printIt("p1: ", p1);
        printIt("p2: ", p2);
        printIt("p3: ", p3);
        printIt("prod: ", prod);
    }    
        
        

    
}
