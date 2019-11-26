<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<base href="http://localhost:8020/front/">
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>票务列表</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<%--<!-- 引入自定义css文件 style.
css -->--%>
<link rel="stylesheet" href="css/style.css" type="text/css" />

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
	width: 100%;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}
</style>
</head>


<script type="text/javascript">
	$(function () {
	    var li_id="${li_id}";
		document.getElementById(li_id).className="active";
		var showmore="${showmoreInfo}";
		if(showmore=="1"){
            $("#moreInfo").attr("style","display:visible");
        }
    })
</script>

<body>




	<!-- 引入header.jsp -->
	<jsp:include page="header.jsp"></jsp:include>



	<div class="row" style="width: 1210px; margin: 0 auto;">
		<div  >
			<%--&lt;%&ndash;<ol class="breadcrumb" id="UIbreadcrumb">&ndash;%&gt;--%>
				<%--&lt;%&ndash;<li><a href="#">首页</a></li>&ndash;%&gt;--%>
				<%--&lt;%&ndash;&ndash;%&gt;--%>
			<%--&lt;%&ndash;</ol>&ndash;%&gt;--%>
				<div style="width: 50px;margin-left: 20px">
				   城市
				</div>

				<div style="width: 1000px;margin-top: -30px;margin-left: 100px">

					<ul class="nav nav-pills">
						<%--<li class="active">--%
							<%--<a href="#">Home</a>--%>
						<%--</li>--%>

						<c:forEach items="${townInfoList}" var="p" varStatus="status">
							<c:if test="${status.count<=15}">
								<li id="${p.id}">
									<a href="/ProductSubTypeListServlet?ID=${p.town}&currentPage=1&liId=${p.id}" onclick="outputCity()">${p.town}</a>
								</li>
							</c:if>


						</c:forEach>


						<li><a onclick="more()" style="color: inherit" id="more">更多</a></li>

					</ul>
					<ul class="nav nav-pills" style="display: none" id="moreInfo">
						<c:forEach items="${townInfoList}" var="p" varStatus="status">
							<c:if test="${status.count>15}">
								<li id="${p.id}"><a href="/ProductSubTypeListServlet?ID=${p.town}&currentPage=1&liId=${p.id}&moreInfoFLag=1">${p.town}</a></li>
								<c:if test="${status.count%15==0}">
									<br>
									<br>
								</c:if>
							</c:if>


						</c:forEach>

					</ul>
				</div>


				<script type="text/javascript">
					function outputCity() {

                    }
					function more() {
						var value=document.getElementById("moreInfo").style.display;
	//                    alert(value)
						if(value=='none')
						{
							$("#moreInfo").attr("style","display:visible");
						}
						else
						{
							$("#moreInfo").attr("style","display:none");
						}
					}
			</script>








		</div>


		
		
		
		<script type="text/javascript">
		/* 页面加载完毕就获取子栏目 */
				$(function()
				{
					
					var cid=${cid};
					
					var content="";
					$.post(
						"${pageContext.request.contextPath}/ProductSubTypeDisServlet",
						{"cid":cid},
						function(data)
						{
							for(var i=0;i<data.length;i++){
								/* 传递该子栏目的ID */
								content+="<li><a href='${pageContext.request.contextPath}/ProductSubTypeListServlet?ID="+data[i].ID+"'>"+data[i].tname+"</a></li>";
							}
							 
							$("#UIbreadcrumb").html(content);
							
							
						},
						"json"
						
					)
					
					
				});
				
		
				
		</script>
		
		<c:forEach items="${SpliteBookList }" var="pro">
		
			<div class="col-md-2" style="height:250px;margin-top: 50px">
			<!-- pro.ID 图书ID  cid栏目ID -->
				<a href="/ProductInfoServlet?pid=${pro.ID}&cid=${cid}&currentPage=${currentPage}&typeID=${pro.typeID}">
					<img src="${pro.picture}" width="170" height="170" style="display: inline-block;">
				</a>
				<p>
					<a href="/ProductInfoServlet?subtypeFlag=${subtypeid }&pid=${pro.ID}&cid=${cid}&currentPage=${currentPage}&typeID=${pro.typeID}" style='color: green'>${pro.ticketName }</a>
				</p>
				<c:if test="${!empty subtypeFlag }">
					<p>
						<font color="">场次：${pro.place}</font>
					</p>
				</c:if>
				<p>
					<font color="#FF0000">价格：${pro.price}起</font>
				</p>
			</div>
		
		</c:forEach>

		

		

	</div>

