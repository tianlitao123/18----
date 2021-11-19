<!--
 * @Author: sun
 * @Date: 2021/11/6 15:49
 * @title: UserPage
 * @Description:
 * @version: 1.0
 -->
<template>
    <div class="body">
        <div id="HeadBackground" >
               <div id="Header-up"></div>

               <div v-bind:class="showBigHeader?'bigHeader':'header'" id="Header-down">
                   <div class="Bussiness" v-if="UserData.business!=''"><a id="Bussiness" >职业:{{UserData.business}}</a></div>
                   <div  class="Gender" v-if="UserData.gender!=''"><a id="Gender">用户性别:{{UserData.gender}}</a></div>
                   <div  class="Description" v-if="UserData.description!=''"> <a id="Description">{{UserData.description}}</a></div>
                   <div class="Headline" v-if="UserData.headline!=''"> <a id="Headline" >{{UserData.headline}}</a> </div>
                   <div class="Location" v-if="UserData.location!=''"> <a id="Location" >居住地:{{UserData.location}}</a> </div>
               </div>

            <div>
                <el-avatar :size="150" v-bind:src="UserData.avatar_url" shape="square" class="UserPicture" @click="GetData"></el-avatar>
                <div class="UserName"><a id="UserName" >{{UserData.name}}</a></div>
            </div>

        </div>

        <div class="contant" >
            <div id="Kind">
                <div id="UserMessage" style="width: 500px;height: 500px;"/>
                <div id="KindCakeMap" style="width: 500px;height: 500px;"/>
                <div id="KindBrokenMap" style="width: 500px;height: 500px;"/>
                <div><a id="KindSuggest">{{kindSuggest}}</a></div>
<!--                <div id="history">-->
<!--                    <el-select v-model="value" placeholder="Select">-->
<!--                        <el-option-->
<!--                                v-for="item in this.control"-->
<!--                                :key="item.value"-->
<!--                                :label="item.label"-->
<!--                                :value="item.value"-->
<!--                        >-->
<!--                        </el-option>-->
<!--                    </el-select>-->
<!--                </div>-->

                <div id="GenderNum" style="width: 500px;height: 500px;"/>
                <div id="GenderLevel" style="width: 500px;height: 500px;"/>
                <div id="LocationLevel" style="width: 500px;height: 500px;"/>
                <div id="ALLLevel" style="width: 500px;height: 500px;"/>
                <div id="AverageAnswer" style="width: 500px;height: 500px;"/>
                <div id="AverageQuestion" style="width: 500px;height: 500px;"/>
                <div id="AverageFollowing" style="width: 500px;height: 500px;"/>
                <div id="AverageFavorite" style="width: 500px;height: 500px;"/>
                <div id="AverageVoteup" style="width: 500px;height: 500px;"/>
                <div id="AverageFollower" style="width: 500px;height: 500px;"/>
                <div id="AllBussiness" style="width: 500px;height: 500px;"/>
                <div id="LocationBussiness" style="width: 500px;height: 500px;"/>
                <div id="Compare" style="width: 500px;height: 500px;"/>
            </div>
        </div>

    </div>
</template>

