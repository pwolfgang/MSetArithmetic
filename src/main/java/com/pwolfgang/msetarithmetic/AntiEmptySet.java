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
 * This class models the anti empty mset.
 * @author Paul Wolfgang <a href="mailto:paul@pwolfgang.com"></a>
 */
public class AntiEmptySet implements MSet {

    MSet parent;
    
    /**
     * {@inheritDoc}
     * @return Always return 0
     */
    @Override
    public int size() {return 0;}
    
    /**
     * {@inheritDoc}
     * @return Always returns true.
     */
    @Override
    public boolean isEmptySet() {
        return true;
    }
    
    /**
     * {@inheritDoc}
     * @return Always returns true.
     */
    @Override
    public boolean isAntiEmptySet() {
        return true;
    }
    
    /**
     * {@inheritDoc}
     * @return Always returns "[]&#x1D43;"
     */
    @Override
    public String toString() {
        return "[]\u1D43";
    }
    
    /**
     * {@inheritDoc}
     * @return Always returns "[]&#x1D43;0"
     */
    @Override
    public String toStringWithHeight() {
        return "[]\u1D430";
    }
    
    /**
     * {@inheritDoc}
     * @return Always returns "0&#x1D43;"
     */
    @Override
    public String toIntegerString() {
        return "0\u1D43";
    }
    
    /**
     * {@inheritDoc}
     * @return A new AntiEmptySet.
     */
    @Override
    public AntiEmptySet clone() {
        return new AntiEmptySet();
    }
    
    /**
     * {@inheritDoc}
     * @return A new EmptyMSet.
     */
    @Override
    public MSet makeAnti() {
        return new EmptyMSet();
    }
    
    /**
     * {@inheritDoc}
     * @return Always returns true.
     */
    public boolean isAnti() {
        return true;
    }
    
    /**
     * {@inheritDoc}
     * @return An empty iterator.
     */
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
    
    /**
     * {@inheritDoc}
     * Invokes addEmptyMSet on other.
     * @return The sum of this MSet and other
     */
    @Override
    public MSet add(MSet other) {
        return other.addAntiEmptySet(this);
    }
    
    /**
     * {@inheritDoc}
     * The sum of an AntiEmptySet and an EmptyMset is an AntiEmptySet
     * @return The sum of this MSet and other
     */
    @Override
    public MSet addEmptyMSet(EmptyMSet other) {
        return new AntiEmptySet();
    }
    
    /**
     * {@inheritDoc}
     * The sum of two AntiEmptySets is and EmptyMSet.
     * @return The sum of this MSet and other
     */
    @Override
    public MSet addAntiEmptySet(AntiEmptySet other) {
        return new EmptyMSet();
    }
    
    /**
     * {@inheritDoc}
     * The sum of an EmptyMSet a NonEmptyMSet is an anti copy of the NonEmptyMSet.
     * @return The sum of this MSet and other
     */
    @Override
    public MSet addNonEmptyMSet(NonEmptyMSet other) {
        return other.clone().makeAnti();
    }
    
    /**
     * {@inheritDoc}
     * Invokes mulEmptyMSet on other.
     * @return The product of this MSet and other
     */
    @Override
    public MSet mul(MSet other) {
        return other.mulAntiEmptySet(this);
    }
    
    /**
     * {@inheritDoc}
     * The product of a EmptyMSet and an AntiEmptySet is an AntiEmptySet
     * @return The product of this MSet and other
     */
    @Override
    public MSet mulEmptyMSet(EmptyMSet other) {
        return new AntiEmptySet();
    }
    
    /**
     * {@inheritDoc}
     * The product of a AntiEmptySet and an AntiEmptySet is an EmptyMSet
     * @return The product of this MSet and other
     */
    @Override
    public MSet mulAntiEmptySet(AntiEmptySet other) {
        return new EmptyMSet();
    }
    
    /**
     * {@inheritDoc}
     * The product of a non-empty mset and an anti empty mset is an AntiEmptySet
     * @return The product of this MSet and other
     */
    @Override
    public MSet mulNonEmptyMSet(NonEmptyMSet other) {
        return new AntiEmptySet();
    }
    
    
    /**
     * {@inheritDoc}
     * Invokes crtEmptyMSet on other.
     * @return The product of this MSet and other
     */
    @Override
    public MSet crt(MSet other) {
        return other.crtAntiEmptySet(this);
    }
    
    /**
     * {@inheritDoc}
     * The crt of a EmptyMSet and an AntiEmptySet is an AnteEmptySet
     * @return The caret of this MSet and other
     */
    @Override
    public MSet crtEmptyMSet(EmptyMSet other) {
        return new AntiEmptySet();
    }

    /**
     * {@inheritDoc}
     * The crt of a AntiEmptySet and an AntiEmptySet is an AntiEmptySet
     * @return The caret of this MSet and other
     */
    @Override
    public MSet crtAntiEmptySet(AntiEmptySet other) {
        return new AntiEmptySet();
    }
    
    /**
     * {@inheritDoc}
     * The crt of a AntiEmptySet and a non-empty mset is an AntiEmptySet
     * @return The caret of this MSet and other
     */
    @Override
    public MSet crtNonEmptyMSet(NonEmptyMSet other) {
        return new AntiEmptySet();
    }
    
    /**
     * {@inheritDoc}
     * @return Always returns "0".
     */
    @Override
    public int getHeight() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParent(MSet parent) {
        this.parent = parent;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getDepth() {
        if (parent != null) {
            return parent.getDepth()+1;
        } else {
            return 0;
        }
    }
    
    /**
     * {@inheritDoc}
     * @return A new AntiEmptySet
     */
    @Override
    public MSet Z() {
        return new AntiEmptySet();
    }
            
    /**
     * {@inheritDoc}
     * @return A new AntiEmptySet
     */
    @Override
    public MSet N() {
        return new AntiEmptySet();
    }
    
    /**
     * {@inheritDoc}
     * @return A new AntiEmptySet
     */
    @Override
    public MSet P() {
        return new AntiEmptySet();
    }
    
    /**
     * {@inheritDoc}
     * @return A new AntiEmptySet
     */
    @Override
    public MSet M() {
        return new AntiEmptySet();
    }
    
    /**
     * {@inheritDoc}
     * @return True if other is either an EmptyMSet or an AntiEmptySet.
     */
    @Override
    public boolean equalsNoAnti(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        var oClass = o.getClass();
        return oClass == EmptyMSet.class || oClass == AntiEmptySet.class;
    }
    
    /**
     * {@inheritDoc}
     * @return True if other is either an AntiEmptySet.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        return (this.getClass() == o.getClass());
    }

    /**
     * {@inheritDoc}
     * @return "0&#x1D43;"
     */
    @Override
    public String asPolyNumber() {
        return "0\u1D43";
    }
    
   
    /**
     * {@inheritDoc}
     * @return An empty list
     */
    @Override
    public List<MSet> getContent() {
        return Collections.emptyList();
    }
    
    /**
     * {@inheritDoc}
     * @return a new EmptyMSet.
     */
    @Override
    public MSet negateExponent() {
        return new EmptyMSet();
    }

    
}
