var ws=null;
var stompClient = null;
var flag=null;
var onProcess=null;


function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}

function connect() {
//         	ws =  new SockJS('/socketjs/echo');

// 			ws.onopen = function () {
// 			    setConnected(true);
// 			    console.log('Info: connection opened.');
// 			};
// 			ws.onmessage = function (event) {
// 				console.log('Received: ' + event.data);
// 			};
// 			ws.onclose = function (event) {
// 			    setConnected(false);
// 			    console.log('Info: connection closed.');
// 			    console.log(event);
// 			};
	
	
    var socket = new SockJS('/hello');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
//        setConnected(true);
        console.log('Connected: ' + frame);
        
        onmessage = function(message) {
//        	console.log("onmessage");
        	var jsonObject=JSON.parse(message.body);
        	var keys=Object.keys(jsonObject);
        	if(keys.indexOf("fileuploadDone")>=0 && flag==true){
        		var sHtml="<i class='fi-upload'></i>";
				$("#uploadicon").html(sHtml);
        		flag=false;
        		location.reload();
        	}
        	if(keys.indexOf("ProcessDone")>=0){
        		console.log(jsonObject.ProcessDone);
        		location.reload();
        	}
        	for(var i=0;i<keys.length;i++){
        		if(keys[i]=="content"){
//        			showGreeting(JSON.parse(message.body).content);
        		}
        		else if(keys[i]=="filename"){
        			if(flag!=true){
        				var sHtml="<object type='image/svg+xml' data='../resources/foundation-icons/svgs/fi-loading-rotate.svg'></object>";
        				$("#uploadicon").html(sHtml);
        				$("#uploadicon").prop("onclick",null).off("click");
        				$("#uploadicon").on("click",function(){showProgress()});

        				flag=true;
        			}
        		}
        		else if(keys[i]=="InsertIndex"){
        			var index=jsonObject.InsertIndex;
        			var totalCount=jsonObject.totalCount;
        			var per=(index/totalCount)*100;
        			$("#processInsert_div #progressMeter").width(per+"%");
        			$("#processInsert_div #InsertFileName").text(jsonObject.filename);
        		}
        		else if(keys[i]=="TransIndex"){
        			var index=jsonObject.TransIndex;
        			var totalCount=jsonObject.totalCount;
        			var per=(index/totalCount)*100;
        			$("#processTrans_div #progressMeter").width(per+"%");
        			$("#processTrans_div #TransFileName").text(jsonObject.filename);
        		}
        	}
            
          }
        
        stompClient.subscribe('/topic/greetings',onmessage);
    });
}

function disconnect() {
//         	if (ws != null) {
//                 ws.close();
//                 ws = null;
//             }
//             setConnected(false);
    
    
    if (stompClient != null) {
        stompClient.disconnect();
    }
//    setConnected(false);
//    console.log("Disconnected");
}

function sendName() {
//         	var name = document.getElementById('name').value;
//         	ws.send(JSON.stringify({ 'name': name }));
	
    var name = document.getElementById('name').value;
    stompClient.send("/app/hello", {}, JSON.stringify({ 'name': name }));
}

function showGreeting(message) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(message));
    response.appendChild(p);
}

function showProgress(index,totalCount){
	var sHtml="";
	if(flag==false || flag==null){
		sHtml="진행중인 작업 없음";
	}
	else if(flag==true){
		sHtml="<fieldset>"+
		"<legend>파일 인서트 경과</legend>"+
		"<div id='processInsert_div'>" +
		"<div class='progress [small-# large-#] [secondary alert success] [radius round]'>"+
		"<span id='progressMeter' class='meter' style='width: 0%'></span>"+
		"</div>"+
		"<p id='InsertFileName'></p>"+
		"</div>"+
		"</fieldset>"+
		"<fieldset>"+
		"<legend>원문 패턴 번역 경과</legend>"+
		"<div id='processTrans_div'>" +
		"<div class='progress [small-# large-#] [secondary alert success] [radius round]'>"+
		"<span id='progressMeter' class='meter' style='width: 0%'></span>"+
		"</div>"+
		"<p id='TransFileName'></p>"+
		"</div>"+
		"</fieldset>";
	}
	createModal("파일 작업 경과",sHtml);
}

