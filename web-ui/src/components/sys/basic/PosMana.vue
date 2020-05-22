<template>
    <div>
        <div>
            <el-input
                    placeholder="添加职位..."
                    prefix-icon="el-icon-plus"
                    size="small"
                    class="addPosInput"
                    style="width: 300px; margin-right: 8px"
                    v-model="pos.name">
            </el-input>

            <el-button icon="el-icon-plus" size="small" type="primary">添加</el-button>
        </div>
        <div class="posManaMain">
            <el-table
                    :data="positions"
                    border
                    stripe
                    size="small"
                    style="width: 70%">
                <el-table-column
                        prop="id"
                        label="编号"
                        width="55">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="职位名称"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="createDate"
                        label="创建时间">
                </el-table-column>
            </el-table>
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
                },
                positions: []
            }
        }
    }
</script>

<style>
    .addPosInput {
        width: 300px;
        margin-right: 8px;
    }

    .posManaMain {
        margin-top: 10px;
    }

</style>
