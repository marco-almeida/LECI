--fsm

library ieee;
use ieee.std_logic_1164.all;

entity Escolha_Prod_FSM is
	port (clock, clock2, clock3, reset, cafe, cha, choc : in std_logic;
			selWord0 : out std_logic_vector(4 downto 0);
			selWord1 : out std_logic_vector(4 downto 0);
			selWord2 : out std_logic_vector(4 downto 0);
			selWord3 : out std_logic_vector(4 downto 0);
			escolhido, pronto, clkEnable : out std_logic);
end Escolha_Prod_FSM;
--clock 2 e 3 para o estado helo e estado ledg
architecture fsm of Escolha_Prod_FSM is
type T_State is (E0, E1, E2, E3);
signal PS : T_state := E0;
signal s_choc, s_cha, s_cafe : std_logic;
begin
	
process (clock)
	begin
		if (rising_edge(clock)) then
			if (reset = '1') then -- se for feito reset vamos para o E0
				escolhido <= '0';
				pronto <= '0';
				PS <= E0;
			else
				case PS is
					when E0 =>
						clkEnable <= '1';
						selWord0 <= "00000"; --O
						selWord1 <= "11000"; --L
						selWord2 <= "01110"; --E
						selWord3 <= "10111"; --H
						if (clock2 = '1') then -- wait 4 s
							PS <= E1;
						end if;	
					when E1 =>
						clkEnable <= '0';
						selWord0 <= "01011"; --b
						selWord1 <= "01110"; --E
						selWord2 <= "01011"; --b
						selWord3 <= "01110"; --E
							PS <= E2;
					when E2 =>
						clkEnable <= '0';
						if (cafe = '1') then
							selWord0 <= "01110"; --E
							selWord1 <= "01111"; --F
							selWord2 <= "01010"; --A
							selWord3 <= "01100"; --C
							escolhido <= '1';
							s_cafe <= '1';
							PS <= E3;
						elsif (cha = '1') then
							selWord0 <= "01010"; --A
							selWord1 <= "10111"; --H
							selWord2 <= "01100"; --C
							selWord3 <= "11111"; --desativado
							escolhido <= '1';
							s_cha <= '1';
							PS <= E3;
						elsif (choc = '1') then
							selWord0 <= "01100"; --C
							selWord1 <= "00000"; --O
							selWord2 <= "10111"; --H
							selWord3 <= "01100"; --C
							escolhido <= '1';
							s_choc <= '1';
							PS <= E3;
						end if;
					when E3 => -- se o switch voltar a 0, vamos para E1
						if (s_cafe = '1') then
							if (cafe = '0') then
								pronto <='0';
								s_cafe <= '0';
								escolhido <= '0';
								PS <= E1;
							end if;
						
						elsif (s_cha = '1') then
							if (cha = '0') then
								pronto <='0';
								escolhido <= '0';
								s_cha <= '0';
								PS <= E1;
							end if;
						
						
						elsif (s_choc = '1') then
							if (choc = '0') then
								pronto <='0';
								escolhido <= '0';
								s_choc <= '0';
								PS <= E1;
							end if;
						end if;
						
						clkEnable <= '1';
						if (clock3 = '1') then -- wait 7s
							pronto <= '1';
							escolhido <= '0';
							clkEnable <= '0';
						end if;
					when others =>
						PS <= E0;
				end case;
			end if;
		end if;
	end process;
end fsm;