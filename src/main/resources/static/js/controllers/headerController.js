app.controller('headerContrl',['$scope', '$state','userService' ,function($scope, $state,userService) {
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
		$scope.gotoAdmin = function(){
			$state.go("header.admin")
		}

		$scope.currentUser = userService.getUser();
		
}]);