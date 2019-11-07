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
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo"><b>视频管理系统</b></div>

    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" class="layui-nav-img">
          <span id="username"></span>
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="../admin/logout.do"><i class="layui-icon layui-icon-return"></i>退出 </a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul id="menu" class="layui-nav layui-nav-tree"  lay-filter="test" style="margin-top: 100px;text-align: center">
        <li class="layui-nav-item" ><a href="videoManage.jsp" target="myframe" style="height: 80px;padding-top: 30px"><b>视频管理</b></a></li>
        <li class="layui-nav-item"><a href="speakerManage.jsp" target="myframe" style="height: 80px;padding-top: 30px"><b>主讲人管理</b></a></li>
        <li class="layui-nav-item"><a href="courseManage.jsp" target="myframe" style="height: 80px;padding-top: 30px"><b>课程管理</b></a></li>
      </ul>
    </div>
  </div>

  <div class="layui-body">
    <%--<div style="overflow:hidden;width:500px;height:320px;">
      <div style="margin-left: -250px;margin-top: -270px;">
        <iframe  allowtransparency="true" seamless class="ui-layout-center" src="http://ecki.ceair.com/WebCheckin/uniEnter/query.shtml" id="mainframe" name="mainframe" style="width:100%;height:580px;"></iframe>
      </div></div>--%>
    <iframe name="myframe" width="100%" height="100%" frameborder="0" src="videoManage.jsp"></iframe>
  </div>

  <div class="layui-footer" style="text-align: center">
    <!-- 底部固定区域 -->
     <a href="http://www.yikexingxing.com">©yikexingxing.com</a>
  </div>
</div>
<script>
  //JavaScript代码区域
  layui.use(['element', 'layer'], function () {
    var element = layui.element;
    var layer = layui.layer;

    $.ajax({
      type: "get",
      url: "../admin/query.do",
      dataType: "json",
      success: function (data) {
        if (data.code == 1) {
          $("#username").html(data.username);
        } else {
          layer.msg("获取数据异常");
        }
      }
    })


  });
</script>

    </body>
</html>
