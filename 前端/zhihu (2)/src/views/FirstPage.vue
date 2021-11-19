<!--
 * @Author: sun
 * @Date: 2021/10/26 11:27
 * @title: FirstPage
 * @Description:
 * @version: 1.0
 -->
<template>
	<div id="body">
		<Description></Description>
		<button @click="getResoponse()">222</button>
	</div>
</template>

<script>
	import Description from '../components/Description.vue'
	import axios from 'axios'
	// import VueAxios from 'vue-axios'
	export default {
		methods: {
			getResoponse() {
				axios.get('http://mockjs.docway.net/mock/1jVeX6aHqfA/location/getName',).then((res) => {
					console.log(res.data)
				})
		},
	},
	components: {
			Description,
		},

	}
</script>

<style scoped>
	/* .el-header{
        background-color:#409EFF;
    } */
</style>
