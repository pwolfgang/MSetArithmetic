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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public interface MSet extends Comparable<MSet>, Cloneable, Iterable<MSet> {

    char[] subScripts = {'\u2080', '\u2081', '\u2082', '\u2083',
        '\u2084', '\u2085', '\u2086', '\u2087', '\u2088', '\u2089'};

    char[] superScripts = {'\u2070', '\u00B9', '\u00B2', '\u00B3', '\u2074',
        '\u2075', '\u2076', '\u2077', '\u2078', '\u2079'};

    int size();

    boolean isEmptySet();

    boolean isAntiEmptySet();

    @Override
    public String toString();

    public String toIntegerString();

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

    public static MSet of(MSet... mSets) {
        if (mSets == null || mSets.length == 0) {
            return new EmptyMSet();
        } else {
            return new NonEmptyMSet(mSets);
        }
    }

    public static MSet of(int n) {
        if (n == 0) {
            return new EmptyMSet();
        } else {
            return new NonEmptyMSet(n);
        }
    }

    MSet clone();

    MSet makeAnti();

    boolean isAnti();

    @Override
    Iterator<MSet> iterator();

    static MSet addOrMulEmptySets(MSet x, MSet y) {
        boolean xA = x.isAntiEmptySet();
        boolean yA = y.isAntiEmptySet();
        if (xA && yA) {
            return new EmptyMSet();
        } else if (!xA && !yA) {
            return new EmptyMSet();
        } else {
            return new AntiEmptySet();
        }
    }

    public static MSet add(MSet x, MSet y) {
        if (x.isEmptySet() && y.isEmptySet()) {
            return addOrMulEmptySets(x, y);
        } else if (x.isAntiEmptySet()) {
            return y.makeAnti();
        } else if (y.isAntiEmptySet()) {
            return x.makeAnti();
        } else {
            return add(new MSet[]{x, y});
        }
    }

    public static MSet add(MSet... mSets) {
        if (mSets.length == 0) {
            return new EmptyMSet();
        } else {
            List<MSet> list = new LinkedList<>();
            for (MSet mSet : mSets) {
                if (mSet.isEmptySet()) {
                    if (mSet.isAntiEmptySet()) {
                        list.add(new AntiEmptySet());
                    } else {
                        list.add(new EmptyMSet());
                        list.add(new AntiEmptySet());
                    }
                } else {
                    list.addAll(((NonEmptyMSet) mSet).content);
                }
            }
            anialate(list);
            if (!list.isEmpty()) {
                return new NonEmptyMSet(list);
            } else {
                return new EmptyMSet();
            }
        }
    }

    static void anialate(List<MSet> mSets) {
        boolean foundPair = true;
        while (foundPair) {
            foundPair = false;
            for (int i = 0; i < mSets.size(); i++) {
                var mSet1 = mSets.get(i);
                for (int j = i + 1; j < mSets.size(); j++) {
                    var mSet2 = mSets.get(j);
                    if (((mSet1.isAnti() && !mSet2.isAnti())
                            || (!mSet1.isAnti() && mSet2.isAnti()))
                            && mSet1.equals(mSet2)) {
                        mSets.remove(j);
                        mSets.remove(i);
                        foundPair = true;
                        break;
                    }
                }
                if (foundPair) {
                    break;
                }
            }
        }
    }

public static MSet mul(MSet x, MSet y) {
        if (x.isEmptySet() && y.isEmptySet()) {
            return addOrMulEmptySets(x, y);
        }
        MSet xC;
        if (x.isEmptySet() && !x.isAntiEmptySet()) {
            xC = MSet.of(new EmptyMSet(), new AntiEmptySet());
        } else {
            xC = x.clone();
        }
        MSet yC;
        if (y.isEmptySet() && !y.isAntiEmptySet()) {
            yC = MSet.of(new EmptyMSet(), new AntiEmptySet());
        } else {
            yC = y.clone();
        }

        List<MSet> resultList = new ArrayList<>();
        for (var msetX : xC) {
            for (var msetY : yC) {
                resultList.add(add(msetX, msetY));
            }
        }
        anialate(resultList);
        return MSet.of(resultList.toArray(MSet[]::new));
    }

    public static MSet mul(MSet... mSets) {
        switch (mSets.length) {
            case 0:
                return MSet.of(0);
            case 1:
                return mSets[0];
            default:
                var x = mSets[0];
                var y = mSets[1];
                var z = MSet.mul(x, y);
                for (int i = 2; i < mSets.length; i++) {
                    z = MSet.mul(z, mSets[i]);
                }
                return z;
        }
    }

    public static MSet crt(MSet x, MSet y) {

        List<MSet> resultList = new ArrayList<>();
        var itrX = x.iterator();
        while (itrX.hasNext()) {
            var msetX = itrX.next();
            var itrY = y.iterator();
            while (itrY.hasNext()) {
                var msetY = itrY.next();
                resultList.add(mul(msetX, msetY));
            }
        }
        anialate(resultList);
        return MSet.of(resultList.toArray(MSet[]::new));
    }

    public static MSet crt(MSet... mSets) {
        switch (mSets.length) {
            case 0:
                return MSet.of(0);
            case 1:
                return mSets[0];
            default:
                var x = mSets[0];
                var y = mSets[1];
                var z = MSet.crt(x, y);
                for (int i = 2; i < mSets.length; i++) {
                    z = MSet.crt(z, mSets[i]);
                }
                return z;
        }
    }

    int getHeight();

    int getDepth();

    void setParent(MSet parent);

    String toStringWithHeight();

    MSet Z();

    MSet N();

    MSet P();

    MSet M();

    String asPolyNumber();

    List<MSet> getContent();

}
