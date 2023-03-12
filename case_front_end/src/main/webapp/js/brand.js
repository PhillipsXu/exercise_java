new Vue({
    el: '#root',
    data() {
        return {
            /* 表单加载 */
            loading: false,
            /* 表格数据 */
            tableData: [],
            /* 复选框数据 */
            multipleSelection: [],
            /* 搜索表单数据 */
            formInline: {
                companyName: '',
                brandName: '',
                status: ''
            },
            /* 弹窗数据 */
            dialogFormVisible: false,
            form: {
                id: '',
                brandName: '',
                companyName: '',
                address: '',
                ordered: '',
                status: ''
            },
            formLabelWidth: '120px',
            rules: {
                brandName: [
                    {required: true, message: '请输入品牌名称', trigger: 'blur'}
                ],
                companyName: [
                    {required: true, message: '请输入公司名称', trigger: 'blur'}
                ]
            },
            address: [],
            /* 当前页 */
            currentPage: 1,
            /* 当前条数 */
            size: 5,
            /* 总条数 */
            totalSize: 0
        }
    },
    methods: {
        /* 动态行样式 */
        tableRowClassName({row, rowIndex}) {
            if (rowIndex % 2 === 1) {
                return 'success-row';
            }
            return '';
        },
        /* 编辑方法 */
        handleEdit(index, row) {
            this.form = row;
            this.dialogFormVisible = true;
        },
        /* 删除方法 */
        handleDelete(index, row) {
            this.deleteById(row.id);
        },
        toggleSelection(rows) {
            if (rows) {
                rows.forEach(row => {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },
        /* 复选方法 */
        handleSelectionChange(val) {
            for (let i = 0; i < val.length; i++) {
                this.multipleSelection = val.map(item => {
                    return item.id;
                })
            }
        },
        /* 查询方法 */
        onSubmit() {
            console.log(this.formInline);
            this.selectByPage(this.currentPage, this.size, this.formInline);
        },
        /* 新增或修改方法 */
        onDialogSubmit(formName) {
            let url = '';
            let msg = '';
            if (this.form.id === '') {
                url = 'http://localhost:80/case_front_end_war_exploded/brand/insert';
                msg = '添加成功！'
            } else {
                url = 'http://localhost:80/case_front_end_war_exploded/brand/modify';
                msg = '修改成功！'
            }
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.dialogFormVisible = false;
                    axios({
                        url,
                        method: 'POST',
                        data: this.form
                    }).then(({data}) => {
                        if (data.status === '200') {
                            const h = this.$createElement;
                            this.$notify({
                                title: '提示消息',
                                message: h('i', {style: 'color: teal'}, msg),
                                type: 'success',
                                duration: 3000,
                                showClose: false
                            });
                        }
                    }).then(() => {
                        setTimeout(() => {
                            this.selectByPage(this.currentPage, this.size, this.formInline);
                        }, 3000)
                    }).finally(() => {
                        this.form = {
                            id: '',
                            brandName: '',
                            companyName: '',
                            address: '',
                            ordered: '',
                            status: ''
                        };
                    })
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        /* 重置表单 */
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        myResetForm() {
            this.formInline = {
                companyName: '',
                brandName: '',
                status: ''
            }
        },
        /* 取消提交 */
        onCancelSubmit() {
            this.form = {
                id: '',
                brandName: '',
                companyName: '',
                address: '',
                ordered: '',
                status: ''
            };
            this.dialogFormVisible = false;
        },
        /* 分页方法 */
        handleSizeChange(val) {
            this.size = val;
            this.currentPage = 1;
            this.selectByPage(this.currentPage, this.size, this.formInline);
        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.selectByPage(this.currentPage, this.size, this.formInline);
        },
        /* 分页查询 */
        selectByPage(currentPage, size, data) {
            this.loading = true;
            axios({
                url: 'http://localhost:80/case_front_end_war_exploded/brand/selectByTags?currentPage=' + currentPage + '&size=' + size,
                method: 'POST',
                data: data
            }).then((response) => {
                if (response.status === 200) {
                    this.tableData = response.data.rows;
                    this.totalSize = response.data.totalCount;
                    this.loading = false;
                }
            })
        },
        /* 批量删除 */
        deleteByIds() {
            this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                axios({
                    url: 'http://localhost:80/case_front_end_war_exploded/brand/delByIds',
                    method: 'POST',
                    data: this.multipleSelection
                }).then(({data}) => {
                    if (data.status === '200') {
                        this.$message({
                            message: '删除成功！',
                            type: 'success',
                            duration: 3000
                        });
                    }
                }).then(() => {
                    this.selectByPage(this.currentPage, this.size, this.formInline);
                }).catch(() => {
                    this.$message({
                        message: '删除失败！',
                        type: 'error',
                        duration: 1500
                    })
                })
            })
        },
        /* 单个删除 */
        deleteById(id) {
            this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                axios({
                    url: 'http://localhost:80/case_front_end_war_exploded/brand/delById?id=' + id,
                    method: 'GET'
                }).then(({data}) => {
                    if (data.status === '200') {
                        this.$message({
                            message: '删除成功！',
                            type: 'success',
                            duration: 3000
                        });
                    }
                }).then(() => {
                    this.selectByPage(this.currentPage, this.size, this.formInline);
                }).catch(() => {
                    this.$message({
                        message: '删除失败！',
                        type: 'error',
                        duration: 1500
                    })
                })
            })
        },
        /* 格式化表格 */
        showStatus(row, column, cellValue, index) {
            return row.status === 1 ? '启用' : '禁用';
        },
        /* 查询地区 */
        selectCountry() {
            axios({
                url: 'http://localhost:80/case_front_end_war_exploded/country',
                method: 'GET'
            }).then(({data}) => {
                console.log(data);
                this.address = data;
            })
        }
    },
    mounted() {
        this.currentPage = 1;
        this.size = 5;
        this.formInline = {
            companyName: '',
            brandName: '',
            status: ''
        }
        this.selectByPage(this.currentPage, this.size, this.formInline);
        this.selectCountry();
    },
    watch: {
        'form': {
            handler(newVal, oldVal) {
                // console.log(newVal)
            },
            deep: true,
            immediate: true
        }
    }
})