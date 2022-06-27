$(document).ready(function () {
    let id = '';
    let countryTable = $('#countries').DataTable({
        ajax: {
            url: "/country/list",
            method: 'GET',
            dataSrc: ''
        },
        columns: [
            {title: "T/r", data: 'tr'},
            {title: "Name", data: 'name'},
            {
                title: "Action", data: 'id',
                render: function (data, type, row) {
                    return "<div class='row'><div class='col'><button class='btn btn-warning' id='editCountry'>Edit</button></div>" +
                        "<div class='col'><button class='btn btn-danger' id='delete'>Detele</button></div> </div>"

                }
            },
        ]
    })

    $('#saveCountry').click(function () {
        let name = document.getElementById('countryName').value;
        let obj = {name: name};
        $.ajax({
            url: '/country',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(name)
        });
        $('#countryModal').modal('toggle');
        window.location.reload();
    })

    $(countryTable.table().body()).on('click', '#delete', function () {
        let data = countryTable.row($(this).parents('tr')).data();
        id = data.id;
        $('#deleteModal').modal('toggle');
    })

    $('#deleteCountry').click(function () {
        $.ajax({
            url: '/country/' + id,
            method: 'DELETE'
        })
        $('#deleteModal').modal('toggle');
        window.location.reload();
    })

    $(countryTable.table().body()).on('click', '#editCountry', function () {
        let data = countryTable.row($(this).parents('tr')).data();
        id = data.id;
        $('#editModal').modal('toggle');
    })


    $('#saveEdit').click(function () {
        let edit = document.getElementById('countryEditName').value;
        let obj = {name: edit}
        $.ajax({

            url: '/country/' + id,
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(obj)
        });
        $('#editModal').modal('toggle');
        window.location.reload();
    })
})