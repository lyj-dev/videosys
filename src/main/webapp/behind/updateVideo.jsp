<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../js/jquery-1.12.2.min.js"></script>
    <link rel="stylesheet" href="../layui/css/layui.css" />
    <script type="text/javascript" src="../layui/layui.js" ></script>

</head>
<body>
<form class="layui-form" action="">
    <div class="layui-form-item">
        <div class="layui-input-block">
            <input type="hidden" name="id" id="id"  value="${video.id}"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">名称：</label>
        <div class="layui-input-block">
            <input type="text" name="title" id="title"  class="layui-input"
                   value="${video.title}" style="width: 500px">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">教师名字：</label>
        <div class="layui-input-block" style="width: 500px">
            <select name="speakerId" id="speaker" lay-verify="required">
                <c:forEach items="${speakerList}" var="speaker">
                    <option value="${speaker.id}">${speaker.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">所属课程：</label>
        <div class="layui-input-block" style="width: 500px">
            <select name="courseId" id="course" lay-verify="required" >
                <c:forEach items="${courseList}" var="course">
                    <option value="${course.id}">${course.title}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">时长：</label>
        <div class="layui-input-block">
            <input type="text" id="time" name="time" value="${video.time}"
                   class="layui-input" style="width: 500px">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">封面图片地址：</label>
        <div class="layui-input-block">
            <input type="text" id="imgUrl" name="imgUrl" value="${video.imgUrl}"
                   class="layui-input" style="width: 600px">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">视频播放地址：</label>
        <div class="layui-input-block">
            <input type="text" id="videoUrl" name="videoUrl" value="${video.videoUrl}"
                   class="layui-input" style="width: 600px">
        </div>
    </div>

    <%--
    <div class="layui-form-item">
        <label class="layui-form-label">开关</label>
        <div class="layui-input-block">
            <input type="checkbox" name="switch" lay-skin="switch">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单选框</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="男" title="男">
            <input type="radio" name="sex" value="女" title="女" checked>
        </div>
    </div>--%>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea id="comment" name="detail" class="layui-textarea" style="width: 500px">${video.detail}</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block" style="margin-left: 220px">
            <button class="layui-btn" lay-submit lay-filter="formDemo">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    layui.use(['layer', 'form'], function () {
        var layer = layui.layer
            , form = layui.form;

        //自定义验证规则
        form.verify({
            time: [
                /\d+/
                , '时间格式不正确'
            ],
            imgUrl: [
                /(https?|ftp|file):[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]/
                , 'URL格式不正确'
            ],
            videoUrl: [
                /(https?|ftp|file):[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]/
                , 'URL格式不正确'
            ]

        });
        //Demo
        layui.use('form', function () {
            var form = layui.form;
            //监听提交
            form.on('submit(formDemo)', function (data) {
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/video/addOrUpdate.do",
                    data:data.field,
                    dataType:"json",
                    success:function(data){  //{code:1,info:....}
                        if(data === 1){
                            var t = parent.layui.table;
                            t.reload('videoTable');// 重新加载页面,demo 表示父窗体table标签的id值
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index); //再执行关闭
                        }else{
                            alert(data.info);
                        }
                    }
                });

                return false;
            });
        })
    })
</script>
<script type="text/javascript">
    $(function() {
        $("#speaker").find("option[value='${video.speakerId}']").attr("selected", "selected");
    });
    $(function() {
        $("#course").find("option[value='${video.courseId}']").attr("selected", "selected");
    });
</script>
</body>
</html>
