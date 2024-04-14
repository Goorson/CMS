document.addEventListener('DOMContentLoaded', function() {
    const contentspace = document.getElementById('contentspace');

    document.body.addEventListener('click', function(event) {
        if (event.target.matches('.menu-btn')) {
            const target = event.target.getAttribute('data-target');

            fetch(target)
                .then(response => response.text())
                .then(html => {
                    contentspace.innerHTML = html;
                })
                .catch(error => {
                    console.error('Error loading the content:', error);
                    contentspace.innerHTML = `<p>Error loading content.</p>`;
                });
        }
        else if (event.target.matches('.edit-btn')) {
            const target = event.target.getAttribute('data-target');

            fetch(target)
                .then(response => response.text())
                .then(html => {
                contentspace.innerHTML = html;
            })
                .catch(error => {
                console.error('Error loading the content:', error);
                contentspace.innerHTML = `<p>Error loading content.</p>`;
            });
        }
    });
});
