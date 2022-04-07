$(function () {
    $('.header').load('header.html');
    $('.footer').load('footer.html');
    $('#renewalServiceForm').bootstrapValidator({
        live: 'disabled',
        fields: {
            insurance: {
                validators: {
                    notEmpty: {
                        message: '请选择险种'
                    }
                }
            },
            userName: {
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    }
                }
            },
            phoneNum: {
                validators: {
                    notEmpty: {
                        message: '手机号码不能为空'
                    },
                    regexp: {
                        regexp: /^1\d{10}$/ ,
                        message: '请输入正确的手机号'
                    },
                }
            },
            verification: {
                validators: {
                    notEmpty: {
                        message: '验证码不能为空'
                    }
                }
            },
            carSeries: {
                validators: {
                    notEmpty: {
                        message: '请选择车系'
                    }
                }
            },
            "agree[]": {
                validators: {
                    notEmpty: {
                        message: '隐私政策未同意'
                    }
                }
            },
        }
    });
    $('#submitBtn').on('click', function () {
        $('#renewalServiceForm').bootstrapValidator('validate');
        if ($("#renewalServiceForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
            var formData = {
                insurance: $('#insurance').val(),
                name: $('#name').val(),
                phoneNumber: $('#phoneNumber').val(),
                verificationCode: $('#verificationCode').val(),
                carSeries: $('#carSeries').val(),
            }
            layer.confirm('确定提交吗?', {title: '提示'}, function (index) {
                layer.msg('提交成功', {icon: 1, time: 2000});
                layer.close(index);
            });
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
    var count = 60, timer = null;
    $(".send-btn").click(function () {
        /*防止重复点击按钮*/
        if (timer == null) {
            $(".send-btn").text("60s");
            timer = setInterval(function () {
                count--;
                $(".send-btn").text(count + "s");
                if (count <= 0) {
                    clearInterval(timer);
                    $(".send-btn").text("发送验证码");
                    timer = null;
                }
            }, 1000);
        }
    });
});
