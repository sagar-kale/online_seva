app.controller('courcesCntrl', ['$scope', '$state', 'apiLink', 'APIService', function($scope, $state, apiLink, APIService) {

	$scope.listOfCources = ["MSCIT","DTP","CCC","TALLY","HTML","CSS","CPP","Java"];	
	$scope.selectedDiv = "Register";
	
	$scope.toggleForms = function(str){
		$scope.selectedDiv = str;
	}

			
	}]);