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
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.StringJoiner;
import java.util.TreeSet;

/**
 *
 * @author Paul Wolfgang <a href="mailto:paul@pwolfgang.com"></a>
 */
public class NonEmptyMSet implements MSet {
    
    int height;
    MSet parent;
    boolean anti;
    
    SortedSet<MSet> content;
    
    @Override
    public boolean isEmptySet() {
        return false;
    }
    
    @Override
    public boolean isAntiEmptySet() {
        return false;
    }
    
    NonEmptyMSet(MSet... mSets) {
        this(Arrays.asList(mSets));
    }
    
    NonEmptyMSet(List<MSet> mSets) {
        content = new TreeSet<>(MSet::compareTo);
        int maxHeight = 0;
        for (MSet m : mSets) {
            var mSet = m.clone();
            mSet.setParent(this);
            if (mSet.getHeight() > maxHeight) {
                maxHeight = mSet.getHeight();
            }          
            content.add(mSet);
        }
        height = maxHeight+1;
    }
    
    NonEmptyMSet(int n) {
        content = new TreeSet<>(MSet::compareTo);
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                MSet mSet = new EmptyMSet();
                mSet.setParent(this);
                content.add(mSet);
            }
        } else if (n < 0) {
            for (int i = 0; i < -n; i++) {
                MSet mSet = new AntiEmptySet();
                mSet.setParent(this);
                content.add(mSet);
            }            
        } else {
            throw new IllegalArgumentException();
        }
        height=1;
    } 
    
    NonEmptyMSet(SortedSet<MSet> c) {
        content = new TreeSet<>(MSet::compareTo);
        int maxHeight = 0;
        for (MSet mSet : c) {
            MSet newMSet = mSet.clone();
            newMSet.setParent(this);
            if (newMSet.getHeight() > maxHeight) {
                maxHeight = newMSet.getHeight();
            }          
            content.add(newMSet);
        }
        height = maxHeight+1;
    }
    
    @Override
    public NonEmptyMSet clone() {
        List<MSet> contentsList = new ArrayList<>();
        content.forEach(m -> contentsList.add(m.clone()));
        NonEmptyMSet theClone = new NonEmptyMSet(contentsList);
        theClone.anti = this.anti;
        return theClone;
    }
    
    @Override
    public MSet makeAnti() {
        var result = clone();
        result.anti = !anti;
        return result;
    }
    
    @Override
    public boolean isAnti() {
        return anti;
    }
    
    @Override
    public int size() {
        return content.size();
    }
    
    @Override
    public String toString() {
        var stj = anti ? new StringJoiner(" ", "[", "]\u1D43") : new StringJoiner(" ", "[", "]");
        content.forEach(m -> stj.add(m.toString()));
        return stj.toString();
    }
    
    @Override
    public String toStringWithHeight() {
        var stj = new StringJoiner(" ", "[", "]");
        content.forEach(m -> stj.add(m.toStringWithHeight()));
        return String.format("%s%d",stj.toString(),height);
    }
        
    @Override
    public String toIntegerString() {
        int countOfEmptySets = 0;
        var itr = content.iterator();
        StringJoiner sj = null;
        while (itr.hasNext()) {
            var c = itr.next();
            if (c.isEmptySet()) {
                if (c.isAntiEmptySet()) {
                    countOfEmptySets--;
                } else {
                    countOfEmptySets++;
                }
            } else {
                sj = new StringJoiner(" ", "[", "]");
                if (countOfEmptySets != 0) {
                    if (countOfEmptySets < 0) {
                        for (int i = 0; i < -countOfEmptySets; i++) {
                            sj.add("0\u1D43");
                        }
                    } else {
                        for (int i = 0; i < countOfEmptySets; i++) {
                            sj.add("0");
                        }
                    }
                }
                sj.add(c.toIntegerString());
                finishLoop(itr, sj);         
            }      
        }
        if (sj == null) {
            var n = Integer.toString(countOfEmptySets);
            if (isAnti()) {
                return n + "\u1D43";
            } else {
                return n;
            }
        } else {
            if (isAnti()) {
                return sj.toString() + "\u1D43";
            } else {
                return sj.toString();
            }
        }
    }
    
    private void finishLoop(Iterator<MSet> itr, StringJoiner sj) {
        while (itr.hasNext()) {
            sj.add(itr.next().toIntegerString());
        }
    }
    
    @Override
    public Iterator<MSet> iterator() {
        return content.iterator();
    }
      
    /**
     * Remove any pairs of equal MSet and anti-MSet. 
     * @param mSets A list of MSets.
     */
    static void annihilate(List<MSet> mSets) {
        boolean foundPair = true;
        while (foundPair) {
            foundPair = false;
            for (int i = 0; i < mSets.size(); i++) {
                var mSet1 = mSets.get(i);
                for (int j = i + 1; j < mSets.size(); j++) {
                    var mSet2 = mSets.get(j);
                    if (((mSet1.isAnti() && !mSet2.isAnti())
                            || (!mSet1.isAnti() && mSet2.isAnti()))
                            && mSet1.equalsNoAnti(mSet2)) {
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
    
    
    @Override
    public MSet add(MSet other) {
        return other.addNonEmptyMSet(this);
    }
    
    @Override
    public MSet addEmptyMSet(EmptyMSet other) {
        return this.clone();
    }
    
    @Override
    public MSet addAntiEmptySet(AntiEmptySet other) {
        return this.makeAnti();
    }
    
    @Override
    public MSet addNonEmptyMSet(NonEmptyMSet other) {
        List<MSet> list = new LinkedList<>();
        list.addAll(this.content);
        list.addAll(other.getContent());
        annihilate(list);
        boolean resultIsAnti = (this.anti || other.isAnti()) && !(this.anti && other.isAnti());
        MSet result;
        if (!list.isEmpty()) {
            result = new NonEmptyMSet(list);
        } else {
            result = new EmptyMSet();
        }
        if (resultIsAnti) {
            return result.makeAnti();
        } else {
            return result;
        }
    }
    
    @Override
    public MSet mul(MSet other) {
        return other.mulNonEmptyMSet(this);
    }
    
    @Override
    public MSet mulEmptyMSet(EmptyMSet other) {
        return new EmptyMSet();
    }
    
    @Override
    public MSet mulAntiEmptySet(AntiEmptySet other) {
        return new AntiEmptySet();
    }
    
    @Override
    public MSet mulNonEmptyMSet(NonEmptyMSet other) {
        List<MSet> resultList = new ArrayList<>();
        for (var msetX : this) {
            for (var msetY : other) {
                resultList.add(msetX.add(msetY));
            }
        }
        annihilate(resultList);
        if (!resultList.isEmpty()) {
            return new NonEmptyMSet(resultList);
        } else {
            return new EmptyMSet();
        }
        
    }
    
    @Override
    public MSet crt(MSet other) {
        return other.crtNonEmptyMSet(this);
    }
    
    @Override
    public MSet crtEmptyMSet(EmptyMSet other) {
        return new EmptyMSet();
    }
    
    @Override
    public MSet crtAntiEmptySet(AntiEmptySet other) {
        return new AntiEmptySet();
    }
    
    @Override
    public MSet crtNonEmptyMSet(NonEmptyMSet other) {
        List<MSet> resultList = new ArrayList<>();
        for (var msetX : this) {
            for (var msetY : other) {
                resultList.add(msetX.mul(msetY));
            }
        }
        annihilate(resultList);
        if (!resultList.isEmpty()) {
            return new NonEmptyMSet(resultList);
        } else {
            return new EmptyMSet();
        }        
    }
    
    @Override
    public int getHeight() {
        return height;
    }
    
    @Override
    public int getDepth() {
        if (parent != null) {
            return parent.getDepth() + 1;
        } else {
            return 0;
        }
    }
    
    @Override
    public void setParent(MSet parent) {
        this.parent = parent;
    }
    
    
    @Override
    public MSet N() {
        return MSet.of(content.size());
    }
    
    @Override
    public MSet P() {
        List<MSet> mSets = new ArrayList<>();
        content.forEach(mSet -> mSets.add(mSet.N()));
        MSet[] mSetArray = mSets.toArray(MSet[]::new);
        return MSet.of(mSetArray);
    }
    
    @Override
    public MSet M() {
        List<MSet> mSets = new ArrayList<>();
        content.forEach(mSet -> mSets.add(mSet.P()));
        MSet[] mSetArray = mSets.toArray(MSet[]::new);
        return MSet.of(mSetArray);        
    }
    
    @Override
    public boolean equalsNoAnti(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() == o.getClass()) {
            NonEmptyMSet other = (NonEmptyMSet)o;
            var thisContent = this.content;
            var otherContent = other.clone().content;
            if (thisContent.size() != otherContent.size()) {
                return false;
            }
            boolean found = false;
            for (var x : thisContent) {
                var itr2 = otherContent.iterator();
                found = false;
                while (!found && itr2.hasNext()) {
                    var y = itr2.next();
                    if (x.equals(y)) {
                        found = true;
                        itr2.remove();
                    }
                }
            }
            return found;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() == o.getClass()) {
            NonEmptyMSet other = (NonEmptyMSet)o;
            if (this.anti != other.anti) {
                return false;
            }
            var thisContent = this.content;
            var otherContent = other.clone().content;
            if (thisContent.size() != otherContent.size()) {
                return false;
            }
            boolean found = false;
            for (var x : thisContent) {
                var itr2 = otherContent.iterator();
                found = false;
                while (!found && itr2.hasNext()) {
                    var y = itr2.next();
                    if (x.equals(y)) {
                        found = true;
                        itr2.remove();
                    }
                }
                if (!found) {
                    return false;
                }
            }
            return found;
        } else {
            return false;
        }
    }
    
    List<List<MSet>> groupEquals() {
        List<List<MSet>> result = new ArrayList<>();
        var c = new LinkedList<>(content);
        while (!c.isEmpty()) {
            List<MSet> subList = new ArrayList<>();
            var itr = c.iterator();
            var m = itr.next();
            subList.add(m);
            itr.remove();
            while (itr.hasNext()) {
                var x = itr.next();
                if (m.equals(x)) {
                    subList.add(x);
                    itr.remove();
                }
            }
            result.add(subList);
        }        
        return result;
    }
    
    String asString(List<List<MSet>> equalList) {
        var strJoiner = new StringJoiner(" + ","[", "]");
        equalList.forEach((List<MSet> el) -> {
            MSet[] ar = el.toArray(MSet[]::new);
            strJoiner.add(MSet.of(ar).toIntegerString());
        });
        return strJoiner.toString();
    }
    
    @Override
    public String asPolyNumber() {
        List<List<MSet>> list = groupEquals();
        var stringJoiner = new StringJoiner("+");
        list.forEach(el ->{
            int count = el.size();
            MSet first = el.get(0);
            var stringBuilder = new StringBuilder();
            if (first.getHeight() == 0) {
                if (first.isAnti()) {
                    stringBuilder.append(Integer.toString(-count));
                } else {
                    stringBuilder.append(Integer.toString(count));
                }
            } else {
                if (first.isAnti()) {
                    stringBuilder.append(Integer.toString(-count));
                } else {
                    if (count > 1) {
                        stringBuilder.append(Integer.toString(count));
                    }
                }
                stringBuilder.append(genSupSub((NonEmptyMSet)first));
            }
            stringJoiner.add(stringBuilder);
        });
        var result = stringJoiner.toString();
        return isAnti() ? result + "\u1D43" : result;
    }
    
    String genSupSub(NonEmptyMSet m) {
        var ll = m.groupEquals();
        var stb = new StringBuilder();
        ll.forEach(el ->{
            stb.append("\u03B1");
            stb.append(genSub(el.get(0).size()));
            var count = countSets(el);
            if (count < 0) {
                stb.append("\u207B");
                stb.append(genSup(-count));
            } else if (count > 1) {
                stb.append(genSup(count));
            }
        });
        return stb.toString();      
    }
    
    int countSets(List<MSet> el) {
        int count = 0;
        for (var c : el) {
            if (c.isAnti()) {
                count--;
            } else {
                count++;
            }
        }
        return count;
    }

    
    String genSub(int n) {
        var stb = new StringBuilder();
        var s = Integer.toString(n);
        for (int i = 0; i < s.length(); i++) {
            var k = (s.charAt(i) - '0');
            stb.append(subScripts[k]);
        }
        return stb.toString();
    }

    String genSup(int n) {
        var stb = new StringBuilder();
        var s = Integer.toString(n);
        for (int i = 0; i < s.length(); i++) {
            var k = (s.charAt(i) - '0');
            stb.append(superScripts[k]);
        }
        return stb.toString();
    }
    
    @Override
    public List<MSet> getContent() {
        return new ArrayList<>(content);
    }

    @Override
    public MSet negateExponent() {
        var c = getContent();
        List<MSet> newC = new ArrayList<>();
        c.forEach(m -> newC.add(m.makeAnti()));
        if (isAnti()) {
            return MSet.of(newC).makeAnti();
        } else {
            return MSet.of(newC);
        }
     }

}
