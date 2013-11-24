var SERVER_URL="http://localhost:8080/bemszobor"
function updateProtests(serviceUrl, tableid) {
	//var results = downloadProtest(serviceUrl);
	//doupdate(results);
	$.getJSON(serviceUrl,function(result){
		$(tableid).find("tbody").empty();
		$.each(result, function(index, value) {
			var row = '<tr><td>' + value["date"] + '</td><td>' + value["desc"]
					+ '</td><td>' + value["location"] + '</td><td>'
					+ value["people"]
					+ ' fő&nbsp;&nbsp;&nbsp;&nbsp;<a href="/protest/'
					+ value["protest_id"] + '">Tovább »</a></td><</tr>';
			var selector = tableid + ' > tbody:last';
			//alert("row:" +row);
			$(selector).append(row);
		});
	  });
	
}

$(".clickrow").on("click", function() {
	window.location = this.attr('href');
	alert(this.attr('href'));
});

function updateFutureProtests() {
	updateProtests(SERVER_URL+"/protests/active", "#tprotests1");
}

$("#d1").on("click", function() {
	updateFutureProtests();
});

$("#d2").on("click", function() {
	updateProtests(SERVER_URL+"/protests/active", "#tprotests2");
});

$("#d3").on("click", function() {
	updateProtests(SERVER_URL+"/protests/active", "#tprotests3");
});

$(document).ready(function() {
	$('.carousel').carousel({
		interval : 15000,
		pause : "false"
	});
});

$(document).ready(function() {
	updateFutureProtests();
});