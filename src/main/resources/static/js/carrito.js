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
    cartItems.innerHTML = ''; // Limpia el contenido de la tabla
    let total = 0;

    cart.forEach(item => {
        const tr = document.createElement('tr');
        const subtotal = item.precio * item.cantidad;
        total += subtotal;

        // Genera las filas dinámicamente con inputs para modificar la cantidad
        tr.innerHTML = `
            <td>${item.nombre}</td>
            <td>S/. ${item.precio.toFixed(2)}</td>
            <td>
                <input type="number" class="form-control form-control-sm" value="${item.cantidad}" min="1" max="99"
                    onchange="updateQuantity('${item.productoId}', this.value)">
            </td>
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

// Actualiza la cantidad de un producto
function updateQuantity(productoId, nuevaCantidad) {
    nuevaCantidad = parseInt(nuevaCantidad, 10);

    // Valida la cantidad y actualiza el carrito
    if (nuevaCantidad > 0) {
        cart.forEach(item => {
            if (item.productoId === productoId) {
                item.cantidad = nuevaCantidad;
            }
        });
        updateCartTable(); // Vuelve a renderizar la tabla
    } else {
        alert('La cantidad debe ser mayor que cero');
    }
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

// Generate purchase
function generarCompra() {
    if (cart.length === 0) {
        alert('El carrito está vacío');
        return;
    }

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
        if (!response.ok) {
            throw new Error('Error al realizar la compra');
        }
        return response.json();
    })
    .then(data => {
        alert('Compra realizada con éxito');
        cart = [];
        updateCartCounter();
        updateCartTable();
        cartModal.style.display = 'none';
    })
    .catch(error => {
        if (error.message === 'Unauthorized') {
            window.location.href = '/login';
        } else {
            alert(error.message);
        }
    });
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
