document.addEventListener('DOMContentLoaded', () => {
  const usuarioForm = document.getElementById('usuarioForm');
  const idUsuarioInput = document.getElementById('id_usuario');
  const emailInput = document.getElementById('email');
  const originalEmailInput = document.getElementById('originalEmail');
  const passwordDiv = document.getElementById('passdiv');

  const emailExists = async (email) => {
    try {
      const response = await fetch(`/admin/usuarios/checkEmail?email=${encodeURIComponent(email)}`);
      if (!response.ok) {
        throw new Error('Error al verificar el email');
      }
      return await response.json();
    } catch (error) {
      console.error('Error en emailExists:', error);
      return false;
    }
  };

  document.querySelectorAll('.btnEditar').forEach(button => {
    button.addEventListener('click', function () {
      const usuario = {
        id_usuario: this.getAttribute('data-id_usuario'),
        nombre: this.getAttribute('data-nombre'),
        direccion: this.getAttribute('data-direccion'),
        email: this.getAttribute('data-email'),
        password: this.getAttribute('data-password'),
        rol: this.getAttribute('data-rol')
      };

      idUsuarioInput.value = usuario.id_usuario;
      document.getElementById('nombre').value = usuario.nombre;
      document.getElementById('direccion').value = usuario.direccion;
      emailInput.value = usuario.email;
      originalEmailInput.value = usuario.email;
      document.getElementById('password').value = usuario.password;
      document.getElementById('rol').value = usuario.rol;

      passwordDiv.style.display = 'none';

      usuarioForm.action = '/admin/usuarios/editar/' + usuario.id_usuario;
      usuarioForm.method = 'POST';
    });
  });

  document.querySelectorAll('.btnEliminar').forEach(button => {
    button.addEventListener('click', function (event) {
      event.preventDefault();
      const url = this.getAttribute('href');

      Swal.fire({
        title: '¿Estás seguro?',
        text: '¡No podrás revertir esto!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Sí, eliminarlo!'
      }).then((result) => {
        if (result.isConfirmed) {
          window.location.href = url;
        }
      });
    });
  });

  document.querySelector('.btnAgregar').addEventListener('click', function () {
    usuarioForm.reset();
    idUsuarioInput.value = 0;
    originalEmailInput.value = '';
    document.getElementById('rol').selectedIndex = 0;
    passwordDiv.style.display = 'block';
    usuarioForm.setAttribute('method', 'POST');
    usuarioForm.setAttribute('th:action', '@{/admin/usuarios}');
  });

  const mostrarMensaje = (tipo, mensaje) => {
    Swal.fire({
      icon: tipo,
      title: tipo === 'error' ? 'Oops...' : '¡Éxito!',
      text: mensaje
    });
  };

  usuarioForm.addEventListener('submit', async function (event) {
    event.preventDefault();

    const email = emailInput.value.trim();
    const originalEmail = originalEmailInput.value.trim();
    const isEditing = idUsuarioInput.value !== '0';

    try {
      const exists = await emailExists(email);

      if (exists && !isEditing) {
        mostrarMensaje('error', 'El email ya está registrado');
      } else if (exists && isEditing) {
        if (originalEmail === email) {
          usuarioForm.submit();
          mostrarMensaje('success', 'Se actualizó el Usuario exitosamente');
        } else {
          mostrarMensaje('error', 'El email ya está registrado');
        }
      } else {
        usuarioForm.submit();
        mostrarMensaje('success', isEditing ? 'Se actualizó el Usuario exitosamente' : 'Se agregó el Usuario exitosamente');
      }
    } catch (error) {
      mostrarMensaje('error', 'Ocurrió un error al validar el email');
      console.error(error);
    }
  });
});
