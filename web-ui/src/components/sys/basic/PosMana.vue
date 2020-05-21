<template>
    <div>
        <div>
            <el-input
                placeholder="添加职位"
                prefix-icon="el-icon-plus"
                size="small"
                style="width: 300px; margin-right: 8px"
                v-model="pos.name">
            </el-input>

            <el-button icon="el-icon-plus" size="small" type="primary">添加</el-button>
        </div>
        <div>
            <el-table
                :data=""
        </div>
    </div>
</template>

<script>
    export default {
        name: "PosMana",
        data() {
            return {
                pos: {
                    name: ''
                }
            }
        }
    }
</script>

<style scoped>

</style>
