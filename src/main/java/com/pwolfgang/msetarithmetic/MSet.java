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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An MSet is a data structure that contains an unordered collection of
 * objects with duplicates allowed. This specialized MSet can only contain
 * other MSets.
 * This is based on N.J. Wildberger Math Foundations lectures beginning with
 * lecture 227 "Box Arithmetic A multiset approach." 
 * 
 * @author Paul Wolfgang <a href="mailto:paul@pwolfgang.com"></a>
 */
public interface MSet extends Comparable<MSet>, Cloneable, Iterable<MSet> {

    char[] subScripts = {'\u2080', '\u2081', '\u2082', '\u2083',
        '\u2084', '\u2085', '\u2086', '\u2087', '\u2088', '\u2089'};

    char[] superScripts = {'\u2070', '\u00B9', '\u00B2', '\u00B3', '\u2074',
        '\u2075', '\u2076', '\u2077', '\u2078', '\u2079'};

    /**
     * Return the size of this MSet.
     * @return the size of this MSet.
     */
    int size();

    /**
     * Indicate that this is an empty MSet
     * @return True for empty MSets
     */
    boolean isEmptySet();

    /**
     * Indicate that this is the anti empty MSet
     * @return True for anti empty MSets
     */
    boolean isAntiEmptySet();

    /**
     * Create a String representation of this MSet
     * @return a String representation of this MSet
     */
    @Override
    public String toString();

    /**
     * Create an integer representation of this MSet. The empty MSet is zero.
     * An MSet that contains only empty MSets represents the integer which is
     * the count of the empty MSets. Note that anti empty MSets represent 
     * negative integers.
     * @return An integer representation of this MSet.
     */
    public String toIntegerString();

    /**
     * Compare this MSet to another MSet. MSEts are ordered first by height and
     * next by size. To distinguish possible duplicates the hashCode is used.
     * Two MSets are never considered equal by this method.
     * @param other The other MSet
     * @return -1 if this MSet is less than other and +1 if greater.
     */
    @Override
    default public int compareTo(MSet other) {
        int compareHeight = Integer.compare(this.getHeight(), other.getHeight());
        if (compareHeight != 0) {
            return compareHeight;
        } else {
            int compareSize = Integer.compare(this.size(), other.size());
            if (compareSize != 0) {
                return compareSize;
            } else {
                return Integer.compare(this.hashCode(), other.hashCode());
            }
        }
    }
    
    /** Determine if two MSets have equal contents, but one may be the anti
     * of the other.
     * @param o The other object.
     * @return True if the contents are equal.
     */
    public boolean equalsNoAnti(Object o);

    /**
     * Copnstruct an MSet from an array of MSets
     * @param mSets The list of MSets
     * @return The resulting MSet
     */
    public static MSet of(MSet... mSets) {
        if (mSets == null || mSets.length == 0) {
            return new EmptyMSet();
        } else {
            return new NonEmptyMSet(mSets);
        }
    }

    /**
     * Copnstruct an MSet from a list of MSets
     * @param mSets The list of MSets
     * @return The resulting MSet
     */
    public static MSet of(List<MSet> mSets) {
        if (mSets == null || mSets.isEmpty()) {
            return new EmptyMSet();
        } else {
            return new NonEmptyMSet(mSets);
        }
    }

    /**
     * Construct an MSet that represents an integer. The integer n is 
     * represented by an MSet containing n empty MSets. If n is negative
     * the resulting MSet contains n anti-empty MSets. 
     * @param n
     * @return 
     */
    public static MSet of(int n) {
        if (n == 0) {
            return new EmptyMSet();
        } else {
            return new NonEmptyMSet(n);
        }
    }

   /**
    * Make a deep copy of this MSet.
    * @return A deep copy of this MSet.
    */
    MSet clone();

