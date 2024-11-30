document.querySelector("form").addEventListener("submit", (e) => {
    e.preventDefault();

    const formData = new FormData(e.target);

    const payload = Object.fromEntries(formData);


    fetch("/api/validate/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
    })
        .then(async (response) => {

            if (!response.ok) {
                const invalidFields = await response.json();

                for (let field of invalidFields) {
                    const input = document.querySelector(`#${field}`);

                    if (input) {
                        showError(input);
                    }
                }
                throw new Error("Validation failed");
            }
            return response.json();
        })
        .then(() => {
            window.location.href = "users/login";
        })
        .catch((error) => {
            console.error("Error during signup:", error.message);
        });
});

document.querySelectorAll("input").forEach(i => i.addEventListener("focus", (e) => {
    i.classList.remove("is-invalid")
    showHideMessage(i, "none");
}));

function showError(input) {
    showHideMessage(input, "block");
    input.classList.add("is-invalid");
}

function showHideMessage(input, state) {
    const closest = input.closest("div").querySelector(".invalid-feedback");

    if (closest) {
        closest.style.display = `${state}`;

        if (input.id === "floatingInput") {
            closest.textContent = !input.value
                ? "Invalid email!"
                : "User with this email already exists!";
        } else if (input.id === "floatingDate") {
            closest.textContent = "You must be at least 18 years old!";
        }
    }
}
