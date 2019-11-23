<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<base href="http://localhost:8020/front/">
<html>
<head>

    <link rel="icon" type="image/x-icon" href="img/logo2.png" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>票务详情-大麦网</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
    <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="css/style.css" type="text/css" />

    <style>
        body {
            margin-top: 20px;
            margin: 0 auto;
        }

        .carousel-inner .item img {
            width: 100%;
            height: 300px;
        }
    </style>

    <script type="text/javascript">
        function addCart(){
            //获得购买的商品的数量
            var buyNum = $("#buyNum").val();
            var location=$("#citylist option:selected").text()
            var round=$("#roundValue").val();
            var price=$("#priceValue").val();

            window.location.href="/AddProductToCart?pid=${product.ID}&buyNum="+buyNum+"&location="+location+"&round="+round+"&price="+price;
            <%--window.location.href="/AddProductToCart?pid=${product.ID}&buyNum="+buyNum;--%>
        }



    </script>

</head>

<body>
<!-- 引入header.jsp -->
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <button class="btn">

    </button>
    <div class="row">
        <div
                style="border: 1px solid #e4e4e4; width: 930px; margin-bottom: 10px; margin: 0 auto; padding: 10px; margin-bottom: 10px;">
            <a href="/frontindex">首页&nbsp;&nbsp;&gt;</a> <a href="${pageContext.request.contextPath}/ProductListServlet?cid=${cid }&currentPage=${currentPage}">${superTypename }&nbsp;&nbsp;&gt;</a>
            <a href="/ProductSubTypeListServlet?ID=${typeID }&cid=${cid }&currentPage=${currentPage}">${subTypename }</a>
        </div>

        <div style="margin: 0 auto; width: 950px;">
            <div class="col-md-6">
                <img style="opacity: 1; width: 400px; height: 350px;" title=""
                     class="medium"
                     src="${product.picture}">


            </div>




            <div class="col-md-6">
                <div>
                    <strong>${product.ticketName}</strong>
                </div>
                <div
                        style="border-bottom: 1px dotted #dddddd; width: 350px; margin: 10px 0 10px 0;">
                    <div>编号:${product.ID}</div>
                </div>

                <div style="height: 50px">
                    演出地点：
                    <select id="citylist" name="cityValue" onchange="changeRound(this)">

                        <c:forEach items="${cityList}" var="p" >

                            <%--<div  onclick="changeRound(this)" id="${p.city}" class="btn btn-default" style="display: inline;width: 8%;height: 80px;border-width:1px;border-style:solid" >--%>
                            <%--${p.city}--%>
                            <%--</div>--%>

                            <option  value="${p.location}">${p.city}</option>



                        </c:forEach>
                    </select>

                    <img src="img/map.png" style="display: inline;height: 30px " onclick="showMap()">

                </div>

                <div style="height: 80px;" >
                    场次:
                    <select  id="roundValue" class="form-control" name="roundValue">
                        <c:forEach items="${initRoundList}" var="p">
                            <option   value="${p.round}">${p.round}</option>
                        </c:forEach>
                    </select>
                </div>



                <script type="text/javascript">
                    function showMap() {
                        var location=$("#citylist").val()
//                        var html=$("#citylist option:selected").text()
//                        alert(location)
                        window.open("https://restapi.amap.com/v3/staticmap?key=da3f89f7c685285905e53d03ba5e4482&size=1024*1024&zoom=11&markers=mid,0xFF0000,A:"+location+"&location="+location);
                    }


                    function changeRound(obj) {
//                        发送请求
                        var  place=$("#citylist option:selected").text();
                        $.post(
                            "/getRound",
                            {"tid":"${product.ID}","city":place},
                            function(data)
                            {
                                var content;
                                for(var i=0;i<data.length;i++){
                                    console.log(i)
                                    console.log(data[i].round+" "+data[i].city)
                                    content+="<option value='"+data[i].round+"'>"+data[i].round+"</option>";
                                }
                                $("#roundValue").html(content);


                                <%--window.location.href="/ProductInfoServlet?pid=${product.ID }&markcommentcurPage=1&currentPage=${currentPage}&cid=${cid}&typeID=${typeID}";--%>
                            },
                            "json"
                        )

                    }
                </script>

                <div style="margin: 0px 0 10px 0;">
                    <%--折扣价:<strong style="color: #ef0101;">￥：${product.nowPrice}元/张</strong> 原价：--%>
                    <%--<strong>￥${product.price}元/张</strong>--%>
                        折扣价:
                        <select  id="priceValue" class="form-control" name="priceValue">
                            <c:forEach items="${priceList}" var="p">
                                <option   value="${p.price}"><strong style="color: #ef0101;">${p.price}</strong></option>
                            </c:forEach>
                        </select>

                </div>

                <%--<div style="margin: 10px 0 10px 0;">--%>
                    <%--促销: <a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)"--%>
                           <%--style="background-color: #f07373;">限时抢购</a>--%>
                <%--</div>--%>

                <div
                        style="padding: 10px; border: 1px solid #e7dbb1; width: 330px; margin: 15px 0 10px 0;; background-color: #ffffff;">
                    <div style="margin: 5px 0 10px 0;">购买</div>

                    <div
                            style="border-bottom: 1px solid #faeac7; margin-top: 20px; padding-left: 10px;">
                        购买数量:
                        <input id="buyNum" name="buyNum" value="1" maxlength="4" size="10" type="text">
                    </div>

                    <div style=" text-align: center;">
                        <a href="javascript:void(0);" onclick="addCart()">
                            <!-- <input style="background: url('./images/product.gif') no-repeat scroll 0 -600px rgba(0, 0, 0, 0); height: 36px; width: 127px;"
                               value="加入购物车" type="button"> -->

                            <img src="./images/productcart.png" style="height: 120px; width: 150px;margin-left:-40px;rgba(0, 0, 0, 0)" onmouseover="javascript:this.style.cursor='pointer'">
                        </a> &nbsp;

                        <div class="btn-group" style="margin-left: -35px;margin-top: 10px">
                            <c:if test="${islove==1 }">
                                <a onclick="loveshoucang('${product.ID}')"  id="love"  class="btn btn-default" style="color:red" >已收藏</a>
                            </c:if>
                            <c:if test="${islove==0 }">
                                <a onclick="loveshoucang('${product.ID}')"  id="love"  class="btn btn-default" >收藏</a>
                            </c:if>


                        </div>

                    </div>
                </div>
                <div>


                    <!-- 判断是否是搜索来的结果 -->
                    <c:if test="${empty isSearchResult }">
                        <!-- 判断是父栏目还是子栏目分页 -->

                            <a href="${pageContext.request.contextPath}/ProductListServlet?cid=${cid }&currentPage=${currentPage}">返回列表页面</a>







                    </c:if>
                </div>
            </div>
        </div>



        <div class="clear"></div>
        <div style="width: 950px; margin: 0 auto;">
            <div style="background-color: #d3d3d3; width: 930px; padding: 10px 10px; margin: 10px 0 10px 0;">
                <strong><h4>项目详情</h4></strong>
            </div>

            <div>
                <%--<img src="${product.picture}" style="height:160px;width:200px">--%>
                <div style="width:950px;">
                    ${product.introduce }

                </div>
            </div>

            <div
                    style="background-color: #d3d3d3; width: 930px; padding: 10px 10px; margin: 10px 0 10px 0;">

                <strong><h4>票务参数</h4></strong>
            </div>

            <div style="margin-top: 10px; width: 900px;">
                <table class="table table-striped">
                    <tbody>
                    <tr class="active">
                        <th colspan="2">基本参数
                    </tr>
                    <tr>
                        <th width="10%">浏览次数</th>
                        <td width="30%">${product.hit }</td>
                    </tr>
                    <tr>
                        <th width="10%">主办方</th>
                        <td>${product.author }</td>
                    </tr>
                    <tr>
                        <th width="10%">该票余量</th>
                        <td>${product.amount }</td>
                    </tr>
                    </tbody>
                </table>
            </div>


            <div>
                <table class="table table-striped" style="text-align: center">
                    <caption style="font-weight: bolder"><h4>图书评论</h4></caption>
                    <thead>
                        <tr style="font-weight: bolder" id="showtitle">
                            <td>评论人</td>
                            <td>评论时间</td>
                            <td>评论内容</td>
                        </tr>
                    </thead>


                    <c:if test="${empty markcommentlist }">
                        <script type="text/javascript">
                            $(function(){
                                /* 没有评论显示。显示相应的友好提示信息 */
                                $("#showupanddown").hide();
                                $("#showtitle").hide();
                                $("#showsplitemark").html("");
                                $("#showsplitemark").html("<br><h3>该商品还没有人发表评论哦～快来给它发表评论吧～<h3>");
                            })

                        </script>
                    </c:if>
                    <tbody id="showsplitemark">
                        <c:forEach items="${markcommentlist}" var="mark">
                            <tr>
                                <td >${mark.uname }</td>
                                <td style="width:95px;margin-left:20px"><input type="date"  readonly value="${mark.marktime }" class="form-control" style="text-align:center;text-indent:9px" ></td>
                                <td>${mark.comment }</td>
                            </tr>

                        </c:forEach>


                    </tbody>
                    <tfoot>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>
                            <div class="pagination" style="text-align: center; margin-top: 10px;"  id="showupanddown">


                                <%--<!-- 上一页 -->--%>
                                <c:if test="${markcommentcurPage==1 }">
                                    <li class="disabled">
                                        <a href="javascript:void(0);" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <c:if test="${markcommentcurPage!=1 }">
                                    <li>
                                        <a    href="/ProductInfoServlet?pid=${pid }&markcommentcurPage=${markcommentcurPage-1}&currentPage=${currentPage}&subtypeFlag=${subtypeFlag}&cid=${cid}&typeID=${typeID}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>


                                <!-- 显示每一页 -->
                                <c:forEach begin="1" end="${markcommenttotalPage }" var="page">
                                    <!-- 判断是否是当前页 -->
                                    <c:if test="${page==markcommentcurPage }">
                                        <li class="active"><a href="javascript:void(0);">${page }</a></li>
                                    </c:if>
                                    <c:if test="${page!=markcommentcurPage }">
                                        <li><a  href="/ProductInfoServlet?pid=${pid }&markcommentcurPage=${page}&currentPage=${currentPage}&subtypeFlag=${subtypeFlag}&cid=${cid}&typeID=${typeID}" >${page }</a></li>
                                    </c:if>
                                </c:forEach>


                                <!-- 下一页 -->
                                <c:if test="${markcommentcurPage==markcommenttotalPage }">
                                    <li class="disabled">
                                        <a href="javascript:void(0);" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <c:if test="${markcommentcurPage!=markcommenttotalPage }">
                                    <li>
                                        <a href="/ProductInfoServlet?pid=${pid }&markcommentcurPage=${markcommentcurPage+1}&currentPage=${currentPage}&subtypeFlag=${subtypeFlag}&cid=${cid}&typeID=${typeID}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </c:if>

                            </div>
                        </td>

                    </tr>
                    <tr>
                        <td colspan="3" >
                            <textarea class="form-control" style="text-algin:left" id="expressmark"></textarea>
                        </td>

                    </tr>

                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>

                        <td >

                            <div class="btn-group" >
                                <c:if test="${empty user }">
                                    <a href="login.jsp">
                                        <button type="button" class="btn btn-default"  >
                                            你还未登录,不能发表评论

                                        </button>
                                    </a>
                                </c:if>
                                <c:if test="${!empty user }">
                                    <button type="button" class="btn btn-default" onclick="addMark()">发表评论</button>
                                </c:if>


                            </div>
                        </td>

                    </tr>
                    </tfoot>
                </table>
            </div>


                <%--<!--ajax异步获取信息   -->--%>

                <script type="text/javascript">
                    function addMark()
                    {

                        var expressmark=$("#expressmark").val();
                        alert(expressmark);
                        $.post(
                            "/AddMark",
                            {"bid":"${product.ID}","bname":"${product.ticketName}","expressmark":expressmark},
                            function(data)
                            {
//                                alert(data);
                                window.location.href="/ProductInfoServlet?pid=${product.ID }&markcommentcurPage=1&currentPage=${currentPage}&cid=${cid}&typeID=${typeID}";
                            },
                            "json"
//
                        )
//
//
                    }
//
                </script>

            <%--</div>--%>
        <%--</div>--%>

    </div>
</div>

<script type="text/javascript">
    function loveshoucang(obj)//收藏的ajax
    {

        $.post(
            "/AddBookToLove",
            {"pid":obj},
            function(data)
            {
                if(data=="收藏")
                {
                    alert("收藏成功!");
                    $("#love").html("已收藏");
                    $("#love").css("color","red");

                }
                else if(data=="取消收藏")
                {
                    alert("取消收藏成功!");
                    $("#love").html("收藏");
                    $("#love").css("color","black");
                }

            },
            "json"

        )


    }


</script>
<!-- 引入footer.jsp -->
<jsp:include page="footer.jsp"></jsp:include>

</body>

</html>