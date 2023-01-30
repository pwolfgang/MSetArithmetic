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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class EmptyMSet implements MSet {
    
    MSet parent;
    
    @Override
    public int size() {return 0;}
    
    @Override
    public String toString() {
        return "[]";
    }
    
    public String toStringWithHeight() {
        return "[]0";
    }
    
    @Override
    public String toIntegerString() {
        return "0";
    }
    
    @Override
    public EmptyMSet clone() {
        return new EmptyMSet();
    }
    
    @Override
    public Iterator<MSet> iterator() {
        return new Iterator<MSet>() {
            @Override
            public boolean hasNext() {
                return false;
            }
            @Override
            public MSet next() {
                throw new NoSuchElementException();
            }
        };
    }
    
    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setParent(MSet parent) {
        this.parent = parent;
    }
    
    @Override
    public int getDepth() {
        if (parent != null) {
            return parent.getDepth()+1;
        } else {
            return 0;
        }
    }
    
    @Override
    public MSet Z() {
        return new EmptyMSet();
    }
            
    @Override
    public MSet N() {
        return new EmptyMSet();
    }
    @Override
    public MSet P() {
        return new EmptyMSet();
    }
    @Override
    public MSet M() {
        return new EmptyMSet();
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        return this.getClass() == o.getClass();
    }
    
    @Override
    public String asPolyNumber() {
        return "0";
    }
    
    @Override
    public List<MSet> getContent() {
        return Collections.emptyList();
    }
}
