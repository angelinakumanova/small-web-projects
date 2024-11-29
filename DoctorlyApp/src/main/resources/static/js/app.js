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
            console.log(response);
            if (!response.ok) {
                const invalidFields = await response.json();

                for (let field of invalidFields) {
                    const input = document.querySelector(`#${field}`);
                    console.log(input);
                    if (input) {
                        showError(input);
                    }
                }
                throw new Error("Validation failed");
            }
            return response.json();
        })
        .then((data) => {
            console.log("Signup successful!", data);

            window.location.href = "/users/login";
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
    if (input.id === "floatingDate" || input.id === "floatingPassword" || input.id === "floatingInput") {
        const closest = input.closest("div").children[2];
        closest.style.display = `${state}`;
        if (input.id === "floatingInput") {
            if (!input.value) {
                closest.textContent = "Invalid email!"
            } else {
                closest.textContent = "User with this email already exists!"
            }
        }
    }
}
