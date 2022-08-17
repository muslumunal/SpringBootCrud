angular.module('my-app')
    .component('personEdit', {
        templateUrl: '/app/template/person-edit.html',
        controller: function ($scope, $routeParams, PersonApi, $location, DataFactory) {

            $scope.save = function(form){

                if(form.$valid){
                    PersonApi.persist($scope.person, function(){
                        toastr.success("Person eklendi..");
                        $location.path("/home");
                    });
                }else{
                    console.log(form);
                    toastr.warning("Lütfen tüm alanları giriniz.");
                }
            };

            $scope.init = function () {

                $scope.genders = DataFactory.getGenders();

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