const usersMenuHeader = document.querySelector('.users-menu-header');
const usersMenuOptions = document.querySelector('.users-menu-options');

document.querySelectorAll('.users-menu-header').forEach(header => {
    header.addEventListener('click', function() {
        const options = this.nextElementSibling;
        options.style.display = (options.style.display === 'block') ? 'none' : 'block';
    });
});