<template>
	<ChartNavigator></ChartNavigator>
	<div>{{name.message}}</div>
</template>

<script>
	import ChartNavigator from '../components/ChartNavigator.vue'
	import axios from 'axios'
	export default {
		name: 'TotalChart',
		data() {
			return {
				name: {
					message: '',
					code: '',
					data: '',
				}
			}
		},
		components: {
			ChartNavigator,
		},
		mounted() {
			axios.get('http://mockjs.xiaoyaoji.cn/mock/1jVeX6aHqfA/location/getName', ).then((res) => {
				console.log(res.data);
							this.name = res.data
							console.log(this.name)
			})

			axios.post("http://mockjs.xiaoyaoji.cn/mock/1jVeX6aHqfA/all_figure/get_userDraw", {
				"key1": "level-gender",
				"key2": "gender-level"
			}).then((res) => {
				console.log(res.data)
			})
		}
	}
</script>

<style scoped>

</style>
