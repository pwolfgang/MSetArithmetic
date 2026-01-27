/*
 * Copyright (C) 2026 Paul
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

/**
 *
 * @author Paul
 */
public class VirtualBox {
    
    private final MSet leftBox;
    private final MSet rightBox;
    
    public VirtualBox(MSet left, MSet right) {
        leftBox = left;
        rightBox = right;
    }
    
    @Override
    public String toString() {
        return "(" + leftBox.toIntegerString() + "\u2296" + rightBox.toIntegerString() + ")";
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o instanceof VirtualBox other) {
            MSet term1 = leftBox.add(other.rightBox);
            MSet term2 = rightBox.add(other.leftBox);
            return term1.equals(term2);
        } else {
            return false;
        }
    }
    
    public VirtualBox add(VirtualBox other) {
        return new VirtualBox(leftBox.add(other.leftBox), rightBox.add(other.rightBox));   
    }
    
    public VirtualBox neg() {
        return new VirtualBox(rightBox, leftBox);
    }
    
    public VirtualBox sub(VirtualBox other) {
        return add(other.neg());
    }
    
    public VirtualBox mul(VirtualBox other) {
        var term1 = leftBox.mul(other.leftBox).add(rightBox.mul(other.rightBox));
        var term2 = leftBox.mul(other.rightBox).add(rightBox.mul(other.leftBox));
        return new VirtualBox(term1, term2);
    }
    
    
}
