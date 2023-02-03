# MSetArithmetic
An implementation of N. J. Wildberger's model of arithmetic using multisets
This is based on the youtube lectures beginning with Math Foundations 227 "A multiset approach to arithmetic"
This project implements the features described through lecture 231 -- Negative numbers from multisets.
The basic unit is the multiset. A multiset is an unordered collection of objects with the allowance for repeated instances.
The number zero is represented by the empty mset []. An integer is represented by an mset containing msets. Thus 1 is [[]]
2 is [[][]] and so on.
