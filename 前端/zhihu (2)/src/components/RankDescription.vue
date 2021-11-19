<template>

	<div class="common-layout">
		<!-- <TotalRankCard></TotalRankCard> -->

		<el-container>
			<el-aside>
				<TotalRankCard :rankListsItem="rankLists"></TotalRankCard>
			</el-aside>
			<el-container>
				<el-main>
					<RankDescriptionMainContent></RankDescriptionMainContent>
				</el-main>
				<el-footer>
					<RankDescriptionFooterContent></RankDescriptionFooterContent>
				</el-footer>
			</el-container>
		</el-container>
	</div>
</template>

<script>
	import TotalRankCard from './TotalRankCard.vue'
	import RankDescriptionMainContent from './RankDescriptionMainContent.vue'
	import RankDescriptionFooterContent from './RankDescriptionFooterContent.vue'
	import axios from 'axios'
	export default {
		name: 'RankDescription',
		components: {
			TotalRankCard,
			RankDescriptionMainContent,
			RankDescriptionFooterContent
		},
		data() {
			return {
				AllRankData: "",
				ItemUserData: [],
				rankLists: {
					id: '001', //分区id
					title: "热搜榜",
					titleURL: require("../assets/hot.png"),
					color: '#f7b731',
					backgroundcolor: '#eb3b5a',
					formula: '',
					content: [{
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占热度
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, {
						id2: '', //用户id
						name: '', //用户名
						url_token: '', //用户url
						avatar_url: '', //用户头像
						value: '', //所占权重
						year: '', //年份
						month: '', //月
					}, ],
				}
			}
		},
		methods: {
			getResoponse() {
				axios.get('http://mockjs.docway.net/mock/1jVeX6aHqfA/location/getName', ).then((res) => {
					console.log(res.data)
				})

			},
			// //获取所有榜单信息
			// async GetAllRankInfo() {
			// 	await axios.get('http://101.34.151.80:9090/areas/getAllAreas', ).then((
			// 		res) => {
			// 		this.AllRankData = res.data.data[0]
			// 		// id:分区id
			// 		// name:分区名称
			// 		// formula:权重公式
			// 		console.log(res.data.data)
			// 		this.rankLists.id = this.AllRankData.id;
			// 		this.rankLists.title = this.AllRankData.name;
			// 		this.rankLists.formula = this.AllRankData.formula;
			// 	})


			// },
			//获取获取分区（id）中的信息（包含用户名称和value）
			async GetItemRankInfo() {
				await axios.get(
					'http://101.34.151.80:9090/area_info/getUsersInfoByAreasId?id=1&year=2021&month=11',
				).then((
					res) => {
					this.ItemUserData = res.data.data
					for (let i = 0; i < this.ItemUserData.length; i++) {
						this.rankLists.content[i].id2 = this.ItemUserData[i].id2;
						this.rankLists.content[i].name = this.ItemUserData[i].name;
						this.rankLists.content[i].url_token = this.ItemUserData[i].url_token
						this.rankLists.content[i].avatar_url = this.ItemUserData[i].avatar_url
						this.rankLists.content[i].value = this.ItemUserData[i].value
						this.rankLists.content[i].year = this.ItemUserData[i].year
						this.rankLists.content[i].month = this.ItemUserData[i].month
					}

					// console.log(this.rankLists);
					// console.log(this.rankLists.content)
					// id2:用户uuid
					// name:用户名
					// url_token:页面后面的成员内容
					// avatar_url:用户图片
					// value:排序根据这个
					// year:年
					// month:月
				})
			},
		},
		mounted() {
			// this.GetAllRankInfo();
			this.GetItemRankInfo();
		}
	}
</script>

<style scoped>
	/deep/ .el-footer {
		background-color: transparent;
		color: var(--el-text-color-primary);
		height: 400px !important;
	}


	.el-aside {
		background-color: transparent;
		width: 500px !important;
	}

	.el-container {
		height: auto;
	}
</style>
