var SERVER_URL = "http://localhost:8080/bemszobor";

var test = {
	"V" : 100000,
	"CD" : 56000,
	"D" : 200000
};

var test2 = [ {
	"age" : 18,
	"f" : 2,
	"m" : 0
}, {
	"age" : 19,
	"f" : 0,
	"m" : 2
}, {
	"age" : 30,
	"f" : 1,
	"m" : 4
}, {
	"age" : 32,
	"f" : 0,
	"m" : 1
} ];

function getDemonstrationId() {
	return 3;// TODO modify
}

// Load the Visualization API and the piechart package.
google.load('visualization', '1', {
	'packages' : [ 'corechart' ]
});

// Set a callback to run when the Google Visualization API is loaded.
google.setOnLoadCallback(updatechartsonFirstTab);

function getTotalData() {
	return test;// TODO modify

	/*
	 * var serviceUrl = SERVER_URL +"/stat/total/"+getDemonstrationId();
	 * 
	 * $.getJSON(serviceUrl,function(result){ updatetotalSection(result[0]);
	 * 
	 * });
	 * 
	 */
}

function getData4FirstTab() {
	return test2;// TODO modify

	/*
	 * var serviceUrl = SERVER_URL +"/stat/total/"+getDemonstrationId();
	 * 
	 * $.getJSON(serviceUrl,function(result){ updatetotalSection(result[0]);
	 * 
	 * });
	 * 
	 */
}
var total_ageChart;
var total_genderChart;

function updatechartsonFirstTab() {
	
	total_ageChart = new google.visualization.AreaChart(document
			.getElementById('total_areachart_age'));
	
	total_genderChart = new google.visualization.PieChart(document
			.getElementById('total_piechart_gender'));

	var result = getData4FirstTab();
	drawAgeChart(result, total_ageChart);
	drawGenderChart(result, total_genderChart);
}


function drawGenderChart(list, chart) {
	var female = 0;
	var male = 0;
	for (var i = 0; i < list.length; i++) {
		female = female + list[i]["f"];
		male = male + list[i]["m"];
	}

	var data = google.visualization.arrayToDataTable([ [ 'Nem', 'Fő' ],
			[ 'Férfi', male ], [ 'Nő', female ] ]);

	var options = {
		fontName : 'Helvetica',
		is3D : true,
		colors : [ '#428bca', '#dc3912' ]
	};

	chart.draw(data, options);
}



function drawAgeChart(list, chart) {
	var data = new google.visualization.DataTable();
	data.addColumn('number', 'Életkor');
	data.addColumn('number', 'Férfi');
	data.addColumn('number', 'Nő');
	for (var i = 0; i < list.length; i++) {
		data.addRow([ list[i]["age"], list[i]["m"], list[i]["f"] ]);
	}

	var options = {
		hAxis : {
			title : 'Életkor',
			titleTextStyle : {
				color : '#5a5a5a'
			}
		},
		vAxis : {
			minValue : 0
		},

		fontName : 'Helvetica',
		colors : [ '#428bca', '#dc3912' ]

	};

	chart.draw(data, options);
}

function updadateTotalTab(){
	updatetotalSection(getTotalData());
	var data = getData4FirstTab();
	drawAgeChart(data, total_ageChart);
	drawGenderChart(data, total_genderChart);
}

function updatetotalSection(value) {

	var no_of_counterprotesters = value["CD"];
	var no_of_protesters = value["D"];
	var no_of_visitors = value["V"];
	var total = no_of_counterprotesters + no_of_protesters + no_of_visitors;

	$("#no_protesters").text(no_of_protesters);
	$("#no_counterprotesters").text(no_of_counterprotesters);
	$("#no_visitors").text(no_of_visitors);
	$("#no_total").text(total);
}

var c = 33;
function start() {
	updatetotalSection(getTotalData());
	setInterval(function(){
		updadateTotalTab();
		c = c+1;
		test2.push({"age" : c,
		        	"f" : 10,
		        	"m" : 13});
		
		test = {
				"V" : test["V"]+1000,
				"CD" :  test["CD"]+1000,
				"D" : test["D"]+2000
			};
	}, 3000);
}

start();

$(document).ready(function() {

});
