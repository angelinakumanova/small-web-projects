document.querySelector("button").addEventListener("click", function (e) {
    let isValid = true;

    const firstName = document.getElementById("floatingFirstName");
    const lastName = document.getElementById("floatingLastName")
    const birthday = document.getElementById("floatingDate");
    const email = document.getElementById("floatingInput");
    const password = document.getElementById("floatingPassword");
    const confirmPassword = document.getElementById("floatingConfirmPassword");


    if (firstName.value.trim() === "" || firstName.value.trim().length < 2) {
        showError(firstName);
        isValid = false;
    }

    if (lastName.value.trim() === "" || lastName.value.trim().length() < 2) {
        showError(lastName);
        isValid = false;
    }

    if (birthday.value.trim() === "") {
        showError(birthday);
        isValid = false;
    }

    if (!validateEmail(email.value)) {
        showError(email);
        isValid = false;
    }

    if (password.value.length < 6) {
        showError(password);
        isValid = false;
    }

    if (password.value !== confirmPassword.value) {
        showError(confirmPassword);
        isValid = false;
    }

    if (!isValid) {
        e.preventDefault(); 
    }
});

document.querySelectorAll("input").forEach(i => i.addEventListener("click", (e) => {
    i.classList.remove("is-invalid")
    showHidePasswordMessage(i, "none");
}));

function showError(input) {
    showHidePasswordMessage(input, "block");
    input.classList.add("is-invalid");
}

function showHidePasswordMessage(input, state) {
    if (input.id === "floatingPassword") {
        document.querySelector(".invalid-feedback").style.display = `${state}`;
    }
}

function validateEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}
