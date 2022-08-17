var app = angular.module("my-app",
    [
        'ngRoute',
        'ngResource',
        'ui.bootstrap',
        'ngFileUpload'
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
        .when('/personb64/:id', {
            template: '<person-edit-b64></person-edit-b64>'
        })
        .when('/person-multi/:id', {
            template: '<person-multi></person-multi>'
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
        },
        persistMulti: {
            method: "POST",
            url: "/new-person",
            transformRequest: function (data) {
                let person = data.person;
                let file = data.file;

                var fd = new FormData();
                angular.forEach(person, function (value, key) {
                    if (value instanceof FileList) {
                        if (value.length == 1) {
                            fd.append(key, value[0]);
                        } else {
                            angular.forEach(value, function (file, index) {
                                fd.append(key + '_' + index, file);
                            });
                        }
                    } else {
                        fd.append(key, value);
                    }
                });

                fd.append("file", file);

                return fd;
            },
            headers: { "Content-Type": undefined }
        }
    });
}]);

app.factory('AccountApi', ['$resource', function ($resource) {

    var baseUrl = "/";

    return $resource('/', {}, {
        login: {
            method: 'POST',
            url: "/login"
        },
        me: {
            method: "GET",
            url: "/me"
        }
    });
}]);

app.factory("AccountService", function (AccountApi) {

    let loggedIn = false;
    let observers = []; // array of functions

    function getLoggedIn() {
        return loggedIn;
    }

    function setLoggedIn(l) {
        loggedIn = l;
        for (let i = 0; i < observers.length; i++) {
            observers[i](l);
        }
    }

    function addObserver(o) {
        observers.push(o);
    }

    /*
        AccountApi.me(function(data){
            if(data){
                setLoggedIn(true);
            }
        });
        */

    if (current_user) {
        setLoggedIn(true);
    }

    return {
        setL: setLoggedIn,
        getL: getLoggedIn,
        addO: addObserver
    }

});

app.factory("DataFactory", function(){
    let genders = ["MALE", "FEMALE"];

    return {
        getGenders: function(){
            return genders;
        }
    }
});

app.filter("timeago", function () {
    //time: the time
    //local: compared to what time? default: now
    //raw: wheter you want in a format of "5 minutes ago", or "5 minutes"
    return function (time, local, raw) {
        if (!time) return "never";

        if (!local) {
            (local = Date.now())
        }

        if (angular.isDate(time)) {
            time = time.getTime();
        } else if (typeof time === "string") {
            time = new Date(time).getTime();
        }

        if (angular.isDate(local)) {
            local = local.getTime();
        } else if (typeof local === "string") {
            local = new Date(local).getTime();
        }

        if (typeof time !== 'number' || typeof local !== 'number') {
            return;
        }

        var
            offset = Math.abs((local - time) / 1000),
            span = [],
            MINUTE = 60,
            HOUR = 3600,
            DAY = 86400,
            WEEK = 604800,
            MONTH = 2629744,
            YEAR = 31556926,
            DECADE = 315569260;

        if (offset <= MINUTE) span = ['', raw ? 'now' : 'less than a minute'];
        else if (offset < (MINUTE * 60)) span = [Math.round(Math.abs(offset / MINUTE)), 'min'];
        else if (offset < (HOUR * 24)) span = [Math.round(Math.abs(offset / HOUR)), 'hr'];
        else if (offset < (DAY * 7)) span = [Math.round(Math.abs(offset / DAY)), 'day'];
        else if (offset < (WEEK * 52)) span = [Math.round(Math.abs(offset / WEEK)), 'week'];
        else if (offset < (YEAR * 10)) span = [Math.round(Math.abs(offset / YEAR)), 'year'];
        else if (offset < (DECADE * 100)) span = [Math.round(Math.abs(offset / DECADE)), 'decade'];
        else span = ['', 'a long time'];

        span[1] += (span[0] === 0 || span[0] > 1) ? 's' : '';
        span = span.join(' ');

        if (raw === true) {
            return span;
        }
        return (time <= local) ? span + ' ago' : 'in ' + span;
    }
});

app.directive("myPerson", function () {
    return {
        restrict: 'E',
        scope: {
            person: '=pe',
            showLastName: '='
        },
        templateUrl: '/app/template/person.html',
        link: function (scope, element, attribue) {

            scope.callMyName = function () {
                toastr.success(scope.person.firstName);
            };


            scope.init = function () {

            };

            scope.init();
        }
    };
})

