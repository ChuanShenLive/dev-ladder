<template>
    <div>
        <div class="permissManaTool">
            <el-input size="small" placeholder="请输入角色英文名" v-model="role.name">
                <template slot="prepend">ROLE_</template>
            </el-input>
            <el-input size="small" placeholder="请输入角色中文名" v-model="role.nameZh"></el-input>
            <el-button type="primary" size="small" icon="el-icon-plus">添加角色</el-button>
        </div>
        <div class="permissManaMain">
            <el-collapse accordion @change="change">
                <el-collapse-item :title="role.nameZh" :name="role.id" v-for="(role, index) in roles" :key="index">
                    <el-card class="box-card">
                        <div slot="header" class="clearfix">
                            <span>可访问的资源</span>
                            <el-button style="float: right; padding: 3px 0; color: #ff0000;" icon="el-icon-delete"
                                       type="text"></el-button>
                        </div>
                        <div>
                            <el-tree
                                    show-checkbox
                                    node-key="id"
                                    :default-checked-keys="selectedMenus"
                                    :data="allMenus" :props="defaultProps"></el-tree>
                        </div>
                    </el-card>
                </el-collapse-item>
            </el-collapse>
        </div>
    </div>
</template>

<script>
    export default {
        name: "PermissMana",
        data() {
            return {
                role: {
                    name: '',
                    nameZh: '',
                },
                roles: [],
                allMenus: [],
                selectedMenus:[],
                defaultProps: {
                    children: 'children',
                    label: 'name',
                    renderAfterExpand: false
                }
            }
        },
        mounted() {
            this.initRoles();
        },
        methods: {
            change(rid) {
                if (rid) {
                    this.initAllMenus();
                    this.initSelectedMenus(rid);
                    console.log(this.selectedMenus);
                }
            },
            initSelectedMenus(rid) {
                this.getRequest("/system/basic/permiss/mids/" + rid).then(resp=>{
                   if (resp) {
                       this.selectedMenus = resp;
                   }
                });
            },
            initAllMenus() {
                this.getRequest("/system/basic/permiss/menus").then(resp => {
                    if (resp) {
                        this.allMenus = resp;
                    }
                });
            },
            initRoles() {
                this.getRequest("/system/basic/permiss/").then(resp => {
                    console.log(resp);
                    if (resp) {
                        this.roles = resp;
                    }
                });
            }
        }
    }
</script>

<style>

    .permissManaTool .el-input {
        width: 300px;
        margin-right: 8px;
    }

    .permissManaMain {
        margin-top: 8px;
        width: 700px;
    }

</style>
