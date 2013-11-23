
// download protests TODO remove it
function downloadProtest(serviceUrl) {

	var result = [ {
		"date" : "2007-08-31T16:47+00:00",
		"desc" : "valami",
		"location" : "8010 Graz",
		"people" : "22323",
		"protest_id" : "22323"
	}, {
		"date" : "2008-08-31T16:47+00:00",
		"desc" : "valami2",
		"location" : "8010 Graz",
		"people" : "11111",
		"protest_id" : "22323"
	}, {
		"date" : "2009-08-31T16:47+00:00",
		"desc" : "valami3",
		"location" : "8010 Graz",
		"people" : "23423",
		"protest_id" : "22323"
	}, {
		"date" : "2010-08-31T16:47+00:00",
		"desc" : "valami4",
		"location" : "8010 Graz",
		"people" : "44344",
		"protest_id" : "22323"
	}, {
		"date" : "2011-08-31T16:47+00:00",
		"desc" : "valami5",
		"location" : "8010 Graz",
		"people" : "34444",
		"protest_id" : "22323"
	}, {
		"date" : "2012-08-31T16:47+00:00",
		"desc" : "valami6",
		"location" : "8010 Graz",
		"people" : "67777",
		"protest_id" : "22323"
	} ];

	return result;
}

function updateProtests(serviceUrl, tableid) {
	var results = downloadProtest(serviceUrl);
	$(tableid).find("tbody").empty();
	$.each(results, function(index, value) {
		var row = '<tr><td>' + value["date"] + '</td><td>' + value["desc"]
				+ '</td><td>' + value["location"] + '</td><td>'
				+ value["people"]
				+ ' fő&nbsp;&nbsp;&nbsp;&nbsp;<a href="/protest/'
				+ value["protest_id"] + '">Tovább »</a></td><</tr>';
		var selector = tableid + ' > tbody:last';
		$(selector).append(row);
	});
}

$(".clickrow").on("click", function() {
	window.location = this.attr('href');
	alert(this.attr('href'));
});

$("#d1").on("click", function() {
	updateProtests("s1", "#tprotests1");
});

$("#d2").on("click", function() {
	updateProtests("s2", "#tprotests2");
});

$("#d3").on("click", function() {
	updateProtests("s3", "#tprotests3");
});

$(document).ready(function() {

	$('.carousel').carousel({
		interval : 15000,
		pause : "false"
	});
});

$(document).ready(function() {
	updateProtests("s1", "#tprotests1");
});