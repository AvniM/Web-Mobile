//toggle between login and register
function toggle() {
    var loginBlock = document.getElementById("login");
    var registerBlock = document.getElementById("register");

    if (loginBlock.style.display === "none") {
        loginBlock.style.display = "block";
        registerBlock.style.display = "none";
    } else {
        loginBlock.style.display = "none";
        registerBlock.style.display = "block";
    }
}

var news_url = "https://newsapi.org/v2/sources?";
var top_headlines_url = "https://newsapi.org/v2/top-headlines?";
var developer_key = "50f74aaace1c4c81a3b32c63ef8cf5d7";

function findResources() {
    $.ajax({
        url: news_url+"apiKey="+developer_key
    }).done(function(response) {
        var sources = response.sources;
        if (sources.length !== 0) {
            var sourceDD = document.getElementById("sourceDropdown")
            sourceDD.options[sourceDD.options.length] = new Option('Select Source', '');
            for (var i = 0; i < sources.length; i++) {
                sourceDD.options[sourceDD.options.length] = new Option(sources[i].name, sources[i].id);
            }
        } else {
            alert("Unable to load source list!!!");
        }
    });
}

function searchNews(){
    var topic = document.getElementById("topicTextbox").value;
    var selectedSource = document.getElementById("sourceDropdown").value;
    // check if user has entered both topic and source or either one.
    if (topic && selectedSource) {
        var parameters = "q=" + topic + "&sources=" + selectedSource;
    } else if (topic) {
        var parameters = "q=" + topic;
    } else if (selectedSource) {
        var parameters = "sources=" + selectedSource;
    } else {
        displayError();
        return;
    }
    // create request url
    var url = top_headlines_url + parameters + "&apiKey=" +developer_key;
    $.ajax({
        url: url
    }).done( function(response){
        var articles = response.articles;
        if (articles.length !== 0) {
            var output = "";
            for (var i = 0; i < articles.length; i++) {
                output += "<h3>" + articles[i].title + "</h3>";
                output += "<p>" + (new Date(articles[i].publishedAt)).toUTCString() + "</p>";
                output += "<a href='" + articles[i].url + "' target='_blank'>" + "<img src='"
                    + articles[i].urlToImage + "' class='img-responsive img-thumbnail'></a>";
                output += "<hr>";
            }
            displaySuccess(output);
        }else{
            displayNoResultError();
        }
    });

}

function displayError() {
    var output = "<h5> Please select atleast one criteria.</h5>";
    document.getElementById("output").innerHTML = output;
}

function displayNoResultError() {
    var output = "<h5> No results found!</h5>";
    document.getElementById("output").innerHTML = output;
}

function displaySuccess(output) {
    document.getElementById("output").innerHTML = output;
}