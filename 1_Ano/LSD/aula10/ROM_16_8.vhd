library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity ROM_16_8 is
	port (address : in  std_logic_vector(3 downto 0);
			dataOut: out std_logic_vector(7 downto 0));
end ROM_16_8;

architecture Behav of ROM_16_8 is

    subtype TDataWord is std_logic_vector(7 downto 0);
    type TROM is array (0 to 15) of TDataWord;
    constant c_memory: TROM := (X"18",X"24",X"42",X"81",
                                X"18",X"24",X"42",X"81",
                                X"18",X"24",X"42",X"81",
                                x"00",x"01",x"00",x"01");

begin

    dataOut <= c_memory(to_integer(unsigned(address)));

end Behav;