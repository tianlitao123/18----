<template>
	<el-container style="margin:0 0 20px 0;box-shadow: 0 2px 10px 0 rgb(0 0 0 / 10%);">
		<el-header>

			<div
				style='width:350px;height:100%;box-sizing:border-box;border:1px solid black;background:linear-gradient(10.1deg,transparent 49.5%, #000 49.5%, #000 50.5%, transparent 50.5%);'>
				<h2>图样</h2>
				<h2 style='position:absolute;top:35px;left:250px;'>标签</h2>
			</div>
			<!-- <div style="-webkit-transform: skewY(-5deg);
			-moz-transform: skewY(-5deg);
			-ms-transform: skewY(-5deg);
			-o-transform: skewY(-5deg);
			transform: skewY(-5deg);">
			<div style="-webkit-transform: skewY(5deg);
  -moz-transform: skewY(5deg);
  -ms-transform: skewY(5deg);
  -o-transform: skewY(5deg);
  transform: skewY(5deg);"></div>
			</div> -->
			<ChartNavigatorTop></ChartNavigatorTop>
		</el-header>
		<el-container>
			<el-aside width="280px">
				<ChartNavigatorLeft></ChartNavigatorLeft>
			</el-aside>
			<el-main>
				<!--  -->
				<ChartNavigatorMain></ChartNavigatorMain>
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
	import ChartNavigatorTop from './ChartNavigatorTop.vue'
	import ChartNavigatorLeft from './ChartNavigatorLeft.vue'
	import ChartNavigatorMain from './ChartNavigatorMain.vue'
	export default {
		name: 'ChartNavigator',
		components: {
			ChartNavigatorTop,
			ChartNavigatorLeft,
			ChartNavigatorMain
		},
	}
</script>

<style scoped>
	/* /deep/ .el-tag {
		color: #42B983
	} */

	.el-main {
		background-color: #c8d6e5;
		padding: 40px;
	}



	.el-header {
		display: flex;
		flex-direction: row;
		width: 100%;
		height: 100px;
		padding: 0px;
		border: 1px solid antiquewhite;
	}

	.el-aside {
		border: 1px solid antiquewhite;
		width: auto !important;
	}
</style>
