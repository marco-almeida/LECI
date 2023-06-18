-- *NOT* minimum characteristics!

type table ATable {
   name: text;
   number: integer;
};

t: ATable;
t := new; -- new empty table
["One", 1] >> t; -- add line at the end of the table
["Two", 2] >> t;

t2: ATable;
t2 := t; -- t2 and t1 refer to the same table
t_even: ATable;
t_even := t when number \\ 2 = 0 ; -- t_even is a view of table t for lines in which number is even

["Three", 3] >> t;
["Four", 4] >> t;
["Five", 5] >> t;
["Six", 6] >> t;

t2 := new(t_even); -- t2 is a reference to a new table which is a copy of t_even


remove t2 when number \\ 2 = 0; -- remove all t2 lines in which number is even
println t2;

println t_even; -- line Four and Six should also appear!


