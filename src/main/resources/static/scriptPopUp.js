// Funkcja do pokazywania/ukrywania popupu
function togglePopup() {
    var popup = document.getElementById("popup");
    if (popup.style.display === "none" || popup.style.display === "") {
        popup.style.display = "block";
    } else {
        popup.style.display = "none";
    }
}
