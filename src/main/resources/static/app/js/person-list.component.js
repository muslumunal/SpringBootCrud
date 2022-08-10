angular.module('my-app')
    .component('personList', {
        templateUrl: '/app/template/person-list.html',
        controller: function ($scope, $location, PersonApi) {

            $scope.edit = function (person) {
                $location.path("/person/" + (person.id ? person.id : "new"));
            };

            $scope.delete = function (person) {
                /* wrong
                PersonApi.remove({ id: person.id });
                _.remove($scope.people, { id: person.id });
                */

                // callback method
                /*
                PersonApi.remove({ id: person.id }, function(){
                    _.remove($scope.people, { id: person.id });
                });
                */
                
                let ret = PersonApi.remove({ id: person.id });
                ret.$promise.then(function(){
                    _.remove($scope.people, { id: person.id });
                });
                
                console.log('js function exit');
            };

            $scope.init = function () {
                $scope.people = PersonApi.list();

                let paging = {
                    currentPage: 1,
                    pageSize: 10
                };

                $scope.paging = paging;
            };

            $scope.init();
        }
    });