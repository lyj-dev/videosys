<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <input type="hidden" name="id" id="id"  value="${course.id}"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">名称：</label>
        <div class="layui-input-block">
            <input type="text" id="title" name="title"  class="layui-input"
                   value="${course.title}" style="width: 300px">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">科目：</label>
        <div class="layui-input-block" style="width: 300px">
            <select  id="subject" name="subjectId" lay-verify="required">
                <c:forEach items="${subjectList}" var="subject">
                    <option value="${subject.id}">${subject.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">介绍：</label>
        <div class="layui-input-block">
            <textarea id="desc" name="desc" class="layui-textarea" style="width: 300px;height: 200px">${course.desc}</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block" style="margin-left: 160px">
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

        });
        //Demo
        layui.use('form', function () {
            var form = layui.form;
            //监听提交
            form.on('submit(formDemo)', function (data) {
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/course/addOrUpdate.do",
                    data:data.field,
                    dataType:"json",
                    success:function(data){  //{code:1,info:....}
                        if(data === 1){
                            var t = parent.layui.table;
                            t.reload('courseTable');// 重新加载页面,demo 表示父窗体table标签的id值
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
        $("#subject").find("option[value='${course.subjectId}']").attr("selected", "selected");
    });
</script>
</body>
</html>
