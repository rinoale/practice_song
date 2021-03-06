var currentPage=0;
var howMany=10;
var currentPageFirstNumber=0;
var max_list=7;
var totalPages=0;
var tagId_toChange;
var html_backup;
var ASC_or_DESC="DESC";
var sortBy="SEQ";
var functionToExecuse;

function showFileList(fromIndex){
	$.ajax({
		url : "dbAccess",
		type : "GET",
		data : {
			"fromIndex": fromIndex,
			"howMany": howMany,
			"sortBy": sortBy,
			"ASC_or_DESC": ASC_or_DESC
		},
		dataType : "json",
		success : function(data) {
			for(var i=0;i<data.TB_PATTERN_DATA_LIST.length;i++){
				createHtmlTable(data.TB_PATTERN_DATA_LIST[i], "#db_filelist");
			}
			pagination(data.totalCount,"#db_filelist_pagination");
			totalPages=Math.ceil(data.totalCount/howMany);
			showTotalCount(data.totalCount);
			addSelectEvent();
		}, error : function (xhr, status, err) {
			alert("xhr : " + xhr + ", err :" + err);
		}
	});
}

function searchFileList(fromIndex,url){
	var chosen_column=$("#choose_kind_pattern").val()
	var text_to_search=$("#text_to_search").val();
	$.ajax({
		url : url,
		type : "GET",
		data : {
			"fromIndex": fromIndex,
			"howMany": howMany,
			"sortBy": sortBy,
			"ASC_or_DESC": ASC_or_DESC,
			"chosen_column": chosen_column,
			"text_to_search": text_to_search
		},
		dataType : "json",
		async: true,
		success : function(data) {
			for(var i=0;i<data.TB_PATTERN_DATA_LIST.length;i++){
				createHtmlTable(data.TB_PATTERN_DATA_LIST[i], "#db_filelist");
			}
			pagination(data.totalCount,"#db_filelist_pagination");
			totalPages=Math.ceil(data.totalCount/howMany);
			
			showTotalCount(data.totalCount);
			
			var text_to_search=$("#text_to_search").val();
			addSelectEvent();
    		$('tbody').highlight(text_to_search);
		}, error : function (xhr, status, err) {
			alert("xhr : " + xhr + ", err :" + err);
		}
	});
}

function createHtmlTable(TB_PATTERN_DATA,table_id){

	var sHtml="<tr id='pattern_"+TB_PATTERN_DATA.seq+"'>"+
			  "<td id='pattern_seq' class='seq_num'>"+TB_PATTERN_DATA.seq+"</td>"+
			  "<td id='pattern_org'>"+TB_PATTERN_DATA.org_PTTRN+"</td>"+
			  "<td id='pattern_trnst'>"+TB_PATTERN_DATA.trnst_PTTRN+"</td>"+
			  "<td id='edit_menu'>"+
			  "<div class='icon-bar two-up'>"+
			  "<a class='item' onClick='dbEdit("+TB_PATTERN_DATA.seq+")'>"+
			  "<i class='fi-page-edit' id='edit_icon'></i>"+
			  "</a>"+
			  "<a class='item' onClick='dbDelete("+TB_PATTERN_DATA.seq+")'>"+
			  "<i class='fi-page-delete' id='delete_icon'></i>"+
			  "</a>"+
			  "</div>"+
			  "</td>"+
			  "</tr>";
	$(table_id).append(sHtml);
}

function createHtmlTable_export(TB_PATTERN_DATA,table_id){

	var sHtml="<tr>"+
			  "<td style='text-align:center'>"+TB_PATTERN_DATA.seq+"</td>"+
			  "<td>"+TB_PATTERN_DATA.org_PTTRN+"</td>"+
			  "<td>"+TB_PATTERN_DATA.trnst_PTTRN+"</td>"+
			  "</tr>";
	$(table_id).append(sHtml);
}

