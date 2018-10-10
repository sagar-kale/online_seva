app.controller('homeController',['$scope', '$state' ,function($scope, $state) {
	console.log("inside homeController");
    $("#parentloader").hide();
	$scope.gotoJobs = function(){
		$state.go("header.jobs");
	}
	$scope.reDirectToCources = function(){
				$state.go("header.cources");
			}
		
		console.log("inside home controller");
}]);