app.filter('adminTypeName', function() {
	return function(number) {
	    if(number ===0){
	    	return "System Maintenance" ;
	    }else if(number ===1){
	    	return "Administrator" ;
	    }else if(number ===2){
	    	return "Maintainer" ;
	    }else if(number ===3){
	    	return "User" ;
		}else{
	    	return "" ;
	    }
	}
});

app.filter('boolean', function() {
	return function(number) {
		if(number === 0) {
			return "False" ;
		} else if(number === 1) {
			return "True" ;
		}else{
			return "" ;        
		}
	}
});

app.filter('operatingLogTypeName', function() {
	return function(number) {
		if(number === 1) {
			return "Add" ;
		} else if(number === 2) {
			return "Delete" ;
		} else if(number === 3) {
			return "Edit" ;
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
