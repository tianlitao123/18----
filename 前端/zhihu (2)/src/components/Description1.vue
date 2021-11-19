<template>
	<div class="des-main">
		<div class='pageContainer'>
			<div class="pageHeader">
			<div id='page1Left'>
				<h1 style="font-size: 40px;">知乎百万级用户数据分析与搜索</h1>
				<h2 style="font-size: 25px;">——专门为想探究更深数据的人士设置的网站</h2>
				<div id='page1LeftButton'>
					<el-button type="danger" round @click="ButtonLinkTo('https://www.baidu.com/')">
						帮助文档
					</el-button>
					<el-button type="primary" round @click="showSearch()">
						实例演示
					</el-button>
				</div>
			</div>
			<div id='pageLogo'>
				<img src="../assets/数据魔方.png" alt="../assets/alert.png" style="height:100%; width:100%;">
			</div>
			</div>
		</div>
		<div class='pageContainer'>
			<div class="pageOrigin">
				<h1 style="font-size: 40px;" class='itemTitle'>功能特性</h1>
				<div class="photoImg">
					<img src="../assets/FirstPageDescription.jpg" alt="../assets/alert.png"
						style="height:100%; width:100%;">
				</div>
			</div>
		</div>
		<div class='pageContainer'>
			<div class="pageOrigin">
				<h1 style="font-size: 40px;" class='itemTitle'  id="search">示例</h1>
				<h1 style="font-size: 30px;" class='itemTitle'>数据可视化展示</h1>
				<div class="photoImg">
					<img src="../assets/chart.png" alt="../assets/alert.png" style="height:100%; width:100%;">
				</div>
			</div>
		</div>
		
		<div class='pageContainer'>
			<div class="pageOrigin">
				<h1 style="font-size: 30px;" class='itemTitle'>排行榜展示</h1>
				<div id="rankOrigin">
					<div id="rankLeftOrigin">
						<div class="photoImg">
							<img src="../assets/rankexample1.jpeg" alt="../assets/alert.png"
								style="height:100%; width:100%;">
						</div>
						<div class="photoImg">
							<img src="../assets/rankexample3.gif" alt="../assets/alert.png" style="height:100%; width:100%;">
						</div>
					</div>
					<div id="rankRightOrigin">
						<div class="photoImg">
							<img src="../assets/rankexample2.jpeg" alt="../assets/alert.png"
								style="height:100%; width:100%;">
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class='pageContainer'>
			<div class="pageOrigin">
				<h1 style="font-size: 30px;" class='itemTitle'>检测展示</h1>
				<div class="photoImg">
					<img src="../assets/rankexample4.jpeg" alt="../assets/alert.png" style="height:100%; width:100%;">
				</div>
			</div>
		</div>
		<div class='pageContainer'>
			<div class="pageFooter">
				<B>
					<h1 style="font-size: 60px;" class='itemTitle'>关注我们</h1>
				</B>
				<div id='FooterButton'>
					<el-button style="margin-right:600px ;border:0px; padding:0px;"><img src="../assets/footer1.png"
							alt="../assets/alert.png" style="height:100%; width:100%;"
							@click="ButtonLinkTo('https://github.com/tianlitao123/zhihu-userstatistics')"></el-button>
					<el-button style="margin-left:60px ;border:0px; padding:0px;"><img src="../assets/footer2.png"
							alt="../assets/alert.png" style="height:100%; width:100%;"
							@click="ButtonLinkTo('file:///Users/tianlitao041170/Library/Containers/com.tencent.xinWeChat/Data/Library/Application%20Support/com.tencent.xinWeChat/2.0b4.0.9/9f44623ffb303b0b8d5844ba118dfe76/Message/MessageTemp/9e20f478899dc29eb19741386f9343c8/Image/4341636980209_.pic.jpg')">
					</el-button>
				</div>
			</div>
		</div>
	</div>
	<el-backtop></el-backtop>
</template>

<script>
	export default {
		name: "Description1",
		data() {
			return {
				SystemName: "知乎百万级用户数据分析与搜索",
				SystemDescription: "-专门为想探究更深数据的人士设置的网站",
				ButtonName1: "帮助文档",
				ButtonName2: "实例演示"
			}
		},
		methods: {
			showSearch() {

				var oDiv = document.getElementById("search");

				var t = document.createElement("input");
				t.type = "text";
				oDiv.appendChild(t);
				t.focus();

				oDiv.removeChild(t);

			},
			ButtonLinkTo(src) {
				setTimeout(location.href = src, 500);
			}
		},
	}
</script>

<style>
	.des-main {
		height: 100%;
		width: 100%;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
	}

	.pageContainer {
		margin: 20px 204px 20px 204px;
		width: auto;
		height: 100%;
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
		align-items: center;
	}

	#page1Left {
		padding-left: 30px;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: flex-start;
		font-weight: 30;
	}

	#page1LeftButton {
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
		align-items: center;
		margin: 35px 0;
	}

	.el-button {
		margin: 0 60px !important;
	}

	#pageLogo {
		display: flex;
		flex-direction: column;
		align-items: center;
		text-align: center;
		justify-content: center;
		width: 500px;
		height: 500px;
	}

	.pageOrigin {
		height: 100%;
		width: 100%;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: flex-start;
	}

	.itemTitle {
		display: flex;
		flex-direction: center;
		align-items: center;
		text-align: center;
		justify-content: center;
	}

	.photoImg {
		height: auto;
		width: 100%;
	}

	#rankOrigin {
		display: flex;
		flex-direction: row;
		align-items: center;
	}

	#rankLeftOrigin {
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
	}

	#rankRightOrigin {
		height: 100%;
		margin-left: 20px;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
	}

	.pageFooter {
		height: 400px;
		width: 100%;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: flex-start;
	}

	#FooterButton {
		width: 100%;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		margin: 35px 0;
	}
	.pageHeader{
		width: 100%;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		margin: 35px 0;
	}
</style>
