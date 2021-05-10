app.factory('vendorService', function(baseHttp) {
	return {
		queryVendorList: function(params, callback) {
			return baseHttp.service('api_backend/queryVendorList', params, callback);
		},
		deleteVendor: function(params, callback) {
			return baseHttp.service('api_backend/deleteVendor', params, callback);
		},
	}
})
app.controller('vendorController', function($timeout, $rootScope, $scope, $window, $location, $http, $filter, $state, $modal, vendorService, util, $sce) {


	$scope.vendorList = [];
	$scope.queryCondition = { "taxId": '' };


	var columns_table = [
		{ title: "商家統編", data: "taxId", sWidth: "10%" },
		{ title: "商家名稱", data: "vendorName", sWidth: "10%" },
		{ title: "負責人", data: "ownerName", sWidth: "10%" },

	];
	var js = {
		title: "操作", data: null, sWidth: "10%", "bSortable": false, render: function(data, type, full, meta) {
			var btnStr = '<button name="edit_vendor" class="btn btn-sm btn-edit margin_top" value="' + meta.row + '"><i class="ace-icon fa fa-pencil-square-o"></i>編輯</button>';
			btnStr += '<button name="delete_vendor" class="btn btn-sm btn-dele margin_top" value="' + meta.row + '"><i class="ace-icon fa fa-trash-o"></i>刪除</button>';
			return btnStr;
		}
	};

	columns_table.push(js);


	var opt_table = {
		"oLanguage": { "sUrl": "dataTables.zh-tw.txt" },
		"bJQueryUI": true,
		select: false,
		"sPaginationType": 'full_numbers',
		data: $scope.vendorList,
		"scrollX": "100%",
		columns: columns_table,
	};


	var vendorTable = $('#vendor_table').DataTable(opt_table);
	$scope.queryVendorList = function(param) {
		vendorService.queryVendorList(param, function(data, status, headers, config) {
			if (data.result) {
				$scope.vendorList = data.data;
				util.refreshDataTable(vendorTable, $scope.vendorList);

			}
		})
	};
	$scope.queryVendorList($scope.queryCondition);

	$scope.searchVendor = function() {
		$scope.queryVendorList($scope.queryCondition);
	};

	$scope.addVendor = function() {
		$state.go('vendorEdit');
	};


	$('#vendor_table tbody').on('click', 'button[name="edit_vendor"]', function() {
		var index = $(this).val();
		var vendor = $scope.vendorList[index];
		$state.go('vendorEdit', { "vendorId": vendor.vendorId });
	});

	$('#vendor_table tbody').on('click', 'button[name="delete_vendor"]', function() {
		var index = $(this).val();
		var vendor = $scope.vendorList[index];
		$scope.deleteVendor({ "vendorId": vendor.vendorId });
	});

	$scope.deleteVendor = function(params) {
		vendorService.deleteVendor(params, function(data, status, headers, config) {
			if (data.result) {
				util.alert("刪除成功");
				$scope.queryVendorList($scope.queryCondition);
			} else {
				util.alert("刪除失敗");
			}
		})
	}

});