    /**
     * Make an equivalent anti version of this MSet. This is a deep copy
     * with the anti flag set on the copy. When applied to an anti-MSet, the
     * result is to make a copy with the anti flag cleared.
     * @return an anti copy of this MSet
     */
    MSet makeAnti();

    /**
     * Indicate that this is and anti-MSet
     * @return true if an anti MSet
     */
    boolean isAnti();

    /**
     * Create an Iterator over the contents of this MSet
     * @return an Iterator over the contents of this MSet
     */
    @Override
    Iterator<MSet> iterator();
    
    /** 
     * Return a new MSet that is the sum of this MSet and another MSet.
     * If both MSets are empty, then return the result of addOrMultEmptySets. 
     * If one of the MSets is the anti empty MSet, then return the anti of the 
     * other MSet. Otherwise the result sum of two MSets is an MSet that 
     * contains the contents of the two MSets. After the combination of the two 
     * MSets any pairs of object and anti-object are removed.
     * @param other The other MSet
     * @return The sum of this and other
     */
    MSet add(MSet other);
    
    /**
     * Return a new MSet that is the sum of this MSet an another EmptyMSet.
     * @param other The other EmptyMSet
     * @return The sum of this and other
     */
    MSet addEmptyMSet(EmptyMSet other);
    /**
     * Return a new MSet that is the sum of this MSet an another AntiEmptySet.
     * @param other The other AntiEMptySet.
     * @return The sum of this and other
     */
    MSet addAntiEmptySet(AntiEmptySet other);
    /**
     * Return a new MSet that is the sum of this MSet and another NonEmptyMSet.
     * @param other The other NonEmptyMSet.
     * @return The sum of this and other.
     */
    MSet addNonEmptyMSet(NonEmptyMSet other);
    /**
     * Return a new MSet that is the product of this MSet an another MSet.
     * The result is an MSet of the pair-wise sums of the contents of the 
     * input MSets. 
     * @param other The other MSet.
     * @return this times other
     */
    MSet mul(MSet other);
    /**
     * Return a new MSet that is the product of this MSet an another EmptyMSet.
     * @param other The other EmptyMSet.
     * @return this times other
     */
    MSet mulEmptyMSet(EmptyMSet other);
    /**
     * Return a new MSet that is the product of this MSet an another AntiEmptySet.
     * @param other The other AntiEmptySet.
     * @return this times other
     */
    MSet mulAntiEmptySet(AntiEmptySet other);
    /**
     * Return a new MSet that is the product of this MSet an another NonEmptyMSet.
     * @param other The other NonEmptyMSet.
     * @return this times other
     */
    MSet mulNonEmptyMSet(NonEmptyMSet other);
    /**
     * Return an MSet that is the result of the caret operator.
     * @param other The other MSet
     * @return this ^ other
     */
    MSet crt(MSet other);
    /**
     * Return an MSet that is the result of the caret operator.
     * @param other The other EmptyMSet
     * @return this ^ other
     */
    MSet crtEmptyMSet(EmptyMSet other);
    /**
     * Return an MSet that is the result of the caret operator.
     * @param other The other AntiEmptySet
     * @return this ^ other
     */
    MSet crtAntiEmptySet(AntiEmptySet other);
    /**
     * Return an MSet that is the result of the caret operator.
     * @param other The other NonEmptyMSet
     * @return this ^ other
     */
    MSet crtNonEmptyMSet(NonEmptyMSet other);

    /**
     * Return the sum of two MSets. 
     * @param x one MSet
     * @param y the other MSet
     * @return x + y
     */
    public static MSet add(MSet x, MSet y) {
        return x.add(y);
    }

    /**
     * Compute the sum of an array of MSets. The result is the combination
     * of the contents of the input MSets. 
     * @param mSets An array of MSets.
     * @return The sum of the MSets.
     */
    public static MSet add(MSet... mSets) {
        switch (mSets.length) {
            case 0 -> {
                return new EmptyMSet();
            }
            case 1 -> {
                return mSets[0];
            }
            default -> {
                var x = mSets[0];
                var y = mSets[1];
                var z = x.add(y);
                for (int i = 2; i < mSets.length; i++) {
                    z = z.add(mSets[i]);
                }
                return z;               
            }
        }
    }


