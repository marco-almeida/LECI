# BD: Guião 5


## ​Problema 5.1
 
### *a)*

```
π Fname,Minit,Lname,Ssn, Pname (project⨝Pnumber=Pno (employee⨝Ssn=Essn works_on))
```


### *b)* 

```
temp = ρ SuperEmp (π Ssn (σ (Fname='Carlos' ∧ Minit='D' ∧ Lname='Gomes') (employee)))
π Fname,Minit,Lname (employee ⨝employee.Super_ssn=SuperEmp.Ssn temp)
```


### *c)* 

```
γ Pname; SUM(Hours) -> total_hours (works_on⨝Pno=Pnumber project)
```


### *d)* 

```
π Fname, Minit, Lname (σ (Dno=3 ∧ Hours>20 ∧ Pname = 'Aveiro Digital') (project⨝Pnumber=Pno (employee⨝Ssn=Essn works_on)))
```


### *e)* 

```
π Fname,Minit,Lname (σ Hours=null (works_on⟗Essn=Ssn employee))
```


### *f)* 

```
γ Dname; avg(Salary) -> Salary (σSex='F' (department⨝Dnumber=Dno employee))
```


### *g)* 

```
σdependents > 2 (γ Essn; count(Essn) -> dependents (employee⨝Ssn=Essn dependent))
```


### *h)* 

```
πFname,Minit,Lname,Ssn σ Essn=null (dependent⟗Essn=Mgr_ssn (department⨝Mgr_ssn=Ssn employee))
```


### *i)* 

```
π Fname,Minit,Lname,Address (σ (Plocation='Aveiro' ∧ Dlocation≠'Aveiro') (dept_location⨝Dnumber=Dno (project⨝Pnumber=Pno (employee⨝Ssn=Essn works_on))))
```


## ​Problema 5.2

### *a)*

```
σ (numero=null) (encomenda⟗fornecedor=nif fornecedor)
```

### *b)* 

```
p_avg = ρprod_avg γ codProd; avg(unidades) -> avg_units (item)
π nome, avg_units (produto⨝codigo=codProd p_avg)
```


### *c)* 

```
γ; avg(unitsByEnc) -> media_produtos_por_encomenda (γ numero; COUNT(codProd) -> unitsByEnc (encomenda⨝numero=numEnc item))
```


### *d)* 

```
γ fornecedor.nome, produto.nome; SUM(item.unidades) -> provided_units (produto⨝codigo=item.codProd (fornecedor⨝nif=fornecedor (encomenda⨝numero=numEnc item)))
```


## ​Problema 5.3

### *a)*

```
σ (numPresc=null) (paciente⟗prescricao)
```

### *b)* 

```
γ especialidade; COUNT(numPresc) -> prescricoes (medico⨝numSNS=numMedico prescricao)
```


### *c)* 

```
γ farmacia; COUNT(numPresc) -> prescricoes (σ (farmacia≠null) (prescricao))
```


### *d)* 

```
π nome (σ numPresc=null (presc_farmaco⟗nomeFarmaco=nome farmaco))
```

### *e)* 

```
γ farmacia, farmaceutica.nome ; COUNT(numPresc) -> sold (farmaceutica⨝numReg=numRegFarm (presc_farmaco⨝(σ farmacia≠null prescricao)))
```

### *f)* 

```
pat = γ numUtente; COUNT(numMedico) -> dif_doctors (π numUtente,numMedico prescricao)
π paciente.nome (paciente⨝(σ dif_doctors > 1 pat))
```
