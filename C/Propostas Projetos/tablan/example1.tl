-- line comment
-- minimum characteristics!

type table ATable {
   name: text -> "Name:"; -- the final [optional] part indicates the textual view of the column (that is, what must be printed as the header of the table)
   number: integer;
};

t: ATable;
t := new; -- new empty table
t -> "Table:"; -- assigns header to table referenced by t
t.number -> "Number:"; -- assigns header to column number to table referenced by t
c: integer;
c := 1;

["One", c] >> t; -- add line at the end of the table
c := c+1;
["Two", c] >> t;
c := c+1;
["Three", c] >> t;

println t; -- prints table with column alignment (adapted to maximum length of each column)

