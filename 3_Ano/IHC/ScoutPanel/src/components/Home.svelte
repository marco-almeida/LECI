<script>
    import Contact from "./Contact.svelte";
    import { posts } from "../stores/stores.js";
    import { nameList } from "./MemberList.svelte";
    var n = [1, 1, 1, 1, 1, 1, 1, 1, 1];
    var files;
    var fileCount = 0;
    let postText = "";
    $: if (files) {
        fileCount = files.length;
    }

    function openFileExplorer() {
        document.getElementById("file-input").click();
    }

    function publishPost() {
        console.log(posts);
        posts.update((value) => {
            return [postText, ...value];
        });
        files = [];
        postText = "";
    }

    function getRandomInt(min, max) {
        min = Math.ceil(min);
        max = Math.floor(max);
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }
</script>

<main>
    <div
        style="height: 100%; width: 72%; position: absolute; top: 0; left: 0;"
        class="p-3"
    >
        <div style="height: 100%; " class="shadow-none p-3 bg-light rounded">
            <div style="height: 100%; overflow-y: auto;">
                <!-- class= "hide-scrollbar" para esconder scrollbar -->
                <div id="top" class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-1">
                                <!-- This div will take up 10% of the left space -->
                                <div>
                                    <img
                                        src="static/default_avatar.png"
                                        style="width: 50px; heigth: 50px"
                                        class="m-1"
                                        alt="user"
                                    />
                                </div>
                            </div>
                            <div class="col-md-11">
                                <!-- The main content of the card-body goes here -->
                                <div class="mb-3">
                                    <textarea
                                        bind:value={postText}
                                        class="form-control"
                                        id="exampleFormControlTextarea1"
                                        rows="3"
                                        placeholder="Escreva algo..."
                                    />
                                </div>
                                <div
                                    class="d-flex justify-content-between align-items-center"
                                >
                                    <div class="d-flex">
                                        <button
                                            type="button"
                                            on:click={openFileExplorer}
                                            class="btn btn-link"
                                            ><i class="bi bi-image-fill" />
                                            {#if fileCount > 0}
                                                {fileCount}
                                            {/if}
                                        </button>
                                        <input
                                            bind:files
                                            type="file"
                                            id="file-input"
                                            style="display: none"
                                            accept="image/png, image/jpeg, video/mp4 ,video/x-m4v, video/*"
                                            multiple
                                        />
                                        <button
                                            type="button"
                                            class="btn btn-link"
                                            ><i
                                                class="bi bi-emoji-smile"
                                            /></button
                                        >
                                        <button
                                            type="button"
                                            class="btn btn-link"
                                            ><svg
                                                xmlns="http://www.w3.org/2000/svg"
                                                class="svg-icon"
                                                style="width: 1em; height: 1em;vertical-align: middle;fill: currentColor;overflow: hidden; vertical-align: -3px;"
                                                viewBox="0 0 1024 1024"
                                                version="1.1"
                                                ><path
                                                    d="M128 938.666667V341.333333h170.666667v597.333334H128m298.666667 0V85.333333h170.666666v853.333334h-170.666666m298.666666 0v-341.333334h170.666667v341.333334h-170.666667z"
                                                    fill=""
                                                /><path
                                                    xmlns="http://www.w3.org/2000/svg"
                                                    d="M128 938.666667V341.333333h170.666667v597.333334H128m298.666667 0V85.333333h170.666666v853.333334h-170.666666m298.666666 0v-341.333334h170.666667v341.333334h-170.666667z"
                                                    fill=""
                                                /></svg
                                            ></button
                                        >
                                    </div>
                                    <button
                                        class="btn btn-primary"
                                        on:click={publishPost}>Publicar</button
                                    >
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                {#each $posts as post}
                    <div class="p-3 mt-3 bg-body rounded" style="width: 100%">
                        <img
                            src="static/default_avatar.png"
                            style="width: 50px; heigth: 50px"
                            class="m-3"
                            alt="user"
                        />
                        <h5 style="display: inline" class="">Você</h5>
                        <p class="m-3">{post}</p>
                        <div
                        style="width: 100%; display: flex; justify-content: space-between;"
                    >
                        <div
                            style="height: 100%; width: 33%; text-align: center;"
                        >
                            <svg
                                style="width: 25px; heigth: 25px"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 24 24"
                                ><title>share-outline</title><path
                                    style="fill: blue;"
                                    d="M14,5V9C7,10 4,15 3,20C5.5,16.5 9,14.9 14,14.9V19L21,12L14,5M16,9.83L18.17,12L16,14.17V12.9H14C11.93,12.9 10.07,13.28 8.34,13.85C9.74,12.46 11.54,11.37 14.28,11L16,10.73V9.83Z"
                                /></svg
                            >
                            0
                        </div>
                        <div style="width: 33%; text-align: center">
                            <svg
                                style="width: 22px; heigth: 22px"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 24 24"
                                ><title>comment-outline</title><path
                                    style="fill: green;"
                                    d="M9,22A1,1 0 0,1 8,21V18H4A2,2 0 0,1 2,16V4C2,2.89 2.9,2 4,2H20A2,2 0 0,1 22,4V16A2,2 0 0,1 20,18H13.9L10.2,21.71C10,21.9 9.75,22 9.5,22V22H9M10,16V19.08L13.08,16H20V4H4V16H10Z"
                                /></svg
                            >
                            0
                        </div>
                        <div style="width: 33%; text-align: center">
                            <svg
                                style="width: 25px; heigth: 25px"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 24 24"
                                ><title>heart-outline</title><path
                                    style="fill: red;"
                                    d="M12.1,18.55L12,18.65L11.89,18.55C7.14,14.24 4,11.39 4,8.5C4,6.5 5.5,5 7.5,5C9.04,5 10.54,6 11.07,7.36H12.93C13.46,6 14.96,5 16.5,5C18.5,5 20,6.5 20,8.5C20,11.39 16.86,14.24 12.1,18.55M16.5,3C14.76,3 13.09,3.81 12,5.08C10.91,3.81 9.24,3 7.5,3C4.42,3 2,5.41 2,8.5C2,12.27 5.4,15.36 10.55,20.03L12,21.35L13.45,20.03C18.6,15.36 22,12.27 22,8.5C22,5.41 19.58,3 16.5,3Z"
                                /></svg
                            >
                            0
                        </div>
                    </div>
                    </div>
                {/each}

                <div class="p-3 mt-3 bg-body rounded" style="width: 100%">
                    <img
                        src="static/default_avatar.png"
                        style="width: 50px; heigth: 50px"
                        class="m-3"
                        alt="user"
                    />
                    <h5 style="display: inline" class="">Maria Nunes</h5>
                    <p class="m-3">Grande dia!</p>
                    <img
                        src="https://flordelis.escutismo.pt/wp-content/uploads/sites/2/2021/01/porque-contratar-uma-pessoa-que-tenha-sido-escuteira.jpg"
                        style="width: 100%"
                        alt="nerd"
                    />
                    <div
                        style="width: 100%; display: flex; justify-content: space-between;"
                    >
                        <div
                            style="height: 100%; width: 33%; text-align: center;"
                        >
                            <svg
                                style="width: 25px; heigth: 25px"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 24 24"
                                ><title>share-outline</title><path
                                    style="fill: blue;"
                                    d="M14,5V9C7,10 4,15 3,20C5.5,16.5 9,14.9 14,14.9V19L21,12L14,5M16,9.83L18.17,12L16,14.17V12.9H14C11.93,12.9 10.07,13.28 8.34,13.85C9.74,12.46 11.54,11.37 14.28,11L16,10.73V9.83Z"
                                /></svg
                            >
                            {getRandomInt(10, 20)}
                        </div>
                        <div style="width: 33%; text-align: center">
                            <svg
                                style="width: 22px; heigth: 22px"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 24 24"
                                ><title>comment-outline</title><path
                                    style="fill: green;"
                                    d="M9,22A1,1 0 0,1 8,21V18H4A2,2 0 0,1 2,16V4C2,2.89 2.9,2 4,2H20A2,2 0 0,1 22,4V16A2,2 0 0,1 20,18H13.9L10.2,21.71C10,21.9 9.75,22 9.5,22V22H9M10,16V19.08L13.08,16H20V4H4V16H10Z"
                                /></svg
                            >
                            {getRandomInt(5, 15)}
                        </div>
                        <div style="width: 33%; text-align: center">
                            <svg
                                style="width: 25px; heigth: 25px"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 24 24"
                                ><title>heart-outline</title><path
                                    style="fill: red;"
                                    d="M12.1,18.55L12,18.65L11.89,18.55C7.14,14.24 4,11.39 4,8.5C4,6.5 5.5,5 7.5,5C9.04,5 10.54,6 11.07,7.36H12.93C13.46,6 14.96,5 16.5,5C18.5,5 20,6.5 20,8.5C20,11.39 16.86,14.24 12.1,18.55M16.5,3C14.76,3 13.09,3.81 12,5.08C10.91,3.81 9.24,3 7.5,3C4.42,3 2,5.41 2,8.5C2,12.27 5.4,15.36 10.55,20.03L12,21.35L13.45,20.03C18.6,15.36 22,12.27 22,8.5C22,5.41 19.58,3 16.5,3Z"
                                /></svg
                            >
                            {getRandomInt(30, 70)}
                        </div>
                    </div>
                </div>
                <div class="p-3 mt-3 bg-body rounded" style="width: 100%">
                    <img
                        src="static/default_avatar.png"
                        style="width: 50px; heigth: 50px"
                        class="m-3"
                        alt="user"
                    />
                    <h5 style="display: inline" class="">João Rosa</h5>
                    <p class="m-3">
                        A melhor foto que tiramos há duas semanas!
                    </p>
                    <img
                        src="https://escutismo.pt/img/full/dirigentes_161122022331_lisboa.jpg"
                        style="width: 100%"
                        alt=""
                    />
                    <div
                        style="width: 100%; display: flex; justify-content: space-between;"
                    >
                        <div
                            style="height: 100%; width: 33%; text-align: center;"
                        >
                            <svg
                                style="width: 25px; heigth: 25px"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 24 24"
                                ><title>share-outline</title><path
                                    style="fill: blue;"
                                    d="M14,5V9C7,10 4,15 3,20C5.5,16.5 9,14.9 14,14.9V19L21,12L14,5M16,9.83L18.17,12L16,14.17V12.9H14C11.93,12.9 10.07,13.28 8.34,13.85C9.74,12.46 11.54,11.37 14.28,11L16,10.73V9.83Z"
                                /></svg
                            >
                            {getRandomInt(10, 20)}
                        </div>
                        <div style="width: 33%; text-align: center">
                            <svg
                                style="width: 22px; heigth: 22px"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 24 24"
                                ><title>comment-outline</title><path
                                    style="fill: green;"
                                    d="M9,22A1,1 0 0,1 8,21V18H4A2,2 0 0,1 2,16V4C2,2.89 2.9,2 4,2H20A2,2 0 0,1 22,4V16A2,2 0 0,1 20,18H13.9L10.2,21.71C10,21.9 9.75,22 9.5,22V22H9M10,16V19.08L13.08,16H20V4H4V16H10Z"
                                /></svg
                            >
                            {getRandomInt(5, 15)}
                        </div>
                        <div style="width: 33%; text-align: center">
                            <svg
                                style="width: 25px; heigth: 25px"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 24 24"
                                ><title>heart-outline</title><path
                                    style="fill: red;"
                                    d="M12.1,18.55L12,18.65L11.89,18.55C7.14,14.24 4,11.39 4,8.5C4,6.5 5.5,5 7.5,5C9.04,5 10.54,6 11.07,7.36H12.93C13.46,6 14.96,5 16.5,5C18.5,5 20,6.5 20,8.5C20,11.39 16.86,14.24 12.1,18.55M16.5,3C14.76,3 13.09,3.81 12,5.08C10.91,3.81 9.24,3 7.5,3C4.42,3 2,5.41 2,8.5C2,12.27 5.4,15.36 10.55,20.03L12,21.35L13.45,20.03C18.6,15.36 22,12.27 22,8.5C22,5.41 19.58,3 16.5,3Z"
                                /></svg
                            >
                            {getRandomInt(30, 70)}
                        </div>
                    </div>
                </div>

                <div class="p-3 mt-3 bg-body rounded" style="width: 100%">
                    <img
                        src="static/default_avatar.png"
                        style="width: 50px; heigth: 50px"
                        class="m-3"
                        alt="user"
                    />
                    <h5 style="display: inline" class="">
                        Alexandre Rodrigues
                    </h5>
                    <p class="m-3">Foto de grupo de ontem!</p>
                    <img
                        src="https://agencia.ecclesia.pt/portal/wp-content/uploads/2018/04/Escutismo.jpg"
                        style="width: 100%"
                        alt=""
                    />
                    <div
                        style="width: 100%; display: flex; justify-content: space-between;"
                    >
                        <div
                            style="height: 100%; width: 33%; text-align: center;"
                        >
                            <svg
                                style="width: 25px; heigth: 25px"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 24 24"
                                ><title>share-outline</title><path
                                    style="fill: blue;"
                                    d="M14,5V9C7,10 4,15 3,20C5.5,16.5 9,14.9 14,14.9V19L21,12L14,5M16,9.83L18.17,12L16,14.17V12.9H14C11.93,12.9 10.07,13.28 8.34,13.85C9.74,12.46 11.54,11.37 14.28,11L16,10.73V9.83Z"
                                /></svg
                            >
                            {getRandomInt(10, 20)}
                        </div>
                        <div style="width: 33%; text-align: center">
                            <svg
                                style="width: 22px; heigth: 22px"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 24 24"
                                ><title>comment-outline</title><path
                                    style="fill: green;"
                                    d="M9,22A1,1 0 0,1 8,21V18H4A2,2 0 0,1 2,16V4C2,2.89 2.9,2 4,2H20A2,2 0 0,1 22,4V16A2,2 0 0,1 20,18H13.9L10.2,21.71C10,21.9 9.75,22 9.5,22V22H9M10,16V19.08L13.08,16H20V4H4V16H10Z"
                                /></svg
                            >
                            {getRandomInt(5, 15)}
                        </div>
                        <div style="width: 33%; text-align: center">
                            <svg
                                style="width: 25px; heigth: 25px"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 24 24"
                                ><title>heart-outline</title><path
                                    style="fill: red;"
                                    d="M12.1,18.55L12,18.65L11.89,18.55C7.14,14.24 4,11.39 4,8.5C4,6.5 5.5,5 7.5,5C9.04,5 10.54,6 11.07,7.36H12.93C13.46,6 14.96,5 16.5,5C18.5,5 20,6.5 20,8.5C20,11.39 16.86,14.24 12.1,18.55M16.5,3C14.76,3 13.09,3.81 12,5.08C10.91,3.81 9.24,3 7.5,3C4.42,3 2,5.41 2,8.5C2,12.27 5.4,15.36 10.55,20.03L12,21.35L13.45,20.03C18.6,15.36 22,12.27 22,8.5C22,5.41 19.58,3 16.5,3Z"
                                /></svg
                            >
                            {getRandomInt(30, 70)}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div
        style="height: 100%; width: 28%; position: absolute; top: 0; right: 0;"
        class="p-3"
    >
        <div style="height: 100%" class="shadow-none p-3 bg-light rounded">
            <div style="height: 50%" class="shadow-none m-1 rounded">
                <div class="mb-3" style="text-align: center">
                    <h5><b>Contactos</b></h5>
                </div>
                <div style="height: 85%; overflow: auto">
                    {#each nameList as chatName}
                        <Contact person={chatName} />
                    {/each}
                </div>
            </div>
            <div style="height: 50%" class="shadow-none m-1 rounded">
                <div class="mb-3" style="text-align: center">
                    <h5><b>João Rosa</b></h5>
                </div>
                <div style="height: 85%; overflow: auto">
                    <div>
                        <div class="mb-1 p-3 bg-body" style="width: 100%">
                            <p style="display: inline" class="">
                                Vais a alguma atividade no próximo mês?
                            </p>
                        </div>
                        <div
                            class="mb-1 p-3 bg-body"
                            style="width: 100%; text-align: right"
                        >
                            <p style="display: inline" class="">
                                Acho que só vou a uma
                            </p>
                        </div>
                        <div class="mb-1 p-3 bg-body" style="width: 100%">
                            <p style="display: inline" class="">Qual?</p>
                        </div>
                        <div
                            class="mb-1 p-3 bg-body"
                            style="width: 100%; text-align: right"
                        >
                            <p style="display: inline" class="">Dia 26</p>
                        </div>
                        <div class="mb-1 p-3 bg-body" style="width: 100%">
                            <p style="display: inline" class="">
                                Vemo-nos lá então!!
                            </p>
                        </div>
                        <textarea class="form-control" style="height: 20px" />
                        <button
                            class="form-control btn btn-primary"
                            style="height: 1%;">Enviar</button
                        >
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