function dbEdit(seq){
	tagId_toChange="#pattern_"+seq;
	var tr_id="#pattern_"+seq;
	
	findEditingRow();
	
	$(tr_id).addClass("editing");
	
	html_backup=$(tr_id).html();
	
	var tr_id_org=$(tr_id).find("#pattern_org");
	
	var tr_id_org_text=$(tr_id).find("#pattern_org").text();
	
	var tr_id_trnst=$(tr_id).find("#pattern_trnst");
	
	var tr_id_trnst_text=$(tr_id).find("#pattern_trnst").text();
	
	var tr_id_menu=$(tr_id).find("#edit_menu");
	
	var sHtml_org="<textarea id='org_edit_"+seq+"'>"+tr_id_org_text+"</textarea>";
	var sHtml_trnst="<textarea id='trnst_edit_"+seq+"'>"+tr_id_trnst_text+"</textarea>";
	$(tr_id_org).html(sHtml_org);
	$(tr_id_trnst).html(sHtml_trnst);
	
	var sHtml_menu="<div class='icon-bar two-up' id='edit_sub_menu'>"+
	  "<a class='item' id='edit_text' onClick='edit_confirm("+seq+")'>"+
	  "<p>수정</p>"+
	  "</a>"+
	  "<a class='item' id='cancel_text' onClick='rollback()'>"+
	  "<p>취소<p>"+
	  "</a>"+
	  "</div>"
	$(tr_id_menu).html(sHtml_menu);
}

function dbDelete(seq){
	tagId_toChange="#pattern_"+seq;
	var tr_id="#pattern_"+seq;
	
	findEditingRow();
	
	$(tr_id).addClass("editing");
	
	html_backup=$(tr_id).html();

	var tr_id_menu=$(tr_id).find("#edit_menu");

	var sHtml_menu="<div class='icon-bar two-up' id='edit_sub_menu'>"+
	  "<a class='item' id='edit_text' onClick='delete_confirm("+seq+")'>"+
	  "<p>삭제</p>"+
	  "</a>"+
	  "<a class='item' id='cancel_text' onClick='rollback()'>"+
	  "<p>취소</p>"+
	  "</a>"+
	  "</div>"
	$(tr_id_menu).html(sHtml_menu);
}

function delete_confirm(seq){
	var seq_list=new Array();
	if(seq.length==null){
		seq_list.push(seq);
	}
	else if(seq.length>=1){
		for(var i=0;i<seq.length;i++){
			seq_list.push(seq[i]);
		}
	}
	
	var seq_list_json=JSON.stringify(seq_list);
	
	$.ajax({
		url : "dbDelete",
		type : "GET",
		data : {
			"SEQ_LIST": seq_list_json
		},
		dataType : "json",
		success : function(data) {
//			console.log(data.result);
			location.reload();
		}, error : function (xhr, status, err) {
			alert("xhr : " + xhr + ", err :" + err);
		}
	});
}

function findEditingRow(){
	var editing_row=$(".editing");
	
	if(editing_row.length>0){
		$(editing_row).html(html_backup);
		tagId_toChange="";
		html_backup="";
		editing_row.removeClass("editing");
	}
}

function patternTranslator() {
	var new_org_pttrn=$("#new_org_pttrn").val();
	var new_trnst_pttrn=$("#new_trnst_pttrn").val();
	var org_cont=$("#org_cont").val();
	
	$.ajax({
		url : "pttrnTransAccess",
		type : "GET",
		data : {
			"new_org_pttrn": new_org_pttrn,
			"new_trnst_pttrn": new_trnst_pttrn,
			"org_cont": org_cont
		},
		dataType : "json",
		async: true,
		beforeSend:function() {
		       var loading="<img name='loadingImage' id='loadingImg_Trans' src='../resources/img/loading_anim.gif'  width='100px' height='100px' />";
		       $("#patternReturnList").html(loading);
		},
		success : function(data) {
//			console.log(data.result);
			$("#trnst_cont").val(data.result);
//			console.log(data.isApplyPattern);
//			console.log(data.patternReturnList);
//			console.log(data.transPatternExtMsgList);
			var patternReturnList=data.patternReturnList;
			
			var org_pttrn=data.org_pttrn;
			var trnst_pttrn=data.trnst_pttrn;
			$("#new_org_pttrn").val(org_pttrn);
			$("#new_trnst_pttrn").val(trnst_pttrn);
			
			var sHtml_pre="<table>"+
			  		  "<tbody>";
			var sHtml="";
			for(var i=0;i<patternReturnList.length;i++){
				var bgc;
				
				if(i%2==0){
					bgc="#ffffff";
				}
				else{
					bgc="#f0f0f0";
				}
				
				  sHtml=sHtml+"<tr>"+
						      "<td rowspan='2' style='width: 60px;background-color:"+bgc+"'>"+patternReturnList[i].patternId+"</td>"+
					  	      "<td style='width: 60px;'>원문</td>"+
						      "<td style='width: 870px;'>"+patternReturnList[i].originalText+"</td>"+
						      "</tr>"+
						      "<tr>"+
						      "<td>번역</td>"+
						      "<td style='width: 870px;'>"+patternReturnList[i].transText+"</td>"+
						      "</tr>";
			}
			
			var sHtml_post="</tbody>"+
						   "</table>";
			$("#patternReturnList").html(sHtml_pre+sHtml+sHtml_post);
		}, error : function (xhr, status, err) {
			$("#patternReturnList").html("");
			alert("xhr : " + xhr + ", err :" + err);
		}
	});
}

