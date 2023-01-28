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

import java.io.PrintStream;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class Main {
    
    public static void main(String... args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        System.out.printf("%s%n", "\u03b1\u2081\u00b2");

    }
    
}
