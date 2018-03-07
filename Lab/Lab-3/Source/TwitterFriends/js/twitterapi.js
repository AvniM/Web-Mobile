var Twitter = require('twitter');
var fs = require('fs');

var client = new Twitter({
    consumer_key: "bR6Laeo9MOxlvqCVNvBlwPuvn",
    consumer_secret: "fmjeR54JsgF5yQouBBcmubWZCtGn602zNy8G2KEJFZk9G3Ahli",
    access_token_key: "712180562-wnFa9ahIaiR7mFZrHyodaOmYepgl0cL2Rsr2bGfs",
    access_token_secret: "GHYZH6CpQouvUS3EeRSbrGMOtxHPkBfLM9r6dvX9MYHAW"
});

var searchCriteria = "IndianExpress";
var params = {screen_name: searchCriteria,
    count: 3};

var dict = {name: searchCriteria, friend: []};

client.get('friends/list', params, function(error, tweets, response) {
    if (!error) {
        var result = [];
        for (i = 0; i < tweets.users.length; i++) {
            result.push(tweets.users[i].screen_name);
        }
        dict.friend = result;
        console.log(dict);
    }
});