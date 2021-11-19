<template>
		<RankTitle></RankTitle>
		<RankDescription></RankDescription>
</template>

<script>
	import RankTitle from './RankTitle.vue'
	import RankDescription from './RankDescription.vue'
	export default {
		name: 'RankHeader',
		components: {
			RankTitle,
			RankDescription
		},
	}
</script>

<style scoped>
</style>
