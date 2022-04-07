$(function () {
    $('.footer').load('footer.html');

    $('.info-menu-item').on('click', function () {
       if (!$(this).hasClass('info-menu-item-active')) {
           $('.info-menu-item-active').removeClass('info-menu-item-active');
           $(this).addClass('info-menu-item-active');
           $('.info-item-active').removeClass('info-item-active');
           $('.info-item').eq($(this).attr('index')).addClass('info-item-active');
       }
    });

    $('.myAppoint').on('click', function () {
        $('#table').bootstrapTable("refresh");
        $("#recordModal").modal("show");
    });
});
