var num_of_appending=3;
	var num_of_appended=0;
	
	var writesList=new Array();
	
	function loadWrites(){
		$(".Writes").append("<hr/>");
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
	     		"<h6 class='commentShowButton'><a onClick='showComment("+seq+")'>Comments (2)</a></h6>"+
	              "<div id='comment_"+seq+"'>"+
		          "</div>"+
	            "</div>"+
	          "</div>");
		num_of_appended++;
	}
	
	function appendWithEffect(WRITE_CONTENT,seq){
		var newWrite=$(
				"<hr/>"+
				"<div class='row write' style='display:none'  id='seq_"+seq+"'>"+
	            	"<div class='large-2 columns small-3'><img src='http://placehold.it/80x80&text=[img]'/></div>"+
		            "<div class='large-10 columns'>"+
		              "<p><strong>Some Person said:</strong> "+WRITE_CONTENT+"</p>"+
		              "<ul class='inline-list'>"+
		                "<li><a href=''>Reply</a></li>"+
		                "<li><a href=''>Share</a></li>"+
		              "</ul>"+
		              "<h6 class='commentShowButton'><a onClick='showComment("+seq+")'>Comments (2)</a></h6>"+
		              "<div id='comment_"+seq+"'>"+
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
	
	function showComment(seq){
		
		var parentSeq=seq;
		
		$.ajax({
			url : "writes/getCommentList.do",
			type : "GET",
			data : {
				"parentSeq": parentSeq
			},
			dataType : "json",
			success : function(result) {
				var resultList=result.resultList;
				var parentSeq=result.parentSeq;
				for(var i=0;i<resultList.length;i++){
					var comment=resultList[i].comments_CONTENT
					var sHtml="<div class='row'>"+
								  "<div class='large-2 columns small-3'><img src='http://placehold.it/50x50&text=[img]'/></div>"+
								  "<div class='large-10 columns'><p>"+comment+"</p></div>"+
							  "</div>";
					
					$("#comment_"+parentSeq).append(sHtml);
				}
				
				$("#seq_"+parentSeq).find(".commentShowButton").remove();
			}, error : function (xhr, status, err) {
				alert("xhr : " + xhr + ", err :" + err);
			}
		});
	}