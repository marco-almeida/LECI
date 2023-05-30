<script>
    import Inscrever from "./Inscrever.svelte";
    import MemberList from "./MemberList.svelte";
    import ListaPagamentos from "./ListaPagamentos.svelte";

    import { admin_tab } from "../stores/stores.js";
    let _admin_tab;
    admin_tab.subscribe((value) => {
        _admin_tab = value;
    });
</script>

<main>
    <div style="height: 100%; width: 25%; position: absolute; top: 0; left: 0; overflow: auto">
        <div class="shadow-none p-3 m-3 rounded">
            <svg
                style="width: 50px; height: 50px; color: white;"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
            />
        </div>
        <div class="shadow-none p-3 m-3 bg-light rounded">
            <a
                href="#"
                class="nav-link"
                style="display: block"
                on:click={() => admin_tab.update(() => "lista")}
                ><svg
                fill="#0A58CA"
                    style="width: 30px; height: 30px;"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    ><title>list-box-outline</title><path
                        d="M11 15H17V17H11V15M9 7H7V9H9V7M11 13H17V11H11V13M11 9H17V7H11V9M9 11H7V13H9V11M21 5V19C21 20.1 20.1 21 19 21H5C3.9 21 3 20.1 3 19V5C3 3.9 3.9 3 5 3H19C20.1 3 21 3.9 21 5M19 5H5V19H19V5M9 15H7V17H9V15Z"
                    /></svg
                >
                {#if _admin_tab == "lista"}
                    <strong>Lista de Membros</strong>
                {:else}
                    Lista de Membros
                {/if}</a
            >
        </div>
        <div class="shadow-none p-3 m-3 bg-light rounded">
            <a
                href="#"
                class="nav-link"
                style="display: block"
                on:click={() => admin_tab.update(() => "inscrever")}
            >
                <svg
                fill="#0A58CA"
                    style="width: 30px; height: 30px;"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    ><title>plus</title><path
                        d="M19,13H13V19H11V13H5V11H11V5H13V11H19V13Z"
                    /></svg
                >
                {#if _admin_tab == "inscrever"}
                    <strong>Inscrever Membro</strong>
                {:else}
                    Inscrever Membro
                {/if}</a
            >
        </div>
        <div class="shadow-none p-3 m-3 bg-light rounded">
            <a
                href="#"
                class="nav-link"
                style="display: block"
                on:click={() => admin_tab.update(() => "pedidos")}
            >
                <svg
                    xmlns="http://www.w3.org/2000/svg"
                    fill="#0A58CA"
                    width="30px"
                    height="30px"
                    viewBox="0 0 1920 1920"
                >
                    <g fill-rule="evenodd">
                        <path
                            d="M106.667 267H320v213.333h853.33V267h213.34v160h106.66V160.333h-329.1C1142.26 98.19 1083 53.667 1013.33 53.667H480c-69.665 0-128.931 44.523-150.896 106.666H0V1867h1493.33v-320h-106.66v213.33H106.667V267Zm320 106.667v-160c0-29.456 23.878-53.334 53.333-53.334h533.33c29.46 0 53.34 23.878 53.34 53.334v160H426.667Z"
                        />
                        <path
                            d="m1677.57 528.309 225.88 225.883c22.02 22.023 22.02 57.713 0 79.849L1225.8 1511.69c-10.62 10.5-24.96 16.49-39.98 16.49H959.937c-31.171 0-56.47-25.3-56.47-56.47v-225.89c0-15.02 5.986-29.36 16.489-39.86L1597.6 528.309c22.14-22.136 57.83-22.136 79.97 0Zm-155.41 235.144 146.03 146.033 115.43-115.426-146.04-146.033-115.42 115.426Zm-505.75 651.787h146.03l425.9-425.9-146.03-146.038-425.9 425.898v146.04Z"
                        />
                    </g>
                </svg>
                {#if _admin_tab == "pedidos"}
                    <strong>Pedidos de Requerimentos</strong>
                {:else}
                    Pedidos de Requerimentos
                {/if}</a
            >
        </div>
        <div class="shadow-none p-3 m-3 bg-light rounded">
            <a
                href="#"
                class="nav-link"
                style="display: block"
                on:click={() => admin_tab.update(() => "estatisticas")}
            >
                <svg
                fill="#0A58CA"
                    style="width:30px; height:30px"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    ><title>chart-line</title><path
                        d="M16,11.78L20.24,4.45L21.97,5.45L16.74,14.5L10.23,10.75L5.46,19H22V21H2V3H4V17.54L9.5,8L16,11.78Z"
                    /></svg
                > {#if _admin_tab == "estatisticas"}
                <strong>Estatísticas</strong>
                {:else}
                Estatísticas
                {/if}</a
            >
        </div>
        <div class="shadow-none p-3 m-3 bg-light rounded">
            <a
                href="#"
                class="nav-link"
                style="display: block"
                on:click={() => admin_tab.update(() => "listaPagamentos")}
            >
            <svg
            fill="#0A58CA"
            style="width: 30px; height: 30px;"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            ><title>cash</title><path
                d="M3,6H21V18H3V6M12,9A3,3 0 0,1 15,12A3,3 0 0,1 12,15A3,3 0 0,1 9,12A3,3 0 0,1 12,9M7,8A2,2 0 0,1 5,10V14A2,2 0 0,1 7,16H17A2,2 0 0,1 19,14V10A2,2 0 0,1 17,8H7Z"
            /></svg
        > {#if _admin_tab == "listaPagamentos"}
                <strong>Lista de Pagamentos</strong>
                {:else}
                Lista de Pagamentos
                {/if}</a
            >
        </div>
    </div>
    <div
        style="height: 100%; width: 75%; position: absolute; top: 0; right: 0;"
    >
        {#if _admin_tab == "inscrever"}
            <div style="height: 95%; overflow: auto">
                <Inscrever />
            </div>
        {:else if _admin_tab == "lista"}
            <div style="height: 95%; overflow: auto">
                <MemberList />
            </div>
        {:else if _admin_tab == "listaPagamentos"}
        <div style="height: 95%; overflow: auto">
            <ListaPagamentos />
        </div>
    {/if}
    </div>
</main>
