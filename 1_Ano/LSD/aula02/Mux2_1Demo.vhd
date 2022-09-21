library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity Mux2_1Demo is

	port(inputs : in std_logic_vector(1 downto 0);
		  sel    : in std_logic;
		  outmux : out std_logic);
end Mux2_1Demo;

architecture v1 of Mux2_1Demo is
begin

	outmux <= inputs(0) when sel = '0' else inputs(1);
	
end v1;

architecture v2 of Mux2_1Demo is
begin 
	process(inputs, sel)
	begin
		if (sel = '0') then
			outmux <= inputs(0);
		else
			outmux <= inputs(1);
		end if;
	end process;
end v2;