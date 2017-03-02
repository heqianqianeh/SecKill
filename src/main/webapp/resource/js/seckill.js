/**
 * Created by heqianqian on 2017/3/1.
 */
var seckill = {
    URL:{
        now:function () {
            return "/seckill/time/now";
        },
        expose:function (seckillId) {
            return "/seckill/"+seckillId+"/expose";
        },
        execute:function (seckillId,md5) {
            return "/seckill/"+seckillId+"/"+md5+"/execute";
        }
    },
    //验证手机号
    validatePhone:function (phone) {
        if(phone&&phone.length>5&&!isNaN(phone)){
            return true;
        }else{
            return false;
        }
    },
    //执行秒杀
    handleSeckill:function (seckillId,node) {
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.get(seckill.URL.expose(seckillId),{},function (result) {
            if(result&&result["success"]){
                var expose = result["data"];
                if(expose["expose"]){
                    //开启秒杀
                    var md5 = expose["md5"];
                    $("#killBtn").one("click",function () {
                        //禁用按钮
                        $(this).addClass("disabled");
                        //发送秒杀请求
                        $.get(seckill.URL.execute(seckillId,md5),{},function (result) {
                            if(result&&result["success"]){
                                var killResult = result["data"];
                                var state = killResult["state"];
                                var stateInfo = killResult["stateInfo"];
                                //显示秒杀结果
                                node.html('<span class="label label-success">' + stateInfo + '</span>');
                            }
                        });
                    });
                    node.show();
                }else {//秒杀未开启
                    var now = expose["currentTime"];
                    var start = expose["startTime"];
                    var end = expose["endTime"];
                    seckill.countDown(seckillId,now,start,end);
                }
            }else{
                console.log("result="+result);
            }
        });
    },
    //倒计时
    countDown:function (seckillId,nowTime,startTime,endTime) {
        var seckillBox = $('#seckillBox');
        //时间判断
        if(nowTime>endTime){//秒杀结束
            seckillBox.html("秒杀结束");
        }else if(nowTime<startTime){//秒杀未开始
            console.log("秒杀未开始");
            //开始倒计时
            var killTime = new Date(startTime+1000);
            seckillBox.countdown(killTime,function (result) {
                var format = result.strftime("秒杀倒计时 %D天 %H时 %M分 %S秒");
                seckillBox.html(format);
            }).on("finish.countdown",function () {
                //执行秒杀
                seckill.handleSeckill(seckillId,seckillBox);
            });
        }else{//秒杀开始
            //执行秒杀
            seckill.handleSeckill(seckillId,seckillBox);
        }
    },
    detail:{
        init:function (params) {
            var killPhone = $.cookie("userPhone");
            if(!seckill.validatePhone(killPhone)) {
                var killPhoneModal = $("#killPhoneModal");
                killPhoneModal.modal({//显示弹出层
                    show:true,
                    backdrop:"static",
                    keyboard:false
                });
                $("#killPhoneBtn").click(function () {
                    var inputPhone = $("#killphoneKey").val();//拿到输入手机号
                    if(seckill.validatePhone(inputPhone)){
                        //电话写入cookie
                        $.cookie("userPhone",inputPhone,{expires:7,path:"/seckill"});
                        //刷新页面
                        window.location.reload();
                    }else{
                        $('#killphoneMessage').hide().html('<label class="label label-danger">手机号错误!</label>').show(300);
                    }
                });
            }
            //已经登录
            var secKillId = params['seckillId'];
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            $.get(seckill.URL.now(),{},function (result) {
                if(result&&result["success"]){
                    var nowTime = result["data"];
                    seckill.countDown(secKillId,nowTime,startTime,endTime);
                }else{
                    console.log("result="+result);
                }
            })
        }
    }
}