<script>
    import axios from 'axios'
    import * as echarts from 'echarts'
    import { ElMessageBox, ElMessage } from 'element-plus'
    export default {
        name: "UserPage",

        data(){
            return {
                avatar_url:"https://pic2.zhimg.com/v2-73b02fdb3ef4e07fb7599ecb607f3959_xl.jpg",
                UserData:"",
                // name:"小白一个",
                // level_info_level:0,
                // bussiness_name:"所在行业：计算机",
                // BaseData:[],
                loading: true,
                kindSuggest:"",
                showBigHeader:false,
                OtherData:"",
                GenderLevelWoman:"",
                GenderLevelMan:"",
                GenderLevelX:"",
                LocationManNum:0,
                LocationWomanNum:0,
                LocationXNum:0,
                LocationLevelNum:[],
                AllLevelNum:[],
                AverageAnswer:[],
                AverageQuestion:[],
                AverageFollowing:[],
                AverageVoteup:[],
                AverageFollower:[],
                Bussiness:[],
                LocationBussiness:[],
                userId:"",
                AveerageAllAnswer:0,
                AveerageAllQuestion:0,
                AverageAllFollower:0,
                AverageAllFollowing:0,
                AverageAllVoteup:0,
                AverageALLArticles:0,
                UserLevel:0,
                Usertime:[],
                Timeanswer_count:[],
                Timearticles_count:[],
                Timefavorite_count:[],
                Timefollower_count:[],
                Timefollowing_count:[],
                Timefrequency:[],
                Timequestion_count:[],
                Timevoteup_count:[],
                // control:[{value:"answer_count",label:"回答数量",},{value:"articles_count",label:"文章数量 ",},{value:"favorite_count",label:"粉丝数量",},{value:"follower_count",label:"关注者数量",},{value:"following_count",label:"被关注数量",},{value:"frequency",label:"被检索",},{value:"question_count",label:"提问数量",},{value:"voteup_count",label:"被赞同",}
                // ]

        }
        },
        methods: {
            //获取当地性别人数
            async GetLocationGender(){
                //获取地区男女人数
                axios.post("http://101.34.151.80:9090/all_figure/get_userDraw",
                    {
                        "key1":"location-gender",
                        "key2":"gender-location",
                        "year":2021,
                        "month":11
                    }).then(res=>{
                    let temporary=res.data.data
                    let temporaryWoman=0
                    let temporaryMan=0
                    let temporaryX=0
                    // console.log(temporary)
                    for(let i=0;i<temporary.length;i++){
                        if(temporary[i].key_value1.match(this.UserData.location[0])){
                            if(temporary[i].key_value2=="男"){
                                temporaryMan=temporaryMan+temporary[i].num
                            }else if(temporary[i].key_value2=="女"){
                                temporaryWoman=temporaryWoman+temporary[i].num
                            }else if(temporary[i].key_value2=="未知"){
                                temporaryX=temporaryX+temporary[i].num
                            }
                        }
                    }
                    this.LocationWomanNum=temporaryWoman
                    this.LocationManNum=temporaryMan
                    this.LocationXNum=temporaryX

                   // console.log(this.LocationWomanNum,this.LocationManNum,this.LocationXNum)
                    this.SetLocationGender()
                })



            },

            //获取全站性别等级人数
            async GetAllGenderLevel() {
                axios.post("http://101.34.151.80:9090/all_figure/get_userDraw",
                    {
                        "key1":"level-gender",
                        "key2":"gender-level",
                        "key_value1":2021,
                        "key_value2":11
                    }).then(res=>{
                    // console.log(res.data.data)
                    let li=res.data.data
                    //
                    let man=[]
                    let woman=[]
                    let x=[]
                    let newman=[]
                    let newx=[]
                    let newwoman=[]
                    for(let i=0;i<li.length;i++){
                        if(li[i].key_value2=="女"){
                            woman.push([li[i].key_value1,li[i].num])
                        }
                    }
                    for(let i=0;i<li.length;i++){
                        if(li[i].key_value2=="男"){
                            man.push([li[i].key_value1,li[i].num])
                        }
                    }
                    for(let i=0;i<li.length;i++){
                        if(li[i].key_value2=="未知"){
                            x.push([li[i].key_value1,li[i].num])
                        }
                    }

                    //女性数据处理

                    for(let i=1;i<11;i++){
                        newwoman.push([i,0])
                    }

                    for(let i=0;i<woman.length;i++){
                        for(let j=0;j<newwoman.length;j++){
                            if(newwoman[j][0]==woman[i][0]){
                                newwoman[j][1]=woman[i][1]
                            }
                        }
                    }

                    //男性数据处理

                    for(let i=1;i<11;i++){
                        newman.push([i,0])
                    }

                    for(let i=0;i<man.length;i++){
                        for(let j=0;j<newman.length;j++){
                            if(newman[j][0]==man[i][0]){
                                newman[j][1]=man[i][1]
                            }
                        }
                    }

                    //未知性别处理

                    for(let i=1;i<11;i++){
                        newx.push([i,0])
                    }

                    for(let i=0;i<x.length;i++){
                        for(let j=0;j<newx.length;j++){
                            if(newx[j][0]==x[i][0]){
                                newx[j][1]=x[i][1]
                            }
                        }
                    }
                    this.GenderLevelWoman=newwoman
                    this.GenderLevelMan=newman
                    this.GenderLevelX=newx

                    // console.log(this.GenderLevelMan)
                    this.SetGenderLevel()
                })


            },
            //获取用户个人数据
            async GetUserData() {
                this.userId=this.$route.query.userId


                //console.log(this.$route.query.userId)

                //console.log(this.userId)
                axios.get("http://101.34.151.80:9090/user_check/getUserById2",{params:{'id2':this.userId}}).then((res)=>{
                    console.log("-------------------------------")
                    console.log(res.data)
                    console.log("-------------------------------")
                    if(res.data.code==400){
                        ElMessageBox.alert('查询用户信息失败', 'Title', {
                                    confirmButtonText: '返回',
                                    callback: (action) => {
                                        ElMessage({
                                            type: 'info',
                                            message: `action: ${action}`,
                                        })
                                        this.$router.push('/SearchPage')
                                    },
                                })
                    }else{
                        this.UserData=res.data.data[0]
                        this.UserLevel=this.UserData.level
                        console.log(this.UserData)
                        if(this.UserData.location[0]==""){
                            this.UserData.location="用户未设置，默认为北京"
                        }else{
                            this.UserData.location=this.UserData.location[0]
                        }
                        if ((this.UserData.business!=null)&&(this.UserData.gender!=null)){
                            this.showBigHeader=true
                        }

                        // console.log(this.UserData)
                        this.SetUserMessage()
                        this.SetKindCakedMap();
                        this.SetKindBrokenMap();
                        this.setKindSuggest();
                        this.GetHistory()
                        this.GetLocationGender()
                        this.GetLocationLevel()
                        // await this.GetLocationLevel()
                        this.GetAllGenderLevel()
                        this.GetLevelMessage()
                        this.SetComopare()
                    }

                })
               

                //获取用户个人数据
                    // answer_count: 回答数量
                    // articles_count: 文章数量
                    // avatar_url: 头像地址
                    // business: 公司
                    // columns_count: 0
                    // description: 个人描述
                    // favorite_count: 收藏数量
                    // follower_count: 粉丝数目
                    // following_columns_count: 关注专栏数
                    // following_count: 关注数目
                    // following_favlists_count: 关注文件夹数量
                    // following_question_count: 关注问题数
                    // following_topic_count: 关注话题数
                    // frequency: 被检索频率
                    // gender: 用户性别
                    // headline: 用户头文字
                    // id:
                    // id2: "2"
                    // level: 等级
                    // location: []
                    // name: "kid"
                    // question_count: 提问数量
                    // type: 用户类型
                    // url_token: null
                    // voteup_count: 被赞同次数
                    // zvideo_count: 视频数量
               // })


            },
            //获取等级和各个信息
            async GetLevelMessage(){
                axios.post("http://101.34.151.80:9090/all_figure/get_userDraw",
                    {
                        "key1":"level-answer_count",
                        "key2":"answer_count-level",
                        "key_value1":2021,
                        "key_value2":11
                    }).then(res=>{
                        let temp=res.data.data
                         // console.log(temp)
                        let num=0
                        let answer=0
                        let average=0
                        let st=[]
                        let alnum=0
                        let ala=0
                    //等级循环
                        for(let j=1;j<11;j++){
                            for (let i=0;i<temp.length;i++){
                                if(temp[i].key_value1==j){
                                    ala=ala+temp[i].key_value2*temp[i].num
                                    alnum=alnum+temp[i].num
                                    answer=answer+temp[i].key_value2*temp[i].num
                                    num = num+temp[i].num
                                }
                            }
                            average=answer/num
                            st.push([j,average])
                            num = 0
                            answer=0
                        }

                      this.AveerageAllAnswer=ala/alnum
                      this.AverageAnswer=st
                      // console.log( this.AveerageAllAnswer,this.AverageAnswer)
                      this.SetAverageAnswer()

                })




                axios.post("http://101.34.151.80:9090/all_figure/get_userDraw",
                    {
                        "key1":"level-question_count",
                        "key2":"question_count-level",
                        "key_value1":2021,
                        "key_value2":11
                    }).then(res=>{
                    let temp=res.data.data
                    // console.log(temp)
                    let num=0
                    let answer=0
                    let average=0
                    let st=[]

                    let alnum=0
                    let ala=0
                    //等级循环
                    for(let j=1;j<11;j++){
                        for (let i=0;i<temp.length;i++){
                            if(temp[i].key_value1==j){

                                ala=ala+temp[i].key_value2*temp[i].num
                                alnum=alnum+temp[i].num

                                answer=answer+temp[i].key_value2*temp[i].num
                                num = num+temp[i].num
                            }
                        }
                        average=answer/num
                        st.push([j,average])
                        num = 0
                        answer=0
                    }
                    this.AveerageAllQuestion=ala/alnum
                    this.AverageQuestion=st
                    this.SetAverageQuestion()
                })

                //全站following_count
                axios.post("http://101.34.151.80:9090/all_figure/get_userDraw",
                    {
                        "key1":"level-following_count",
                        "key2":"following_count-level",
                        "key_value1":2021,
                        "key_value2":11
                    }).then(res=> {
                    let temp = res.data.data
                    let num = 0
                    let answer = 0
                    let average = 0
                    let st = []

                    let alnum = 0
                    let ala = 0
                    //等级循环
                    for (let j = 1; j < 11; j++) {
                        for (let i = 0; i < temp.length; i++) {
                            if (temp[i].key_value1 == j) {

                                ala = ala + temp[i].key_value2 * temp[i].num
                                alnum = alnum + temp[i].num

                                answer = answer + temp[i].key_value2 * temp[i].num
                                num = num + temp[i].num
                            }
                        }
                        average = answer / num
                        st.push([j, average])
                        num = 0
                        answer = 0
                    }
                    this.AverageAllFollowing = ala / alnum
                    this.AverageFollowing = st
                    this.SetAverageFollowing()
                })

                    axios.post("http://101.34.151.80:9090/all_figure/get_userDraw",
                        {
                            "key1": "level-voteup_count",
                            "key2": "voteup_count-level",
                            "key_value1": 2021,
                            "key_value2": 11
                        }).then(res => {
                        let temp = res.data.data
                        let num = 0
                        let answer = 0
                        let average = 0
                        let st = []
                        let alnum=0
                        let ala=0
                        //等级循环
                        for (let j = 1; j < 11; j++) {
                            for (let i = 0; i < temp.length; i++) {
                                if (temp[i].key_value1 == j) {

                                    ala=ala+temp[i].key_value2*temp[i].num
                                    alnum=alnum+temp[i].num

                                    answer = answer + temp[i].key_value2 * temp[i].num
                                    num = num + temp[i].num
                                }
                            }
                            average = answer / num
                            st.push([j, average])
                            num = 0
                            answer = 0
                        }

                        this.AverageAllVoteup=ala/alnum
                        this.AverageVoteup = st
                        this.SetAverageVoteup()

                    })

                

                axios.post("http://101.34.151.80:9090/all_figure/get_userDraw",
                    {
                        "key1": "level-follower_count",
                        "key2": "follower_count-level",
                        "key_value1": 2021,
                        "key_value2": 11
                    }).then(res => {
                    let temp = res.data.data
                    let num = 0
                    let answer = 0
                    let average = 0
                    let st = []
                    let alnum=0
                    let ala=0
                    //等级循环
                    for (let j = 1; j < 11; j++) {
                        for (let i = 0; i < temp.length; i++) {
                            if (temp[i].key_value1 == j) {
                                ala=ala+temp[i].key_value2*temp[i].num
                                alnum=alnum+temp[i].num
                                answer = answer + temp[i].key_value2 * temp[i].num
                                num = num + temp[i].num
                            }
                        }
                        average = answer / num
                        st.push([j, average])
                        num = 0
                        answer = 0
                    }
                    this.AverageAllFollower=ala/alnum
                    this.AverageFollower = st
                    console.log(this.AverageFollower)
                    this.SetAverageFollower()

                })


                axios.post("http://101.34.151.80:9090/all_figure/get_userDraw",
                    {
                        "key1": "business",
                        "key_value1": 2021,
                        "key_value2": 11
                    }).then(res => {
                      this.Bussiness  = res.data.data
                    this.SetBussiness()
                    })


            },

            async GetHistory(){
                axios.get("http://101.34.151.80:9090/User_date/getUserAndDate",{params:{'id2':this.userId}}).then((res)=>{
                    console.log(res)
                    var temp = res.data.data[0]
                    var c=[]
                    if(temp.length>7){
                        for (let i=0;i<7;i++){
                            c.push(temp[i])
                        }
                    }else{
                        for (let i=0;i<temp.length;i++){
                            c.push(temp[i])
                        }
                    }

                    for(let i=0;i<c.length;i++){
                        let a=c[i].year+"-"+c[i].month+"-"+c[i].day
                        this.Usertime.push(a)
                        this.Timeanswer_count.push(c[i].answer_count),
                        this.Timearticles_count.push(c[i].articles_count),
                        this.Timefavorite_count.push(c[i].favorite_count),
                        this.Timefollower_count.push(c[i].follower_count)
                        this.Timefollowing_count.push(c[i].following_count)
                        this.Timefrequency.push(c[i].frequency)
                        this.Timequestion_count.push(c[i].question_count)
                        this.Timevoteup_count.push(c[i].voteup_count)
                }})
            },

            async SetHistory(){

            },

            //获取地区
            async GetLocationLevel(){
                axios.post("http://101.34.151.80:9090/all_figure/get_userDraw",
                    {
                        "key1":"location-level",
                        "key2":"level-location",
                        "key_value1":2021,
                        "key_value2":11
                    }).then(res=>{

                        let temporary=res.data.data
                        var ttt=[[1,0],[2,0],[3,0],[4,0],[5,0],[6,0],[7,0],[8,0],[9,0],[10,0]]
                        for (let i=0;i<temporary.length;i++){
                            if (temporary[i].key_value1.match(this.UserData.location)){
                                ttt[temporary[i].key_value2-1][1]= ttt[temporary[i].key_value2-1][1]+temporary[i].num
                            }
                        }

                    this.LocationLevelNum=ttt
                    this.SetLocationLevel()
                })

                axios.post("http://101.34.151.80:9090/all_figure/get_userDraw",
                    {
                        "key1":"level",
                        "key_value1":2021,
                        "key_value2":11
                    }).then(res=>{
                    let temporary=res.data.data
                    var ttt=[[1,0],[2,0],[3,0],[4,0],[5,0],[6,0],[7,0],[8,0],[9,0],[10,0]]

                    for (let i=0;i<temporary.length;i++){
                        for(let j=0;j<ttt.length;j++){
                            if (temporary[i].key_value1==ttt[j][0]){
                                console.log(temporary[i].num)
                                ttt[j][1]=temporary[i].num
                            }
                        }

                    }
                    console.log(ttt)

                    this.AllLevelNum=ttt
                    this.SetALLLevel()
                })
                
                
                
                axios.post("http://101.34.151.80:9090/all_figure/get_userDraw",
                    {
                        "key1":"location-business",
                        "key2":"business-location",
                        "key_value1":2021,
                        "key_value2":11
                    }).then(res=>{
                    let temporary=res.data.data
                    let bu=[]
                    let aa=false
                    for (let i=0;i<temporary.length;i++){
                        if (temporary[i].key_value1.match(this.UserData.location)){
                            for (let j=0;j<bu.length;j++){
                                if (bu[j].key_value2==temporary[i].key_value2){
                                    bu[j].num=bu[j].num+temporary[i].num
                                    aa=true
                                }
                            }
                            if (aa==false){
                                bu.push(temporary[i])
                                aa = false
                            }
                        }
                    }
                    this.LocationBussiness=bu
                    this.SetLocationBussiness()
                })
            },

            //用户在全站的等级性别图
            async SetGenderLevel(){
                var chartDom = document.getElementById('GenderLevel');
                var myChart = echarts.init(chartDom);
                var option;
                option = {
                    legend: {},
                    tooltip: {},
                    dataset: {
                        source: [
                            ['product', 'Man', 'Woman', 'Unknow'],
                            ['一级', this.GenderLevelMan[0][1], this.GenderLevelWoman[0][1], this.GenderLevelX[0][1]],
                            ['二级', this.GenderLevelMan[1][1], this.GenderLevelWoman[1][1], this.GenderLevelX[1][1]],
                            ['三级', this.GenderLevelMan[2][1], this.GenderLevelWoman[2][1], this.GenderLevelX[2][1]],
                            ['四级', this.GenderLevelMan[3][1], this.GenderLevelWoman[3][1], this.GenderLevelX[3][1]],
                            ['五级', this.GenderLevelMan[4][1], this.GenderLevelWoman[4][1], this.GenderLevelX[4][1]],
                            ['六级', this.GenderLevelMan[5][1], this.GenderLevelWoman[5][1], this.GenderLevelX[5][1]],
                            ['七级', this.GenderLevelMan[6][1], this.GenderLevelWoman[6][1], this.GenderLevelX[6][1]],
                            ['八级', this.GenderLevelMan[7][1], this.GenderLevelWoman[7][1], this.GenderLevelX[7][1]],
                            ['九级', this.GenderLevelMan[8][1], this.GenderLevelWoman[8][1], this.GenderLevelX[8][1]],
                            ['十级', this.GenderLevelMan[9][1], this.GenderLevelWoman[9][1], this.GenderLevelX[9][1]],

                        ]
                    },
                    xAxis: { type: 'category' },
                    yAxis: {},
                    // Declare several bar series, each will be mapped
                    // to a column of dataset.source by default.
                    series: [{ type: 'bar' }, { type: 'bar' }, { type: 'bar' }]
                };
                option && myChart.setOption(option);

            },
            //用户信息雷达图
            async SetUserMessage(){
                var chartDom = document.getElementById('UserMessage');
                var myChart = echarts.init(chartDom);
                var option;

               var lin=[this.UserData.answer_count + this.UserData.zvideo_count + this.UserData.articles_count + this.UserData.columns_count, this.UserData.follower_count,
                       this.UserData.voteup_count, this.UserData.following_count, this.UserData.question_count]
               for (var i = 0;i<=4;i++){
                   for (var j=0;j<=4-i;j++){
                       if(lin[j]>lin[j+1]){
                           var c= lin[i]
                           lin[i]=lin[j+1]
                           lin[j+1]=c

                       }
                   }
               }

                option = {
                    title: {
                        text: '用户信息图',
                        left:'center'
                    },
                    legend: {
                        data: ['用户信息']
                    },

                    radar: {
                        // shape: 'circle',
                        indicator: [
                            {name: '创作数量'},
                            {name: '粉丝数'},
                            {name: '被赞同数'},
                            {name: '关注数'},
                            {name: '提问数'},
                        ]
                    },
                    series: [
                        {
                            type: 'radar',
                            data: [
                                {
                                    value: [this.UserData.answer_count + this.UserData.zvideo_count + this.UserData.articles_count + this.UserData.columns_count, this.UserData.follower_count,
                                        this.UserData.voteup_count, this.UserData.following_count, this.UserData.question_count],
                                    name: 'Allocated Budget',
                                    emphasis: {
                                        label: {
                                            show: true,
                                            color: '#fff',
                                            fontSize: 15,
                                            formatter: '{c}',       // 鼠标悬浮时展示数据加上单位
                                            backgroundColor: '#0D1B42',
                                            borderRadius: 5,
                                            padding: 10,
                                            shadowBlur: 3
                                        }
                                    }


                                }],
                            itemStyle: {
                                color: new echarts.graphic.LinearGradient(         // 设置渐变色
                                    0, 0, 0, 1,
                                    [
                                        { offset: 0, color: 'rgba(0, 230, 98, 1)' },
                                        { offset: 1, color: 'rgba(0, 155, 171, 1)' }
                                    ]
                                )
                            },
                            areaStyle:                                          // 雷达图辐射区域的样式
                                {
                                    color: new echarts.graphic.LinearGradient(
                                        0, 0, 0, 1,
                                        [
                                            { offset: 0, color: 'rgba(0, 230, 98, 1)' },
                                            { offset: 1, color: 'rgba(0, 155, 171, 1)' }
                                        ]
                                    )
                                },
                        }
                    ],

                };

                option && myChart.setOption(option);

            },

            //种类图
            async SetKindCakedMap(){
                var chartDom = document.getElementById('KindCakeMap');
                var myChart = echarts.init(chartDom);
                var option = {
                    title: {
                        text: "用户"+this.UserData.name+"作品种类数量饼图",
                        subtext: '各类型作品数量图',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left'
                    },
                    series: [
                        {
                            name: 'Access From',
                            type: 'pie',
                            radius: '50%',
                            data: [
                                { value: this.UserData.articles_count, name: '文章' },
                                { value: this.UserData.columns_count, name: '专栏' },
                                { value: this.UserData.zvideo_count, name: '视频' },
                                { value: this.UserData.answer_count, name: '回答' },
                            ],
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                option && myChart.setOption(option);
            },
            //种类建议
            async setKindSuggest(){
                var lin=[this.UserData.articles_count, this.UserData.zvideo_count, this.UserData.answer_count, this.UserData.columns_count]
                for (var i = 0;i<=4;i++){
                    for (var j=0;j<=4-i;j++){
                        if(lin[j]>lin[j+1]){
                            var c= lin[i]
                            lin[i]=lin[j+1]
                            lin[j+1]=c

                        }
                    }
                }

                if(lin[0]==this.UserData.articles_count){
                    this.kindSuggest="您的文章产量最少，数量为"+this.UserData.articles_count+"\n"
                }else if (lin[0]== this.UserData.zvideo_count) {
                    this.kindSuggest="您的视频产量最少，数量为"+this.UserData.zvideo_count+"\n"
                }else if (lin[0]==this.UserData.answer_count){
                    this.kindSuggest="您的回答产量最少，数量为"+this.UserData.answer_count+"\n"
                }else if (lin[0]==this.UserData.columns_count){
                    this.kindSuggest="您的专栏产量最少，数量为"+this.UserData.columns_count+"\n"
                }

                if(lin[3]==this.UserData.articles_count){
                    this.kindSuggest=this.kindSuggest+"您的文章产量最多，数量为"+this.UserData.articles_count+"\n"
                }else if (lin[3]==this.kindSuggest+ this.UserData.zvideo_count) {
                    this.kindSuggest=this.kindSuggest+"您的视频产量最多，数量为"+this.UserData.zvideo_count+"\n"
                }else if (lin[3]==this.UserData.answer_count){
                    this.kindSuggest=this.kindSuggest+"您的回答产量最多，数量为"+this.UserData.answer_count+"\n"
                }else if (lin[3]==this.UserData.columns_count){
                    this.kindSuggest=this.kindSuggest+"您的专栏产量最多，数量为"+this.UserData.columns_count+"\n"
                }

                if(lin[3]==0){
                    this.kindSuggest="您目前没有作品，可以尝试回答些问题寻找知己哦！"
                }

                if((lin[3]-lin[2])>50){
                    this.kindSuggest= this.kindSuggest+"您可以多尝试其他类型的创作"
                }


            },
            //种类折线图
            async SetKindBrokenMap(){
                var chartDom = document.getElementById('KindBrokenMap');
                var myChart = echarts.init(chartDom);
                var option;

                option = {
                    title: {
                        text: "用户"+this.UserData.name+"作品种类数量折线图",
                        subtext: '各类型作品数量图',
                        left: 'center'
                    },
                    xAxis: {
                        type: 'category',
                        data: ['文章', '视频', '回答', '专栏',]
                    },
                    yAxis: {
                        type: 'value'
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    series: [
                        {
                            data: [this.UserData.articles_count, this.UserData.zvideo_count, this.UserData.answer_count, this.UserData.columns_count],
                            type: 'line',
                            label: {
                                    show: true,
                                    position: 'bottom',
                                    textStyle: {
                                        fontSize: 20
                                    }
                                }
                        }
                    ]
                };

                option && myChart.setOption(option);
            },
           
            //当地男女比例图
            async SetLocationGender(){
               // console.log(this.LocationWomanNum,this.LocationManNum,this.LocationXNum)
                var chartDom = document.getElementById('GenderNum');
                var myChart = echarts.init(chartDom);
                var option;
                option = {
                    title: {
                        text: '当地男女比例图',
                        left:'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        top: '5%',
                        left: 'center'
                    },
                    series: [
                        {
                            name: 'Access From',
                            type: 'pie',
                            radius: ['40%', '70%'],
                            avoidLabelOverlap: false,
                            itemStyle: {
                                borderRadius: 10,
                                borderColor: '#fff',
                                borderWidth: 2
                            },
                            label: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    fontSize: '40',
                                    fontWeight: 'bold'
                                }
                            },
                            labelLine: {
                                show: false
                            },
                            data: [
                                { value: this.LocationManNum, name: '男性' },
                                { value: this.LocationWomanNum, name: '女性' },
                                {value: this.LocationXNum,name:'未知'}
                            ]
                        }
                    ]
                };

                option && myChart.setOption(option);
            },

            async SetALLLevel(){
                var chartDom = document.getElementById('ALLLevel');
                var myChart = echarts.init(chartDom);
                var c=this.UserData.level
                console.log(this.AllLevelNum)
                var option = {
                    title: {
                        text: '全站各等级数据',
                        left:'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        formatter: function (params){
                            return params[0].name+"<br>"+params[0].data
                        }
                    },
                    xAxis: {
                        type: 'category',
                        data: ['一级', '二级', '三级', '四级', '五级', '六级', '七级','八级','九级','十级']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            data: [
                                this.AllLevelNum[0][1],
                                this.AllLevelNum[1][1],
                                this.AllLevelNum[2][1],
                                this.AllLevelNum[3][1],
                                this.AllLevelNum[4][1],
                                this.AllLevelNum[5][1],
                                this.AllLevelNum[6][1],
                                this.AllLevelNum[7][1],
                                this.AllLevelNum[8][1],
                                this.AllLevelNum[9][1],

                            ],
                            type: 'bar',
                            itemStyle:{
                                //每个柱子颜色不同
                                color:function(data){
                                    let colorList=[];
                                    for (let i =1;i<11;i++){
                                        if(i!=c){
                                            colorList.push("#0000FF")
                                        }else{
                                            colorList.push('#a90000')
                                        }
                                    }
                                    return colorList[data.dataIndex];
                                }
                            }
                        }
                    ]
                }

                option && myChart.setOption(option);
            },
            //当地各等级人数
            async SetLocationLevel(){
                var chartDom = document.getElementById('LocationLevel');
                var myChart = echarts.init(chartDom);
                var c=this.UserData.level
                var option = {
                    title: {
                        text: '当地各等级数据',
                        left:'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        formatter: function (params){
                            return params[0].name+"<br>"+params[0].data
                        }
                    },
                    xAxis: {
                        type: 'category',
                        data: ['一级', '二级', '三级', '四级', '五级', '六级', '七级','八级','九级','十级']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            data: [
                                this.LocationLevelNum[0][1],
                                this.LocationLevelNum[1][1],
                                this.LocationLevelNum[2][1],
                                this.LocationLevelNum[3][1],
                                this.LocationLevelNum[4][1],
                                this.LocationLevelNum[5][1],
                                this.LocationLevelNum[6][1],
                                this.LocationLevelNum[7][1],
                                this.LocationLevelNum[8][1],
                                this.LocationLevelNum[9][1],

                            ],
                            type: 'bar',
                            itemStyle:{
                                //每个柱子颜色不同
                                color:function(data){
                                    let colorList=[];
                                    for (let i =1;i<11;i++){
                                        if(i!=c){
                                            colorList.push("#0000FF")
                                        }else{
                                            colorList.push('#a90000')
                                        }
                                    }
                                    return colorList[data.dataIndex];
                                }
                            }
                        }
                    ]
                }

                option && myChart.setOption(option);
            },

            //全站各等级平均回答
            async SetAverageAnswer(){
                var c=this.UserLevel
                var chartDom = document.getElementById('AverageAnswer');
                var myChart = echarts.init(chartDom);
                var option = {
                    title: {
                        text: '全站各等级平均回答数据',
                        left:'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        formatter: function (params){
                            return params[0].name+"<br>"+params[0].data
                        }
                    },
                    xAxis: {
                        type: 'category',
                        data: ["一级","二级","三级","四级","五级","六级","七级","八级","九级","十级"]
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            data: [
                                this.AverageAnswer[0][1],
                                this.AverageAnswer[1][1],
                                this.AverageAnswer[2][1],
                                this.AverageAnswer[3][1],
                                this.AverageAnswer[4][1],
                                this.AverageAnswer[5][1],
                                this.AverageAnswer[6][1],
                                this.AverageAnswer[7][1],
                                this.AverageAnswer[8][1],
                                this.AverageAnswer[9][1],
                            ],
                            type: 'bar',
                            itemStyle:{
                                //每个柱子颜色不同
                                color:function(data){
                                    let colorList=[];
                                    for (let i =1;i<11;i++){
                                        if(i!=c){
                                            colorList.push("#0000FF")
                                        }else{
                                            colorList.push('#a90000')
                                        }
                                    }
                                    return colorList[data.dataIndex];
                                },

                            }
                        }
                    ]
                }

                option && myChart.setOption(option);
            },

            //全站各等级平均提问
            async SetAverageQuestion(){
                // console.log(this.AverageQuestion)
                var chartDom = document.getElementById('AverageQuestion');
                var myChart = echarts.init(chartDom);
                var c=this.UserData.level
                var option = {
                    title: {
                        text: '全站各等级平均提问数据',
                        left:'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        formatter: function (params){
                            return params[0].name+"<br>"+params[0].data
                        }
                    },
                    xAxis: {
                        type: 'category',
                        data: ['一级', '二级', '三级', '四级', '五级', '六级', '七级','八级','九级','十级']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            data: [
                                this.AverageQuestion[0][1],
                                this.AverageQuestion[1][1],
                                this.AverageQuestion[2][1],
                                this.AverageQuestion[3][1],
                                this.AverageQuestion[4][1],
                                this.AverageQuestion[5][1],
                                this.AverageQuestion[6][1],
                                this.AverageQuestion[7][1],
                                this.AverageQuestion[8][1],
                                this.AverageQuestion[9][1],
                            ],
                            type: 'bar',
                            itemStyle:{
                                //每个柱子颜色不同
                                color:function(data){
                                    let colorList=[];
                                    for (let i =1;i<11;i++){
                                        if(i!=c){
                                            colorList.push("#0000FF")
                                        }else{
                                            colorList.push('#a90000')
                                        }
                                    }
                                    return colorList[data.dataIndex];
                                }
                            }
                        }
                    ]
                }

                option && myChart.setOption(option);
            },

            //全站各等级平均关注数量
            async SetAverageFollowing(){
                var chartDom = document.getElementById('AverageFollowing');
                var myChart = echarts.init(chartDom);
                var c=this.UserData.level
                var option = {
                    title: {
                        text: '全站各等级平均关注数据',
                        left:'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        formatter: function (params){
                            return params[0].name+"<br>"+params[0].data
                        }
                    },
                    xAxis: {
                        type: 'category',
                        data: ['一级', '二级', '三级', '四级', '五级', '六级', '七级','八级','九级','十级']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            data: [
                                this.AverageFollowing[0][1],
                                this.AverageFollowing[1][1],
                                this.AverageFollowing[2][1],
                                this.AverageFollowing[3][1],
                                this.AverageFollowing[4][1],
                                this.AverageFollowing[5][1],
                                this.AverageFollowing[6][1],
                                this.AverageFollowing[7][1],
                                this.AverageFollowing[8][1],
                                this.AverageFollowing[9][1],

                            ],
                            type: 'bar',
                            itemStyle:{
                                //每个柱子颜色不同
                                color:function(data){
                                    let colorList=[];
                                    for (let i =1;i<11;i++){
                                        if(i!=c){
                                            colorList.push("#0000FF")
                                        }else{
                                            colorList.push('#a90000')
                                        }
                                    }
                                    return colorList[data.dataIndex];
                                }
                            }
                        }
                    ]
                }

                option && myChart.setOption(option);
            },
            
            
            async SetAverageVoteup(){
                var chartDom = document.getElementById('AverageVoteup');
                var myChart = echarts.init(chartDom);
                var c=this.UserData.level
                var option = {
                    title: {
                        text: '全站各等级平均被赞同数据',
                        left:'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        formatter: function (params){
                            return params[0].name+"<br>"+params[0].data
                        }
                    },
                    xAxis: {
                        type: 'category',
                        data: ['一级', '二级', '三级', '四级', '五级', '六级', '七级','八级','九级','十级']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            data: [

                                this.AverageVoteup[0][1],
                                this.AverageVoteup[1][1],
                                this.AverageVoteup[2][1],
                                this.AverageVoteup[3][1],
                                this.AverageVoteup[4][1],
                                this.AverageVoteup[5][1],
                                this.AverageVoteup[6][1],
                                this.AverageVoteup[7][1],
                                this.AverageVoteup[8][1],
                                this.AverageVoteup[9][1],

                            ],
                            type: 'bar',
                            itemStyle:{
                                //每个柱子颜色不同
                                color:function(data){
                                    let colorList=[];
                                    for (let i =1;i<11;i++){
                                        if(i!=c){
                                            colorList.push("#0000FF")
                                        }else{
                                            colorList.push('#a90000')
                                        }
                                    }
                                    return colorList[data.dataIndex];
                                }
                            }
                        }
                    ]
                }

                option && myChart.setOption(option);
            },

            async SetBussiness(){
                var chartDom = document.getElementById('AllBussiness');
                var myChart = echarts.init(chartDom);

                var bu=this.Bussiness

                function setname(){
                    var name=[]
                    var se=[]
                    for(let i=0;i<bu.length;i++){
                        name.push(bu[i].key_value1)
                        se.push({name:bu[i].key_value1,value:bu[i].num})
                    }
                    // console.log(name)
                    return{
                        legendData: name,
                        seriesData: se
                    }
                }



                const data=setname()

                var option = {
                    title: {
                        text: '全站行业统计数据',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        type: 'scroll',
                        orient: 'vertical',
                        right: 10,
                        top: 20,
                        bottom: 20,
                        data: data.legendData
                    },
                    series: [
                        {
                            name: '行业',
                            type: 'pie',
                            radius: '55%',
                            center: ['40%', '50%'],
                            data: data.seriesData,
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                }

                option && myChart.setOption(option);
            },

            async SetLocationBussiness(){
                var chartDom = document.getElementById('LocationBussiness');
                var myChart = echarts.init(chartDom);

                var bu=this.LocationBussiness

                function setname(){
                    var name=[]
                    var se=[]
                    for(let i=0;i<bu.length;i++){
                        name.push(bu[i].key_value2)
                        se.push({name:bu[i].key_value2,value:bu[i].num})
                    }
                    // console.log(name)
                    return{
                        legendData: name,
                        seriesData: se
                    }
                }



                const data=setname()

                var option = {
                    title: {
                        text: '当地行业统计数据',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        type: 'scroll',
                        orient: 'vertical',
                        right: 10,
                        top: 20,
                        bottom: 20,
                        data: data.legendData
                    },
                    series: [
                        {
                            name: '行业',
                            type: 'pie',
                            radius: '55%',
                            center: ['40%', '50%'],
                            data: data.seriesData,
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                }

                option && myChart.setOption(option);
            },


            async SetAverageFollower(){
                var chartDom = document.getElementById('AverageFollower');
                var myChart = echarts.init(chartDom);
                var c=this.UserData.level
                var option = {
                    title: {
                        text: '全站各等级平均粉丝',
                        left:'center'
                    },
                    tooltip: {
                        trigger: 'axis',
                        formatter: function (params){
                            return params[0].name+"<br>"+params[0].data
                        }
                    },
                    xAxis: {
                        type: 'category',
                        data: ['一级', '二级', '三级', '四级', '五级', '六级', '七级','八级','九级','十级']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            data: [
                                this.AverageFollower[0][1],
                                this.AverageFollower[1][1],
                                this.AverageFollower[2][1],
                                this.AverageFollower[3][1],
                                this.AverageFollower[4][1],
                                this.AverageFollower[5][1],
                                this.AverageFollower[6][1],
                                this.AverageFollower[7][1],
                                this.AverageFollower[8][1],
                                this.AverageFollower[9][1],

                            ],
                            type: 'bar',
                            itemStyle:{
                                //每个柱子颜色不同
                                color:function(data){
                                    let colorList=[];
                                    for (let i =1;i<11;i++){
                                        if(i!=c){
                                            colorList.push("#0000FF")
                                        }else{
                                            colorList.push('#a90000')
                                        }
                                    }
                                    return colorList[data.dataIndex];
                                }
                            }
                        }
                    ]
                }

                option && myChart.setOption(option);
            },

            async SetComopare(){
                clearTimeout(this.timer);  //清除延迟执行

                this.timer = setTimeout(()=>{   //设置延迟执行
                    var chartDom = document.getElementById('Compare');
                    var myChart = echarts.init(chartDom);
                    var option;
                    var a=this.AverageAnswer[this.UserData.level][1]
                    var b=this.AverageQuestion[this.UserData.level][1]
                    var c=this.AverageFollower[this.UserData.level][1]
                    var d=this.AverageFollowing[this.UserData.level][1]
                    var e= this.AverageVoteup[this.UserData.level][1]
                    var f=0

                    option = {
                        title: {
                            text: '各数据比较图'
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['个人数据', '全站数据', '同等级数据']
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        toolbox: {
                            feature: {
                                saveAsImage: {}
                            }
                        },
                        xAxis: {
                            type: 'category',
                            boundaryGap: false,
                            data: ['回答数目', '提问数目', '关注', '粉丝', '被点赞', '文章数目']
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                name: '全站数据',
                                type: 'line',
                                stack: 'Total',
                                data: [this. AveerageAllAnswer,this. AveerageAllQuestion,this. AverageAllFollower,this. AverageAllFollowing,this.AverageAllVoteup,this. AverageALLArticles]
                            },
                            {
                                name: '个人数据',
                                type: 'line',
                                stack: 'Total',
                                data: [this.UserData.answer_count, this.UserData.question_count, this.UserData.follower_count, this.UserData.following_count,this.UserData.voteup_count,this.UserData.articles_count]
                            },
                            {
                                name: '同等级数据',
                                type: 'line',
                                stack: 'Total',
                                data: [a,b,c,d, e, f]
                            },

                        ]
                    };

                    option && myChart.setOption(option);
                },50000);

            },



        },

        created(){
          this.GetUserData()
        },
        mounted() {

        }
    }
</script>

<style scoped>
    html,body{height:100%;}
    .body{
        background-color:#f1f2f6;
        background-size: 100%,100%;

    }
    .header{
        height: 100px;
        width: 80%;
    }
    .bigHeader{
        height: 200px;
        width: 80%;
    }
    #Header-up{
        position: relative;
        left: 10%;
        background: #908b81;
        height: 100px;
        width: 80%;
        border-radius: 10px 10px 0px 0px;
    }
    #Header-down{
        position: relative;
        left: 10%;
        background: #ffffff;
        border-radius: 0px 0px 10px 10px;
    }
    .UserPicture{
        position: absolute;
        top: 20px;
        left: 180px;
        border:3px solid #fff;
    }
    .UserName{
        position: absolute;
        top: 100px;
        left: 380px;
        font-size:30px;
    }
    .Bussiness{
        font-size:10px;
        position:relative;
        left:20%;
        top:30%;
    }
    .Gender{
        font-size:10px;
        position:relative;
        left:20%;
        top:30%;
    }
    .Description{
        font-size:10px;
        position:relative;
        width:1000px;
        left:20%;
        top:30%;
    }
    .Headline{
        font-size:10px;
        position:relative;
        left:20%;
        top:30%;
    }
    .Location{
        font-size:10px;
        position:relative;
        left:20%;
        top:30%;
    }
    .contant{
        border-radius: 10px 10px 0px 0px;
        height: 100%;
        width: 80%;
        margin: auto;
        background-color:#ffffff;
        margin-top:20px;
    }
    #KindSuggest{
        font-size:20px;
        position:relative;
        left:10%;
        top: 50px;
    }
    #KindCakeMap{
        position:relative;
        display:inline-block;
    }
    #KindBrokenMap{
        position:relative;
        display:inline-block;
        left:10%;
    }
    #UserMessage{
        position:relative;
        left:30%;
    }
    #GenderNum{
        position:relative;
        top:100px;
        left:10%;
        display:inline-block;
    }
    #GenderLevel{
        position:relative;
        top:100px;
        left:10%;
        display:inline-block;
    }

    #LocationLevel{
        position:relative;
        top:100px;
        left:10%;
        display:inline-block;
    }
    #ALLLevel{
        position:relative;
        top:100px;
        left:10%;
        display:inline-block;
    }
    #AverageAnswer{
        position:relative;
        top:100px;
        left:30%;

    }

    #AverageQuestion{
        position:relative;
        top:100px;
        left:30%;

    }

    #AverageFollowing{
        position:relative;
        top:100px;
        left:30%;

    }
    #AverageFavorite{
        position:relative;
        top:100px;
        left:30%;

    }

    #AverageVoteup{
        position:relative;
        top:100px;
        left:30%;

    }

    #AverageFollower{
        position:relative;
        top:100px;
        left:30%;

    }

    #AllBussiness{
        position:relative;
        top:100px;
        display:inline-block;
    }

    #LocationBussiness{
        position:relative;
        top:100px;
        left:10%;
        display:inline-block;
    }
    #Compare{
        position:relative;
        top:100px;
        left:30%;

    }
 #history{
     position:relative;
     top:100px;
     left:30%;
 }






</style>