    /**
     * Compute the product of two MSets. The product is formed by adding
     * all possible pairs from the two MSets.
     * @param x An MSet
     * @param y The other MSet
     * @return x × y
     */
    public static MSet mul(MSet x, MSet y) {
        return x.mul(y);
    }

    /**
     * Compute the product of an array of MSets. The product is formed
     * by taking the first two and forming their product. Each subsequent
     * MSet is then multiplied by the result.
     * @param mSets A list of MSets
     * @return The product of the MSets.
     */
    public static MSet mul(MSet... mSets) {
        switch (mSets.length) {
            case 0 -> {
                return MSet.of(0);
            }
            case 1 -> {
                return mSets[0];
            }
            default -> {
                var x = mSets[0];
                var y = mSets[1];
                var z = x.mul(y);
                for (int i = 2; i < mSets.length; i++) {
                    z = z.mul(mSets[i]);
                }
                return z;
            }
        }
    }

    /**
     * Form the caret of two MSets. The result of the caret operator is the
     * sum of the products of all pairs.
     * @param x One MSet.
     * @param y The other MSet.
     * @return x ^ y
     */
    public static MSet crt(MSet x, MSet y) {
        return x.crt(y);
    }

    /**
     * Compute the caret operator on an array of MSets.
     * @param mSets The MSets
     * @return The caret operator applied to the list.
     */
    public static MSet crt(MSet... mSets) {
        switch (mSets.length) {
            case 0 -> {
                return MSet.of(0);
            }
            case 1 -> {
                return mSets[0];
            }
            default -> {
                var x = mSets[0];
                var y = mSets[1];
                var z = x.crt(y);
                for (int i = 2; i < mSets.length; i++) {
                    z = z.crt(mSets[i]);
                }
                return z;
            }
        }
    }

    /**
     * The height of this MSet within the MSet tree
     * @return The height of this MSet.
     */
    int getHeight();

    /**
     * The depth of this MSet within the MSet tree
     * @return the depth of this MSet.
     */
    int getDepth();

    /**
     * Set the parent of this MSet
     * @param parent The parent of the MSet
     */
    void setParent(MSet parent);

    /**
     * Append the height of this tree to the string representation
     * @return A string representation of this MSet with its height appended.
     */
    String toStringWithHeight();

    /**
     * Count of the zero level of the MSet tree.
     * @return zero (The empty MSet).
     */
    default MSet Z() {
        return new EmptyMSet();
    }

    /**
     * The number of children of the root.
     * @return An MSet containing Z applied to the contents. 
     */
    MSet N();

    /**
     * The polynumber truncation of the MSet tree
     * @return An MSet containing N applied to the contents.
     */
    MSet P();

    /**
     * The multiset truncation of the MSet tree
     * @return An MSet containing P applied to the contents.
     */
    MSet M();

    /**
     * Create a polynumber representation of the MSet.
     * @return A Polynumber representation of the MSet.
     */
    String asPolyNumber();

    /**
     * The contents of this MSet
     * @return The contents of the MSet as a List
     */
    List<MSet> getContent();
    
    /**
     * Multiply this MSet by -1.
     * @return A copy of this MSet where each element is the anti of the 
     * original contents.
     */
    MSet negateExponent();
    
    /**
     * If this MSet is a polynumber, return the polynumber with
     * each exponent negated. This is accomplished by multiplying each element
     * of this MSet by -1. The effect of this method on MSets that do not
     * represent a polynumber is not defined.
     * @return A copy of this polynumber with each exponent negated.
     */
    default MSet sigma() {
        var c = getContent();
        if (c.isEmpty()) return this;
        List<MSet> newC = new ArrayList<>();
        c.forEach(m -> newC.add(m.negateExponent()));
        return MSet.of(newC);    
    }
    
