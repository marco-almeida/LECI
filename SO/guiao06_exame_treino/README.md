# Exame prático NM 2019/2020

```cpp
pthread_t doctors[ndoctors];
pthread_t nurses[nnurses];

for (int i = 0; i < ndoctors; i++) {
    thread_create(&doctors[i], NULL, doctor_iteration, NULL);
}

for (int i = 0; i < nnurses; i++) {
    thread_create(&nurses[i], NULL, nurse_iteration, NULL);
}
```

Estamos a criar arrays de threads de medicos e nurses.
Depois estamos a popular estes arrays. O thread_create() leva de argumentos -> onde a thread deve ser criada, neste caso é o indice do array correspondente à thread. O segundo argumento nsei, nunca usei. O terceiro argumento é apenas o nome da função que a thread vai realizar. O quarto argumento é NULL caso a funçao do terceiro argumento não tenha argumentos. Se tiver, metemos aqui no 4o argumento. doctor_iteration() e nurse_iteration() não têm nada disso, então os args sao null. Depois temos isto:

```cpp
pthread_t patients[npatients];

int args[npatients];

for(uint i=0;i<npatients;i++) {
    args[i] = i;
    thread_create(&patients[i] , NULL , patient_life , &args[i]); 
}
```

Aqui já é preciso usar o 4o argumento porque a funçao patient_life(id) pede um argumento.

Se apanharmos funçoes a pedirem dois é preciso criar uma struct ARGV com os atributos sendo argumentos, e.g id e valor.

Para os patients usa-se thread join, porque assim que sao tratados, a thread deles acaba. Para nurses e doctors, so acaba a thread quando nao ha mais patients, ou seja assim que todas as threads dos patients acabarem. Então, os nurses e doctors têm que estar a fazer o seu trabalho num loop infinito (assumindo sempre que ha patients) e depois manualmente acabar a thread deles. Deve haver outra maneira de fazer isto em que se pode usar threadjoin
