library ieee;
use ieee.std_logic_1164.all;

entity nandGate is
	port (a : in std_logic;
			b : in std_logic;
			c : out std_logic);
end nandGate;

architecture struct of nandGate is
	signal s_andOut : std_logic;
begin

	u1: entity work.andGate(behav)
			port map(inp0 => a,
						inp1 => b,
						outp => s_andOut);
						
	u2: entity work.notGate(behav)
			port map(inp => s_andOut,
						outp => c);

end struct;