function calculateEfficiency(){
	var characterAD=$("#attackDamage").val();
	var characterCC=$("#criticalChance").val();
	var characterCD=1.5;
	
	
	if($("#value1").val().length>0){
		var option=$("#option1").val();
		if(option=="penet"){
			
		}
		else if(option=="crit"){
			characterCD=Number(characterCD)+Number($("#value1").val());
		}
		else if(option=="ad"){
			characterAD=Number(characterAD)*Number($("#value1").val());
		}
		else if(option=="cc"){
			characterCC=Number(characterCC)+Number($("#value1").val());
		}
	}
	if($("#value2").val().length>0){
		var option=$("#option1").val();
		if(option=="penet"){
			
		}
		else if(option=="crit"){
			characterCD=Number(characterCD)+Number($("#value1").val());
		}
		else if(option=="ad"){
			characterAD=Number(characterAD)*Number($("#value1").val());
		}
		else if(option=="cc"){
			characterCC=Number(characterCC)+Number($("#value1").val());
		}
	}
	if($("#value3").val().length>0){
		var option=$("#option3").val();
		if(option=="penet"){
			
		}
		else if(option=="crit"){
			characterCD=Number(characterCD)+Number($("#value3").val());
		}
		else if(option=="ad"){
			characterAD=Number(characterAD)*Number($("#value3").val());
		}
		else if(option=="cc"){
			characterCC=Number(characterCC)+Number($("#value3").val());
		}
	}
	
	console.log("기본뎀 : "+characterAD*(100-characterCC)+", 크리뎀 : "+characterAD*characterCD*characterCC);
	$("#result").val(Number(characterAD*(100-characterCC))+Number(characterAD*characterCD*characterCC));
}