    /** Convert an integer String representation of an MSet into an MSet.
     * 
     * @param s The String to be parsed
     * @return MSet equivalent
     */
    static MSet parse(String s) {
        List<MSet> result = new ArrayList<>();
        int k = 0;
        while (k < s.length() && s.charAt(k) != '[') {k++;}
        if (k == s.length()) return null;
        k = parse(s, k+1, result);
        char c = s.charAt(k);
        if (c == '\u1D43') {
            return MSet.of(result).makeAnti();
        } else {
            return MSet.of(result);
        }
    }
    
    /** *  Convert an integer String representation of an MSet into an MSet
     * An MSet is a list of MSets enclosed within '[' and ']'.An integer 
    <i>n</i> represents <i>n</i> empty MSets. If <i>n</i> is negative
     * these are anti empty MSets.
     * @param s The String to be parsed
     * @param k The index one passed the opening '['
     * @param result The list containing the result to date.
     * @return The index of the closing ']'
     */
    private static int parse(String s, int k, List<MSet> result) {
            for (char c = s.charAt(k); c != ']'; c=s.charAt(++k)) {
                switch (c) {
                    case '[' -> {
                        List<MSet> list = new ArrayList<>();
                        k = parse(s, k+1, list);
                       if (s.charAt(k+1) == '\u1D43') {
                            result.add(MSet.of(list).makeAnti());
                            k++;
                        } else {
                            result.add(MSet.of(list));
                        }
                    }
                    case '-' -> {
                        var i = -parseInt(s, k+1);
                        c = s.charAt(++k);
                        while (Character.isDigit(c)) {
                            c = s.charAt(++k);
                        }
                        if (c == '\u1D43') {
                            result.add(MSet.of(i).makeAnti());
                        } else {
                            result.add(MSet.of(i));
                            --k;
                        }
                    }
                    case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                        int i = parseInt(s,k);
                        c = s.charAt(k);
                        while (Character.isDigit(c)) {
                            c = s.charAt(++k);
                        }
                        if (c == '\u1D43') {
                            result.add(MSet.of(i).makeAnti());
                        } else {
                            result.add(MSet.of(i));
                            --k;
                        }
                    }
                    case ' ' -> {
                    }
                }
            }
        return k;
    }
    
    private static int parseInt(String s, int k) {
        int n = 0;
        for (char c = s.charAt(k); Character.isDigit(c); c=s.charAt(++k)) {
            n = n * 10;
            n = n + (c - '0');
        }
        return n;
    }
    
    /**
     * Generate the nth symetric binomial
     * @param n
     * @return &#x03B1;<sup>-n</sup> - &#X03B1<sup>n</sup>
     */
    public static MSet B(int n) {
        return MSet.of(MSet.of(-n),MSet.of(n));
    }
    
    /**
     * Generate the nth alternating binomial
     * @param n
     * @return -&#x03B1;<sup>-n</sup> - &#X03B1<sup>n</sup>
    */
    public static MSet A(int n) {
        if (n == 0) {
            return MSet.of(0);
        } else {
            return MSet.of(MSet.of(-n).makeAnti(), MSet.of(n));
        }
    }
    
    /**
     * Generate the nth central polynumber
     * @param n
     * @return The nth central polynumber
     */
    public static MSet C(int n) {
        if (n == 0) {
            return MSet.of(1);
        }
        MSet result;
        if ((n & 1) == 0) { // n is even
            result = MSet.of(1);
            for (int i = 2; i<= n; i+=2) {
                result = result.add(MSet.B(i));
            }
        } else { // n is odd
            result = MSet.of(0);
            for (int i = 1; i <= n; i+=2) {
                result = result.add(MSet.B(i));
            }
        }
        return result;
    }
}
