library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity Mux4_1Demo is

	port(inputs3 : in std_logic_vector(7 downto 0);
			inputs2 : in std_logic_vector(7 downto 0);
			inputs1 : in std_logic_vector(7 downto 0);
			inputs0 : in std_logic_vector(7 downto 0);
			sel : in std_logic_vector(1 downto 0);
			outmux : out std_logic_vector(7 downto 0));
	
end Mux4_1Demo;

architecture v1 of Mux4_1Demo is
begin

	outmux <= inputs0 when sel = "00" else
				 inputs1 when sel = "01" else
				 inputs2 when sel = "10" else
				 inputs3;
	
end v1;

architecture v2 of Mux4_1Demo is
begin
	process(all)
	begin
		if (sel = "00") then
			outmux <= inputs0;
		elsif (sel = "01") then
			outmux <= inputs1;
		elsif (sel = "10") then
			outmux <= inputs2;
		else
			outmux <= inputs3;
		end if;
	end process;
end v2;

