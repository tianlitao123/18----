<!--
 * @Author: sun
 * @Date: 2021/11/6 15:49
 * @title: UserPage
 * @Description:
 * @version: 1.0
 -->
<template>
	<keep-alive>
		<div class="body">
			<div id="HeadBackground">
				<div id="Header-up"></div>

				<div v-bind:class="showBigHeader?'bigHeader':'header'" id="Header-down">
					<div class="Bussiness" v-if="UserData.business!=null"><a id="Bussiness">公司:{{UserData.business}}</a>
					</div>
					<div class="Gender" v-if="UserData.gender!=null"><a id="Gender">用户性别:{{UserData.gender}}</a></div>
					<div class="Description" v-if="UserData.description!=null"> <a
							id="Description">{{UserData.description}}</a></div>
					<div class="Headline" v-if="UserData.headline!=null"> <a id="Headline">{{UserData.headline}}</a>
					</div>
				</div>

				<div>
					<el-avatar :size="150" v-bind:src="UserData.avatar_url" shape="square" class="UserPicture"
						@click="GetData"></el-avatar>
					<div class="UserName"><a id="UserName">{{UserData.name}}</a></div>
				</div>

			</div>

			<div class="contant">
				<div id="Kind">
					<div id="KindCakeMap" style="width: 500px;height: 500px;" />
					<div id="KindBrokenMap" style="width: 500px;height: 500px;" />
					<div><a id="KindSuggest">{{kindSuggest}}</a></div>
				</div>
			</div>
		</div>
	</keep-alive>
</template>

