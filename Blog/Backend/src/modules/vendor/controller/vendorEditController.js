app.factory('vendorEditService', function(baseHttp) {
	return {
		queryVendor: function(params, callback) {
			return baseHttp.service('api_backend/queryVendor', params, callback);
		},
		saveVendor: function(params, callback) {
			return baseHttp.service('api_backend/saveVendor', params, callback);
		},
		queryVendorList: function(params, callback) {
			return baseHttp.service('api_backend/queryVendorList', params, callback);
		},
	}
})
app.controller('vendorEditController', function($stateParams, $rootScope, $scope, $window, $location, $http, $filter, $state, $modal, vendorEditService, util, $sce) {

	if ($rootScope.rootUser.adminType == 2) {
		$("#contentForm :input").prop("disabled", true);
	}

	$scope.vendor = {};
	$scope.vendorId = $stateParams.vendorId;
	$scope.queryVendor = function(param) {
		vendorEditService.queryVendor(param, function(data, status, headers, config) {
			if (data.result) {
				$scope.vendor = data.data;
			}
		})
	};

	if ($scope.vendorId != null) {
		var param = {
			"vendorId": $scope.vendorId
		};
		$scope.queryVendor(param);
	}

	$scope.saveVendor = function(valid) {
		util.confirm('確定要修改', function(r) {
			if (r) {

				vendorEditService.saveVendor($scope.vendor, function(data, status, headers, config) {
					if (data.result) {
						util.alert(data.message);
						$state.go('vendor');
					} else {
						util.alert(data.message);
					}

				})
			}
		});
	};

	$scope.cancel = function() {
		$state.go('vendor');
	}


})