<template>
    <div>
        <div style="margin-top: 10px; display: flex; justify-content: center;">
            <el-input v-model="keywords"
                      placeholder="通过用户名搜索用户..."
                      prefix-icon="el-icon-search"
                      @keydown.enter.native="doSearch"
                      style="width: 400px; margin-right: 10px;">
            </el-input>
            <el-button icon="el-icon-search" type="primary" @click="doSearch">搜 索</el-button>
        </div>
        <div class="hr-container">
            <el-card class="hr-card" v-for="(hr, index) in hrs" :key="index">
                <div slot="header" class="clearfix">
                    <span>{{hr.name}}</span>
                    <el-button style="float: right; padding: 3px 0; color: #e30007" type="text"
                               icon="el-icon-delete" @click="deleteHr(hr)">
                    </el-button>
                </div>
                <div>
                    <div class="img-container">
                        <img :src="hr.userface" :alt="hr.name" :title="hr.name" class="userface-img">
                    </div>
                    <div class="user-info-container">

                        <div>用户名: {{hr.name}}</div>
                        <div>手机号码: {{hr.phone}}</div>
                        <div>电话号码: {{hr.telephone}}</div>
                        <div>地址: {{hr.address}}</div>
                        <div>用户状态:
                            <el-switch v-model="hr.enabled"
                                       @change="enabledChange(hr)"
                                       active-color="#13ce66"
                                       active-text="启用"
                                       inactive-color="#ff4949"
                                       inactive-text="禁用">
                            </el-switch>
                        </div>
                        <div>用户角色:
                            <el-tag type="success" style="margin-right: 4px" v-for="(role, indexj) in hr.roles"
                                    :key="indexj">{{role.nameZh}}
                            </el-tag>
                            <el-popover placement="right" title="角色列表" width="200" trigger="click" @show="showPop(hr)" @hide="hidePop(hr)">
                                <el-select v-model="selectedRoles" multiple placeholder="请选择">
                                    <el-option v-for="(r, indexKey) in allRoles"
                                               :key="indexKey"
                                               :label="r.nameZh"
                                               :value="r.id">
                                    </el-option>
                                </el-select>
                                <el-button slot="reference" icon="el-icon-more" type="text"></el-button>
                            </el-popover>
                        </div>
                        <div>备注: {{hr.remark}}</div>
                    </div>
                </div>
            </el-card>

        </div>
    </div>
</template>

<script>
    export default {
        name: "SysHr",
        data() {
            return {
                keywords: '',
                hrs: [],
                selectedRoles: [],
                allRoles: [],
            }
        },
        mounted() {
            this.initHrs();
        },
        methods: {
            doSearch() {
                this.initHrs();
            },
            initHrs: function () {
                this.getRequest('/system/hr/?keywords=' + this.keywords).then(resp => {
                    if (resp) {
                        this.hrs = resp;
                    }
                })
            },
            deleteHr(hr) {
                this.$confirm('此操作将永久删除[' + hr.name + '], 是否继续？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: "warning"
                }).then(() => {
                    this.deleteRequest('/system/hr/' + hr.id).then(resp=>{
                        if (resp) {
                            this.initHrs();
                        }
                    });
                }).catch(() => {
                    this.$message({type: "info", message: "已取消删除"})
                })
            },
            showPop: function (hr) {
                this.initAllRoles();
                let roles = hr.roles;
                this.selectedRoles = [];
                roles.forEach(r => {
                    this.selectedRoles.push(r.id);
                });
            },
            hidePop: function (hr) {
                let roles = [];
                Object.assign(roles, hr.roles);
                let flag = false;
                if (roles.length != this.selectedRoles.length) {
                    flag = true;
                } else {
                    for (let i = 0; i < roles.length; i++) {
                        let role = roles[i];
                        for (let j = 0; j < this.selectedRoles.length; j++) {
                            let sr = this.selectedRoles[j];
                            if (role.id == sr) {
                                roles.splice(i, 1);
                                i--;
                                break;
                            }
                        }
                    }
                    if (roles.length != 0) {
                        flag = true;
                    }
                }
                if (flag) {
                    let url = '/system/hr/role?hrid=' + hr.id;
                    this.selectedRoles.forEach(sr => {
                        url = url + '&rids=' + sr;
                    })
                    this.putRequest(url).then(resp => {
                        if (resp) {
                            this.initHrs();
                        }
                    })
                }

            },
            initAllRoles: function () {
                this.getRequest('/system/hr/roles').then(resp => {
                    if (resp) {
                        this.allRoles = resp;
                    }
                });
            },
            enabledChange: function (hr) {
                this.putRequest('/system/hr/', hr).then(resp => {
                    this.initHrs();
                });
            }
        }
    }
</script>

<style>
    .hr-container {
        margin-top: 10px;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;
    }

    .hr-card {
        width: 350px;
        margin-bottom: 20px;
    }

    .img-container {
        width: 100%;
        display: flex;
        justify-content: center;
    }

    .userface-img {
        width: 72px;
        height: 72px;
        border-radius: 72px;
    }

    .user-info-container {
        margin-top: 20px;
    }

    .user-info-container div {
        font-size: 12px;
        color: #409eff;
    }
</style>
