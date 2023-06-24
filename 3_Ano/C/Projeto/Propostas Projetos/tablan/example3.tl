-- minimum characteristics!

type table Table {
   n1: real;
   n2: real;
   average: real := (n1+n2)/2; -- defined as a formula!
};

t: Table;
t := new; -- new empty table

[real(read in "n1:"), real(read in "n2:")] >> t; -- read in: read text from standard input 
[real(read in "n1:"), real(read in "n2:")] >> t;
print "n1 and ";
println " n2: ";
[real(read in), real(read in)] >> t;

println t;

