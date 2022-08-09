var app = angular.module("my-app",
    [
        'ngRoute',
        'ngResource'
    ]);


app.config(function ($routeProvider, $locationProvider) {

    // configure html5 to get links working on jsfiddle
    $locationProvider.html5Mode(true);

    $routeProvider
        .when('/home', {
            template: '<person-list></person-list>'
        })
        .when('/person/:id', {
            template: '<person-edit></person-edit>'
        })
         .when('/login', {
                    template: '<login></login>'
                })
        .otherwise({
            redirectTo: "/"
        });
});

app.filter("ageFilter", function () {
    return function (input, age) {
        return input <= age ? "Genc" : "Yetiskin";
    }
});

app.factory('PersonApi', ['$resource', function ($resource) {

    var baseUrl = "/person";

    return $resource('/person/:id', { id: '@id' }, {
        list: {
            method: 'GET',
            url: baseUrl + "/list",
            isArray: true
        },
        read: {
            method: "GET",
            url: baseUrl + "/:id"
        },
        persist: {
            method: "POST",
            url: baseUrl + "/persist"
        },
        remove: {
            method: "DELETE",
            url: baseUrl + "/remove"
        }
    });
}]);

app.factory('AccountApi', ['$resource', function ($resource) {

    var baseUrl = "/";

    return $resource('/', {  }, {
        login: {
            method: 'POST',
            url: "/login"
        }
    });
}]);