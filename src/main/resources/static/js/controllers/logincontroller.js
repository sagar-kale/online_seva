app.controller('logincontroller',['$scope', '$state' ,function($scope, $state) {
	console.log("inside logincontroller");
		$(document).scrollTop(0);
		$scope.reDirectToHome = function(){
		 	$state.go("header.home");
		 }
}]);