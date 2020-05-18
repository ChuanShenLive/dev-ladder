<template>
    <div>
        <el-container>
            <!-- Begin Page Header -->
            <el-header class="homeHeader">
                <div class="title">
                    过墙梯
                </div>
                <el-dropdown class="userInfo" @command="commandHandler">
                    <span class="el-dropdown-link">
                        {{user.name}}
                        <i><img :src="user.userface" alt=""></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>
                        <el-dropdown-item command="setting">设置</el-dropdown-item>
                        <el-dropdown-item command="logout" divided>注销登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-header>
            <!-- End page Header -->

            <!-- Begin Menu -->
            <el-container>
                <el-aside width="200px">
                    <el-menu router unique-opened>
                        <el-submenu :index="index+''" v-for="(item, index) in routes" v-if="!item.hidden" :key="index">
                            <template slot="title">
                                <i :class="item.iconCls"></i>
                                <span>{{item.name}}</span>
                            </template>
                            <el-menu-item v-for="(child, indexj) in item.children" :index="child.path" :key="indexj"> {{child.name}}</el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-aside>
                <!-- End Menu -->

                <!-- Begin Page Main -->
                <el-main><router-view/></el-main>
                <!-- End Page Main -->

            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Home",
        data() {
            return {
                user: JSON.parse(window.sessionStorage.getItem("user"))
            }
        },
        computed: {
            routes() {
                return this.$store.state.routes;
            }
        },
        methods: {
            commandHandler(cmd) {
                if (cmd == 'logout') {
                    console.log(this.$route);
                    this.$confirm('此操作将注销登录, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then( () => {
                        this.getRequest("/logout");
                        window.sessionStorage.removeItem("user");
                        this.$store.commit('initRoutes', []);
                        console.log(this.$router)
                        this.$router.replace("/");
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消操作!'
                        })
                    });
                }
            },
        }
    }
</script>

<style>
    .homeHeader {
        background-color: #409eff;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0px 15px;
        box-sizing: border-box;
    }

    .homeHeader .title {
        font-size: 30px;
        font-family: 华文行楷;
        color: #ffffff;
    }

    .homeHeader .userInfo {
        cursor: pointer;
    }
    .el-dropdown-link {
        display: flex;
        align-items: center;
    }

    .el-dropdown-link img {
        width: 48px;
        height: 48px;
        border-radius: 24px;
        margin-left: 8px;
    }
    .el-submenu i.fa {
        color: #409eff;
        margin-right: 5px;
    }

</style>
