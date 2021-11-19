<template>
	<!-- 	<el-tag>标签一</el-tag>
	<el-tag type="success">标签二</el-tag>
	<el-tag type="info">标签三</el-tag>
	<el-tag type="warning">标签四</el-tag>
	<el-tag type="danger">标签五</el-tag> -->
	<el-tabs type="border-card">
		<!-- 男女比例图 -->
		<el-tab-pane label="人数(性别比例)">
			<div id="china_map_box">
				<div style="border:1px solid antiquewhite;width:20%;height:100%;font-size:10px;position:absolute;top:20px;left:40px;">
					<h1>统计到的总人数{{personNum}}</h1>
					<h1>统计到的男生总人数{{maleNum}}</h1>
					<h1>统计到的女生总人数{{femaleNum}}</h1>
					<h1>统计到的性别未知人数{{nodesignNum}}</h1>
					<h1>统计到的中国人数{{chinaNum}}</h1>
					<h1>统计到的海外人数{{foreignNum}}</h1>
					<h1>统计到的海外人数男{{foreignMaleNum}}</h1>
					<h1>统计到的海外人数女{{foreignFemaleNum}}</h1>
					<h1>统计到的地区未知人数{{nodesignNum1}}</h1>
					<h1>统计到的北京人数{{locationInfoLists[0].locationPersonNum}}</h1>
				</div>
				<div id="china_map"></div>
			</div>
		</el-tab-pane>
		<!-- 等级分布图 -->
		<el-tab-pane label="等级分布">
			<div id="china_map_box">
				<div style="border:1px solid antiquewhite;width:20%;height:100%;font-size:10px;position:absolute;top:20px;left:40px;">
					<h1>统计到的总人数{{personNumForLevel}}</h1>
					<!-- <h1>统计到的平均等级{{averageLevel}}</h1> -->
					<h1>统计到的平均等级为{{averageLevel}}</h1>
					<h1>统计到的各个等级的人数:
						<div v-for="(o,index) in totalLevel" :key="index">
							{{index+1 + '级人数有'}}{{o.levelNum}}
						</div>
					</h1>
				</div>
				<div id="china_map1"></div>
			</div>
		</el-tab-pane>
		<el-tab-pane label="点赞数量分布">
			<div id="china_map_box">
				<div style="border:1px solid antiquewhite;width:20%;height:100%;font-size:10px;position:absolute;top:20px;left:40px;">
					<h1>统计到的总人数{{personNumForVoteUp}}</h1>
					<!-- <h1>统计到的平均等级{{averageLevel}}</h1> -->
					<h1>统计到的平均点赞数为{{averageVoteUp}}</h1>
					<!-- <h1>统计到的各个等级的点赞数:
						<div v-for="(o,index) in totalLevel" :key="index">
							{{index+1 + '级点赞数有'}}{{o.levelNum}}
						</div>
					</h1> -->
				</div>
				<div id="china_map2"></div>
			</div>
		</el-tab-pane>
		<el-tab-pane label="行业地区分析">
			<div id="china_map_box">
				<div style="border:1px solid antiquewhite;width:20%;height:100%;font-size:10px;position:absolute;top:20px;left:40px;overflow:scroll">
					<h1>统计到的总人数{{personNum}}</h1>
					<h1>统计到的行业有<span v-for="(o,index) in businessMap" :key="index">{{o}}&nbsp;</span></h1>
					<!-- <h1>统计到的平均等级{{averageLevel}}</h1> -->
					<!-- <h3>统计到的<span v-for="(o,index) in businessMap" :key="index">{{o}}&nbsp;行业人数是{{Math.floor(1000*Math.random())}}&nbsp;</span></h3> -->
						<h3>统计到的<span v-for="(o,index) in businessMap1" :key="index">{{o.name}}&nbsp;行业人数是{{o.value}}&nbsp;</span></h3>
					<!-- <h1>统计到的各个等级的点赞数:
						<div v-for="(o,index) in totalLevel" :key="index">
							{{index+1 + '级点赞数有'}}{{o.levelNum}}
						</div>
					</h1> -->
				</div>
				<div id="china_map3"></div>
			</div>

			<!-- <div id="china_map_box">
				<div id="china_map"></div>
			</div> -->
		</el-tab-pane>
	</el-tabs>

</template>

