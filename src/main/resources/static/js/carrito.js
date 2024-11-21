// static/js/carrito.js
let cart = JSON.parse(localStorage.getItem('cart')) || [];
let cartCounter = document.getElementById('cart-counter');
let cartModal = document.getElementById('cart-modal');
let cartItems = document.getElementById('cart-items');
let cartTotal = document.getElementById('cart-total');

// Update cart counter
function updateCartCounter() {
    cartCounter.textContent = cart.reduce((total, item) => total + item.cantidad, 0);
    localStorage.setItem('cart', JSON.stringify(cart));
}

// Add to cart
function addToCart(producto, cantidad) {
    const existingItem = cart.find(item => item.productoId === producto.id_producto);

    if (existingItem) {
        existingItem.cantidad += cantidad;
    } else {
        cart.push({
            productoId: producto.id_producto,
            nombre: producto.nombre_prod,
            precio: producto.precio,
            cantidad: cantidad
        });
    }

    updateCartCounter();
    updateCartTable();
}

// Update cart table
function updateCartTable() {
    cartItems.innerHTML = '';
    let total = 0;

    cart.forEach(item => {
        const tr = document.createElement('tr');
        const subtotal = item.precio * item.cantidad;
        total += subtotal;

        // Genera las filas dinámicamente con inputs para modificar la cantidad
        tr.innerHTML = `
            <td>${item.nombre}</td>
            <td>S/. ${item.precio.toFixed(2)}</td>
            <td>${item.cantidad}</td>
            <td>S/. ${subtotal.toFixed(2)}</td>
            <td>
                <button class="btn btn-danger btn-sm" onclick="removeFromCart('${item.productoId}')">
                    <i class="bi bi-trash"></i>
                </button>
            </td>
        `;

        cartItems.appendChild(tr);
    });

    // Actualiza el total en el DOM
    cartTotal.textContent = `S/. ${total.toFixed(2)}`;
}


// Elimina un producto del carrito
function removeFromCart(productoId) {
    cart = cart.filter(item => item.productoId !== productoId); // Filtra el producto
    updateCartCounter();
    updateCartTable(); // Vuelve a renderizar la tabla
}

// Vacía el carrito después de realizar la venta
function clearCart() {
    cart = []; // Vacía el carrito
    updateCartCounter();
    updateCartTable(); // Vuelve a renderizar la tabla
}

// Mostrar confirmación usando SweetAlert2
function generarCompra() {
    if (cart.length === 0) {
        Swal.fire({
            icon: 'warning',
            title: 'Carrito vacío',
            text: 'El carrito está vacío, agrega productos para continuar.',
            confirmButtonText: 'Aceptar'
        });
        return;
    }

    // Confirmación de compra con SweetAlert2
    Swal.fire({
        title: '¿Estás seguro?',
        text: '¿Quieres continuar con la compra?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Sí, continuar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            // Crear el DTO de la compra
            const compraDTO = {
                detalles: cart.map(item => ({
                    productoId: item.productoId,
                    cantidad: item.cantidad,
                    precioUnitario: item.precio,
                    subtotal: item.precio * item.cantidad
                })),
                total: cart.reduce((total, item) => total + (item.precio * item.cantidad), 0)
            };

            // Enviar la compra al servidor
            fetch('/api/compras/realizar', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(compraDTO)
            })
            .then(response => {
                if (response.ok) {
                    // Compra exitosa
                    Swal.fire({
                        title: '¡Compra exitosa!',
                        text: 'Tu compra ha sido procesada con éxito.',
                        icon: 'success',
                        confirmButtonText: 'Aceptar'
                    }).then(() => {
                        // Limpiar el carrito
                        clearCart();
                        location.reload();
                    });
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'Hubo un error al procesar la compra. Intenta nuevamente.',
                        confirmButtonText: 'Aceptar'
                    });
                }
            });
        }
    });
}

// Limpiar el carrito
function clearCart() {
    cart = []; // Vaciar el carrito
    updateCartCounter();
    updateCartTable(); // Volver a renderizar la tabla
}


// Event listeners
document.addEventListener('DOMContentLoaded', () => {
    updateCartCounter();
    updateCartTable();

    // Add to cart buttons
    document.querySelectorAll('.lbtn-primary').forEach(button => {
        button.addEventListener('click', (e) => {
            const card = e.target.closest('.card');
            const cantidad = parseInt(card.querySelector('.lstock-input').value);
            const producto = {
                id_producto: card.dataset.productoId,
                nombre_prod: card.querySelector('.card-title').textContent,
                precio: parseFloat(card.querySelector('.h4').textContent.replace('S/. ', ''))
            };
            addToCart(producto, cantidad);
        });
    });

    // Cart modal
    document.getElementById('cart-button').addEventListener('click', () => {
        cartModal.style.display = 'block';
    });

    document.getElementById('close-cart').addEventListener('click', () => {
        cartModal.style.display = 'none';
    });
});