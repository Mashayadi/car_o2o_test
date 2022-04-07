$(function () {
    $('.header').load('header.html');
    $('.footer').load('footer.html');
    // 地图对象
    var map = null;
    // 周边POI搜索
    var placeSearch;
    // 当前位置经纬度
    var currentPoint = [113.264385, 23.129112];

    // 选中菜单
    $('.left-menu-item').eq(getQueryVariable('id')).addClass('left-menu-item-active');
    $('.content-item').eq(getQueryVariable('id')).addClass('content-item-active');
    if (getQueryVariable('id') === '1' && !map) {
        loadMap()
    }
    // 切换菜单
    $('.left-menu-item').on('click', function () {
        if (!$(this).hasClass('left-menu-item-active')) {
            $('.left-menu-item-active').removeClass('left-menu-item-active');
            $(this).addClass('left-menu-item-active');
            $('.content-item-active').removeClass('content-item-active');
            $('.content-item').eq($(this).attr('index')).addClass('content-item-active');
            if ($(this).attr('index') === '1' && !map) {
                loadMap()
            }
        }
    });

    // 故障灯切换
    $('.light-item').on('click', function () {
        if (!$(this).hasClass('light-item-active')) {
            $('.light-item-active').removeClass('light-item-active');
            $(this).addClass('light-item-active');
        }
    });

    // 切换周边
    $('.surround-menu-item').on('click', function () {
        if (!$(this).hasClass('surround-menu-item-active')) {
            $('.surround-menu-item').removeClass('surround-menu-item-active');
            $(this).addClass('surround-menu-item-active');
            if ($(this).text() === '周边停车场') {
                placeSearch.setType('停车场');
                placeSearch.clear();
                placeSearch.searchNearBy('', currentPoint, 500, function(status, result) {

                });
            } else if ($(this).text() === '周边加油站') {
                placeSearch.setType('加油站');
                placeSearch.clear();
                placeSearch.searchNearBy('', currentPoint, 1000, function(status, result) {

                });
            }
        }
    });

    // 加载地图
    function loadMap() {
        map = new AMap.Map('map-container', {
            zoom: 11,//级别
            center: [113.264385, 23.129112],//中心点坐标
            viewMode: '3D'//使用3D视图
        });
        setTimeout(function () {
            map.plugin('AMap.Geolocation', function () {
                geolocation = new AMap.Geolocation({
                    enableHighAccuracy: true,//是否使用高精度定位，默认:true
                    timeout: 10000,          //超过10秒后停止定位，默认：无穷大
                    maximumAge: 0,           //定位结果缓存0毫秒，默认：0
                    convert: true,           //自动偏移坐标，偏移后的坐标为高德坐标，默认：true
                    showButton: true,        //显示定位按钮，默认：true
                    buttonPosition: 'LB',    //定位按钮停靠位置，默认：'LB'，左下角
                    buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
                    showMarker: true,        //定位成功后在定位到的位置显示点标记，默认：true
                    showCircle: true,        //定位成功后用圆圈表示定位精度范围，默认：true
                    panToLocation: true,     //定位成功后将定位到的位置作为地图中心点，默认：true
                    zoomToAccuracy:true      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
                });
                map.addControl(geolocation);
                geolocation.getCurrentPosition();
                AMap.event.addListener(geolocation, 'complete', function (res) {
                    currentPoint = [res.position.lng, res.position.lat];
                    getPlaceSearch();
                });//返回定位信息
                AMap.event.addListener(geolocation, 'error', function () {

                });//返回定位出错信息
            });
        }, 0);
        function getPlaceSearch() {
            AMap.service(["AMap.PlaceSearch"], function() {
                //构造地点查询类
                placeSearch = new AMap.PlaceSearch({
                    type: '停车场', // 兴趣点类别
                    pageSize: 20, // 单页显示结果条数
                    pageIndex: 1, // 页码
                    city: "广州", // 兴趣点城市
                    citylimit: true,  //是否强制限制在设置的城市内搜索
                    map: map, // 展现结果的地图实例
                    panel: "panel", // 结果列表将在此容器中进行展示。
                    autoFitView: true // 是否自动调整地图视野使绘制的 Marker点都处于视口的可见范围
                });
                placeSearch.searchNearBy('', currentPoint, 500, function(status, result) {

                });
            });
        }
    }

    // 购车计算机，表单验证规则
    $('#buyCarForm').bootstrapValidator({
        live: 'disabled',
        fields: {
            carSeries: {
                validators: {
                    notEmpty: {
                        message: '请选择车系'
                    }
                }
            },
            modal: {
                validators: {
                    notEmpty: {
                        message: '请选择车型'
                    }
                }
            },
            configure: {
                validators: {
                    notEmpty: {
                        message: '请选择配置'
                    }
                }
            }
        }
    });
    // 购车计算机，开始计算
    $('#calculation-buyCar').on('click', function () {
        $('#buyCarForm').bootstrapValidator('validate');
        if ($("#buyCarForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
            var formData = {
                carSeries: $('#carSeries-buyCar').val(),
                modal: $('#modal-buyCar').val(),
                configure: $('#configure-buyCar').val(),
            }
            console.log(formData)
            // $.ajax({
            //     type : "POST",
            //     contentType: "application/json;charset=UTF-8",
            //     url : "",
            //     data : JSON.stringify(formData),
            //     success : function(result) {
            //         console.log(result);
            //     },
            //     error : function(e){
            //         console.log(e.status);
            //         console.log(e.responseText);
            //     }
            // });
        }
    });

    // 保险计算机，表单验证规则
    $('#insuranceForm').bootstrapValidator({
        live: 'disabled',
        fields: {
            carSeries: {
                validators: {
                    notEmpty: {
                        message: '请选择车系'
                    }
                }
            },
            modal: {
                validators: {
                    notEmpty: {
                        message: '请选择车型'
                    }
                }
            },
            configure: {
                validators: {
                    notEmpty: {
                        message: '请选择配置'
                    }
                }
            },
            stages: {
                validators: {
                    notEmpty: {
                        message: '请选择分期'
                    }
                }
            }
        }
    });
    // 保险计算机，开始计算
    $('#calculation-stages').on('click', function () {
        $('#insuranceForm').bootstrapValidator('validate');
        if ($("#insuranceForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
            var formData = {
                carSeries: $('#carSeries-insurance').val(),
                modal: $('#modal-insurance').val(),
                configure: $('#configure-insurance').val(),
                stages: $('#stages-insurance').val(),
            }
            console.log(formData)
            // $.ajax({
            //     type : "POST",
            //     contentType: "application/json;charset=UTF-8",
            //     url : "",
            //     data : JSON.stringify(formData),
            //     success : function(result) {
            //         console.log(result);
            //     },
            //     error : function(e){
            //         console.log(e.status);
            //         console.log(e.responseText);
            //     }
            // });
        }
    });

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
