<template>
	<div class="rank-title">
		<div>
		<img src="../assets/rank.png" alt="">
		</div>
		<h1 id="rank-id">知乎百万级用户排行</h1>
	</div>
</template>

<script>
	export default {
		name: 'RankTitle',
		data() {
			return {
				fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
				url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
			}
		},
	}
</script>

<style scoped>
	.rank-title {
		height: 200px;
		width: 100%;
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
		align-items: center;
		font-size: 50px;
	}

	#rank-id {
		flex-grow: 1;
		text-shadow: 5px 5px 5px aquamarine;
		justify-content: center;
		align-items: center;
		display: flex;
	}
</style>
