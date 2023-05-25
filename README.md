# MSetArithmetic
An implementation of N. J. Wildberger's model of arithmetic using multisets
This is based on the youtube lectures beginning with Math Foundations 227 
"A multiset approach to arithmetic"
This project implements the features described through lecture 233 -- 
The curious world of integral polynumbers.The basic unit is the multiset. 
A multiset is an unordered collection of objects with the allowance for repeated instances. 
The objects are restricted to be multisets.
## Representing natural numbers 
The number zero is represented by the empty mset []. An integer is represented 
by an mset containing msets. Thus 1 is [[]] 2 is [[][]] and so on.
## Representing PolyNumbers
A PolyNumber is an mset of msets. 
The polynumber &#x03B1; is represented by the multiset containing a multiset
containing an empty multiset, which is the mset containing the natural number one..

[[[]]] = [[1]] = &#x03B1;

The polynumber 2&#x03B1; is the multiset containing two instances of alpha

[[[]][[]]] = [[1][1]] = 2&#x03B1;

The polynumber &#x03B1;<sup>2</sup> is represented by the multiset containing a multiset
containing two empty multisets, which is the mset containing the natural number two.

[[[][]]] = [[2]] = &#x03B1;<sup>2</sup>

In general, m&#x03b1;<sup>n</sup> is represented by an mset containing m copies of the natural number n.

The polynumber 3 + &#x03B1; + &#x03B1;<sup>3</sup> + 2&#x03B1;<sup>4</sup> is represented by

[[][][][[]][[][][]][[][][][]][[][][][]]]

which can be written as [0 0 0 1 3 4 4].

## Multinumbers

A muultinumber is an mset of poly numbers. For example:

[0 0 0 [1] [1 1] [1 1] [1 1] [1 1] [1 1 1 1 1]]

Interpreting polynumbers as polynomials the "variable" &#x03B1; is used. Encapsulating a polynumber
into a mset introduces additional "variables". &#x03B1; now becomes &#x03B1;<sub>0</sub>, then
[&#x03B1;<sub>0</sub>] becomes &#x03B1;<sub>1</sub>. In general m&#x03B1;<sub>k</sub><sup>n</sup>
is represented by an mset containing m copies of the mset containing n copies of the number k.

 [0 0 2 3 3 3 3 [1] [1] [1] [0 1] [0 1] [0 1] [0 1] [0 1] [0 0 1 1]]
 
 Represents
 
 2+α₀²+4α₀³+3α₁+5α₀α₁+α₀²α₁²
 
 ## Operations

### Addition
Addition of multisets is performed by creating a new multiset that contains the
contents of the multisets being combined.

#### Addition of natural numbers

[[][]] + [[][][]] = [[][][][][]] (2 + 3 = 5)

#### Addition of polynumbers

[3 3 4] + 0 + 1 + [3 7] = [0 3 3 3 4 7]

2α₀³+α₀⁴ + 0 + 1 + α₀³+α₀⁷ = 1+3α₀³+α₀⁴+α₀⁷

#### Addition of multinumbers

[[4] [3]] + [0 [4] [1 1 2]] + [4 [1 1 2]] = [0 4 [4] [4] [3] [1 1 2] [1 1 2]]

α₄+α₃ + 1+α₄+α₁²α₂ + α₀⁴+α₁²α₂ = 1+α₀⁴+2α₄+α₃+2α₁²α₂ 

