angular.module('my-app')
    .component('personMulti', {
        templateUrl: '/app/template/person-edit-multi.html',
        controller: function ($scope, $routeParams, PersonApi, $location, Upload) {

            $scope.save = function(form){

                if(form.$valid){
                   
                    PersonApi.persistMulti({person: $scope.person, file: $scope.file}, function(){
                        toastr.success("Person eklendi..");
                        $location.path("/home");
                    });
                
                }else{
                    toastr.warning("Lütfen tüm alanları giriniz.");
                }
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