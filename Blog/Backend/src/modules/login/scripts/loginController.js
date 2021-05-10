app.controller('loginController', function ($scope, $rootScope, $location, $cookieStore, $window, $state, $http, AuthenticationService, util, $modal) {
    $scope.dataLoading = false;   
    $scope.loginUser = {
    		userCode:null, password:null, codeStr:null
    	}
        $scope.isCheck = false;

        var loginer = $cookieStore.get('myLoginer');
        console.log(loginer);
        if(loginer){
            $scope.loginUser.userCode = loginer.usercode;
            $scope.loginUser.password = loginer.password;
            $scope.isCheck = loginer.isCheck;
        }

    	AuthenticationService.ClearCredentials();

        $scope.createCaptcha = function (valid) {
            var code ='';
            var codeLength = 4;
            var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
            var codeArray = new Array();

            for (var i = 0; i < codeLength; i++){
                codeArray[i]= codeChars[Math.floor(Math.random() * codeChars.length)];
                code +=codeArray[i];
            }
            $scope.loginUser.codeStr = code;

            var c = document.getElementById("CapCode"),
                ctx=c.getContext("2d"),
                ctx_w = c.width,
                ctx_h = c.height,
                x = ctx_w / 2,
                y = ctx_h / 2,
                img = new Image();

            img.src = "assets/images/_temp/getImgCode-1.jpg";

            img.onload = function () {
                ctx.drawImage(img, 0, 0, ctx_w, ctx_h);
                ctx.font="18px Roboto Slab";
                ctx.fillStyle = '#000';
                ctx.textAlign = 'center';
                ctx.setTransform (1, -0.12, 0, 1, 0, 15);
                ctx.fillText(code, x, y);
            };
        }

        $scope.createCaptcha();

        $scope.isMemory = function(){

            if($scope.isCheck){
                var loginer = {
                    usercode:$scope.loginUser.userCode,
                    password:$scope.loginUser.password,
                    isCheck:$scope.isCheck
                }
                $cookieStore.put('myLoginer',loginer);
            }else{
                $cookieStore.remove('myLoginer');
            }
        }

     	$scope.login_error = false ;
        $scope.login = function (valid) {
        	if(valid){
                $scope.dataLoading = true;
                if($scope.loginUser.vCodeId==$scope.loginUser.codeStr){
    	            AuthenticationService.Login($scope.loginUser, function(response,request) {
    	            	if(response.result){
    	            		$rootScope.sessionId  = response.securityKey;
                            AuthenticationService.SetCredentials(response.data.adminInfo); 
                            if (response.data.adminInfo.adminType > 2) {
                                $window.location.href = 'index.html#/coupon';
                            } else {
                                $window.location.href = 'index.html#/home';
                            }
                           
    	                    
    	            	}else{
    	            		$scope.error_msg = response.message;
    	            		util.alert(response.message);
    	            		$scope.dataLoading = false;
    	            		$scope.login_error = true ;
    	                }
    	            })
                }else{
                    util.alert("驗證碼錯誤");
                    $scope.createCaptcha();
                    $scope.loginUser.vCodeId="";
                    $scope.dataLoading = false;
                    $scope.login_error = true ;
                }
        	}else{
                util.alert("請輸入帳號及密碼！");
        	}
        }
    }
)