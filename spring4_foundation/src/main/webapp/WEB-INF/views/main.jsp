<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>패턴 데이터 관리 시스템</title>
<link rel="stylesheet" href="../resources/css/foundation.css" />
<link rel="stylesheet" href="../resources/css/common.css" />
<link rel="stylesheet" href="../resources/foundation-icons/foundation-icons.css" />
<script src="../resources/js/vendor/modernizr.js"></script>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="../resources/js/jquery.highlight-5.js"></script>
<script src="../resources/js/common/mainFunction.js"></script>
</head>
<body>
	
	
	<input id="fileupload" name="fileupload" type="file" style="display:none"/>
		<div id="vertical-bar">
			<div class="icon-bar vertical six-up">
				<a class="item" onClick="location.reload()">
					<i class="fi-home"></i>
				</a>
				<a class="item" onClick="selectFile()">
					<i class="fi-page-add"></i>
				</a>
				<a class="item" id="uploadicon">
					<i class="fi-upload"></i>
				</a>
				<a class="item" onClick="showFileDownloadList()">
					<i class="fi-download"></i>
				</a>
				<a class="item" onClick="rowDelete_confirm()">
					<i class="fi-page-delete"></i>
				</a>
				<a class="item" onClick="DELETEALL()">
					<i class="fi-trash"></i>
				</a>
			</div>
		</div>

			<div class="row">
				<ul class="tabs" data-tab>
					<li class="tab-title active"><a href="#panel1">DB 패턴 데이터</a></li>
					<li class="tab-title"><a href="#panel2">패턴 번역 테스트</a></li>
					<li class="tab-title"><a href="#panel3">패턴 데이터 입력하기</a></li>
		<!-- 			<li class="tab-title"><a href="#panel4">Tab 4</a></li> -->
				</ul>
			</div>
			<div class="row">
				<div class="tabs-content">
					<div class="content active" id="panel1">
						<div class="row">
							<div class="small-13 columns">
								<div class="row collapse">
									<div class="small-2 columns">
										<select id="choose_kind_pattern">
								          <option value="ORG_PTTRN">원문 패턴</option>
								          <option value="TRNST_PTTRN">번역문 패턴</option>
								        </select>
									</div>
									<div class="small-9 columns">
							          <input type="text" id="text_to_search" placeholder="패턴 검색하기">
							        </div>
							        <div class="small-1 columns">
							          <a id="searchButton" class="button postfix" onClick="searchDBPattern()">검색</a>
							        </div>
						        </div>
						        
						        <div class="row collapse" id="table_option">
						        	<div class="small-8 columns">
										<h5 id="ShowtotalCount"><small></small></h5>
						        	</div>
						        	<div class="small-3 columns" id="select_list">
							        	<div class="range-slider" id="select_list_size" data-slider data-options="start: 10; end: 500;display_selector: #days-off-count;initial: 10;">
											<span class="range-slider-handle" role="slider" tabindex="0"></span>
											<span class="range-slider-active-segment"></span>
											<input type="hidden">
										</div>
									</div>
									<div class="small-1 columns">
										<input type="number" id="days-off-count" value="10"/>
									</div>
						        </div>
							    <table role="grid" class="table" id="db_pattern">
							    	<thead>
								    	<tr>
								    		<th width="60" onClick="selectSortBy('SEQ')" style="cursor: pointer">번호</th>
											<th width="500" onClick="selectSortBy('ORG_PTTRN')" style="cursor: pointer" class="title_text">원문 패턴</th>
											<th width="500" onClick="selectSortBy('TRNST_PTTRN')" style="cursor: pointer" class="title_text">번역 패턴</th>
											<th width="100"></th>
									    </tr>
								    </thead>
								    <tbody id="db_filelist">
								    	<!-- 파일리스트 -->
									</tbody>
							    </table>
							    <div class="pagination-centered">
									<ul class="pagination" id="db_filelist_pagination">
									</ul>
								</div>
						    </div>
					    </div>
					</div>
					<div class="content" id="panel2">
						<div class="row">
							<div class="small-13 columns">
								<fieldset>
									<legend>패턴 입력</legend>
									<div class="row">
										<div class="small-6 columns">
											<label>원문 패턴
												<input type="text" id="new_org_pttrn" placeholder="입력할 원문 패턴">
											</label>
										</div>
										<div class="small-6 columns">
											<label>번역 패턴
												<input type="text" id="new_trnst_pttrn" placeholder="입력할 번역 패턴">
											</label>
										</div>
									</div>
									<div class="row">
										<div class="small-6 columns">
											<label>원문 문장
												<textarea id="org_cont" placeholder="원문문장 입력"></textarea>
											</label>
										</div>
										<div class="small-6 columns">
											<label>번역 문장
												<textarea id="trnst_cont" readonly></textarea>
											</label>
										</div>
									</div>

									<div class="row">
										<div class="small-2 columns" id="insert_div">
											<a class="tiny button" onClick="patternTranslator()">번역</a>
										</div>
										<div class="large-10 columns" id="text_warning">
										</div>
									</div>
									
								</fieldset>
							</div>
						</div>
						<div class="row">
							<div class="small-12" id="patternReturnList">
							</div>
						</div>
					</div>
					<div class="content" id="panel3">
						<div class="row">
							<div class="small-9 columns">
								<fieldset>
									<legend>패턴 입력</legend>
									
									<label>원문 패턴
										<input type="text" id="org_insert" placeholder="입력할 원문 패턴">
									</label>
									<label>번역 패턴
										<input type="text" id="trnst_insert" placeholder="입력할 번역 패턴">
									</label>
									<div class="row">
										<div class="small-2 columns" id="insert_div">
											<a class="tiny button" onClick="insertPattern()">입력</a>
										</div>
										<div class="large-10 columns" id="text_warning">
										</div>
									</div>
								</fieldset>
							</div>
						</div>
						
						
					</div>
					<div class="content" id="panel4">
						<div class="row">
							<div class="small-8 columns">
								<div class="row">
									<div class="small-9 columns">
										<input type="text" id="file_path" placeholder="업로드할 파일 선택">
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="small-9 columns">
							    <table role="grid">
							    	<thead>
								    	<tr>
								    		<th width="50">번호</th>
											<th width="500" class="title_text">원문 패턴</th>
											<th width="500" class="title_text">번역 패턴</th>
											<th width="300"></th>
									    </tr>
								    </thead>
								    <tbody id="excel_filelist">
								    	<!-- 파일리스트 -->
									</tbody>
							    </table>
							    <div class="pagination-centered">
									<ul class="pagination" id="excel_filelist_pagination">
									</ul>
								</div>
						    </div>
					    </div>
					</div>
				</div>
			</div>

	
	<div id="div_for_table_export" style="display:none">
		<table id="db_pattern_list" border="1">
			<thead>
		    	<tr>
		    		<th width="50" style="background-color:#F5F5F5">번호</th>
					<th width="300" style="background-color:#F5F5F5">원문 패턴</th>
					<th width="300" style="background-color:#F5F5F5">번역 패턴</th>
			    </tr>
		    </thead>
		    <tbody id="db_filelist_export">
			</tbody>
		</table>
	</div>

	<a href="#" id="modalTrigger" data-reveal-id="myModal" style="display:none">Click Me For A Modal</a>

	<div id="myModal" class="tiny reveal-modal" data-reveal aria-labelledby="modalTitle" aria-hidden="true" role="dialog">
	</div>
	
	
	<a href="#" id="fileupload_modalTrigger" data-reveal-id="fileupload_modal" style="display:none">Click Me For A Modal</a>
	<div id="fileupload_modal" class="large reveal-modal" data-reveal aria-labelledby="modalTitle" aria-hidden="true" role="dialog">
		<div id="dragandrophandler">업로드할 파일</div>
		<div id="status1"></div>
	</div>
	
	
	<intercept-url pattern="/favicon.ico" access="ROLE_ANONYMOUS" />

