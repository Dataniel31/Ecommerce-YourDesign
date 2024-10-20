document.addEventListener('DOMContentLoaded', () => {
  const productoForm = document.getElementById('productoForm');
  const idProductoInput = document.getElementById('id_producto');

  document.querySelectorAll('.btnEditar').forEach(button => {
      button.addEventListener('click', function () {
          const producto = {
              id_producto: this.getAttribute('data-id_producto'),
              nombre_prod: this.getAttribute('data-nombre_prod'),
              marca: this.getAttribute('data-marca'),
              precio: this.getAttribute('data-precio'),
              stock: this.getAttribute('data-stock'),
              imagen: this.getAttribute('data-imagen')
          };
          idProductoInput.value = producto.id_producto;
          document.getElementById('nombre_prod').value = producto.nombre_prod;
          document.getElementById('marca').value = producto.marca;
          document.getElementById('precio').value = producto.precio;
          document.getElementById('stock').value = producto.stock;
          document.getElementById('imagen').value = producto.imagen;

          productoForm.action = '/admin/productos/edit/' + producto.id_producto;
          productoForm.method = 'PUT';
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
    productoForm.reset();
    idProductoInput.value = 0;
    productoForm.setAttribute('method', 'POST');
    productoForm.setAttribute('th:action', '@{/admin/productos}');
  });

  const mostrarMensaje = (tipo, mensaje) => {
    Swal.fire({
      icon: tipo,
      title: tipo === 'error' ? 'Oops...' : '¡Éxito!',
      text: mensaje
    });
  };

  productoForm.addEventListener('submit', function (event) {
    const hasError = false;
    const isEditing = idProductoInput.value !== 0;

    if (hasError) {
      event.preventDefault();
      mostrarMensaje('error', isEditing ? 'Error al editar Producto' : 'Error al agregar Producto');
    } else {
      mostrarMensaje('success', isEditing ? 'Se actualizó el Producto exitosamente' : 'Se agregó el Producto exitosamente');
    }
  });
});
