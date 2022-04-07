$(function () {
    $('.header').load('header.html');
    $('.footer').load('footer.html');

    // 滚动到对应模块
    $('.nav-list-item').on('click', function () {
            var mainContainer = $('html'),
                scrollToContainer = mainContainer.find('.content-item'+$(this).attr('index'));
            //动画效果
            mainContainer.animate({
                scrollTop: scrollToContainer.offset().top - 80
            }, 1000);//1秒滑动到指定位置
    })
});
