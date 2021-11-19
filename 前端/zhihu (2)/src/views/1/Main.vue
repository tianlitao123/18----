<!--
 * @Author: sun
 * @Date: 2021/10/26 13:40
 * @title: Main
 * @Description:
 * @version: 1.0
 -->
<template>
	<el-container>
		<el-header class="el-header" height="150px">
			<PageHeader></PageHeader>
		</el-header>
		<el-main class="main">
			<router-view name="ma"></router-view>
			<!-- 
			Q:改成这样怎么样？<Description></Description>
			A:不要改，因为违反了复用，可以直接在这里展示你所要展示的页面
			-->
			<Footer></Footer>
		</el-main>

	</el-container>
</template>

<script>
	import PageHeader from '../components/Header.vue'
	import Footer from '../components/Footer.vue'
	export default {
		name: "Main",
		components: {
			PageHeader,
			Footer,
		}
	}
</script>

<style scoped>
	.el-header {
		background-color: gold;
	}

	.main {
		width: 100%;
		height: auto;
		padding: 0px;
	}
</style>

