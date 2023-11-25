$('document').ready(function() {
    $('table #editButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (address) {
            $('#idEditAddress').val(address.addressId);
            $('#cityEdit').val(address.city);
            $('#stateEdit').val(address.state);
            $('#countryEdit').val(address.country);
            $('#detailEdit').val(address.detail);
        });
        $('#editModal').modal();
    });
});

$('document1').ready(function() {
    $('table #editButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (roomType) {
            $('#idEditRoom').val(roomType.roomTypeId);
            $('#nameEdit').val(roomType.roomTypeName);
            $('#costEdit').val(roomType.roomCost);
            $('#descriptionEdit').val(roomType.description);
            $('#smokeEdit').val(roomType.smokeFriendly);
            $('#petEdit').val(roomType.petFriendly);
        });
        $('#editModal1').modal();
    });
});
$('document2').ready(function() {
    $('table #editButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (service) {
            $('#idEdit').val(service.serviceId);
            $('#name').val(service.serviceName);
            $('#description').val(service.serviceDescription);
            $('#cost').val(service.serviceCost);
        });
        $('#editModal2').modal();
    });
});