function rollback(){
	$(tagId_toChange).html(html_backup);

	findEditingRow();
}

function edit_confirm(seq){
	var tr_id="#pattern_"+seq;
	
	var org_pttrn_edit=$(tr_id).find("#org_edit_"+seq).val();
	var trnst_pttrn_edit=$(tr_id).find("#trnst_edit_"+seq).val();
	
	$.ajax({
		url : "dbEdit",
		type : "GET",
		data : {
			"SEQ": seq,
			"ORG_PTTRN": org_pttrn_edit,
			"TRNST_PTTRN": trnst_pttrn_edit
		},
		dataType : "json",
		async: true,
		success : function(data) {
//			console.log(data.result);
			if(data.result=="이미 존재하는 패턴입니다."){
				rollback();
				addSelectEvent();
			}else if(data.result=="입력 완료"){
				replModifiedSentence(seq);
				addSelectEvent();
			}
		}, error : function (xhr, status, err) {
			alert("xhr : " + xhr + ", err :" + err);
		}
	});
	
}

function selectSortBy(sortByThis){
	if(ASC_or_DESC=="ASC"){
		ASC_or_DESC="DESC";
	}else if(ASC_or_DESC=="DESC"){
		ASC_or_DESC="ASC";
	}
	
	sortBy=sortByThis;
	
	clearListPage("#db_filelist","#db_filelist_pagination");
	currentPage=0;
	
	searchFileList(0,"dbSearch");
	
}

function replModifiedSentence(seq){
	
	var tr_id="#pattern_"+seq;
	var org_pttrn_edit=$(tr_id).find("#org_edit_"+seq).val();
	var trnst_pttrn_edit=$(tr_id).find("#trnst_edit_"+seq).val();
	
	rollback();
	
	$(tr_id).find("#pattern_org").text(org_pttrn_edit);
	$(tr_id).find("#pattern_trnst").text(trnst_pttrn_edit);
	
	
	
}

