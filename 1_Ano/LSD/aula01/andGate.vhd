library ieee;
use ieee.std_logic_1164.all;

entity andGate is
	port (inp0 : in std_logic;
			inp1 : in std_logic;
			outp : out std_logic);
end andGate;

architecture behav of andGate is
begin

	outp <= inp0 and inp1;


end behav;