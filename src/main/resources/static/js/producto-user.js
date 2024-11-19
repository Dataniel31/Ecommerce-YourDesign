document.querySelectorAll('input[type="number"]').forEach(input => {
    input.addEventListener('input', function() {
        const max = parseInt(this.max);
        const value = parseInt(this.value);
        if (value > max) {
            this.value = max;
        }
        if (value < 1) {
            this.value = 1;
        }
    });
});