<!--
 * @Author: sun
 * @Date: 2021/10/26 13:40
 * @title: Main
 * @Description:
 * @version: 1.0
 * 新
 -->
<template>
	<keep-alive>
	<el-container>
		<el-header>
			<PageHeader></PageHeader>
		</el-header>

		<el-main class="main">
			<router-view name="ma"></router-view>
		</el-main>
		<el-footer>
			<Footer></Footer>
		</el-footer>
	</el-container>
	</keep-alive>
</template>

<script>
	import PageHeader from '../components/Header.vue'
	import Footer from '../components/Footer.vue'
	export default {
		name: "Main",
		components: {
			PageHeader,
			Footer
		},
		data() {

		},
		methods: {}
	}
</script>

<style scoped>
	.el-header {
		height: 52px !important;
		background-color: #2e86de;
		position:sticky;
		top: 0px;
		box-shadow: 0 1px 3px rgb(18 18 18 / 10%);
	}

	.main {
		width: 100%;
		height: auto;
		padding: 0px;
	}
</style>
