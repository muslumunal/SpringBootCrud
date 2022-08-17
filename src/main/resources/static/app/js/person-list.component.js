angular.module('my-app')
    .component('personList', {
        templateUrl: '/app/template/person-list.html',
        controller: function ($scope, $location, PersonApi, $uibModal) {

            $scope.edit = function (person) {
                $location.path("/person/" + (person.id ? person.id : "new"));
            };

            $scope.edit64 = function (person) {
                $location.path("/personb64/" + (person.id ? person.id : "new"));
            };
            $scope.editMulti = function (person) {
                $location.path("/person-multi/" + (person.id ? person.id : "new"));
            };

            $scope.editInModal = function (person) {
                var modalInstance = $uibModal.open({
                    templateUrl: '/app/template/person-modal.html',
                    controller: 'PersonModalController',
                    size: 'md',
                    resolve: {
                        Person: function () {
                            return angular.copy(person);
                        }
                    }
                });

                modalInstance.result.then(function (ret) {
                    if (person.id) {
                        // update 
                        let idx = _.findIndex($scope.people, { id: ret.id });
                        if (idx != -1) {
                            $scope.people.splice(idx, 1, ret);
                        }
                    } else {
                        // create
                        $scope.people.splice(0, 0, ret);
                    }
                }, function () {
                    // cancel implementation
                });
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
                ret.$promise.then(function () {
                    _.remove($scope.people, { id: person.id });
                });

                console.log('js function exit');
            };

            $scope.setPerson = function (person) {
                $scope.p = person;
            };

            $scope.init = function () {
                $scope.people = PersonApi.list();

                let paging = {
                    currentPage: 1,
                    pageSize: 10
                };

                $scope.paging = paging;

                $scope.p = {};

                $scope.date = new Date();
            };

            $scope.init();
        }
    })
    .controller("PersonModalController", function ($scope, Person, $uibModalInstance, PersonApi, DataFactory) {

        $scope.ok = function () {
            PersonApi.persist($scope.person, function (data) {
                $uibModalInstance.close(data);
            });
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.init = function () {
            $scope.person = Person;
            $scope.hideSubmit = true;

            $scope.genders = DataFactory.getGenders();
        };
        $scope.init();
    });