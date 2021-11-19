<template>
	<div class="select-info">
		<div>
			<!-- <h3>标签:<div v-for="(item,index) in buttonLabel" :key="index">
					<div v-if="item.checked">
						{{item.name}}
					</div>
				</div>
			</h3>
			<h3>图样:{{
				
			}}</h3> -->
			<el-button type="text" @click="dialogVisible = true" >开始</el-button>
			<el-dialog title="提示" v-model="dialogVisible" width="30%" :before-close="handleClose">
				<span>这次选中的标签是</span>
				<span v-for="(item,index) in buttonLabel" :key="index">
					<span v-if="item.checked">
						{{ item.name }} &nbsp;
					</span>
				</span>
				<div>这次选中的图样是{{chartPattern}}</div>
				<template #footer>
					<span class="dialog-footer">
						<el-button @click="dialogVisible = false">取 消</el-button>
						<el-button type="primary" @click="dialogVisible = false">确 定
						</el-button>
					</span>
				</template>
			</el-dialog>
		</div>

		<!-- 	<div>

			<button @click="runProject()" class="btn">运行</button>

		</div> -->

	</div>
	<!-- <div id="main2" v-show="isClick==true"
	></div> -->
	<div id="main">
		
	</div>
</template>

<script>
	import mybus from "../mybus";
	import axios from 'axios'
	import * as echarts from 'echarts'
	export default {

		data() {
			return {
				isClick:false,
				buttonLabel: [],
				chartPattern: '',
				draw_data: [],
				dialogVisible: false,
				GenderLevelWoman: "",
				GenderLevelMan: "",
				GenderLevelX: "",
				checkTags: 'tag-allowable',
			}
		},
		methods: {
			// SetChart(){
			// 	this.GetAllGenderLevel();
			// },
// 			async SetGenderLevel() {
// 				var chartDom = document.getElementById('main');
// 				var myChart = echarts.init(chartDom);
// 				var option;
// 				option = {
// 					legend: {},
// 					tooltip: {},
// 					dataset: {
// 						source: [
// 							['product', 'Man', 'Woman', 'Unknow'],
// 							['一级', this.GenderLevelMan[0][1], this.GenderLevelWoman[0][1], this.GenderLevelX[0][1]
// 							],
// 							['二级', this.GenderLevelMan[1][1], this.GenderLevelWoman[1][1], this.GenderLevelX[1][1]
// 							],
// 							['三级', this.GenderLevelMan[2][1], this.GenderLevelWoman[2][1], this.GenderLevelX[2][1]
// 							],
// 							['四级', this.GenderLevelMan[3][1], this.GenderLevelWoman[3][1], this.GenderLevelX[3][1]
// 							],
// 							['五级', this.GenderLevelMan[4][1], this.GenderLevelWoman[4][1], this.GenderLevelX[4][1]
// 							],
// 							['六级', this.GenderLevelMan[5][1], this.GenderLevelWoman[5][1], this.GenderLevelX[5][1]
// 							],
// 							['七级', this.GenderLevelMan[6][1], this.GenderLevelWoman[6][1], this.GenderLevelX[6][1]
// 							],
// 							['八级', this.GenderLevelMan[7][1], this.GenderLevelWoman[7][1], this.GenderLevelX[7][1]
// 							],
// 							['九级', this.GenderLevelMan[8][1], this.GenderLevelWoman[8][1], this.GenderLevelX[8][1]
// 							],
// 							['十级', this.GenderLevelMan[9][1], this.GenderLevelWoman[9][1], this.GenderLevelX[9][1]
// 							],

// 						]
// 					},
// 					xAxis: {
// 						type: 'category'
// 					},
// 					yAxis: {},
// 					// Declare several bar series, each will be mapped
// 					// to a column of dataset.source by default.
// 					series: [{
// 						type: 'bar'
// 					}, {
// 						type: 'bar'
// 					}, {
// 						type: 'bar'
// 					}]
// 				};
// 				option && myChart.setOption(option);

// 			},
// 			async GetAllGenderLevel() {
// 				axios.post("http://101.34.151.80:9090/all_figure/get_userDraw", {
// 					"key1": "level-gender",
// 					"key2": "gender-level",
// 					"key_value1": 2021,
// 					"key_value2": 11
// 				}).then(res => {
// 					// console.log(res.data.data)
// 					let li = res.data.data
// 					//
// 					let man = []
// 					let woman = []
// 					let x = []
// 					let newman = []
// 					let newx = []
// 					let newwoman = []
// 					for (let i = 0; i < li.length; i++) {
// 						if (li[i].key_value2 == "女") {
// 							woman.push([li[i].key_value1, li[i].num])
// 						}
// 					}
// 					for (let i = 0; i < li.length; i++) {
// 						if (li[i].key_value2 == "男") {
// 							man.push([li[i].key_value1, li[i].num])
// 						}
// 					}
// 					for (let i = 0; i < li.length; i++) {
// 						if (li[i].key_value2 == "未知") {
// 							x.push([li[i].key_value1, li[i].num])
// 						}
// 					}

// 					//女性数据处理

// 					for (let i = 1; i < 11; i++) {
// 						newwoman.push([i, 0])
// 					}

// 					for (let i = 0; i < woman.length; i++) {
// 						for (let j = 0; j < newwoman.length; j++) {
// 							if (newwoman[j][0] == woman[i][0]) {
// 								newwoman[j][1] = woman[i][1]
// 							}
// 						}
// 					}

// 					//男性数据处理

// 					for (let i = 1; i < 11; i++) {
// 						newman.push([i, 0])
// 					}

// 					for (let i = 0; i < man.length; i++) {
// 						for (let j = 0; j < newman.length; j++) {
// 							if (newman[j][0] == man[i][0]) {
// 								newman[j][1] = man[i][1]
// 							}
// 						}
// 					}

// 					//未知性别处理

// 					for (let i = 1; i < 11; i++) {
// 						newx.push([i, 0])
// 					}

// 					for (let i = 0; i < x.length; i++) {
// 						for (let j = 0; j < newx.length; j++) {
// 							if (newx[j][0] == x[i][0]) {
// 								newx[j][1] = x[i][1]
// 							}
// 						}
// 					}
// 					this.GenderLevelWoman = newwoman
// 					this.GenderLevelMan = newman
// 					this.GenderLevelX = newx
// // console.log(this.GenderLevelMan)
// 					this.SetGenderLevel()
// 				})


// 			},

			onChange(tagListsItem) {
				if (tagListsItem.disable != true) {
					this.tagLists[tagListsItem.id - 1].checked = !tagListsItem.checked
					let num = [];
					let selectedLists = [];
					//将已选中的存入
					for (let i = 0; i < this.tagLists.length; i++) {
						if (this.tagLists[i].checked == true) {
							num.unshift(i);
							selectedLists.push(this.tagLists[i]);
							console.log(num)
						}
					}
					//如果已选中的数量为2
					if (num.length == 2) {
						//非选中的设为不可选
						for (let i = 0; i < this.tagLists.length; i++) {
							if (i != num[0] && i != num[1]) {
								this.tagLists[i].disable = true
								console.log(this.tagLists[i])
							}
						}
					} else {
						//少于2，则将所有的设为false
						for (let i = 0; i < this.tagLists.length; i++) {
							this.tagLists[i].disable = false
						}
					}
					for (let i = 0; i < this.tagLists.length; i++) {
						if (this.tagLists[i].checked == false && num.length == 2) {
							this.checkTags = 'tag-disable'
						} else {
							this.checkTags = 'tag-allowable'
						}
					}
					mybus.emit('tagListEvent', selectedLists);
				}
			},
			spliceArray(num1, num2) {
				console('hellow worlds')
				this.buttonLabel.splice(num1, num2)
			},
			confirmEvent() {
				console.log('confirm!')
			},
			cancelEvent() {
				console.log('cancel!')
			},
			runProject() {
				// var data = {
				// 	"key1": "level-gender",
				// 	"key2": "gender-level",
				// 	"key_value1": 2020,
				// 	"key_value2": 10
				// }
				// axios.post('http://mockjs.xiaoyaoji.cn/mock/1jVeX6aHqfA/all_figure/get_userDraw',data).then((res) => {
				// 	this.draw_data=res.data.data;
				// 	for(var i=0;i<this.draw_data.length;i++)
				// 	// {

				// 	// }
				// 	})
			},
			handleClose(done) {
				this.$confirm('确认关闭？')
					.then((_) => {
						done()
					})
					.catch((_) => {})
			},
		},
		mounted() {
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById('main'));
			// 基于准备好的dom，初始化echarts实例
			// var myChart1 = echarts.init(document.getElementById('main1'));
			window.onresize = function() {
				myChart.resize();
				// myChart1.resize();
			};
			// 绘制图表
			myChart.setOption({
				title: {
					text: "图表",
					textAlign: "center",
					left: "center"
				},
				tooltip: {},
				legend: {
					width: '100%',
					height: '100%',
					itemHeight: '100%',
					itemWidth: '100%',
				},
				xAxis: {
					data: ['lv1', 'lv2', 'lv3', 'lv4', 'lv5', 'lv6']
				},
				yAxis: {},
				series: [{
					name: '销量',
					type: 'bar',
					data: [5, 20, 36, 10, 10, 20]
				}]
			});
			// 绘制图表
// 			myChart1.setOption({
// 				title: {
// 					text: "图表",
// 					textAlign: "center",
// 					left: "center"
// 				},
// 				tooltip: {},
// 				legend: {
// 					width: '100%',
// 					height: '100%',
// 					itemHeight: '100%',
// 					itemWidth: '100%',
// 				},
// 				xAxis: {type: 'category', data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']},yAxis: {type: 'value'},
// 				series: [{data: [150, 230, 224, 218, 135, 147, 260],type: 'line'
// }
// ]
// 			});
			mybus.on('tagListEvent', tagListsItem => {
				// console.log(tagListsItem);
				// this.buttonLabel.splice(0);
				// for(let i = 0;i < tagListsItem.length;i++){
				// this.buttonLabel.push(tagListsItem[i].name)//标签信息
				// }
				// this.buttonLabel = []
				// this.buttonLabel.push(tagListsItem);
				// console.log(this.buttonLabel)
				this.buttonLabel = tagListsItem;
			});
			mybus.on('showPatternImgInfo', patternName => {
				this.chartPattern = patternName //图表信息
			});
		}
	}
