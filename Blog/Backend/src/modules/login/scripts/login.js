'use strict';
var rootUrl = '';
var loginUrl ='';
angular.module('Authentication', []);
var app = angular.module('app',['ui.router', 'ngCookies', 'ui.bootstrap', 'ngSanitize', 'ngRoute',
     'Authentication', 'locationConfigModule']);

app.factory('baseHttp', function($rootScope, $http){
    function doService(url, params, callback, error){
        if (params == null) { params = {}; }
        $http.post(url, params).success(
            function(data, status, headers, config){
                callback(data, status, headers, config);
            }).error(function(data, status, headers, config){
                alert('Oops ! has error ! try it again !');
            }
        )
    }
    return {
        service : function(url, params, callback, error){
            url = rootUrl + url;
            doService(url, params, callback, error);
        }
    }
})

app.config(['$routeProvider',function($routeProvider) {   
    $routeProvider.when('/login', {
        controller: 'loginController',
        templateUrl: 'modules/login/view/loginContent.html'
    }).when('/', {
        redirectTo: 'index.html' 
    }).otherwise({ redirectTo: '/login' });
}])

app.run(function($rootScope, $location, $cookieStore, $http, $window, $state, locationIP, serviceName, backendName){
    rootUrl = locationIP + serviceName + '/';
    $rootScope.rootUrl = rootUrl;
    loginUrl = locationIP + backendName +'/#/login'; 

    $rootScope.rg_gl = $cookieStore.get('rg_gl') || {};
    if ($rootScope.rg_gl.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.rg_gl.currentUser.authdata;
     }

    $rootScope.$on('$locationChangeStart', function (event, next, current){
        var url = $location.path();
        if ((url !== '/login') && !$rootScope.rg_gl.currentUser) {
            $window.location.href = loginUrl;
        }
    });

    $rootScope.$on('$stateChangeError', function(event) {
        $state.go('404');
    });

    $rootScope.logout = function() {
        $cookieStore.remove('rg_gl');
        $window.location.replace(loginUrl);
    }
})