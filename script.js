// Quantitative Inc. - Minimal Vanilla JavaScript

document.addEventListener('DOMContentLoaded', () => {
  // 1. Auto-update Footer Copyright Year
  const yearEl = document.getElementById('year');
  if (yearEl) {
    yearEl.textContent = new Date().getFullYear();
  }

  // 2. Handle Contact Form Submission
  const contactForm = document.getElementById('contactForm');
  if (contactForm) {
    contactForm.addEventListener('submit', (e) => {
      e.preventDefault();
      alert('Thank you for contacting Quantitative Inc.! An Aberdeen representative will reach out to you shortly.');
      contactForm.reset();
    });
  }
});
