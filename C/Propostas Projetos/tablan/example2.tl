-- minimum characteristics!

type table ATable -> "The Table!" {
   name: text -> "Name:";
   number: integer -> "Number:";
};

t: ATable;
t := new; -- new empty table
c: integer;
c := 1;

["One", c] >> t; -- add line at the end of the table
[] >> t; -- add empty (undefined) line at the end of the table
c := c+1;
["Two", c] >> t;
[] >> t; -- add empty (undefined) line at the end of the table
c := c+1;
["Three", c] >> t;
[] >> t; -- add empty (undefined) line at the end of the table

println t;

tt: ATable;
tt -> "Table of names (view)";
tt := t.name; -- tt contains a view of table referenced by t consisting only of column name
ttt: ATable;
ttt := new(t.name); -- creates a new table copying only the column name of table referenced by t
ttt -> "Table of names (copy)";

["Four", c+1] >> t;

println tt; -- print column name of table (line Four must also appear!)
println ttt; -- print table referenced by ttt (line Four does not exist!)

println "Yet another print of a table" center (length t.name + 1 + length t.number + 3); -- centers string in table (assuming on extra character before, between, and after each column) 
for elem in t do -- iterate table lines
   elem.name := elem.name + ":";
   println "|",elem.name, "|", elem.number, "|";
end;

-- three alternative reads of the same table: 
t2: ATable;
t2 := read "list1.csv";
println t2;
t2 := read "list2.csv" using column 2 as name, column 1 as number;
println t2;
t2 := read "list1.csv" using "name" as name, "number" as number;
println t2;