<script>
	import axios from 'axios'
	import * as echarts from 'echarts'

	export default {
		name: "UserPage",
		data() {
			return {
				avatar_url: "https://pic2.zhimg.com/v2-73b02fdb3ef4e07fb7599ecb607f3959_xl.jpg",
				UserData: "",
				// name:"小白一个",
				// level_info_level:0,
				// bussiness_name:"所在行业：计算机",
				// BaseData:[],
				loading: true,
				kindSuggest: "",
				showBigHeader: false,
				OtherData: "",

			}
		},
		methods: {
			//获取数据
			async GetData() {
				await axios.get('http://mockjs.xiaoyaoji.cn/mock/1jVeX6aHqfA/user_check/getUserById2', ).then((
				res) => {
					this.UserData = res.data.data[0]
					// answer_count: 回答数量
					// articles_count: 文章数量
					// avatar_url: 头像地址
					// business: 公司
					// columns_count: 0
					// description: 个人描述
					// favorite_count: 收藏数量
					// follower_count: 粉丝数目
					// following_columns_count: 关注专栏数
					// following_count: 关注数目
					// following_favlists_count: 关注文件夹数量
					// following_question_count: 关注问题数
					// following_topic_count: 关注话题数
					// frequency: 被检索频率
					// gender: 用户性别
					// headline: 用户头文字
					// id:
					// id2: "2"
					// level: 等级
					// location: []
					// name: "kid"
					// question_count: 提问数量
					// type: 用户类型
					// url_token: null
					// voteup_count: 被赞同次数
					// zvideo_count: 视频数量
				})
				if ((this.UserData.business = !null) && (this.UserData.gender != null)) {
					this.showBigHeader = true
				}

				axios.post("http://mockjs.xiaoyaoji.cn/mock/1jVeX6aHqfA/all_figure/get_userDraw", {
					"key1": "level-gender",
					"key2": "gender-level",
					"key_value1": 2021,
					"key_value2": 10
				}).then(res => {
					console.log(res)
				})

			},

			//种类图
			async SetKindCakedMap() {
				var chartDom = document.getElementById('KindCakeMap');
				var myChart = echarts.init(chartDom);
				var option = {
					title: {
						text: "用户" + this.UserData.name + "作品种类数量饼图",
						subtext: '各类型作品数量图',
						left: 'center'
					},
					tooltip: {
						trigger: 'item'
					},
					legend: {
						orient: 'vertical',
						left: 'left'
					},
					series: [{
						name: 'Access From',
						type: 'pie',
						radius: '50%',
						data: [{
								value: this.UserData.articles_count,
								name: '文章'
							},
							{
								value: this.UserData.columns_count,
								name: '专栏'
							},
							{
								value: this.UserData.zvideo_count,
								name: '视频'
							},
							{
								value: this.UserData.answer_count,
								name: '回答'
							},
						],
						emphasis: {
							itemStyle: {
								shadowBlur: 10,
								shadowOffsetX: 0,
								shadowColor: 'rgba(0, 0, 0, 0.5)'
							}
						}
					}]
				};
				option && myChart.setOption(option);
			},
			//种类建议
			async setKindSuggest() {
				var lin = [this.UserData.articles_count, this.UserData.zvideo_count, this.UserData.answer_count, this
					.UserData.columns_count
				]
				for (var i = 0; i <= 4; i++) {
					for (var j = 0; j <= 4 - i; j++) {
						if (lin[j] > lin[j + 1]) {
							var c = lin[i]
							lin[i] = lin[j + 1]
							lin[j + 1] = c

						}
					}
				}

				if (lin[0] == this.UserData.articles_count) {
					this.kindSuggest = "您的文章产量最少，数量为" + this.UserData.articles_count + "\n"
				} else if (lin[0] == this.UserData.zvideo_count) {
					this.kindSuggest = "您的视频产量最少，数量为" + this.UserData.zvideo_count + "\n"
				} else if (lin[0] == this.UserData.answer_count) {
					this.kindSuggest = "您的回答产量最少，数量为" + this.UserData.answer_count + "\n"
				} else if (lin[0] == this.UserData.columns_count) {
					this.kindSuggest = "您的专栏产量最少，数量为" + this.UserData.columns_count + "\n"
				}

				if (lin[3] == this.UserData.articles_count) {
					this.kindSuggest = this.kindSuggest + "您的文章产量最多，数量为" + this.UserData.articles_count + "\n"
				} else if (lin[3] == this.kindSuggest + this.UserData.zvideo_count) {
					this.kindSuggest = this.kindSuggest + "您的视频产量最多，数量为" + this.UserData.zvideo_count + "\n"
				} else if (lin[3] == this.UserData.answer_count) {
					this.kindSuggest = this.kindSuggest + "您的回答产量最多，数量为" + this.UserData.answer_count + "\n"
				} else if (lin[3] == this.UserData.columns_count) {
					this.kindSuggest = this.kindSuggest + "您的专栏产量最多，数量为" + this.UserData.columns_count + "\n"
				}

				if (lin[3] == 0) {
					this.kindSuggest = "您目前没有作品，可以尝试回答些问题寻找知己哦！"
				}

				if ((lin[3] - lin[2]) > 50) {
					this.kindSuggest = this.kindSuggest + "您可以多尝试其他类型的创作"
				}


			},
			//种类折线图
			async SetKindBrokenMap() {
				var chartDom = document.getElementById('KindBrokenMap');
				var myChart = echarts.init(chartDom);
				var option;

				option = {
					title: {
						text: "用户" + this.UserData.name + "作品种类数量折线图",
						subtext: '各类型作品数量图',
						left: 'center'
					},
					xAxis: {
						type: 'category',
						data: ['文章', '视频', '回答', '专栏', ]
					},
					yAxis: {
						type: 'value'
					},
					grid: {
						left: '3%',
						right: '4%',
						bottom: '3%',
						containLabel: true
					},
					series: [{
						data: [this.UserData.articles_count, this.UserData.zvideo_count, this.UserData
							.answer_count, this.UserData.columns_count
						],
						type: 'line',
						label: {
							show: true,
							position: 'bottom',
							textStyle: {
								fontSize: 20
							}
						}
					}]
				};

				option && myChart.setOption(option);
			},
			//用户等级、全站等级、地区等级
			async SetRankMap() {
				var chartDom = document.getElementById('fi');
				var myChart = echarts.init(chartDom);
				var option = {
					title: {
						text: "地区等级分布",
					},
					xAxis: {
						type: 'category',
						data: ['一级', '二级', '三级', '四级', '五级', '六级', '七级', '八级', '九级', '十级']
					},
					yAxis: {
						type: 'value',
						scale: true,
					},
					series: [{
						data: [120, 200, 150, 80, 70, 110, 130, 200, 100, 25],
						type: 'bar',
						showBackground: true,
						backgroundStyle: {
							color: 'rgba(180, 180, 180, 0.2)'
						},
						itemStyle: {
							normal: {
								color: function(params) {
									var colorList = ['#C1232B', '#B5C334', ];
									return colorList[params.dataIndex];

								},
								label: {
									show: true, //开启显示
									position: 'top', //在上方显示
									textStyle: { //数值样式
										color: 'black',
										fontSize: 16
									}
								}
							}

						}
					}]
				};
				option && myChart.setOption(option);
			},
			//同种类比较图
			async SetMap() {
				var chartDom = document.getElementById('fi');
				var myChart = echarts.init(chartDom);


				// var option = {
				//     title: {
				//         text: '创作比较图'
				//     },
				//     tooltip: {
				//         trigger: 'axis'
				//     },
				//     legend: {
				//         data: ['用户个人', '同等级', '同地区', '全站']
				//     },
				//     grid: {
				//         left: '3%',
				//         right: '4%',
				//         bottom: '3%',
				//         containLabel: true
				//     },
				//     toolbox: {
				//         feature: {
				//             saveAsImage: {}
				//         }
				//     },
				//     xAxis: {
				//         type: 'category',
				//         boundaryGap: false,
				//         data: ['文章', '视频', '文章', '专栏']
				//     },
				//     yAxis: {
				//         type: 'value'
				//     },
				//     series: [
				//         {
				//             name: '文章',
				//             type: 'line',
				//             stack: 'Total',
				//             data: [120, 132, 101, 134,],
				//             label: {
				//                 show: true,
				//                 position: 'bottom',
				//                 textStyle: {
				//                     fontSize: 20
				//                 }
				//             }
				//         },
				//         {
				//             name: '视频',
				//             type: 'line',
				//             stack: 'Total',
				//             data: [220, 182, 191, 234],
				//             label: {
				//                 show: true,
				//                 position: 'bottom',
				//                 textStyle: {
				//                     fontSize: 20
				//                 }
				//             }
				//         },
				//         {
				//             name: '回答',
				//             type: 'line',
				//             stack: 'Total',
				//             data: [150, 232, 201, 154],
				//             label: {
				//                 show: true,
				//                 position: 'bottom',
				//                 textStyle: {
				//                     fontSize: 20
				//                 }
				//             }
				//         },
				//         {
				//             name: '专栏',
				//             type: 'line',
				//             stack: 'Total',
				//             data: [320, 332, 301, 334],
				//             label: {
				//                 show: true,
				//                 position: 'bottom',
				//                 textStyle: {
				//                     fontSize: 20
				//                 }
				//             }
				//         },
				//     ]
				// };
				var option = {
					title: {
						text: '创作比较图'
					},
					tooltip: {
						trigger: 'axis'
					},
					legend: {
						data: ['用户个人', '同等级', '同地区', '全站']
					},
					grid: {
						left: '3%',
						right: '4%',
						bottom: '3%',
						containLabel: true
					},
					toolbox: {
						feature: {
							saveAsImage: {}
						}
					},
					xAxis: {
						type: 'category',
						boundaryGap: false,
						data: ['文章', '视频', '专栏', '回答']
					},
					yAxis: {
						type: 'value'
					},
					series: [{
							name: '用户个人',
							type: 'line',
							data: [120, 132, 101, 134, 90, 230, 210],
							label: {
								show: true,
								textStyle: {
									fontSize: 20
								}
							}
						},
						{
							name: '同等级',
							type: 'line',
							data: [220, 182, 191, 234, 290, 330, 310],
							label: {
								show: true,
								textStyle: {
									fontSize: 20
								}
							}
						},
						{
							name: '同地区',
							type: 'line',
							data: [150, 232, 201, 154, 190, 330, 410],
							label: {
								show: true,
								textStyle: {
									fontSize: 20
								}
							}
						},
						{
							name: '全站',
							type: 'line',
							data: [320, 332, 301, 334, 390, 330, 320],
							label: {
								show: true,
								textStyle: {
									fontSize: 20
								}
							}
						},
					]
				};

				option && myChart.setOption(option);
			},

			async start() {
				await this.GetData();
				await this.SetKindCakedMap();
				await this.SetKindBrokenMap();
				await this.setKindSuggest();
			}

		},

		created() {

		},
		mounted() {
			this.start();
		}
	}
