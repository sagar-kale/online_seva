app.controller('headerContrl',['$scope', '$state' ,function($scope, $state) {
	console.log("inside headerContrl");
		$(document).scrollTop(0);
		$scope.openMenu = false;
		// $scope.gotoLandingPage = function(){
		// 	$state.go("ultimatix");
		// }

		$scope.logoutApp = function(){
			$state.go("login")
		}
		$scope.gotoHome = function(){
			$state.go("header.home")
		}
}]);