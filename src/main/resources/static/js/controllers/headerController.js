app.controller('headerContrl',['$scope', '$state','userService','apiLink','APIService',function($scope, $state,userService,apiLink,APIService) {
	console.log("inside headerContrl");
		$(document).scrollTop(0);
		$scope.openMenu = false;
		// $scope.gotoLandingPage = function(){
		// 	$state.go("ultimatix");
		// }

		$scope.logoutApp = function(){
            var url = apiLink.logout;
            APIService.httpGet(url).then(
                function(res){
                    console.log("jkhaksdkja", res.data);
                    $state.go("login")
                    swal("", res.data.message, res.data.msgType);
                },
                function(error) {
                    console.log(error);
                });
		}
		$scope.gotoHome = function(){
			$state.go("header.home")
		}
		$scope.gotoAdmin = function(){
			$state.go("header.admin")
		}

		$scope.currentUser = userService.getUser();
		
}]);