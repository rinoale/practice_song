function sendFileToServer(formData,status)
{
    var uploadURL ="fileupload"; //Upload URL
    var extraData ={}; //Extra Data.
    var jqXHR=$.ajax({
            xhr: function() {
            var xhrobj = $.ajaxSettings.xhr();
            if (xhrobj.upload) {
                    xhrobj.upload.addEventListener('progress', function(event) {
                        var percent = 0;
                        var position = event.loaded || event.position;
                        var total = event.total;
                        if (event.lengthComputable) {
                            percent = Math.ceil(position / total * 100);
                        }
                        //Set progress
                        status.setProgress(percent);
                    }, false);
                }
            return xhrobj;
        },
    url: uploadURL,
    type: "POST",
    contentType:false,
    processData: false,
        cache: false,
        data: formData,
        success: function(data){
            status.setProgress(100);
            status.process_insert;
            status.process_trans;
            status.process_delete;
            status.setProcess_insert();
            status.setProcess_trans();
            status.setProcess_delete();
            
        }
    }); 
 
    status.setAbort(jqXHR);
}
 
var rowCount=0;
function createStatusbar(obj)
{
     rowCount++;
     var row="odd";
     if(rowCount %2 ==0) row ="even";
     this.statusbar = $("<div class='statusbar "+row+"'></div>");
     this.filename = $("<div id='uploadedfile' class='filename'></div>").appendTo(this.statusbar);
     this.size = $("<div class='filesize'></div>").appendTo(this.statusbar);
     this.progressBar = $("<div class='progressBar'><div></div></div>").appendTo(this.statusbar);
     this.abort = $("<div class='abort'>Abort</div>").appendTo(this.statusbar);
     this.process_insert = $("<BR/><div class='process' id='_insert'>입력</div>").appendTo(this.statusbar);
     this.process_trans = $("<div class='process' id='_trans'>번역</div>").appendTo(this.statusbar);
     this.process_delete = $("<div class='process' id='_delete'>삭제</div>").appendTo(this.statusbar);
     obj.after(this.statusbar);
 
    this.setFileNameSize = function(name,size)
    {
        var sizeStr="";
        var sizeKB = size/1024;
        if(parseInt(sizeKB) > 1024)
        {
            var sizeMB = sizeKB/1024;
            sizeStr = sizeMB.toFixed(2)+" MB";
        }
        else
        {
            sizeStr = sizeKB.toFixed(2)+" KB";
        }
 
        this.filename.html(name);
        this.size.html(sizeStr);
    }
    this.setProgress = function(progress)
    {       
        var progressBarWidth =progress*this.progressBar.width()/ 100;  
        this.progressBar.find('div').animate({ width: progressBarWidth }, 10).html(progress + "% ");
        if(parseInt(progress) >= 100)
        {
            this.abort.hide();
        }
    }
    this.setAbort = function(jqxhr)
    {
        var sb = this.statusbar;
        this.abort.click(function()
        {
            jqxhr.abort();
            sb.hide();
        });
    }
    
    this.setProcess_insert = function()
    {
    	this.process_insert.click(function(){
    		var filename=$(this).closest(".statusbar").find("#uploadedfile").text();
    		processRequest(filename,"insert");
    	});

    }
    this.setProcess_trans = function()
    {
    	this.process_trans.click(function(){
    		var filename=$(this).closest(".statusbar").find("#uploadedfile").text();
    		processRequest(filename,"trans");
    	});
    }
    this.setProcess_delete = function()
    {
    	var sb = this.statusbar;
    	this.process_delete.click(function(){
    		var filename=$(this).closest(".statusbar").find("#uploadedfile").text();
    		processRequest(filename,"delete");
    		sb.hide();
    	});
    }
}
function handleFileUpload(files,obj)
{
   for (var i = 0; i < files.length; i++) 
   {
        var fd = new FormData();
        fd.append('file', files[i]);
 
        var status = new createStatusbar(obj); //Using this we can set progress.
        status.setFileNameSize(files[i].name,files[i].size);
        sendFileToServer(fd,status);
 
   }
}

function processRequest(filename,process){
	$.ajax({
		url : "fileprocess",
		type : "GET",
		data: {
			"filename": filename,
			"process": process
		},
		dataType : "json",
		async: true,
		success : function(data) {
//			console.log(data.result);

		}, error : function (xhr, status, err) {
			alert("xhr : " + xhr + ", err :" + err);
		}
	});	
}

$(document).ready(function()
{
	var obj=$("#dragandrophandler");
	obj.on('dragenter', function (e) 
	{
	    e.stopPropagation();
	    e.preventDefault();
	    $(this).css('border', '2px solid #0B85A1');
	});
	
	obj.on('dragover', function (e) 
	{
	     e.stopPropagation();
	     e.preventDefault();
	});

	obj.on('drop', function (e) 
	{
	 
	     $(this).css('border', '2px dotted #0B85A1');
	     e.preventDefault();
	     var files = e.originalEvent.dataTransfer.files;
	 
	     //We need to send dropped files to Server
	     handleFileUpload(files,obj);
	});
	
	$(document).on('dragenter', function (e) 
	{
	    e.stopPropagation();
	    e.preventDefault();
	});
	$(document).on('dragover', function (e) 
	{
	  e.stopPropagation();
	  e.preventDefault();
	  obj.css('border', '2px dotted #0B85A1');
	});
	$(document).on('drop', function (e) 
	{
	    e.stopPropagation();
	    e.preventDefault();
	});
 
});
