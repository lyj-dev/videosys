<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../layui/css/layui.css" />
    <script type="text/javascript" src="../layui/layui.js" ></script>
    <script type="text/javascript" src="../js/jquery-1.12.2.min.js" ></script>

</head>
<body class="layui-layout-body">
<div style="height: 70px;text-align: center; background-color: #C8EBFA;">
    <h1 style="text-align: center;padding-top: 20px">视频管理</h1>
</div>

<div class="layui-form searchDiv">
    <div class="layui-form-item" style="margin-left: 350px">
        <div class="layui-input-inline">
            <input type="text" class="layui-input" id="title" name="title" placeholder="请输入名称">
        </div>
        <div class="layui-input-inline">
            <select name="speakerId" id="speaker">
                <option value="" selected="">--请选择老师--</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select name="courseId" id="course">
                <option value="" selected="">--请选择课程--</option>
            </select>
        </div>

        <button type="button" class="layui-btn layui-btn-normal layui-btn-sm" data-type="reload"
            style="height:38px">
            <i class="layui-icon layui-icon-search"></i>
        </button>
    </div>
</div>

<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <div class="layui-table-tool-temp">
            <div class="layui-inline" lay-event="add">
                <i class="layui-icon layui-icon-add-1"></i>
            </div>
            <div class="layui-inline" lay-event="delete">
                <i class="layui-icon layui-icon-delete"><span id="delNum"></span></i>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">
        <i class="layui-icon"></i>
    </a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">
        <i class="layui-icon"></i>
    </a>
</script>

<script type="text/javascript">
    function submitdata(delList) {
        $.ajax({
            url: '../video/delete.do',
            type: 'post',
            dataType: 'json',
            data: "id=" + delList,
            success: function (data) {
                if (data === 0) {
                    layer.msg('删除成功');
                    table.reload();
                } else {
                    layer.msg('删除失败');
                }
            },
            'error': function () {
                layer.msg('系统错误');
            }
        })
    }
</script>

<script>
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            id: "videoTable"
            ,elem: '#test'
            ,url:'../video/list.do'
            ,toolbar: '#toolbarDemo'
            ,title: '主讲人数据表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'xuhao', title:'序号',  fixed: 'left', width: 60, type: 'numbers'}
                ,{field:'title', title:'名称',  edit: 'text', width: 200}
                ,{field:'course', title:'所属课程',  edit: 'text', width: 200}
                ,{field:'detail', title:'介绍',  edit: 'text', width: 400}
                ,{field:'speaker', title:'讲师'}
                ,{field:'time', title:'时长', width: 100, sort: true}
                ,{field:'playNum', title:'播放次数', width: 100, sort: true}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width: 100}
            ]]
            ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                , groups: 3 //只显示 1 个连续页码
                , first: "首页" //不显示首页
                , last: "尾页" //不显示尾页
                , limit: 5
                , limits: [5, 10, 20]

            }
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            displaySelectedNum(checkStatus.data.length);
            switch(obj.event){
                case 'add':
                    layer.open({
                        type: 2 //此处以iframe举例
                        , title: '添加视频'
                        , area: ['720px', '620px']
                        , content: '../video/query.do'
                    });
                    break;
                case 'delete':
                    if (checkStatus.data.length === 0) {
                        layer.alert("请选择需要删除的数据");
                        return;
                    }
                    var delList = [];
                    checkStatus.data.forEach(function (n) {
                        delList.push(n.id);
                    });
                    layer.confirm('确认要删除所选中的行吗？',function(index){
                        submitdata(delList);
                        var t = layui.table;
                        t.reload('videoTable');// 重新加载页面
                    });

                    // console.log(checkStatus.data); //获取选中行的数据
                    // console.log(checkStatus.data.length); //获取选中行数量，可作为是否有选中行的条件

            }
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    submitdata(data.id);
                    var t = layui.table;
                    t.reload('videoTable');// 重新加载页面
                });
            } else if(obj.event === 'edit'){
                layer.open({
                    type: 2 //此处以iframe举例
                    , title: '修改视频信息'
                    , area: ['720px', '620px']
                    , content: '../video/query.do?id=' + data.id
                });
            }
        });

        var active = {
            reload: function(){
                //执行重载
                table.reload('videoTable', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: { // 额外需要提交的参数\
                        title: $("#title").val(),
                        speakerId:$("#speaker").val(),
                        courseId:$("#course").val()
                    }
                });
            }
        };

        $('.searchDiv .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>

<%--ajax请求加载页面下拉框内容--%>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url: "../speaker/list.do?page=1&limit=65536",
            dataType: "json",
            type: "get",
            success: function(returnData) {
                $(returnData.data).each(function (index, item) {
                    var html = "<option value=" + item.id + ">" + item.name + "</option>";
                    $("#speaker").append(html);
                })
            }
        });
        $.ajax({
            url: "../course/list.do?page=1&limit=65536",
            dataType: "json",
            type: "get",
            success: function(returnData) {
                $(returnData.data).each(function (index, item) {
                    var html = "<option value=" + item.id + ">" + item.title + "</option>";
                    $("#course").append(html);
                })
            }
        });

    });
    function displaySelectedNum(n) {
        $("#delNum").html(n);
    }
</script>
</body>
</html>
