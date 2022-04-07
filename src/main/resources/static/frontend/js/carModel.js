$(function () {
    $('.header').load('header.html');
    $('.footer').load('footer.html');
    // 选中菜单
    $('.left-menu-item').eq(getQueryVariable('id')).addClass('left-menu-item-active');
    // 请求数据
    getData(getQueryVariable('id'));
    // 切换车型
    $('.left-menu-item').on('click', function () {
        if (!$(this).hasClass('left-menu-item-active')) {
            $('.left-menu-item-active').removeClass('left-menu-item-active');
            $(this).addClass('left-menu-item-active');
            // 请求数据
            getData($(this).attr('index'));
        }
    });


    /**
     * 请求数据
     * @param type 车的类型
     */
    function getData(type) {
        var loading = layer.load(3);
        $.ajax({
            type: "GET",
            url: "/frontend/json/carModel.json",
            dataType: "json",
            success: function (res) {
                switch (type) {
                    case '0':
                        renderList(res.data.car);
                        break;
                    case '1':
                        renderList(res.data.suv);
                        break;
                    case '2':
                        renderList(res.data.coupe);
                        break;
                    case '3':
                        renderList(res.data.mpv);
                        break;
                    case '4':
                        renderList(res.data.electricVehicle);
                        break;
                    case '5':
                        renderList(res.data.hybrid);
                        break;
                    default:
                        break;
                }
                layer.close(loading);
            }
        });
    }

    /**
     * 渲染列表数据
     * @param list
     */
    function renderList(list) {
        $('.car-list').empty();
        var domList = '';
        for (var i = 0; i < list.length; i++) {
            var item = list[i];
            domList += '<div class="car-list-item">\n' +
                '                <img src="' + item.img + '"/>\n' +
                '                <p class="title">' + item.name + '</p>\n' +
                '                <p class="subtitle">厂商建议零售价</p>\n' +
                '                <p class="subtitle">¥' + item.price + '万起 / 月付款 ¥' + item.monthPrice + '起</p>\n' +
                '            </div>';
        }
        $('.car-list').append(domList);
    }

    /**
     * 获取URL参数
     * @param variable 参数名
     * @returns {string|boolean}
     */
    function getQueryVariable(variable) {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            if (pair[0] == variable) {
                return pair[1];
            }
        }
        return false;
    }
});
