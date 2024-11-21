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

    cartTotal.textContent = `S/. ${total.toFixed(2)}`;
}


// Elimina un producto del carrito
function removeFromCart(productoId) {
    cart = cart.filter(item => item.productoId !== productoId);
    updateCartTable();
}

// Vacía el carrito después de realizar la venta
function clearCart() {
    cart = [];
    updateCartCounter();
    updateCartTable();
}

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

    Swal.fire({
        title: '¿Estás seguro?',
        text: '¿Quieres continuar con la compra?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Sí, continuar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            const compraDTO = {
                detalles: cart.map(item => ({
                    productoId: item.productoId,
                    cantidad: item.cantidad,
                    precioUnitario: item.precio,
                    subtotal: item.precio * item.cantidad
                })),
                total: cart.reduce((total, item) => total + (item.precio * item.cantidad), 0)
            };

            fetch('/api/compras/realizar', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(compraDTO)
            })
            .then(response => {
                if (response.ok) {
                    Swal.fire({
                        title: '¡Compra exitosa!',
                        text: 'Tu compra ha sido procesada con éxito.',
                        icon: 'success',
                        confirmButtonText: 'Aceptar'
                    }).then(() => {
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
    cart = [];
    updateCartCounter();
    updateCartTable();
}


document.addEventListener('DOMContentLoaded', () => {
    updateCartCounter();
    updateCartTable();

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

    document.getElementById('cart-button').addEventListener('click', () => {
        cartModal.style.display = 'block';
    });

    document.getElementById('close-cart').addEventListener('click', () => {
        cartModal.style.display = 'none';
    });
});