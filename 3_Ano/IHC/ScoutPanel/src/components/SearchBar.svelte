<script>
    export let onSearch = () => {};
</script>

<div class="search-bar">
    <div class="search-input">
        <input
            type="text"
            placeholder="Pesquisar por nome"
            on:input={(e) => onSearch(e.target.value)}
        />
        <span class="search-icon">&#128269;</span>
    </div>
</div>

<style>
    .search-bar {
        display: flex;
        justify-content: center;
        margin-bottom: 2rem;
    }

    .search-input {
        position: relative;
        width: 100%;
        max-width: 600px;
    }

    input {
        width: 100%;
        max-width: 600px;
        padding: 0.5rem;
        border: 1px solid black;
        border-radius: 5px;
        font-size: 1rem;
        font-weight: 400;
        outline: none;
        transition: all 0.3s;
    }

    input:focus {
        background-color: #ffffff;
        border-color: #999999;
        box-shadow: 0 0 0 2px rgba(0, 0, 0, 0.1);
    }

    input::placeholder {
        color: #999;
    }

    .search-icon {
        position: absolute;
        right: 10px;
        top: 50%;
        transform: translateY(-50%);
        font-size: 1.2rem;
        color: #999;
    }
</style>
