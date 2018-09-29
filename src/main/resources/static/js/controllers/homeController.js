app.controller('homeController',['$scope', '$state' ,function($scope, $state) {
	console.log("inside homeController");
	$scope.gotoJobs = function(){
		$state.go("header.jobs");
	}
	$scope.reDirectToCources = function(){
				$state.go("header.cources");
			}
		
		console.log("inside home controller");
}]);