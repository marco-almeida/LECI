chancesZeroAVinte = [];
for a = 0:20
   chancesZeroAVinte(a+1) = probCalculator(0.5, 20, a, 1e5);
end
bar(0:20, chancesZeroAVinte);
