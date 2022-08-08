angular.module('my-app')
    .component('personEdit', {
        templateUrl: '/app/template/person-edit.html',
        controller: function ($scope, $routeParams, PersonApi, $location) {

            $scope.save = function(){
                // save person to server

                PersonApi.persist($scope.person, function(){
                    $location.path("/");
                });
            };

            $scope.init = function () {

                $scope.genders = ["MALE", "FEMALE"];

                let id = $routeParams.id;
                if ( id == 'new') {
                    $scope.person = {};
                } else {
                    // fetch from server
                    $scope.person = PersonApi.read({id: id});
                }
            };

            $scope.init();
        }
    });