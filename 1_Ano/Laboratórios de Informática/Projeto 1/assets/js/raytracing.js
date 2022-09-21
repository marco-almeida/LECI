function rayTracing() {
  var ol, i;
  ol = document.getElementsByClassName("overlay-imagens");
  for (i = 0; i < ol.length; i++) {
    compImagens(ol[i]);
  }
  function compImagens(img) {
    var slider, img, click = 0, w, h;
    w = img.offsetWidth;
    h = img.offsetHeight;
    img.style.width = (w / 2) + "px";
    slider = document.createElement("sld");
    slider.setAttribute("class", "compSlide");
    img.parentElement.insertBefore(slider, img);
    slider.style.top = (h / 2) - (slider.offsetHeight / 2) + "px";
    slider.style.left = (w / 2) - (slider.offsetWidth / 2) + "px";
    slider.addEventListener("mousedown", darSlide);
    window.addEventListener("mouseup", pararSlide);
    slider.addEventListener("touchstart", darSlide);
    window.addEventListener("touchend", pararSlide);
    function darSlide(s) {
      s.preventDefault();
      click = 1;
      window.addEventListener("mousemove", moverSlide);
      window.addEventListener("touchmove", moverSlide);
    }
    function pararSlide() {
      click = 0;
    }
    function moverSlide(s) {
      var tr;
      if (click == 0) return false;
      tr = Cursor(s)
      if (tr < 0) tr = 0;
      if (tr > w) tr = w;
      slide(tr);
    }
    function Cursor(s) {
      var a, x = 0;
      s = s || window.event;
      a = img.getBoundingClientRect();
      x = s.pageX - a.left;
      x = x - window.pageXOffset;
      return x;
    }
    function slide(x) {
      img.style.width = x + "px";
      slider.style.left = img.offsetWidth - (slider.offsetWidth / 2) + "px";
    }
  }
}

