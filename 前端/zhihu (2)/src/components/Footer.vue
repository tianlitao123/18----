<!--
 * @Author: sun
 * @Date: 2021/10/24 20:09
 * @title: Footer
 * @Description:
 * @version: 1.0
 -->
<template>
    <div id="footerback">
		<article>
			Copyright  2017-2021, The Apache Software Foundation Apache ECharts, ECharts, Apache, the Apache feather, and the Apache ECharts project logo are either registered trademarks or trademarks of the Apache Software Foundation.
		</article>
        <!-- <el-col :span="22" id="Description">{{Description}}</el-col>
        <el-col :span="2" id="Logo"><img src="src/assets/logo.png" width="100" height="100"></el-col> -->
    </div>
</template>

<script>
    export default {
        name: "Footer",
        data(){
            return {
                Description:"Copyright  2017-2021, The Apache Software Foundation Apache ECharts, ECharts, Apache, the Apache feather, and the Apache ECharts project logo are either registered trademarks or trademarks of the Apache Software Foundation."
            }
        },
    }
</script>

<style scoped>

#footerback{
	background-color:#718093;
	height:100px;
	width: 100%;
}
</style>