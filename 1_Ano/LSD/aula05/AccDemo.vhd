library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity AccDemo is
	generic (M : positive := 8);
	port (SW		: in std_logic_vector(17 downto 0);
			KEY	: in std_logic_vector(3 downto 0);	
			LEDR	: out std_logic_vector(17 downto 0));
			
end AccDemo;

architecture shell of AccDemo is
begin

	u1: entity work.AccN(struct)
		generic map(K => M)
		port map(clk => not KEY(0),
					enable => SW(17),
					reset => SW(16),
					dataIn => SW(M-1 downto 0),
					dataOut => LEDR(M-1 downto 0));

end shell;