<!-- 	<div> -->
<!--         <button id="connect" onclick="connect();">Connect</button> -->
<!--         <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button> -->
<!--     </div> -->
<!--     <div id="conversationDiv"> -->
<!--         <label>What is your name?</label><input type="text" id="name" /> -->
<!--         <button id="sendName" onclick="sendName();">Send</button> -->
<!--         <p id="response"></p> -->
<!--     </div> -->
<!--     <a href="/pause">pause</a> -->
<!--     <a href="revive">resume</a> -->
    
<!-- 	<object type="image/svg+xml" data="../resources/foundation-icons/svgs/fi-loading-rotate.svg">Your browser does not support SVG</object> -->

	
    <script src="../resources/js/vendor/jquery.js"></script>
    <script src="../resources/js/foundation.min.js"></script>
    <script src="../resources/js/jquery.table2excel.min.js"></script>
    <script src="../resources/js/jquery.base64.js"></script>
    <script src="../resources/js/jquery.highlight-5.js"></script>
    <script src="../resources/js/common/FileUploadFunction.js"></script>
    <script>
	    function downloadExcel(innerHtml_div_id,filename){
			var html_export_template_pre="<html xmlns:o='urn:schemas-microsoft-com:office:office'"+
			"mlns:x='urn:schemas-microsoft-com:office:excel'"+
				"xmlns='http://www.w3.org/TR/REC-html40'>"+
	
				"<head>"+
				"<meta http-equiv=Content-Type content='text/html; charset=utf-8'>"+
				"<meta name=ProgId content=Excel.Sheet>"+
				"<meta name=Generator content='Microsoft Excel 11'>"+
	
				"<!--[if gte mso 9]><xml>"+
				 "<x:ExcelWorkbook>"+
				  "<x:ExcelWorksheets>"+
				   "<x:ExcelWorksheet>"+
				    "<x:Name>Worksheet Name</x:Name>"+
				    "<x:WorksheetOptions>"+
				     "<x:Selected/>"+
				     "<x:FreezePanes/>"+
				     "<x:FrozenNoSplit/>"+
				     "<x:SplitHorizontal>1</x:SplitHorizontal>"+
				     "<x:TopRowBottomPane>1</x:TopRowBottomPane>"+
				     "<x:SplitVertical>1</x:SplitVertical>"+
				     "<x:LeftColumnRightPane>1</x:LeftColumnRightPane>"+
				     "<x:ActivePane>0</x:ActivePane>"+
				     "<x:Panes>"+
				      "<x:Pane>"+
				       "<x:Number>3</x:Number>"+
				      "</x:Pane>"+
				      "<x:Pane>"+
				       "<x:Number>1</x:Number>"+
				      "</x:Pane>"+
				      "<x:Pane>"+
				       "<x:Number>2</x:Number>"+
				      "</x:Pane>"+
				      "<x:Pane>"+
				       "<x:Number>0</x:Number>"+
				      "</x:Pane>"+
				     "</x:Panes>"+
				     "<x:ProtectContents>False</x:ProtectContents>"+
				     "<x:ProtectObjects>False</x:ProtectObjects>"+
				     "<x:ProtectScenarios>False</x:ProtectScenarios>"+
				    "</x:WorksheetOptions>"+
				   "</x:ExcelWorksheet>"+
				  "</x:ExcelWorksheets>"+
				  "<x:ProtectStructure>False</x:ProtectStructure>"+
				  "<x:ProtectWindows>False</x:ProtectWindows>"+
				 "</x:ExcelWorkbook>"+
				"</xml><![endif]-->"+
				"</head>"+
				"<body>";
				
			var html_export_template_post="</body></html>";
			var dt = new Date();
		    var day = dt.getDate();
		    var month = dt.getMonth() + 1;
		    var year = dt.getFullYear();
		    var hour = dt.getHours();
		    var mins = dt.getMinutes();
		    var postfix = day + "." + month + "." + year + "_" + hour + "." + mins;
		    //creating a temporary HTML link element (they support setting file names)
		    var a = document.createElement('a');
		    //getting data from our div that contains the HTML table
		    var data_type = 'data:application/vnd.ms-excel;base64;charset=utf-8';
		    var table_div = document.getElementById('div_for_table_export');
		    var table_html_outer=$.base64.encode(html_export_template_pre+table_div.innerHTML+html_export_template_post);
		    var table_html = table_html_outer.replace(/ /g, '%20');
		    a.href = data_type + ', ' + table_html;
		    //setting the file name
		    a.download = filename + '.xls';
		    //triggering the function
		    a.click();
		    //just in case, prevent default behaviour
		}

    </script>
    <script src="../resources/js/sockjs-0.3.4.js"></script>
    <script src="../resources/js/stomp.js"></script>
    <script src="../resources/js/common/ConnectFunction.js"></script>
    <script>
	    function ThreadObserve(){
	    	$.ajax({
	    		url : "threadObserve",
	    		type : "GET",
	    		dataType : "json",
	    		async: true,
	    		success : function(data) {
	    			ThreadObserve();
	    		}, error : function (xhr, status, err) {
	    			alert("xhr : " + xhr + ", err :" + err);
	    		}
	    	});
	    }
    
    	var delay = (function(){
			var timer = 0;
			return function(callback, ms){
				clearTimeout (timer);
				timer = setTimeout(callback, ms);
			};
    	})();
    	
    	$(document).foundation({
    		  slider: {
    		    on_change: function(){
    		    	delay(function(){
    		    		var new_howMany=$("#days-off-count").val();
    		    		
    		    		if(howMany!=new_howMany){
							howMany=new_howMany;
							$("#select_list_size").foundation('slider', 'set_value', new_howMany);
							searchDBPattern();
    		    		}
    		    	}, 1000 );
    		    }
    		  }
    	});
    	
    	$(document).on('keypress', function(e) {
    	    var tag = e.target.tagName.toLowerCase();
    	    if (tag != 'input' && tag != 'textarea') 
//     	    	console.log(e.which);
    	        if(e.which==97){
    	        	$("#available_prev_arrow").click();
    	        }
    	        else if(e.which==100){
    	        	$("#available_next_arrow").click();
    	        }
    	});
    	
		$(document).ready(function(){
		
		    $("#fileupload").change(function() {
		        var filename=$("#fileupload")[0].files[0].name;
		        $("#file_path").val(filename);
		        
		    });
		    
		    $('#text_to_search').keyup(function(e) {
		    	if (e.which == 13){
		    		searchDBPattern();
	    	    }
		    	else if(e.which == 38){
		    		$("#choose_kind_pattern").val("ORG_PTTRN");
		    	}
		    	else if(e.which == 40){
		    		$("#choose_kind_pattern").val("TRNST_PTTRN");
		    	}
		    	else{
			    	delay(function(){
			    		searchDBPattern();
			    	}, 1000 );
		    	}
		    });
		    
		});
		
		$(document).foundation();
		showFileList(0,howMany);
		
		connect();
		
		$("#uploadicon").on("click",function(){showProgress()});
		
	</script>
</body>
</html>