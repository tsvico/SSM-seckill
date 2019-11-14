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
        },
        login: function () {
            return "/seckill/getUser";
        }
    },
    state: {
        getState: function () {
            return JSON.parse(localStorage.getItem("islogin") || 'false');
        },
    },
    handleSeckillkill: function (seckillId, node) {
        //处理秒杀逻辑
        var urlNumber = Math.floor(Math.random() * 10000000 + 1);
        node = node.parent(".buyLink");
        node.hide()
            .html('<button class="btn addCartButton" id="killBtn' + urlNumber + '">开始秒杀</button>');
        //只允许第一个提交的订单被发送到订单子系统
        console.log(210,seckillId,$.cookie(""+seckillId)||false);
        if($.cookie(""+seckillId)||false){
            node.html('<button class="btn btn-success">已秒杀成功</button>');
            node.show();
            return;
        }
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
                        var killUrl = seckill.URL.execution(seckillId, exposer.md5);
                        //console.log("killUrl:" + killUrl);
                        //绑定一次点击事件
                        $('#killBtn' + urlNumber).one('click', function () {
                            //执行秒杀请求
                            $(this).addClass('disabled'); //禁用按钮
                            //发送秒杀请求
                            $.post(killUrl, {}, function (result) {
                                if (result && result.success) {
                                    let killResult = result.data;
                                    let state = killResult.state;
                                    let stateInfo = killResult.stateInfo;
                                    layer.msg(stateInfo);
                                    let tempClass = "";
                                    switch (state) {
                                        case 1:
                                            tempClass = " btn-success";
                                            break;
                                        case -1:
                                            tempClass = " btn-warning";
                                            break;
                                        case 0:
                                            tempClass = "btn-info";
                                            break;
                                        default:
                                            tempClass = "btn-danger";
                                            break;
                                    }
                                    node.html('<button class="btn ' + tempClass + '">' + stateInfo + '</button>'); //显示秒杀结果
                                    if (state === 1) {
                                        layer.msg("请稍后");
                                        setTimeout(function () {
                                            $.cookie(seckillId,1);
                                            location.href = "shopping";
                                        }, 1000)
                                    }
                                } else {
                                    layer.msg(result.error);
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
                        if (havinventory) {
                            //还有库存
                            seckill.countdown(seckillId, now, start, end);
                        } else {
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
    countdown: function (seckillId, nowTime, startTime, endTime) {
        var seckillBox = $('.buyButton');
        console.log(startTime);
        //时间判断
        if (nowTime > endTime) {
            //秒杀结束
            seckillBox.html('秒杀结束');
            $('.juhuasuan span')[1].innerHTML = '秒杀已结束';
        } else if (nowTime < startTime) {
            console.log('秒杀未开始');
            //秒杀未开始,计时事件绑定
            var killTime = new Date(startTime + wc); //加1秒，防止计时过程中时间偏移
            seckillBox.countdown(killTime, function (event) {
                //时间的格式
                var format = event.strftime('%D天%H小时%M分%S秒');
                seckillBox.html(format);
                $('.juhuasuanTime').html(format);
            }).on('finish.countdown', function () {
                //时间完成后回调事件
                //获取秒杀地址，控制显示逻辑，执行秒杀
                $('.juhuasuan span')[1].innerHTML = '已开始秒杀';
                seckill.handleSeckillkill(seckillId, seckillBox);
            })
        } else {
            //秒杀开始
            $('.juhuasuan span')[1].innerHTML = '已开始秒杀';
            seckill.handleSeckillkill(seckillId, seckillBox);
        }
    },
    login: function(){
        //未登录
        var killPhoneModel = $('#loginModal');
        killPhoneModel.modal({
            show: true, //显示弹出层
            backdrop: 'static', //禁止位置关闭
            keyboard: false //关闭键盘事件
        });
        $('.loginSubmitButton').click(function () {
            var name = $('#name').val();
            var pwd = $('#password').val();

            if (0 === name.length || 0 === pwd.length) {
                $("span.errorMessage").html("请输入账号密码");
                $("div.loginErrorMessageDiv").show();
                localStorage.removeItem("islogin");
                return false;
            }
            let reg = /^[a-zA-Z0-9_-]{4,16}$/;
            if (reg.test(name)) {
                layer.msg("登录中",{
                    icon:16,
                    time:-1
                });
                $.ajax({
                    url: seckill.URL.login(),
                    type: 'post',
                    dataType: 'json',
                    data: {username: name, password: md5(pwd)},
                   success: function (res) {
                        layer.closeAll();
                        if (res.code===1){
                            console.log(res.message);
                            $("span.errorMessage").html("登录成功");
                            $("div.loginErrorMessageDiv").show();
                            localStorage.setItem("islogin", "true");
                            //登陆成功
                            layer.msg(res.message);
                            //刷新页面
                            setTimeout(function(){window.location.reload()},1000);
                        }else{
                            //登录失败
                            $("span.errorMessage").html("账号或密码错误");
                            $("div.loginErrorMessageDiv").show();
                            localStorage.removeItem("islogin");
                            return false;
                        }
                    },
                    error: function (res) {
                        layer.closeAll();
                        console.log(res);
                        layer.msg(res.status);
                        return false;
                    }
                });
            } else {
                $("span.errorMessage").html("账号格式不正确(4到16位)");
                $("div.loginErrorMessageDiv").show();
                return false;
            }
        });
    },
    //详情页秒杀逻辑
    detail: {
        //详情页初始化
        init: function (params) {
            //手机验证和登录，计时交互
            //规划交互流程
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
                        //var nowTime = res.data;
                        let data = res.data;
                        if (data.isLogin) {
                            //已经登录
                            let nowTime = data.time;
                            wc = new Date().getTime() - nowTime;
                            seckill.countdown(seckillId, nowTime, startTime, endTime);
                            localStorage.setItem("islogin", "true");
                        } else {
                            seckill.login();
                            return false;
                        }
                    }
                }
            })
        }
    }
};
