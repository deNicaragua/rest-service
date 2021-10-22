$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/get",
        dataType: "text"
        }).then(function(data) {
            $('.rate').append(data.Id);
        });
});
