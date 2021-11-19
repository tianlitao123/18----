<template>
	<div id='item-Leftcard'>
		<div id='item-itemleft-card' @click="showImgInfo()">
			<img :src="cardImg" alt="../assets/Alerts.svg" id='card-img'>
			<b>{{cardTitle}}</b>
		</div>
		<div>
			<img @mouseover="showInfo()" @mouseout="outInfo()" src="../assets/提示说明.svg" alt="../assets/Alerts.svg" title="提示说明" id='tip-img'>
				<div v-if='show' style="position: absolute;z-index: 1;">{{cardInfo}}</div>
		</div>
	</div>
</template>

<script>
	import mybus from "../mybus";
	import {
		eltooltip
	} from 'element-plus'
	export default {
		name: 'ChartNavigatorLeftCard',
		props: ['leftCards'],
		data() {
			return {
				show: false,
				a: false,
			}
		},
		computed: {
			cardTitle() {
				return this.leftCards.title
			},
			cardImg() {
				return this.leftCards.img
			},
			cardInfo() {
				return this.leftCards.info
			},
		},

		methods: {
			showInfo() {
				this.show = true;
			},
			outInfo() {
				this.show = false;
			},
			showImgInfo() {
				let imgTitle = this.cardTitle;
				this.a = !this.a;
				if (this.a == true)
					mybus.emit('showPatternImgInfo', imgTitle);
				else
					mybus.emit('showPatternImgInfo', '');
			},
			handleClose(done) {
				this.$confirm('确认关闭？')
					.then((_) => {
						done()
					})
					.catch((_) => {})
			},
		}
	}
</script>

<style scoped>
	#item-Leftcard {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		background-color: aliceblue;
		height: 100%;
		width: auto;
		padding: 0 20px 0 20px
	}

	#item-itemleft-card {
		height: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		cursor: pointer;
	}

	#card-img {
		height: 100%
	}

	#tip-img {
		width: 100%;
		height: 29px;
		cursor: pointer;
	}

	.box {
		width: 400px;
		}
		.top {
			text-align: center;
		}

		.item {
			margin: 4px;
		}
</style>
