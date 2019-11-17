<%--
  Created by IntelliJ IDEA.
  User: Scarlet
  Date: 2019/11/17
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="box">
    <div class="box_1">
        <h3>
            <span>用户登录</span>
        </h3>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr valign="top">
                <td bgcolor="#FFFFFF" align="center">
                    <form action="index/login.action" method="post" name="myform" onsubmit="return checkLogin()">
                        <table width="60%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
                            <tr>
                                <td width="28%" align="right" bgcolor="#FFFFFF">
                                    用户名：
                                </td>
                                <td width="76%" align="left" bgcolor="#FFFFFF">
                                    <input type="text" name="username" size="25" class="inputBg" id="username" />
                                </td>
                            </tr>
                            <tr>
                                <td width="28%" align="right" bgcolor="#FFFFFF">
                                    密码：
                                </td>
                                <td width="76%" align="left" bgcolor="#FFFFFF">
                                    <input name="password" type="password" size="25" class="inputBg" id="password"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center" bgcolor="#FFFFFF">
                                    <input type="submit" class="bnt_blue_1" style="border: none;" value="确认登录" />
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
