function confirmDelete (id) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url:"http://localhost:8080/deleteClient",
        type: "POST",
        data: {
            id: id
        },
        success: function () {
            $("#myTableRow").remove();
            swal("Your Client has been deleted!", {
                icon: "success",
            });
        },
        error: function () {
            swal("Poof! server error!", {
                icon: "error",
            });
        }
    })
}

    function confirmDelete2(id) {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url:"http://localhost:8080/deleteCompte",
            type: "POST",
            data: {
                id: id
            },
            success: function () {
                $("#myTableRow").remove();
                swal("Your Compte has been deleted!", {
                    icon: "success",
                });
            },
            error: function () {
                swal("Poof! server error!", {
                    icon: "error",
                });
            }
        })
    }
