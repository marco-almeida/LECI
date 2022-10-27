function val = leiPoisson (m, k)
  val = (m.^k / factorial(k)) * exp(-m);

end