function pagination(totalCount, pagination_id){
	if(currentPage!=0){
		var sHtml="<li class='arrow'><a id='available_prev_arrow' onClick='goToPrevPages()'>&laquo;</a></li>";
	}
	else{
		var sHtml="<li class='arrow unavailable'><a>&laquo;</a></li>";
	}
	
	var pages=totalCount/howMany;
	if(pages<9){
		for(var i=0;i<pages;i++){
			var page=i+1;
			if(i==currentPage){
				sHtml=sHtml+"<li class='current'><a href=''>"+page+"</a></li>";
			}
			else{
				sHtml=sHtml+"<li><a onClick='goToPage("+i+")'>"+page+"</a></li>";
			}
		}
	}
	else{
		if(4<currentPage){
			for(var i=0;i<2;i++){
				var page=i+1;
				if(i==currentPage){
					sHtml=sHtml+"<li class='current'><a href=''>"+page+"</a></li>";
				}
				else{
					sHtml=sHtml+"<li><a onClick='goToPage("+i+")'>"+page+"</a></li>";
				}
			}
			sHtml=sHtml+"<li class='unavailable'><a href=''>&hellip;</a></li>";
			for(var i=currentPage-2;i<=currentPage;i++){
				var page=i+1;
				if(i==currentPage){
					sHtml=sHtml+"<li class='current'><a href=''>"+page+"</a></li>";
				}
				else{
					sHtml=sHtml+"<li><a onClick='goToPage("+i+")'>"+page+"</a></li>";
				}
			}
			if(currentPage<pages-5){
				for(var i=currentPage+1;i<currentPage+3;i++){
					var page=i+1;
					if(i==currentPage){
						sHtml=sHtml+"<li class='current'><a href=''>"+page+"</a></li>";
					}
					else{
						sHtml=sHtml+"<li><a onClick='goToPage("+i+")'>"+page+"</a></li>";
					}
				}
				sHtml=sHtml+"<li class='unavailable'><a href=''>&hellip;</a></li>";
				var pages_ceil=Math.ceil(pages);
				for(var i=pages_ceil-2;i<pages_ceil;i++){
					var page=i+1;
					if(i==currentPage){
						sHtml=sHtml+"<li class='current'><a href=''>"+page+"</a></li>";
					}
					else{
						sHtml=sHtml+"<li><a onClick='goToPage("+i+")'>"+page+"</a></li>";
					}
				}
			}
			else{
				for(var i=currentPage+1;i<pages;i++){
					var page=i+1;
					if(i==currentPage){
						sHtml=sHtml+"<li class='current'><a href=''>"+page+"</a></li>";
					}
					else{
						sHtml=sHtml+"<li><a onClick='goToPage("+i+")'>"+page+"</a></li>";
					}
				}
			}
		}
		else{
			for(var i=0;i<max_list;i++){
				var page=i+1;
				if(i==currentPage){
					sHtml=sHtml+"<li class='current'><a href=''>"+page+"</a></li>";
				}
				else{
					sHtml=sHtml+"<li><a onClick='goToPage("+i+")'>"+page+"</a></li>";
				}
			}
			if(max_list<pages){
				if(currentPage<pages-4){
					sHtml=sHtml+"<li class='unavailable'><a href=''>&hellip;</a></li>";
					var pages_ceil=Math.ceil(pages);
					for(var i=pages_ceil-2;i<pages_ceil;i++){
						var page=i+1;
						if(i==currentPage){
							sHtml=sHtml+"<li class='current'><a href=''>"+page+"</a></li>";
						}
						else{
							sHtml=sHtml+"<li><a onClick='goToPage("+i+")'>"+page+"</a></li>";
						}
					}
				}
				else{
					for(var i=currentPage+1;i<pages;i++){
						var page=i+1;
						if(i==currentPage){
							sHtml=sHtml+"<li class='current'><a href=''>"+page+"</a></li>";
						}
						else{
							sHtml=sHtml+"<li><a onClick='goToPage("+i+")'>"+page+"</a></li>";
						}
					}
				}
			}
		}
	}
	
	var pages_round=Math.floor(pages);
	if(currentPage==pages_round){
		sHtml=sHtml+"<li class='arrow unavailable'><a>&raquo;</a></li>"
	}
	else{
		sHtml=sHtml+"<li class='arrow'><a id='available_next_arrow' onClick='goToNextPages()'>&raquo;</a></li>"
	}
	
	$(pagination_id).append(sHtml);
}

function goToPage(pageNum){
	var fromIndex_x_howmany=pageNum*howMany;
	currentPage=pageNum;
	$("#db_filelist").html("");
	$("#db_filelist_pagination").html("");
	searchFileList(fromIndex_x_howmany,"dbSearch");
}

function goToNextPages(){
	var nextPage=currentPage+5;
	if(nextPage<totalPages){
		goToPage(nextPage);
	}
	else{
		goToPage(totalPages-1);
	}
}

function goToPrevPages(){
	var nextPage=currentPage-5;
	if(0<nextPage){
		goToPage(nextPage);
	}
	else{
		goToPage(0);
	}
}

function fileupload(){
	var filedata=new FormData();
	filedata.append("file", $("#fileupload")[0].files[0]);
	
	$("#excel_filelist").html("");
	$("#excel_filelist_pagination").html("");
	
	clearListPage("#db_filelist","#db_filelist_pagination");
	currentPage=0;
	
	$.ajax({
		url : "fileupload",
		type : "POST",
		data : filedata,
		dataType: 'json',
		cached: false,
		contentType : false,
		processData: false,
		async: true,
		beforeSend:function() {
		       var loading="<img name='loadingImage' id='loadingImg' src='../resources/img/loading_anim.gif'  width='100px' height='100px' />";
		       $("#db_filelist").html(loading);
		},
//		uploadProgress: function(event, position, total, percentComplete){
//			var percentVal=percentComplete+'%';
//			console.log(percentVal);
//		},
		success : function(data) {
//			console.log(data.totalCount);
// 				for(var i=0;i<data.ExcelPatternlist.length;i++){
// 					createHtmlTable(data.ExcelPatternlist[i],"#excel_filelist");
// 				}
		}, error : function (xhr, status, err) {
			location.reload();
			alert("xhr : " + xhr + ", err :" + err);
		}
	});
}

function clearListPage(list_id,pagination_id){
	$(list_id).html("");
	$(pagination_id).html("");
}

