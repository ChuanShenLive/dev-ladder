<template>
    <div>
        <div>
            <el-input
                    placeholder="添加职称..."
                    prefix-icon="el-icon-plus"
                    size="small"
                    class="addJobLevelInput"
                    @keydown.enter.native="addJobLevel"
                    v-model="jobLevel.name">
            </el-input>
            <el-select v-model="jobLevel.titleLevel"
                       placeholder="职称等级"
                       class="addJobLevelSelect"
                       size="small">
                <el-option
                        v-for="item in titleLevel"
                        :key="item"
                        :label="item"
                        :value="item">
                </el-option>
            </el-select>
            <el-button icon="el-icon-plus" size="small" type="primary" @click="addPosition">添加</el-button>
        </div>
        <div class="jobLevelManaMain">
            <el-table
                    :data="jobLevels"
                    border
                    stripe
                    size="small"
                    style="width: 80%"
                    @selection-change="handleSelectionChange">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>
                <el-table-column
                        prop="id"
                        label="编号"
                        width="55">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="职称名称"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="titleLevel"
                        width="150"
                        label="职称级别">
                </el-table-column>
                <el-table-column
                        prop="createDate"
                        width="150"
                        label="创建时间">
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button size="mini" @click="showEditView(scope.$index, scope.row)">编辑</el-button>
                        <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
    export default {
        name: "JobLevelMana",
        data() {
            return {
                jobLevel: {
                    name: '',
                    titleLevel:''
                },
                titleLevel: [
                    '正高级',
                    '副高级',
                    '中级',
                    '初级',
                    '员级'
                ],
                jobLevels: [],
            }
        }
    }
</script>

<style>
    .addJobLevelInput{
        width: 300px;
        margin-right: 8px;
    }
    .addJobLevelSelect{
        margin-right: 8px;
    }
    .jobLevelManaMain{
        margin-top: 10px;
    }
</style>
