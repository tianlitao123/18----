<template>
	<div id='top-Navigator'>

		<el-check-tag v-for="tagListsItem in tagLists " :key="tagListsItem.id" @click="onChange(tagListsItem)"
			:checked="tagListsItem.checked" :class="checkTags">
			{{tagListsItem.name}}
		</el-check-tag>
		<!-- <el-check-tag @change="onChange" :checked="checked1">性别</el-check-tag>
		<el-check-tag @change="onChange" :checked="checked2">等级</el-check-tag>
		<el-check-tag @change="onChange" :checked="checked3">地点</el-check-tag>
		<el-check-tag v-model:checked="checked">职业</el-check-tag>
		<el-check-tag>回答数量</el-check-tag>
		<el-check-tag>问题数量</el-check-tag>
		<el-check-tag>关注数量</el-check-tag>
		<el-check-tag>粉丝数量</el-check-tag>
		<el-check-tag>关注问题数量</el-check-tag>
		<el-check-tag>关注话题数量</el-check-tag>
		<el-check-tag>专栏数量</el-check-tag>
		<el-check-tag>关注文件夹数量</el-check-tag>
		<el-check-tag>投票数量</el-check-tag> -->

	</div>
</template>

<script>
	import mybus from "../mybus";
	import axios from 'axios'
	import * as echarts from 'echarts'
	export default {
		name: 'ChartNavigatorTop',
		data() {
			return {
				
				GenderLevelWoman:"",
				GenderLevelMan:"",
				GenderLevelX:"",
				checkTags: 'tag-allowable',
				tagLists: [{
						id: 1,
						name: '性别',
						checked: false,
						disable: false
					}, {
						id: 2,
						name: '等级',
						checked: false,
						disable: false
					}, {
						id: 3,
						name: '专栏数量',
						checked: false,
						disable: false
					}, {
						id: 4,
						name: '职业',
						checked: false,
						disable: false
					}, {
						id: 5,
						name: '回答数量',
						checked: false,
						disable: false
					}, {
						id: 6,
						name: '问题数量',
						checked: false,
						disable: false
					}, {
						id: 7,
						name: '关注数量',
						checked: false,
						disable: false
					}, {
						id: 8,
						name: '粉丝数量',
						checked: false,
						disable: false
					}, {
						id: 9,
						name: '关注问题数量',
						checked: false,
						disable: false
					}, {
						id: 10,
						name: '关注话题数量',
						checked: false,
						disable: false
					}, {
						id: 11,
						name: '关注文件夹数量',
						checked: false,
						disable: false
					},
					{
						id: 12,
						name: '专栏数量',
						checked: false,
						disable: false
					}
				],
			}
		},
		methods: {
			async SetGenderLevel() {
				var chartDom = document.getElementById('GenderLevel');
				var myChart = echarts.init(chartDom);
				var option;
				option = {
					legend: {},
					tooltip: {},
					dataset: {
						source: [
							['product', 'Man', 'Woman', 'Unknow'],
							['一级', this.GenderLevelMan[0][1], this.GenderLevelWoman[0][1], this.GenderLevelX[0][1]
							],
							['二级', this.GenderLevelMan[1][1], this.GenderLevelWoman[1][1], this.GenderLevelX[1][1]
							],
							['三级', this.GenderLevelMan[2][1], this.GenderLevelWoman[2][1], this.GenderLevelX[2][1]
							],
							['四级', this.GenderLevelMan[3][1], this.GenderLevelWoman[3][1], this.GenderLevelX[3][1]
							],
							['五级', this.GenderLevelMan[4][1], this.GenderLevelWoman[4][1], this.GenderLevelX[4][1]
							],
							['六级', this.GenderLevelMan[5][1], this.GenderLevelWoman[5][1], this.GenderLevelX[5][1]
							],
							['七级', this.GenderLevelMan[6][1], this.GenderLevelWoman[6][1], this.GenderLevelX[6][1]
							],
							['八级', this.GenderLevelMan[7][1], this.GenderLevelWoman[7][1], this.GenderLevelX[7][1]
							],
							['九级', this.GenderLevelMan[8][1], this.GenderLevelWoman[8][1], this.GenderLevelX[8][1]
							],
							['十级', this.GenderLevelMan[9][1], this.GenderLevelWoman[9][1], this.GenderLevelX[9][1]
							],

						]
					},
					xAxis: {
						type: 'category'
					},
					yAxis: {},
					// Declare several bar series, each will be mapped
					// to a column of dataset.source by default.
					series: [{
						type: 'bar'
					}, {
						type: 'bar'
					}, {
						type: 'bar'
					}]
				};
				option && myChart.setOption(option);

			},
			async GetAllGenderLevel() {
				axios.post("http://101.34.151.80:9090/all_figure/get_userDraw", {
					"key1": "level-gender",
					"key2": "gender-level",
					"key_value1": 2021,
					"key_value2": 11
				}).then(res => {
					// console.log(res.data.data)
					let li = res.data.data
					//
					let man = []
					let woman = []
					let x = []
					let newman = []
					let newx = []
					let newwoman = []
					for (let i = 0; i < li.length; i++) {
						if (li[i].key_value2 == "女") {
							woman.push([li[i].key_value1, li[i].num])
						}
					}
					for (let i = 0; i < li.length; i++) {
						if (li[i].key_value2 == "男") {
							man.push([li[i].key_value1, li[i].num])
						}
					}
					for (let i = 0; i < li.length; i++) {
						if (li[i].key_value2 == "未知") {
							x.push([li[i].key_value1, li[i].num])
						}
					}

					//女性数据处理

					for (let i = 1; i < 11; i++) {
						newwoman.push([i, 0])
					}

					for (let i = 0; i < woman.length; i++) {
						for (let j = 0; j < newwoman.length; j++) {
							if (newwoman[j][0] == woman[i][0]) {
								newwoman[j][1] = woman[i][1]
							}
						}
					}

					//男性数据处理

					for (let i = 1; i < 11; i++) {
						newman.push([i, 0])
					}

					for (let i = 0; i < man.length; i++) {
						for (let j = 0; j < newman.length; j++) {
							if (newman[j][0] == man[i][0]) {
								newman[j][1] = man[i][1]
							}
						}
					}

					//未知性别处理

					for (let i = 1; i < 11; i++) {
						newx.push([i, 0])
					}

					for (let i = 0; i < x.length; i++) {
						for (let j = 0; j < newx.length; j++) {
							if (newx[j][0] == x[i][0]) {
								newx[j][1] = x[i][1]
							}
						}
					}
					this.GenderLevelWoman = newwoman
					this.GenderLevelMan = newman
					this.GenderLevelX = newx

					// console.log(this.GenderLevelMan)
					this.SetGenderLevel()
				})


			},

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

		}
	}
</script>

<style scoped>
	#top-Navigator {
		width: 100%;
		display: flex;
		flex-direction: row;
		align-items: center;
	}

	.el-check-tag {
		width: 100%;
		text-align: center;
		margin: 0 20px 0 20px;
		border-radius: 15px;
		background-color: #38ada9;
		color: antiquewhite;
		/* font-family: "Times New Roman", Times, serif; */
	}

	.el-check-tag:hover {
		background-color: #54d59d;
	}

	.el-check-tag:active {
		background-color: #6a89cc;
	}

	.tag-disable {
		cursor: not-allowed;
	}

	.tag-allowable {
		cursor: pointer;
	}
</style>