<!--父栏目分页 -->
<c:if test="${empty subtypeFlag }">
	<!--分页 -->
	<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
		<ul class="pagination" style="text-align: center; margin-top: 10px;">
		
			<!-- 上一页 -->
			<c:if test="${currentPage==1 }">
				<li class="disabled">
					<a href="javascript:void(0);" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			</c:if>
			<c:if test="${currentPage!=1 }">
				<li>
					<a href="${pageContext.request.contextPath}/ProductListServlet?cid=${cid}&currentPage=${currentPage-1 }" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			</c:if>
			
		
			<!-- 显示每一页 -->
			<c:forEach begin="1" end="${totalPage }" var="page">
				<!-- 判断是否是当前页 -->
				<c:if test="${page==currentPage }">
					<li class="active"><a href="javascript:void(0);">${page }</a></li>
				</c:if>
				<c:if test="${page!=currentPage }">
					<li><a href="${pageContext.request.contextPath}/ProductListServlet?cid=${cid}&currentPage=${page }">${page }</a></li>
				</c:if>
			</c:forEach>
			
			
			<!-- 下一页 -->
			<c:if test="${currentPage==totalPage }">
				<li class="disabled">
					<a href="javascript:void(0);" aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</c:if>
			<c:if test="${currentPage!=totalPage }">
				<li>
					<a href="${pageContext.request.contextPath}/ProductListServlet?cid=${cid}&currentPage=${currentPage+1 }" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</c:if>
			
		</ul>
	</div>
	<!-- 分页结束 -->
</c:if>



<c:if test="${!empty subtypeFlag }">

<!--分页 -->
	<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
		<ul class="pagination" style="text-align: center; margin-top: 10px;">
		
			<!-- 上一页 -->
			<c:if test="${currentPage==1 }">
				<li class="disabled">
					<a href="javascript:void(0);" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			</c:if>
			<c:if test="${currentPage!=1 }">
				<li>
					<a href="${pageContext.request.contextPath}/ProductSubTypeListServlet?ID=${subtypeFlag }&cid=${cid}&currentPage=${currentPage-1 }" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			</c:if>
			
		
			<!-- 显示每一页 -->
			<c:forEach begin="1" end="${totalPage }" var="page">
				<!-- 判断是否是当前页 -->
				<c:if test="${page==currentPage }">
					<li class="active"><a href="javascript:void(0);">${page }</a></li>
				</c:if>
				<c:if test="${page!=currentPage }">
					<li><a href="${pageContext.request.contextPath}/ProductSubTypeListServlet?ID=${subtypeFlag }&cid=${cid}&currentPage=${page }">${page }</a></li>
				</c:if>
			</c:forEach>
			
			
			<!-- 下一页 -->
			<c:if test="${currentPage==totalPage }">
				<li class="disabled">
					<a href="javascript:void(0);" aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</c:if>
			<c:if test="${currentPage!=totalPage }">
				<li>
					<a href="${pageContext.request.contextPath}/ProductSubTypeListServlet?ID=${subtypeFlag }&cid=${cid}&currentPage=${currentPage+1 }" aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</c:if>
			
		</ul>
	</div>
	<!-- 分页结束 -->




</c:if>





	<!--商品浏览记录-->
	<div
		style="width: 1210px; margin: 0 auto; padding: 0 9px; border: 1px solid #ddd; border-top: 2px solid #999; height: 286px;">

		<h4 style="width: 50%; float: left; font: 14px/30px 微软雅黑">浏览记录</h4>
		<div style="width: 50%; float: right; text-align: right;">
			<%--<a href="">more</a>--%>
		</div>
		<div style="clear: both;"></div>

		<div style="overflow: hidden;">

			<ul style="list-style: none;">
			
				<c:forEach items="${historyProductList }" var="historyPro">
					<li style="width: 150px; height: 256px; float: left; margin: 0 8px 0 0; padding: 0 18px 15px; text-align: center;">

						<a href="/ProductInfoServlet?pid=${historyPro.ID}&cid=${cid}&currentPage=${currentPage}&typeID=${historyPro.typeID}">
							<img src="${historyPro.picture}" width="130px" height="130px" />"
						</a>
						<p>
							<a href="/ProductInfoServlet?subtypeFlag=${subtypeid }&pid=${historyPro.ID}&cid=${cid}&currentPage=${currentPage}&typeID=${historyPro.typeID}" style='color: green'>${historyPro.ticketName }</a>
						</p>

						<p>
							<font color="#FF0000">价格：${historyPro.price}起</font>
						</p>
					</li>
				</c:forEach>
			
				

			</ul>

		</div>
	</div>


	<!-- 引入footer.jsp -->
	<jsp:include page="footer.jsp"></jsp:include>

</body>

</html>