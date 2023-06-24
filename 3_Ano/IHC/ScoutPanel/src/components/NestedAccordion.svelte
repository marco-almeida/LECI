<script>
    export let index;
    export let person;

    const id = `${index}`;
    const innerId = `inner${index}`;

    /**
     * Returns a random number between min (inclusive) and max (exclusive)
     */
    function getRandomInt(min, max) {
        min = Math.ceil(min);
        max = Math.floor(max);
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }
</script>

<div class="accordion" id="accordionExample{id}">
    <div class="accordion-item">
        <h2 class="accordion-header" id="heading{id}">
            <button
                class="accordion-button collapsed"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#collapse{id}"
                aria-expanded="false"
                aria-controls="collapse{id}"
            >
                <img
                    src={person.image}
                    style="width: 50px; height: 50px"
                    alt=""
                />
                <div class="m-2">{person.name}</div>
            </button>
        </h2>
        <div
            id="collapse{id}"
            class="accordion-collapse collapse"
            aria-labelledby="heading{id}"
            data-bs-parent="#accordionExample{id}"
        >
            <div class="accordion-body">
                <div class="table-responsive">
                    <table class="table table-borderless">
                        <tbody>
                            <tr>
                                <td>{person.address}</td>
                                <td>{person.phoneNumber}</td>
                                <td>{person.email}</td>
                            </tr>
                            <tr>
                                <td>{person.birthDate}</td>
                                <td>{person.sex}</td>
                            </tr>
                            <tr>
                                <td>{person.bio}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="accordion" id="accordionExample{innerId}">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading{innerId}">
                            <button
                                class="accordion-button collapsed"
                                type="button"
                                data-bs-toggle="collapse"
                                data-bs-target="#collapse{innerId}"
                                aria-expanded="false"
                                aria-controls="collapse{innerId}"
                            >
                                Pagamentos
                            </button>
                        </h2>
                        <div
                            id="collapse{innerId}"
                            class="accordion-collapse collapse"
                            aria-labelledby="heading{innerId}"
                            data-bs-parent="#accordionExample{innerId}"
                        >
                            <div class="accordion-body">
                                <ul class="list-group">
                                    <li
                                        class="list-group-item d-flex justify-content-between align-items-start"
                                    >
                                        <div class="ms-2 me-auto">
                                            <div class="">
                                                <strong
                                                    >Dívida trimestral Q1 -
                                                </strong>Pago a {getRandomInt(
                                                    1,
                                                    28
                                                )
                                                    .toString()
                                                    .padStart(
                                                        2,
                                                        "0"
                                                    )}/{getRandomInt(1, 3)
                                                    .toString()
                                                    .padStart(2, "0")}
                                            </div>
                                        </div>
                                        <span
                                            class="badge bg-success rounded-pill"
                                            >24.99€</span
                                        >
                                    </li>
                                    {#if Math.random() < 0.5}
                                        <li
                                            class="list-group-item d-flex justify-content-between align-items-start"
                                        >
                                            <div class="ms-2 me-auto">
                                                <div class="">
                                                    <strong
                                                        >Dívida trimestral Q2 -
                                                    </strong>Pago a {getRandomInt(
                                                        1,
                                                        30
                                                    )
                                                        .toString()
                                                        .padStart(
                                                            2,
                                                            "0"
                                                        )}/{getRandomInt(4, 6)
                                                        .toString()
                                                        .padStart(2, "0")}
                                                </div>
                                            </div>
                                            <span
                                                class="badge bg-success rounded-pill"
                                                >24.99€</span
                                            >
                                        </li>
                                    {:else}
                                        <li
                                            class="list-group-item d-flex justify-content-between align-items-start"
                                        >
                                            <div class="ms-2 me-auto">
                                                <div class="">
                                                    <strong
                                                        >Dívida trimestral Q2 -
                                                    </strong>Em dívida
                                                </div>
                                            </div>
                                            <span
                                                class="badge bg-danger rounded-pill"
                                                >24.99€</span
                                            >
                                        </li>
                                    {/if}

                                    <li
                                        class="list-group-item d-flex justify-content-between align-items-start"
                                    >
                                        <div class="ms-2 me-auto">
                                            <div class="">
                                                <strong
                                                    >Dívida trimestral Q3 -
                                                </strong>Em dívida
                                            </div>
                                        </div>
                                        <span
                                            class="badge bg-danger rounded-pill"
                                            >24.99€</span
                                        >
                                    </li>
                                    <li
                                        class="list-group-item d-flex justify-content-between align-items-start"
                                    >
                                        <div class="ms-2 me-auto">
                                            <div class="">
                                                <strong
                                                    >Dívida trimestral Q4 -
                                                </strong>Em dívida
                                            </div>
                                        </div>
                                        <span
                                            class="badge bg-danger rounded-pill"
                                            >24.99€</span
                                        >
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
