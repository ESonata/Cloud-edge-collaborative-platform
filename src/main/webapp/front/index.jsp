<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<base href="http://localhost:8080/front/">
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>大麦网</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</head>
	<body>
		<div class="container-fluid">

			<!-- 引入header.jsp -->
			<jsp:include page="header.jsp"></jsp:include>

			<!-- 轮播图 -->
			<div class="container-fluid">
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					<!-- 轮播图的中的小点 -->
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
					</ol>
					<!-- 轮播图的轮播图片 -->
					<div class="carousel-inner" role="listbox">
						<div class="item active">
							<img src="img/banner1.png" style="width:1600px">
							<div class="carousel-caption">
								<!-- 轮播图上的文字 -->
								快点击这里啊
							</div>
						</div>
						<div class="item">
							<img src="img/banner1.png" style="width:1600px">
							<div class="carousel-caption">
								<!-- 轮播图上的文字 -->
							</div>
						</div>
						<div class="item">
							<img src="img/banner1.png" style="width:1600px">
							<div class="carousel-caption">
								<!-- 轮播图上的文字 -->
							</div>
						</div>
					</div>

					<!-- 上一张 下一张按钮 -->
					<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
						<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a>
					<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
						<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
			</div>
			
			<!-- 热门商品 -->
			<div class="container-fluid">
				<div class="col-md-12">
					<h2>热门图书&nbsp;&nbsp;<img src="img/title2.jpg"/></h2>
				</div>
				<div class="col-md-2" style="border:1px solid #E7E7E7;border-right:0;padding:0;">
					<img src="products/hao/big02.png" width="205" height="404" style="display: inline-block;"/>
				</div>
				<div class="col-md-10">
					<div class="col-md-6" style="text-align:center;height:200px;padding:0px;">
						<a href="product_info.htm">
							<img src="products/hao/big01.png" width="516px" height="200px" style="display: inline-block;">
						</a>
					</div>
					
					<c:forEach items="${hotbooklist }" var="hotPro">

						<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
							<a href="product_info.htm">
								<img src="${hotPro.picture }" width="130" height="130" style="display: inline-block;">
							</a>
							<p><a href="product_info.html" style='color:#666'>${hotPro.bookName }</a></p>
							<p><font color="#E4393C" style="font-size:16px">&yen;${hotPro.price }</font></p>
						</div>

					</c:forEach>
				
					
	
					
				</div>
			</div>
			
			<!-- 广告条 -->
            <div class="container-fluid">
				<img src="products/hao/ad.jpg" width="100%"/>
			</div>
			
			<!-- 最新商品 -->
			<div class="container-fluid">
				<div class="col-md-12">
					<h2>最新图书&nbsp;&nbsp;<img src="img/title2.jpg"/></h2>
				</div>
				<div class="col-md-2" style="border:1px solid #E7E7E7;border-right:0;padding:0;">
					<img src="products/hao/big02.png" width="205" height="404" style="display: inline-block;"/>
				</div>
				<div class="col-md-10">
					<div class="col-md-6" style="text-align:center;height:200px;padding:0px;">
						<a href="product_info.htm">
							<img src="products/hao/big01.png" width="516px" height="200px" style="display: inline-block;">
						</a>
					</div>
				
					<c:forEach items="${newbooklist }" var="newPro">

						<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
							<a href="product_info.htm">
								<img src="${newPro.picture }" width="130" height="130" style="display: inline-block;">
							</a>
							<p><a href="product_info.html" style='color:#666'>${newPro.bookName }</a></p>
							<p><font color="#E4393C" style="font-size:16px">&yen;${newPro.price }</font></p>
						</div>

					</c:forEach>
					
				</div>
			</div>			
			
			
			
			<!-- 打折商品 -->
			<div class="container-fluid">
				<div class="col-md-12">
					<h2>最热打折图书&nbsp;&nbsp;<img src="img/title2.jpg"/></h2>
				</div>
				<div class="col-md-2" style="border:1px solid #E7E7E7;border-right:0;padding:0;">
					<img src="products/hao/big02.png" width="205" height="404" style="display: inline-block;"/>
				</div>
				<div class="col-md-10">
					<div class="col-md-6" style="text-align:center;height:200px;padding:0px;">
						<a href="product_info.htm">
							<img src="products/hao/big01.png" width="516px" height="200px" style="display: inline-block;">
						</a>
					</div>
				
					<c:forEach items="${salebooklist }" var="Pro">

						<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
							<a href="product_info.htm">
								<img src="${Pro.picture }" width="130" height="130" style="display: inline-block;">
							</a>
							<p><a href="product_info.html" style='color:#666'>${Pro.bookName }</a></p>
							<p><font color="#E4393C" style="font-size:16px;text-decoration:line-through;" >&yen;${Pro.nowPrice }</font></p>
							<p><font color="#E4393C" style="font-size:16px">&yen;${Pro.price }</font></p>
						</div>

					</c:forEach>
					
				</div>
			</div>			
			<!-- 引入footer.jsp -->
			<jsp:include page="footer.jsp"></jsp:include>
			
		</div>
	</body>

</html>