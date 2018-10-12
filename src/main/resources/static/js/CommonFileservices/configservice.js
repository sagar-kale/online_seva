app.factory('APIService', ['$q', '$http', '$rootScope', '$state', function ($q, $http, $rootScope, $state) {
    return {
        uniqueID: function () {
            return Math.random() * Math.pow(10, 20);
        },
        httpGet: function (url) {
            var deferred = $q.defer();
            $rootScope.onAjax = {};
            var uniqueID = this.uniqueID();
            $http({
                method: 'GET',
                url: url,
                headers: {
                    //'Authorization' : $rootScope.token
                    "Access-Control-Allow-Origin": "*"
                },
                beforeSend: function () {
                    $rootScope.onAjax[uniqueID] = true;
                }
            }).then(function mySucces(response) {
                delete $rootScope.onAjax[uniqueID];
                deferred.resolve(response);
            }, function myError(e) {
                console.log(e)
                delete $rootScope.onAjax[uniqueID];
                deferred.reject(e);
            });
            return deferred.promise;
        },
        httpPostJson: function (url, data) {
            var deferred = $q.defer();
            $rootScope.onAjax = {};
            var uniqueID = this.uniqueID();
            $http({
                method: 'POST',
                url: url,
                headers: {
                    "Access-Control-Allow-Origin": "*",
                    "Content-Type": "application/json"
                },
                data: data,
                beforeSend: function () {
                    $rootScope.onAjax[uniqueID] = true;
                }
            }).then(function mySucces(response) {
                deferred.resolve(response);
                delete $rootScope.onAjax[uniqueID];
            }, function myError(e) {
                console.log(e)
                delete $rootScope.onAjax[uniqueID];
                deferred.reject(e);
            });
            return deferred.promise;
        },
        httpPostForm: function (url, data) {
            var deferred = $q.defer();
            $rootScope.onAjax = {};
            var uniqueID = this.uniqueID();
            $http({
                method: 'POST',
                url: url,
                headers: {
                    //'Authorization' : $rootScope.token
                },
                data: data,
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: angular.identity,
                beforeSend: function () {
                    $rootScope.onAjax[uniqueID] = true;
                }
            }).then(function mySucces(response) {
                deferred.resolve(response);
                delete $rootScope.onAjax[uniqueID];
            }, function myError(e) {
                console.log(e)
                delete $rootScope.onAjax[uniqueID];
                deferred.reject(e);
            });
            return deferred.promise;
        },
        httpPut: function (url, data) {
            var deferred = $q.defer();
            $rootScope.onAjax = {};
            var uniqueID = this.uniqueID();
            $http({
                method: 'PUT',
                url: url,
                headers: {
                    "Access-Control-Allow-Origin": "*",
                    "Content-Type": "application/json"
                },
                data: data,
                beforeSend: function () {
                    $rootScope.onAjax[uniqueID] = true;
                }
            }).then(function mySucces(response) {
                deferred.resolve(response);
                delete $rootScope.onAjax[uniqueID];
            }, function myError(e) {
                console.log(e)
                delete $rootScope.onAjax[uniqueID];
                deferred.reject(e);
            });
            return deferred.promise;
        },


        httpDelete: function (url, data) {
            var deferred = $q.defer();
            $rootScope.onAjax = {};
            var uniqueID = this.uniqueID();
            $http({
                method: 'DELETE',
                url: url,
                headers: {
                    "Access-Control-Allow-Origin": "*",
                    "Content-Type": "application/json"

                },
                data: data,
                beforeSend: function () {
                    $rootScope.onAjax[uniqueID] = true;
                }
            }).then(function mySucces(response) {
                deferred.resolve(response);
                delete $rootScope.onAjax[uniqueID];
            }, function myError(e) {
                console.log(e)
                delete $rootScope.onAjax[uniqueID];
                deferred.reject(e);
            });
            return deferred.promise;
        }

    };
}]);