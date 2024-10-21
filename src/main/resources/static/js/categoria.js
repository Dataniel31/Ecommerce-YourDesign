document.addEventListener('DOMContentLoaded', () => {
  const cateForm = document.getElementById('cateForm');
  const idCategoriaInput = document.getElementById('id_cate');

  document.querySelectorAll('.btnEditar').forEach(button => {
      button.addEventListener('click', function () {
          const categoria = {
              id_cate: this.getAttribute('data-id_cate'),
              nombre: this.getAttribute('data-nombre'),
              img_cate: this.getAttribute('data-img_cate')
          };
          idCategoriaInput.value = categoria.id_cate;
          document.getElementById('nombre').value = categoria.nombre;

          cateForm.action = '/admin/categorias/editar/' + categoria.id_cate;
          cateForm.method = 'POST';
      });
  });


  document.querySelectorAll('.btnEliminar').forEach(button => {
      button.addEventListener('click', function (event) {
          event.preventDefault();
          const url = this.getAttribute('href');

          Swal.fire({
              title: '¿Estás seguro?',
              text: "¡No podrás revertir esto!",
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
      cateForm.reset();
      idCategoriaInput.value = 0;
      cateForm.setAttribute('action', '/admin/categorias');
      cateForm.setAttribute('method', 'POST');
  });


  const mostrarMensaje = (tipo, mensaje) => {
      Swal.fire({
          icon: tipo,
          title: tipo === 'error' ? 'Oops...' : '¡Éxito!',
          text: mensaje
      });
  };


  marcaForm.addEventListener('submit', function (event) {
      const hasError = false;
      const isEditing = idMarcaInput.value !== 0;

      if (hasError) {
          event.preventDefault();
          mostrarMensaje('error', isEditing ? 'Error al editar Marca' : 'Error al agregar marca');
      } else {
          mostrarMensaje('success', isEditing ? 'Se actualizó la Marca exitosamente' : 'Se agregó la Marca exitosamente');
      }
  });
});
