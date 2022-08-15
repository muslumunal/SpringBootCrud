angular.module('my-app')
    .component('personEditB64', {
        templateUrl: '/app/template/person-edit-b64.html',
        controller: function ($scope, $routeParams, PersonApi, $location, Upload) {

            $scope.save = function(form){

                if(form.$valid){
                    Upload.base64DataUrl($scope.file).then(function(data){
                        $scope.person.avatar = data;
                        PersonApi.persist($scope.person, function(){
                            toastr.success("Person eklendi..");
                            $location.path("/home");
                        });
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