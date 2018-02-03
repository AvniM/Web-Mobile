var courses = [
    ["Instructor Based", "1001", "Ut Pharetra", "Turpis egestas pretium aenean pharetra magna. Ut diam quam nulla porttitor massa id.", "3", "Phasellus Vestibulum", "TR 10am-12pm", "Tempus 106", "5", "3.5"],
    ["Instructor Based", "1002", "Nibh Mauris", "Egestas quis ipsum suspendisse ultrices. Turpis nunc eget lorem dolor sed viverra ipsum nunc", "3", "Nullam Molestie", "TW 10am-12pm", "Horsit 211", "12", "4.2"],
    ["Instructor Based", "1003", "Curabitur Ipsum", "Nisl rhoncus mattis rhoncus urna neque viverra. Sed vulputate odio ut enim blandit volutpat maecenas.", "3", "Nullam non Nisi", "MW 11am-1pm", "Aenean 211", "20", "4.8"],
    ["Online", "1004", "Non Sodales Neque", "Vitae tempus quam pellentesque nec. Egestas sed sed risus pretium quam. Nulla porttitor massa id neque.", "3", "Mauris Nunc", "TR 11am-1pm", "Alena 211", "30", "4.9"],
    ["Online", "1005", "Nisl Rhoncus", "Non diam phasellus vestibulum lorem. Pretium aenean pharetra magna ac placerat", "3", "Praesent Semper", "MR 1pm-3pm", "Mustir 211", "40", "5.0"],
    ["Instructor Based", "1006", "Cursus Mattis", "Pretium quam vulputate dignissim suspendisse in est ante.", "1", "Amet Consectetur", "MW 4pm-6pm", "Meana 211", "15", "2.1"],
    ["Instructor Based", "1007", "Cras Sed Felis", "Lacus vestibulum sed arcu non odio. Vel fringilla est ullamcorper eget nulla. Pellentesque habitant morbi tristique senectus et.", "1", "Tincidunt Eget", "F 12pm-3pm", "Dolor 211", "20", "1.5"],
    ["Online", "1008", "Phasellus Vestibulum", "Sed blandit libero volutpat sed cras.", "3", "Amerare Molestie", "S 8am-11am", "Aenean 211", "12", "2.2"],
    ["Instructor Based", "1009", "Inucmenay Toute", "Nibh sed pulvinar proin gravida. A lacus vestibulum sed arcu non. Libero justo laoreet sit amet. Bibendum ut tristique et egestas. ", "3", "Tristique senectu", "TF 6pm-8pm", "Dictumst 211", "60", "1.0"],
    ["Instructor Based", "1010", "Jamies Laud", "Vivamus blandit risus vel enim rutrum dictum. Mauris sit amet viverra libero.", "1", "Jamiet Aliguet", "TR 10am-12pm", "Aliquet 211", "45", "2.4"]];
window.localStorage.setItem("allCourses", courses); // Saving

// Function to shuffle an array
// Can be used for random selection
function shuffle (array) {
    var i = 0
        , j = 0
        , temp = null;

    for (i = array.length - 1; i > 0; i -= 1) {
        j = Math.floor(Math.random() * (i + 1));
        temp = array[i];
        array[i] = array[j];
        array[j] = temp
    }
}

function searchCourses() {
    var o1 = document.getElementById("term");
    var o2 = document.getElementById("level");
    var o3 = document.getElementById("career");

    if (o1.value == "" || o2.value == "" || o3.value == "" ){
        //If the "Please Select" option is selected display error.
        alert("Please select an option!");
    }
    else {
        //show the results
        var x = document.getElementById("coursesTable");
        x.style.visibility = 'visible';

        // On clicking search, get random 3 courses
        var shuffledCourses = courses.slice(0);
        shuffle(shuffledCourses);
        var newCourses = shuffledCourses.slice(0, 3);

        // delete previous results, if any
        var rows = document.getElementById("coursesTable").getElementsByTagName("tr").length;
        if(rows > 1) {
            document.getElementById("coursesTable").deleteRow(1);
            document.getElementById("coursesTable").deleteRow(1);
            document.getElementById("coursesTable").deleteRow(1);
        }

        // Populate table with random courses
        var table = document.getElementById("coursesTable");
        for (var i = 0; i < newCourses.length; i++) {
            // create a new row
            var newRow = table.insertRow(table.length);
            for (var j = 0; j < newCourses[i].length; j++) {

                if(j == 0) {
                    if(newCourses[i][0] == "Instructor Based")
                        newCourses[i][0] = "<img src=\"./images/classroomcourse_icon.png\" alt=\"\">";
                    else
                        newCourses[i][0] = "<img src=\"./images/onlinecourse_icon.png\" alt=\"\">";
                }
                if(j <= 1) {
                    var courseNo = newCourses[i][1];
                    cellValue = '<a href=./courseRegister.html?courseNo=' + courseNo + ' target=\"_blank\">' + newCourses[i][j] + '</a>';
                }
                else{
                    cellValue = newCourses[i][j];
                }

                // create a new cell
                var cell = newRow.insertCell(j);
                // add value to the cell
                cell.innerHTML = cellValue;
            }
        }
    }
}