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
		 appendWrites("${write.WRITES_CONTENT}","${write.seq}");
		 </c:forEach>
		 
	}
	
	function pageClear(){
		alert('clear');
		$(".Writes").html("");
	}
	
	function sendWritesindexGetReplies(index_num){
		$.ajax({
			url : "writes/getcontent.do",
			type : "GET",
			data : {
				"index_num": index_num
			},
			dataType : "json",
			success : function(data) {
				for(var i=0;i<data.writes.length;i++){
					appendWithEffect(data.writes[i].writes_CONTENT,data.writes[i].seq);
				}
			}, error : function (xhr, status, err) {
				alert("xhr : " + xhr + ", err :" + err);
			}
		});
	}
	
	function prependWithEffect(WRITE_CONTENT){
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
		$(".Writes").prepend(newWrite);
		newWrite.show("slow");
	}
	
	function appendWrites(WRITE_CONTENT,seq){
		$(".Writes").append("<hr/>"+
				"<div class='row write' id='seq_"+seq+"'>"+
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
	
	function appendWithEffect(WRITE_CONTENT,seq){
		var newWrite=$("<hr/>"+
				"<div class='row write' style='display:none'  id='seq_"+seq+"'>"+
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
	
	function Writes(){
		var write=$("#Writes").val();
		$.ajax({
			url : "writes/write.do",
			type : "GET",
			data : {
				"write": write
			},
			dataType : "json",
			success : function(data) {
				prependWithEffect(data.write);				
			}, error : function (xhr, status, err) {
				alert("xhr : " + xhr + ", err :" + err);
			}
		});
	}
	</script>
  </head>
  <body>
    
      <div class="row" id="sub_header">
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
    
    <script src="../resources/js/vendor/jquery.js"></script>
    <script src="../resources/js/foundation.min.js"></script>
    <script>
		$(document).foundation();
		
		loadWritesAtFirst();
      
		$(window).scroll(function() {
			if($(window).scrollTop() + $(window).height() == $(document).height()) {
				var id_num=$(".write:last").attr("id");
				
				var index_num=id_num.substring(id_num.indexOf("_")+1);
				
				console.log(index_num);
				
				sendWritesindexGetReplies(index_num);
			}
		});
    </script>
  </body>
</html>
