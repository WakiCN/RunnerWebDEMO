<%--
  Created by IntelliJ IDEA.
  User: Waki
  Date: 2018/5/9
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input id="vail">
<button id="sendEmail">发送邮件</button>
<button id="register">注册账号</button>
</body>
</html>
<script src="jquery.js"></script>
<script>
    function x() {
        var url="/Register?MName=Register&"+Math.random();
        $.post(url,{
            Name:'Test',
            PassWord:'test1',
            Sex:'女',
            Vaildcode:$("#vail").val()
        },function (x) {
            alert(x);
        });
    }
    function Email() {
        var url="/ValidCode?MName=printCodeEmail&"+Math.random();
        $.post(url,{
            UserName:'Test',
            UserEmail:'1062540709@qq.com'
        },function (x) {
            alert(x);
        });
    }
    $(function () {
        $("#sendEmail").click(function () {
            Email();
        });
        $("#register").click(function () {
            x();
        });
    });
</script>
