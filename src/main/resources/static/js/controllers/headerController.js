app.controller('headerContrl',['$scope', '$state','userService','apiLink','APIService',function($scope, $state,userService,apiLink,APIService) {
 	$(document).scrollTop(0);
	$scope.menuCliked = false;
		$scope.openMenu = false;
	$scope.logoutApp = function(){
            var url = apiLink.logout;
            APIService.httpGet(url).then(
                function(res){
                    console.log("jkhaksdkja", res.data);
                    $state.go("login")
                  //  swal("", res.data.message, res.data.msgType);
                    location.href='/';
                },
                function(error) {
                    console.log(error);
                });
		}
//		$scope.gotoHome = function(){
//			$state.go("header.home")
//		}
//		$scope.gotoAdmin = function(){
//			$state.go("header.admin")
//		}
//		$scope.gotoJobs = function(){
//			$state.go("header.jobs");
//		}
//		$scope.reDirectToCources = function(){
//			$state.go("header.cources");
//		}
		
	
//    $('.navbar-nav li').click(function (e) {
//        $('.navbar-nav li').removeClass('activeLiDiv');
//        $(this).addClass('activeLiDiv');
//
//    });
//    
		$scope.reDirectTo = function(path){
			
			$scope.state =path;
			$state.go(path); 
				
//			$scope.menuCliked = false;
//			if (path == 'home') {
//				$state.go("header.home");
//			}
//			else if(path == 'admin'){
//				$state.go("header.admin")
//			}
//			else if(path == 'jobs'){
//				$state.go("header.jobs");
//			}
//			else if(path == 'home'){
//				$state.go("header.admin")
//			}
//			else if(path == 'students'){
//				$state.go("header.cources");
//			}
		}
		$scope.reDirectTo($state.current.name);
}]);