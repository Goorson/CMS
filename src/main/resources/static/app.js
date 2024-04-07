document.addEventListener('DOMContentLoaded', function() {
    const contentspace = document.getElementById('contentspace');

    document.body.addEventListener('click', function(event) {
        // Check if the clicked element is a menu button
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
        // Check if the clicked element is an edit button
        else if (event.target.matches('.edit-btn')) {
            event.preventDefault(); // Prevent the default action
            const productId = event.target.getAttribute('data-id');

            // Assuming you have a route like "/products/edit/{id}" that returns the form for editing
            fetch(productId)
                .then(response => response.text())
                .then(html => {
                    contentspace.innerHTML = html;
                })
                .catch(error => {
                    console.error('Error:', error);
                    contentspace.innerHTML = `<p>Error loading form.</p>`;
                });
        }
    });
});
