(function () {

    var app = angular.module("myApp", ['ngMaterial']);

    app.factory('myService', ['$http', '$q', function ($http, $q) {
        var REST_LIST_POEM_URI = 'http://localhost:9200/list/poem/_search';
        var REST_POEM_URI = 'http://localhost:9200/list/poem/';

        var REST_LIST_STORY_URI = 'http://localhost:9200/list/story/_search';
        var REST_STORY_URI = 'http://localhost:9200/list/story/';

        var REST_LIST_SONG_URI = 'http://localhost:9200/list/song/_search';
        var REST_SONG_URI = 'http://localhost:9200/list/song/';
        var factory = {
            fetchAllPoems: function () {
                var deferred = $q.defer();
                $http.get(REST_LIST_POEM_URI).then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        console.error('Error while fetching Poems');
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
            },

            addNewPoem: function (poem, list) {
                var deferred = $q.defer();
                if (list == "") {
                    list.length = 0;
                }
                var max = 0;
                for (var i = 0; i < list.length; i++) {
                    if (parseInt(list[i]._id) > max)
                        max = parseInt(list[i]._id);
                }

                $http.post(REST_POEM_URI + (max + 1), poem).then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        console.error('Error while fetching Poems');
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
            },

            updatePoem: function (poem, id) {
                var deferred = $q.defer();
                $http.put(REST_POEM_URI + id, poem).then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        console.error('Error while fetching Poems');
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
            },

            deletePoem: function (id) {
                var deferred = $q.defer();
                $http.delete(REST_POEM_URI + id).then(
                    function (reponse) {
                        deferred.resolve(reponse.data);
                    },
                    function (errResponse) {
                        console.error('Error while fetching Poems');
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
            },

            fetchAllStories: function () {
                var deferred = $q.defer();
                $http.get(REST_LIST_STORY_URI).then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        console.error('Error while fetching Poems');
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
            },

            addNewStory: function (story, list) {
                var deferred = $q.defer();
                if (list == "") {
                    list.length = 0;
                }
                var max = 0;
                for (var i = 0; i < list.length; i++) {
                    if (parseInt(list[i]._id) > max)
                        max = parseInt(list[i]._id);
                }

                $http.post(REST_STORY_URI + (max + 1), story).then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        console.error('Error while fetching Poems');
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
            },

            updateStory: function (story, id) {
                var deferred = $q.defer();
                $http.put(REST_STORY_URI + id, story).then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        console.error('Error while fetching Poems');
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
            },

            deleteStory: function (id) {
                var deferred = $q.defer();
                $http.delete(REST_STORY_URI + id).then(
                    function (reponse) {
                        deferred.resolve(reponse.data);
                    },
                    function (errResponse) {
                        console.error('Error while fetching Poems');
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
            },


            fetchAllSongs: function () {
                var deferred = $q.defer();
                $http.get(REST_LIST_SONG_URI).then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        console.error('Error while fetching Poems');
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
            },

            addNewSong: function (song, list) {
                var deferred = $q.defer();
                if (list == "") {
                    list.length = 0;
                }
                var max = 0;
                for (var i = 0; i < list.length; i++) {
                    if (parseInt(list[i]._id) > max)
                        max = parseInt(list[i]._id);
                }

                $http.post(REST_SONG_URI + (max + 1), song).then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        console.error('Error while fetching Poems');
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
            },

            updateSong: function (song, id) {
                var deferred = $q.defer();
                $http.put(REST_SONG_URI + id, song).then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        console.error('Error while fetching Poems');
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
            },

            deleteSong: function (id) {
                var deferred = $q.defer();
                $http.delete(REST_SONG_URI + id).then(
                    function (reponse) {
                        deferred.resolve(reponse.data);
                    },
                    function (errResponse) {
                        console.error('Error while fetching Poems');
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
            }
        };

        return factory;
    }]);

    app.controller('myController', ['$scope', '$mdDialog', 'myService', function ($scope, $mdDialog, myService) {

        $scope.pageAdd = 'add.html';
        $scope.pagePoem = 'poem.html';
        $scope.pageSearch = 'search.html';
        $scope.view = 'poem.html';

        $scope.select = "poem";

        $scope.customFullscreen = false;
        $scope.hideDetail = true;
        var position = -1;

        //HIDE
        $scope.clickHide = function () {
            $scope.hideDetail = true;
        };
        $scope.hideAdd = true;

        fetchAllStories();
        function fetchAllStories() {
            myService.fetchAllStories()
                .then(
                    function (d) {
                        $scope.listPoem = d.hits.hits;
                    },
                    function (errResponse) {
                        console.error('Error while fetching Poems');
                    }
                );
            return $scope.listPoem;
        }

        fetchAllSongs();
        function fetchAllSongs() {
            myService.fetchAllSongs()
                .then(
                    function (d) {
                        $scope.listPoem = d.hits.hits;
                    },
                    function (errResponse) {
                        console.error('Error while fetching Poems');
                    }
                );
            return $scope.listPoem;
        }

        fetchAllPoems();
        function fetchAllPoems() {
            myService.fetchAllPoems()
                .then(
                    function (d) {
                        $scope.listPoem = d.hits.hits;
                    },
                    function (errResponse) {
                        console.error('Error while fetching Poems');
                    }
                );
            return $scope.listPoem;
        }

        $scope.listPoem = fetchAllPoems();

        $scope.listCategory = ('Romantic Resistance Affection Poetry Fleer').split(' ').map(function (categoly) {
            return {value: categoly};
        });

        $scope.clickPoem = function () {
            $scope.view = 'poem.html';
            $scope.select.select = "poem";
            $scope.listPoem = fetchAllPoems();

            $scope.hideDetail = true;
            $scope.listCategory = ('Romantic Resistance Affection Poetry Fleer').split(' ').map(function (categoly) {
                return {value: categoly};
            });
        };

        $scope.clickSong = function () {
            $scope.view = 'poem.html';
            $scope.select = "song";

            fetchAllSongs();

            $scope.hideDetail = true;
            $scope.listCategory = ('Pop Rock Jazz Blues R&B/Soul HipHop Country Dance').split(' ').map(function (categoly) {
                return {value: categoly};
            });

        };

        $scope.clickStory = function () {
            $scope.view = 'poem.html';
            $scope.select = "story";

            fetchAllStories();

            $scope.hideDetail = true;
            $scope.listCategory = ('Adventure Legend').split(' ').map(function (categoly) {
                return {value: categoly};
            });
        };

        $scope.clickSearch = function () {
            $scope.view = 'search.html';
        };

        $scope.showPageAdd = function () {
            $scope.poem = {id: '', title: '', author: '', category: 'Romantic', content: ''};
            $scope.hideDetail = true;
            if ($scope.hideAdd == true) {
                $scope.hideAdd = false;
            } else {
                $scope.hideAdd = true;
            }
        };

        $scope.clickDetail = function (id) {
            $scope.hideAdd = true;
            if (position != id) {
                $scope.hideDetail = false;
                position = id;
            } else {
                $scope.hideDetail = !$scope.hideDetail;
            }

            for (var i = 0; i < $scope.listPoem.length; i++) {
                if (id == $scope.listPoem[i]._id) {
                    $scope.poem.title = $scope.listPoem[i]._source.title;
                    $scope.poem.author = $scope.listPoem[i]._source.author;
                    $scope.poem.category = $scope.listPoem[i]._source.category;
                    $scope.poem.content = $scope.listPoem[i]._source.content;
                }
            }
        };

        // ================== CRUD ======================= //
        $scope.poem = {title: '', author: '', category: 'Romantic', content: ''};

        // ========= UPDATE ========= //
        $scope.updatePoem = function (poem) {
            if ($scope.select == "poem") {
                myService.updatePoem(poem, position).then(
                    function () {
                        fetchAllPoems();
                        alert("Update Complete!");
                    },
                    function (errResponse) {
                        console.error("ERROR while UPDATE POEM");
                    }
                );
            } else if ($scope.select == "song") {
                myService.updateSong(poem, position).then(
                    function () {
                        fetchAllSongs();
                        alert("Update Complete!");
                    },
                    function (errResponse) {
                        console.error("ERROR while UPDATE POEM");
                    }
                );
            } else {
                myService.updateStory(poem, position).then(
                    function () {
                        fetchAllStories();
                        alert("Update Complete!");
                    },
                    function (errResponse) {
                        console.error("ERROR while UPDATE POEM");
                    }
                );
            }
            $scope.hideDetail = true;
        };

        // ========= DELETE ========= //
        $scope.clickDelete = function (id) {
            var confirm = $mdDialog.confirm()
                .title('Would you like to delete')
                .ok('YES')
                .cancel('I think again');

            $mdDialog.show(confirm).then(
                function () {
                    if ($scope.select == "poem") {
                        myService.deletePoem(id).then(
                            function () {
                                fetchAllPoems();
                                alert("Deleted poem");
                            },
                            function () {
                                console.error("ERROR while deleted");
                            }
                        );
                    } else if ($scope.select == "song") {
                        myService.deleteSong(id).then(
                            function () {
                                fetchAllSongs();
                                alert("Deleted song");
                            },
                            function () {
                                console.error("ERROR while deleted");
                            }
                        );
                    } else {
                        myService.deleteStory(id).then(
                            function () {
                                fetchAllStories();
                                alert("Deleted story");
                            },
                            function () {
                                console.error("ERROR while deleted");
                            }
                        );
                    }
                },
                function () {

                }
            );
        };

        // ========= SUBMIT ADD ========= //
        $scope.submitAdd = function (poem, list) {
            if ($scope.select == "poem") {
                myService.addNewPoem(poem, list).then(
                    function () {
                        fetchAllPoems();
                        alert("Add Compelte");
                    },
                    function (errResponse) {
                        console.error('Error while creating Poems');
                    }
                );
            } else if ($scope.select == "song") {
                myService.addNewSong(poem, list).then(
                    function () {
                        fetchAllSongs();
                        alert("Add Compelte");
                    },
                    function (errResponse) {
                        console.error('Error while creating Poems');
                    }
                );
            } else {
                myService.addNewStory(poem, list).then(
                    function () {
                        fetchAllStories();
                        alert("Add Compelte");
                    },
                    function (errResponse) {
                        console.error('Error while creating Poems');
                    }
                );
            }

            $scope.hideAdd = true;

        };
        // ================== END CRUD ======================= //
    }]);

    // SEARCH CONTROLLER
    app.controller('searchController', ['$scope', '$mdDialog', 'myService', function ($scope, $mdDialog, myService) {
        $scope.poem = {id: '', title: '', author: '', category: 'Romantic', content: ''};
        $scope.hidenTable = true;

        $scope.term = '';
        $scope.criteria = '';

        fetchAllPoems();
        function fetchAllPoems() {
            myService.fetchAllPoems()
                .then(
                    function (d) {
                        $scope.listPoem = d.hits.hits;
                    },
                    function (errResponse) {
                        console.error('Error while fetching Users');
                    }
                );
            return $scope.listPoem;
        }

        fetchAllStories();
        function fetchAllStories() {
            myService.fetchAllStories()
                .then(
                    function (d) {
                        $scope.listPoem = d.hits.hits;
                    },
                    function (errResponse) {
                        console.error('Error while fetching Poems');
                    }
                );
            return $scope.listPoem;
        }

        fetchAllSongs();
        function fetchAllSongs() {
            myService.fetchAllSongs()
                .then(
                    function (d) {
                        $scope.listPoem = d.hits.hits;
                    },
                    function (errResponse) {
                        console.error('Error while fetching Poems');
                    }
                );
            return $scope.listPoem;
        }

        $scope.hide = true;
        $scope.list = [];

        $scope.selection = ('Title Author Category Content').split(' ').map(function (select) {
            return {value: select};
        });

        $scope.selectType = ('Poem Story Song').split(' ').map(function (select) {
            return {value: select};
        });

        $scope.listPoem = fetchAllPoems();

        $scope.select = function(){
            if ($scope.type == "Poem"){
                $scope.listPoem = fetchAllPoems();
            }else if ($scope.type == "Poem"){
                $scope.listPoem = fetchAllSongs();
            }else{
                $scope.listPoem = fetchAllStories();
            }
        }

        $scope.submitSearch = function () {
            for (var i = 0; i < $scope.listPoem.length; i++) {
                if ($scope.term != null && $scope.term != "") {
                    if ($scope.type == "Poem"){
                        if ($scope.criteria == "Title") {
                            alert($scope.listPoem.length);
                            if ($scope.listPoem[i]._source.title.toUpperCase().search($scope.term.toUpperCase()) > -1) {
                                $scope.list.push($scope.listPoem[i]);
                            }
                        } else if ($scope.criteria == "Author") {
                            if ($scope.listPoem[i]._source.author.toUpperCase().search($scope.term.toUpperCase()) > -1) {
                                $scope.list.push($scope.listPoem[i]);
                            }
                        } else if ($scope.criteria == "Category") {
                            if ($scope.listPoem[i]._source.category.toUpperCase().search($scope.term.toUpperCase()) > -1) {
                                $scope.list.push($scope.listPoem[i]);
                            }
                        } else {
                            if ($scope.listPoem[i]._source.content.toUpperCase().search($scope.term.toUpperCase()) > -1) {
                                $scope.list.push($scope.listPoem[i]);
                            }
                        }
                    }else if ($scope.type == "Song"){

                    }else{

                    }

                    $scope.hide = false;
                } else {
                    $mdDialog.show(
                        $mdDialog.alert()
                            .parent(angular.element(document.querySelector('#popupContainer')))
                            .clickOutsideToClose(true)
                            .title('Term is empty')
                            .textContent('You should not leave Term blank')
                            .ok('Retry')
                    );
                }
            }
        };
    }])
})()