function insertPattern(){
	var org_insert=$("#org_insert").val();
	var trnst_insert=$("#trnst_insert").val();
	
	if(org_insert!="" && trnst_insert!=""){
		$.ajax({
			url : "dbInsert",
			type : "GET",
			data : {
				"ORG_PTTRN": org_insert,
				"TRNST_PTTRN": trnst_insert
			},
			dataType : "json",
			success : function(data) {
				
				if(data.result=="입력 완료"){
					location.reload();
//					console.log(data.result);
				}
				else{
					if(data.result=="원문패턴이 이미 존재하는 패턴입니다."){
//						console.log(data.result);
//						console.log(data.TRNST_PTTRN_in_DB);
						
						var sHtml="<div class='panel'>"+
								  data.TRNST_PTTRN_in_DB+
								  "</div>"+
								  "를 "+
								  "<div class='panel callout radius'>"+
								  trnst_insert+
								  "</div>"+
								  "로 변경하시겠습니까?";
						var seq=data.SEQ;
						createModal("원문패턴이 이미 존재합니다",sHtml,function (){
							var seq=data.SEQ;
							updateDB(seq,org_insert,trnst_insert);
						});
					}
					else if(data.result=="이미 존재하는 패턴쌍입니다."){
//						console.log(data.result);
					}
				}
			}, error : function (xhr, status, err) {
				alert("xhr : " + xhr + ", err :" + err);
			}
		});
	}
	else {
		$('#text_warning').html("<p>패턴쌍을 입력해주십시오.</p>");
		$('#text_warning p').delay(5000).fadeOut('slow');
	}
}

function updateDB(SEQ,ORG_PTTRN,TRNST_PTTRN){
//	console.log(SEQ);
//	console.log(ORG_PTTRN);
//	console.log(TRNST_PTTRN);
	
	$.ajax({
		url : "dbEdit",
		type : "GET",
		data : {
			"SEQ": SEQ,
			"ORG_PTTRN": ORG_PTTRN,
			"TRNST_PTTRN": TRNST_PTTRN
		},
		dataType : "json",
		async: true,
		success : function(data) {
			if(data.result=="이미 존재하는 패턴입니다."){
//				console.log(data.result);
			}else if(data.result=="입력 완료"){
				closeModal();
//				console.log(data.result);
			}
		}, error : function (xhr, status, err) {
			alert("xhr : " + xhr + ", err :" + err);
		}
	});
}

function downloadExcel_trigger(){
	$.ajax({
		url : "dbExport",
		type : "GET",
		dataType : "json",
		async: true,
		success : function(data) {
			for(var i=0;i<data.TB_PATTERN_DATA_LIST.length;i++){
				createHtmlTable_export(data.TB_PATTERN_DATA_LIST[i], "#db_filelist_export");
			}
			downloadExcel("div_for_table_export", "전체_패턴_데이터");
			location.reload();
		}, error : function (xhr, status, err) {
			alert("xhr : " + xhr + ", err :" + err);
		}
	});
}

function showFileDownloadList(){
	var sHtml="<a onclick='downloadExcel_trigger()'>전체 DB패턴 다운로드</a><BR><hr/>";
	$.ajax({
		url : "downloadlist",
		type : "GET",
		dataType : "json",
		async: false,
		success : function(data) {
			if(data.downloadlist!=null){
				for(var i=0;i<data.downloadlist.length;i++){
					var bgColor;
					if(i%2==0){
						bgColor="#ffffff";
					}
					else{
						bgColor="#eeeeee";
					}
//					console.log(data.downloadlist[i]);
					var filename=data.downloadlist[i];
					sHtml=sHtml+"<div class='row' style='background-color:"+bgColor+"'>" +
					"<object type='image/svg+xml' id='downloadicon' data='../resources/foundation-icons/svgs/fi-page-csv.svg'></object><a href='files/"+data.downloadlist[i]+"'>"+data.downloadlist[i]+"</a><BR>"+
					"</div>";
				}
			}
			createModal("파일 다운로드",sHtml);

		}, error : function (xhr, status, err) {
			alert("xhr : " + xhr + ", err :" + err);
		}
	});
	

}

function showTotalCount(totalCount){
	$("#ShowtotalCount small").text('총 '+totalCount+'개의 패턴');
}

function searchDBPattern(){
	var chosen_column=$("#choose_kind_pattern").val()
	var text_to_search=$("#text_to_search").val();
	
	if(text_to_search==""){
		clearListPage("#db_filelist","#db_filelist_pagination");
		currentPage=0;
		showFileList(0,howMany);
	}
	else{
		clearListPage("#db_filelist","#db_filelist_pagination");
		currentPage=0;
		searchFileList(0,"dbSearch");
	}
}

