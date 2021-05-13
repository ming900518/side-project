app.factory('contentService', function(baseHttp) {
	return {
		queryContentList: function(params, callback) {
			return baseHttp.service('api_backend/queryContentList', params, callback);
		},
		deleteContent: function(params, callback) {
			return baseHttp.service('api_backend/deleteContent', params, callback);
		},
	}
})
app.controller('contentController', function($timeout, $rootScope, $scope, $window, $location, $http, $filter, $state, $modal, contentService, util, $sce) {


	$scope.contentList = [];
	$scope.queryCondition = { "title": '' };


	var columns_table = [
		{ title: "Title", data: "title", sWidth: "10%" },
		{ title: "Category", sWidth:"10%" ,data: null,render: function(data, type, full, meta) {
				var btnStr = data.category1;
				if (data.category2) {
					btnStr = btnStr + ", " + data.category2
				}
				if (data.category3) {
					btnStr = btnStr + ", " + data.category3
				}
				return btnStr
			}
		},
		{ title: "Author", data: "userName", sWidth: "10%" },
		{
			title: "Publish Date", sWidth: "10%", data: null, render: function (data, type, full, meta) {
				var btnStr = $filter('date')(data.publishDate, 'yyyy/MM/dd HH:mm:ss');
				return btnStr;
			}
		},
		{
			title: "Edit Date", sWidth: "10%", data: null, render: function (data, type, full, meta) {
				var btnStr = $filter('date')(data.editDate, 'yyyy/MM/dd HH:mm:ss');
				return btnStr;
			}
		},
	];
	var js = {
		title: "Action", data: null, sWidth: "10%", "bSortable": false, render: function(data, type, full, meta) {
			var btnStr = '<button name="edit_content" class="btn btn-sm btn-edit margin_top" value="' + meta.row + '"><i class="ace-icon fa fa-pencil-square-o"></i>Edit</button>';
			btnStr += '<button name="delete_content" class="btn btn-sm btn-dele margin_top" value="' + meta.row + '"><i class="ace-icon fa fa-trash-o"></i>Delete</button>';
			return btnStr;
		}
	};

	columns_table.push(js);


	var opt_table = {
		"bJQueryUI": true,
		select: false,
		"sPaginationType": 'full_numbers',
		data: $scope.contentList,
		"scrollX": "100%",
		columns: columns_table,
	};


	var contentTable = $('#content_table').DataTable(opt_table);
	$scope.queryContentList = function(param) {
		contentService.queryContentList(param, function(data, status, headers, config) {
			if (data.result) {
				$scope.contentList = data.data;
				util.refreshDataTable(contentTable, $scope.contentList);

			}
		})
	};

	$scope.queryContentList($scope.queryCondition);

	$scope.searchContent = function() {
		$scope.queryContentList($scope.queryCondition);
	};

	$scope.addContent = function() {
		$state.go('contentEdit', { "adminId": $rootScope.rootUser.adminId });
	};


	$('#content_table tbody').on('click', 'button[name="edit_content"]', function() {
		var index = $(this).val();
		var content = $scope.contentList[index];
		$state.go('contentEdit', { "contentId": content.contentId, "adminId": $rootScope.rootUser.adminId });
	});

	$('#content_table tbody').on('click', 'button[name="delete_content"]', function() {
		var index = $(this).val();
		var content = $scope.contentList[index];
		$scope.deleteContent({ "contentId": content.contentId });
	});

	$scope.deleteContent = function(params) {
		contentService.deleteContent(params, function(data, status, headers, config) {
			if (data.result) {
				util.alert("Delete successfully.");
				$scope.queryContentList($scope.queryCondition);
			} else {
				util.alert("Delete failed.");
			}
		})
	}

});