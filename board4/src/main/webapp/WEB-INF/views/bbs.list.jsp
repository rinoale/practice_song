<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <title>스프링프레임워크 게시판</title>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <style>
    .od{
    float: left;
    }
    #bh{
    align: left;
    width: 100px;
    }
    #title{
    width: 400px;
    }
    </style>


  </head>
  <body>
  <h1>${message}</h1>
	<div class="od" id="bh">글번호</div><div class="od" id="title">제목</div><div>글쓴이</div>
	<c:forEach var="item" items="${list}" varStatus="status">
	
	
    	<div id="${item.idx}" class="nb" style="cursor:pointer;">
      		<div class="od" id="bh">${item.idx}</div>      
      		<div class="od" id="title">${item.subject}</div>      
      		<div>${item.user_name}</div>
      	</div>

      <div id="d${item.idx}" class="nb" style="display:none">
      </div>
      <div>${item.reg_datetime}</div>
      <input id="c${item.idx}" class="nb" type="checkbox" style="display:none">

    
    
    </c:forEach>
  <div><a href="./write">쓰기</a></div>
  <script type="text/javascript">
	$(document).ready(function(){

		$(".nb").click(function(){

			var id=$(this).attr("id");
			
			$("#d"+id).load('./'+id);
			if ($("#c"+id).is(':checked')==false){
				$("#"+id).css({"background-color":"ANTIQUEWHITE"});
				$("#d"+id).show();
				$("#c"+id).get()[0].setAttribute('checked',true);
			}else if($("#c"+id).is(':checked')==true){
				$("#"+id).css({"background-color":"white"});
				$("#d"+id).hide();
				$("#c"+id).get()[0].removeAttribute('checked');
			}
		});
	});
	
	</script>
	
  </body>
</html>
