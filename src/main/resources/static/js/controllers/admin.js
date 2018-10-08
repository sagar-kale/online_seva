app.controller('adminContrl', ['$scope', '$state', 'apiLink', 'APIService', function($scope, $state, apiLink, APIService) {

    $scope.activeTab = "show1";
    $scope.toggleTabs = function(tab) {
        $scope.activeTab = tab;
    }

    $scope.showSection2 = false;
    $scope.addmoreDetails = function() {
        $scope.showSection2 = true;
    }
    $(function() {

        function toggleChevron(e) {
            $(e.target)
                .prev('.panel-heading')
                .find("i")
                .toggleClass('rotate-icon');
            $('.panel-body.animated').toggleClass('zoomIn zoomOut');
        }

        $('#accordion').on('hide.bs.collapse', toggleChevron);
        $('#accordion').on('show.bs.collapse', toggleChevron);
    });
    angular.element(document).ready(function() {
        dTable = $('#user_table')
        dTable.DataTable();
    });
    $scope.clickedNext = function() {
        var target = $("#headingTwo");
        $("#headingTwo").click();
    }

    $scope.readOnlyFirst = false;
    $scope.saveFirstSection = function() {
        $scope.readOnlyFirst = true;
        $scope.clickedNext();


    }
    $scope.editFirstSection = function() {
        $scope.readOnlyFirst = false;
    }


    $scope.getAllUserList = function() {
        var url = apiLink.getAllUsers;
        APIService.httpGet(url).then(
            function(res) {
                $scope.userList = res.data;
                console.log("jkhaksdkja", $scope.userList);


            },
            function(error) {
                console.log(error);
            });
    }
    $scope.getAllUserList();

     $scope.getAllStudents = function() {
        var url = apiLink.getAllStudents;
        APIService.httpGet(url).then(
            function(res) {
                $scope.studentList = res.data;
                console.log("studentList", $scope.studentList);


            },
            function(error) {
                console.log(error);
            });
    }
  





    $scope.saveJobDetails = function() {

        var jobdata = {

            "lastDate": $scope.basicsJob.LastDate,
            "qualification": $scope.basicsJob.Qualification,
            "sector": $scope.basicsJob.Sector,
            "totalPosts": $scope.basicsJob.TotalPosts,
            "title": $scope.basicsJob.title,
            "startDate": $scope.basicsJob.StartDate,


            "jobSubDetails": {
                "aboutJob": $scope.subDetails.aboutJob,
                "postName": $scope.subDetails.postName,
                "totalVacancies": $scope.subDetails.TotalVacancies,
                "salaryScale": $scope.subDetails.SalaryScale,
                "jobLocation": $scope.subDetails.JobLocation,
                "educationalQualifiction": $scope.subDetails.EducationalQualifiction,
                "selectionProcess": $scope.subDetails.SelectionProcess,
                "ageLimit": $scope.subDetails.AgeLimit,
                "applicationFee": $scope.subDetails.applicationFee,
                "howToApplay": $scope.subDetails.Applay,
                "youTubeLink": $scope.subDetails.YouTubeLink

            }

        };
        var url = apiLink.saveJobDetails;


        APIService.httpPostJson(url, jobdata).then(
            function(res) {

                if (res.data.msgType == 'error') {
                    swal("", res.data.message, res.data.msgType);
                } else {
                    $scope.basicsJob ={};
                     $scope.subDetails = {};
//                    $scope.secondsection.$setPristine();
//                    $scope.thirdsection.$setPristine();
                    console.log("saved", res.data);
                    swal("", res.data.message, res.data.msgType);

                }

            },
            function(error) {
                console.log(error);
            });



    }

   // ************* admin actions **********

   $scope.activateUser = function(user){

   			var url = apiLink.updateUser;

                APIService.httpPut(url,user.username).then(
                    function(res) {

                    	if(res.data.msgType=='success'){
                    		 swal("", "user successfully activated", "success");
                    		 user.active = true;
                    	}
                    	else{
                    		 swal("", res.data.message, res.data.msgType);

                    	}

                    },
                    function(error) {
                        console.log(error);
                    });


   };


    $scope.deActivateUser = function(user){
    		var url = apiLink.updateUser;
         //   var data = {"username":user.username};
        APIService.httpPut(url,user.username).then(
            function(res) {

            	if(res.data.msgType=='success'){
            		 swal("", "user successfully Deactivated", "success");
            		 user.active = false;
            	}
            	else{
            		 swal("", res.data.message, res.data.msgType);

            	}

            },
            function(error) {
                console.log(error);
            });


   }

   $scope.removeUser = function(user){
        var url = apiLink.removeUser;
         //   var data = {"username":user.username};
        APIService.httpDelete(url,user.username).then(
            function(res) {

            	if(res.data.msgType=='success'){
            	    $scope.getAllUserList();
            		 swal("", "user removed successfully ", "success");

            	}
            	else{
            		 swal("", res.data.message, res.data.msgType);
            	}

            },
            function(error) {
                console.log(error);
            });
   }
$scope.approveStd = function(std){

}

}]);