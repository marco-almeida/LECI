-- *NOT* minimum characteristics!

type real Nota { invariant value >= 0 and value <= 20, "Invalid grade!"; }; -- only real number in [0,20]

type table EpocaNormal {
   nome: text;
   nmec: integer;
   teorica_intercalar: Nota;
   trabalho: Nota;
   exame_teorico: Nota;
   exame_pratico: Nota;
   ep_normal: Nota := 0.2*teorica_intercalar + 0.2*exame_teorico + 0.3*trabalho + 0.3*exame_pratico;
};

type table Recurso(en: EpocaNormal) { -- table defined extending EpocaNormal
   teorico_parte1: Nota;
   teorico_parte2: Nota;
   exame_pratico: Nota;
   recurso: Nota := 0.2*(isdef(teorico_parte1) ? teorico_parte1 : en.teorica_intercalar)  +
                    0.2*(isdef(teorico_parte2) ? teorico_parte2 : en.exame_teorico)  +
                    0.3*(isdef(exame_pratico) ? exame_pratico : en.exame_pratico)  +
                    0.3*en.trabalho;
};

type table Pratica(en: EpocaNormal, re: Recurso) {
   exame_pratico_en: en.exame_pratico;
   exame_pratico_re: re.exame_pratico;
};

t1: EpocaNormal;

t1 := read "epoca-normal.csv";

t2: Recurso(t1);

t2 := read "recurso.csv";

t1 -> "Época normal";
println t1;
t2 -> "Época de recurso";
println t2;
