var available = "";

window.onload = function() {
    var allCourses = window.localStorage.getItem("allCourses").split(',');
    var courseNo = document.URL.substring(parent.document.URL.indexOf('=') + 1, parent.document.URL.length);
    var col = courseNo - 1001;

    //populate courseDetails table
    var courseDetails = ["Open", courseNo, allCourses[2+col*10], allCourses[3+col*10], allCourses[4+col*10], "On Campus", allCourses[0+col*10]];
    var table1 = document.getElementById("courseDetails");

    var newRow1 = table1.insertRow(table1.length);
    for (var i = 0; i < courseDetails.length; i++) {
        var cell = newRow1.insertCell(i);
        cell.innerHTML = courseDetails[i];
    }

    //populate otherDetails table
    var otherDetails = [allCourses[5+col*10], allCourses[6+col*10], allCourses[7+col*10], col*10, allCourses[8+col*10], 0];
    available = allCourses[8+col*10];
    var table2 = document.getElementById("otherDetails");

    var newRow2 = table2.insertRow(table2.length);
    for (var j = 0; j < otherDetails.length; j++) {
        var cell = newRow2.insertCell(j);
        cell.innerHTML = otherDetails[j];
    }

};

function registerCourse() {
    var allCourses = window.localStorage.getItem("allCourses").split(',');
    alert("You are enrolled in the course!");
    var table2 = document.getElementById("otherDetails");
    available = available - 1;
    table2.rows[1].cells[4].innerHTML = available;

    //disable button
    document.getElementById("registerBtn").disabled = true;
}
