HOW TO USE
1. change the candidate_code/reference_code the JAVA code should start with pubilc class or class as shown in code
2. run the python file

WHAT PACKAGE TO INSTALL
pip install javalang 
other package re/collection/math can be imported directly

BASIC INTRODUCTION
There are three parameters in total for computing n-gram, AST n-gram, and semantic similarity.Each parameter calculation includes comments.

n-gram：
1.Tokenize the code sequence
2.compute the precision from 1-gram to n-gram
3.average these precisions, and then multiply by a brevity-penalty factor.

AST n-gram：
1.Transform Java code into an AST
2.flatten it into a “node-type sequence”
3.count n-gram overlap with clipped precision 
4.compute syntactic similarity.

semantic similarity：
1.Perform AST extraction on the code
2.extract node types and variable types
3.compare the two using Jaccard 
4.fuse the resulting similarities with a weighted average.
