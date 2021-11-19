<template>
	<el-carousel :interval="4000" type="card" :autoplay="false">
		<el-carousel-item v-for="rankListsItem in rankLists" :key="rankListsItem.id">
			<h3 class="medium">
				<TotalRankCard :rankListsItem="rankListsItem">
					<!-- <v-for="rankListsItemContent in rankLists.content" :key="rankListsItemContent.contentId"
						:itemContent="rankListsItemContent.content"> -->
				</TotalRankCard>
			</h3>
		</el-carousel-item>
	</el-carousel>
</template>

<script>
	import TotalRankCard from './TotalRankCard.vue'
	import axios from 'axios'
	export default {
		name: "TotalRankCarouselCard",
		components: {
			TotalRankCard
		},
		data() {
			return {
				ItemUserData: [],
				rankLists: [{
						id: '002', //分区id
						title: "潜水榜",
						titleURL: require("../assets/潜水.png"),
						color: '#747d8c',
						backgroundcolor: '#70a1ff',
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
					},
					{
							id: '003', //分区id
							title: "用户活跃榜",
							titleURL: require("../assets/活跃度.png"),
							color: '#7bed9f',
							backgroundcolor: '#ff4757',
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
						},
					{
							id: '004', //分区id
							title: "用户被检索榜",
							titleURL: require("../assets/检索.png"),
							color: '#ffa502',
							backgroundcolor: '#5352ed',
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
						},
					{
							id: '005', //分区id
							title: "新人榜",
							titleURL: require("../assets/新.png"),
							color: '#8854d0',
							backgroundcolor: '#0fb9b1',
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
						},
					{
							id: '006', //分区id
							title: "文章质量榜",
							titleURL: require("../assets/质量技术.png"),
							color: '#000000',
							backgroundcolor: '#fa8231',
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
						},
				]
			}
		},
		methods: {
				//获取获取分区（id2）中的信息（包含用户名称和value）
				async GetItemRankInfo2() {
					await axios.get(
						'http://101.34.151.80:9090/area_info/getUsersInfoByAreasId?id=2&year=2021&month=11',
					).then((
						res) => {
						for (let i = 0; i < (this.rankLists[0].content.length); i++) {
							this.rankLists[0].content[i].id2 = res.data.data[i].id2;
							this.rankLists[0].content[i].name = res.data.data[i].name;
							this.rankLists[0].content[i].url_token = res.data.data[i].url_token
							this.rankLists[0].content[i].avatar_url = res.data.data[i].avatar_url
							this.rankLists[0].content[i].value = res.data.data[i].value
							this.rankLists[0].content[i].year = res.data.data[i].year
							this.rankLists[0].content[i].month = res.data.data[i].month
						}
		
		
					
						// id2:用户uuid
						// name:用户名
						// url_token:页面后面的成员内容
						// avatar_url:用户图片
						// value:排序根据这个
						// year:年
						// month:月
					})
				},
				// 获取获取分区（id3）中的信息（包含用户名称和value）
				async GetItemRankInfo3() {
					await axios.get(
						'http://101.34.151.80:9090/area_info/getUsersInfoByAreasId?id=3&year=2021&month=11',
					).then((
						res) => {
						for (let i = 0; i < this.rankLists[1].content.length; i++) {
							this.rankLists[1].content[i].id2 = res.data.data[i].id2;
							this.rankLists[1].content[i].name = res.data.data[i].name;
							this.rankLists[1].content[i].url_token = res.data.data[i].url_token
							this.rankLists[1].content[i].avatar_url = res.data.data[i].avatar_url
							this.rankLists[1].content[i].value = res.data.data[i].value
							this.rankLists[1].content[i].year = res.data.data[i].year
							this.rankLists[1].content[i].month = res.data.data[i].month
						}
						
						
				// 		console.log(this.rankLists.content)
				// 		// id2:用户uuid
				// 		// name:用户名
				// 		// url_token:页面后面的成员内容
				// 		// avatar_url:用户图片
				// 		// value:排序根据这个
				// 		// year:年
				// 		// month:月
					})
				},
				//获取获取分区（id4）中的信息（包含用户名称和value）
				async GetItemRankInfo4() {
					await axios.get(
						'http://101.34.151.80:9090/area_info/getUsersInfoByAreasId?id=4&year=2021&month=11',
					).then((
						res) => {
						for (let i = 0; i < this.rankLists[2].content.length; i++) {
							this.rankLists[2].content[i].id2 = res.data.data[i].id2;
							this.rankLists[2].content[i].name = res.data.data[i].name;
							this.rankLists[2].content[i].url_token = res.data.data[i].url_token
							this.rankLists[2].content[i].avatar_url = res.data.data[i].avatar_url
							this.rankLists[2].content[i].value = res.data.data[i].value
							this.rankLists[2].content[i].year = res.data.data[i].year
							this.rankLists[2].content[i].month = res.data.data[i].month
						}
						
						
						console.log(this.rankLists.content)
						// id2:用户uuid
						// name:用户名
						// url_token:页面后面的成员内容
						// avatar_url:用户图片
						// value:排序根据这个
						// year:年
						// month:月
					})
				},
				//获取获取分区（id5）中的信息（包含用户名称和value）
				async GetItemRankInfo5() {
					await axios.get(
						'http://101.34.151.80:9090/area_info/getUsersInfoByAreasId?id=5&year=2021&month=11',
					).then((
						res) => {
						for (let i = 0; i < this.rankLists[3].content.length; i++) {
							this.rankLists[3].content[i].id2 = res.data.data[i].id2;
							this.rankLists[3].content[i].name = res.data.data[i].name;
							this.rankLists[3].content[i].url_token = res.data.data[i].url_token
							this.rankLists[3].content[i].avatar_url = res.data.data[i].avatar_url
							this.rankLists[3].content[i].value = res.data.data[i].value
							this.rankLists[3].content[i].year = res.data.data[i].year
							this.rankLists[3].content[i].month = res.data.data[i].month
						}
						
						
						console.log(this.rankLists.content)
						// id2:用户uuid
						// name:用户名
						// url_token:页面后面的成员内容
						// avatar_url:用户图片
						// value:排序根据这个
						// year:年
						// month:月
					})
				},
				//获取获取分区（id6）中的信息（包含用户名称和value）
				async GetItemRankInfo6() {
					await axios.get(
						'http://101.34.151.80:9090/area_info/getUsersInfoByAreasId?id=6&year=2021&month=11',
					).then((
						res) => {
						for (let i = 0; i < this.rankLists[4].content.length; i++) {
							this.rankLists[4].content[i].id2 = res.data.data[i].id2;
							this.rankLists[4].content[i].name = res.data.data[i].name;
							this.rankLists[4].content[i].url_token = res.data.data[i].url_token
							this.rankLists[4].content[i].avatar_url = res.data.data[i].avatar_url
							this.rankLists[4].content[i].value = res.data.data[i].value
							this.rankLists[4].content[i].year = res.data.data[i].year
							this.rankLists[4].content[i].month = res.data.data[i].month
						}
					})
				},
				},
			mounted() {
				this.GetItemRankInfo2();
				this.GetItemRankInfo3();
				this.GetItemRankInfo4();
				this.GetItemRankInfo5();
				this.GetItemRankInfo6();
			}
		}

</script>

<style scoped>
	.el-carousel__item h3 {
		color: #475669;
		font-size: 20px;
		opacity: 0.75;
		line-height: 5px;
		margin: 0;
	}

	.el-carousel__item:nth-child(2n) {
		background-color: #99a9bf;
	}

	.el-carousel__item:nth-child(2n + 1) {
		background-color: #d3dce6;
	}
	/* .el-carousel{
		padding-top: 100px;
	} */
	/deep/ .el-carousel__container{
		
		height:1800px;
	}
	.medium{
		height:100%;
	}
	
</style>
