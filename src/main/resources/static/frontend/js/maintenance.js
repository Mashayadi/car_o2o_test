$(function () {
    $('.header').load('header.html');
    $('.footer').load('footer.html');
    $('#appointmentTime').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true
    })
    $('#maintenanceForm').bootstrapValidator({
        live: 'disabled',
        fields: {
            phoneNum: {
                validators: {
                    notEmpty: {
                        message: '手机号码不能为空'
                    },
                    regexp: {
                        regexp: /^1\d{10}$/,
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
            dateVal: {
                validators: {
                    notEmpty: {
                        message: '预约时间不能为空'
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
        $('#maintenanceForm').bootstrapValidator('validate');
        if ($("#maintenanceForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
            var formData = {
                verificationCode: $('#verificationCode').val(),
                appointmentTime: $('#appointmentTime').val(),
                serviceType:$('input[name="serviceType"]:checked').val(),
                info: $('#problemDescription').val(),
            }
            layer.confirm('确定提交吗?', {title: '提示'}, function (index) {
                 $.ajax({
                     type : "POST",
                     contentType: "application/json;charset=UTF-8",
                     url : "/appoint",
                     data : JSON.stringify(formData),
                     success : function(result) {
                         if(result.success){
                             $("#maintenanceForm input").val("");
                             $('#problemDescription').val("")
                             layer.msg('提交成功', {icon: 1, time: 2000});
                             layer.close(index);
                         }else{
                             layer.msg(result.msg, {icon: 1, time: 2000});
                             layer.close(index);
                         }
                     }
                 });

            });

        }
    });
    // 发送验证码
    var count = 60, timer = null;
    $(".send-btn").click(function () {
        var phone = $('#phoneNumber').val();
        if (!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(phone))) {
            alert("手机号码有误，请重填");
            return false;
        }
        /*防止重复点击按钮*/
        if (timer == null) {
            $.ajax({
                url:"/sendMsgCode",
                data:{phone:phone},
                success:function(result){
                    if(result.success){
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
                    }else{
                        alert(data.msg);
                    }
                }
            });

        }
    });
});
