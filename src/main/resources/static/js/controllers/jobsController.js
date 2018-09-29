app.controller('jobsController',['$scope', '$state' ,function($scope, $state) {
	console.log("inside homeController");
			$scope.gotoJobs = function(){
				$state.go("header.jobs");
			}
		
		console.log("inside home controller");
}]);