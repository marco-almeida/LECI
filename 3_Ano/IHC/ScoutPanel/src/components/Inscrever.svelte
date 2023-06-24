<script>
    import { admin_tab } from "../stores/stores.js";
    let _admin_tab;
    admin_tab.subscribe((value) => {
        _admin_tab = value;
    });

    let avatar, fileinput;
    import { nameList } from "./MemberList.svelte";
    import { nameList as nameList2 }  from "./ListaPagamentos.svelte";
    let newMemberName = "";
    let newMemberAddress = "";
    let newMemberNumber = "";
    let newMemberEmail = "";
    let newMemberBirthdate = "";
    let newMemberSex = "";
    let newMemberBio = "";
    const onFileSelected = (e) => {
        let image = e.target.files[0];
        let reader = new FileReader();
        reader.readAsDataURL(image);
        reader.onload = (e) => {
            avatar = e.target.result;
        };
    };
    function signup() {
        if (!avatar) {
            avatar = "static/default_avatar.png";
        }
        if (!newMemberName||!newMemberAddress||!newMemberNumber||!newMemberEmail||!newMemberBirthdate||!newMemberSex) {
            document.getElementById('secretButtonError').click();
            return false;
        }
        nameList.push({
            name: newMemberName,
            address: newMemberAddress,
            phoneNumber: newMemberNumber,
            email: newMemberEmail,
            birthDate: newMemberBirthdate,
            sex: newMemberSex,
            bio: newMemberBio,
            image: avatar,
        });nameList2.push({
            name: newMemberName,
            address: newMemberAddress,
            phoneNumber: newMemberNumber,
            email: newMemberEmail,
            birthDate: newMemberBirthdate,
            sex: newMemberSex,
            bio: newMemberBio,
            image: avatar,
        });
        document.getElementById('secretButton').click();
    }
</script>

<main>
    <button id='secretButton' type="button" data-bs-toggle="modal" data-bs-target="#exampleModal" style='display:none'></button>
    <button id='secretButtonError' type="button" data-bs-toggle="modal" data-bs-target="#exampleModalError" style='display:none'></button>

    <div class="shadow-none p-3 m-3 bg-light rounded">
        <h2 align="center">Formulário de inscrição</h2>
        Campos marcados com <span style="color: red;">(*)</span> são
        obrigatórios.
        <br />
        <label for="nome" class="form-label m-1">
            <span style="color: #0a58ca;">Nome Completo</span>
            <span style="color: red;">(*)</span></label
        >
        <input
            bind:value={newMemberName}
            type="text"
            class="form-control"
            id="nome"
            required
        />
        <label for="morada" class="form-label m-1">
            <span style="color: #0a58ca;">Morada</span>
            <span style="color: red;">(*)</span>
        </label>
        <input
            bind:value={newMemberAddress}
            type="text"
            class="form-control"
            id="morada"
            required
        />

        <label for="number" class="form-label m-1">
            <span style="color: #0a58ca;">Telemóvel/Telefone</span>
            <span style="color: red;">(*)</span>
        </label>
        <input
            bind:value={newMemberNumber}
            type="number"
            class="form-control"
            id="number"
            required
        />

        <label for="date" class="form-label m-1">
            <span style="color: #0a58ca;">Data de nascimento</span>
            <span style="color: red;">(*)</span>
        </label>
        <input
            bind:value={newMemberBirthdate}
            type="date"
            class="form-control"
            id="date"
            required
        />

        <label for="email" class="form-label m-1">
            <span style="color: #0a58ca;">Endereço de Email</span>
            <span style="color: red;">(*)</span>
        </label>
        <input
            bind:value={newMemberEmail}
            type="email"
            class="form-control"
            id="email"
            placeholder="name@example.com"
            required
        />
        <label for="sexo" class="form-label m-1"
            ><span style="color: #0a58ca;">Sexo</span>
            <span style="color: red;">(*)</span></label
        >
        <select bind:value={newMemberSex} class="form-select" aria-label="Sexo" required>
            <option value="Feminino">Feminino</option>
            <option value="Masculino">Masculino</option>
            <option value="Outro">Outro</option>
        </select>

        <label for="aboutme" class="form-label m-1"
            ><span style="color: #0a58ca;">Biografia</span>
        </label>
        <textarea
            bind:value={newMemberBio}
            class="form-control"
            id="exampleFormControlTextarea1"
            rows="3"
        />
        <div id="app" class="m-1 p-3">
            {#if avatar}
                <img class="avatar" src={avatar} alt="d" />
            {:else}
                <img
                    class="avatar"
                    src="static/default_avatar.png"
                    alt="Default Avatar"
                />
            {/if}
            <input
                type="image"
                class="upload"
                src="static/camera.png"
                alt="Submeter imagem"
                on:click={() => {
                    fileinput.click();
                }}
            />
            Fotografia de Escuteiro
            <input
                style="display:none"
                type="file"
                accept=".jpg, .jpeg, .png"
                on:change={(e) => onFileSelected(e)}
                bind:this={fileinput}
            />
        </div>
        <!-- Button trigger modal -->
        <button
            class="btn btn-primary"
            on:click={()=>{signup()}}
        >
            Inscrever
        </button>

        <!-- Modal -->
        <div
            class="modal fade"
            id="exampleModal"
            tabindex="-1"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
        >
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">
                            Sucesso!
                        </h1>
                        <button
                            type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"
                        />
                    </div>
                    <div class="modal-body">Membro inscrito com sucesso!</div>
                    <div class="modal-footer">
                        <button
                            type="button"
                            data-bs-dismiss="modal"
                            class="btn btn-primary"
                            on:click={() => admin_tab.update(() => "lista")}
                            >Ver lista de membros</button
                        >
                        <!-- TODO: Meter este botao a dar redirect para a lista de membros -->
                    </div>
                </div>
            </div>
        </div>
        <div
            class="modal fade"
            id="exampleModalError"
            tabindex="-1"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
        >
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">
                            Erro!
                        </h1>
                        <button
                            type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"
                        />
                    </div>
                    <div class="modal-body">Preencha todos os campos obrigatórios!</div>
                    <div class="modal-footer">
                        <button
                            type="button"
                            data-bs-dismiss="modal"
                            class="btn btn-primary"
                            >Voltar</button
                        >
                        <!-- TODO: Meter este botao a dar redirect para a lista de membros -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<style>
    #app {
        display: flex;
        align-items: center;
        justify-content: center;
        flex-flow: column;
    }

    .upload {
        display: flex;
        height: 50px;
        width: 50px;
        cursor: pointer;
    }
    .avatar {
        display: flex;
        height: 200px;
        width: 200px;
    }

    textarea {
        resize: none;
        height: auto;
        overflow-y: hidden;
    }

    textarea:active,
    textarea:focus {
        outline: none;
    }
</style>
