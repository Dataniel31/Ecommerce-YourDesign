document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("box");
    const passwordInput = document.getElementById("password");
    const alertContainer = document.getElementById("alert-container");

    form.addEventListener("submit", (event) => {
        const password = passwordInput.value;

        // Remover alerta anterior
        alertContainer.innerHTML = "";

        if (password.length < 8) {
            event.preventDefault();

            const alert = document.createElement("div");
            alert.className = "alert alert-danger alert-dismissible fade show";
            alert.setAttribute("role", "alert");
            alert.innerHTML = `La contraseña debe tener al menos 8 caracteres.`;
            alertContainer.appendChild(alert);

            passwordInput.focus();
        }
    });
});