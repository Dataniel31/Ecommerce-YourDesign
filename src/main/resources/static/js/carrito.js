// Initialize cart from localStorage or empty array
let cart = JSON.parse(localStorage.getItem('cart')) || [];
updateCartCounter();

// Add event listeners
document.addEventListener('DOMContentLoaded', () => {
    // Cart button click
    document.getElementById('cart-button').addEventListener('click', toggleCart);

    // Close cart button click
    document.getElementById('close-cart').addEventListener('click', toggleCart);

    // Add to cart buttons
    document.querySelectorAll('.lbtn-primary').forEach(button => {
        button.addEventListener('click', addToCart);
    });
});

function toggleCart() {
    const cartModal = document.getElementById('cart-modal');
    cartModal.style.display = cartModal.style.display === 'block' ? 'none' : 'block';
    if (cartModal.style.display === 'block') {
        renderCart();
    }
}

function addToCart(event) {
    const productCard = event.target.closest('.card');
    const quantityInput = productCard.querySelector('.lstock-input');
    const quantity = parseInt(quantityInput.value);
    const maxStock = parseInt(quantityInput.getAttribute('max'));

    if (quantity < 1) {
        alert('La cantidad debe ser mayor a 0');
        return;
    }

    const product = {
        id: productCard.dataset.productId || Date.now().toString(),
        name: productCard.querySelector('.card-title').textContent,
        price: parseFloat(productCard.querySelector('.h4').textContent.replace('S/. ', '')),
        quantity: quantity,
        maxStock: maxStock
    };

    const existingProductIndex = cart.findIndex(item => item.id === product.id);

    if (existingProductIndex !== -1) {
        const newQuantity = cart[existingProductIndex].quantity + quantity;
        if (newQuantity <= maxStock) {
            cart[existingProductIndex].quantity = newQuantity;
            alert('Se actualizó la cantidad del producto en el carrito');
        } else {
            alert(`No se puede agregar más unidades. Stock máximo: ${maxStock}`);
            return;
        }
    } else {
        if (quantity <= maxStock) {
            cart.push(product);
            alert('Producto añadido al carrito');
        } else {
            alert(`No se puede agregar más unidades. Stock máximo: ${maxStock}`);
            return;
        }
    }

    saveCart();
    updateCartCounter();
    quantityInput.value = '1'; // Reset quantity input to 1
}

function updateCartCounter() {
    const counter = document.getElementById('cart-counter');
    const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
    counter.textContent = totalItems.toString();
}

function renderCart() {
    const cartItems = document.getElementById('cart-items');
    const cartTotal = document.getElementById('cart-total');

    cartItems.innerHTML = '';
    let total = 0;

    cart.forEach(item => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${item.name}</td>
            <td>S/. ${item.price.toFixed(2)}</td>
            <td>
                <input type="number" class="form-control quantity-control"
                       value="${item.quantity}" min="1" max="${item.maxStock}"
                       onchange="updateQuantity('${item.id}', this.value)">
            </td>
            <td>S/. ${(item.price * item.quantity).toFixed(2)}</td>
            <td>
                <button class="btn btn-danger btn-sm" onclick="removeItem('${item.id}')">
                    <i class="bi bi-trash"></i>
                </button>
            </td>
        `;
        cartItems.appendChild(row);
        total += item.price * item.quantity;
    });

    cartTotal.textContent = `S/. ${total.toFixed(2)}`;
}

function updateQuantity(productId, newQuantity) {
    const product = cart.find(item => item.id === productId);
    if (!product) return;

    newQuantity = parseInt(newQuantity);
    if (newQuantity < 1 || newQuantity > product.maxStock) {
        alert('Cantidad inválida');
        renderCart();
        return;
    }

    product.quantity = newQuantity;
    saveCart();
    updateCartCounter();
    renderCart();
}

function removeItem(productId) {
    cart = cart.filter(item => item.id !== productId);
    saveCart();
    updateCartCounter();
    renderCart();
}

function saveCart() {
    localStorage.setItem('cart', JSON.stringify(cart));
}
