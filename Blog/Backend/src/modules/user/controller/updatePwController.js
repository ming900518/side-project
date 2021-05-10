app.factory('updatePwService', function(baseHttp) {
	return {
		updatePw: function(params,callback){
            return baseHttp.service('api_backend/updatePw',params,callback);
        }
    }
})
.controller('updatePwController', function ($stateParams,$rootScope, $scope, $window ,$location, $http, $filter, $state, $modal, updatePwService, util, $sce) {
    $scope.adminInfo = {
    	"userPw": "",
    	"newPw": "",
    	"againNewPw": "",
    	"userCode": $rootScope.rootUser.userCode
    }

	$scope.updateAdmin = function(valid){
    	var newPwStr =$scope.adminInfo.newPw;

        if(valid){
        	if(newPwStr.length < 8)
        	{
        		util.alert("Password should be longer than 8 characters.");
        		return false;
        	}	
        	if(newPwStr.length > 20)
        	{
        		util.alert("Password should be shorter than 20 characters.");
        		return false;
        	}	
        	if(newPwStr.indexOf(' ') >0)
        	{
        		util.alert("Password should not be blank.");
        		return false;
        	}	
        
        	
        	//var pattern = "([A-Za-z]|[0-9]|-|_){8,20}";
        	//var reg = new RegExp(pattern,"g");
        	var reg = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+`\-={}\[\]:";'<>?,.\/]).{4,16}$/;
        	var pw_valid = reg.test(newPwStr);
        	if(pw_valid == false){
        		util.alert("Password should include alphabets and numbers.");
	        	return false;
        	}
        	
        	
            if($scope.adminInfo.newPw == $scope.adminInfo.againNewPw){
	            	updatePwService.updatePw($scope.adminInfo,function(data, status, headers, config){
		            	if(data.result){
		                    util.alert('Edit successfully.');
		                    $state.go('updatePw', {}, { reload: true });
		                }else{
		                	util.alert(data.message);
		                }
		            })
            }else{
            	util.alert('Different password, check again.');
            }
        }else{
            util.alert('Required fields are not filled in completely.');
        }
    }
    
})
