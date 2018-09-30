app.controller('courcesCntrl',['$scope', '$state' ,function($scope, $state) {

	$scope.listOfCources = ["MSCIT","DTP","CCC","TALLY","HTML","CSS","CPP","Java"];	
	$scope.selectedDiv = "Register";
	
	$scope.toggleForms = function(str){
		$scope.selectedDiv = str;
	}

	
}]);