var ctx = document.getElementById('myChart').getContext('2d');
var chart = new Chart(ctx, {
	type: 'bar',
	data: {
		labels: ['RTX2070 SUPER', 'RX580', 'GTX1080', 'RTX2060', 'GTX1660Ti', 'GTX1650', 'GTX1070', 'GTX1050', 'GTX1050Ti', 'GTX1060'],
		datasets: [{
			label: 'AMD',
			backgroundColor: 'red',
			borderColor: 'red',
			data: [0, 47, 0, 0, 0, 0, 0, 0, 0, 0]
		}, {
			label: 'NVIDIA',
			backgroundColor: 'green',
			borderColor: 'green',
			data: [100, 0, 89, 75, 64, 36, 68, 23, 26, 48]
		}]
	},
	options: {
		scales: {
			xAxes: [{
				stacked: true,
			}],
			yAxes: [{
				stacked: true,
			}]
		}
	}
});
