<!--
 * @Author: sun
 * @Date: 2021/10/28 20:21
 * @title: SearchPage
 * @Description:
 * @version: 1.0
 -->
<template>
    <keep-alive>
    <div id="main" class="body">
        <div id='logo' class="logo">
            <img src="../assets/logo.png">
        </div>

        <div id="search" class="search">
            <el-input v-model="InputValue" placeholder="请输入搜索内容">
                <template #suffix>
                    <i class="el-input__icon el-icon-search" @click="Search"></i>
                </template>
            </el-input>
        </div>

        <div id='history' class="history" v-show="InputList.length>0">
            <a>历史记录</a>
            <el-table :data="InputList" stripe ref="multipleTable" @selection-change="handleSelectionChange" max-height="300">
                <el-table-column type="selection" width="55" />
                <el-table-column prop="Value" label="搜索内容" width="550"/>
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
    export default {
        name: "SearchPage",
        setup(){
            //
            const InputValue=ref("")
            //true为可移除，false为不可移除
            const InputList=ref([])
            const MultipleSelection=ref([])

            //查询
            function Search(){
                //默认可移除
                InsertInputList(InputValue)
                InputValue.value=""
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
                            }
                        }

                    })
                    .catch(() => {
                        ElMessage({
                            type: 'info',
                            message: '取消删除',
                        })
                    })

            }

            function handleSelectionChange(val) {
                MultipleSelection.value= val
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
                FixedSelect
            }
        }
    }
</script>

<style scoped>
.body{
    background-color:steelblue;
    width:100vw;
    height:100vh;
}
.logo{
    margin:auto;
    width:30%;
    padding-top: 90px;
}
.search{
    margin: auto;
    width: 50%;
    padding-top: 50px
}
.history{
    margin: auto;
    width: 50%;
    padding-top: 50px;
}

</style>