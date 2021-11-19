<template>
	<div>
		<ul v-for="(value,index) in values" :key=index>
			<li @click="cg_current_index(index)" :class="{'bg_color':current_index==index}">
				选择 一
			</li>
		</ul>
	</div>
</template>
<script>
	export default {
		name: "eique",
		data() {
			return {
				values: [{
						id: 1,
						name: "选项一"
					},
					{
						id: 2,
						name: "选项二"
					},
					{
						id: 3,
						name: "选项三"
					},
					{
						id: 4,
						name: "选项四"
					},
					{
						id: 5,
						name: "选项五"
					}
				],
				current_index: -1
			}
		},
		methods: {
			cg_current_index(index) {
				this.current_index = index
			}
		}
	}
</script>

<style scoped>
	li {
		width: 300px;
		margin: 0 40%;
		border: 1px solid darkgray;
		cursor: pointer;
		padding: 10px;
		margin-top: 10px;
	}

	.bg_color {
		background-color: #FFE69F;
	}
</style>
