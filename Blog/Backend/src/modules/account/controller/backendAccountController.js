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
        "oLanguage": {
            "sUrl": "dataTables.zh-tw.txt"
        },
        "bJQueryUI": true,
        "sPaginationType": 'full_numbers',
        data: $scope.backendAccountList,
        columns: [{
                title: "帳號",
                data: "userCode",
                sWidth: "15%"
            },{
                title: "姓名",
                data: "userName",
                sWidth: "15%"
            },{
                title: "所屬單位",
                data: "department",
                sWidth: "15%"
            }, {
                title: "權限角色",
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
                        '<button name="edit" class="btn btn-sm btn-edit" value="' + row + '"><i class="ace-icon fa fa-pencil-square-o"></i>編輯</button>' +
                        '&nbsp;';
                    if ($rootScope.rootUser.adminType < 2 ) {
                        btnStr += '<button name="delete" class="btn btn-sm btn-dele "  value="' + row + '"><i class="ace-icon fa fa-trash-o"></i>刪除</button> ' +
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
                        util.confirm('確定要新增 <font color="red">' + $scope.adminInfo.userName + '</font>', function (r) {
                            if (r) {
                                if (valid) {
                                	if ($scope.adminInfo.userPw.length < 8) {
                        				util.alert("密碼請設定八碼(含八碼)以上!");
                        				return false;
                        			}
                        			if ($scope.adminInfo.userPw.length > 20) {
                        				util.alert("密碼請設定二十碼(含二十碼)以下!");
                        				return false;
                        			}
                        			if ($scope.adminInfo.userPw.indexOf(' ') > 0) {
                        				util.alert("密碼禁止使用空白密碼!");
                        				return false;
                        			}

                        			var pattern = "([A-Za-z]|[0-9]|-|_){8,20}";
                        			var reg = new RegExp(pattern,"g");
                        			//var reg = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+`\-={}\[\]:";'<>?,.\/]).{4,16}$/;
                        			var pw_valid = reg.test($scope.adminInfo.userPw);
                        			if (pw_valid == false) {
                        				util.alert("密碼設定應包括數字、英文字母及特別字元!");
                        				return false;
                        			}
                        			
                                    $scope.loading = true;
                                    console.log($scope.adminInfo);
                                    backendAccountService.addAdmin($scope.adminInfo, function (data, status, headers, config) {
                                        if (data.result) {
                                            $scope.cancel();
                                            util.alert('新增成功');
                                            $scope.backendAccount();
                                        } else {
                                            util.alert(data.message);
                                        }
                                        $scope.loading = false;
                                    })
                                } else {
                                    util.alert('必填欄位未輸入');
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
            util.alert('權限不足');
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
                        util.confirm('確定要修改 <font color="red">' + data.userName + '</font>', function (r) {
                            if (r) {
                                if (valid) {                        			
                                    $scope.loading = true;
                                    backendAccountService.editAdmin($scope.adminInfo, function (data, status, headers, config) {
                                        if (data.result) {
                                            $scope.cancel();
                                            util.alert('修改成功');
                                            $scope.backendAccount();
                                        } else {
                                            util.alert(data.message);
                                        }
                                        $scope.loading = false;
                                    })
                                } else {
                                    util.alert('必填欄位未輸入');
                                }
                            }
                        })

                    } else {
                        util.alert('權限不足');
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
        util.confirm('確定要刪除 <font color="red">' + data.userName + '</font>', function (r) {
            if (r) {
                if (data.adminId == 0) {
                    util.alert('此帳號無法刪除!');
                } else {
                    backendAccountService.deleteAdmin(data, function (data, status, headers, config) {
                        if (data.result) {
                            $scope.backendAccount();
                            util.alert("刪除成功");
                        } else {
                            util.alert(data.message);
                        }
                    })
                }
            }
        })
    }

})