function addSelectEvent(){
	$(function () {
		$(".seq_num").unbind();
		
		var isMouseDown = false,
			isHighlighted;
			$(".seq_num").mousedown(function (event) {
				if(event.which==1){
					isMouseDown = true;
					$(this).closest("tr").toggleClass("highlighted");
					isHighlighted = $(this).closest("tr").hasClass("highlighted");
					return false; // prevent text selection
				}
				else if(event.which==3){
					SelectAll();
					return false; // prevent text selection
				}
			}).mouseover(function () {
				if (isMouseDown) {
					$(this).closest("tr").toggleClass("highlighted", isHighlighted);
				}
			}).bind("selectstart", function () {
				return false;
			})
			
			$('#db_pattern').on('contextmenu', '.seq_num', function(e){ return false; });
	
			$(document).mouseup(function () {
				isMouseDown = false;
			});
	});
}

function rowDelete_confirm(){
	var selected_row=$(".highlighted");
	if(selected_row.length==0){
//		console.log('선택된row없음');
	}
	else{
		createModal("","선택된  PATTERN을 삭제하시겠습니까?",rowDelete);
	}
}

function rowDelete(){
	var selected_row=$(".highlighted");
	var seq_list=new Array();
	for(var i=0;i<selected_row.length;i++){
		var seq_id=$(selected_row[i]).attr("id");
		var seq=seq_id.substring(seq_id.indexOf('_')+1);
		seq_list.push(seq);
	}
	
	delete_confirm(seq_list);
	
}

function SelectAll(){
	var selected_row=$(".highlighted");
	if(selected_row.length>0){
		for(var i=0;i<selected_row.length;i++){
			$(selected_row[i]).toggleClass("highlighted");
		}
	}
	else{
		var all_row=$(".seq_num");
		for(var i=0;i<all_row.length;i++){
			$(all_row[i]).closest("tr").toggleClass("highlighted");
		}
	}
}

function DELETEALL(){
	createModal("","DB안에 있는 PATTERN 전부 삭제하시겠습니까?",DBClear);
}

function createModal(headerMessage,content,functionName){
	var sHtml_button;
	if(functionName=="notice"){
		sHtml_button="<div id='confirm' class='small-12 columns'><a class='button tiny' onClick='closeModal()'>확인</a></div>";
	}
	else if(functionName!=null){
		sHtml_button="<div class='row'>"+
					 "<div id='yes' class='large-1 columns'><a class='button tiny' onClick='functionExecute()'>예</a></div>"+
			 		 "<div id='no' class='small-3 columns'><a class='button tiny' onClick='closeModal()'>아니오</a></div>"+
			 		 "</div>";
	}
	else{
		sHtml_button="";
	}
	functionToExecuse=functionName;
	var sHtml="<h2 id='modalTitle'>"+headerMessage+"</h2>"+
			  "<p class='lead'>"+content+"</p>"+
			  sHtml_button+
			  "<a class='close-reveal-modal' aria-label='Close'>&#215;</a>";
	$("#myModal").append(sHtml);
	
	$("#modalTrigger").trigger('click');
	
	$(document).on('close.fndtn.reveal', '[data-reveal]', function () {
		$("#myModal").html("");
	});
	
}

function functionExecute(){
	functionToExecuse();
}

function closeModal(){
	$('a.close-reveal-modal').trigger('click');
}


function DBClear(){
	$.ajax({
		url : "dbTruncate",
		type : "GET",
		dataType : "json",
		async: true,
		success : function(data) {
//			console.log(data.result);
			closeModal();
			location.reload();
		}, error : function (xhr, status, err) {
			alert("xhr : " + xhr + ", err :" + err);
		}
	});
}

function selectFile(){
	$("#fileupload_modalTrigger").trigger("click");
//	$("#fileupload").click();
//	var sHtml="<div id='dragandrophandler'>Drag & Drop Files Here</div>";
//	createModal("파일 업로드",sHtml);
//	$(document).on('dragenter', function (e) 
//			{
//			    e.stopPropagation();
//			    e.preventDefault();
//			});
//			$(document).on('dragover', function (e) 
//			{
//			  e.stopPropagation();
//			  e.preventDefault();
//			  obj.css('border', '2px dotted #0B85A1');
//			});
//			$(document).on('drop', function (e) 
//			{
//			    e.stopPropagation();
//			    e.preventDefault();
//			});
}