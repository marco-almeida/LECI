 -- guiao 9 parte 1 modelo Moore
 library ieee;
use ieee.std_logic_1164.all;

entity SeqDetFSMMoore is
    port(Xin   : in  std_logic;
          clk   : in  std_logic;
          reset : in  std_logic; 
          Yout  : out std_logic
            );

end SeqDetFSMMoore;

architecture MooreArch of SeqDetFSMMoore is
    type Tstate is (E0, E1, E2, E3, E4);
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
        Yout <= '0';
            case s_currentState is
                when E0 =>
                    if(Xin = '1') then 
                        s_nextState <= E1;
                    else
                        s_nextState <= E0;
                    end if;
                when E1 =>
                    if(Xin = '0') then 
                        s_nextState <= E2;
                    else
                        s_nextState <= E1;
                    end if;
                when E2 =>
                    if(Xin = '0') then 
                        s_nextState <= E3;
                    else
                        s_nextState <= E1;
                    end if;
                when E3 =>
                    if(Xin = '1') then 
                        s_nextState <= E4;
                    else
                        s_nextState <= E0;
                    end if;
                when E4 =>
                    Yout <= '1';
                    if(Xin = '1') then 
                        s_nextState <= E1;
                    else
                        s_nextState <= E2;
                    end if;
                when others =>
                    s_nextState <= E0;
            end case;
        end process;
end MooreArch;