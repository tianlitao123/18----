<template>
    <keep-alive>
        <div id="main" class="body" @click="HideHistory">
            <div id='logo' class="logo" >
                <img src="../assets/logo.png" @click="returnHome" id='logoImg'>
            </div>

            <div id="search" class="search">
                <el-input v-model="InputValue" placeholder="请输入搜索内容" @click.stop="ShowHistory" @input="SearchShow" @change="Search" @keydown.enter="Search">
                    <template #suffix>
                        <i class="el-input__icon el-icon-search" @click="Search"></i>
                    </template>
                </el-input>
            </div>
			<div>
				
			</div>
            <div id='history' class="history" v-show="historyshow" @click.stop="ShowHistory">
                <el-table v-bind:data="InputListShow" stripe ref="multipleTable" @selection-change="handleSelectionChange" max-height="300" @row-click="FillValue" >
                    <el-table-column type="selection" width="50" />
                    <el-table-column prop="Value" label="搜索内容" width="450"/>
                    <el-table-column prop="Fix" width="50">
                        <template #header>
                            <a @click="FixedSelect">固定</a>
                        </template>
                        <template #default="scope">
                            <div @click="Fixed(scope.row)">
                                <i class="el-icon-lock" v-show="scope.row.Fix ==false"></i>
                                <i class="el-icon-unlock" v-show="scope.row.Fix ==true"></i>
                            </div>
                        </template>
                    </el-table-column>

                    <el-table-column width="50" >
                        <template #header>
                            <a @click="DeleteSelect">删除</a>
                        </template>
                        <template #default="scope">
                            <div @click="Delete(scope.$index,scope.row.Fix)">
                                <i class="el-icon-close"></i>
                            </div>
                        </template>
                    </el-table-column>



                </el-table>
            </div>
        </div>
    </keep-alive>
</template>

