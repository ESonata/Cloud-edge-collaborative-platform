<%--
  Created by IntelliJ IDEA.
  User: Scarlet
  Date: 2019/11/17
  Time: 22:12
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
            <span>用户注册</span>
        </h3>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr valign="top">
                <td bgcolor="#FFFFFF" align="center">
                    <form action="index/register.action" method="post" name="myform">
                        <table width="60%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
                            <tr>
                                <td width="28%" align="right" bgcolor="#FFFFFF">用户名：</td>
                                <td width="76%" align="left" bgcolor="#FFFFFF"><input type="text" name="username" size="25"
                                                                                      class="inputBg" id="username" /></td>
                            </tr>
                            <tr>
                                <td width="28%" align="right" bgcolor="#FFFFFF">密码：</td>
                                <td width="76%" align="left" bgcolor="#FFFFFF"><input name="password" type="password" size="25"
                                                                                      class="inputBg" id="password" /></td>
                            </tr>
                            <tr>
                                <td width="28%" align="right" bgcolor="#FFFFFF">姓名：</td>
                                <td width="76%" align="left" bgcolor="#FFFFFF"><input name="realname" type="text" size="25"
                                                                                      class="inputBg" id="realname" /></td>
                            </tr>
                            <tr>
                                <td width="28%" align="right" bgcolor="#FFFFFF">性别：</td>
                                <td width="76%" align="left" bgcolor="#FFFFFF"><select name="sex" id="sex" class="inputBg">
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select></td>
                            </tr>
                            <tr>
                                <td width="28%" align="right" bgcolor="#FFFFFF">出生日期：</td>
                                <td width="76%" align="left" bgcolor="#FFFFFF"><input name="birthday" type="text" size="25"
                                                                                      class="inputBg" id="birthday" readonly="readonly" onclick="WdatePicker()" /></td>
                            </tr>
                            <tr>
                                <td width="28%" align="right" bgcolor="#FFFFFF">联系方式：</td>
                                <td width="76%" align="left" bgcolor="#FFFFFF"><input name="contact" type="text" size="25"
                                                                                      class="inputBg" id="contact" /></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center" bgcolor="#FFFFFF"><input type="submit" class="bnt_blue_1"
                                                                                        style="border: none;" value="确认注册" /></td>
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>
        </table>
    </div>
    <div class="blank5"></div>
</div>
</body>
</html>
