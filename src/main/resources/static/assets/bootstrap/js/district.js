$(document).ready(function () {
    let id = '';
    let districtTable = $('#districts').DataTable({
        ajax: {
            url: "/district/list",
            method: 'GET',
            dataSrc: ''
        },
        columns: [
            {title: "T/r", data: 'tr'},
            {title: "Name", data: 'name'},
            {title: "Region", data: 'regionName'},
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

    $("#countryId").change( function () {
        let countryId=$(this).val();
        $.ajax({
            url:'/region/byCountry/'+countryId,
            method:"GET",
            success: function (viloyatlar){
                $("#regionId").empty();
                $("#regionId").append(`<option value="0" selected disabled>Viloyat tanlang</option>`)
                $.each(viloyatlar, function (i, item) {
                    $("#regionId").append(`<option value=${item.id}>${item.name}</option>`)


                })
            }
        })
    })

    $('#saveDistrict').click(function () {
        let name = document.getElementById('districtName').value;
        let regionId=document.getElementById('regionId').value;
        let obj = {name: name, regionId:regionId};
        if (regionId!=='0') {
            if (name !=='') {
                $.ajax({
                    url: '/district',
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: function (res) {
                        if (res.success) {
                            $('#districtModal').modal('toggle');
                            window.location.reload();
                            alert(res.message)
                        }else {
                            alert(res.message)
                        }
                    }
                });
            }else {
                alert("District bush qolmasin")
            }
        }else {
            alert("Country tanlashingiz shart")
        }
        // $('#districtModal').modal('toggle');
        // window.location.reload();
    })

    $(districtTable.table().body()).on('click', '#delete', function () {
        let data = districtTable.row($(this).parents('tr')).data();
        id = data.id;
        $('#deleteModal').modal('toggle');
    })

    $('#deleteDistrict').click(function () {
        $.ajax({
            url: '/district/' + id,
            method: 'DELETE'
        })
        $('#deleteModal').modal('toggle');
        window.location.reload();
    })

    $(districtTable.table().body()).on('click', '#edit', function () {
        let data = districtTable.row($(this).parents('tr')).data();
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

    $.ajax({
        url:"/region/list",
        method:"GET",
        success: function (regionlar){
            $.each(regionlar, function (tartb, region){
                $("#regionIdEdit").append(`<option value=${region.id}>${region.name}</option>`)
            })
        }
    })

    $(districtTable.table().body()).on('click', '#edit', function () {
        let data = districtTable.row($(this).parents('tr')).data();
         document.getElementById('districtEditName').value=data.name;
         document.getElementById('regionIdEdit').value=data.name;
        $('#districtEditModal').modal('toggle');
    })

    $('#editDistrict').click(function () {
        let regionId=document.getElementById('regionIdEdit').value;
        let name = document.getElementById('districtEditName').value;
        let obj = {name: name, regionId:regionId};
        $.ajax({
            url: '/district/'+id,
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(obj)
        });
        $('#districtEditModal').modal('toggle');
        window.location.reload();
    })
})