<script >
    import { ref} from 'vue'
    import { ElMessageBox, ElMessage } from 'element-plus'
    import { useRouter } from 'vue-router'
    export default {
        name: "SearchPage",
        setup(){
            //
            const InputValue=ref("")
            //true为可移除，false为不可移除
            const InputList=ref([])
            const InputListShow=ref([])
            const SuggestList=ref([])
            const MultipleSelection=ref([])
            const historyshow=ref(false)
            const router = useRouter()
            //查询
            function Search(){
                //默认可移除
                console.log(InputValue)
                if(InputValue.value==""){
                    ElMessage({
                        type: 'info',
                        message: '搜索内容不能为空',
                    })
                }else{
                    InsertInputList(InputValue)
                    let a=InputValue.value
                    InputValue.value=""
                    router.push({ path: '/UserPage', query: { userId: a }})
                }
            
            }
			//返回主页
			function returnHome(){
				router.push('/');
			}
            //存入历史列表
            function InsertInputList(Value){
                InputList.value.push({"Value":Value.value,"Fix":true})
            }

            //固定或取消用户搜索历史
            function Fixed(item){
                item.Fix=!item.Fix
                console.log(InputList)
            }

            //删除用户历史
            function Delete(index,bo){
                if(bo==true){
                    ElMessageBox.confirm(
                        '是否删除指定历史记录',
                        'Warning',
                        {
                            confirmButtonText: '删除',
                            cancelButtonText: '取消',
                            type: 'warning',
                        }
                    )
                        .then(() => {
                            InputList.value.splice(index,1)
                            ElMessage({
                                type: 'success',
                                message: '删除成功',
                            })
                        })
                        .catch(() => {
                            ElMessage({
                                type: 'info',
                                message: '取消删除',
                            })
                        })

                }else{
                    ElMessage({
                        type: 'info',
                        message: '请取消锁定后再删除',
                    })
                }
            }

            function FixedSelect(){
                ElMessageBox.confirm(
                    '是否固定选定历史记录',
                    'Warning',
                    {
                        confirmButtonText: '固定',
                        cancelButtonText: '取消',
                        type: 'warning',
                    }
                )
                    .then(() => {
                        for (let i=0;i<MultipleSelection.value.length;i++){
                            for(let j=0;j<InputList.value.length;j++){
                                if(MultipleSelection.value[i]==InputList.value[j]){
                                    InputList.value[j].Fix=false
                                    MultipleSelection.value[i].Fix=false
                                    break
                                }
                            }
                        }
                        console.log(InputList.value)
                    })
                    .catch(() => {
                        ElMessage({
                            type: 'info',
                            message: '取消固定',
                        })
                    })

            }

            function DeleteSelect(){
                ElMessageBox.confirm(
                    '是否删除选定历史记录',
                    'Warning',
                    {
                        confirmButtonText: '删除',
                        cancelButtonText: '取消',
                        type: 'warning',
                    }
                )
                    .then(() => {
                        //判定是否有锁定
                        for (let i=0;i<MultipleSelection.value.length;i++){
                            if(MultipleSelection.value[i].Fix==false){
                                ElMessageBox.confirm(
                                    '是否取消选中固定',
                                    'Warning',
                                    {
                                        confirmButtonText: 'OK',
                                        cancelButtonText: 'Cancel',
                                        type: 'warning',
                                    }
                                )
                                    .then(() => {
                                        ElMessage({
                                            type: 'success',
                                            message: '取消固定成功',
                                        })
                                        for (let i=0;i<MultipleSelection.value.length;i++){
                                            for(let j=0;j<InputList.value.length;j++){
                                                if(MultipleSelection.value[i]==InputList.value[j]){
                                                    InputList.value.splice(j,1)
                                                    break
                                                }
                                            }
                                        }
                                        ElMessage({
                                            type: 'success',
                                            message: '删除成功',
                                        })
                                    })
                                    .catch(() => {

                                    })
                            }else{
                                for (let i=0;i<MultipleSelection.value.length;i++){
                                    for(let j=0;j<InputList.value.length;j++){
                                        if(MultipleSelection.value[i]==InputList.value[j]){
                                            InputList.value.splice(j,1)
                                            break
                                        }
                                    }
                                }
                                ElMessage({
                                    type: 'success',
                                    message: '删除成功',
                                })
                            }
                        }

                    })
                    .catch(() => {
                        ElMessage({
                            type: 'info',
                            message: '取消删除',
                        })
                    })
                console.log(InputList.value)
            }

            function SearchShow(){
                SuggestList.value=[]
                InputListShow.value=[]
                console.log(InputValue.value)
                for (let i=0;i<InputList.value.length;i++){
                    if(InputList.value[i].Value.includes(InputValue.value)){
                        SuggestList.value.push(InputList.value[i])
                    }
                }
                InputListShow.value=SuggestList.value
            }

            function handleSelectionChange(val) {
                MultipleSelection.value= val
            }

            function ShowHistory(){
                historyshow.value=true
                InputListShow.value=InputList.value
            }

            function HideHistory(){
                historyshow.value=false
            }

            function FillValue(row){
                InputValue.value=row.Value
            }

            return{
                InputList,
                InputValue,
                Search,
                InsertInputList,
                Fixed,
                Delete,
                DeleteSelect,
                handleSelectionChange,
                FixedSelect,
                ShowHistory,
                historyshow,
                HideHistory,
                SearchShow,
                InputListShow,
                SuggestList,
                FillValue,
				returnHome
            }
        }
    }
</script>

<style scoped>
    .body{
        background-color:#273c75;
        width:100vw;
        height:100vh;
    }
    .logo{
        margin:auto;
		display:flex;
		justify-content: center;
		align-items: center;
        padding-top: 90px;
    }
	#logoImg:hover{
		cursor: pointer;
	}
    .search{
        margin: auto;
        width: 50%;
        padding-top: 50px
    }
    .history{
        margin: auto;
        width: 50%;
    }

</style>