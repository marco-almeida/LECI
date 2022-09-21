library ieee;
use ieee.std_logic_1164.all;

entity TimerN is
    generic(K : positive := 6);
    port( clk   : in std_logic;
            start : in std_logic;
            debug : out integer;
            timerOut : out std_logic);
end TimerN;

architecture v1 of TimerN is
        signal s_counter : integer := 0; --integer 32bits
begin

    process(clk)
    begin
        if (rising_edge(clk)) then
            if (s_counter = 0) then
                if (start = '1') then
                    s_counter <= s_counter + 1;
                    timerOut <= '1';
                end if;
            elsif(s_counter < K) then
                s_counter <= s_counter + 1;
            else 
                s_counter <= 0;
                timerOut <= '0';
            end if;
        end if;
    end process;
    debug <= s_counter;
end v1;