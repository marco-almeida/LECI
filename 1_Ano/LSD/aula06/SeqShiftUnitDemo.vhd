library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity SeqShiftUnitDemo is
	port( SW : in std_logic_vector(17 downto 0);
			LEDR : out std_logic_vector(17 downto 0);
			KEY : in std_logic_vector(3 downto 0));
end seqShiftUnitDemo;

architecture shell of seqShiftUnitDemo is
begin

u1: entity work.SeqShiftUnit(Behavioral)
	port map(clk => not KEY(0),
				dataIn => SW(7 downto 0),
				siLeft => SW(16),
				siRight => SW(17),
				loadEn => SW(15),
				rotate => SW(14),
				dirLeft => SW(13),
				shArith => SW(12),
				dataOut => LEDR(7 downto 0));

end shell;