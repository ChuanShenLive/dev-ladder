<template>
    <div style="width: 500px;">
        <el-input
                prefix-icon="el-icon-search"
                placeholder="请输入部门名称进行搜索...."
                v-model="filterText">
        </el-input>
        <el-tree
                :data="deps"
                :props="defaultProps"
                :expand-on-click-node="false"
                :filter-node-method="filterNode"
                ref="tree">
            <span class="custom-tree-node" style="display: flex; justify-content: space-between; width: 100%"
                  slot-scope="{node, data}">
                <span>{{ data.name }}</span>
                <span>
                    <el-button
                            type="primary"
                            size="mini"
                            class="depBtn"
                            @click="() => showAddDepView(data)">
                        添加部门
                    </el-button>
                    <el-button
                            type="danger"
                            size="mini"
                            class="depBtn"
                            @click="() => deleteDep( data)">
                        删除部门
                    </el-button>
                </span>
            </span>
        </el-tree>
        <el-dialog
                title="添加部门"
                :visible.sync="dialogVisible"
                width="30%">
            <div>
                <table>
                    <tr>
                        <td>
                            <el-tag>上级部门</el-tag>
                        </td>
                        <td>{{pname}}</td>
                    </tr>
                    <tr>
                        <td>
                            <el-tag>部门名称</el-tag>
                        </td>
                        <td>
                            <el-input v-model="dep.name" placeholder="请输入部门名称..."></el-input>
                        </td>
                    </tr>
                </table>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="doAddDep">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "DepMana",
        data() {
            return {
                filterText: '',
                deps: [],
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                dialogVisible: false,
                dep: {
                    name: '',
                    parentId: -1
                },
                pname: '',
            }
        },
        mounted() {
            this.initDeps();
        },
        methods: {
            initDep() {
                this.dep = {
                    name: '',
                    parentId: -1
                };
                this.pname = '';
            },
            addDep2Deps(deps, dep) {
                for (let i = 0; i < deps.length; i++) {
                    let d = deps[i];
                    if (d.id == dep.parentId) {
                        d.children = d.children.concat(dep)
                        return;
                    } else {
                        this.addDep2Deps(d.children, dep);
                    }
                }
            },
            removeDepFromDeps(deps, id) {
                for (let i = 0; i < deps.length; i++) {
                    let d = deps[i];
                    if (d.id == id) {
                        deps.splice(i, 1);
                        return;
                    } else {
                        this.removeDepFromDeps(d.children, id);
                    }
                }
                    },
            doAddDep() {
                this.postRequest("/system/basic/department/", this.dep).then(resp => {
                    if (resp) {
                        this.addDep2Deps(this.deps, resp);
                        this.dialogVisible = false;
                        // 初始化变量
                        this.initDep();
                    }
                })
            },
            showAddDepView(data) {
                this.pname = data.name;
                this.dep.parentId = data.id;
                this.dialogVisible = true;
            },
            deleteDep(data) {
                if (data.parent) {
                    this.$message.error("存在子部门, 父部门部门删除失败!");
                } else {
                    this.$confirm('此操作将永久删除[' + data.name + ']部门, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.deleteRequest("/system/basic/department/" + data.id).then((resp) => {
                            if (resp) {
                                this.removeDepFromDeps(this.deps, data.id);
                            }
                        })
                    }).catch(() => {

                    });
                }
            },
            initDeps() {
                this.getRequest("/system/basic/department/").then(resp => {
                    if (resp) {
                        this.deps = resp;
                    }
                });
            },
            filterNode(value, data) {
                if (!value) return true;
                return data.name.indexOf(value) !== -1;
            }
        },
        watch: {
            filterText(val) {
                this.$refs.tree.filter(val);
            }
        }
    }
</script>

<style>
    .depBtn {
        padding: 2px;
    }
</style>
