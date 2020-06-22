<template>
    <div>
        <el-input placeholder="请输入部门名称进行搜索...." v-model="filterText">
        </el-input>
        <el-tree
                :data="deps"
                :props="defaultProps"
                :filter-node-method="filterNode"
                ref="tree">
        </el-tree>
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
                    label: 'label'
                },
            }
        },
        methods: {
            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            }
        }
    }
</script>

<style scoped>

</style>
