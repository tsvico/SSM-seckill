//存放主要交互逻辑js代码
//javascript 模块化
var seckill = {
    //封装秒杀相关ajax
    URL: {
        now: function () {
            return "/seckill/time/now";
        },
        exposer: function (seckillId) {
            return '/seckill/' + seckillId + "/exposer";
        },
        execution: function (seckillId, md5) {
            return '/seckill/' + seckillId + "/" + md5 + '/execution';
        }
    },
    handleSeckillkill: function (seckillId, node) {
        //处理秒杀逻辑
        var urlNumber = Math.floor(Math.random() * 1000000 + 1);
        node.hide()
            .html('<button class="btn btn-primary btn-lg" id="killBtn'+urlNumber+'">开始秒杀</button>');
        $.ajax({
            url: seckill.URL.exposer(seckillId),
            type: 'post',
            datatype: 'json',
            success: function (result) {
                //在回调函数中，执行交互流程
                if (result && result.success) {
                    var exposer = result.data;
                    if (exposer.exposed) {
                        //开启秒杀
                        //获取秒杀地址
                        var md5 = exposer.md5;
                        var killUrl = seckill.URL.execution(seckillId, exposer.md5);
                        //console.log("killUrl:" + killUrl);
                        //绑定一次点击事件
                        $('#killBtn'+urlNumber).one('click', function () {
                            //执行秒杀请求
                            $(this).addClass('disabled'); //禁用按钮
                            //发送秒杀请求
                            $.post(killUrl, {}, function (result) {
                                if (result && result.success) {
                                    var killResult = result.data;
                                    var state = killResult.state;
                                    var stateInfo = killResult.stateInfo;
                                    node.html('<span class="label label-success">' + stateInfo + '</span>'); //显示秒杀结果
                                }
                            })
                        });
                        node.show();
                    } else {
                        //未开启秒杀
                        var now = exposer.now;
                        var start = exposer.start;
                        var end = exposer.end; //重新计算计时逻辑
                        var havinventory = exposer.havinventory;
                        if (havinventory){
                            //还有库存
                            seckill.countdown(seckillId, now, start, end);
                        }else {
                            //库存为0
                            node.html('秒杀结束'); //显示秒杀结果
                            node.show();
                        }
                    }
                } else {
                    console.log("result:" + result);
                }
            }
        })
    },
    validatePhone: function (phone) {
        var patt = /^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$/;
        if (phone && phone.length === 11) {
            return patt.test(phone);
        }
        return false;
    },
    countdown: function (seckillId, nowTime, startTime, endTime) {
        var seckillBox = $('#seckill-box');
        console.log(startTime);
        //时间判断
        if (nowTime > endTime) {
            //秒杀结束
            seckillBox.html('秒杀结束');
        } else if (nowTime < startTime) {
            console.log('秒杀未开始');
            //秒杀未开始,计时事件绑定
            var killTime = new Date(startTime + wc); //加1秒，防止计时过程中时间偏移
            seckillBox.countdown(killTime, function (event) {
                //时间的格式
                var format = event.strftime('秒杀倒计时：%D天%H时%M分%S秒');
                seckillBox.html(format);
            }).on('finish.countdown', function () {
                //时间完成后回调事件
                //获取秒杀地址，控制显示逻辑，执行秒杀
                seckill.handleSeckillkill(seckillId, seckillBox);
            })
        } else {
            //秒杀开始
            seckill.handleSeckillkill(seckillId, seckillBox);
        }
    },
    //详情页秒杀逻辑
    detail: {
        //详情页初始化
        init: function (params) {
            //手机验证和登录，计时交互
            //规划交互流程
            //cookie‘中查询手机号
            var killPhone = $.cookie("killPhone");

            //验证手机号
            console.log();
            if (!seckill.validatePhone(killPhone)) {
                //未登录
                var killPhoneModel = $('#killPhoneModal');
                killPhoneModel.modal({
                    show: true, //显示弹出层
                    backdrop: 'static', //禁止位置关闭
                    keyboard: false //关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killphoneKey').val();
                    if (seckill.validatePhone(inputPhone)) {
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        //刷新页面
                        window.location.reload();
                    } else {
                        $('#killphoneMessage').hide().html('<label class="label label-danger">手机号错误</label>').show(300);
                    }
                });
            }
            //已经登录
            // 计时交互
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            $.ajax({
                url: seckill.URL.now(),
                data: {},
                datatype: 'json',
                success: function (res) {
                    if (res && res.success) {
                        var nowTime = res.data;
                        wc = new Date().getTime()-nowTime;
                        seckill.countdown(seckillId, nowTime, startTime, endTime);
                    }
                }
            })
        }
    }
};
