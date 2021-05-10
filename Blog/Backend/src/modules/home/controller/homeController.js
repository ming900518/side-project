app.factory('homeService', function(baseHttp) {
	return {

	}
})
app.controller('homeController', function($timeout, $rootScope, $scope, $window, $location, $http, $filter, $state, $modal, homeService, util, $sce) {

	var status_color = ['#68BC31', '#2091CF', '#AF4E96', '#008080', '#00FFFF', '#808000'];

	var data1 = [
		{ label: 1, data: 250, color: status_color[0] },
		{ label: 2, data: 200, color: status_color[1] },
		{ label: 3, data: 100, color: status_color[2] }
		];

	var data2 = [
		{ label: 1, data: 250, color: status_color[0] },
		{ label: 2, data: 200, color: status_color[1] },
		{ label: 3, data: 100, color: status_color[2] },
		{ label: 4, data: 150, color: status_color[3] },
		{ label: 5, data: 300, color: status_color[4] },
		{ label: 6, data: 50, color: status_color[5] }
		];

	var placeholder2 = $('#piechart-placeholder2').css({
		'width': '90%',
		'min-height': '150px'
	});
	var placeholder1 = $('#piechart-placeholder1').css({
		'width': '90%',
		'min-height': '150px'
	});

	function drawPieChart(placeholder, data, position) {
		$.plot(placeholder, data, {
			series: {
				pie: {
					show: true,
					tilt: 0.8,
					highlight: {
						opacity: 0.25
					},
					stroke: {
						color: '#fff',
						width: 2
					},
					startAngle: 2
				}
			},
			legend: {
				show: true,
				position: position || "ne",
				labelBoxBorderColor: null,
				margin: [-30, 15]
			},
			grid: {
				hoverable: true,
				clickable: true
			}
		})
	}

	drawPieChart(placeholder1, data1);
	drawPieChart(placeholder2, data2);

	placeholder1.data('chart', data1);
	placeholder1.data('draw', drawPieChart);
	placeholder2.data('chart', data2);
	placeholder2.data('draw', drawPieChart);


});