<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Foundation | Welcome</title>
    <link rel="stylesheet" href="../resources/css/foundation.css" />
    <script src="../resources/js/vendor/modernizr.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script>
	var num_of_appending=3;
	var num_of_appended=0;
	
	var writesList=new Array();
	
	function loadWrites(){
		$(".Writes").append("<hr/>");
	}
	
	function loadWritesAtFirst(){
		
		 <c:forEach var="write" items="${writes}">
		 	writesList.push("${write.WRITES_CONTENT}");
		 </c:forEach>
		 
		 for(var i=0;i<3;i++){
			 appendWrites(writesList[i]);
		 }
	}
	
	function sendWritesindexGetReplies(seq){
		$.ajax({
			url : "writes/content.do",
			type : "GET",
			data : {
				"seq": seq
			},
			dataType : "json",
			success : function(data) {
				alert(data.seq);				
			}, error : function (xhr, status, err) {
				alert("xhr : " + xhr + ", err :" + err);
			}
		});
	}
	
	function appendWrites(WRITE_CONTENT){
		$(".Writes").append("<hr/>"+
				"<div class='row'>"+
	            "<div class='large-2 columns small-3'><img src='http://placehold.it/80x80&text=[img]'/></div>"+
	            "<div class='large-10 columns'>"+
	              "<p><strong>Some Person said:</strong> "+WRITE_CONTENT+"</p>"+
	              "<ul class='inline-list'>"+
	                "<li><a href=''>Reply</a></li>"+
	                "<li><a href=''>Share</a></li>"+
	              "</ul>"+
	     		""+
	     		""+
	              "<h6>2 Comments</h6>"+
	              "<div class='row'>"+
	                "<div class='large-2 columns small-3'><img src='http://placehold.it/50x50&text=[img]'/></div>"+
	                "<div class='large-10 columns'><p>${comments.get(0).COMMENTS_CONTENT}</p></div>"+
	              "</div>"+
	              "<div class='row'>"+
	                "<div class='large-2 columns small-3'><img src='http://placehold.it/50x50&text=[img]'/></div>"+
	                "<div class='large-10 columns'><p>Bacon ipsum dolor sit amet nulla ham qui sint exercitation eiusmod commodo, chuck duis velit. Aute in reprehenderit</p></div>"+
	              "</div>"+
	            "</div>"+
	          "</div>");
		num_of_appended++;
	}
	
	function appendWithEffect(WRITE_CONTENT){
		var newWrite=$("<hr/>"+
				"<div class='row' style='display:none'>"+
	            "<div class='large-2 columns small-3'><img src='http://placehold.it/80x80&text=[img]'/></div>"+
	            "<div class='large-10 columns'>"+
	              "<p><strong>Some Person said:</strong> "+WRITE_CONTENT+"</p>"+
	              "<ul class='inline-list'>"+
	                "<li><a href=''>Reply</a></li>"+
	                "<li><a href=''>Share</a></li>"+
	              "</ul>"+
	     		""+
	     		""+
	              "<h6>2 Comments</h6>"+
	              "<div class='row'>"+
	                "<div class='large-2 columns small-3'><img src='http://placehold.it/50x50&text=[img]'/></div>"+
	                "<div class='large-10 columns'><p>${comments.get(0).COMMENTS_CONTENT}</p></div>"+
	              "</div>"+
	              "<div class='row'>"+
	                "<div class='large-2 columns small-3'><img src='http://placehold.it/50x50&text=[img]'/></div>"+
	                "<div class='large-10 columns'><p>Bacon ipsum dolor sit amet nulla ham qui sint exercitation eiusmod commodo, chuck duis velit. Aute in reprehenderit</p></div>"+
	              "</div>"+
	            "</div>"+
	          "</div>");
		$(".Writes").append(newWrite);
		newWrite.show("slow");
		num_of_appended++;
	}
	</script>
  </head>
  <body>
    
      <div class="row">
        <div class="large-12 columns">
          <div class="panel">
            <h1>Feed Template</h1>
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
        
         
         
        <div class="large-6 columns Writes">
     
           
          <div class="row">
            <div class="large-2 columns small-3"><img src="http://placehold.it/80x80&text=[img]"/></div>
            <div class="large-10 columns">
              <p><strong>Some Person said:</strong> ${writes.get(0).WRITES_CONTENT}</p>
              <ul class="inline-list">
                <li><a href="">Reply</a></li>
                <li><a href="">Share</a></li>
              </ul>
     
     
              <h6>2 Comments</h6>
              <div class="row">
                <div class="large-2 columns small-3"><img src="http://placehold.it/50x50&text=[img]"/></div>
                <div class="large-10 columns"><p>${comments.get(0).COMMENTS_CONTENT}</p></div>
              </div>
              <div class="row">
                <div class="large-2 columns small-3"><img src="http://placehold.it/50x50&text=[img]"/></div>
                <div class="large-10 columns"><p>이것은 가비지 태그, DB에서불러온것 아님</p></div>
              </div>
            </div>
          </div>
        </div>
     
         
         
        <aside class="large-3 columns hide-for-small">
          <p><img src="http://placehold.it/300x440&text=[ad]"/></p>
          <p><img src="http://placehold.it/300x440&text=[ad]"/></p>
        </aside>
     
      </div>
    
    <script src="../resources/js/vendor/jquery.js"></script>
    <script src="../resources/js/foundation.min.js"></script>
    <script>
		$(document).foundation();
		
		loadWritesAtFirst();
      
		$(window).scroll(function() {
			if($(window).scrollTop() + $(window).height() == $(document).height()) {
				for(var i=num_of_appended;i<num_of_appending+num_of_appended && i<writesList.length;i++){
					appendWithEffect(writesList[i]);
				}
				sendWritesindexGetReplies(1);
			}
		});
    </script>
  </body>
</html>
