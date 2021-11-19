<template>
	<div class="rank-list">
		<div class="rank">
			<RankHeader></RankHeader>
		</div>
	</div>
	<div class="rank">
		<TotalRankCarouselCard></TotalRankCarouselCard>
	</div>
</template>
<script>
	import TotalRankCarouselCard from '../components/TotalRankCarouselCard.vue'

	import RankHeader from '../components/RankHeader.vue'
	export default {
		name: "TotalRankList",
		components: {
			TotalRankCarouselCard,
			RankHeader
		},
	}
</script>

<style scoped>
	.rank-list {
		width:100%;
		height:auto;
		background: red linear-gradient(to bottom, rgba(46, 134, 222, 1.0), rgba(255, 255, 255, 1.0));
	}

	.rank {
		height:100%;
		padding: 20px 204px 20px 204px;
	}
</style>
