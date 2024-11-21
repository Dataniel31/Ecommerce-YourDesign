document.addEventListener('DOMContentLoaded', () => {
    const marcaForm = document.getElementById('marcaForm');
    const idMarcaInput = document.getElementById('id_marca');

    document.querySelectorAll('.btnEditar').forEach(button => {
        button.addEventListener('click', function () {
            const marca = {
                id_marca: this.getAttribute('data-id_marca'),
                nombre: this.getAttribute('data-nombre'),
                img_marca: this.getAttribute('data-img_marca')
            };
            idMarcaInput.value = marca.id_marca;
            document.getElementById('nombre').value = marca.nombre;

            marcaForm.action = '/admin/marcas/editar/' + marca.id_marca;
            marcaForm.method = 'POST';
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
        marcaForm.reset();
        idMarcaInput.value = 0;
        marcaForm.setAttribute('action', '/admin/marcas');
        marcaForm.setAttribute('method', 'POST');
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
