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
<%--<div style="height: 70px;text-align: center; background-color: #F2F2F2;">--%>
<%--    <h1 style="text-align: center;padding-top: 20px">主讲人管理</h1>--%>
<%--</div>--%>
<div style="height: 70px;text-align: center; background-color: #C8EBFA;">
    <h1 style="text-align: center;padding-top: 20px">主讲人管理</h1>
</div>

<table class="layui-hide" id="test" lay-filter="test" style="margin: 0px"></table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <div class="layui-table-tool-temp">
            <div class="layui-inline" lay-event="add">
                <i class="layui-icon layui-icon-add-1"></i>
            </div>
            <div class="layui-inline" lay-event="delete">
                <i class="layui-icon layui-icon-delete"></i>
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
            url: '../speaker/delete.do',
            type: 'post',
            dataType: 'json',
            data: "id=" + delList,
            success: function (data) {
                if (data == 0) {
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
            id: "speakerTable"
            ,elem: '#test'
            ,url:'../speaker/list.do'
            ,toolbar: '#toolbarDemo'
            ,title: '主讲人数据表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'xuhao', title:'序号',  fixed: 'left', type:"numbers"}
                ,{field:'name', title:'名称',  edit: 'text'}
                ,{field:'job', title:'职位',  edit: 'text'}
                ,{field:'desc', title:'简介',width:800}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
            ]]
            ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                , groups: 3 //只显示 1 个连续页码
                , first: false //不显示首页
                , last: false //不显示尾页
                , limit: 5
                , limits: [5, 10, 20]

            }
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'add':
                    layer.open({
                        type: 2 //此处以iframe举例
                        , title: '添加主讲人信息'
                        , area: ['500px', '500px']
                        , content: 'updateSpeaker.jsp'
                    });
                    break;
                case 'delete':
                    if (checkStatus.data.length == 0) {
                        layer.alert("请选择需要删除的数据");
                        return;
                    }
                    layer.confirm('是否删除选中的行？', {
                        btn: ['是', '否'] //可以无限个按钮
                        ,btn1: function(index, layero){
                        }
                    }, function(index, layero){
                        //选择是则删除
                        var delList = [];
                        checkStatus.data.forEach(function (n) {
                            delList.push(n.id);
                        });
                        submitdata(delList);
                        var t = layui.table;
                        t.reload('speakerTable');// 重新加载页面
                        // console.log(checkStatus.data); //获取选中行的数据
                        // console.log(checkStatus.data.length); //获取选中行数量，可作为是否有选中行的条件
                    });



            }
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    submitdata(data.id);
                    var t = layui.table;
                    t.reload('speakerTable');// 重新加载页面
                });
            } else if(obj.event === 'edit'){
                layer.open({
                    type: 2 //此处以iframe举例
                    , title: '修改主讲人信息'
                    , area: ['500px', '500px']
                    , content: '../speaker/query.do?id=' + data.id

                });

            }
        });
    });
</script>

</body>
</html>
