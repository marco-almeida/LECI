<script>
    // @ts-nocheck

    import Home from "./components/Home.svelte";
    import Admin from "./components/Admin.svelte";
    import Pagamento from "./components/Pagamento.svelte";
    import {posts} from "./stores/stores.js";
    var main_tab = "home";
    let admin_mode = false;
    let textSelected = "text";
    let postText = "";

    function publishPost() {
        console.log(posts);
        posts.update((value) => {
            return [postText, ...value];
        });
        postText = "";
    }

</script>

<main>
    <div
        style="height: 100%; width: 16%; position: absolute; top: 0; left: 0; overflow: auto"
    >
        <div class="shadow-none p-3 m-3 rounded">
            <a
                href="#top"
                class="nav-link"
                style="display: block"
                on:click={() => (main_tab = "home")}
            >
                <svg
                    style="width: 50px; height: 50px;"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    ><title>fleur-de-lis</title><path
                        d="M12 2S9 4 9 7 11 12 11 16H10S10 14 9 12C7 8 3 10 3 13S5 16 5 16C5 13 8.5 13 8.5 16H7V18H10.5L9 20S10 21 11 20L12 22L13 20C14 21 15 20 15 20L13.5 18H17V16H15.5C15.5 13 19 13 19 16C19 16 21 16 21 13S17 8 15 12C14 14 14 16 14 16H13C13 12 15 10 15 7S12 2 12 2Z"
                    /></svg
                > <strong>Scout Panel</strong>
            </a>
        </div>

        <div class="shadow-none p-3 m-3 bg-light rounded">
            <a
                href="#"
                class="nav-link"
                style="display: block"
                on:click={() => (main_tab = "perfil")}
            >
                <svg
                    fill="#0A58CA"
                    style="width: 30px; height: 30px;"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    ><title>account-outline</title><path
                        d="M12,4A4,4 0 0,1 16,8A4,4 0 0,1 12,12A4,4 0 0,1 8,8A4,4 0 0,1 12,4M12,6A2,2 0 0,0 10,8A2,2 0 0,0 12,10A2,2 0 0,0 14,8A2,2 0 0,0 12,6M12,13C14.67,13 20,14.33 20,17V20H4V17C4,14.33 9.33,13 12,13M12,14.9C9.03,14.9 5.9,16.36 5.9,17V18.1H18.1V17C18.1,16.36 14.97,14.9 12,14.9Z"
                    /></svg
                >
                {#if main_tab == "perfil"}
                    <strong>Perfil</strong>
                {:else}
                    Perfil
                {/if}</a
            >
        </div>
        <div class="shadow-none p-3 m-3 bg-light rounded">
            <a
                href="#"
                class="nav-link"
                style="display: block"
                on:click={() => (main_tab = "notificacoes")}
            >
                <svg
                    fill="#0A58CA"
                    style="width: 30px; height: 30px;"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    ><title>bell-outline</title><path
                        d="M10 21H14C14 22.1 13.1 23 12 23S10 22.1 10 21M21 19V20H3V19L5 17V11C5 7.9 7 5.2 10 4.3V4C10 2.9 10.9 2 12 2S14 2.9 14 4V4.3C17 5.2 19 7.9 19 11V17L21 19M17 11C17 8.2 14.8 6 12 6S7 8.2 7 11V18H17V11Z"
                    /></svg
                >
                {#if main_tab == "notificacoes"}
                    <strong>Notificações</strong>
                {:else}
                    Notificações
                {/if}
            </a>
        </div>
        {#if admin_mode == true}
            <div class="shadow-none p-3 m-3 bg-light rounded">
                <a
                    href="#"
                    class="nav-link"
                    style="display: block"
                    on:click={() => (main_tab = "admin")}
                >
                    <svg
                        fill="#0A58CA"
                        style="width: 30px; height: 30px;"
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 24 24"
                        ><title>security</title><path
                            d="M12,12H19C18.47,16.11 15.72,19.78 12,20.92V12H5V6.3L12,3.19M12,1L3,5V11C3,16.55 6.84,21.73 12,23C17.16,21.73 21,16.55 21,11V5L12,1Z"
                        /></svg
                    >
                    {#if main_tab == "admin"}
                        <strong>Administração</strong>
                    {:else}
                        Administração
                    {/if}</a
                >
            </div>
        {/if}
        {#if admin_mode == false}
            <div class="shadow-none p-3 m-3 bg-light rounded">
                <a
                    href="#"
                    class="nav-link"
                    style="display: block"
                    on:click={() => (main_tab = "pagamentos")}
                >
                    <svg
                        fill="#0A58CA"
                        style="width: 30px; height: 30px;"
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 24 24"
                        ><title>cash</title><path
                            d="M3,6H21V18H3V6M12,9A3,3 0 0,1 15,12A3,3 0 0,1 12,15A3,3 0 0,1 9,12A3,3 0 0,1 12,9M7,8A2,2 0 0,1 5,10V14A2,2 0 0,1 7,16H17A2,2 0 0,1 19,14V10A2,2 0 0,1 17,8H7Z"
                        /></svg
                    >
                    {#if main_tab == "pagamentos"}
                        <strong>Pagamentos</strong>
                    {:else}
                        Pagamentos
                    {/if}
                </a>
            </div>
        {/if}
        <div class="shadow-none p-3 m-3 bg-light rounded">
            <a
                href="#"
                class="nav-link"
                style="display: block"
                on:click={() => (main_tab = "definicoes")}
            >
                <svg
                    fill="#0A58CA"
                    style="width: 30px; height: 30px;"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    ><title>cog</title><path
                        d="M12,15.5A3.5,3.5 0 0,1 8.5,12A3.5,3.5 0 0,1 12,8.5A3.5,3.5 0 0,1 15.5,12A3.5,3.5 0 0,1 12,15.5M19.43,12.97C19.47,12.65 19.5,12.33 19.5,12C19.5,11.67 19.47,11.34 19.43,11L21.54,9.37C21.73,9.22 21.78,8.95 21.66,8.73L19.66,5.27C19.54,5.05 19.27,4.96 19.05,5.05L16.56,6.05C16.04,5.66 15.5,5.32 14.87,5.07L14.5,2.42C14.46,2.18 14.25,2 14,2H10C9.75,2 9.54,2.18 9.5,2.42L9.13,5.07C8.5,5.32 7.96,5.66 7.44,6.05L4.95,5.05C4.73,4.96 4.46,5.05 4.34,5.27L2.34,8.73C2.21,8.95 2.27,9.22 2.46,9.37L4.57,11C4.53,11.34 4.5,11.67 4.5,12C4.5,12.33 4.53,12.65 4.57,12.97L2.46,14.63C2.27,14.78 2.21,15.05 2.34,15.27L4.34,18.73C4.46,18.95 4.73,19.03 4.95,18.95L7.44,17.94C7.96,18.34 8.5,18.68 9.13,18.93L9.5,21.58C9.54,21.82 9.75,22 10,22H14C14.25,22 14.46,21.82 14.5,21.58L14.87,18.93C15.5,18.67 16.04,18.34 16.56,17.94L19.05,18.95C19.27,19.03 19.54,18.95 19.66,18.73L21.66,15.27C21.78,15.05 21.73,14.78 21.54,14.63L19.43,12.97Z"
                    /></svg
                >
                {#if main_tab == "definicoes"}
                    <strong>Definições</strong>
                {:else}
                    Definições
                {/if}</a
            >
        </div>
        <div class="shadow-none p-3 m-3 rounded" />
        <div class="shadow-none p-3 m-3 text-bg-primary rounded">
            <a
                href="#"
                align="center"
                class="nav-link"
                data-bs-toggle="modal"
                data-bs-target="#publishModal"
                style="display: block">Publicar</a
            >
        </div>
        <div class="form-check form-switch m-3">
            <input
                class="form-check-input"
                type="checkbox"
                role="switch"
                id="flexSwitchCheckChecked"
                bind:checked={admin_mode}
            />
            <label class="form-check-label" for="flexSwitchCheckDefault"
                >Modo de Administrador</label
            >
        </div>
    </div>
    <div
        style="height: 100%; width: 84%; position: absolute; top: 0; right: 0;"
    >
        {#if main_tab == "home"}
            <Home />
        {:else if main_tab == "admin"}
            <Admin />
        {:else if main_tab == "pagamentos"}
            <Pagamento />
        {/if}
    </div>
</main>

<!-- Modal -->
<div
    class="modal fade"
    id="publishModal"
    tabindex="-1"
    aria-labelledby="publishModalLabel"
    aria-hidden="true"
>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <div
                    class="btn-group"
                    role="group"
                    aria-label="Basic radio toggle button group"
                    id="asd"
                >
                    <input
                        type="radio"
                        class="btn-check"
                        name="btnradio"
                        id="btnradio1"
                        bind:group={textSelected}
                        value={"text"}
                        autocomplete="off"
                        checked
                    />
                    <label class="btn btn-outline-primary" for="btnradio1"
                        >Texto</label
                    >

                    <input
                        type="radio"
                        class="btn-check"
                        name="btnradio"
                        id="btnradio2"
                        bind:group={textSelected}
                        value={"media"}
                        autocomplete="off"
                    />
                    <label class="btn btn-outline-primary" for="btnradio2"
                        >Media</label
                    >

                    <input
                        type="radio"
                        class="btn-check"
                        name="btnradio"
                        id="btnradio3"
                        bind:group={textSelected}
                        value={"poll"}
                        autocomplete="off"
                    />
                    <label class="btn btn-outline-primary" for="btnradio3"
                        >Votação</label
                    >
                </div>
                <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="modal"
                    aria-label="Close"
                />
            </div>

            <div class="modal-body">
                <input
                    bind:value={postText}
                    type="text"
                    class="form-control"
                    id="nome"
                    placeholder="Descrição"
                />
                {#if textSelected == "media"}
                    <div class="mb-3">
                        <label for="formFile" class="form-label"
                            >Arrastar ou submeter imagens/videos</label
                        >
                        <input
                            class="form-control"
                            type="file"
                            id="formFile"
                            accept="image/png, image/jpeg, video/mp4 ,video/x-m4v, video/*"
                            multiple
                        />
                    </div>
                {/if}
            </div>
            <div class="modal-footer">
                <button
                    type="button"
                    on:click={publishPost}
                    class="btn btn-primary"
                    data-bs-dismiss="modal">Publicar</button
                >
            </div>
        </div>
    </div>
</div>
