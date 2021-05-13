app.factory('contentEditService', function(baseHttp) {
	return {
		queryContent: function(params, callback) {
			return baseHttp.service('api_backend/queryContent', params, callback);
		},
		saveContent: function(params, callback) {
			return baseHttp.service('api_backend/saveContent', params, callback);
		},
	}
})
app.controller('contentEditController', function($stateParams, $rootScope, $scope, $window, $location, $http, $filter, $state, $modal, contentEditService, util, $sce) {

	if ($rootScope.rootUser.adminType == 2) {
		$("#contentForm :input").prop("disabled", true);
	}

	$scope.content = {};
	$scope.contentId = $stateParams.contentId;
	console.log($stateParams.adminId)
	$scope.adminId = $stateParams.adminId;
	$scope.queryContent = function(param) {
		contentEditService.queryContent(param, function(data, status, headers, config) {
			if (data.result) {
				$scope.content = data.data;
				CKEDITOR.replace( 'content' );
			}
		})
	};

	if ($scope.contentId != null) {
		var param = {
			"contentId": $scope.contentId
		};
		$scope.queryContent(param);
	} else {
		CKEDITOR.replace('content');
	}

	$scope.saveContent = function(valid) {
		$scope.content.content = CKEDITOR.instances.content.getData();
		util.confirm('Save content?', function(r) {
			if (r) {
				$scope.content.adminId = $scope.adminId
				console.log($scope.content)
				contentEditService.saveContent($scope.content, function(data, status, headers, config) {
					if (data.result) {
						util.alert(data.message);
						$state.go('content');
					} else {
						util.alert(data.message);
					}

				})
			}
		});
	};

	$scope.cancel = function() {
		$state.go('content');
	}


})