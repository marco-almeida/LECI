library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity FreqDivider is
    port (clkIn : in std_logic;
            k : in std_logic_vector(31 downto 0);
            debug  : out std_logic_vector(31 downto 0);
            clkOut : out std_logic);
end FreqDivider;


architecture v1 of FreqDivider is 
    signal s_count 	 : unsigned(31 downto 0);
    signal s_k        : unsigned(31 downto 0);
begin
	s_k <= unsigned(k);
	process(clkIn)
	begin
		if(rising_edge(clkIn)) then
			if(s_count >= s_k - 1) then
            s_count <= (others => '0');
         else
				s_count <= s_count + 1;
         end if;
      end if;    
   end process;
   debug <= std_logic_vector(s_count);
end v1;