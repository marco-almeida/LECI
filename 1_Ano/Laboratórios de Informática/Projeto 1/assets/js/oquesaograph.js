var ctx = document.getElementById('oquesaochart').getContext('2d');
var chart = new Chart(ctx, {
	type: 'bar',
	data: {
		labels: ['RTX 3090', 'RADEON RX 6900XT', 'RTX 3070', 'RADEON RX 6800', 'RTX 2080', 'RADEON RX 5700XT', 'RTX 2060', 'RADEON RX 5600XT', 'GTX 1080', 'RADEON RX 470'],
		datasets: [{
			label: 'AMD',
			backgroundColor: 'red',
			borderColor: 'red',
			data: [0, 5120, 0, 3840, 0, 2560, 0, 2304, 0, 2048]
		}, {
			label: 'NVIDIA',
			backgroundColor: 'green',
			borderColor: 'green',
			data: [10496, 0, 5888, 0, 2944, 0, 1920, 0, 2560, 0]
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