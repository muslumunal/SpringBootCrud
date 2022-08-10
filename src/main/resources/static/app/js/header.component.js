angular.module("my-app")
    .controller("HeaderController", function($scope, AccountService){


        $scope.observe = function(state){
            $scope.isLoggedIn = state;
        }

        $scope.init = function(){
            $scope.applicationName = "My Person Application";

            $scope.isLoggedIn = AccountService.getL();
            AccountService.addO($scope.observe);

        };

        $scope.init();
    });