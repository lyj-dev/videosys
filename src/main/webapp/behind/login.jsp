<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="../layui/css/layui.css">
        <style type="text/css">
            #bg {
                position: fixed;
                left: 0;
                top: 0;
                height: 50%;
                width: 100%;
                z-index: -1;
                background-color: #009688;
            }
            .container{
                width: 420px;
                height: 320px;
                min-height: 320px;
                max-height: 320px;
                position: absolute;
                top: 0;
                left: 0;
                bottom: 0;
                right: 0;
                margin: auto;
                padding: 20px;
                z-index: 130;
                border-radius: 8px;
                background-color: #fff;
                box-shadow: 0 3px 18px rgba(100, 0, 0, .5);
                font-size: 16px;
            }
            .layui-input{
                border-radius: 5px;
                width: 300px;
                height: 40px;
                font-size: 15px;
            }
            .layui-input{
                border-color: gray;
            }
            .layui-form-item{
                margin-left: -20px;
            }
            #logoid{
                margin-top: 15px;
                padding-left:150px;
                padding-bottom: 15px;
                color: brown;
            }
            .layui-btn{
                margin-left: -50px;
                border-radius: 5px;
                width: 350px;
                height: 40px;
                font-size: 15px;
            }
            .verity{
                width: 120px;
            }
            .font-set{
                font-size: 13px;
                text-decoration: none;
                margin-left: 120px;
            }
            a:hover{
                text-decoration: underline;
            }

        </style>
    </head>
    <body>
        <div id="bg"></div>
        <div style="text-align: center;margin-top: 100px">
            <h1 style="color: whitesmoke;">视频后台管理系统</h1>
        </div>
        <form class="layui-form" action="" method="post" onsubmit="return false;">
            <div class="container">

                <div class="layui-form-mid layui-word-aux">
                    <!--<img id="logoid" src="06.png" height="35">-->
                    <h3 id="logoid">管理员登录</h3>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">账号</label>
                    <div class="layui-input-block">
                        <input type="text" name="username" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密 &nbsp;&nbsp;码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                    </div>
                     <div class="layui-form-mid layui-word-aux"></div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">验证码</label>
                    <div class="layui-input-inline" id="yzm" style="width: 300px">
                            <input type="text" name="yzm" required  lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input verity">
                            <span id="v_container" style="width: 45%;height: 40px;float:right;margin-top: -38px"></span>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">登录</button>
                    </div>
                </div>
            </div>
        </form>
        <script type="text/javascript" src="../layui/layui.js"></script>
        <script type="text/javascript" src="../js/jquery-1.12.2.min.js"></script>
        <script>
            layui.use(['form', 'layedit', 'laydate'], function(){
                var form = layui.form
                    ,layer = layui.layer
                    ,layedit = layui.layedit
                    ,laydate = layui.laydate;


                 //自定义验证规则
                 form.verify({
                   account: [
                       /^\d{6}$/
                       ,'账号格式有误'
                   ]
                   ,password: [
                     /^[a-zA-Z0-9]{3,12}$/
                     ,'密码必须3到12位，且不能出现空格'
                   ]

                 });


                //监听提交
                form.on('submit(formDemo)', function(data){
                    var code =$("input[name='yzm']").val();
                    var res = verifyCode.validate(code);
                    if (!res) {
                        alert("验证码错误");
                        return false;
                    }


                    $.ajax({
                        type:"post",
                        url:"../admin/login.do",
                        data:data.field,
                        dataType:"json",
                        success:function(data){
                            if(data === 1){
                                window.location.href = "index.jsp";
                            } else {
                                layer.alert("账号或密码有误", {
                                    title: '登录信息'
                                })
                            }

                        }
                    });

                    return false;
                });

            });
        </script>
        <script src="../js/gVerify.js"></script>
        <script src="../js/index.js"></script>
    </body>
</html>
