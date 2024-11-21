document.getElementById('profileForm').addEventListener('submit', function (event) {
    // Limpiar mensajes de error previos
    document.getElementById('currentPasswordError').textContent = '';
    document.getElementById('newPasswordError').textContent = '';
    document.getElementById('confirmPasswordError').textContent = '';

    const currentPassword = document.getElementById('currentPassword').value;
    const newPassword = document.getElementById('newPassword').value;
    const confirmPassword = document.getElementById('confirmPassword').value;

    let isValid = true;

    if (newPassword || confirmPassword) {
        if (newPassword !== confirmPassword) {
            document.getElementById('confirmPasswordError').textContent =
                'Las contraseñas nuevas no coinciden.';
            isValid = false;
        }

        if (newPassword.length < 8) {
            document.getElementById('newPasswordError').textContent =
                'La contraseña debe tener al menos 8 caracteres.';
            isValid = false;
        }

        if (currentPassword === newPassword) {
            document.getElementById('newPasswordError').textContent =
                'La contraseña nueva no puede ser igual a la contraseña actual.';
            isValid = false;
        }
    }

    if (!isValid) {
        event.preventDefault();
    }
});
