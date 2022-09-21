library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity CounterUpDown4 is
	port(clk 	: in std_logic;
		  upDown : in std_logic; --1 for up 0 for down
		  reset 	: in std_logic; --1 for 0000
		  count  : out std_logic_vector(3 downto 0));
end CounterUpDown4;

architecture Behavioral of CounterUpDown4 is
	signal s_count : unsigned(3 downto 0);
begin

	process(clk)
	begin
		if (rising_edge(clk)) then
			if(reset = '1') then
				s_count <= "0000";
			else
				if (upDown = '1') then
					s_count <= s_count + 1;
				else
					s_count <= s_count - 1;
				end if;
			end if;
		end if;
	end process;
	count <= std_logic_vector(s_count);
 
end Behavioral;