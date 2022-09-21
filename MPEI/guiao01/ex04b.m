chancesZeroAVinte = [];
for a = 0:20
   chancesZeroAVinte(a+1) = probCalculator(0.5, a, 10, 1e5);

end
stem(0:20, chancesZeroAVinte);
disp(sum(chancesZeroAVinte))
