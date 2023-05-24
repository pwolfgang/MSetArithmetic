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

In general, m&#x03b1<sup>n</sup> is represented by an mset containing m copies of the natural number n.

The polynumber 3 + &#x03B1; + &#x03B1;<sup>3</sup> + 2&#x03B1;<sup>4</sup> is represented by

[[][][][[]][[][][]][[][][][]][[][][][]]]

which can be written as [0 0 0 1 3 4 4].

## Multinumbers

A muultinumber is an mset of poly numbers.

### Addition
Addition of multisets is performed by creating a new multiset that contains the
contents of the multisets being combined. For example:

[[][]] + [[][][]] = [[][][][][]] (2 + 3 = 5)

As will be seen later, addition of multisets extends beyond natural numbers.
### Multiplication
Multiplication of two multisets is performed by adding each mset in the LHS
to each mset in the rhs. For example:

[[][]] × [[][][]] = [[][][] [][][]] (2 × 3 = 6)

The first empty set in the LHS is added to each of the three empty sets in the
rhs giving three empty sets. Then the second empty set is added to the three
empty sets giving a total of six. As will be seen later this applies to more
than natural numbers. Multiplication is associative.
### Caret
The caret operator (^) is similar to multiplication except that each LHS element
is multiplied by each RHS element.

[[[][]] ^ [[][][]] = [[][][][][][]]

For natural numbers this operation is effectively the same as multiplication.
For more complicated multisets it is more meaningful.




2α₀³+α₀⁴ + 0 + 1 + α₀³+α₀⁷ = 1+3α₀³+α₀⁴+α₀⁷


