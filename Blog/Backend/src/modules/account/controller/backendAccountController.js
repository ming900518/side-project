app.factory('backendAccountService', function (baseHttp) {
    return {
        adminList: function (params, callback) {
            return baseHttp.service('api_backend/adminList', params, callback);
        },
        addAdmin: function (params, callback) {
            return baseHttp.service('api_backend/addAdmin', params, callback);
        },
        deleteAdmin: function (params, callback) {
            return baseHttp.service('api_backend/deleteAdmin', params, callback);
        },
        editAdmin: function (params, callback) {
            return baseHttp.service('api_backend/editAdmin', params, callback);
        }
    }
})
app.controller('backendAccountController', function ($rootScope, $scope, $window, $location, $http, $filter, $state, $modal, backendAccountService, util, $sce, $compile) {

    if ($rootScope.rootUser.adminType == 2 ) {
        $("#myForm :input").prop("disabled", true);
    }

    var initAdminInfo = {
        userCode: null,
        userName: null,
        adminType: 0,
        department: null,
        isAccount: null,
        isComment: null,
        isHome: null,
        isVideo: null,
        isTest: null,
        isSentence: null,
        isChart: null,
        createAdminId: $rootScope.rootUser.adminId
    }
    $scope.adminInfoDataInitial = function () {
        initAdminInfo = {
            userCode: null,
            userName: null,
            adminType: 0,
            userPw: '',
            department: null,
            status : 0,
            note : '' ,
            createdBy: $rootScope.rootUser.adminId
        }        
        return initAdminInfo;
    }

    var opt = {
        "bJQueryUI": true,
        "sPaginationType": 'full_numbers',
        data: $scope.backendAccountList,
        columns: [{
                title: "Account",
                data: "userCode",
                sWidth: "15%"
            },{
                title: "Name",
                data: "userName",
                sWidth: "15%"
            },{
                title: "Rule",
                data: null,
                sWidth: "15%",
                render: function (data, type, full, meta) {
                    var btnStr = $filter('adminTypeName')(data.adminType);
                    return btnStr;
                }
            },
            
            {
                data: null,
                "bSortable": false,
                sWidth: "30%",
                render: function (data, type, full, meta) {
                    var row = meta.row;
                    var btnStr =
                        '<button name="edit" class="btn btn-sm btn-edit" value="' + row + '"><i class="ace-icon fa fa-pencil-square-o"></i>Edit</button>' +
                        '&nbsp;';
                    if ($rootScope.rootUser.adminType < 2 ) {
                        btnStr += '<button name="delete" class="btn btn-sm btn-dele "  value="' + row + '"><i class="ace-icon fa fa-trash-o"></i>Remove</button> ' +
                            '&nbsp;';
                    }

                    btnStr += '</div>';
                    return btnStr;
                }
            }
        ]
    }

    var myTable = $('#dynamic-table').DataTable(opt);
    $('#dynamic-table tbody').on('click', 'button[name="edit"]', function () {
        var index = $(this).val();
        $scope.edit($scope.backendAccountList[index]);
    });

    $('#dynamic-table tbody').on('click', 'button[name="delete"]', function () {
        var index = $(this).val();
        $scope.deleteAccount($scope.backendAccountList[index]);

    });

    //後台帳號列表
    $scope.backendAccount = function () {
        backendAccountService.adminList($scope.search, function (data, status, headers, config) {
            if (data.result) {
                $scope.backendAccountList = data.data;
                util.refreshDataTable(myTable, $scope.backendAccountList);
            }
        })
    };
    $scope.backendAccount();


    // 新增後台帳號
    $scope.adminInfoPopoverView = function () {
        if ($rootScope.rootUser.adminType < 2 ) {

            $scope.adminInfo = util.clone($scope.adminInfoDataInitial());
            var modalInstance = $modal.open({
                templateUrl: 'modules/account/view/popover/addAdmin.html',
                backdrop: 'static',
                scope: $scope,
                controller: function ($scope, $modalInstance, util) {
                    $scope.save = function (valid) {
                        util.confirm('Are you sure to add <font color="red">' + $scope.adminInfo.userName + '</font>?', function (r) {
                            if (r) {
                                if (valid) {
                                	if ($scope.adminInfo.userPw.length < 8) {
                        				util.alert("Password should be longer than 8 characters.");
                        				return false;
                        			}
                        			if ($scope.adminInfo.userPw.length > 20) {
                        				util.alert("Password should be shorter than 20 characters.");
                        				return false;
                        			}
                        			if ($scope.adminInfo.userPw.indexOf(' ') > 0) {
                        				util.alert("Password should not be blank.");
                        				return false;
                        			}

                        			var pattern = "([A-Za-z]|[0-9]|-|_){8,20}";
                        			var reg = new RegExp(pattern,"g");
                        			//var reg = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+`\-={}\[\]:";'<>?,.\/]).{4,16}$/;
                        			var pw_valid = reg.test($scope.adminInfo.userPw);
                        			if (pw_valid == false) {
                        				util.alert("Password should include alphabets and numbers.");
                        				return false;
                        			}
                        			
                                    $scope.loading = true;
                                    console.log($scope.adminInfo);
                                    backendAccountService.addAdmin($scope.adminInfo, function (data, status, headers, config) {
                                        if (data.result) {
                                            $scope.cancel();
                                            util.alert('Add successfully.');
                                            $scope.backendAccount();
                                        } else {
                                            util.alert(data.message);
                                        }
                                        $scope.loading = false;
                                    })
                                } else {
                                    util.alert('Required fields are not filled in completely.');
                                }
                            }
                        })
                    }

                    $scope.cancel = function () {
                        $scope.inputDisabled = false;
                        $scope.adminInfoDataInitial();
                        $modalInstance.dismiss('cancel');
                    }
                }
            })
        } else {
            util.alert('Permission Denied.');
        }
    }

    //修改後台帳號
    $scope.edit = function (data) {
        $scope.adminInfo = util.clone(data);
        var modalInstance = $modal.open({
            templateUrl: 'modules/account/view/popover/editAdmin.html',
            backdrop: 'static',
            scope: $scope,
            controller: function ($scope, $modalInstance, util) {
                $scope.save = function (valid) {
                    if ($rootScope.rootUser.adminType < 2 ) {
                        util.confirm('Are you sure to edit <font color="red">' + data.userName + '</font>?', function (r) {
                            if (r) {
                                if (valid) {                        			
                                    $scope.loading = true;
                                    backendAccountService.editAdmin($scope.adminInfo, function (data, status, headers, config) {
                                        if (data.result) {
                                            $scope.cancel();
                                            util.alert('Edit successfully.');
                                            $scope.backendAccount();
                                        } else {
                                            util.alert(data.message);
                                        }
                                        $scope.loading = false;
                                    })
                                } else {
                                    util.alert('Required fields are not filled in completely.');
                                }
                            }
                        })

                    } else {
                        util.alert('Permission Denied');
                    }
                }
                $scope.cancel = function () {
                    $scope.inputDisabled = false;
                    $scope.adminInfoDataInitial();
                    $modalInstance.dismiss('cancel');
                }
            }
        })
    }


    //刪除後台帳號
    $scope.deleteAccount = function (data) {
        util.confirm('Are you sure to remove <font color="red">' + data.userName + '</font>?', function (r) {
            if (r) {
                if (data.adminId == 0) {
                    util.alert('This account is not removable.');
                } else {
                    backendAccountService.deleteAdmin(data, function (data, status, headers, config) {
                        if (data.result) {
                            $scope.backendAccount();
                            util.alert("Remove successfully.");
                        } else {
                            util.alert(data.message);
                        }
                    })
                }
            }
        })
    }

})