</script>

<style scoped>
	html,
	body {
		height: 100%;
	}

	.body {
		background-color: #f1f2f6;
		background-size: 100%, 100%;

	}

	.header {
		height: 100px;
		width: 80%;
	}

	.bigHeader {
		height: 200px;
		width: 80%;
	}

	#Header-up {
		position: relative;
		left: 10%;
		background: #908b81;
		height: 100px;
		width: 80%;
		border-radius: 10px 10px 0px 0px;
	}

	#Header-down {
		position: relative;
		left: 10%;
		background: #ffffff;
		border-radius: 0px 0px 10px 10px;
	}

	.UserPicture {
		position: absolute;
		top: 20px;
		left: 180px;
		border: 3px solid #fff;
	}

	.UserName {
		position: absolute;
		top: 100px;
		left: 380px;
		font-size: 30px;
	}

	.Bussiness {
		font-size: 10px;
		position: relative;
		left: 30%;
		top: 30%;
	}

	.Gender {
		font-size: 10px;
		position: relative;
		left: 30%;
		top: 30%;
	}

	.Description {
		font-size: 10px;
		position: relative;
		left: 30%;
		top: 30%;
	}

	.Headline {
		font-size: 10px;
		position: relative;
		left: 30%;
		top: 30%;
	}

	.contant {
		border-radius: 10px 10px 0px 0px;
		height: 100%;
		width: 80%;
		margin: auto;
		background-color: #ffffff;
		margin-top: 20px;
	}

	#KindSuggest {
		font-size: 20px;
		position: relative;
		left: 10%;
		top: 30px;
	}

	#KindCakeMap {
		position: relative;
		display: inline-block;
	}

	#KindBrokenMap {
		position: relative;
		display: inline-block;
	}
</style>
