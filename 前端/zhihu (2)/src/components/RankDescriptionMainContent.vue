<template>
	<div id='main' style="height:100%"></div>
</template>

<script>
	import * as echarts from 'echarts';
	import $ from 'jquery'
	export default {
		name: 'RankDescriptionMainContent',
		mounted() {
			var ROOT_PATH =
				'https://cdn.jsdelivr.net/gh/apache/echarts-website@asf-site/examples';

			var chartDom = document.getElementById('main');
			// chartDom.style.height=window.innerHeight+'px';
			var myChart = echarts.init(chartDom);
			myChart.resize();
			var option;

			const updateFrequency = 2000;
			const dimension = 0;
			const countryColors = {
				Australia: '#00008b',
				Canada: '#f00',
				China: '#ffde00',
				Cuba: '#002a8f',
				Finland: '#003580',
				France: '#ed2939',
				Germany: '#000',
				Iceland: '#003897',
				India: '#f93',
				Japan: '#bc002d',
				'North Korea': '#024fa2',
				'South Korea': '#000',
				'New Zealand': '#00247d',
				Norway: '#ef2b2d',
				Poland: '#dc143c',
				Russia: '#d52b1e',
				Turkey: '#e30a17',
				'United Kingdom': '#00247d',
				'United States': '#b22234'
			};
			$.when(
				$.getJSON('https://cdn.jsdelivr.net/npm/emoji-flags@1.3.0/data.json'),
				$.getJSON(ROOT_PATH + '/data/asset/data/life-expectancy-table.json')
			).done(function(res0, res1) {
				const flags = res0[0];
				const data = res1[0];
				const years = [];
				for (let i = 0; i < data.length; ++i) {
					if (years.length === 0 || years[years.length - 1] !== data[i][4]) {
						years.push(data[i][4]);
					}
				}

				function getFlag(countryName) {
					if (!countryName) {
						return '';
					}
					return (
						flags.find(function(item) {
							return item.name === countryName;
						}) || {}
					).emoji;
				}
				let startIndex = 10;
				let startYear = years[startIndex];
				option = {
					grid: {
						top: 10,
						bottom: 30,
						left: 150,
						right: 80,
						width:'auto',
						height:'auto'
					},
					xAxis: {
						max: 'dataMax',
						axisLabel: {
							formatter: function(n) {
								return Math.round(n) + '';
							}
						}
					},
					dataset: {
						source: data.slice(1).filter(function(d) {
							return d[4] === startYear;
						})
					},
					yAxis: {
						type: 'category',
						inverse: true,
						max: 10,
						axisLabel: {
							show: true,
							fontSize: 14,
							formatter: function(value) {
								return value + '{flag|' + getFlag(value) + '}';
							},
							rich: {
								flag: {
									fontSize: 25,
									padding: 5
								}
							}
						},
						animationDuration: 300,
						animationDurationUpdate: 300
					},
					series: [{
						realtimeSort: true,
						seriesLayoutBy: 'column',
						type: 'bar',
						itemStyle: {
							color: function(param) {
								return countryColors[param.value[3]] || '#5470c6';
							}
						},
						encode: {
							x: dimension,
							y: 3
						},
						label: {
							show: true,
							precision: 1,
							position: 'right',
							valueAnimation: true,
							fontFamily: 'monospace'
						}
					}],
					// Disable init animation.
					animationDuration: 0,
					animationDurationUpdate: updateFrequency,
					animationEasing: 'linear',
					animationEasingUpdate: 'linear',
					graphic: {
						elements: [{
							type: 'text',
							right: 160,
							bottom: 60,
							style: {
								text: startYear,
								font: 'bolder 80px monospace',
								fill: 'rgba(100, 100, 100, 0.25)'
							},
							z: 100
						}]
					}
				};
				// console.log(option);
				myChart.setOption(option);
				myChart.resize();
				for (let i = startIndex; i < years.length - 1; ++i) {
					(function(i) {
						setTimeout(function() {
							updateYear(years[i + 1]);
						}, (i - startIndex) * updateFrequency);
					})(i);
				}

				function updateYear(year) {
					let source = data.slice(1).filter(function(d) {
						return d[4] === year;
					});
					option.series[0].data = source;
					option.graphic.elements[0].style.text = year;
					myChart.setOption(option);
					myChart.resize();
				}
			});
			option && myChart.setOption(option);
			myChart.resize();
			
			// //假设实例的名字是myChart,在echats配置完实例以后,配置下面几行代码即可
			// window.onresize = function() {
			// 	myChart.resize();
			// }
			
		}
	}
</script>

<style scoped>
	/* #main {
		height: 100%;
		width: 100%;
		background-color: #42B983;
	} */

	/deep/ canvas {
		height: 100% !important;
	}
</style>
