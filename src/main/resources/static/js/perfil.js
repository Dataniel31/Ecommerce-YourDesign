document.getElementById('profileForm').addEventListener('submit', function(event) {
        const newPassword = document.getElementById('newPassword').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        if (newPassword || confirmPassword) {
            if (newPassword !== confirmPassword) {
                event.preventDefault();
                alert('Las contraseñas nuevas no coinciden');
                return false;
            }

            if (newPassword.length < 8) {
                event.preventDefault();
                alert('La contraseña debe tener al menos 6 caracteres');
                return false;
            }
        }

        return true;
});