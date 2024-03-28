document.addEventListener('DOMContentLoaded', function() {
    const buttons = document.querySelectorAll('.menu-btn');

    buttons.forEach(button => {
        button.addEventListener('click', function() {
            const target = this.getAttribute('data-target');

            fetch(target)
                .then(response => response.text())
                .then(html => {
                    document.getElementById('contentspace').innerHTML = html;
                })
                .catch(error => {
                    console.error('Error loading the content:', error);
                    document.getElementById('contentspace').innerHTML = `<p>Error loading content.</p>`;
                });
        });
    });
});
