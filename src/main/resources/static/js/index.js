document.addEventListener('DOMContentLoaded', () => {
  const dropdownBtns = document.querySelectorAll('.dropdown-btn');

  dropdownBtns.forEach((dropdownBtn) => {
    const dropdownContent = dropdownBtn.nextElementSibling;

    // Toggle the 'show' class when the button is clicked
    dropdownBtn.addEventListener('click', (event) => {
      closeAllDropdowns();

      // Check if the dropdown is already visible
      const isVisible = dropdownContent.classList.contains('show');

      // Toggle the 'show' class based on its current visibility
      dropdownContent.classList.toggle('show', !isVisible);

      // Prevent the click event from propagating to the document
      event.stopPropagation();
    });

    // Close the dropdown when clicking outside the button and the dropdown content
    document.addEventListener('click', () => {
      closeAllDropdowns();
    });

    // Prevent dropdown from closing when clicking inside the dropdown content
    dropdownContent.addEventListener('click', (event) => {
      event.stopPropagation();
    });
  });

  function closeAllDropdowns() {
    dropdownBtns.forEach((btn) => {
      const content = btn.nextElementSibling;
      content.classList.remove('show');
    });
  }
});

document.addEventListener('DOMContentLoaded', function () {
  let options = {
    strings: ["","Welcome to Gpts AI tools."],
    typeSpeed: 300,        // Adjust the speed of typing
    backSpeed: 50,         // Set backSpeed to 0 for forward counting
    startDelay: 500,      // Delay before typing starts
    backDelay: 2000,      // Delay before starting to delete
    startString: 0,       // Index of the string to start from (in this case, "Welcome")
    loop: true,
    loopCount: 10,
  };
  
  let typed = new Typed("#bg-center", options);
  
  
});

