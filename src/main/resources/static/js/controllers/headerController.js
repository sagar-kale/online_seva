app.controller('headerContrl',['$scope', '$state','userService','apiLink','APIService',function($scope, $state,userService,apiLink,APIService) {
	console.log("inside headerContrl");
	$scope.currentUser = userService.getUser();
	// if(!$scope.currentUser.username){
	// 	$state.go('login');
	// }
	// 	$(document).scrollTop(0);
		$scope.openMenu = false;
		

		$scope.logoutApp = function(){
            var url = apiLink.logout;
            APIService.httpGet(url).then(
                function(res){
                    console.log("jkhaksdkja", res.data);
                    $state.go("login")
                    window.location.reload(true);
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

		
		
		
}]);