<script>
	// import echarts from "echarts";
	import axios from 'axios'
	import * as echarts from 'echarts'
	import 'echarts/map/js/china.js'
	// import chinaCityJson from "@/data/china-cities.json";
	export default {
		name: 'MapsCard',

		data() {
			return {
				businessMap1:[],
				businessMap:[],
				averageVoteUp:'',
				personNumForVoteUp:'',
				//所有人数的等级统计
				totalLevel: [{
					levelNum: '',
				}, {
					levelNum: '',
				}, {
					levelNum: '',
				}, {
					levelNum: '',
				}, {
					levelNum: '',
				}, {
					levelNum: '',
				}, {
					levelNum: '',
				}, {
					levelNum: '',
				}, {
					levelNum: '',
				}, {
					levelNum: '',
				}, ],
				
				personNumForLevel:'',//所有人总数
				averageLevel: '',//所有人数的平均等级
				
				d: [],//收录的中国城市信息
				personLists: [],
				personLists1: '',
				personNum: {
					type: Number,
					default: 0, //默认值
				}, //接收到的总人数
				maleNum: {
					type: Number,
					default: 0, //默认值
				}, //接收到的男生总人数
				femaleNum: {
					type: Number,
					default: 0, //默认值
				}, //接收到的女生总人数
				nodesignNum: {
					type: Number,
					default: 0, //默认值
				}, //接收到的未知总人数
				chinaNum: {
					type: Number,
					default: 0, //默认值
				}, //接收到的海外总人数
				foreignNum: {
					type: Number,
					default: 0, //默认值
				}, //接收到的海外总人数
				foreignMaleNum: {
					type: Number,
					default: 0, //默认值
				}, //接收到的海外男总人数
				foreignFemaleNum: {
					type: Number,
					default: 0, //默认值
				}, //接收到的海外女总人数
				nodesignNum1: {
					type: Number,
					default: 0, //默认值
				}, //接收到的未知总人数
				
				
				locationInfoLists: [{ //中国每个省份的具体信息
						id: '',
						locationName: '北京',
						cityLocationName: ['北京'],
						totalLevelNum:'',
						averageLevelNum:'6',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '黑龙江',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'3',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['哈尔滨', '齐齐哈尔', '鸡西', '鹤岗', '双鸭山', '大庆', '伊春', '佳木斯', '七台河', '牡丹江',
							'黑河', '绥化', '大兴安岭'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '内蒙古',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'4',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['呼和浩特', '包头', '乌海', '赤峰', '通辽', '鄂尔多斯', '呼伦贝尔', '巴彦淖尔', '乌兰察布',
							'兴安盟', '锡林郭勒盟', '阿拉善盟'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '吉林',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'5',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['长春', '吉林', '四平', '辽源', '通化', '白山', '松原', '白城', '延边'],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '辽宁',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						averageLevelNum:'5',
						totalLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['沈阳', '大连', '鞍山', '抚顺', '本溪', '丹东', '锦州', '营口', '阜新', '辽阳', '盘锦',
							'盘锦', '朝阳', '葫芦岛'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '天津',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'5',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['天津'],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '河北',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'5',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['石家庄', '唐山', '秦皇岛', '邯郸', '邢台', '保定', '张家口', '承德', '沧州', '廊坊',
							'衡水'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '山西',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'5',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['太原', '大同', '阳泉', '长治', '晋城', '朔州', '晋中', '运城', '忻州', '临汾',
							'吕梁'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '山东',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						averageLevelNum:'5',
						totalLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['济南', '青岛', '淄博', '枣庄', '东营', '烟台', '潍坊', '济宁', '泰安', '威海', '日照',
							'莱芜', '临沂', '德州', '聊城', '滨州', '菏泽'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '河南',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						averageLevelNum:'5',
						totalLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['郑州', '开封', '洛阳', '平顶山', '安阳', '鹤壁', '新乡', '焦作', '济源', '濮阳',
							'许昌', '漯河', '三门峡', '南阳', '商丘', '信阳', '周口', '驻马店'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '陕西',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'4',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['西安', '铜川', '宝鸡', '咸阳', '渭南', '延安', '汉中', '榆林', '安康', '商洛'],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '宁夏',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['银川', '石嘴山', '吴忠', '固原', '中卫'],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '甘肃',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['兰州', '嘉峪关', '金昌', '白银', '天水', '武威', '张掖', '平凉', '酒泉', '庆阳',
							'定西', '陇南', '临夏', '甘南'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '青海',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['西宁', '海东', '海北', '黄南', '海南', '果洛', '玉树', '海西'],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '西藏',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['拉萨', '昌都', '山南', '日喀则', '那曲', '阿里', '林芝'],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						totalLevelNum:'',
						locationName: '新疆',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['乌鲁木齐', '克拉玛依', '吐鲁番', '哈密', '昌吉', '博尔塔拉', '巴音郭楞', '阿克苏',
							'克孜勒苏柯尔克孜自治州', '喀什', '和田', '伊犁', '塔城', '阿勒泰', '石河子', '阿拉尔', '图木舒克', '五家渠'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '四川',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['成都', '自贡', '攀枝花', '泸州', '德阳', '绵阳', '广元', '遂宁', '内江', '乐山',
							'南充', '眉山', '宜宾', '广安', '达州', '雅安', '巴中', '资阳', '阿坝', '甘孜', '凉山'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '重庆',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['重庆'],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '贵州',
						totalLevelNum:'',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['贵阳', '六盘水', '遵义', '安顺', '铜仁', '黔西南', '毕节', '黔东南', '黔南'],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '湖南',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['长沙', '株洲', '湘潭', '衡阳', '邵阳', '岳阳', '常德', '张家界', '益阳', '郴州',
							'永州', '怀化', '娄底', '湘西'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '湖北',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						averageLevelNum:'',
						totalLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['武汉', '黄石', '十堰', '宜昌', '襄阳', '鄂州', '荆门', '孝感', '荆州', '黄冈', '咸宁',
							'随州', '恩施', '仙桃', '潜江', '天门', '神农架林区', '襄樊'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '云南',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['昆明', '曲靖', '玉溪', '保山', '昭通', '丽江', '普洱', '临沧', '楚雄', '红河', '文山',
							'西双版纳', '大理', '德宏', '怒江', '迪庆'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '广西',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['南宁', '柳州', '桂林', '梧州', '北海', '防城港', '钦州', '贵港', '玉林', '百色',
							'贺州', '河池', '来宾', '崇左'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '广东',
						totalLevelNum:'',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['广州', '韶关', '深圳', '珠海', '汕头', '佛山', '江门', '湛江', '茂名', '肇庆', '惠州',
							'梅州', '汕尾', '河源', '阳江', '清远', '东莞', '中山', '东沙群岛', '潮州', '揭阳', '云浮'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '澳门',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						averageLevelNum:'',
						totalLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['澳门', '离岛'],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '香港',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['香港岛', '香港岛', '新界'],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '海南',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['海口', '三亚', '三沙', '五指山', '琼海', '儋州', '文昌', '万宁', '东方', '定安县',
							'屯昌县', '澄迈县', '临高县', '白沙', '昌江', '乐东', '陵水', '保亭', '琼中'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '台湾',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						averageLevelNum:'',
						totalLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['台北', '高雄', '台南', '台中', '金门县', '南投县', '基隆', '新竹', '嘉义', '新北', '宜兰县',
							'新竹县', '桃园县', '苗栗县', '彰化县', '嘉义县', '云林县', '屏东县', '台东县', '花莲县', '澎湖县', '连江县'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '福建',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['福州', '厦门', '莆田', '三明', '泉州', '漳州', '南平', '龙岩', '宁德'],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '江西',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['南昌', '景德镇', '萍乡', '九江', '新余', '鹰潭', '赣州', '吉安', '宜春', '抚州',
							'上饶'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '浙江',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['杭州', '宁波', '温州', '嘉兴', '湖州', '绍兴', '金华', '衢州', '舟山', '台州',
							'丽水'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '上海',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['上海'],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '江苏',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['南京', '无锡', '徐州', '常州', '苏州', '南通', '连云港', '淮安', '盐城', '扬州',
							'镇江', '泰州', '宿迁'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '安徽',
						totalLevelNum:'',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['合肥', '芜湖', '蚌埠', '淮南', '马鞍山', '淮北', '铜陵', '安庆', '黄山', '滁州',
							'阜阳', '宿州', '六安', '亳州', '池州', '宣城'
						],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
					{
						id: '',
						locationName: '南海诸岛',
						totalVoteUpNum:'',
						averageVoteUpNum:'',
						totalLevelNum:'',
						averageLevelNum:'',
						locationlevelNum: [{
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, {
							levelNum: '',
						}, ],
						cityLocationName: ['合肥'],
						locationPersonNum: '',
						locationMaleNum: '',
						locationFemaleNum: '',
						locationYear: '',
						locationMonth: '',
					},
				],
				//echart 配制option  
				options: {
					tooltip: {
						triggerOn: "mousemove", //mousemove、click
						padding: 8,
						borderWidth: 1,
						borderColor: '#409eff',
						backgroundColor: 'rgba(255,255,255,0.7)',
						textStyle: {
							color: '#000000',
							fontSize: 13
						},
						formatter: function(e, t, n) { // eslint-disable-line no-unused-vars 
							let data = e.data;
							// console.log(data);
							// console.log(e);
							// console.log(t);
							// console.log(n);
							//模拟数据
							// data.specialImportant = Math.random() * 1000 | 0;
							// data.import = Math.random() * 1000 | 0;
							// data.compare = Math.random() * 1000 | 0;
							// data.common = Math.random() * 1000 | 0;
							// data.specail = Math.random() * 1000 | 0;

							let context = `
               <div>
                   <p><b style="font-size:15px;">${data.name}</b>(2021年11月份)</p>
                   <p class="tooltip_style"><span class="tooltip_left">总人数</span><span class="tooltip_right">${data.value}</span></p>
                   <p class="tooltip_style"><span class="tooltip_left">男性人数</span><span class="tooltip_right">${data.maleNum}</span></p>
                   <p class="tooltip_style"><span class="tooltip_left">女性人数</span><span class="tooltip_right">${data.femaleNum}</span></p>
                   <p class="tooltip_style"><span class="tooltip_left">男女比例</span><span class="tooltip_right">${data.maleFemaleRate}</span></p>
               </div>
            `
							return context;
						}
					},
					visualMap: {
						show: true,
						left: 26,
						bottom: 40,
						showLabel: true,
						pieces: [{
								gte: 1000,
								label: ">= 1000",
								color: "#1f307b"
							},
							{
								gte: 500,
								lt: 999,
								label: "500 - 999",
								color: "#3c57ce"
							},
							{
								gte: 100,
								lt: 499,
								label: "100 - 499",
								color: "#6f83db"
							},
							{
								gte: 10,
								lt: 99,
								label: "10 - 99",
								color: "#9face7"
							},
							{
								lt: 10,
								label: '<10',
								color: "#bcc5ee"
							}
						]
					},
					geo: {
						map: "china",
						scaleLimit: {
							min: 1,
							max: 2
						},
						zoom: 1,
						top: 120,
						label: {
							normal: {
								show: true,
								fontSize: "14",
								color: "rgba(0,0,0,0.7)"
							}
						},
						itemStyle: {
							normal: {
								//shadowBlur: 50,
								//shadowColor: 'rgba(0, 0, 0, 0.2)',
								borderColor: "rgba(0, 0, 0, 0.2)"
							},
							emphasis: {
								areaColor: "#f2d5ad",
								shadowOffsetX: 0,
								shadowOffsetY: 0,
								borderWidth: 0
							}
						}
					},
					series: [{
						name: "突发事件",
						type: "map",
						geoIndex: 0,
						data: []
					}]
				},
				//echart data
				dataList: [{
						name: "南海诸岛",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
						// common: 40,
						// specail: 20
					},
					{
						name: "北京",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "天津",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "上海",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "重庆",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "河北",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "河南",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "云南",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "辽宁",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "黑龙江",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "湖南",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "安徽",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "山东",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "新疆",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "江苏",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "浙江",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "江西",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "湖北",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "广西",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "甘肃",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "山西",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "内蒙古",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "陕西",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "吉林",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "福建",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "贵州",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "广东",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "青海",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "西藏",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "四川",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "宁夏",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "海南",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "台湾",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "香港",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					},
					{
						name: "澳门",
						value: 100,
						maleNum: 100,
						femaleNum: 10,
						maleFemaleRate: 10,
					}
				],
				//echart 配制option
				options1: {
					tooltip: {
						triggerOn: "mousemove", //mousemove、click
						padding: 8,
						borderWidth: 1,
						borderColor: '#409eff',
						backgroundColor: 'rgba(255,255,255,0.7)',
						textStyle: {
							color: '#000000',
							fontSize: 13
						},
						formatter: function(e, t, n) { // eslint-disable-line no-unused-vars 
							let data = e.data;
							console.log(data);
							// console.log("aaaaa111" + data)
				// 			let context = `
				//    <div>
				//        <p><b style="font-size:15px;">${data.name}</b>(2021年第11月)</p>
				//        <p class="tooltip_style"><span class="tooltip_left">平均等级</span><span class="tooltip_right">${data.value}</span></p>
				//      <p v-for='(o,index) in ${data.levelNum}' :key="index" class="tooltip_style">
				// 	 <span class="tooltip_left">{{}}级人数</span>
				// 	 <span class="tooltip_right">{{o.level}}</span>
				// 	 </p>
				//    </div>
				// `
				let context = `
				   <div>
				       <p><b style="font-size:15px;">${data.name}</b>(2021年第11月)</p>
				       <p class="tooltip_style"><span class="tooltip_left">平均等级</span><span class="tooltip_right">${data.value}</span></p>
				     <p class="tooltip_style">
					 <span class="tooltip_left">1级人数</span>
					 <span class="tooltip_right">${data.levelNum[0].levelNum}</span>
					  <br/>
					 <span class="tooltip_left">2级人数</span>
					 <span class="tooltip_right">${data.levelNum[1].levelNum}</span>
					  <br/>
					 <span class="tooltip_left">3级人数</span>
					 <span class="tooltip_right">${data.levelNum[2].levelNum}</span>
					  <br/>
					 <span class="tooltip_left">4级人数</span>
					 <span class="tooltip_right">${data.levelNum[3].levelNum}</span>
					  <br/>
					 <span class="tooltip_left">5级人数</span>
					 <span class="tooltip_right">${data.levelNum[4].levelNum}</span>
					  <br/>
					 <span class="tooltip_left">6级人数</span>
					 <span class="tooltip_right">${data.levelNum[5].levelNum}</span>
					  <br/>
					 <span class="tooltip_left">7级人数</span>
					 <span class="tooltip_right">${data.levelNum[6].levelNum}</span>
					  <br/>
					 <span class="tooltip_left">8级人数</span>
					  
					 <span class="tooltip_right">${data.levelNum[7].levelNum}</span>
					 <br/>
					 <span class="tooltip_left">9级人数</span>
					 <span class="tooltip_right">${data.levelNum[8].levelNum}</span>
					  <br/>
					 <span class="tooltip_left">10级人数</span>
					 <span class="tooltip_right">${data.levelNum[9].levelNum}</span>
					  <br/>
					 </p>
				   </div>
				`
							return context;
						}
					},
					visualMap: {
						show: true,
						left: 26,
						bottom: 40,
						showLabel: true,
						pieces: [
							{
								gt: 7,
								lte: 10,
								label: "7 < x <= 10级",
								color: "#e55039"
							},
							{
								gt: 6,
								lte: 7,
								label: "6 < x <= 7级",
								color: "#ff9999"
							},
							{
								gt: 5,
								lte: 6,
								label: "5 < x <= 6级",
								color: "#fa983a"
							},
							{
								gt: 4,
								lte: 5,
								label: "4 < x <= 5级",
								color: "#f8c291"
							},
							{
								lte: 4,
								label: '4级以下',
								color: "#f7d794"
							}
						]
					},
					geo: {
						map: "china",
						scaleLimit: {
							min: 1,
							max: 2
						},
						zoom: 1,
						top: 120,
						label: {
							normal: {
								show: true,
								fontSize: "14",
								color: "rgba(0,0,0,0.7)"
							}
						},
						itemStyle: {
							normal: {
								//shadowBlur: 50,
								//shadowColor: 'rgba(0, 0, 0, 0.2)',
								borderColor: "rgba(0, 0, 0, 0.2)"
							},
							emphasis: {
								areaColor: "#f2d5ad",
								shadowOffsetX: 0,
								shadowOffsetY: 0,
								borderWidth: 0
							}
						}
					},
					series: [{
						name: "突发事件",
						type: "map",
						geoIndex: 0,
						data: []
					}]
				},
				//echart data
				dataList1: [{
						name: "南海诸岛",
						value: 1.3,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "北京",
						value: 5.4,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "天津",
						value: 4.0,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "上海",
						value: 400,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "重庆",
						value: 5.0,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "河北",
						value: 6.0,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "河南",
						value: 8.0,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "云南",
						value: 1.1,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "辽宁",

						value: 4.2,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "黑龙江",
						value: 4.0,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "湖南",
						value: 6.9,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "安徽",
						value: 1.2,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "山东",
						value: 3.9,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "新疆",
						value: 1.2,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "江苏",
						value: 3.1,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "浙江",
						value: 1.4,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "江西",
						value: 3.6,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "湖北",
						value: 5.2,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "广西",
						value: 3.3,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "甘肃",
						value: 7.1,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "山西",
						value: 5.1,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "内蒙古",
						value: 1.3,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "陕西",
						value: 2.2,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "吉林",
						value: 4.1,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "福建",
						value: 1.8,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "贵州",
						value: 5.1,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "广东",
						value: 3.8,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "青海",
						value: 1,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "西藏",
						value: 0,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "四川",
						value: 4.4,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "宁夏",
						value: 4.2,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "海南",
						value: 2.2,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "台湾",
						value: 3.3,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "香港",
						value: 5,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					},
					{
						name: "澳门",
						value: 5.1,
						levelNum: [{
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, {
							level: ''
						}, ],
					}
				],
				options2: {
					tooltip: {
						triggerOn: "mousemove", //mousemove、click
						padding: 8,
						borderWidth: 1,
						borderColor: '#409fff',
						backgroundColor: 'rgba(255,255,255,0.6)',
						textStyle: {
							color: '#000000',
							fontSize: 13
						},
						formatter: function(e, t, n) { // eslint-disable-line no-unused-vars 
							let data = e.data;
							//模拟数据

							let context = `
				   <div>
				       <p><b style="font-size:15px;">${data.name}</b>(2021年11月份)</p>
				       <p class="tooltip_style"><span class="tooltip_left">人均点赞数</span><span class="tooltip_right">${data.value}</span></p>
				       <p class="tooltip_style"><span class="tooltip_left">总点赞数量</span><span class="tooltip_right">${data.voteUpNum}</span></p>
				   </div>
				`
							return context;
						}
					},
					visualMap: {
						show: true,
						left: 26,
						bottom: 40,
						showLabel: true,
						pieces: [{
								gte: 100000,
								label: ">= 10000",
								color: "#6ab04c"
							},
							{
								gte: 10000,
								lt: 100000,
								label: "500 - 999",
								color: "#20bf6b"
							},
							{
								gte: 1000,
								lt: 10000,
								label: "100 - 499",
								color: "#26ff99"
							},
							{
								gte: 100,
								lt: 1000,
								label: "10 - 99",
								color: "#26de81"
							},
							{
								lt: 10,
								label: '<10',
								color: "#55efc4"
							}
						]
					},
					geo: {
						map: "china",
						scaleLimit: {
							min: 1,
							max: 2
						},
						zoom: 1,
						top: 120,
						label: {
							normal: {
								show: true,
								fontSize: "14",
								color: "rgba(0,0,0,0.7)"
							}
						},
						itemStyle: {
							normal: {
								//shadowBlur: 50,
								//shadowColor: 'rgba(0, 0, 0, 0.2)',
								borderColor: "rgba(0, 0, 0, 0.2)"
							},
							emphasis: {
								areaColor: "#f2d5ad",
								shadowOffsetX: 0,
								shadowOffsetY: 0,
								borderWidth: 0
							}
						}
					},
					series: [{
						name: "突发事件",
						type: "map",
						geoIndex: 0,
						data: []
					}]
				},
				//echart data
				dataList2: [{
						name: "南海诸岛",
						value: 100,
						voteUpNum:'',
					},
					{
						name: "北京",
						value: 1540,
						voteUpNum:'',
					},
					{
						name: "天津",
						value: 130,
						voteUpNum:'',
					},
					{
						name: "上海",
						value: 400,
						voteUpNum:'',
					},
					{
						name: "重庆",
						value: 750,
						voteUpNum:'',
					},
					{
						name: "河北",
						value: 130,
						voteUpNum:'',
					},
					{
						name: "河南",
						value: 830,
						voteUpNum:'',
					},
					{
						name: "云南",
						value: 110,
						voteUpNum:'',
					},
					{
						name: "辽宁",
						value: 19,
						voteUpNum:'',
					},
					{
						name: "黑龙江",
						value: 150,
						voteUpNum:'',
					},
					{
						name: "湖南",
						value: 690,
						voteUpNum:'',
					},
					{
						name: "安徽",
						value: 60,
						voteUpNum:'',
					},
					{
						name: "山东",
						value: 39,
						voteUpNum:'',
					},
					{
						name: "新疆",
						value: 4,
						voteUpNum:'',
					},
					{
						name: "江苏",
						value: 31,
						voteUpNum:'',
					},
					{
						name: "浙江",
						value: 104,
						voteUpNum:'',
					},
					{
						name: "江西",
						value: 36,
						voteUpNum:'',
					},
					{
						name: "湖北",
						value: 52,
						voteUpNum:'',
					},
					{
						name: "广西",
						value: 33,
						voteUpNum:'',
					},
					{
						name: "甘肃",
						value: 7,
						voteUpNum:'',
					},
					{
						name: "山西",
						value: 5,
						voteUpNum:'',
					},
					{
						name: "内蒙古",
						value: 778,
						voteUpNum:'',
					},
					{
						name: "陕西",
						value: 22,
						voteUpNum:'',
					},
					{
						name: "吉林",
						value: 4,
						voteUpNum:'',
					},
					{
						name: "福建",
						value: 18,
						voteUpNum:'',
					},
					{
						name: "贵州",
						value: 5,
						voteUpNum:'',
					},
					{
						name: "广东",
						value: 98,
						voteUpNum:'',
					},
					{
						name: "青海",
						value: 1,
						voteUpNum:'',
					},
					{
						name: "西藏",
						value: 0,
						voteUpNum:'',
					},
					{
						name: "四川",
						value: 44,
						voteUpNum:'',
					},
					{
						name: "宁夏",
						value: 4,
						voteUpNum:'',
					},
					{
						name: "海南",
						value: 22,
						voteUpNum:'',
					},
					{
						name: "台湾",
						value: 3,
						voteUpNum:'',
					},
					{
						name: "香港",
						value: 5,
						voteUpNum:'',
					},
					{
						name: "澳门",
						value: 555,
						voteUpNum:'',
					}
				],
				options3: {
					tooltip: {
						triggerOn: "mousemove", //mousemove、click
						padding: 8,
						borderWidth: 1,
						borderColor: '#409eff',
						backgroundColor: 'rgba(255,255,255,0.7)',
						textStyle: {
							color: '#000000',
							fontSize: 13
						},
						formatter: function(e, t, n) { // eslint-disable-line no-unused-vars 
							let data = e.data;
							//模拟数据
							data.specialImportant = Math.random() * 1000 | 0;
							data.import = Math.random() * 1000 | 0;
							data.compare = Math.random() * 1000 | 0;
							data.common = Math.random() * 1000 | 0;
							data.specail = Math.random() * 1000 | 0;

							let context = `
				   <div>
				       <p><b style="font-size:15px;">${data.name}</b>(2021年11月)</p>
				       <p class="tooltip_style"><span class="tooltip_left">总人数</span><span class="tooltip_right">${data.value}</span></p>
				       <p class="tooltip_style"><span class="tooltip_left">特别重大事件</span><span class="tooltip_right">${data.specialImportant}</span></p>
				       <p class="tooltip_style"><span class="tooltip_left">重大事件</span><span class="tooltip_right">${data.import}</span></p>
				       <p class="tooltip_style"><span class="tooltip_left">较大事件</span><span class="tooltip_right">${data.compare}</span></p>
				       <p class="tooltip_style"><span class="tooltip_left">一般事件</span><span class="tooltip_right">${data.common}</span></p>
				       <p class="tooltip_style"><span class="tooltip_left">特写事件</span><span class="tooltip_right">${data.specail}</span></p>
				   </div>
				`
							return context;
						}
					},
					visualMap: {
						show: true,
						left: 26,
						bottom: 40,
						showLabel: true,
						pieces: [{
								gte: 1000,
								label: ">= 1000",
								color: "#000000"
							},
							{
								gte: 500,
								lt: 999,
								label: "500 - 999",
								color: "#2d3436"
							},
							{
								gte: 100,
								lt: 499,
								label: "100 - 499",
								color: "#636e72"
							},
							{
								gte: 10,
								lt: 99,
								label: "10 - 99",
								color: "#b2bec3"
							},
							{
								lt: 10,
								label: '<10',
								color: "#ffffff"
							}
						]
					},
					geo: {
						map: "china",
						scaleLimit: {
							min: 1,
							max: 2
						},
						zoom: 1,
						top: 120,
						label: {
							normal: {
								show: true,
								fontSize: "14",
								color: "rgba(0,0,0,0.7)"
							}
						},
						itemStyle: {
							normal: {
								//shadowBlur: 50,
								//shadowColor: 'rgba(0, 0, 0, 0.2)',
								borderColor: "rgba(0, 0, 0, 0.2)"
							},
							emphasis: {
								areaColor: "#f2d5ad",
								shadowOffsetX: 0,
								shadowOffsetY: 0,
								borderWidth: 0
							}
						}
					},
					series: [{
						name: "突发事件",
						type: "map",
						geoIndex: 0,
						data: []
					}]
				},
				//echart data
				dataList3: [{
						name: "南海诸岛",
						value: 100,
						eventTotal: 100,
						specialImportant: 10,
						import: 10,
						compare: 10,
						common: 40,
						specail: 20
					},
					{
						name: "北京",
						value: 1540
					},
					{
						name: "天津",
						value: 130
					},
					{
						name: "上海",
						value: 400
					},
					{
						name: "重庆",
						value: 750
					},
					{
						name: "河北",
						value: 130
					},
					{
						name: "河南",
						value: 830
					},
					{
						name: "云南",
						value: 110
					},
					{
						name: "辽宁",
						value: 19
					},
					{
						name: "黑龙江",
						value: 150
					},
					{
						name: "湖南",
						value: 690
					},
					{
						name: "安徽",
						value: 60
					},
					{
						name: "山东",
						value: 39
					},
					{
						name: "新疆",
						value: 4
					},
					{
						name: "江苏",
						value: 31
					},
					{
						name: "浙江",
						value: 104
					},
					{
						name: "江西",
						value: 36
					},
					{
						name: "湖北",
						value: 52
					},
					{
						name: "广西",
						value: 33
					},
					{
						name: "甘肃",
						value: 7
					},
					{
						name: "山西",
						value: 5
					},
					{
						name: "内蒙古",
						value: 778
					},
					{
						name: "陕西",
						value: 22
					},
					{
						name: "吉林",
						value: 4
					},
					{
						name: "福建",
						value: 18
					},
					{
						name: "贵州",
						value: 5
					},
					{
						name: "广东",
						value: 98
					},
					{
						name: "青海",
						value: 1
					},
					{
						name: "西藏",
						value: 0
					},
					{
						name: "四川",
						value: 44
					},
					{
						name: "宁夏",
						value: 4
					},
					{
						name: "海南",
						value: 22
					},
					{
						name: "台湾",
						value: 3
					},
					{
						name: "香港",
						value: 5
					},
					{
						name: "澳门",
						value: 555
					}
				],
				options4: {
					tooltip: {
						triggerOn: "mousemove", //mousemove、click
						padding: 8,
						borderWidth: 1,
						borderColor: '#409eff',
						backgroundColor: 'rgba(255,255,255,0.7)',
						textStyle: {
							color: '#000000',
							fontSize: 13
						},
						formatter: function(e, t, n) { // eslint-disable-line no-unused-vars 
							let data = e.data;
							//模拟数据
							data.specialImportant = Math.random() * 1000 | 0;
							data.import = Math.random() * 1000 | 0;
							data.compare = Math.random() * 1000 | 0;
							data.common = Math.random() * 1000 | 0;
							data.specail = Math.random() * 1000 | 0;

							let context = `
				   <div>
				       <p><b style="font-size:15px;">${data.name}</b>(2020年第一季度)</p>
				       <p class="tooltip_style"><span class="tooltip_left">事件总数</span><span class="tooltip_right">${data.value}</span></p>
				       <p class="tooltip_style"><span class="tooltip_left">特别重大事件</span><span class="tooltip_right">${data.specialImportant}</span></p>
				       <p class="tooltip_style"><span class="tooltip_left">重大事件</span><span class="tooltip_right">${data.import}</span></p>
				       <p class="tooltip_style"><span class="tooltip_left">较大事件</span><span class="tooltip_right">${data.compare}</span></p>
				       <p class="tooltip_style"><span class="tooltip_left">一般事件</span><span class="tooltip_right">${data.common}</span></p>
				       <p class="tooltip_style"><span class="tooltip_left">特写事件</span><span class="tooltip_right">${data.specail}</span></p>
				   </div>
				`
							return context;
						}
					},
					visualMap: {
						show: true,
						left: 26,
						bottom: 40,
						showLabel: true,
						pieces: [{
								gte: 100,
								label: ">= 1000",
								color: "#1f307b"
							},
							{
								gte: 500,
								lt: 999,
								label: "500 - 999",
								color: "#3c57ce"
							},
							{
								gte: 100,
								lt: 499,
								label: "100 - 499",
								color: "#6f83db"
							},
							{
								gte: 10,
								lt: 99,
								label: "10 - 99",
								color: "#9face7"
							},
							{
								lt: 10,
								label: '0 - 10',
								color: "#bcc5ee"
							}
						]
					},
					geo: {
						map: "china",
						scaleLimit: {
							min: 1,
							max: 2
						},
						zoom: 1,
						top: 120,
						label: {
							normal: {
								show: true,
								fontSize: "14",
								color: "rgba(0,0,0,0.7)"
							}
						},
						itemStyle: {
							normal: {
								//shadowBlur: 50,
								//shadowColor: 'rgba(0, 0, 0, 0.2)',
								borderColor: "rgba(0, 0, 0, 0.2)"
							},
							emphasis: {
								areaColor: "#f2d5ad",
								shadowOffsetX: 0,
								shadowOffsetY: 0,
								borderWidth: 0
							}
						}
					},
					series: [{
						name: "突发事件",
						type: "map",
						geoIndex: 0,
						data: []
					}]
				},
				//echart data
				dataList4: [{
						name: "南海诸岛",
						value: 100,
						eventTotal: 100,
						specialImportant: 10,
						import: 10,
						compare: 10,
						common: 40,
						specail: 20
					},
					{
						name: "北京",
						value: 540
					},
					{
						name: "天津",
						value: 130
					},
					{
						name: "上海",
						value: 400
					},
					{
						name: "重庆",
						value: 750
					},
					{
						name: "河北",
						value: 130
					},
					{
						name: "河南",
						value: 830
					},
					{
						name: "云南",
						value: 110
					},
					{
						name: "辽宁",
						value: 19
					},
					{
						name: "黑龙江",
						value: 150
					},
					{
						name: "湖南",
						value: 690
					},
					{
						name: "安徽",
						value: 60
					},
					{
						name: "山东",
						value: 39
					},
					{
						name: "新疆",
						value: 4
					},
					{
						name: "江苏",
						value: 31
					},
					{
						name: "浙江",
						value: 104
					},
					{
						name: "江西",
						value: 36
					},
					{
						name: "湖北",
						value: 52
					},
					{
						name: "广西",
						value: 33
					},
					{
						name: "甘肃",
						value: 7
					},
					{
						name: "山西",
						value: 5
					},
					{
						name: "内蒙古",
						value: 778
					},
					{
						name: "陕西",
						value: 22
					},
					{
						name: "吉林",
						value: 4
					},
					{
						name: "福建",
						value: 18
					},
					{
						name: "贵州",
						value: 5
					},
					{
						name: "广东",
						value: 98
					},
					{
						name: "青海",
						value: 1
					},
					{
						name: "西藏",
						value: 0
					},
					{
						name: "四川",
						value: 44
					},
					{
						name: "宁夏",
						value: 4
					},
					{
						name: "海南",
						value: 22
					},
					{
						name: "台湾",
						value: 3
					},
					{
						name: "香港",
						value: 5
					},
					{
						name: "澳门",
						value: 555
					}
				]
			};
		},
		methods: {

			//获取数据
			async GetData() {
				await axios.post("http://101.34.151.80:9090/all_figure/get_userDraw", {
					"key1": "location-gender",
					"key2": "gender-location",
					"key_value1": 2021,
					"key_value2": 11
				}).then(res => {
					this.personNum = 0;
					this.maleNum = 0;
					this.femaleNum = 0;
					this.nodesignNum = 0;
					this.foreignNum = 0;
					this.foreignMaleNum = 0;
					this.foreignFemaleNum = 0;
					this.nodesignNum1 = 0;
					this.chinaNum = 0;

					// let arrLists = [{
					// 	id: '',
					// 	location: '',
					// 	locationPersonNum: '',
					// }];
					// 前四条数据
					for (let i = 0; i < res.data.data.length; i++) {
						this.personNum = this.personNum + res.data.data[i].num;
						if (res.data.data[i].key_value2 === '男') {
							this.maleNum = this.maleNum + res.data.data[i].num;
						} else if (res.data.data[i].key_value2 === '女') {
							this.femaleNum = this.femaleNum + res.data.data[i].num;
						} else if (res.data.data[i].key_value2 === '未知') {
							this.nodesignNum = this.nodesignNum + res.data.data[i].num;
						}
					}

					for (let k = 0; k < this.locationInfoLists.length; k++) { //遍历整个中国数组
						this.locationInfoLists[k].locationPersonNum = 0; //设置各省份人数默认值为0
						this.locationInfoLists[k].locationMaleNum = 0; //设置各省份男人数默认值为0
						this.locationInfoLists[k].locationFemaleNum = 0; //设置各省份女人数默认值为0
						this.d.push(this.locationInfoLists[k].locationName);
						for (let j = 0; j < this.locationInfoLists[k].cityLocationName.length; j++) {
							this.d.push(this.locationInfoLists[k].cityLocationName[j]);
						}
					}
					// console.log(this.d);
					// console.log(this.d.includes('北京'))
					for (let k = 0; k < this.locationInfoLists.length; k++) { //遍历整个中国数组
						this.locationInfoLists[k].locationPersonNum = 0; //设置各省份人数默认值为0
						this.locationInfoLists[k].locationMaleNum = 0; //设置各省份男人数默认值为0
						this.locationInfoLists[k].locationFemaleNum = 0; //设置各省份女人数默认值为0


						for (let j = 0; j < this.locationInfoLists[k].cityLocationName.length; j++) {
							for (let i = 0; i < res.data.data.length; i++) { //遍历获取到的数值
								if (res.data.data[i].key_value1 !== null) {
									//如果城名或者省会名能和获取的数据地点进行匹配的话执行下面
									if (this.locationInfoLists[k].cityLocationName[j] === res.data.data[i]
										.key_value1 || this.locationInfoLists[k].locationName === res.data
										.data[i].key_value1) {
										this.locationInfoLists[k].locationPersonNum = this.locationInfoLists[k]
											.locationPersonNum + res.data.data[i].num;
										if (res.data.data[i].key_value2 === '男') {
											this.locationInfoLists[k].locationMaleNum = this.locationInfoLists[
													k]
												.locationMaleNum + res.data.data[i].num;
										} else if (res.data.data[i].key_value2 === '女') {
											this.locationInfoLists[k].locationFemaleNum = this
												.locationInfoLists[k].locationFemaleNum + res.data.data[i]
												.num;
										}
									}
									// //如果获取的地点不能和中国匹配
									// else if(d.includes(res.data.data[i].key_value1)!==true){
									// 	this.foreignNum = this.foreignNum + res.data.data[i].num;
									// 	if (res.data.data[i].key_value2 === '男') {
									// 		this.foreignMaleNum = this.foreignMaleNum  + res.data.data[i].num;
									// 	} else if (res.data.data[i].key_value2 === '女') {
									// 		this.foreignFemaleNum  = this.foreignFemaleNum + res.data.data[i].num;
									// 	}
									// }

								}
							}
						}
						this.chinaNum = this.chinaNum + this.locationInfoLists[k].locationPersonNum;
						// for (let i = 0; i < res.data.data.length; i++) {//遍历获取到的数值
						// //判断获取到的数值和城名称能不能一致
						// 	if (res.data.data[i].key_value1 !== null) {

						// 		if (this.locationInfoLists[k].locationName === res.data.data[i].key_value1) {
						// 			this.locationInfoLists[k].locationPersonNum = this.locationInfoLists[k]
						// 				.locationPersonNum + res.data.data[i].num;
						// 			if (res.data.data[i].key_value2 === '男') {
						// 				this.locationInfoLists[k].locationMaleNum = this.locationInfoLists[k]
						// 					.locationMaleNum + res.data.data[i].num;
						// 			} else if (res.data.data[i].key_value2 === '女') {
						// 				this.locationInfoLists[k].locationFemaleMaleNum = this
						// 					.locationInfoLists[k].locationFemaleMaleNum + res.data.data[i].num;
						// 			}
						// 		}

						// 	}
						// }
					}
					for (let i = 0; i < res.data.data.length; i++) { //遍历获取到的数值
						////如果获取的地点不能和中国匹配
						if (res.data.data[i].key_value1 !== null) {
							if (this.d.includes(res.data.data[i].key_value1) === false && res.data.data[i]
								.key_value1 === '海外地区') {
								// console.log(res.data.data[i].key_value1 + " " + res.data.data[i].key_value2 +
								// 	" " + res.data.data[i].num)
								this.foreignNum = this.foreignNum + res.data.data[i].num;
								if (res.data.data[i].key_value2 === '男') {
									this.foreignMaleNum = this.foreignMaleNum + res.data.data[i].num;
								} else if (res.data.data[i].key_value2 === '女') {
									this.foreignFemaleNum = this.foreignFemaleNum + res.data.data[i].num;
								}
							}
							if (this.d.includes(res.data.data[i].key_value1) === false && res.data.data[i]
								.key_value1 === '未知') {
								// console.log(res.data.data[i].key_value1 + " " + res.data.data[i].key_value2 +
								// 	" " + res.data.data[i].num)
								this.nodesignNum1 = this.nodesignNum1 + res.data.data[i].num;
								// if (res.data.data[i].key_value2 === '男') {
								// 	this.foreignMaleNum = this.foreignMaleNum + res.data.data[i].num;
								// } else if (res.data.data[i].key_value2 === '女') {
								// 	this.foreignFemaleNum = this.foreignFemaleNum + res.data.data[i].num;
								// }
							}
						}
					}

					for (let i = 0; i < this.dataList.length; i++) {
						this.dataList[i].value = 0;
						this.dataList[i].femaleNum = 0;
						this.dataList[i].maleNum = 0;
						this.dataList[i].maleFemaleRate = 0;
						for (let j = 0; j < this.locationInfoLists.length; j++) {
							if (this.dataList[i].name === this.locationInfoLists[j].locationName) {
								// console.log('进来了');
								this.dataList[i].value = this.locationInfoLists[j].locationPersonNum;
								this.dataList[i].femaleNum = this.locationInfoLists[j].locationFemaleNum;
								this.dataList[i].maleNum = this.locationInfoLists[j].locationMaleNum;
								this.dataList[i].maleFemaleRate = (this.locationInfoLists[j].locationMaleNum /
									this.locationInfoLists[j].locationFemaleNum).toFixed(2);
								// console.log(typeof(this.locationInfoLists[j].locationMaleNum));
								// console.log(this.locationInfoLists[j].locationFemaleNum);
								// console.log(typeof(this.locationInfoLists[j].locationFemaleNum));
								// console.log((this.locationInfoLists[j].locationMaleNum / this.locationInfoLists[j].locationFemaleNum).toFixed(2));
								// parseFloat((Number(this.locationInfoLists[j].locationMaleNum) /
								// 	Number(this.locationInfoLists[j].locationFeMmaleNum)));
							}
						}
					}
					// console.log(this.dataList);
					// console.log(this.locationInfoLists);

					// else if (this.locationInfoLists[k].locationName !== res.data.data[i].key_value1) {
					// 	this.foreignNum = this.foreignNum + res.data.data[i].num;
					// 	if (res.data.data[i].key_value2 === '男') {
					// 		this.foreignMaleNum = this.foreignMaleNum + res.data.data[i].num;
					// 	} else if (res.data.data[i].key_value2 === '女') {
					// 		this.foreignFemaleNum = this.foreignFemaleNum + res.data.data[i].num;
					// 	}
					// }
					// for(let i = 0; i < res.data.data.length; i++)
					// {
					// for (let k = 0; k < this.locationInfoLists.length; k++){
					// 	if(res.data.data[i].key_value1!==this.locationInfoLists[k].locationName){

					// 	}

					// }	
					// }

					// console.log(this.personNum);
					// console.log('maleNum' + this.maleNum);
					// console.log(this.femaleNum);
					// console.log(this.nodesignNum);
					// console.log(this.locationInfoLists.length);
					// this.personLists = res.data.data;
					// this.personLists1 = res.data.data;
					// console.log(res.data.data[0]);
					// console.log(this.personLists);
					// console.log(this.personLists1);
					
				})
				console.log(this.locationInfoLists);
				this.setEchartOption();
				this.initEchartMap();
				
			},
			async GetData1() {
				await axios.post("http://101.34.151.80:9090/all_figure/get_userDraw", {
					"key1": "level-location",
					"key2": "location-level",
					"key_value1": 2021,
					"key_value2": 11
				}).then(res => {
					// totalLevel: [{
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, ],
					let allPerson = 0;//所有人数
					let allLevel = 0;//所有等级
					// console.log("woshishabi"+this.locationInfoLists);
					for(let i = 0;i<this.totalLevel.length;i++){
						this.totalLevel[i].levelNum = 0;
					}//初始化
					
					for(let i = 0;i<res.data.data.length;i++){
						allPerson = allPerson +res.data.data[i].num;
						allLevel = allLevel + res.data.data[i].key_value1*res.data.data[i].num;
						for(let k = 1;k<=10;k++){
							if(res.data.data[i].key_value1 == k){
								this.totalLevel[k-1].levelNum = this.totalLevel[k-1].levelNum + res.data.data[i].num;
							}
						}
					}
					
					this.averageLevel = (allLevel/allPerson)//所有人数的平均等级
					this.personNumForLevel  = allPerson;
					// locationInfoLists: [{ //中国每个省份的具体信息
					// 		id: '',
					// 		locationName: '北京',
					// 		cityLocationName: ['北京'],
					// totalLevelNum
					// 		averageLevelNum:'6',
					// 		locationlevelNum: [{
					// 			levelNum: '',
					// 		}, {
					// 			levelNum: '',
					// 		}, {
					// 			levelNum: '',
					// 		}, {
					// 			levelNum: '',
					// 		}, {
					// 			levelNum: '',
					// 		}, {
					// 			levelNum: '',
					// 		}, {
					// 			levelNum: '',
					// 		}, {
					// 			levelNum: '',
					// 		}, {
					// 			levelNum: '',
					// 		}, {
					// 			levelNum: '',
					// 		}, ],
					// 		locationPersonNum: '',
					// 		locationMaleNum: '',
					// 		locationFemaleNum: '',
					// 		locationYear: '',
					// 		locationMonth: '',
					// 	},
					// 向locationInfoList里的locationlevelNum赋值
					// console.log(this.locationInfoLists)
					for (let k = 0; k < this.locationInfoLists.length; k++) { 
						for(let m = 1;m<=10;m++){
						this.locationInfoLists[k].locationlevelNum[m-1].levelNum = 0;
						}
						}
					for (let k = 0; k < this.locationInfoLists.length; k++) { 
						this.locationInfoLists[k].totalLevelNum = 0;
						this.locationInfoLists[k].averageLevelNum = 0;
						// console.log(this.locationInfoLists)
						for(let j = 0;j<this.locationInfoLists[k].cityLocationName.length;j++){
							for(let i = 0;i < res.data.data.length;i++){
								if(this.locationInfoLists[k].cityLocationName[j] == res.data.data[i].key_value2 || this.locationInfoLists[k].locationName == res.data.data[i].key_value2){
												this.locationInfoLists[k].totalLevelNum = this.locationInfoLists[k].totalLevelNum + res.data.data[i].key_value1*res.data.data[i].num;
												for(let m = 1;m<=10;m++){
												
													if(m==res.data.data[i].key_value1){
														this.locationInfoLists[k].locationlevelNum[m-1].levelNum = this.locationInfoLists[k].locationlevelNum[m-1].levelNum + res.data.data[i].num;
													}
												}
												
											}
							}
						}
						this.locationInfoLists[k].averageLevelNum = this.locationInfoLists[k].totalLevelNum/this.locationInfoLists[k].locationPersonNum;
						}
						
						
					// 	totalLevelNum = 0;//遍历中国省份信息
					// console.log(this.locationInfoLists[k].locationlevelNum
					// 		.length);
					// 	for (let i = 0; i < this.locationInfoLists[k].locationlevelNum
					// 		.length; i++) { //遍历每个省份的level数组
					// 		this.locationInfoLists[k].locationlevelNum[i].levelNum = 0;
					// 		for (let j = 0; j < res.data.data.length; j++) {
					// 			if ((i + 1)==(res.data.data[j].key_value1) && this.d.includes(res.data.data[
					// 						j].key_value2)==true) {
					// 						// 	console.log(i+1);
					// 						// 	console.log(res.data.data[j].key_value1)
					// 						// 	console.log(res.data.data[
					// 						// j].key_value2);
					// 				this.locationInfoLists[k].locationlevelNum[i].levelNum = this
					// 					.locationInfoLists[k].locationlevelNum[i].levelNum + res.data.data[j]
					// 					.num;
					// 				n = n + this.res.data.data[j].num;
					// 			}
					// 		}
					// 		// console.log()
					// 		// console.log(n);
					// 		totalLevelNum = totalLevelNum + (i+1)*this.locationInfoLists[k].locationlevelNum[i];
							
					// 		// console.log(this.locationInfoLists[k].average)
					// 	}
					// 	this.locationInfoLists[k].average = (totalLevelNum/n).toFixed(2);
					// }
					
					// dataList1: [{
					// 		name: "南海诸岛",
					// 		value: 1.3,
					// 		levelNum: [{
					// 			level: ''
					// 		}, {
					// 			level: ''
					// 		}, {
					// 			level: ''
					// 		}, {
					// 			level: ''
					// 		}, {
					// 			level: ''
					// 		}, {
					// 			level: ''
					// 		}, {
					// 			level: ''
					// 		}, {
					// 			level: ''
					// 		}, {
					// 			level: ''
					// 		}, {
					// 			level: ''
					// 		}, {
					// 			level: ''
					// 		}, ],
					// 	},
		
					for (let i = 0; i < this.dataList1.length; i++) {
						this.dataList1[i].value = 0;
						for (let j = 0; j < this.locationInfoLists.length; j++) {
							if (this.dataList1[i].name === this.locationInfoLists[j].locationName) {
								// console.log(this.locationInfoLists[j]);
								this.dataList1[i].value = this.locationInfoLists[j].averageLevelNum;
								for(let k = 0;k<this.dataList1[i].levelNum.length;k++)
								this.dataList1[i].levelNum[k] = this.locationInfoLists[j].locationlevelNum[k];
						}
						}
						}
						console.log(this.locationInfoLists);
						console.log(this.dataList1)

					// console.log(res.data.data[0].key_value)
					// console.log(this.d);
					// console.log(this.locationInfoLists);
					// res.data.data[i].key_value1
					// locationInfoLists: [{ //中国每个省份的具体信息
					// 						id: '',
					// 						locationName: '北京',
					// 						cityLocationName: ['北京'],
					// 						locationlevelNum: [{
					// 							levelNum: '',
					// 						}, {
					// 							levelNum: '',
					// 						}, {
					// 							levelNum: '',
					// 						}, {
					// 							levelNum: '',
					// 						}, {
					// 							levelNum: '',
					// 						}, {
					// 							levelNum: '',
					// 						}, {
					// 							levelNum: '',
					// 						}, {
					// 							levelNum: '',
					// 						}, {
					// 							levelNum: '',
					// 						}, {
					// 							levelNum: '',
					// 						}, ],
					// 						locationPersonNum: '',
					// 						locationMaleNum: '',
					// 						locationFemaleNum: '',
					// 						locationYear: '',
					// 						locationMonth: '',
					// 					},

				})
				this.setEchartOption();
				this.initEchartMap();
			},
			async GetData2() {
			await axios.post("http://101.34.151.80:9090/all_figure/get_userDraw", {
					"key1": "voteup_count-location",
					"key2": "location-voteup_count",
					"key_value1": 2021,
					"key_value2": 11
				}).then(res => {
					// totalLevel: [{
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, ],
					// averageVoteUp:'',
					// personNumForVoteUp:'',
					let allPerson = 0;//所有人数
					// let allLevel = 0;//所有等级
					let allVoteUp = 0;//所有点赞
					// console.log("woshishabi"+this.locationInfoLists);
					// for(let i = 0;i<this.totalLevel.length;i++){
					// 	this.totalLevel[i].levelNum = 0;
					// }//初始化
					
					for(let i = 0;i<res.data.data.length;i++){
						allPerson = allPerson +res.data.data[i].num;
						allVoteUp = allVoteUp + res.data.data[i].key_value1*res.data.data[i].num;
					}

					this.averageVoteUp = (allVoteUp/allPerson)//所有人数的平均等级
					this.personNumForVoteUp  = allPerson;
					// 向locationInfoList里的locationlevelNum赋值
				
					// console.log(this.locationInfoLists)
					for (let k = 0; k < this.locationInfoLists.length; k++) { 
						this.locationInfoLists[k].totalVoteUpNum = 0;
						this.locationInfoLists[k].averageVoteUpNum = 0;
						// console.log(this.locationInfoLists)
						for(let j = 0;j<this.locationInfoLists[k].cityLocationName.length;j++){
							for(let i = 0;i < res.data.data.length;i++){
								if(this.locationInfoLists[k].cityLocationName[j] == res.data.data[i].key_value2 || this.locationInfoLists[k].locationName == res.data.data[i].key_value2){
												this.locationInfoLists[k].totalVoteUpNum = this.locationInfoLists[k].totalVoteUpNum + res.data.data[i].key_value1*res.data.data[i].num;
												for(let m = 1;m<=10;m++){
													if(m==res.data.data[i].key_value1){
														this.locationInfoLists[k].locationlevelNum[m-1].levelNum = this.locationInfoLists[k].locationlevelNum[m-1].levelNum + res.data.data[i].num;
													}
												}
												
											}
							}
						}
						this.locationInfoLists[k].averageVoteUpNumNum = this.locationInfoLists[k].totalVoteUpNum/this.locationInfoLists[k].locationPersonNum;
						}
					for (let i = 0; i < this.dataList2.length; i++) {
						this.dataList2[i].value = 0;
						for (let j = 0; j < this.locationInfoLists.length; j++) {
							if (this.dataList2[i].name === this.locationInfoLists[j].locationName) {
								// console.log(this.locationInfoLists[j]);
								this.dataList2[i].value = this.locationInfoLists[j].averageVoteUpNumNum;
								this.dataList2[i].voteUpNum = this.locationInfoLists[j].totalVoteUpNum;
						}
						}
						}
				})
				this.setEchartOption();
				this.initEchartMap();
			},
			async GetData3() {
			await axios.post("http://101.34.151.80:9090/all_figure/get_userDraw", {
					"key1": "business-location",
					"key2": "location-business",
					"key_value1": 2021,
					"key_value2": 11
				}).then(res => {
					// totalLevel: [{
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, {
					// 	levelNum: '',
					// }, ],
					// averageVoteUp:'',
					// personNumForVoteUp:'',
					let allPerson = 0;//所有人数
					// let allLevel = 0;//所有等级
					let allVoteUp = 0;//所有点赞
					
					// console.log("woshishabi"+this.locationInfoLists);
					// for(let i = 0;i<this.totalLevel.length;i++){
					// 	this.totalLevel[i].levelNum = 0;
					// }//初始化
					
					for(let i = 0;i<res.data.data.length;i++){
						this.businessMap.push(res.data.data[i].key_value1)
					}
					
					for(let i=0; i<this.businessMap.length; i++){
						for(var j=i+1; j<this.businessMap.length; j++){
							if(this.businessMap[i]==this.businessMap[j]){         //第一个等同于第二个，splice方法删除第二个
							this.businessMap.splice(j,1);
							j--;
							}
							}
							}
							let m = [];
							for(let i=0; i<this.businessMap.length; i++){
								
								m[i] = 0;
					for(let j = 0;j<res.data.data.length;j++){
					
						if(this.businessMap[i] == res.data.data[j].key_value1)
						{
							m[i] = m[i] + res.data.data[j].num;
						}
						}	
						console.log(m[i]);
						}
							// this.businessMap1.length  = this.businessMap.length;
							for(let i = 0;i<this.businessMap.length;i++){
								this.businessMap1.push({
									name:this.businessMap[i],
									value:m[i],
								})
								// this.businessMap1[i].name = this.businessMap[i];
							}
							console.log(this.businessMap1)
			// console.log('1111'+this.businessMap);
					this.averageVoteUp = (allVoteUp/allPerson)//所有人数的平均等级
					this.personNumForVoteUp  = allPerson;
					// 向locationInfoList里的locationlevelNum赋值
				
					// console.log(this.locationInfoLists)
					for (let k = 0; k < this.locationInfoLists.length; k++) { 
						this.locationInfoLists[k].totalVoteUpNum = 0;
						this.locationInfoLists[k].averageVoteUpNum = 0;
						// console.log(this.locationInfoLists)
						for(let j = 0;j<this.locationInfoLists[k].cityLocationName.length;j++){
							for(let i = 0;i < res.data.data.length;i++){
								if(this.locationInfoLists[k].cityLocationName[j] == res.data.data[i].key_value2 || this.locationInfoLists[k].locationName == res.data.data[i].key_value2){
												this.locationInfoLists[k].totalVoteUpNum = this.locationInfoLists[k].totalVoteUpNum + res.data.data[i].key_value1*res.data.data[i].num;
												for(let m = 1;m<=10;m++){
													if(m==res.data.data[i].key_value1){
														this.locationInfoLists[k].locationlevelNum[m-1].levelNum = this.locationInfoLists[k].locationlevelNum[m-1].levelNum + res.data.data[i].num;
													}
												}
												
											}
							}
						}
						this.locationInfoLists[k].averageVoteUpNumNum = this.locationInfoLists[k].totalVoteUpNum/this.locationInfoLists[k].locationPersonNum;
						}
					for (let i = 0; i < this.dataList2.length; i++) {
						this.dataList2[i].value = 0;
						for (let j = 0; j < this.locationInfoLists.length; j++) {
							if (this.dataList2[i].name === this.locationInfoLists[j].locationName) {
								// console.log(this.locationInfoLists[j]);
								this.dataList2[i].value = this.locationInfoLists[j].averageVoteUpNumNum;
								this.dataList2[i].voteUpNum = this.locationInfoLists[j].totalVoteUpNum;
						}
						}
						}
				})
				this.setEchartOption();
				this.initEchartMap();
			},

			//初始化中国地图
			initEchartMap() {
				let mapDiv = document.getElementById('china_map');
				let mapDiv1 = document.getElementById('china_map1');
				let mapDiv2 = document.getElementById('china_map2');
				let mapDiv3 = document.getElementById('china_map3');
				// let mapDiv4 = document.getElementById('china_map4');
				mapDiv.style.width = window.innerWidth + 'px';
				mapDiv1.style.width = window.innerWidth + 'px';
				mapDiv2.style.width = window.innerWidth + 'px';
				mapDiv3.style.width = window.innerWidth + 'px';
				let myChart = echarts.init(mapDiv);
				let myChart1 = echarts.init(mapDiv1);
				let myChart2 = echarts.init(mapDiv2);
				let myChart3 = echarts.init(mapDiv3);
				// window.onresize = function () {
				// myChart.resize();
				// };
				myChart.setOption(this.options);
				myChart1.setOption(this.options1);
				myChart2.setOption(this.options2);
				myChart3.setOption(this.options3);
			},
			//修改echart配制
			setEchartOption() {
				// console.log("修改图表了")
				this.options.series[0]['data'] = this.dataList;
				this.options1.series[0]['data'] = this.dataList1;
				// console.log("--------------------")
				// console.log(this.options1.series[0]['data'])
				// console.log("--------------------")
				// console.log(this.data);
				// console.log("--------------------")
				// console.log(this.dataList1)
				this.options2.series[0]['data'] = this.dataList2;
				this.options3.series[0]['data'] = this.dataList3;
				// console.log("--------------------")
				// console.log(this.options.series[0]['data'])
				// console.log("--------------------")

			}
		},
		created() {
			this.setEchartOption();
		},
		mounted() {
			this.$nextTick(() => {
				this.initEchartMap();
			})
			this.GetData();
			// setTimeout("alert('对不起, 要你久候')", 10000 )
			this.GetData1();
			this.GetData2();
			this.GetData3();
		}
	};
</script>

<style>
	#china_map {
		width: 100%;
		height: 800px;
	}

	#china_map1 {
		width: 100%;
		height: 800px;
	}

	#china_map2 {
		width: 100%;
		height: 800px;
	}

	#china_map3 {
		width: 100%;
		height: 800px;
	}

	#china_map4 {
		width: 100%;
		height: 800px;
	}

	.el-dropdown {
		vertical-align: top;
	}

	.el-dropdown+.el-dropdown {
		margin-left: 15px;
	}

	.el-icon-arrow-down {
		font-size: 12px;
	}
</style>