</script>

<style scoped>
	h3 {
		margin: 0px
	}

	#main {
		height: 1600px;
		width: 100%;
		background-color: #f5f6fa;
	}

	img {
		width: 300px !important;
	}

	.select-info {
		height: 200px;
		width: 1800px;
		position: absolute;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		background-color: transparent;
		z-index: 2;
	}

	button {
		position: relative;
		padding: 10px 30px;
		margin: 0;
		color: #21ebff;
		text-decoration: none;
		font-size: 20px;
		text-transform: uppercase;
		transition: 0.5s;
		overflow: hidden;
		-webkit-box-reflect: below 1px linear-gradient(transparent, #0003);
	}

	button:hover {
		background: #21ebff;
		color: #111;
		box-shadow: 0 0 50px #21ebff;
		transition-delay: 0.5s;
	}

	button:nth-child(1) {
		filter: hue-rotate(115deg);
	}

	button:nth-child(3) {
		filter: hue-rotate(270deg);
	}

	button::before {
		content: '';
		position: absolute;
		top: 0;
		left: 0;
		width: 10px;
		height: 10px;
		border-top: 2px solid #21ebff;
		border-left: 2px solid #21ebff;
		transition: 0.5s;
		transition-delay: 0.5s;
	}

	button:hover::before {
		width: 100%;
		height: 100%;
		transition-delay: 0s;
	}

	button::after {
		content: '';
		position: absolute;
		right: 0;
		bottom: 0;
		width: 10px;
		height: 10px;
		border-bottom: 2px solid #21ebff;
		border-right: 2px solid #21ebff;
		transition: 0.5s;
		transition-delay: 0.5s;
	}

	button:hover::after {
		width: 100%;
		height: 100%;
		transition-delay: 0s;
	}

	.btn {
		width: 150px;
		height: 50px;
		margin-top: 25px;
		border-radius: 0%;
		/* width:0;
			height:0;
			border-width: 50px;
			border-style: solid;
			border-top-color: transparent;
			border-left-color: transparent;
			border-right-color: transparent; */
	}

	/* .btn {
			background: #eb94d0;
		/* 创建渐变 */
	/* background-image: -webkit-linear-gradient(top, #eb94d0, #2079b0);
			background-image: -moz-linear-gradient(top, #eb94d0, #2079b0);
			background-image: -ms-linear-gradient(top, #eb94d0, #2079b0);
			background-image: -o-linear-gradient(top, #eb94d0, #2079b0);
			background-image: linear-gradient(to bottom, #eb94d0, #2079b0); */
	/* 给按钮添加圆角 */
	/* -webkit-border-radius: 28;
			-moz-border-radius: 28;
			border-radius: 28px;
			text-shadow: 3px 2px 1px #9daef5;
			-webkit-box-shadow: 6px 5px 24px #666666;
			-moz-box-shadow: 6px 5px 24px #666666;
			box-shadow: 6px 5px 24px #666666;
			font-family: Arial;
			color: #fafafa;
			font-size: 27px;
			padding: 19px;
			text-decoration: none;
		}
	*/
	/* 悬停样式 */
	/* .btn:hover {
			background: #2079b0;
			background-image: -webkit-linear-gradient(top, #2079b0, #eb94d0);
			background-image: -moz-linear-gradient(top, #2079b0, #eb94d0);
			background-image: -ms-linear-gradient(top, #2079b0, #eb94d0);
			background-image: -o-linear-gradient(top, #2079b0, #eb94d0);
			background-image: linear-gradient(to bottom, #2079b0, #eb94d0);
			text-decoration: none;
		} */
</style>
