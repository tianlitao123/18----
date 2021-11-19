<template>
	<div class="block">
		<el-carousel>
			<el-carousel-item v-for="o in footerDescriptionLists" :key="o">
				<img :src="o.img" :title="o.title" style="height: 100%;width:100%">
				<!-- {{ footerDescriptionLists.title }} -->
			</el-carousel-item>
		</el-carousel>
	</div>
</template>

<script>
	export default { 
		name: 'RankDescriptionFooterContentCarouselCard',
		data(){
			return {
				footerDescriptionLists:[{
					id:1,
					img:require('../assets/rankFooter1.png'),
					title:'开学季大数据'
				},{
					id:2,
					img:require('../assets/rankFooter2.png'),
					title:'奥运大数据'
				},{
					id:3,
					img:require('../assets/rankFooter3.png'),
					title:'示例'
				},]
			}
		}
	}
</script>

<style scoped>
	/deep/ .el-carousel {
		height: 100%;
	}
	/deep/ .block{
		height:auto;
	}
	.el-carousel__item h3 {
		color: #475669;
		font-size: 14px;
		opacity: 0.75;
		line-height: 150px;
		margin: 0;
	}

	.el-carousel__item:nth-child(2n) {
		background-color: #99a9bf;
	}

	.el-carousel__item:nth-child(2n + 1) {
		background-color: #d3dce6;
	}
</style>
