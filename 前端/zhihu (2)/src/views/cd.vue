<template>
    <el-autocomplete
            popper-class="my-autocomplete"
            v-model="state"
            :fetch-suggestions="querySearch"
            placeholder="请输入内容"
            @select="handleSelect">
        <template #suffix>
            <i class="el-icon-edit el-input__icon" @click="handleIconClick"></i>
        </template>
        <template #default="{ item }">
            <div class="name">{{ item.value }}</div>
            <span class="addr">{{ item.address }}</span>
        </template>
    </el-autocomplete>
</template>
<script>
    export default {
        data() {
            return {
                restaurants: [],
                state: ''
            };
        },
        methods: {
            querySearch(queryString, cb) {
                var restaurants = this.restaurants;
                var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
                // 调用 callback 返回建议列表的数据
                cb(results);
            },
            createFilter(queryString) {
                return (restaurant) => {
                    return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
                };
            },
            loadAll() {
                return [
                    {value: 'vue', link: 'https://github.com/vuejs/vue'},
                    {value: 'element', link: 'https://github.com/ElemeFE/element'},
                    {value: 'cooking', link: 'https://github.com/ElemeFE/cooking'},
                    {value: 'mint-ui', link: 'https://github.com/ElemeFE/mint-ui'},
                    {value: 'vuex', link: 'https://github.com/vuejs/vuex'},
                    {value: 'vue-router', link: 'https://github.com/vuejs/vue-router'},
                    {value: 'babel', link: 'https://github.com/babel/babel'},
                ];
            },
            handleSelect(item) {
                console.log(item);
            },
            handleIconClick(ev) {
                console.log(ev);
            }
        },
        mounted() {
            this.restaurants = this.loadAll();
        }
    }
</script>
<style>
li {
    line-height: normal;
    padding: 7px;
}

.name {
    text-overflow: ellipsis;
    overflow: hidden;
}

.addr {
    font-size: 12px;
    color: #b4b4b4;
}

.highlighted .addr {
    color: #ddd;

}





</style>