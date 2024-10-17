document.addEventListener("DOMContentLoaded", function() {
        const exampleModal = new bootstrap.Modal(document.getElementById('exampleModal2'));
        const form = document.querySelector('#exampleModal2 form');
        const idInput = form.querySelector('#id_usuarioe');
        const nombreInput = form.querySelector('#nombree');
        const direccionInput = form.querySelector('#direccione');
        const emailInput = form.querySelector('#emaile');
        const rolSelect = form.querySelector('#role');

        document.querySelectorAll('.btnEditar').forEach(button => {
            button.addEventListener('click', function() {
                idInput.value = this.getAttribute('data-id_usuario');
                nombreInput.value = this.getAttribute('data-nombre');
                direccionInput.value = this.getAttribute('data-direccion');
                emailInput.value = this.getAttribute('data-email');

                const rol = this.getAttribute('data-rol');
                for (let option of rolSelect.options) {
                    if (option.value === rol) {
                        option.selected = true;
                        break;
                    }
                }

                exampleModal.show();
            });
        });
    });