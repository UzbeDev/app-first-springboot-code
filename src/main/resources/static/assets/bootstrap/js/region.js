$(document).ready(function () {
    let id = '';
    let regionTable = $('#regions').DataTable({
        ajax: {
            url: "/region/list",
            method: 'GET',
            dataSrc: ''
        },
        columns: [
            {title: "T/r", data: 'tr'},
            {title: "Name", data: 'name'},
            {title: "Country", data: 'countryName'},
            {title: "Action", data: 'id',
                render: function (data, type, row) {
                    return "<div class='row'><div class='col'><button class='btn btn-warning' id='edit'>Edit</button></div>" +
                        "<div class='col'><button class='btn btn-danger' id='delete'>Detele</button></div> </div>"

                }
            },
        ]
    })
    $.ajax({
        url:"/country/list",
        method:"GET",
        success: function (davlatlar){
            $.each(davlatlar, function (tartb, davlat){
                $("#countryId").append(`<option value=${davlat.id}>${davlat.name}</option>`)
            })
        }
    })

    $('#saveRegion').click(function () {
        let name = document.getElementById('regionName').value;
        let countryId=document.getElementById('countryId').value;
        let obj = {name: name, countryId:countryId};
        $.ajax({
            url: '/region',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(obj)
        });
        $('#regionModal').modal('toggle');
        window.location.reload();
    })

    $(regionTable.table().body()).on('click', '#delete', function () {
        let data = regionTable.row($(this).parents('tr')).data();
        id = data.id;
        $('#deleteModal').modal('toggle');
    })

    $('#deleteRegion').click(function () {
        $.ajax({
            url: '/region/' + id,
            method: 'DELETE'
        })
        $('#deleteModal').modal('toggle');
        window.location.reload();
    })

    $(regionTable.table().body()).on('click', '#edit', function () {
        let data = regionTable.row($(this).parents('tr')).data();
        id = data.id;
        $('#editModal').modal('toggle');
   })

    $.ajax({
        url:"/country/list",
        method:"GET",
        success: function (davlatlar){
            $.each(davlatlar, function (tartb, davlat){
                $("#countryIdEdit").append(`<option value=${davlat.id}>${davlat.name}</option>`)
            })
        }
    })

    $(regionTable.table().body()).on('click', '#edit', function () {
        let data = regionTable.row($(this).parents('tr')).data();
        document.getElementById('regionEditName').value=data.name;
        id = data.id;
        $('#regionEditModal').modal('toggle');
    })

    $('#editRegion').click(function () {
        let name = document.getElementById('regionEditName').value;
        let countryId=document.getElementById('countryIdEdit').value;
        let obj = {name: name, countryId:countryId};
        $.ajax({
            url: '/region/'+id,
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(obj)
        });
        $('#regionEditModal').modal('toggle');
        window.location.reload();
    })
})