<template>
	<el-card class="box-card" shadow="hover" :style="{background:rankListsItem.backgroundcolor}">
		<template #header>
			<div class="card-header">
				<!-- <span :style="mapStyle">{{cardTitle}}</span> -->
				<div><img :src="rankListsItem.titleURL" alt="../assets/alert.png"></div>
				<span style="font-size:40px" v-bind:style="{color: rankListsItem.color}">{{cardTitle}}</span>
				<!-- <el-button class="button" type="text">Operation button</el-button> -->
			</div>
		</template>
		<el-collapse v-model="activeName" accordion>
			<div v-for="(item,index) in cardContent" :key="item.id" class="text item" style="font-size:30px" >
				<el-collapse-item :title="index+1+' '+ item.name + ' '+ item.value" :name="item.id">
					<div style='height:100px;display: flex;flex-direction: row;align-items: center;flex:1;'> 
					<img :src="item.avatar_url" alt="../assets/Alerts.svg" style='height:100%;width:50%' @click='clickImg(item.id2)'>
					<img :src="item.avatar_url" alt="../assets/Alerts.svg" style='height:100%;width:50%'>
					</div>
				</el-collapse-item>
			</div>
		</el-collapse>
	</el-card>
</template>
<script scoped>
	export default {
		name: "TotalRankCard",

		props: ['rankListsItem'],
		data() {
			return {
				fcolor: 'red',
				activeName: '1',
				mapStyle:  {
					color: this.rankListsItem.color,
					}
			}
		},
		methods:{
			clickImg(a){
				this.$router.push({ path: '/UserPage', query: { userId: a }})
			}
		},
		computed: {
			cardTitle() {
				// console.log(this.rankListsItem);
				return this.rankListsItem.title
			},
			cardTitleColor() {
				return this.rankListsItem.color
			},
			cardContent() {
				return this.rankListsItem.content
			},
		},
	}
</script>

<style scoped>
	.card-header {
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
		align-items: center;
	}

	.text {
		font-size: 14px;
	}

	.item {
		margin-top: 9px;
		margin-bottom: 9px;
		border-radius: 20px!important;
	}
/deep/ .el-collapse-item{
	border-radius: 20px!important;
	background-color: ;
}
	.box-card {
		height: 100%;
	}

	/deep/ .el-collapse-item__header {
		border-bottom: none
	}
	/deep/ .el-collapse-item__wrap{
		background-color: transparent!important;
	}
	/deep/ .el-collapse-item__header{
		border-radius: 20px!important;
	}
	
</style>
