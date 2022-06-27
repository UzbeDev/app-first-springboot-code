$(document).ready(function () {
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

    $("#regionId").change( function () {
        let regionId=$(this).val();
        $.ajax({
            url:'/district/byRegion/'+regionId,
            method:"GET",
            success: function (tumanlar){
                $("#districtId").empty();
                $("#districtId").append(`<option value="0" selected disabled>District tanlang</option>`)
                $.each(tumanlar, function (i, item) {
                    $("#districtId").append(`<option value=${item.id}>${item.name}</option>`)


                })
            }
        })
    })
})