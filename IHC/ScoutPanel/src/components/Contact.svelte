<script>
    export let person;
</script>

<div class="mb-1 bg-body rounded" style="width: 100%">
    <img
        src={person.image}
        style="width: 20px; heigth: 20px"
        class="m-3"
        alt="user"
    />
    <p style="display: inline" class="">
        {person.name.split(" ")[0]}
        {person.name.split(" ").slice(-1)[0]}
    </p>
</div>
