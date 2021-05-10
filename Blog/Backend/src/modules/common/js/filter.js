app.filter('adminTypeName', function() {
	return function(number) {
	    if(number ===0){
	    	return "系統維護" ;
	    }else if(number ===1){
	    	return "平台管理員" ;
	    }else if(number ===2){
	    	return "平台維護員" ;
	    }else if(number ===3){
	    	return "平台使用者" ;
		}else{
	    	return "" ;
	    }
	}
});

app.filter('boolean', function() {
	return function(number) {
		if(number === 0) {
			return "否" ;
		} else if(number === 1) {
			return "是" ;
		}else{
			return "" ;        
		}
	}
});

app.filter('operatingLogTypeName', function() {
	return function(number) {
		if(number === 1) {
			return "新增" ;
		} else if(number === 2) {
			return "刪除" ;
		} else if(number === 3) {
			return "修改" ;
		}else{
			return "" ;        
		}
	}
});

app.filter('dateFormat', function() {
	return function(str) {
		if(str!==null & str!==""){
			var day = new Date(str);
        	var date =  day.getFullYear()+ "/" + ('0'+ (parseInt(day.getMonth())+1).toString()).substr(-2) + "/" + ('0'+ day.getDate()).substr(-2);
        	return date ;        
		}else{
			return "" ;        
		}
	}
});
