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
            <el-button icon="el-icon-plus" size="small" type="primary" @click="addJobLevel">添加</el-button>
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
                        width="180"
                        label="职称级别">
                </el-table-column>
                <el-table-column
                        prop="createDate"
                        label="创建时间">
                </el-table-column>
                <el-table-column
                        prop="enabled"
                        label="是否启用">
                    <template slot-scope="scope">
                        <el-tag type="success" v-if="scope.row.enabled">已启用</el-tag>
                        <el-tag type="danger" v-else>未启用</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button size="mini" @click="showEditView(scope.row)">编辑</el-button>
                        <el-button size="mini" type="danger" @click="deleteHandler(scope.row)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-button type="danger" size="small" style="margin-top: 8px;" :disabled="multipleSelection.length == 0" @click="deleteMany">批量删除</el-button>
        </div>
        <el-dialog title="修改职称" :visible.sync="dialogVisible" width="30%">
            <div>
                <table>
                    <tr>
                        <td>
                            <el-tag>职称名称</el-tag>
                        </td>
                        <td>
                            <el-input size="small" v-model="updateJobLevel.name"></el-input>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <el-tag>职称级别</el-tag>
                        </td>
                        <td>
                            <el-select v-model="updateJobLevel.titleLevel"
                                       placeholder="职称等级"
                                       size="small">
                                <el-option
                                        v-for="item in titleLevel"
                                        :key="item"
                                        :label="item"
                                        :value="item">
                                </el-option>
                            </el-select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <el-tag>是否启用</el-tag>
                        </td>
                        <td>
                            <el-switch v-model="updateJobLevel.enabled" active-text="启用" inactive-text="禁用"></el-switch>
                        </td>
                    </tr>
                </table>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button size="small" @click="dialogVisible = false">取 消</el-button>
                <el-button size="small" type="primary" @click="doUpdate">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "JobLevelMana",
        data() {
            return {
                jobLevel: {
                    name: '',
                    titleLevel: ''
                },
                titleLevel: [
                    '正高级',
                    '副高级',
                    '中级',
                    '初级',
                    '员级'
                ],
                jobLevels: [],
                dialogVisible: false,
                updateJobLevel: {
                    name: '',
                    titleLevel: '',
                    enabled: false
                },
                multipleSelection: [],
            }
        },
        mounted() {
            this.initJobLevels();
        },
        methods: {
            initJobLevels() {
                this.getRequest("/system/basic/jobLevel/").then(resp => {
                    if (resp) {
                        this.jobLevels = resp;
                        this.jobLevel = {
                            name: '',
                            titleLevel: ''
                        }
                    }
                });
            },
            addJobLevel() {
                if (this.jobLevel.name && this.jobLevel.titleLevel) {
                    this.postRequest("/system/basic/jobLevel/", this.jobLevel).then(resp => {
                        if (resp) {
                            this.initJobLevels();
                        }
                    });
                } else {
                    this.$message.error("添加字段不可以为空!")
                }
            },
            deleteHandler(data) {
                this.$confirm('此操作将永久删除[' + data.name + ']职称, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/basic/jobLevel/" + data.id).then(resp => {
                        if (resp) {
                            this.initJobLevels();
                        }
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除!'
                    });
                });
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            showEditView(data) {
                Object.assign(this.updateJobLevel, data);
                this.dialogVisible = true;
            },
            doUpdate() {
                this.putRequest("/system/basic/jobLevel/", this.updateJobLevel).then(resp => {
                    if (resp) {
                        this.initJobLevels();
                        this.dialogVisible = false;
                    }
                }).catch();
            },
            deleteMany() {
                this.$confirm('此操作将永久删除[' + this.multipleSelection.length + ']条记录, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let ids = '?';
                    this.multipleSelection.forEach(item=>{
                       ids += 'ids=' + item.id + '&';
                    });
                    this.deleteRequest("/system/basic/jobLevel/" + ids).then(resp => {
                        if (resp) {
                            this.initJobLevels();
                        }
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除!'
                    });
                });
            }
        }
    }
</script>

<style>
    .addJobLevelInput {
        width: 300px;
        margin-right: 8px;
    }

    .addJobLevelSelect {
        margin-right: 8px;
    }

    .jobLevelManaMain {
        margin-top: 10px;
    }
</style>
