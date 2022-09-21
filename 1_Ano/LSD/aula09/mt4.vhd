library ieee;
use ieee.std_logic_1164.all;

entity mt4 is
    port(Xin   : in  std_logic;
          clk   : in  std_logic;
          reset : in  std_logic; 
          Yout  : out std_logic_vector(3 downto 0)
            );

end mt4;

architecture MealyArch of mt4 is
    type Tstate is (E0, E1, E2, E3);
    signal s_currentState, s_nextState : TState;
begin

sync_proc : process(clk)
    begin
        if (rising_edge(clk)) then
            if (reset = '1') then
                s_currentState <= E0;
            else
                s_currentState <= s_nextState;
            end if;
        end if;
    end process;

comb_proc : process(s_currentState, Xin)
	begin
		Yout <= "0000";
			case s_currentState is
				when E0 =>
					if(Xin = '1') then
						s_nextState <= E1;
						Yout <= "0001";
					else
						s_nextState <= E0;
					end if;
				when E1 =>
					if(Xin = '0') then
						Yout <= "0011";
						s_nextState <= E2;
					else
						s_nextState <= E0;
					end if;
				when E2 =>
					if(Xin = '1') then
						s_nextState <= E3;
						Yout <= "0111";
					else
						s_nextState <= E0;
					end if;
				when E3 =>
					Yout <= "1111";
					if(Xin = '1') then
						s_nextState <= E3;
					else
						s_nextState <= E0;
					end if;
				when others =>
					s_nextState <= E0;
			end case;
		end process;
end MealyArch;