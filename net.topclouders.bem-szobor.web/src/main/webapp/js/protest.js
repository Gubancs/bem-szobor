		google.load('visualization', '1', {
			'packages' : [ 'geomap' ]
		});
		google.setOnLoadCallback(drawMarkersMap);

		function drawMarkersMap() {
			var data = google.visualization.arrayToDataTable([
					[ 'Country', 'Valami' ], [ 'England', 2761477 ],
					[ 'Hungary', 2761477 ], [ 'Austria', 959574 ],
					[ 'Germany', 907563 ], [ 'Finnland', 655875 ],
					[ 'Brazil', 655875 ], [ 'Croatia', 655875 ] ]);

			var options = {
				language : "hu",
				displayMode : 'region',
				colorAxis : {
					colors : [ 'yellow', 'red' ]
				}
			};

			var chart = new google.visualization.GeoChart(document
					.getElementById('chart_div'));
			chart.draw(data, options);

			google.visualization.events.addListener(chart, 'regionClick',
					function() {
						var selection = chart.getSelection();

						var data = google.visualization
								.arrayToDataTable([ [ 'City', 'Valami' ],
										[ 'Budapest', 2761477 ],
										[ 'Debrecen', 2761477 ],
										[ 'Esztergom', 959574 ],
										[ 'Békéscsaba', 907563 ],
										[ 'Körmend', 655875 ],
										[ 'Komló', 655875 ],
										[ 'Zalaegerszeg', 655875 ],
										[ 'Pilismarót', 345345 ],
										[ 'Borota', 2345345 ] ]);

						var options = {
							language : "hu",
							region : 'HU',
							displayMode : 'markers',
							colorAxis : {
								colors : [ 'yellow', 'red' ]
							}
						};

						chart.draw(data, options);

					});

		};


      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Életkor', 'Férfi', 'Nő'],
          ['18',  100,      45],
          ['19',  120,      400],
          ['20',  130,      400],
          ['21',  140,      400],
          ['22',  180,      400],
          ['23',  200,      400],
          ['24',  240,      400],
          ['25',  250,      400],
          ['26',  250,      400],
          ['27',  250,      400],
          ['28',  210,      400],
          ['29',  300,      400],
          ['30',  310,      400],
          ['31',  350,      400],
          ['32',  310,      400],
          ['33',  350,      400],
          ['34',  400,      400],
          ['35',  460,      400],
          ['36',  450,      400],
          ['37',  500,      400],
          ['38',  550,      400],
          ['39',  463,      400],
          ['40',  444,      400],
          ['41',  450,      400],
          ['42',  430,      400],
          ['43',  400,      400],
          ['44',  250,      400],
          ['45',  200,      400],
          ['46',  260,      400],
          ['47',  200,      400],
          ['48',  160,      400],
          ['49',  130,      400],
          ['50',  120,      400],
          ['51',  110,      400],
          ['52',  100,      400],
          ['53',  50,      400],
          ['54',  50,      400],
          ['55',  50,      400],
          ['56',  50,      400],
          ['57',  50,      400],
          ['58',  70,      400],
          ['59',  60,      400],
          ['60',  50,      400]
          
        ]);

        var options = {
          hAxis: {title: 'Életkor', titleTextStyle: {color: '#5a5a5a'}},
          vAxis: {minValue: 0},

          fontName: 'Helvetica',
          colors: ['#428bca','#dc3912']
          
        };

        var chart = new google.visualization.AreaChart(document.getElementById('agechartstat_div'));
        chart.draw(data, options);
        
        var chart = new google.visualization.AreaChart(document.getElementById('agechartstat_div2'));
        chart.draw(data, options);
      }

      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Gender', 'Number'],
          ['Férfi',   234234],
          ['Nő',      182342]
        ]);

        var options = {
          fontName: 'Helvetica',
          is3D: true,
          colors: ['#428bca','#dc3912']
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }

     google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Type', 'number'],
          ['Tüntetők',     154000],
          ['Ellentüntetők',      24000],
          ['Nézelődők',  13000]
        ]);

        var options = {

          fontName: 'Helvetica',
          pieHole: 0.4
        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        chart.draw(data, options);
      }
 