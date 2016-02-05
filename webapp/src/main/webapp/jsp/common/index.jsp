<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>告警系统</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.4 -->
    <link href="/resources/plugins/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <!-- FontAwesome 4.3.0 -->
    <link href="/resources/plugins/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css"/>
    <!-- uniform -->
    <%--<link href="/resources/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>--%>
    <!-- Theme style -->
    <link href="/resources/css/AdminLTE.css" rel="stylesheet" type="text/css"/>
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link href="/resources/css/skins/_all-skins.css" rel="stylesheet" type="text/css"/>

</head>
<body class="skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="header.jsp" flush="true"/>
    <jsp:include page="menu.jsp" flush="true"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper" style="text-align: center; background: #F2F5FA">
        <table id="indexTable" class="table table-striped table-bordered table-hover">
        </table>
    </div>

    <jsp:include page="footer.jsp" flush="true"/>

</div>
<!-- ./wrapper -->

<!-- jQuery 2.1.4 -->
<%--<script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>--%>
<script src="/resources/scripts/common/jquery.min.js" type="text/javascript"></script>
<!-- uniform -->
<%--<script src="/resources/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>--%>
<!-- Bootstrap 3.3.2 JS -->
<script src="/resources/plugins/bootstrap/scripts/bootstrap.js" type="text/javascript"></script>
<!-- FastClick -->
<script src="/resources/plugins/fastclick/fastclick.js" type="text/javascript"></script>
<!-- AdminLTE App -->
<script src="/resources/scripts/app.js" type="text/javascript"></script>
<script src="/resources/scripts/common/common.js" type="text/javascript"></script>
<script src="/resources/scripts/index.js" type="text/javascript"></script>
<script>
    jQuery(document).ready(function () {
        IndexTable.init();
    });
</script>
</body>
</html>
