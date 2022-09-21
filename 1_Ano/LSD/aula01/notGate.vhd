library ieee;
use ieee.std_logic_1164.all;

entity notGate is
	port (inp : in std_logic;
			outp : out std_logic);
end notGate;

architecture behav of notGate is
begin

	outp <= not inp;

end behav;
