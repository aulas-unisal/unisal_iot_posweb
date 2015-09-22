var app = angular.module('sensorApp', []);
app.controller('sensorCtrl', function ($scope, $http, $timeout) {
    $scope.sensores = [];
    $scope.loadSensorApp = function () {
        $http.get("http://localhost:8080/wsiot/api/sensor/", {cache: false})
                .success(function (response) {
                    //console.log(response.sensor);
                    $scope.sensores = response.sensor;
                });
        $scope.config = {
            itemsPerPage: 5,
            fillLastPage: true
        }
    };
    $timeout($scope.loadSensorApp(), 5000);
    //$scope.loadSensorApp();
});
//ng-init="loadSensorApp()