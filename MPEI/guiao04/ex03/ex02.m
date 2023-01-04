[users,Set] = createSet("u.data");

distances = jaccardDistance(users,Set);

threshold = 0.4;
similar = getSimilarities(users,distances,threshold);

%%Print results
N = length(similar);
for k= 1:N,
  fprintf("Distance: %f -> user 1: %d ; user 2: %d\n",similar(k,3),similar(k,1),similar(k,2));
end