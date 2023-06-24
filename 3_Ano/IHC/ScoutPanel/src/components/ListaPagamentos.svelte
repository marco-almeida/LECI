<script context="module">
    export const personListNoDebt = [
        "Marco António Alves Almeida",
        "Daniel Alexandre Martins da Silva",
        "Adalberto Júnior Trindade Vaz do Rosário",
    ]
    export const personList = [
        "Maria Julia Nunes",
        "João Vitor da Rosa",
        "Ana Lídia da Mota",
        "João Guilherme da Paz",
        "Luis Gustavo Azevedo",
        "Gustavo Henrique Teixeira",
        "Maria Clara Barros",
        "Pedro Lucas da Rosa",
        "João Miguel da Rocha",
        "Vitor Gabriel Nascimento",
        "Ana Laura Barros",
        "Alexandre Tomás Rodrigues",
        "Bernardo Gonçalves",
        "Diogo Branco Silva",
        "Bruno Ferreira Gomes",
    ];
    export const nameListNoDebt = [];
    for (let x in personListNoDebt) {
        let firstNameLastName = getFirstAndLastName(personList[x]);
        nameListNoDebt.push({
            name: personListNoDebt[x],
            address: "Morada de " + firstNameLastName,
            phoneNumber: "Telemovel/Telefone de " + firstNameLastName,
            email: "Endereço de Email de " + firstNameLastName,
            birthDate: "Data de Nascimento de " + firstNameLastName,
            sex: "Sexo de " + firstNameLastName,
            bio: "Biografia de " + firstNameLastName,
            image: "static/default_avatar.png",
        });
    }
    export const nameList = [];
    for (let x in personList) {
        let firstNameLastName = getFirstAndLastName(personList[x]);
        nameList.push({
            name: personList[x],
            address: "Morada de " + firstNameLastName,
            phoneNumber: "Telemovel/Telefone de " + firstNameLastName,
            email: "Endereço de Email de " + firstNameLastName,
            birthDate: "Data de Nascimento de " + firstNameLastName,
            sex: "Sexo de " + firstNameLastName,
            bio: "Biografia de " + firstNameLastName,
            image: "static/default_avatar.png",
        });
    }
</script>

<script>
    import NestedAccordion from "./NestedAccordion.svelte";
    import NestedAccordionNoDebt from "./NestedAccordionNoDebt.svelte";

    let filteredNames = nameList;
    let filteredNamesNoDebt = nameListNoDebt;

    function getFirstAndLastName(fullName) {
        // Split the full name into an array of substrings
        const nameParts = fullName.trim().split(" ");

        // Get the first name and last name from the array
        const firstName = nameParts[0] || "";
        const lastName = nameParts[nameParts.length - 1] || "";

        // Return the first name and last name as an object
        return firstName + " " + lastName;
    }
</script>

<main>
    <div class="shadow-none p-3 m-3 bg-light rounded">
        <div class="accordion" id="accordionExample">
            <div class="accordion-item">
              <h2 class="accordion-header" id="headingOne">
                <button class="accordion-button p-5" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                    Membros sem dívidas
                </button>
              </h2>
              <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                <div class="faq-list">
                    {#each filteredNamesNoDebt as person, index}
                        <div class="m-1">
                            <NestedAccordionNoDebt {index} {person} />
                        </div>
                    {/each}
                </div>
              </div>
            </div>
            <div class="accordion-item">
              <h2 class="accordion-header" id="headingTwo">
                <button class="accordion-button collapsed p-5" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                    Membros com dívidas
                </button>
              </h2>
              <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                <div class="faq-list">
                    {#each filteredNames as person, index}
                        <div class="m-1">
                            <NestedAccordion {index} {person} />
                        </div>
                    {/each}
                </div>
              </div>
            </div>
          </div>
    </div>
</main>
