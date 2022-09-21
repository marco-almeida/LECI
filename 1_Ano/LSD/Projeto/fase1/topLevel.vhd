--TopLevel

library ieee;
use ieee.std_logic_1164.all;

entity topLevel is
	port (CLOCK_50 : in std_logic;
			SW : in std_logic_vector(3 downto 0);
			LEDR : out std_logic_vector(0 downto 0);
			LEDG : out std_logic_vector(0 downto 0);
			HEX0 : out std_logic_vector(6 downto 0);
			HEX1 : out std_logic_vector(6 downto 0);
			HEX2 : out std_logic_vector(6 downto 0);
			HEX3 : out std_logic_vector(6 downto 0));
end topLevel;

architecture struct of topLevel is
signal firstLetter, secondLetter, thirdLetter, fourthLetter : std_logic_vector(4 downto 0);
signal slowClock1, slowClock2, clkLiga : std_logic;

begin
u5 : entity work.freqDivider(RTL)
	generic map(divFactor => 400000000) -- faz-se um clock que fique no 0 durante 4 secs
	port map(clkIn => CLOCK_50,
				enable => clkLiga,
				clkOut => slowClock1);
	

u6 : entity work.freqDivider(RTL)
	generic map(divFactor => 700000000) -- faz-se um clock que fique no 0 durante 7 secs
	port map(clkIn => CLOCK_50,
				enable => clkLiga,
				clkOut => slowClock2);

u0 : entity work.Escolha_Prod_FSM(fsm)
	port map(clock => CLOCK_50,
				clock2 => slowClock1, -- 4segundos helo
				clock3 => slowClock2, -- 7segundos ledr
				reset => SW(3), 
				cafe => SW(0),
				cha => SW(1),
				choc => SW(2),
				selWord0 => firstLetter,
				selWord1 => secondLetter,
				selWord2 => thirdLetter,
				selWord3 => fourthLetter,
				escolhido => LEDR(0),
				pronto => LEDG(0),
				clkEnable => clkLiga); -- enable para o freqDivider
	
	
u1 : entity work.bin7segdecoder(bin7) --primeira letra
	port map(code => firstLetter,
				seg => HEX0);
	
	
u2 : entity work.bin7segdecoder(bin7) --segunda letra
	port map(code => secondLetter,
				seg => HEX1);
	
	
u3 : entity work.bin7segdecoder(bin7) --terceira letra
	port map(code => thirdLetter,
				seg => HEX2);
	
	
u4 : entity work.bin7segdecoder(bin7) --segunda letra
	port map(code => fourthLetter,
				seg => HEX3);
end struct;
	
	

	
	

	








