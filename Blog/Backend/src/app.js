'use strict';
var rootUrl = '';
var loginUrl = '';

var app = angular.module('app', ['ui.router', 'ngCookies', 'ui.bootstrap', 'ngSanitize', 'TreeWidget', 'locationConfigModule', 'toggle-switch']);

app.factory('baseHttp', function ($rootScope, $http) {
	function doService(url, params, callback, error) {
		if (params == null) {
			params = {};
		}
		$http.post(url, params).success(
			function (data, status, headers, config) {
				if (data.result == 0 && data.message == 'unlogin') {
					$rootScope.logout();
				}
				callback(data, status, headers, config);
			}).error(function (data, status, headers, config) {
			alert('Error occurred. Please contact the developer.');
		})
	}
	return {
		service: function (url, params, callback, error) {
			url = rootUrl + url;
			doService(url, params, callback, error);
		}
	}
})

app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('/home');
	$stateProvider.state('404', {
		url: '/page-not-found',
		views: {
			"content": {
				templateUrl: 'modules/share/view/page404.html?v=20201102'
			}
		}
	}).state('home', {
		url: '/home',
		views: {
			"content": {
				templateUrl: 'modules/home/view/home.html?v=20201102',
				controller: 'homeController'
			}
		}
	}).state('backendAccount', {
		url: '/backendAccount',
		views: {
			"content": {
				templateUrl: 'modules/account/view/backendAccount.html?v=20201102',
				controller: 'backendAccountController'
			}
		}
	}).state('vendor', {
		url: '/vendor',
		views: {
			"content": {
				templateUrl: 'modules/vendor/view/vendor.html?v=20201102',
				controller: 'vendorController'
			}
		}
	}).state('vendorEdit', {
		url: '/vendorEdit',
		views: {
			"content": {
				templateUrl: 'modules/vendor/view/vendorEdit.html?v=20201102',
				controller: 'vendorEditController'
			}
		},
		params: {
			vendorId: null
		},
		reload: true
	}).state('updataPw', {
		url: '/updataPw',
		views: {
			"content": {
				templateUrl: 'modules/user/view/updataPw.html?v=20201102',
				controller: 'updataPwController'
			}
		},
		reload: true
	})
}])

app.run(function ($rootScope, $location, $cookieStore, $http, $window, $state, locationIP, serviceName, backendName) {
	rootUrl = locationIP + serviceName + '/';
	$rootScope.rootUrl = rootUrl;
	loginUrl = locationIP + backendName + '/#/login';
	$rootScope.imageLoadUrl = rootUrl + 'api_backend';
	$rootScope.backendUrl = locationIP + backendName + '/';


	$rootScope.logout = function () {
		$cookieStore.remove('rg_gl');
		$window.location.replace(loginUrl);
	}

	$rootScope.rg_gl = $cookieStore.get('rg_gl') || {};

	if ($rootScope.rg_gl.currentUser) {
		$http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.rg_gl.currentUser.authdata;
		$rootScope.rootUser = $rootScope.rg_gl.currentUser.adminInfo;
	}

	$rootScope.$on('$locationChangeStart', function (event, next, current) {
		var url = $location.path();
		if (((url !== '/login') && !$rootScope.rg_gl.currentUser)) {
			$window.location.href = loginUrl;
		}
	});

	$rootScope.$on('$stateChangeError', function (event) {
		$state.go('404');
	});

})