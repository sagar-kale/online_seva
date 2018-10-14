app.controller('adminContrl', ['$scope', '$state', 'apiLink', 'APIService', function ($scope, $state, apiLink, APIService) {

    $scope.selectedDiv = "allroles";

    $scope.toggleForms = function (str) {
        $scope.selectedDiv = str;
    }
    $('.userActionsDiv ul li').click(function (e) {
        $('.userActionsDiv ul li').removeClass('activeLi');
        $(this).addClass('activeLi');

    });
    $scope.activeTab = "show1";
    $scope.toggleTabs = function (tab) {
        $scope.activeTab = tab;
    }

    $scope.showSection2 = false;
    $scope.addmoreDetails = function () {
        $scope.showSection2 = true;
    }
    $(function () {

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
    angular.element(document).ready(function () {
        dTable = $('#user_table')
        dTable.DataTable();
    });
    $scope.clickedNext = function () {
        var target = $("#headingTwo");
        $("#headingTwo").click();
    }

    $scope.readOnlyFirst = false;
    $scope.saveFirstSection = function () {
        $scope.readOnlyFirst = true;
        $scope.clickedNext();


    }
    $scope.editFirstSection = function () {
        $scope.readOnlyFirst = false;
    }

    $scope.getAllUserList = function () {
        var url = apiLink.getAllUsers;
        APIService.httpGet(url).then(
            function (res) {
                if (res.data.msgType == 'error') {

                    swal("", res.data.message, res.data.msgType);
                    $state.go("login");
                }
                $scope.userList = res.data.dataList;
                console.log("UserList", $scope.userList);

            },
            function (error) {
                console.log(error);
            });
    }
    $scope.getAllRoleList = function () {
        var url = apiLink.getAllUsers;
        $scope.roleList = [
            {
                name: 'admin',
                id: 1
            },
            {
                name: 'user',
                id: 2
            }
        ];


        /*APIService.httpGet(url).then(
            function (res) {
                if (res.data.msgType == 'error') {

                    swal("", res.data.message, res.data.msgType);
                    $state.go("login");
                }
                $scope.userList = res.data.dataList;
                console.log("UserList", $scope.userList);

            },
            function (error) {
                console.log(error);
            });*/
    }
    $scope.getAllRoleList();

    $scope.getAllStudents = function () {
        var url = apiLink.getAllStudents;
        APIService.httpGet(url).then(
            function (res) {
                if (res.data.msgType == 'error') {

                    swal("", res.data.message, res.data.msgType);
                    $state.go("login");
                }
                $scope.studentList = res.data.dataList;
                console.log("studentList in admin", $scope.studentList);


            },
            function (error) {
                console.log(error);
            });
    }


    $scope.saveJobDetails = function () {

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
            function (res) {

                if (res.data.msgType == 'error') {
                    swal("", res.data.message, res.data.msgType);
                } else {
                    $scope.basicsJob = {};
                    $scope.subDetails = {};
//                    $scope.secondsection.$setPristine();
//                    $scope.thirdsection.$setPristine();
                    console.log("saved", res.data);
                    swal("", res.data.message, res.data.msgType);

                }

            },
            function (error) {
                console.log(error);
            });


    }


    // ************* admin actions **********

    $scope.activateUser = function (user) {

        var url = apiLink.updateUser;

        APIService.httpPut(url, user.username).then(
            function (res) {

                if (res.data.msgType == 'success') {
                    swal("", "user successfully activated", "success");
                    user.active = true;
                }
                else {
                    swal("", res.data.message, res.data.msgType);

                }

            },
            function (error) {
                console.log(error);
            });


    };


    $scope.deActivateUser = function (user) {
        var url = apiLink.updateUser;
        //   var data = {"username":user.username};
        APIService.httpPut(url, user.username).then(
            function (res) {

                if (res.data.msgType == 'success') {
                    swal("", "user successfully Deactivated", "success");
                    user.active = false;
                }
                else {
                    swal("", res.data.message, res.data.msgType);

                }

            },
            function (error) {
                console.log(error);
            });


    }

    $scope.removeUser = function (user) {
        var url = apiLink.removeUser;
        //   var data = {"username":user.username};
        APIService.httpDelete(url, user.username).then(
            function (res) {

                if (res.data.msgType == 'success') {
                    $scope.getAllUserList();
                    swal("", "user removed successfully ", "success");

                }
                else {
                    swal("", res.data.message, res.data.msgType);
                }

            },
            function (error) {
                console.log(error);
            });
    }

    // ************* Approve Student **********

    $scope.approveStudent = function (std) {

        var url = apiLink.updateStudentStatus;

        APIService.httpPut(url, std.email).then(
            function (res) {

                if (res.data.msgType == 'success') {
                    $scope.getAllStudents();
                    swal("", res.data.message, res.data.msgType);
                }
                else {
                    swal("", res.data.message, res.data.msgType);

                }

            },
            function (error) {
                console.log(error);
            });


    };

    // ************* Change Role **********

    $scope.changeUserRole = function (user, userrole) {
        var url = apiLink.fetchUserRoleUrl;

        if (user.role == userrole.name) {
            swal("Same", "current role and selected role is same, please select different role", "warning");
        } else {
            var data = {
                "username": user.username,
                "role": userrole.name
            }

            APIService.httpPut(url, data).then(
                function (res) {

                    if (res.data.msgType == 'success') {
                        $scope.getAllUserList();
                        swal("Done", res.data.message, res.data.msgType);
                    }
                    else {
                        swal("error", res.data.message, res.data.msgType);

                    }

                },
                function (error) {
                    console.log(error);
                });
        }
    };
    //***********  edit job Details **********
	$scope.loadData = function(){
	       var url = apiLink.getAllJobs;
         APIService.httpGet(url).then(
       function(res){
       $scope.jobdata = res.data;
        console.log("jkhaksdkja", $scope.jobdata);
     
       
       },
       function(error) {
         console.log(error);
       });
};
$scope.loadData();
$scope.EditJob = function(editJob){
	console.log("Edit Job",editJob);
}

}]);