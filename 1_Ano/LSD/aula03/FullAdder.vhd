--a b cin s cout
--0 0 0	 0	 0
--0 0 1	 1	 0
--0 1 0	 1  0
--0 1 1	 0  1
--1 0 0	 1  0
--1 0 1	 0  1
--1 1 0	 0  1
--1 1 1	 1  1


library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity FullAdder is
	port(a, b, cin : in std_logic;
		  s, cout : out std_logic);
end FullAdder;

architecture Behavioral of FullAdder is
begin
	
	s    <= cin xor (a xor b);
	cout <= (a and b) or (cin and (a xor b));
 -- Especifique aqui as equações lógicas para as saídas “s” e “cout”
end Behavioral;