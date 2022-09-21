library ieee;
use ieee.std_logic_1164.all;

entity ShiftRegisterN is
	generic (N : positive := 4);
	port (clk : in std_logic;
			dataOut : out std_logic_vector(N-1 downto 0);
			sin : in std_logic);
end ShiftRegisterN;

architecture v1 of ShiftRegisterN is
	signal s_dataOut : std_logic_vector(N-1 downto 0);
begin

	process(clk)
	begin
		if (rising_edge(clk)) then
			s_dataOut <= s_dataOut(N-2 downto 0) & sin;
		end if;
	end process;
	dataOut <= s_dataOut;
end v1;
