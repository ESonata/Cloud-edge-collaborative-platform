<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<base href="http://localhost:8080/front/">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>参与投票-大麦网售票网站</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
	<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<!-- 引入自定义css文件 style.css -->
	<link rel="stylesheet" href="css/style.css" type="text/css" />
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<br>


<table class="table" style="margin-left: 300px;width: 800px;margin-top:40px">
    <caption>>投票编号${spliteVoteplist.vid }</caption>
    <thead>
    <tr>
        <th colspan="2"><h3>投票名称&nbsp;&nbsp;<span class="label label-default">${spliteVoteplist.vname }</span></h3></th>

    </tr>
    	 <tr>
                            <td wdith="20%">开始时间:</td>
                            <td width="80%"><input type="datetime-local"  readonly value="${voteitemstime }" class="form-control" name="stime" maxlength="10" autocomplete="off" /></td>
        </tr>
       <tr>
                            <td wdith="20%">结束时间:</td>
                            <td width="80%"><input type="datetime-local"  readonly value="${voteitemetime }" class="form-control" name="etime" maxlength="13" autocomplete="off" /></td>
       </tr>
    		
    </thead>
    <tbody>
   
  <c:forEach items="${voteitemdetail}" var="item" varStatus="status">
    <tr>
        <td>选项${status.count }:${item }</td>
       
        <td>
                <div class="input-group">
                    <span class="input-group-addon">
                        <input type="radio" name="check" value="item${status.count }" > </span>

                </div><!-- /input-group -->
        </td>
    </tr>
   
  </c:forEach>
    <tr>

        <td colspan="2">

                <button onclick="fn1()" class="btn btn-default" style="margin-left: 90%;margin-top: 10px">为它投票</button>

        </td>
    </tr>

    </tbody>


</table>



<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
		<ul class="pagination" style="text-align: center; margin-top: 10px;">
		
			<!-- 上一页 -->
			<c:if test="${curPage==1 }">
				<li class="disabled">
					<a href="javascript:void(0);" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			</c:if>
			<c:if test="${curPage!=1 }">
				<li>
					<a href="${pageContext.request.contextPath}/FVoteListIndexServlet?curPage=${curPage-1 }" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			</c:if>
			
		
			<!-- 显示每一页 -->
			<c:forEach begin="1" end="${totalPage }" var="page">
				<!-- 判断是否是当前页 -->
				<c:if test="${page==curPage}">
					<li class="active"><a href="javascript:void(0);">${page }</a></li>
				</c:if>
				<c:if test="${page!=curPage }">
					<li><a href="${pageContext.request.contextPath}/FVoteListIndexServlet?curPage=${page }">${page }</a></li>
				</c:if>
			</c:forEach>
			
			
			<!-- 下一页 -->
			<c:if test="${curPage==totalPage }">
				<li class="disabled">
					<a href="javascript:void(0);" aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</c:if>
			<c:if test="${curPage!=totalPage }">
				<li>
					<a href="${pageContext.request.contextPath}/FVoteListIndexServlet?curPage=${currentPage+1 }" aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</c:if>
			
		</ul>
	</div>
	<!-- 分页结束 -->








<script type="text/javascript">

		function fn1()
		{
			var itemname=$("input[name='check']:checked").val();
			if(itemname==null)
				{
					alert("请选择后进行投票!");
					return ;
				}
			$.post(
					"/AddClickVote",
					{"itemname":itemname,"vid":"${spliteVoteplist.vid }"},
					function(data)
					{
						
						if(data=="您已经投过票了!请勿重复投票!")
							{
								alert(data);
								location.reload();
							}
						else
							{
								alert(data);
								location.reload();
							}
					
					},
					"json"
					
					
				
				);
		}

</script>

<script>
    $(function() {
        $(".btn").click(function(){
            $(this).button('loading').delay(1000).queue(function() {
                $(this).button('reset');
                $(this).dequeue();
            });
        });
    });
</script>
</body>
</html>