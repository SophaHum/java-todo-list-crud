document.addEventListener('DOMContentLoaded', function() {
    setTimeout(function(){
        let alerts = document.querySelectorAll('.alert');
        alerts.forEach(function(alert) {
            alert.classList.add('fade-out');
            setTimeout(function() {
                alert.remove();
            }, 500); // Adjust the timeout to match the CSS transition duration
            const toggleLink = alert.querySelector('a[href*="/toggle"]');
            toggleLink.forEach(link => {
                link.addEventListener('click', function(event) {
                    event.preventDefault();
                    const todoItem = this.closest('.list-group-item');
                    todoItem.classList.add('animate__animated', 'animate__pulse');
                    setTimeout(function() {
                        todoItem.classList.remove('animate__animated', 'animate__pulse');
                    }, 500); // Adjust the timeout to match the animation duration
                });
            });
        });
    }, 3000);

    const titleInput = document.getElementById('input[name="title"]');
    if(titleInput){
        titleInput.focus();
    }
});