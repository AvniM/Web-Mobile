var app = angular.module("youtubeApp", []);

app.config(function($sceDelegateProvider) {
    $sceDelegateProvider.resourceUrlWhitelist([
        "self",
        "https://www.youtube.com/**"
    ]);
});

app.controller("youtubeCtrl", function($scope, $http) {
    $scope.videos = new Array();

    $scope.searchVideos = function() {
        var API_KEY = "AIzaSyDLvaq9d36gnPWHj4edpgy8nMKAVeo8IIw";
        var searchText = document.getElementById("searchText").value;

        $http.get('https://www.googleapis.com/youtube/v3/search', {
            params: {
                key: API_KEY,
                type: 'video',
                maxResults: '10',
                part: 'snippet',
                q: searchText}
        }).then(successCallback, errorCallback);

        function successCallback(response){

            console.log(JSON.stringify(response.data.items[0]));
            //success code
            if (response.data.length === 0) {
                alert("No results were found!");
            }
            else {
                $scope.videos = response.data.items;
                console.log($scope.videos);
            }
        }
        function errorCallback(response){
            //error code
            alert("Error while fetching data!");
        }
    };

    $scope.getVideoUrl = function(src) {
        return "https://www.youtube.com/embed/" + src;
    };

});
