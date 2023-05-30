<script context="module">
    export const personList = [
        "Marco António Alves Almeida",
        "Daniel Alexandre Martins da Silva",
        "Adalberto Júnior Trindade Vaz do Rosário",
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
    import SearchBar from "./SearchBar.svelte";

    let filteredNames = nameList;

    function handleSearch(value) {
        let searchTerm = value.trim().toLowerCase();
        filteredNames = nameList.filter(({ name }) =>
            name
                .normalize("NFD")
                .replace(/[\u0300-\u036f]/g, "")
                .toLowerCase()
                .includes(searchTerm)
        );
    }

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
        <h2 align="center">Lista de Membros</h2>
        <SearchBar onSearch={handleSearch} />
        <div class="faq-list">
            {#each filteredNames as person, index}
                <div class="m-1">
                    <NestedAccordion {index} {person} />
                </div>
            {/each}
        </div>
    </div>
</main>
