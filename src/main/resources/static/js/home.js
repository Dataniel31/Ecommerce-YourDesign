document.addEventListener('DOMContentLoaded', function () {
  var carouselInner = document.querySelector('#brandsCarousel .carousel-inner');
  var items = Array.from(document.querySelectorAll('#brandsCarousel .col-3'));

  // Clear existing content inside carousel-inner
  carouselInner.innerHTML = '';

  var groupedItems = [];
  for (var i = 0; i < items.length; i += 4) {
    var item = document.createElement('div');
    item.classList.add('carousel-item');
    if (i === 0) item.classList.add('active');
    var row = document.createElement('div');
    row.classList.add('row', 'text-center');

    for (var j = i; j < i + 4; j++) {
      if (items[j]) {
        row.appendChild(items[j]);
      }
    }
    item.appendChild(row);
    groupedItems.push(item);
  }

  groupedItems.forEach(function(group) {
    carouselInner.appendChild(group);
  });
});
