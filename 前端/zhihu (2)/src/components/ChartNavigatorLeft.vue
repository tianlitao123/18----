<template>
	<div class='left-navigator'>
		<!-- <div id='table-head'>
			<b>类别</b>
			<em>姓名</em>
		</div> -->
		<ul>
			<!-- 	<el-carousel-item v-for="rankListsItem in rankLists" :key="rankListsItem.id">
					<h3 class="medium">
						<TotalRankCard :rankListsItem="rankListsItem">
						</TotalRankCard>
					</h3>
				</el-carousel-item> -->
				<!-- <li v-for="leftCardsItem in leftCards" :key="leftCardsItem.id">
					<ChartNavigatorLeftCard :leftCards="leftCardsItem" class='bg_color'></ChartNavigatorLeftCard>
				</li> -->
			<li v-for="(leftCardsItem,index) in leftCards" :key="leftCardsItem.id"  @click="cg_current_index(index)"
					:class="{'bg_color':current_index==index}">
				<ChartNavigatorLeftCard :leftCards="leftCardsItem"></ChartNavigatorLeftCard>
			</li>
		</ul>
	</div>
</template>

<script>
	import ChartNavigatorLeftCard from './ChartNavigatorLeftCard.vue'
	export default {
		name: 'ChartNavigatorLeft',
		data() {
			return {
				current_index: -1,
				isClick:false,
				num:[],
				leftCards: [{
					id: 1,
					img: require('../assets/折线图.svg'),
					title: '折线图',
					info: '折线图可以显示随时间（根据常用比例设置）而变化的连续数据，因此非常适用于显示在相等时间间隔下数据的趋势。'

				}, {
					id: 2,
					img: require('../assets/扇形图.svg'),
					title: '扇形图',
					info: '通过扇形统计图可以很清楚地表示出各部分数量同总数之间的关系'
				}, {
					id: 3,
					img: require('../assets/柱状图.svg'),
					title: '柱状图',
					info: '柱状图用来比较两个或以上的价值（不同时间或者不同条件），只有一个变量，通常利用于较小的数据集分析。长条图亦可横向排列，或用多维方式表达。'
				}, {
					id: 4,
					img: require('../assets/散点图.svg'),
					title: '散点图',
					info: '散点图通常用于比较跨类别的聚合数据。'
				}, {
					id: 5,
					img: require('../assets/K线图.svg'),
					title: 'k线图',
					info: '通过K线图，我们能够把每日或某一周期的市况表现完全记录下来'
				}, {
					id: 6,
					img: require('../assets/雷达图.svg'),
					title: '雷达图',
					info: '雷达图是以从同一点开始的轴上表示的三个或更多个定量变量的二维图表的形式显示多变量数据的图形方法。'
				}, {
					id: 7,
					img: require('../assets/热力图.svg'),
					title: '热力图',
					info: '热力图可以显示不可点击区域发生的事情。城市热力图该检测方式只提供参考'
				}, ]
			}
		},
		components: {
			ChartNavigatorLeftCard
		},
		methods: {
			cg_current_index(index) {
				// this.isClick = !this.isClick;
				if(this.num.length<2){
					this.num.push(index);
				}
				else{
					this.num.shift();
					this.num.push(index)
				}
				if(this.num[0] == this.num[1])
					this.current_index = index+100
					else this.current_index = index
			}
		}
	}
	// document.querySelector("#left-container")
</script>

<style scoped>
	#table-head {
		border-top: 40px #D6D3D6 solid;
		/*上边框宽度等于表格第一行行高*/
		width: 0px;
		/*让容器宽度为0*/
		height: 0px;
		/*让容器高度为0*/
		border-left: 80px #BDBABD solid;
		/*左边框宽度等于表格第一行第一格宽度*/
		position: relative;
		/*让里面的两个子容器绝对定位*/
	}

	b {
		font-style: normal;
		display: block;
		position: absolute;
		top: -40px;
		left: -40px;
		width: 35px;
	}

	em {
		font-style: normal;
		display: block;
		position: absolute;
		top: -25px;
		left: -70px;
		width: 55x;
	}

	ul {
		display: flex;
		flex-direction: column;
		list-style: none;
		padding: 0;
		margin: 0;
		height: 100%;
	}

	li {
		flex: 1;
		width: 100%;
		padding: 0px;
		}

	.left-navigator {
		width: 300px;
		height: 100%;
	}

	.bg_color {
		background-color: #FFE69F;
		border: 1px solid brown;
		
	}
</style>
