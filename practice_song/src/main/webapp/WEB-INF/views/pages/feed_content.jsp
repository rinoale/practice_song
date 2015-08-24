<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html class="no-js" lang="en">
  <head>   
  <script src="${pageContext.request.contextPath}/resources/js/pageJS/feed_content.js"></script>
  <script>
	function loadWritesAtFirst(){
		 <c:forEach var="write" items="${writes}">
		 appendWrites("${write.WRITES_CONTENT}","${write.seq}");
		 </c:forEach>
	}
  </script>
	<script>
	$(document).ready(function(){
		loadWritesAtFirst();
	      
		$(window).scroll(function() {
			if($(window).scrollTop() + $(window).height() == $(document).height()) {
				var id_num=$(".write:last").attr("id");
				
				var index_num=id_num.substring(id_num.indexOf("_")+1);
				
				console.log(index_num);
				
				sendWritesindexGetReplies(index_num);
			}
		});
	});
	</script>
  </head>
  <body>
      <div class="row body_in_mesh" id="sub_header">
        <div class="large-12 columns">
          <div class="panel">
            <h1 onClick="pageClear()">Feed Template</h1>
          </div>
        </div>
      </div>
       
      <div class="row">
      
         
        <div class="large-3 columns ">
          <div class="panel">
            <a href="#"><img src="http://placehold.it/300x240&text=[img]"/></a>
            <h5><a href="#">Your Name</a></h5>
              <div class="section-container vertical-nav" data-section data-options="deep_linking: false; one_up: true">
              <section class="section">
                <h5 class="title"><a href="#">Section 1</a></h5>
              </section>
              <section class="section">
                <h5 class="title"><a href="#">Section 2</a></h5>
              </section>
              <section class="section">
                <h5 class="title"><a href="#">Section 3</a></h5>
              </section>
              <section class="section">
                <h5 class="title"><a href="#">Section 4</a></h5>
              </section>
              <section class="section">
                <h5 class="title"><a href="#">Section 5</a></h5>
              </section>
              <section class="section">
                <h5 class="title"><a href="#">Section 6</a></h5>
              </section>
            </div>
     
          </div>
        </div>
        
        
         
        <div class="large-6 columns">
        	<div class="row" align="right">
	          <textarea id="Writes" placeholder="" style="height: 150px;resize: none;"></textarea>
	          <div role="button" tabindex="0" class="button tiny" onClick="Writes()">Write</div>
	        </div>
	        <div class="row Writes">
	        </div>
        </div>
     
         
         
        <aside class="large-3 columns hide-for-small">
          <p><img src="http://placehold.it/300x440&text=[ad]"/></p>
          <p><img src="http://placehold.it/300x440&text=[ad]"/></p>
        </aside>
     
      </div>
  </body>
</html>
