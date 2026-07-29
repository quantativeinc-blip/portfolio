// Interactive scripts for Quantitative Inc.
document.addEventListener('DOMContentLoaded', function () {
  console.log('Quantitative Inc. site loaded.');

  // Smooth scroll for internal navigation links
  const links = document.querySelectorAll('a[href^="#"]');
  links.forEach(link => {
    link.addEventListener('click', function (e) {
      e.preventDefault();
      const targetId = this.getAttribute('href');
      if (targetId === '#') return;
      const targetElement = document.querySelector(targetId);
      if (targetElement) {
        targetElement.scrollIntoView({ behavior: 'smooth' });
      }
    });
  });
});
