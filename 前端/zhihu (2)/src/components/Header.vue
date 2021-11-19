<!--
 * @Author: sun
 * @Date: 2021/10/21 18:27
 * @title: head
 * @Description:
 * @version: 1.0
 -->
<template>
	<header id="he1">
		<div class="header-item">
			<img src="../assets/知乎.png" alt="../assets/Alerts.svg" id='head-img'>
		</div>
		<div class="header-item">
			<router-link to="/"><a>首页</a></router-link>
			<router-link to="/TotalChart"><a>图表</a></router-link>
			<router-link to="/TotalRankList"><a>排行榜</a></router-link>
			<router-link to="/SearchPage"><a>搜索</a></router-link>
			<router-link to="/ChinaMaps"><a>地图</a></router-link>
			<router-link to="/UserPage"><a>用户</a></router-link>
		</div>
		<!-- </el-col> -->

	</header>
</template>

<script>
	export default {
		name: "Head",
		data() {
			return {
				LogoName: "AntDesign"
			}
		},
		methods: {
			handleClick(tab, event) {
				console.log(tab, event)
			},
		}
	}
</script>

<style scoped>
	#head-img {
		height: 100%;
	}

	.header-item {
		display: flex;
		height: 100%;
		flex-direction: row;
		align-items: center;
		margin: 0 20px;
		;
	}

	a {
		text-decoration: none;
		padding: 0 20px;
	}

	/* 未访问的链接 
	a:link {
		color: #FF0000;
	} */

	/* 已访问的链接
	a:visited {
		color: #00FF00;
	} */

	a:hover {
		color: aliceblue;
	}

	/* 已选择的链接 */
	a:active {
		color: #0000FF;
	}

	header {
		height: 100%;
		width: 100%;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: flex-start;
		padding-left: 204px;
		padding-right: 204px;
	}
	#he1{
		position: -webkit-sticky; /* Safari */
		position: sticky;
	}
</style>
