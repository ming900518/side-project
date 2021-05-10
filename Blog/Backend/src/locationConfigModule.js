(function () {
	angular.module('locationConfigModule', [])
		// dev	  
	  .value("locationIP","http://localhost:8080/")
	  .value("serviceName","Service")
	  .value("backendName","Backend");

		// release
//	  .value("locationIP","http:///")
//	  .value("serviceName","Service")
//	  .value("backendName","Backend");

})();